
package pakkaaja.logiikka;

import java.io.File;

/**
 * Poikkeus, joka voidaan heittää, kun luotava tiedosto onkin jo olemassa.
 */
public class TiedostoOlemassaPoikkeus extends Exception {
    
    private File tiedosto;
    private String heittaja;
    
    /**
     * Poikkeuksen konstruktori.
     * @param tiedosto virheen aiheuttanut tiedosto
     * @param heittaja poikkeuksen heittäjän tunniste (esim. "pakkaaja" tai "purkaja")
     */
    public TiedostoOlemassaPoikkeus(File tiedosto, String heittaja) {
        this.tiedosto = tiedosto;
        this.heittaja = heittaja;
    }
    
    /**
     * Palauttaa poikkeuksen antaman virheilmoituksen merkkijonona.
     * @return virheilmoitus merkkijonona
     */
    @Override
    public String toString() {
        String ohje = "";

        if (heittaja.equals("pakkaaja")) {
            ohje = "Poista kyseinen tiedosto tai siirrä se talteen ennen samannimisen tiedoston pakkaamista.";
        }
        if (heittaja.equals("purkaja")) {
            ohje = "Poista kyseinen tiedosto tai siirrä se talteen ennen samannimisen tiedoston purkamista.";
        }
        
        return "Tiedosto '" + this.tiedosto.getName() + "' on jo olemassa.\n" + ohje;
    }
    
}
