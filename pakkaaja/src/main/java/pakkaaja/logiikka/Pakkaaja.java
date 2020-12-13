package pakkaaja.logiikka;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import pakkaaja.io.BittiLukija;
import pakkaaja.tietorakenteet.lista.Lista;

/**
 * Abstrakti luokka eri pakkausalgoritmeja käyttäville pakkaajaluokille.
 */
public abstract class Pakkaaja {
    
    protected File tiedostoPakattava;
    private Tilasto tilasto;
    
    /**
     * Metodi hoitaa tiedoston pakkaamisen ja tilastoinnin. Palauttaa pakatun tiedoston, kun pakkaminen on valmis.
     * @return pakattu tiedosto
     * @throws FileNotFoundException Heittää FileNotFoundException-poikkeuksen, jos tiedostoa ei löydy.
     * @throws IOException Heittää IOException-poikkeuksen, jos bittivirran kirjoittaminen ei onnistu.
     */
    public File pakkaaTiedosto() throws FileNotFoundException, IOException {
        long aikaAlku = System.nanoTime();
        File tiedostoPakattu = this.suoritaPakkaaminen();
        long aikaLoppu = System.nanoTime();
        long kesto = aikaLoppu - aikaAlku;
        double kokoAlkuperainen = tiedostoPakattava.length();
        double kokoPakattu = tiedostoPakattu.length();
        this.tilasto = new Tilasto(kesto, kokoAlkuperainen, kokoPakattu);
        return tiedostoPakattu;
    }
    
    /**
     * Metodi palauttaa tilastotiedot Tilasto-oliona.
     * @return tilastot
     */
    public Tilasto getTilasto() {
        return this.tilasto;
    }
    
    /**
     * Abstrakti metodi hoitaa tiedoston pakkaamisen ja palauttaa pakatun tiedoston, kun pakkaminen on valmis.
     * @return pakattu tiedosto
     * @throws FileNotFoundException Heittää FileNotFoundException-poikkeuksen, jos tiedostoa ei löydy.
     * @throws IOException Heittää IOException-poikkeuksen, jos bittivirran kirjoittaminen ei onnistu.
     */
    public abstract File suoritaPakkaaminen() throws FileNotFoundException, IOException;
    
    /**
     * Metodi muodostaa alkuperäisen tiedoston nimestä pakattavan tiedoston nimen ja palauttaa sen.
     * @param tiedostoPakattava pakattava tiedosto
     * @param paate pakattavan tiedoston tunniste
     * @return pakattu tiedosto
     */
    protected File muodostaPakattuTiedosto(File tiedostoPakattava, String paate) {
        String pakattuNimi = tiedostoPakattava.getAbsoluteFile() + "." + paate;
        File tiedostoPakattu = new File(pakattuNimi);
        if (tiedostoPakattu.exists()) {
            throw new IllegalArgumentException("Tiedosto '" + tiedostoPakattu.getName() + "' on jo olemassa.\n"
                    + "Poista kyseinen tiedosto tai siirrä se talteen ennen samannimisen tiedoston pakkaamista.");
        }
        return tiedostoPakattu;
    }
    
    /**
     * Metodi lukee pakattavan tiedoston ja palauttaa sen merkkilistana.
     * @param tiedostoPakattava pakattava tiedosto
     * @return pakattava tiedosto merkkilistana
     * @throws FileNotFoundException Heittää FileNotFoundException-poikkeuksen, jos tiedostoa ei löydy.
     * @throws IOException Heittää IOException-poikkeuksen, jos bittivirran kirjoittaminen ei onnistu.
     */
    protected Lista<Character> lueTiedostoMerkkilistaksi(File tiedostoPakattava) throws FileNotFoundException, IOException {        
        BittiLukija lukija = new BittiLukija(tiedostoPakattava);
        Lista<Character> lista = lukija.lueTiedosto();
        lukija.close();
        return lista;
    }
    
}
