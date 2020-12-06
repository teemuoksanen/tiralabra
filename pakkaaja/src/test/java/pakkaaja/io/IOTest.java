package pakkaaja.io;

import java.io.*;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class IOTest {
    
    @Test(expected = IllegalArgumentException.class)
    public void epakelpoKirjoitettavaBittiHeittaaPoikkeuksen() throws FileNotFoundException, IOException {
        File tiedosto = new File("src/test/resources/bittikirjoittaja.txt");
        BittiKirjoittaja kirjoittaja = new BittiKirjoittaja(tiedosto);
        tiedosto.delete();
        kirjoittaja.kirjoitaBitti(2);
    }
    
    @Test
    public void tyhjastaTiedostostaLukeminenEiPalautaBittia() throws FileNotFoundException, IOException {
        File tiedosto = new File("src/test/resources/tyhja.txt");
        BittiLukija lukija = new BittiLukija(tiedosto);
        int tulos = lukija.lueBitti();
        assertEquals(tulos, -1);
    }
    
}
