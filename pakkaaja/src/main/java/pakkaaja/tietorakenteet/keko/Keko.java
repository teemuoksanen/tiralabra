
package pakkaaja.tietorakenteet.keko;

import pakkaaja.tietorakenteet.puu.Puu;

/**
 * 
 */
public class Keko {
    
    private final static int OLETUSKOKO = 80;
    private Puu[] keko;
    private int laskuri;
    
    public Keko() {
        this.keko = new Puu[OLETUSKOKO];
        this.laskuri = 0;
    }
    
    public Keko(int koko) {
        this.keko = new Puu[koko];
        this.laskuri = 0;
    }
    
    public void lisaa(Puu puu) {
        keko[laskuri++] = puu;
        if (this.laskuri >= this.keko.length) {
            kasvataKekoa();
        }
        
        if (this.laskuri == 1) {
            laskuri++;
        } else {
            jarjestaYlospain();
        }
    }
    
    private void jarjestaYlospain() {
        int solmu = this.laskuri - 1;
        while (solmu > 1 && keko[solmu].pienempiKuin(keko[vanhempi(solmu)])) {
            vaihda(solmu, vanhempi(solmu));
            solmu = vanhempi(solmu);
        }
    }
    
    private void jarjestaAlaspain(int solmu) {
        int pienin = solmu;
        int vasen = vasenLapsi(solmu);
        int oikea = oikeaLapsi(solmu);
        
        if (vasen < this.laskuri && keko[vasen].pienempiKuin(keko[pienin])) {
            pienin = vasen;
        }
        if (oikea < this.laskuri && keko[oikea].pienempiKuin(keko[pienin])) {
            pienin = oikea;
        }
        if (pienin != solmu) {
            vaihda(solmu, pienin);
            jarjestaAlaspain(pienin);
        }
    }
    
    private void vaihda(int a, int b) {
        Puu apuri = keko[a];
        keko[a] = keko[b];
        keko[b] = apuri;
    }
    
    private int vanhempi(int solmu) {
        return solmu / 2;
    }
    
    private int vasenLapsi(int solmu) {
        return solmu * 2;
    }
    
    private int oikeaLapsi(int solmu) {
        return solmu * 2 + 1;
    }
    
    /**
    * Apumetodi, joka kasvattaa keon koon kaksinkertaiseksi nykyiseen verrattuna.
    */
    private void kasvataKekoa() {
        Puu[] uusiKeko = new Puu[2 * this.laskuri];
        for (int i = 0; i < this.keko.length; i++) {
            uusiKeko[i] = this.keko[i];
        }
        this.keko = uusiKeko;
    }
    
}
