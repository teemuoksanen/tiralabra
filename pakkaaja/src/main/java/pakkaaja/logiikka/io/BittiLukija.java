package pakkaaja.logiikka.io;

import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Luokka pakatun tiedoston lukemiseen bittimuodossa.
 */
public class BittiLukija {

    private InputStream stream;
    private int tavu;
    private int bittilaskuri;
    private int luetutBitit;

    /**
     * BittiKirjoittajan konstruktori, joka alustaa tarvitut muuttujat.
     * @param tiedosto luettava (pakattu) tiedosto
     * @throws FileNotFoundException Heittää FileNotFoundException -poikkeuksen, jos tiedostoa ei löydy.
     */
    public BittiLukija(File tiedosto) throws FileNotFoundException {
        this.stream = new BufferedInputStream(new FileInputStream(tiedosto));
        this.tavu = 0;
        this.bittilaskuri = 0;
        this.luetutBitit = 0;
    }

    /**
     * Metodi lukee tavullisen bittejä ja palauttaa tavua vastaavan merkin.
     * @return luettu tavu merkkinä
     * @throws IOException Heittää IOException -poikkeuksen, jos bittivirtaa ei voida lukea.
     */
    public char lueTavu() throws IOException {

        if (this.bittilaskuri == 0) {
            return (char) stream.read();
        } else {
            int edellinenTavu = this.tavu;
            
            edellinenTavu <<= (8 - this.bittilaskuri);
            this.tavu = this.stream.read();
            edellinenTavu |= (this.tavu >>> this.bittilaskuri);

            return (char) (edellinenTavu & 0xff);            
        }

    }

    /**
     * Metodi joka lukee yhdenbitin tavuvirrasta.
     * @return bitin sisältö (joko 0 tai 1) taikka -1, jos bittejä ei ole jäljellä
     * @throws IOException Heittää IOException -poikkeuksen, jos bittivirtaa ei voida lukea.
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
     * @throws IOException Heittää IOException -poikkeuksen, jos bittivirtaa ei voida lukea.
     */
    public void close() throws IOException {
        this.tavu = -1;
        this.bittilaskuri = 0;
        this.stream.close();
    }
}