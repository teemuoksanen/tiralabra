
package pakkaaja.logiikka.lzw;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import pakkaaja.logiikka.Purkaja;
import pakkaaja.tietorakenteet.hajautustaulu.Hajautustaulu;
import pakkaaja.tietorakenteet.lista.Lista;
import pakkaaja.io.*;

/**
 * Luokka purkaa sille syötteenä annetun Lempel-Ziv-Welch -algoritmilla (LZW) pakatun tiedoston.
 */
public class LzwPurkaja extends Purkaja {
    
    private final static int MAKSIMIKOKO_KOODISTO = 65535;
    private Hajautustaulu<Integer, String> koodisto;
    private int koodistonPituus;
        
    /**
     * Purkajan konstruktori, joka alustaa tarvittavat muuttujat ja oliot.
     * @param tiedosto purettava tiedosto
     */
    public LzwPurkaja(File tiedosto) {
        this.tiedostoPakattu = tiedosto;
        this.koodisto = this.alustaKoodisto();
    }
    
    /**
     * Metodi hoitaa pakatun tiedoston purkamisen apumetodiensa avulla ja ilmoittaa puretun tiedoston nimen, kun purkaminen on valmis.
     * @return purettu tiedosto
     * @throws IllegalArgumentException Heittää IllegalArgumentException-poikkeuksen, jos samanniminen purettu tiedosto on jo olemassa.
     * @throws FileNotFoundException Heittää FileNotFoundException-poikkeuksen, jos tiedostoa ei löydy.
     * @throws IOException Heittää IOException-poikkeuksen, jos bittivirran kirjoittaminen ei onnistu.
     */
    @Override
    public File suoritaPurkaminen() throws FileNotFoundException, IOException {
        File tiedostoPurettu = muodostaPurettuTiedosto(this.tiedostoPakattu);
        
        // Luetaan pakattu tiedosto
        BittiLukija lukija = new BittiLukija(this.tiedostoPakattu);
        Lista<Integer> pakatutMerkit = lueTiedosto(lukija);
        lukija.close();
        
        // Käydään pakattu merkistö läpi ja puretaan se
        Lista<Character> puretutMerkit = this.puraMerkit(pakatutMerkit);
        
        // Kirjoitetaan purettu tiedosto
        BittiKirjoittaja kirjoittaja = new BittiKirjoittaja(tiedostoPurettu);
        this.kirjoitaPurettu(puretutMerkit, kirjoittaja);
        kirjoittaja.close();
        
        return tiedostoPurettu;
    }
    
    /**
     * Apumetodi, joka alustaa LZW-algoritmin koodistoon ASCII-aakkoset.
     */
    private Hajautustaulu<Integer, String> alustaKoodisto() {
        Hajautustaulu<Integer, String> alustettuKoodisto = new Hajautustaulu(MAKSIMIKOKO_KOODISTO);
        for (int i = 0; i < 256; i++) {
            String merkki = "" + (char) i;
            alustettuKoodisto.lisaa(i, merkki);
        }
        this.koodistonPituus = 256;
        return alustettuKoodisto;
    }

    /**
     * Apumetodi, joka purkaa LZW-algoritmin avulla pakatut merkit ja palauttaa ne merkkilistana.
     */
    private Lista<Character> puraMerkit(Lista<Integer> pakattu) { 
        Lista<Character> purettu = new Lista();
        String koodipuskuri = "" + (char) (int) pakattu.hae(0);
        purettu.lisaa((char) (int) pakattu.hae(0));
        
        for (int i = 1; i < pakattu.koko(); i++) {
            int nykyinenKoodi = pakattu.hae(i);
            String uusiPuskuri;
            if (this.koodisto.sisaltaaAvaimen(nykyinenKoodi)) {
                uusiPuskuri = this.koodisto.hae(nykyinenKoodi);
            } else if (nykyinenKoodi == this.koodistonPituus) {
                uusiPuskuri = koodipuskuri + koodipuskuri.charAt(0);
            } else {
                throw new IllegalArgumentException("Virheellinen pakkaus kohdassa: " + nykyinenKoodi);
            }
            
            for (int j = 0; j < uusiPuskuri.length(); j++) {
                purettu.lisaa(uusiPuskuri.charAt(j));
            }
            
            this.koodisto.lisaa(this.koodistonPituus++, koodipuskuri + uusiPuskuri.charAt(0));
            
            if (this.koodisto.koko() >= MAKSIMIKOKO_KOODISTO) {
                this.koodisto = this.alustaKoodisto();
            }
            
            koodipuskuri = uusiPuskuri;
        }
        
        return purettu;
    }
    
    /**
     * Apumetodi, joka kirjoittaa puretut merkit tiedostoon.
     */
    private void kirjoitaPurettu(Lista<Character> purettu, BittiKirjoittaja kirjoittaja) throws FileNotFoundException, IOException {
        for (int i = 0; i < purettu.koko(); i++) {
            kirjoittaja.kirjoitaTavu(purettu.hae(i));
        }
    }
    
    /**
     * Apumetodi, joka lukee pakatun tiedoston sisällön merkkilistaksi.
     */
    private Lista<Integer> lueTiedosto(BittiLukija lukija) throws IOException {
        char ensimmainenTavu;
        Lista<Integer> merkit = new Lista();
        while ((int) (ensimmainenTavu = lukija.lueTavu()) != MAKSIMIKOKO_KOODISTO) {
            char toinenTavu = lukija.lueTavu();
            int merkki = (ensimmainenTavu << 8) | toinenTavu;
            merkit.lisaa(merkki);
        }
        return merkit;
    }
    
}
