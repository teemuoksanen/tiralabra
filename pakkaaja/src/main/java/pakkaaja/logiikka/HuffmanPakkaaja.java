
package pakkaaja.logiikka;

import java.io.*;
import pakkaaja.tietorakenteet.hajautustaulu.Hajautustaulu;
import pakkaaja.tietorakenteet.lista.Lista;
import pakkaaja.logiikka.io.BittiKirjoittaja;

/**
 * Luokka pakkaa sille syötteenä annetun tiedoston Huffman-algoritmin mukaisesti.
 */
public class HuffmanPakkaaja implements Pakkaaja {
    
    private File tiedostoPakattava;
    private Hajautustaulu<Character, String> koodisto;
    private Lista<Object> avain;
    private Lista<Character> merkkilista;
    
    /**
     * Pakkaajan konstruktori.
     * @param tiedosto pakattava tiedosto
     */
    public HuffmanPakkaaja(File tiedosto) {
        this.tiedostoPakattava = tiedosto;
    }
    
    /**
     * Metodi hoitaa tiedoston pakkaamisen apumetodiensa avulla ja palauttaa pakatun tiedoston, kun pakkaminen on valmis.
     * @return pakattu tiedosto
     * @throws IllegalArgumentException Heittää TiedostoOlemassaPoikkeus-poikkeuksen, jos samanniminen pakattu tiedosto on jo olemassa.
     * @throws FileNotFoundException Heittää FileNotFoundException-poikkeuksen, jos tiedostoa ei löydy.
     * @throws IOException Heittää IOException-poikkeuksen, jos bittivirran kirjoittaminen ei onnistu.
     */
    @Override
    public File pakkaaTiedosto() throws FileNotFoundException, IOException {
        File tiedostoPakattu = muodostaPakattuTiedosto(this.tiedostoPakattava, "huff");
        this.merkkilista = lueTiedostoMerkkilistaksi(this.tiedostoPakattava);
        HuffmanPakkaajaApuri huffman = new HuffmanPakkaajaApuri(this.merkkilista);
        this.koodisto = huffman.getKoodisto();
        this.avain = huffman.getAvain();
        BittiKirjoittaja kirjoittaja = new BittiKirjoittaja(tiedostoPakattu);
        kirjoitaMerkkimaara(kirjoittaja);
        kirjoitaAvain(kirjoittaja);
        kirjoitaTiedosto(kirjoittaja);
        kirjoittaja.close();
        return tiedostoPakattu;
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
