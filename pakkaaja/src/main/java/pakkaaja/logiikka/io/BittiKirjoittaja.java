package pakkaaja.logiikka.io;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Luokka tiedoston kirjoittamiseen bittimuodossa.
 */
public class BittiKirjoittaja {

    private OutputStream stream;
    private int tavu;
    private int bittilaskuri;

    /**
     * BittiKirjoittajan konstruktori, joka alustaa tarvitut muuttujat.
     * @param tiedosto kirjoitettava (pakattu) tiedosto
     * @throws FileNotFoundException Heittää FileNotFoundException -poikkeuksen, jos tiedostoa ei löydy.
     */
    public BittiKirjoittaja(File tiedosto) throws FileNotFoundException {
        this.tavu = 0;
        this.bittilaskuri = 0;
        this.stream = new BufferedOutputStream(new FileOutputStream(tiedosto));
    }

    /**
     * Metodi kirjoittaa parametrina annetun bitin.
     * @param bit kirjoitettava bitti (0 tai 1)
     */
    public void kirjoitaBitti(int bit) throws IOException {
        if (bit != 1 && bit != 0) {
            System.out.println("VIRHE: Kirjoitettava bitti voi olla vain 0 tai 1.");
        } else {
            tavu = (tavu << 1) | bit;
            bittilaskuri++;
            
            /* Kun kokonainen tavu on valmis, kirjoita se ja nollaa */
            if (bittilaskuri == 8) {
                stream.write(tavu);
                tavu = 0;
                bittilaskuri = 0;
            }
        }
    }
    
    /**
     * Metodi kirjoittaa parametrina annetun bittijonon.
     * @param bittijono kirjoitettava bittijono merkkijonona
     */
    public void kirjoitaBittijono(String bittijono) throws IOException {
        for (int i = 0; i < bittijono.length(); i++) {
            char bitti = bittijono.charAt(i);
            if (bitti == '1') {
                kirjoitaBitti(1);
            } else {
                kirjoitaBitti(0);
            }
        }
    }

    /**
     * Metodi kirjoittaa parametrina annetun merkin binäärimuodossa.
     * Jos tavu on tyhjä, voidaan kirjoittaa koko merkki kerralla, 
     * muuten kirjoitetaan yksi bitti kerrallaan.
     * @param c kirjoitettava merkki
     */
    public void kirjoitaTavu(char c) throws IOException {
        if (bittilaskuri == 0) {
            stream.write(c);
        } else {
            for (int i = 0; i < 8; i++) {
                int bit = ((c >>> (8 - i - 1)) & 1);
                kirjoitaBitti(bit);
            }
        }
    }
    
    /**
     * Metodi täyttää tarvittaessa kesken olevan tavun nollilla ja sulkee
     * sen jälkeen tavuvirran.
     */
    public void close() throws IOException {
        while (bittilaskuri > 0 && bittilaskuri <= 8) {
            kirjoitaBitti(0);
        }

        stream.close();
    }
}