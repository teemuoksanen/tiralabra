
package pakkaaja.logiikka.lzw;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import pakkaaja.logiikka.Pakkaaja;
import pakkaaja.io.BittiKirjoittaja;
import pakkaaja.tietorakenteet.hajautustaulu.Hajautustaulu;
import pakkaaja.tietorakenteet.lista.Lista;

/**
 * Luokka pakkaa sille syötteenä annetun tiedoston Lempel-Ziv-Welch -algoritmin (LZW) mukaisesti.
 */
public class LzwPakkaaja extends Pakkaaja {
    
    private final static int MAKSIMIKOKO_KOODISTO = 65535;
    private Hajautustaulu<String, Integer> koodisto;
    private int koodistonPituus;
    private Lista<Integer> pakattu;
    
    /**
     * Pakkaajan konstruktori.
     * @param tiedosto pakattava tiedosto
     */
    public LzwPakkaaja(File tiedosto) {
        this.tiedostoPakattava = tiedosto;
    }

    /**
     * Metodi hoitaa tiedoston pakkaamisen apumetodiensa avulla ja palauttaa pakatun tiedoston, kun pakkaminen on valmis.
     * @return pakattu tiedosto
     * @throws IllegalArgumentException Heittää IllegalArgumentException-poikkeuksen, jos samanniminen pakattu tiedosto on jo olemassa.
     * @throws FileNotFoundException Heittää FileNotFoundException-poikkeuksen, jos tiedostoa ei löydy.
     * @throws IOException Heittää IOException-poikkeuksen, jos bittivirran kirjoittaminen ei onnistu.
     */
    @Override
    public File suoritaPakkaaminen() throws FileNotFoundException, IOException {
        File tiedostoPakattu = muodostaPakattuTiedosto(this.tiedostoPakattava, "lzw");
        Lista<Character> tiedostoMerkkeina = lueTiedostoMerkkilistaksi(this.tiedostoPakattava);
        
        // Pakataan merkkilista LZW-algoritmilla
        this.koodisto = this.alustaKoodisto();
        this.pakattu = this.pakkaaMerkit(tiedostoMerkkeina);
        
        // Kirjoitetaan pakattu tiedosto
        BittiKirjoittaja kirjoittaja = new BittiKirjoittaja(tiedostoPakattu);
        kirjoitaTiedosto(kirjoittaja);
        kirjoittaja.close();
        
        return tiedostoPakattu;
    }
    
    /**
     * Apumetodi, joka kokoaa pakattavasta merkkilistasta koodiston ja pakattavan listan.
     */
    private Lista<Integer> pakkaaMerkit(Lista<Character> merkkilista) {
        Lista<Integer> pakattu = new Lista(merkkilista.koko());
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
        Hajautustaulu<String, Integer> koodisto = new Hajautustaulu(MAKSIMIKOKO_KOODISTO);
        for (int i = 0; i < 256; i++) {
            String merkki = "" + (char) i;
            koodisto.lisaa(merkki, i);
        }
        this.koodistonPituus = 256;
        return koodisto;
    }
    
}
