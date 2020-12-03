package pakkaaja.logiikka.io;

import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Luokka pakatun tiedoston kirjoittamiseen bittimuodossa.
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
        this.stream = new BufferedOutputStream(new FileOutputStream(tiedosto));
        this.tavu = 0;
        this.bittilaskuri = 0;
    }

    /**
     * Metodi kirjoittaa parametrina annetun bitin.
     * @param bitti kirjoitettava bitti (0 tai 1)
     * @throws IOException Heittää IOException -poikkeuksen, jos bittivirran kirjoittaminen ei onnistu.
     */
    public void kirjoitaBitti(int bitti) throws IOException {
        if (bitti != 1 && bitti != 0) {
            throw new IllegalArgumentException("Kirjoitettava bitti voi olla vain 0 tai 1.");
        } else {
            tavu = (tavu << 1) | bitti;
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
     * @throws IOException Heittää IOException -poikkeuksen, jos bittivirran kirjoittaminen ei onnistu.
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
     * Metodi kirjoittaa parametrina annetun tavun binäärimuodossa.
     * @param tavu kirjoitettava tavu merkkinä
     * @throws IOException Heittää IOException -poikkeuksen, jos bittivirran kirjoittaminen ei onnistu.
     */
    public void kirjoitaTavu(char tavu) throws IOException {
        if (bittilaskuri == 0) {
            stream.write(tavu);
        } else {
            for (int i = 0; i < 8; i++) {
                int bitti = ((tavu >>> (8 - i - 1)) & 1);
                kirjoitaBitti(bitti);
            }
        }
    }

    /**
     * Metodi kirjoittaa parametrina annetun merkkijonon binäärimuodossa.
     * @param jono kirjoitettava merkkijono
     * @throws IOException Heittää IOException -poikkeuksen, jos bittivirran kirjoittaminen ei onnistu.
     */
    public void kirjoitaMerkkijono(String jono) throws IOException {
        stream.write(jono.getBytes());
    }
    
    /**
     * Metodi täyttää tarvittaessa kesken olevan tavun nollilla ja sulkee sen jälkeen tavuvirran.
     * @throws IOException Heittää IOException -poikkeuksen, jos bittivirran kirjoittaminen ei onnistu.
     */
    public void close() throws IOException {
        while (bittilaskuri > 0 && bittilaskuri <= 8) {
            kirjoitaBitti(0);
        }
        stream.close();
    }
}