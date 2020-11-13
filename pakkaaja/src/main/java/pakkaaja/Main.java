
package pakkaaja;

import pakkaaja.ui.Kayttoliittyma;

/**
 * Luokka käynnistää ohjelman käyttöliittymän.
 */
public class Main {

    /**
     * Käynnistää ohjelman käyttöliittymän.
     * @param args komentorivin argumentit
     */
    public static void main(String[] args) {
        Kayttoliittyma textui = new Kayttoliittyma();
        textui.kaynnista();
    }
    
}
