package pakkaaja.logiikka;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Rajapinta eri pakkausalgoritmeja käyttäville purkajaluokille.
 */
public interface Purkaja {
    
    /**
     * Metodi hoitaa tiedoston purkamisen ja palauttaa puretun tiedoston, kun purkaminen on valmis.
     * @return purettu tiedosto
     * @throws FileNotFoundException Heittää FileNotFoundException-poikkeuksen, jos tiedostoa ei löydy.
     * @throws IOException Heittää IOException-poikkeuksen, jos bittivirran kirjoittaminen ei onnistu.
     */
    File puraTiedosto() throws FileNotFoundException, IOException;
    
}
