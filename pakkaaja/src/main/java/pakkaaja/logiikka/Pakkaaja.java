
package pakkaaja.logiikka;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;
import pakkaaja.logiikka.HuffmanKoodaaja;
import pakkaaja.logiikka.huffmanpuu.Puu;

/**
 * Luokka pakkaa sille syötteenä annetun tiedoston Huffman-algoritmin mukaisesti.
 */
public class Pakkaaja {
    
    /**
     * Metodi luo aakoston (merkit ja niiden määrät) annetun tiedoston sisällöstä.
     * @param tiedosto tiedosto, jonka aakosto halutaan luoda
     * @return aakkosto ja kunkin aakkostoon sisältyvän merkin määrät hajautustauluna
     * @throws FileNotFoundException Heittää FileNotFoundException -poikkeuksen, jos tiedostoa ei löydy.
     */
    public static HashMap<Character, Integer> luoAakkosto(File tiedosto) throws FileNotFoundException {
        HashMap<Character, Integer> aakkosto = new HashMap<>();
        
        Scanner tiedostonlukija = new Scanner(tiedosto);
        tiedostonlukija.useDelimiter("");
        
        while (tiedostonlukija.hasNext()) {
            char merkki = tiedostonlukija.next().charAt(0);            
            int nykyinenMaara = aakkosto.getOrDefault(merkki, 0);
            aakkosto.put(merkki, nykyinenMaara + 1);
        }        
        
        return aakkosto;
    }
    
    /**
     * Metodi tulostaa syötteenä annetusta tiedostosta jokaisen merkin sekä kunkin merkin määrän ja Huffman-algoritmilla luodun binäärikoodin.
     * Tämä metodi on tarkoitus poistaa jatkokehityksen aikana, sillä sille ei ole lopullisessa ohjelmassa tarvetta.
     * @param tiedosto tiedosto, josta Huffman-koodisto haluataan luoda
     * @throws FileNotFoundException Heittää FileNotFoundException -poikkeuksen, jos tiedostoa ei löydy.
     */
    public static void tulostaKoodisto(File tiedosto) throws FileNotFoundException {
        HashMap<Character, Integer> aakkosto = luoAakkosto(tiedosto);
        HuffmanKoodaaja koodaaja = new HuffmanKoodaaja(aakkosto);
        HashMap<Character, String> koodisto = koodaaja.getKoodisto();
        
        System.out.println("MERKKI\tMÄÄRÄ\tKOODI");
        for (char merkki : aakkosto.keySet()) {
            System.out.println(merkki + "\t" + aakkosto.get(merkki) + "\t" + koodisto.get(merkki));
        }
    }
    
    
    
}
