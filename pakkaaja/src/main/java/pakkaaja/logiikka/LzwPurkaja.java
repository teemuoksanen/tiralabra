
package pakkaaja.logiikka;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import pakkaaja.logiikka.io.BittiKirjoittaja;
import pakkaaja.logiikka.io.BittiLukija;
import pakkaaja.tietorakenteet.hajautustaulu.Hajautustaulu;
import pakkaaja.tietorakenteet.lista.Lista;

/**
 * LZW-PURKAJA - VIELÄ KESKEN
 * Luokka ourkaa sille syötteenä annetun Lempel-Ziv-Welch -algoritmilla (LZW) pakatun tiedoston.
 * HUOM! Luokka on vielä keskeneräinen - pakkaus ja purku eivät vielä toimi kunnolla!
 */
public class LzwPurkaja {
    
    private final static int MAKSIMIKOKO_KOODISTO = 65535;
    private File tiedostoPakattu;
    private File tiedostoPurettu;
    private Lista<Integer> pakattu;
    private Hajautustaulu<Integer, String> koodisto;
    private int koodistonPituus;
        
    /**
     * Purkajan konstruktori, joka alustaa tarvittavat muuttujat ja oliot.
     * @param tiedosto purettava tiedosto
     */
    public LzwPurkaja(File tiedosto) {
        this.tiedostoPakattu = tiedosto;
        String tiedostoPurettuNimi = muodostaPurettuNimi();
        this.tiedostoPurettu = new File(tiedostoPurettuNimi);
        this.koodisto = this.alustaKoodisto();
    }
    
    /**
     * Metodi hoitaa pakatun tiedoston purkamisen apumetodiensa avulla ja ilmoittaa puretun tiedoston nimen, kun purkaminen on valmis.
     * @return purettu tiedosto
     * @throws TiedostoOlemassaPoikkeus Heittää TiedostoOlemassaPoikkeus-poikkeuksen, jos samanniminen purettu tiedosto on jo olemassa.
     * @throws FileNotFoundException Heittää FileNotFoundException-poikkeuksen, jos tiedostoa ei löydy.
     * @throws IOException Heittää IOException-poikkeuksen, jos bittivirran kirjoittaminen ei onnistu.
     */
    public File puraTiedosto() throws FileNotFoundException, IOException, TiedostoOlemassaPoikkeus {
        if (tiedostoPurettu.exists()) {
            throw new TiedostoOlemassaPoikkeus(tiedostoPurettu, "pakkaaja");
        }
        BittiLukija lukija = new BittiLukija(this.tiedostoPakattu);
        this.pakattu = lueTiedosto(lukija);
        lukija.close();
        Lista<String> purettu = this.puraMerkit(pakattu);
        this.kirjoitaPurettu(purettu);
        return this.tiedostoPurettu;
    }
    
    /**
     * Apumetodi, joka lukee muodostaa puretun tiedoston nimen (".lzw"-pääte pois ja "-purettu" osaksi tiedostonimeä).
     */
    private String muodostaPurettuNimi() {
        String polku = this.tiedostoPakattu.getParent() + "/";
        String nimi = this.tiedostoPakattu.getName().replace(".lzw", "");
        String paate = "";
        int erotin = nimi.lastIndexOf('.');
        if (erotin != -1) {
            paate = nimi.substring(erotin);
            nimi = nimi.substring(0, erotin);
        }
        String uusiNimi = polku + nimi + "-purettu" + paate;
        return uusiNimi;
    }
    
    private Hajautustaulu<Integer, String> alustaKoodisto() {
        Hajautustaulu<Integer, String> koodisto = new Hajautustaulu();
        for (int i = 0; i < 256; i++) {
            String merkki = "" + (char) i;
            koodisto.lisaa(i, merkki);
        }
        this.koodistonPituus = 256;
        return koodisto;
    }
    
    private Lista<String> puraMerkit(Lista<Integer> pakattu) {
        Lista<String> purettu = new Lista();
        String w = "" + (char) (int) pakattu.hae(0);
        
        for (int i = 0; i < pakattu.koko(); i++) {
            int nykyinen = pakattu.hae(i);
            String purku;
            if (this.koodisto.sisaltaaAvaimen(nykyinen)) {
                purku = this.koodisto.hae(nykyinen);
            } else if (nykyinen == this.koodistonPituus) {
                purku = w + w.charAt(0);
            } else {
                throw new IllegalArgumentException("Virheellinen pakkaus kohdassa: " + nykyinen);
            }
            
            purettu.lisaa(purku);
            
            this.koodisto.lisaa(this.koodistonPituus++, w + purku.charAt(0));
            
            w = purku;
        }
        
        return purettu;
    }
    
    private void kirjoitaPurettu(Lista<String> purettu) throws FileNotFoundException, IOException {
        BittiKirjoittaja kirjoittaja = new BittiKirjoittaja(tiedostoPurettu);
        for (int i = 0; i < purettu.koko(); i++) {
            kirjoittaja.kirjoitaMerkkijono(purettu.hae(i));
        }
        kirjoittaja.close();
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
            // System.out.println((int) ensimmainenTavu + "\t" + (int) toinenTavu + "\t" + merkki + "\t" + (char) merkki);
            merkit.lisaa(merkki);
        }
        return merkit;
    }
    
}
