
package pakkaaja.logiikka.huffman;

import java.io.*;
import pakkaaja.logiikka.Purkaja;
import pakkaaja.tietorakenteet.keko.Lehti;
import pakkaaja.tietorakenteet.keko.Puu;
import pakkaaja.tietorakenteet.keko.Solmu;
import pakkaaja.io.BittiKirjoittaja;
import pakkaaja.io.BittiLukija;
import pakkaaja.tietorakenteet.hajautustaulu.Hajautustaulu;

/**
 * Luokka purkaa sille syötteenä annetun Huffman-algoritmilla pakatun tiedoston.
 */
public class HuffmanPurkaja implements Purkaja {
    
    private File tiedostoPakattu;
    private File tiedostoPurettu;
    private long merkit;
    private Hajautustaulu<String, Character> koodisto;
    private Puu puu;
    
    /**
     * Purkajan konstruktori, joka alustaa tarvittavat muuttujat ja oliot.
     * @param tiedosto purettava tiedosto
     */
    public HuffmanPurkaja(File tiedosto) {
        this.tiedostoPakattu = tiedosto;
        this.tiedostoPurettu = new File(muodostaPurettuNimi(this.tiedostoPakattu));
        this.merkit = 0;
        this.koodisto = new Hajautustaulu();
    }
    
    /**
     * Metodi hoitaa pakatun tiedoston purkamisen apumetodiensa avulla ja ilmoittaa puretun tiedoston nimen, kun purkaminen on valmis.
     * @return purettu tiedosto
     * @throws IllegalArgumentException Heittää IllegalArgumentException-poikkeuksen, jos samanniminen purettu tiedosto on jo olemassa.
     * @throws FileNotFoundException Heittää FileNotFoundException-poikkeuksen, jos tiedostoa ei löydy.
     * @throws IOException Heittää IOException-poikkeuksen, jos bittivirran kirjoittaminen ei onnistu.
     */
    @Override
    public File puraTiedosto() throws FileNotFoundException, IOException {
        if (tiedostoPurettu.exists()) {
            throw new IllegalArgumentException("Tiedosto '" + this.tiedostoPurettu.getName() + "' on jo olemassa.\n"
                    + "Poista kyseinen tiedosto tai siirrä se talteen ennen samannimisen tiedoston purkamista.");
        }
        BittiLukija lukija = new BittiLukija(this.tiedostoPakattu);
        lueMerkkimaara(lukija);
        this.puu = luePuu(lukija);
        luoKoodisto(this.puu, "");
        lueTiedosto(lukija);
        lukija.close();
        return this.tiedostoPurettu;
    }
    
    /**
     * Apumetodi, joka lukee alkuperäisen tiedoston merkkimäärän pakattusta tiedostosta.
     */
    private void lueMerkkimaara(BittiLukija lukija) throws IOException {
        int merkitBitteina = 0;
        for (int i = 0; i < 4; i++) {
            char tavu = lukija.lueTavu();
            merkitBitteina <<= 8;
            merkitBitteina |= tavu;
        }
        this.merkit = merkitBitteina;
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
            this.koodisto.lisaa(koodijono, lehti.getMerkki());
        } else {
            Solmu solmu = (Solmu) puu;
            
            String koodijonoVasen = koodijono + "0";
            luoKoodisto(solmu.getVasen(), koodijonoVasen);
            
            String koodijonoOikea = koodijono + "1";
            luoKoodisto(solmu.getOikea(), koodijonoOikea);
        }
    }
    
    /**
     * Apumetodi, joka lukee alkuperäisen tiedoston sisällön pakatusta tiedostosta.
     */
    private void lueTiedosto(BittiLukija lukija) throws IOException {
        BittiKirjoittaja kirjoittaja = new BittiKirjoittaja(tiedostoPurettu);
        
        for (int i = 0; i < this.merkit; i++) {
            String avain = String.valueOf(lukija.lueBitti());
            while (this.koodisto.hae(avain) == null) {
                avain += lukija.lueBitti();
            }
            char merkki = this.koodisto.hae(avain);
            kirjoittaja.kirjoitaTavu(merkki);
        }
        kirjoittaja.close();
    }
    
}
