
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
 */
public class LzwPakkaaja implements Pakkaaja {
    
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
    }

    /**
     * Metodi hoitaa tiedoston pakkaamisen apumetodiensa avulla ja palauttaa pakatun tiedoston, kun pakkaminen on valmis.
     * @return pakattu tiedosto
     * @throws IllegalArgumentException Heittää TiedostoOlemassaPoikkeus-poikkeuksen, jos samanniminen pakattu tiedosto on jo olemassa.
     * @throws FileNotFoundException Heittää FileNotFoundException-poikkeuksen, jos tiedostoa ei löydy.
     * @throws IOException Heittää IOException-poikkeuksen, jos bittivirran kirjoittaminen ei onnistu.
     */
    public File pakkaaTiedosto() throws FileNotFoundException, IOException {
        if (tiedostoPakattu.exists()) {
            throw new IllegalArgumentException("Tiedosto '" + this.tiedostoPakattu.getName() + "' on jo olemassa.\n"
                    + "Poista kyseinen tiedosto tai siirrä se talteen ennen samannimisen tiedoston pakkaamista.");
        }
        this.tiedostoMerkkeina = this.lueTiedostoMerkkilistaksi();
        this.koodisto = this.alustaKoodisto();
        this.pakattu = this.pakkaaMerkit(this.tiedostoMerkkeina);
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
    private Lista<Integer> pakkaaMerkit(Lista<Character> merkkilista) {
        Lista<Integer> pakattu = new Lista();
        String merkkipuskuri = "" + (char) merkkilista.hae(0);
        
        for (int i = 1; i < merkkilista.koko(); i++) {
            Character nykyinenMerkki = (char) merkkilista.hae(i);
            String uusiPuskuri = merkkipuskuri + nykyinenMerkki;
            if (this.koodisto.sisaltaaAvaimen(uusiPuskuri)) {
                merkkipuskuri = uusiPuskuri;
            } else {
                pakattu.lisaa(this.koodisto.hae(merkkipuskuri));
                this.koodisto.lisaa(uusiPuskuri, this.koodistonPituus++);
                merkkipuskuri = "" + nykyinenMerkki;
                if (this.koodisto.koko() >= MAKSIMIKOKO_KOODISTO) {
                    this.koodisto = this.alustaKoodisto();
                }
            }
        }
        
        if (!merkkipuskuri.equals("")) {
            pakattu.lisaa(this.koodisto.hae(merkkipuskuri));
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
