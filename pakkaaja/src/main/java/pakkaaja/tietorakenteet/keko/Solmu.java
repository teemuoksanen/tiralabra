
package pakkaaja.tietorakenteet.keko;

/**
 * Huffman-puun solmu, jolla on vasen ja oikea lapsisolmu.
 * Laajentaa luokkaa Puu.
 * @see Puu
 */
public class Solmu extends Puu {
    
    private Puu vasen;
    private Puu oikea;
    
    /**
     * Solmun konstruktori.
     * @param v vasen lapsipuu
     * @param o oikea lapsipuu
     */
    public Solmu(Puu v, Puu o) {
        super(v.getMaara() + o.getMaara());
        this.vasen = v;
        this.oikea = o;
    }
    
    /**
     * Palauttaa Solmun vasemman lapsipuun (Solmu tai Lehti).
     * @return vasen lapsipuu
     */
    public Puu getVasen() {
        return this.vasen;
    }
    
    /**
     * Palauttaa Solmun oikean lapsipuun (Solmu tai Lehti).
     * @return oikea lapsipuu
     */
    public Puu getOikea() {
        return this.oikea;
    }
    
}
