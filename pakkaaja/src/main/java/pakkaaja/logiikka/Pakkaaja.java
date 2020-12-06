package pakkaaja.logiikka;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Rajapinta eri pakkausalgoritmeja käyttäville pakkaajaluokille.
 */
public interface Pakkaaja {
    
    /**
     * Metodi hoitaa tiedoston pakkaamisen  ja palauttaa pakatun tiedoston, kun pakkaminen on valmis.
     * @return pakattu tiedosto
     * @throws FileNotFoundException Heittää FileNotFoundException-poikkeuksen, jos tiedostoa ei löydy.
     * @throws IOException Heittää IOException-poikkeuksen, jos bittivirran kirjoittaminen ei onnistu.
     */
    File pakkaaTiedosto() throws FileNotFoundException, IOException;
    
}
