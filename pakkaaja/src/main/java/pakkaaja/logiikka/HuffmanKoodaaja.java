
package pakkaaja.logiikka;

import java.util.HashMap;
import java.util.PriorityQueue;
import pakkaaja.logiikka.huffmanpuu.*;

/**
 * Luokka muodostaa aakkostolle Huffman-koodiston (merkki- ja Huffman-binäärikoodi -pari hajautustauluna).
 */
public class HuffmanKoodaaja {
    
    private HashMap<Character, Integer> aakkosto;
    private HashMap<Character, String> koodisto;
    private Puu puu;
    
    /**
     * HuffmanKoodaajan konstruktori, joka kutsuttaessa samalla luo Huffman-puun sekä -koodiston sille syötteenä annetusta aakkostosta.
     * @param aakkosto  aakkosto, josta Huffman-koodisto muodostetaan
     */
    public HuffmanKoodaaja(HashMap<Character, Integer> aakkosto) {
        this.aakkosto = aakkosto;
        this.puu = rakennaPuu();
        this.koodisto = new HashMap<>();
        luoKoodisto(this.puu, "");
    }
    
    /**
     * Palauttaa Huffman-koodiston.
     * @return koodisto eli merkki- ja Huffman-binäärikoodi -pari hajautustauluna
     */
    public HashMap<Character, String> getKoodisto() {
        return this.koodisto;
    }
    
    private Puu rakennaPuu() {
        PriorityQueue<Puu> puut = new PriorityQueue<>();
        
        for (char merkki : this.aakkosto.keySet()) {
            Lehti lehti = new Lehti(merkki, this.aakkosto.get(merkki));
            puut.add(lehti);
        }
        
        while (puut.size() > 1) {
            Puu a = puut.poll();
            Puu b = puut.poll();
            
            Solmu uusiSolmu = new Solmu(a, b);
            puut.add(uusiSolmu);
        }
        
        return puut.poll();
    }

    private void luoKoodisto(Puu puu, String koodijono) {
        if (puu instanceof Lehti) {
            Lehti lehti = (Lehti) puu;
            koodisto.put(lehti.merkki, koodijono);
        } else {
            Solmu solmu = (Solmu) puu;
            
            String koodijonoVasen = koodijono + "0";
            luoKoodisto(solmu.vasen, koodijonoVasen);
            
            String koodijonoOikea = koodijono + "1";
            luoKoodisto(solmu.oikea, koodijonoOikea);
        }
    }
    
}
