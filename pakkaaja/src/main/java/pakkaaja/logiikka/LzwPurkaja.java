
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
    private Lista<String> purettu;
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
            throw new TiedostoOlemassaPoikkeus(tiedostoPurettu, "purkaja");
        }
        BittiLukija lukija = new BittiLukija(this.tiedostoPakattu);
        this.pakattu = lueTiedosto(lukija);
        lukija.close();
        this.purettu = this.puraMerkit(pakattu);
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
    
    /**
     * Apumetodi, joka alustaa LZW-algoritmin koodistoon ASCII-aakkoset.
     */
    private Hajautustaulu<Integer, String> alustaKoodisto() {
        Hajautustaulu<Integer, String> alustettuKoodisto = new Hajautustaulu();
        for (int i = 0; i < 256; i++) {
            String merkki = "" + (char) i;
            alustettuKoodisto.lisaa(i, merkki);
        }
        this.koodistonPituus = 256;
        return alustettuKoodisto;
    }

    /**
     * Apumetodi, joka purkaa LZW-algoritmin avulla kokonaislukulistana annetut merkit ja palauttaa ne merkkijonolistana.
     */    
    private Lista<String> puraMerkit(Lista<Integer> pakattu) {
        Lista<String> purettuLista = new Lista();
        String w = "" + (char) (int) pakattu.hae(0);
        purettuLista.lisaa(w);
        
        for (int i = 1; i < pakattu.koko(); i++) {
            int nykyinen = pakattu.hae(i);
            String purettuMerkki;
            if (this.koodisto.sisaltaaAvaimen(nykyinen)) {
                purettuMerkki = this.koodisto.hae(nykyinen);
            } else if (nykyinen == this.koodistonPituus) {
                purettuMerkki = w + w.charAt(0);
            } else {
                throw new IllegalArgumentException("Virheellinen pakkaus kohdassa: " + nykyinen);
            }
            
            purettuLista.lisaa(purettuMerkki);
            
            this.koodisto.lisaa(this.koodistonPituus++, w + purettuMerkki.charAt(0));

            if (this.koodisto.koko() >= MAKSIMIKOKO_KOODISTO) {
                this.koodisto = this.alustaKoodisto();
            }
            
            w = purettuMerkki;
        }
        
        return purettuLista;
    }
    
    /**
     * Apumetodi, joka kirjoittaa puretut merkit tiedostoon.
     */
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
