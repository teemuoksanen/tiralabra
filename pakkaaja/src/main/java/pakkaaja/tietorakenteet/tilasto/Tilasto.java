package pakkaaja.tietorakenteet.tilasto;

/**
 * Pakkaamisen ja purkamisen tilastotietojen tallentamisesta vastaava olio.
 */
public class Tilasto {
    
    private long kesto;
    private double kokoAlkuperainen;
    private double kokoPakattu;
    
    /**
     * Tilasto-olion konstruktori, joka tallentaa vain keston; käytetään purkamisessa.
     * @param kesto purkamisen kesto millisekunneissa
     */
    public Tilasto(long kesto) {
        this.kesto = kesto;
        this.kokoAlkuperainen = 0;
        this.kokoPakattu = 0;
    }
    
    /**
     * Tilasto-olion konstruktori, joka tallentaa kaikki tiedot; käytetään pakkaamisessa.
     * @param kesto pakkaamisen kesto millisekunneissa
     * @param kokoAlkuperainen pakatun tiedoston alkuperäinen koko tavuina
     * @param kokoPakattu pakatun tiedoston koko pakkaamisen jälkeen tavuina
     */
    public Tilasto(long kesto, double kokoAlkuperainen, double kokoPakattu) {
        this.kesto = kesto;
        this.kokoAlkuperainen = kokoAlkuperainen;
        this.kokoPakattu = kokoPakattu;
    }
    
    /**
     * Palauttaa pakkaustehon (eli montako prosenttia alkuperäinen tiedosto pieneni).
     * @return pakkausteho prosentteina; jos ei tulosta, palautetaan 0
     */
    public double pakkausteho() {
        if (kokoPakattu == 0) {
            return 0;
        }
        return (1 - (kokoPakattu / kokoAlkuperainen)) * 100;
    }
    
    /**
     * Palauttaa pakkaamisen/purkamisen keston sekunteina.
     * @return kesto sekunteina
     */
    public double kestoSekunteina() {
        return kesto / 1e9;
    }

    /**
     * Palauttaa pakatun tiedoston alkuperäisen koon kilotavuina.
     * @return alkuperäisen tiedoston koko kilotavuina
     */
    public double kokoAlkuperainenKilotavuina() {
        return kokoAlkuperainen / 1024;
    }

    /**
     * Palauttaa pakatun tiedoston koon pakkaamisen jälkeen kilotavuina.
     * @return pakatun tiedoston koko kilotavuina
     */
    public double kokoPakattuKilotavuina() {
        return kokoPakattu / 1024;
    }

    /**
     * Palauttaa pakkaamisen/purkamisen keston millisekunteina.
     * @return kesto millisekunteina
     */
    public double getKesto() {
        return kesto;
    }

    /**
     * Palauttaa pakatun tiedoston alkuperäisen koon.
     * @return alkuperäisen tiedoston koko tavuina
     */
    public double getKokoAlkuperainen() {
        return kokoAlkuperainen;
    }

    /**
     * Palauttaa pakatun tiedoston koon pakkaamisen jälkeen.
     * @return pakatun tiedoston koko tavuina
     */
    public double getKokoPakattu() {
        return kokoPakattu;
    }
    
    @Override
    public String toString() {
        String tuloste = "TILASTOT:\n"
                + "Aikaa kului:\t\t" + this.kestoSekunteina() + " s";
        if (this.getKokoAlkuperainen() != 0.0) {
            tuloste += "\n"
                    + "Aluperäinen tiedosto:\t" + this.kokoAlkuperainenKilotavuina() + " kt\n"
                    + "Pakattu tiedosto:\t" + this.kokoPakattuKilotavuina() + " kt\n"
                    + "Pakkausteho:\t\t" + this.pakkausteho() + " %";
        }
        return tuloste;
    }
    
}
