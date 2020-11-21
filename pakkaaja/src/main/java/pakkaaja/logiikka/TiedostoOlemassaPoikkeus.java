
package pakkaaja.logiikka;

import java.io.File;

/**
 *
 */
public class TiedostoOlemassaPoikkeus extends Exception {
    
    private File tiedosto;
    private String tyyppi;
    
    public TiedostoOlemassaPoikkeus(File tiedosto, String tyyppi) {
        this.tiedosto = tiedosto;
        this.tyyppi = tyyppi;
    }
    
    @Override
    public String toString() {
        String ohje = "";

        if (tyyppi.equals("pakkaaja")) {
            ohje = "Poista kyseinen tiedosto tai siirrä se talteen ennen samannimisen tiedoston pakkaamista.";
        }
        if (tyyppi.equals("purkaja")) {
            ohje = "Poista kyseinen tiedosto tai siirrä se talteen ennen samannimisen tiedoston purkamista.";
        }
        
        return "Tiedosto '" + this.tiedosto.getName() + "' on jo olemassa.\n" + ohje;
    }
    
}
