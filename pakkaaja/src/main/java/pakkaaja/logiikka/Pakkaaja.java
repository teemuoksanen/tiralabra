
package pakkaaja.logiikka;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;
import pakkaaja.logiikka.HuffmanKoodaaja;
import pakkaaja.logiikka.huffmanpuu.Puu;

/**
 *
 * @author teemu
 */
public class Pakkaaja {
    
    public static HashMap<Character, Integer> luoAakkosto(File tiedosto) throws FileNotFoundException {
        HashMap<Character, Integer> aakkosto = new HashMap<>();
        
        Scanner tiedostonlukija = new Scanner(tiedosto);
        tiedostonlukija.useDelimiter("");
        
        while (tiedostonlukija.hasNext()) {
            char merkki = tiedostonlukija.next().charAt(0);            
            int nykyinenMaara = aakkosto.getOrDefault(merkki, 0);
            aakkosto.put(merkki, nykyinenMaara+1);
        }        
        
        return aakkosto;
    }
    
    public static void tulostaKoodisto(File tiedosto) throws FileNotFoundException {
        HashMap<Character, Integer> aakkosto = luoAakkosto(tiedosto);
        Puu puu = HuffmanKoodaaja.rakennaPuu(aakkosto);
        
        System.out.println("MERKKI\tMÄÄRÄ\tKOODI");
        HuffmanKoodaaja.tulostaKoodit(puu, "");
    }
    
    
    
}
