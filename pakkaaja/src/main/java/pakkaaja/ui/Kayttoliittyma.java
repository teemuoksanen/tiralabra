
package pakkaaja.ui;

import java.io.*;
import java.util.Scanner;
import pakkaaja.logiikka.Pakkaaja;
import pakkaaja.logiikka.Purkaja;

/**
 * Pakkaajan tekstipohjainen käyttöliittymä.
 * @author teemu
 */
public class Kayttoliittyma {
    
    private Pakkaaja pakkaaja;
    private Purkaja purkaja;
    
    /**
     * Tekstikäyttöliittymän sisältävä luokka.
     */
    public void kaynnista() {
        
        Scanner lukija = new Scanner(System.in);
        
        System.out.println("TERVETULOA KÄYTTÄMÄÄN PAKKAAJAA!");
        
        while (true) {
            
            System.out.println("\nValitse, mitä haluaisit tehdä:\n");
            System.out.println("[1]\t pakkaa tiedosto");
            System.out.println("[2]\t pura pakattu tiedosto");
            System.out.println("[q]\t poistu");
            System.out.println("");
            System.out.println("Komento + enter: ");

            String komento = lukija.nextLine();
            System.out.println("");

            if (komento.equals("q")) {
                
                break;
                
            } else if (komento.equals("1")) {
                
                String tiedostonimi = "tyhja";
                File tiedosto = null;
                
                while (tiedosto == null || !tiedosto.exists()) {
                    if (tiedosto != null) {
                        System.out.println("VIRHE: Tiedostoa '" + tiedostonimi + "' ei löytynyt.\n");
                    }
                    
                    System.out.println("Anna pakattavan tiedoston nimi (ja polku suhteessa ohjelman juurihakemistoon):");
                    
                    tiedostonimi = lukija.nextLine();
                    tiedosto = new File(tiedostonimi);
                    System.out.println("");
                }
                
                try {
                    pakkaaja = new Pakkaaja(tiedosto);
                    pakkaaja.pakkaaTiedosto();
                } catch (Exception ex) {
                    System.out.println("VIRHE: " + ex.toString());
                    ex.printStackTrace();
                }
                
            } else if (komento.equals("2")) {

                String tiedostonimi = "tyhja";
                File tiedosto = null;
                
                while (tiedosto == null || !tiedosto.exists()) {
                    if (tiedosto != null) {
                        System.out.println("VIRHE: Pakattua tiedostoa '" + tiedostonimi + "' ei löytynyt.\n");
                    }
                    
                    System.out.println("Anna purettavan tiedoston nimi (ja polku suhteessa ohjelman juurihakemistoon):");
                    
                    tiedostonimi = lukija.nextLine();
                    tiedosto = new File(tiedostonimi);
                    System.out.println("");
                }
                
                try {
                    purkaja = new Purkaja(tiedosto);
                    purkaja.puraTiedosto();
                } catch (Exception ex) {
                    System.out.println("VIRHE! " + ex.toString());
                }
                
            } else {
                
                System.out.println("Virheellinen komento: " + komento);
                
            }
        
        }        
        
        lukija.close();
        System.out.println("Kiitos, kun käytit Pakkaajaa.");
        System.exit(0);
        
    }
}