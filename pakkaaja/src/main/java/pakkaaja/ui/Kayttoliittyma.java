
package pakkaaja.ui;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.*;
import pakkaaja.logiikka.Pakkaaja;

/**
 * Pakkaajan tekstipohjainen käyttöliittymä.
 * @author teemu
 */
public class Kayttoliittyma {
    
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
                    
                    System.out.println("Anna pakattavan tiedoston nimi (sisältäen polun suhteessa juurihakemistoon):");
                    
                    tiedostonimi = lukija.nextLine();
                    tiedosto = new File(tiedostonimi);
                    System.out.println("");
                }
                
                try {
                    System.out.println("En osaa vielä pakata, mutta tässä on koodisto:");

                    Pakkaaja.tulostaKoodisto(tiedosto);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Pakkaaja.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            } else if (komento.equals("2")) {

                System.out.println("VIRHE: Purkaminen ei ole vielä käytössä.");
                
            } else {
                
                System.out.println("Virheellinen komento: " + komento);
                
            }
        
        }        
        
        lukija.close();
        System.out.println("Kiitos, kun käytit Pakkaajaa.");
        System.exit(0);
        
    }
}