
package pakkaaja.logiikka;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import pakkaaja.tietorakenteet.puu.Lehti;
import pakkaaja.tietorakenteet.puu.Puu;
import pakkaaja.tietorakenteet.puu.Solmu;
import pakkaaja.logiikka.io.BittiKirjoittaja;
import pakkaaja.logiikka.io.BittiLukija;

/**
 * Luokka purkaa sille syötteenä annetun Huffman-algoritmilla pakatun tiedoston.
 */
public class Purkaja {
    
    private File tiedostoPakattu;
    private File tiedostoPurettu;
    private int merkit;
    private HashMap<String, Character> koodisto;
    private Puu puu;
    
    /**
     * Purkajan konstruktori, joka alustaa tarvittavat muuttujat ja oliot.
     * @param tiedosto purettava tiedosto
     * @throws FileNotFoundException Heittää FileNotFoundException -poikkeuksen, jos tiedostoa ei löydy.
     * @throws IOException Heittää IOException -poikkeuksen, jos bittivirran lukeminen tai kirjoittaminen ei onnistu.
     */
    public Purkaja(File tiedosto) {
        this.tiedostoPakattu = tiedosto;
        String tiedostoPurettuNimi = muodostaPurettuNimi();
        this.tiedostoPurettu = new File(tiedostoPurettuNimi);
        this.merkit = 0;
        this.koodisto = new HashMap<>();
    }
    
    private String muodostaPurettuNimi() {
        String polku = this.tiedostoPakattu.getParent() + "/";
        String nimi = this.tiedostoPakattu.getName().replace(".pakattu", "");
        String paate = "";
        int erotin = nimi.lastIndexOf('.');
        if (erotin != -1) {
            paate = nimi.substring(erotin);
            nimi = nimi.substring(0, erotin);
        }
        String uusiNimi = polku + nimi + "-purettu" + paate;
        return uusiNimi;
    }
    
    /**
     * Metodi hoitaa pakatun tiedoston purkamisen apumetodiensa avulla ja ilmoittaa puretun tiedoston nimen, kun purkaminen on valmis.
     * @throws FileNotFoundException Heittää FileNotFoundException -poikkeuksen, jos tiedostoa ei löydy.
     * @throws IOException Heittää IOException -poikkeuksen, jos bittivirran kirjoittaminen ei onnistu.
     */
    public void puraTiedosto() throws FileNotFoundException, IOException {
        if (this.tiedostoPurettu.exists()) {
            System.out.println("Tiedosto '" + this.tiedostoPurettu.getName() + "' löytyy jo purettuna.");
            System.out.println("Poista purettu tiedosto tai siirrä se talteen ennen samannimisen tiedoston purkamista.");
        } else {
            System.out.println("Puretaan tiedosto '" + this.tiedostoPakattu.getName() + "'...\n");
            BittiLukija lukija = new BittiLukija(this.tiedostoPakattu);
            lueMerkkimaara(lukija);
            this.puu = luePuu(lukija);
            luoKoodisto(this.puu, "");
            lueTiedosto(lukija);
            lukija.close();
            System.out.println("Tiedosto on purettu ja tallennettu nimellä:");
            System.out.println(this.tiedostoPurettu.getAbsoluteFile());
        }
    }
    
    /**
     * Apumetodi, joka lukee alkuperäisen tiedoston merkkimäärän pakattusta tiedostosta.
     */
    private void lueMerkkimaara(BittiLukija lukija) throws IOException {
        String merkitBitteina = "";
        for (int i = 0; i < 4; i++) {
            merkitBitteina += Integer.toBinaryString(lukija.lueTavu());
        }
        this.merkit = Integer.parseInt(merkitBitteina, 2);
    }
    
    /**
     * Apumetodi, joka lukee Huffman-algoritmin avaimen pakatusta tiedostosta ja muuntaa sen Huffman-puuksi.
     */
    private Puu luePuu(BittiLukija lukija) throws IOException {
        int bitti = lukija.lueBitti();
        if (bitti == 1) {
            char tavu = lukija.lueTavu();
            return new Lehti(tavu, 0);
        } else {
            return new Solmu(luePuu(lukija), luePuu(lukija));
        }
    }
    
    /**
     * Apumetodi, joka luo Huffman-puusta koodiston hajautustauluun.
     */
    private void luoKoodisto(Puu puu, String koodijono) {
        if (puu instanceof Lehti) {
            Lehti lehti = (Lehti) puu;
            this.koodisto.put(koodijono, lehti.merkki);
        } else {
            Solmu solmu = (Solmu) puu;
            
            String koodijonoVasen = koodijono + "0";
            luoKoodisto(solmu.vasen, koodijonoVasen);
            
            String koodijonoOikea = koodijono + "1";
            luoKoodisto(solmu.oikea, koodijonoOikea);
        }
    }
    
    /**
     * Apumetodi, joka lukee alkuperäisen tiedoston sisällön pakattusta tiedostosta.
     */
    private void lueTiedosto(BittiLukija lukija) throws IOException {
        BittiKirjoittaja kirjoittaja = new BittiKirjoittaja(tiedostoPurettu);
        
        for (int i = 0; i < this.merkit; i++) {
            String avain = String.valueOf(lukija.lueBitti());
            while (this.koodisto.get(avain) == null) {
                avain += lukija.lueBitti();
            }
            char merkki = this.koodisto.get(avain);
            kirjoittaja.kirjoitaTavu(merkki);
        }
        kirjoittaja.close();
    }
    
    /**
     * Metodi palauttaa puretun tiedoston.
     * @return purettu tiedosto
     */
    public File getTiedostoPurettu() {
        return this.tiedostoPurettu;
    }
    
}
