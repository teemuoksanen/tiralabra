
package pakkaaja.logiikka;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Luokka pakkaa sille syötteenä annetun tiedoston Huffman-algoritmin mukaisesti.
 */
public class Pakkaaja {
    
    private String tiedostonimi;
    private File tiedosto;
    private HashMap<Character, Integer> aakkosto;
    private HuffmanKoodaaja koodaaja;
    private HashMap<Character, String> koodisto;
    
    /**
     * Pakkaajan konstruktori, joka kutsuttaessa samalla luo aakoston syötteenä annetusta tiedostosta.
     * @param tiedostonimi pakattavan tiedoston nimi
     * @throws FileNotFoundException Heittää FileNotFoundException -poikkeuksen, jos tiedostoa ei löydy.
     */
    public Pakkaaja(String tiedostonimi) throws FileNotFoundException {
        this.tiedostonimi = tiedostonimi;
        this.tiedosto = new File(tiedostonimi);
        this.aakkosto = luoAakkosto();
        this.koodaaja = new HuffmanKoodaaja(this.aakkosto);
        this.koodisto = koodaaja.getKoodisto();
        
    }
    
    /**
     * Metodi luo aakoston (merkit ja niiden määrät) annetun tiedoston sisällöstä.
     * @return aakkosto ja kunkin aakkostoon sisältyvän merkin määrät hajautustauluna
     * @throws FileNotFoundException Heittää FileNotFoundException -poikkeuksen, jos tiedostoa ei löydy.
     */
    public HashMap<Character, Integer> luoAakkosto() throws FileNotFoundException {
        HashMap<Character, Integer> abc = new HashMap<>();
        
        Scanner tiedostonlukija = new Scanner(this.tiedosto);
        tiedostonlukija.useDelimiter("");
        
        while (tiedostonlukija.hasNext()) {
            char merkki = tiedostonlukija.next().charAt(0);            
            int nykyinenMaara = abc.getOrDefault(merkki, 0);
            abc.put(merkki, nykyinenMaara + 1);
        }        
        
        return abc;
    }
    
    /**
     * Metodi tulostaa syötteenä annetusta tiedostosta jokaisen merkin sekä kunkin merkin määrän ja Huffman-algoritmilla luodun binäärikoodin.
     * Tämä metodi on tarkoitus poistaa jatkokehityksen aikana, sillä sille ei ole lopullisessa ohjelmassa tarvetta.
     * @throws FileNotFoundException Heittää FileNotFoundException -poikkeuksen, jos tiedostoa ei löydy.
     */
    public void tulostaKoodisto() throws FileNotFoundException {
        System.out.println("MERKKI\tMÄÄRÄ\tKOODI");
        for (char merkki : this.aakkosto.keySet()) {
            System.out.println(merkki + "\t" + this.aakkosto.get(merkki) + "\t" + this.koodisto.get(merkki));
        }
    }
    
    public HashMap<Character, Integer> getAakkosto() {
        return this.aakkosto;
    }
    
}
