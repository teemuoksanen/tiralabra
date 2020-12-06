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
    
    /**
     * Metodi muodostaa pakatun tiedoston nimestä purettavan tiedoston nimen ja palauttaa sen.
     * @return puretun tiedoston nimi
     */
    default String muodostaPurettuNimi(File tiedostoPakattu) {
        String polku = tiedostoPakattu.getParent() + "/";
        String nimi = tiedostoPakattu.getName().replace(".huff", "");
        nimi = nimi.replace(".lzw", "");
        String paate = "";
        int erotin = nimi.lastIndexOf('.');
        if (erotin != -1) {
            paate = nimi.substring(erotin);
            nimi = nimi.substring(0, erotin);
        }
        String uusiNimi = polku + nimi + "-purettu" + paate;
        return uusiNimi;
    }
    
}