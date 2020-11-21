
package pakkaaja.logiikka;

import java.io.*;
import java.util.Scanner;
import pakkaaja.tietorakenteet.hajautustaulu.Hajautustaulu;
import pakkaaja.logiikka.io.BittiKirjoittaja;
import pakkaaja.tietorakenteet.lista.Lista;

/**
 * Luokka pakkaa sille syötteenä annetun tiedoston Huffman-algoritmin mukaisesti.
 */
public class Pakkaaja {
    
    private File tiedostoPakattava;
    private File tiedostoPakattu;
    private int merkit;
    private Hajautustaulu<Character, Integer> aakkosto;
    private HuffmanKoodaaja koodaaja;
    private Hajautustaulu<Character, String> koodisto;
    private Lista<Object> avain;
    
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
        this.merkit = 0;
        this.aakkosto = luoAakkosto();
        this.koodaaja = new HuffmanKoodaaja(this.aakkosto);
        this.koodisto = this.koodaaja.getKoodisto();
        this.avain = this.koodaaja.getAvain();
    }
    
    /**
     * Metodi luo aakoston (merkit ja niiden määrät) annetun tiedoston sisällöstä.
     * @return aakkosto ja kunkin aakkostoon sisältyvän merkin määrät hajautustauluna
     * @throws FileNotFoundException Heittää FileNotFoundException -poikkeuksen, jos tiedostoa ei löydy.
     */
    public Hajautustaulu<Character, Integer> luoAakkosto() throws FileNotFoundException {
        Hajautustaulu<Character, Integer> abc = new Hajautustaulu();
        
        Scanner tiedostonlukija = new Scanner(this.tiedostoPakattava);
        tiedostonlukija.useDelimiter("");
        
        while (tiedostonlukija.hasNext()) {
            char merkki = tiedostonlukija.next().charAt(0);            
            int nykyinenMaara = abc.haeTaiPalautaVakio(merkki, 0);
            abc.lisaa(merkki, nykyinenMaara + 1);
            this.merkit++;
        }
        
        return abc;
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
     * Apumetodi, joka kirjoituttaa pakattavan tiedoston merkkimäärän pakattuun tiedstoon.
     */
    private void kirjoitaMerkkimaara(BittiKirjoittaja kirjoittaja) throws IOException {
        String merkitBitteina = String.format("%32s", Integer.toBinaryString(this.merkit)).replaceAll(" ", "0");
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
        Scanner tiedostonlukija = new Scanner(this.tiedostoPakattava);
        tiedostonlukija.useDelimiter("");
        
        while (tiedostonlukija.hasNext()) {
            char merkki = tiedostonlukija.next().charAt(0);
            kirjoittaja.kirjoitaBittijono(this.koodisto.hae(merkki));
        }      
    }
    
    /**
     * Metodi palauttaa aakkoston.
     * @return aakkosto hajautustauluna
     */
    public Hajautustaulu<Character, Integer> getAakkosto() {
        return this.aakkosto;
    }
    
    /**
     * Metodi palauttaa pakatun tiedoston.
     * @return pakattu tiedosto
     */
    public File getTiedostoPakattu() {
        return this.tiedostoPakattu;
    }
    
}
