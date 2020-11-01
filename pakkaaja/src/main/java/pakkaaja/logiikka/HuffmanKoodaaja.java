
package pakkaaja.logiikka;

import java.util.HashMap;
import java.util.PriorityQueue;
import pakkaaja.logiikka.huffmanpuu.*;

/**
 *
 * @author teemu
 */
public class HuffmanKoodaaja {
    
    private HashMap<Character, Integer> aakkosto;
    private HashMap<Character, String> koodisto;
    private Puu puu;
    
    public HuffmanKoodaaja(HashMap<Character, Integer> aakkosto) {
        this.aakkosto = aakkosto;
        this.puu = rakennaPuu();
        this.koodisto = new HashMap<>();
        luoKoodisto(this.puu, "");
    }
    
    public HashMap<Character, String> getKoodisto() {
        return this.koodisto;
    }
    
    public Puu rakennaPuu() {
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
    
    public static void tulostaKoodit(Puu puu, String koodijono) {
        if (puu instanceof Lehti) {
            Lehti lehti = (Lehti)puu;
            System.out.println(lehti.merkki + "\t" + lehti.maara + "\t" + koodijono);
        } else {
            Solmu solmu = (Solmu)puu;
            
            String koodijonoVasen = koodijono + "0";
            tulostaKoodit(solmu.vasen, koodijonoVasen);
            
            String koodijonoOikea = koodijono + "1";
            tulostaKoodit(solmu.oikea, koodijonoOikea);
        }
    }
    
    public void luoKoodisto(Puu puu, String koodijono) {
        if (puu instanceof Lehti) {
            Lehti lehti = (Lehti)puu;
            koodisto.put(lehti.merkki, koodijono);
        } else {
            Solmu solmu = (Solmu)puu;
            
            String koodijonoVasen = koodijono + "0";
            luoKoodisto(solmu.vasen, koodijonoVasen);
            
            String koodijonoOikea = koodijono + "1";
            luoKoodisto(solmu.oikea, koodijonoOikea);
        }
    }
    
}
