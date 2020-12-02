
package pakkaaja.logiikka;

import pakkaaja.tietorakenteet.hajautustaulu.Hajautustaulu;
import pakkaaja.tietorakenteet.lista.Lista;

/**
 * 
 */
public class LzwPurkaja {
    
    private Hajautustaulu<Integer, String> koodisto;
    private int koodistonPituus;
    
    public LzwPurkaja(Lista<Integer> alkuperainen) {
        this.koodisto = this.alustaKoodisto();
        this.koodistonPituus = 256;
    }
    
    private Hajautustaulu<Integer, String> alustaKoodisto() {
        Hajautustaulu<Integer, String> koodisto = new Hajautustaulu();
        for (int i = 0; i < 256; i++) {
            String merkki = "" + (char) i;
            koodisto.lisaa(i, merkki);
        }
        return koodisto;
    }
    
    public Lista<String> puraMerkit(Lista<Integer> pakattu) {
        Lista<String> purettu = new Lista();
        String w = "" + (char)(int) pakattu.hae(0);
        
        for (int i = 1; i < pakattu.koko(); i++) {
            int k = pakattu.hae(i);
            String purku;
            if (this.koodisto.sisaltaaAvaimen(k)) {
                purku = this.koodisto.hae(k);
            } else if (k == this.koodistonPituus) {
                purku = w + w.charAt(0);
            } else {
                throw new IllegalArgumentException("Virheellinen pakkaus kohdassa: " + k);
            }
            
            purettu.lisaa(purku);
            
            this.koodisto.lisaa(this.koodistonPituus++, w + purku.charAt(0));
            
            w = purku;
        }
        
        return purettu;
    }
    
    public int getKoodistonPituus() {
        return this.koodistonPituus;
    }
    
}
