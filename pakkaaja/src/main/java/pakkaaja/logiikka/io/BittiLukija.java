package pakkaaja.logiikka.io;

import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import pakkaaja.tietorakenteet.lista.Lista;

/**
 * Luokka tiedoston lukemiseen bittimuodossa.
 */
public class BittiLukija {

    private InputStream stream;
    private int tavu;
    private int bittilaskuri;
    private int luetutBitit;

    /**
     * BittiKirjoittajan konstruktori, joka alustaa tarvitut muuttujat.
     * @param tiedosto luettava tiedosto
     * @throws FileNotFoundException Heittää FileNotFoundException-poikkeuksen, jos tiedostoa ei löydy.
     */
    public BittiLukija(File tiedosto) throws FileNotFoundException {
        this.stream = new BufferedInputStream(new FileInputStream(tiedosto));
        this.tavu = 0;
        this.bittilaskuri = 0;
        this.luetutBitit = 0;
    }

    /**
     * Metodi lukee tiedoston tavu kerrallaan ja palauttaa tavuja vastaavat merkit.
     * @return merkit Listana
     * @throws IOException Heittää IOException-poikkeuksen, jos bittivirtaa ei voida lukea.
     */
    public Lista<Character> lueTiedosto() throws IOException {
        Lista<Character> merkit = new Lista(256);
        int tavu;
        
        while ((tavu = this.stream.read()) != -1) {
            merkit.lisaa((char) tavu);
        }
        
        return merkit;
    }

    /**
     * Metodi lukee tavullisen bittejä ja palauttaa tavua vastaavan merkin.
     * @return luettu tavu merkkinä
     * @throws IOException Heittää IOException-poikkeuksen, jos bittivirtaa ei voida lukea.
     */
    public char lueTavu() throws IOException {
        if (this.bittilaskuri == 0) {
            return (char) this.stream.read();
        } else {
            int edellinenTavu = this.tavu;
            
            edellinenTavu <<= (8 - this.bittilaskuri);
            this.tavu = this.stream.read();
            edellinenTavu |= (this.tavu >>> this.bittilaskuri);

            return (char) (edellinenTavu & 0xff);            
        }
    }

    /**
     * Metodi joka lukee yhden bitin tavuvirrasta.
     * @return bitin sisältö (joko 0 tai 1) taikka -1, jos bittejä ei ole jäljellä
     * @throws IOException Heittää IOException-poikkeuksen, jos bittivirtaa ei voida lukea.
     */
    public int lueBitti() throws IOException {
        if (this.bittilaskuri == 0) {
            this.tavu = this.stream.read();
            this.bittilaskuri = 8;
        }
        
        if (this.tavu == -1) {
            return -1;
        }

        this.bittilaskuri--;
        return (this.tavu >>> this.bittilaskuri) & 1;
    }

    /**
     * Metodi sulkee tavuvirran.
     * @throws IOException Heittää IOException-poikkeuksen, jos bittivirtaa ei voida lukea.
     */
    public void close() throws IOException {
        this.tavu = -1;
        this.bittilaskuri = 0;
        this.stream.close();
    }
}