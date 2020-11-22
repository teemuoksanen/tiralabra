
package pakkaaja.logiikka;

import java.io.*;
import pakkaaja.tietorakenteet.hajautustaulu.Hajautustaulu;
import pakkaaja.tietorakenteet.lista.Lista;
import pakkaaja.logiikka.io.BittiKirjoittaja;
import pakkaaja.logiikka.io.BittiLukija;

/**
 * Luokka pakkaa sille syötteenä annetun tiedoston Huffman-algoritmin mukaisesti.
 */
public class Pakkaaja {
    
    private File tiedostoPakattava;
    private File tiedostoPakattu;
    private Hajautustaulu<Character, String> koodisto;
    private Lista<Object> avain;
    private Lista<Character> merkkilista;
    
    /**
     * Pakkaajan konstruktori, joka kutsuttaessa samalla luo aakoston, koodiston ja avaimen syötteenä annetusta tiedostosta.
     * @param tiedosto pakattava tiedosto
     * @throws FileNotFoundException Heittää FileNotFoundException-poikkeuksen, jos tiedostoa ei löydy.
     * @throws IOException Heittää IOException-poikkeuksen, jos bittivirran kirjoittaminen ei onnistu.
     */
    public Pakkaaja(File tiedosto) throws FileNotFoundException, IOException {
        this.tiedostoPakattava = tiedosto;
        String tiedostoPakattuNimi = this.tiedostoPakattava.getAbsoluteFile() + ".pakattu";
        this.tiedostoPakattu = new File(tiedostoPakattuNimi);
        this.merkkilista = lueTiedostoMerkkilistaksi();
        HuffmanKoodaaja huffman = new HuffmanKoodaaja(this.merkkilista);
        this.koodisto = huffman.getKoodisto();
        this.avain = huffman.getAvain();
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
        kirjoitaMerkkimaara(kirjoittaja);
        kirjoitaAvain(kirjoittaja);
        kirjoitaTiedosto(kirjoittaja);
        kirjoittaja.close();
        return this.tiedostoPakattu;
    }
    
    /**
     * Metodi palauttaa pakatun tiedoston.
     * @return pakattu tiedosto
     */
    public File getTiedostoPakattu() {
        return this.tiedostoPakattu;
    }
    
    /**
     * Apumetodi, joka laskee kunkin merkin määrät annetun tiedoston sisällöstä.
     */
    private Lista<Character> lueTiedostoMerkkilistaksi() throws FileNotFoundException, IOException {        
        BittiLukija lukija = new BittiLukija(this.tiedostoPakattava);
        Lista<Character> lista = lukija.lueTiedosto();
        lukija.close();
        return lista;
    }
    
    /**
     * Apumetodi, joka kirjoituttaa pakattavan tiedoston merkkimäärän pakattuun tiedstoon.
     */
    private void kirjoitaMerkkimaara(BittiKirjoittaja kirjoittaja) throws IOException {
        String merkitBitteina = String.format("%32s", Integer.toBinaryString(this.merkkilista.koko())).replaceAll(" ", "0");
        kirjoittaja.kirjoitaBittijono(merkitBitteina);
    }
    
    /**
     * Apumetodi, joka kirjoituttaa Huffman-puun avaimen pakattuun tiedstoon.
     */
    private void kirjoitaAvain(BittiKirjoittaja kirjoittaja) throws IOException {
        for (int i = 0; i < this.avain.koko(); i++) {
            Object o = this.avain.listaa()[i];
            if (o instanceof Integer) {
                kirjoittaja.kirjoitaBitti((int) o);
            } else {
                kirjoittaja.kirjoitaTavu((char) o);
            }
        }
    }
    
    /**
     * Apumetodi, joka kirjoituttaa varsinaisen tiedoston sisällön pakattuun tiedstoon.
     */
    private void kirjoitaTiedosto(BittiKirjoittaja kirjoittaja) throws IOException {
        for (int i = 0; i < this.merkkilista.koko(); i++) {
            char merkki = (char) this.merkkilista.hae(i);
            kirjoittaja.kirjoitaBittijono(this.koodisto.hae(merkki));
        }
    }
    
}
