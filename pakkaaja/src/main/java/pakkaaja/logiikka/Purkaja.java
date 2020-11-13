
package pakkaaja.logiikka;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import pakkaaja.logiikka.huffmanpuu.Lehti;
import pakkaaja.logiikka.huffmanpuu.Puu;
import pakkaaja.logiikka.huffmanpuu.Solmu;
import pakkaaja.logiikka.io.BittiKirjoittaja;
import pakkaaja.logiikka.io.BittiLukija;

/**
 * 
 */
public class Purkaja {
    
    private File tiedostoPakattu;
    private File tiedostoPurettu;
    private int merkit;
    private HashMap<String, Character> koodisto;
    private Puu puu;
    
    public Purkaja(File tiedosto) {
        this.tiedostoPakattu = tiedosto;
        String tiedostoPurettuNimi = "puretut/" + this.tiedostoPakattu.getName().replace(".pakattu", "");
        this.tiedostoPurettu = new File(tiedostoPurettuNimi);
        this.merkit = 0;
        this.koodisto = new HashMap<>();
    }
    
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
    
    private void lueMerkkimaara(BittiLukija lukija) throws IOException {
        String merkitBitteina = "";
        for (int i = 0; i < 4; i++) {
            merkitBitteina += Integer.toBinaryString(lukija.lueTavu());
        }
        this.merkit = Integer.parseInt(merkitBitteina, 2);
    }
    
    private Puu luePuu(BittiLukija lukija) throws IOException {
        int bitti = lukija.lueBitti();
        if (bitti == 1) {
            char tavu = lukija.lueTavu();
            return new Lehti(tavu, 0);
        } else {
            return new Solmu(luePuu(lukija), luePuu(lukija));
        }
    }
    
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
    
}
