package pakkaaja.logiikka;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Abstrakti luokka eri pakkausalgoritmeja käyttäville purkajaluokille.
 */
public abstract class Purkaja {
    
    protected File tiedostoPakattu;
    private Tilasto tilasto;
    
    /**
     * Metodi hoitaa tiedoston purkamisen ja tilastoinnin. Palauttaa puretun tiedoston, kun purkaminen on valmis.
     * @return purettu tiedosto
     * @throws FileNotFoundException Heittää FileNotFoundException-poikkeuksen, jos tiedostoa ei löydy.
     * @throws IOException Heittää IOException-poikkeuksen, jos bittivirran kirjoittaminen ei onnistu.
     */
    public File puraTiedosto() throws FileNotFoundException, IOException {
        long aikaAlku = System.nanoTime();
        File tiedostoPurettu = this.suoritaPurkaminen();
        long aikaLoppu = System.nanoTime();
        double kesto = aikaLoppu - aikaAlku;
        this.tilasto = new Tilasto(kesto);
        return tiedostoPurettu;
    }
    
    /**
     * Abstrakti metodi hoitaa tiedoston purkamisen ja palauttaa puretun tiedoston, kun purkaminen on valmis.
     * @return purettu tiedosto
     * @throws FileNotFoundException Heittää FileNotFoundException-poikkeuksen, jos tiedostoa ei löydy.
     * @throws IOException Heittää IOException-poikkeuksen, jos bittivirran kirjoittaminen ei onnistu.
     */
    public abstract File suoritaPurkaminen() throws FileNotFoundException, IOException;
    
    /**
     * Metodi palauttaa tilastotiedot Tilasto-oliona.
     * @return tilastot
     */
    public Tilasto getTilasto() {
        return this.tilasto;
    }
    
    /**
     * Metodi muodostaa pakatun tiedoston nimestä purettavan tiedoston nimen ja palauttaa sen.
     * @param tiedostoPakattu purettava tiedosto
     * @return purettu tiedosto
     */
    protected File muodostaPurettuTiedosto(File tiedostoPakattu) {
        String polku = tiedostoPakattu.getParent() + "/";
        String nimi = tiedostoPakattu.getName().replace(".huff", "");
        nimi = nimi.replace(".lzw", "");
        String paate = "";
        int erotin = nimi.lastIndexOf('.');
        if (erotin != -1) {
            paate = nimi.substring(erotin);
            nimi = nimi.substring(0, erotin);
        }
        String purettuNimi = polku + nimi + "-purettu" + paate;
        File tiedostoPurettu = new File(purettuNimi);
        if (tiedostoPurettu.exists()) {
            throw new IllegalArgumentException("Tiedosto '" + tiedostoPurettu.getName() + "' on jo olemassa.\n"
                    + "Poista kyseinen tiedosto tai siirrä se talteen ennen samannimisen tiedoston purkamista.");
        }
        return tiedostoPurettu;
    }
    
}