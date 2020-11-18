
package pakkaaja.logiikka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import pakkaaja.logiikka.hajautustaulu.Hajautustaulu;
import pakkaaja.logiikka.huffmanpuu.*;

/**
 * Luokka muodostaa aakkostolle Huffman-koodiston (merkki- ja Huffman-binäärikoodi -pari hajautustauluna).
 */
public class HuffmanKoodaaja {
    
    private HashMap<Character, Integer> aakkosto;
    private Hajautustaulu<Character, String> koodisto;
    private Puu puu;
    private List<Object> avain;
    
    /**
     * HuffmanKoodaajan konstruktori, joka kutsuttaessa samalla luo Huffman-puun sekä -koodiston sille syötteenä annetusta aakkostosta.
     * @param aakkosto  aakkosto, josta Huffman-koodisto muodostetaan
     */
    public HuffmanKoodaaja(HashMap<Character, Integer> aakkosto) {
        this.aakkosto = aakkosto;
        this.puu = rakennaPuu();
        this.koodisto = new Hajautustaulu<>();
        this.avain = new ArrayList<>();
        luoKoodisto(this.puu, "");
    }
    
    /**
     * Palauttaa Huffman-koodiston.
     * @return koodisto eli merkki- ja Huffman-binäärikoodi -pari hajautustauluna
     */
    public Hajautustaulu<Character, String> getKoodisto() {
        return this.koodisto;
    }
    /**
     * Palauttaa Huffman-avaimen.
     * @return avain eli Huffman-puu lyhyeksi koodattuna
     */
    public List<Object> getAvain() {
        return this.avain;
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
            koodisto.lisaa(lehti.merkki, koodijono);
            avain.add((int) 1);
            avain.add((char) lehti.merkki);
        } else {
            Solmu solmu = (Solmu) puu;
            
            avain.add((int) 0);
            
            String koodijonoVasen = koodijono + "0";
            luoKoodisto(solmu.vasen, koodijonoVasen);
            
            String koodijonoOikea = koodijono + "1";
            luoKoodisto(solmu.oikea, koodijonoOikea);
        }
    }
    
}
