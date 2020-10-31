
package pakkaaja.logiikka;

import java.util.HashMap;
import java.util.PriorityQueue;
import pakkaaja.logiikka.huffmanpuu.*;

/**
 *
 * @author teemu
 */
public class HuffmanKoodaaja {
    
    public static Puu rakennaPuu(HashMap<Character, Integer> aakkosto) {
        PriorityQueue<Puu> puut = new PriorityQueue<>();
        
        for (char merkki : aakkosto.keySet()) {
            Lehti lehti = new Lehti(merkki, aakkosto.get(merkki));
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
    
    public static void luoMerkisto(Puu puu, String koodijono, HashMap<Character, String> merkisto) {
        if (puu instanceof Lehti) {
            Lehti lehti = (Lehti)puu;
            merkisto.put(lehti.merkki, koodijono);
        } else {
            Solmu solmu = (Solmu)puu;
            
            String koodijonoVasen = koodijono + "0";
            tulostaKoodit(solmu.vasen, koodijonoVasen);
            
            String koodijonoOikea = koodijono + "1";
            tulostaKoodit(solmu.oikea, koodijonoOikea);
        }
    }
    
}
