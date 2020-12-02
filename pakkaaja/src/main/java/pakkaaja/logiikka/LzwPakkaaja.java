
package pakkaaja.logiikka;

import pakkaaja.tietorakenteet.hajautustaulu.Hajautustaulu;
import pakkaaja.tietorakenteet.lista.Lista;

/**
 * 
 */
public class LzwPakkaaja {
    
    private Hajautustaulu<String, Integer> koodisto;
    private int koodistonPituus;
    
    public LzwPakkaaja(Lista<Integer> alkuperainen) {
        this.koodisto = this.alustaKoodisto();
        this.koodistonPituus = 1;
    }
    
    private Hajautustaulu<String, Integer> alustaKoodisto() {
        Hajautustaulu<String, Integer> koodisto = new Hajautustaulu();
        for (int i = 0; i < 256; i++) {
            String merkki = "" + (char) i;
            koodisto.lisaa(merkki, i);
        }
        return koodisto;
    }
    
    public Lista<Integer> pakkaaMerkit(Lista<Character> merkkilista) {
        String w = "";
        Lista<Integer> pakattu = new Lista();
        
        for (int i = 0; i < merkkilista.koko(); i++) {
            Character c = (char) merkkilista.hae(i);
            String wc = w + c;
            if (this.koodisto.sisaltaaAvaimen(wc)) {
                w = wc;
            } else {
                pakattu.lisaa(this.koodisto.hae(w));
                this.koodisto.lisaa(wc, this.koodistonPituus++);
                w = "" + c;
            }
        }
        
        if (!w.equals("")) {
            pakattu.lisaa(this.koodisto.hae(w));
        }
        
        return pakattu;
    }
    
    public int getKoodistonPituus() {
        return this.koodistonPituus;
    }
    
}
