
package pakkaaja.logiikka;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import pakkaaja.logiikka.io.BittiKirjoittaja;

/**
 * Luokka pakkaa sille syötteenä annetun tiedoston Huffman-algoritmin mukaisesti.
 */
public class Pakkaaja {
    
    private File tiedostoPakattava;
    private File tiedostoPakattu;
    private int merkit;
    private HashMap<Character, Integer> aakkosto;
    private HuffmanKoodaaja koodaaja;
    private HashMap<Character, String> koodisto;
    private List<Object> avain;
    
    /**
     * Pakkaajan konstruktori, joka kutsuttaessa samalla luo aakoston, koodiston ja avaimen syötteenä annetusta tiedostosta.
     * @param tiedosto pakattava tiedosto
     * @throws FileNotFoundException Heittää FileNotFoundException -poikkeuksen, jos tiedostoa ei löydy.
     */
    public Pakkaaja(File tiedosto) throws FileNotFoundException, IOException {
        this.tiedostoPakattava = tiedosto;
        String tiedostoPakattuNimi = "pakatut/" + this.tiedostoPakattava.getName() + ".pakattu";
        this.tiedostoPakattu = new File(tiedostoPakattuNimi);
        this.merkit = 0;
        this.aakkosto = luoAakkosto();
        this.koodaaja = new HuffmanKoodaaja(this.aakkosto);
        this.koodisto = this.koodaaja.getKoodisto();
        this.avain = this.koodaaja.getAvain();
    }
    
    /**
     * Metodi luo aakoston (merkit ja niiden määrät) annetun tiedoston sisällöstä.
     * @return aakkosto ja kunkin aakkostoon sisältyvän merkin määrät hajautustauluna
     * @throws FileNotFoundException Heittää FileNotFoundException -poikkeuksen, jos tiedostoa ei löydy.
     */
    public HashMap<Character, Integer> luoAakkosto() throws FileNotFoundException {
        HashMap<Character, Integer> abc = new HashMap<>();
        
        Scanner tiedostonlukija = new Scanner(this.tiedostoPakattava);
        tiedostonlukija.useDelimiter("");
        
        while (tiedostonlukija.hasNext()) {
            char merkki = tiedostonlukija.next().charAt(0);            
            int nykyinenMaara = abc.getOrDefault(merkki, 0);
            abc.put(merkki, nykyinenMaara + 1);
            this.merkit++;
        }
        
        return abc;
    }
    
    public void pakkaaTiedosto() throws FileNotFoundException, IOException {
        if (tiedostoPakattu.exists()) {
            System.out.println("Tiedosto '" + this.tiedostoPakattava.getName() + "' löytyy jo pakattuna.");
            System.out.println("Poista pakattu tiedosto tai siirrä se talteen ennen samannimisen tiedoston pakkaamista.");
        } else {
            System.out.println("Aloitetaan pakatun tiedoston tallentaminen.");
            BittiKirjoittaja kirjoittaja = new BittiKirjoittaja(this.tiedostoPakattu);
            kirjoitaMerkkimaara(kirjoittaja);
            kirjoitaAvain(kirjoittaja);
            kirjoitaTiedosto(kirjoittaja);
            kirjoittaja.close();
            System.out.println("Pakattu tiedosto on tallennettu.");
        }
    }
    
    private void kirjoitaMerkkimaara(BittiKirjoittaja kirjoittaja) throws IOException {
        System.out.print("Kirjoitetaan merkkimäärä... ");
        String merkitBitteina = String.format("%32s", Integer.toBinaryString(this.merkit)).replaceAll(" ", "0");
        // int takaisin = Integer.parseInt(merkitBitteina, 2);
        kirjoittaja.kirjoitaBittijono(merkitBitteina);
        System.out.println("Kirjoitettu.");
    }
    
    private void kirjoitaAvain(BittiKirjoittaja kirjoittaja) throws IOException {
        System.out.print("Kirjoitetaan koodistoa... ");
        
        for (Object o : this.avain) {
            if (o instanceof Integer) {
                kirjoittaja.kirjoitaBitti((int) o);
            } else {
                kirjoittaja.kirjoitaTavu((char) o);
            }
        }
        
        // Välimerkkinä toistaiseksi kaksi ykköstä
        kirjoittaja.kirjoitaBitti(1);
        kirjoittaja.kirjoitaBitti(1);
        
        System.out.println("Kirjoitettu.");
    }
    
    private void kirjoitaTiedosto(BittiKirjoittaja kirjoittaja) throws IOException {
        System.out.print("Kirjoitetaan pakattua tiedostoa... ");
        Scanner tiedostonlukija = new Scanner(this.tiedostoPakattava);
        tiedostonlukija.useDelimiter("");
        
        while (tiedostonlukija.hasNext()) {
            char merkki = tiedostonlukija.next().charAt(0);
            kirjoittaja.kirjoitaBittijono(this.koodisto.get(merkki));
        }
        System.out.println("Kirjoitettu.");        
    }
    
    /**
     * Metodi tulostaa syötteenä annetusta tiedostosta jokaisen merkin sekä kunkin merkin määrän ja Huffman-algoritmilla luodun binäärikoodin.
     * Tämä metodi on tarkoitus poistaa jatkokehityksen aikana, sillä sille ei ole lopullisessa ohjelmassa tarvetta.
     * @throws FileNotFoundException Heittää FileNotFoundException -poikkeuksen, jos tiedostoa ei löydy.
     */
    public void tulostaKoodisto() throws FileNotFoundException {
        System.out.println("MERKKI\tMÄÄRÄ\tKOODI");
        for (char merkki : this.aakkosto.keySet()) {
            System.out.println(merkki + "\t" + this.aakkosto.get(merkki) + "\t" + this.koodaaja.getKoodisto().get(merkki));
        }
        System.out.println("\nHuffman-avain: " + this.koodaaja.getAvain());
    }
    
    public HashMap<Character, Integer> getAakkosto() {
        return this.aakkosto;
    }
    
}
