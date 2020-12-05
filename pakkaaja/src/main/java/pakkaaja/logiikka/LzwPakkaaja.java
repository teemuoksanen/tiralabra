
package pakkaaja.logiikka;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import pakkaaja.logiikka.io.BittiKirjoittaja;
import pakkaaja.logiikka.io.BittiLukija;
import pakkaaja.tietorakenteet.hajautustaulu.Hajautustaulu;
import pakkaaja.tietorakenteet.lista.Lista;

/**
 * Luokka pakkaa sille syötteenä annetun tiedoston Lempel-Ziv-Welch -algoritmin (LZW) mukaisesti.
 * HUOM! Luokka on vielä keskeneräinen - pakkaus ja purku eivät vielä toimi kunnolla!
 */
public class LzwPakkaaja {
    
    private final static int MAKSIMIKOKO_KOODISTO = 65535;
    private File tiedostoPakattava;
    private File tiedostoPakattu;
    private Lista<Character> tiedostoMerkkeina;
    private Hajautustaulu<String, Integer> koodisto;
    private int koodistonPituus;
    private Lista<Integer> pakattu;
    
    /**
     * Pakkaajan konstruktori, joka kutsuttaessa samalla luo koodiston syötteenä annetusta tiedostosta ja lukee tiedoston merkkilistaksi.
     * @param tiedosto pakattava tiedosto
     * @throws FileNotFoundException Heittää FileNotFoundException-poikkeuksen, jos tiedostoa ei löydy.
     * @throws IOException Heittää IOException-poikkeuksen, jos bittivirran kirjoittaminen ei onnistu.
     */
    public LzwPakkaaja(File tiedosto) throws IOException {
        this.tiedostoPakattava = tiedosto;
        String tiedostoPakattuNimi = this.tiedostoPakattava.getAbsoluteFile() + ".lzw";
        this.tiedostoPakattu = new File(tiedostoPakattuNimi);
        this.tiedostoMerkkeina = this.lueTiedostoMerkkilistaksi();
        this.koodisto = this.alustaKoodisto();
        this.pakattu = this.pakkaaMerkit(this.tiedostoMerkkeina);
    }

    /**
     * Metodi hoitaa tiedoston pakkaamisen apumetodiensa avulla ja ilmoittaa pakatun tiedoston nimen, kun pakkaminen on valmis.
     * @return pakattu tiedosto
     * @throws TiedostoOlemassaPoikkeus Heittää TiedostoOlemassaPoikkeus-poikkeuksen, jos samanniminen pakattu tiedosto on jo olemassa.
     * @throws FileNotFoundException Heittää FileNotFoundException-poikkeuksen, jos tiedostoa ei löydy.
     * @throws IOException Heittää IOException-poikkeuksen, jos bittivirran kirjoittaminen ei onnistu.
     */
    public File pakkaaTiedosto() throws FileNotFoundException, IOException, TiedostoOlemassaPoikkeus {
        if (tiedostoPakattu.exists()) {
            throw new TiedostoOlemassaPoikkeus(tiedostoPakattu, "pakkaaja");
        }
        BittiKirjoittaja kirjoittaja = new BittiKirjoittaja(this.tiedostoPakattu);
        kirjoitaTiedosto(kirjoittaja);
        kirjoittaja.close();
        return this.tiedostoPakattu;
    }
    
    /**
     * Apumetodi, joka lukee annetun tiedoston sisällön merkkilistaksi.
     */
    private Lista<Character> lueTiedostoMerkkilistaksi() throws FileNotFoundException, IOException {        
        BittiLukija lukija = new BittiLukija(this.tiedostoPakattava);
        Lista<Character> lista = lukija.lueTiedosto();
        lukija.close();
        return lista;
    }
    
    /**
     * Apumetodi, joka kokoaa pakattavasta merkkilistasta koodiston ja pakattavan listan.
     */
    // HUOM! Metodi on yli 20 riviä - uudelleenfaktoroi, jos mahdollista.
    private Lista<Integer> pakkaaMerkit(Lista<Character> merkkilista) {
        Lista<Integer> pakattu = new Lista();
        String w = "" + (char) merkkilista.hae(0);
        
        for (int i = 1; i < merkkilista.koko(); i++) {
            Character nykyinen = (char) merkkilista.hae(i);
            String wc = w + nykyinen;
            if (this.koodisto.sisaltaaAvaimen(wc)) {
                w = wc;
            } else {
                pakattu.lisaa(this.koodisto.hae(w));
                this.koodisto.lisaa(wc, this.koodistonPituus++);
                w = "" + nykyinen;
                if (this.koodisto.koko() >= MAKSIMIKOKO_KOODISTO) {
                    this.koodisto = this.alustaKoodisto();
                }
            }
        }
        
        if (!w.equals("")) {
            pakattu.lisaa(this.koodisto.hae(w));
        }
        
        return pakattu;
    }
    
    /**
     * Apumetodi, joka kirjoituttaa varsinaisen tiedoston sisällön pakattuun tiedstoon.
     */
    private void kirjoitaTiedosto(BittiKirjoittaja kirjoittaja) throws IOException {
        for (int i = 0; i < this.pakattu.koko(); i++) {
            char merkki = (char) (int) this.pakattu.hae(i);
            int ensimmainenTavu = merkki >> 8;
            kirjoittaja.kirjoitaTavu((char) ensimmainenTavu);
            kirjoittaja.kirjoitaTavu(merkki);
        }
    }
    
    /**
     * Apumetodi, joka alustaa peruskoodiston (ensimmäiset 256 merkkiä).
     */
    private Hajautustaulu<String, Integer> alustaKoodisto() {
        Hajautustaulu<String, Integer> koodisto = new Hajautustaulu();
        for (int i = 0; i < 256; i++) {
            String merkki = "" + (char) i;
            koodisto.lisaa(merkki, i);
        }
        this.koodistonPituus = 256;
        return koodisto;
    }
    
}
