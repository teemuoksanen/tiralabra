
package pakkaaja.logiikka.huffmanpuu;

/**
 * Huffman-puun solmu, jolla on vasen ja oikea lapsisolmu.
 * Laajentaa luokkaa Puu.
 * @see Puu
 */
public class Solmu extends Puu {
    
    /**
     * Solmun vasen lapsipuu (Solmu tai Lehti).
     */
    public Puu vasen;
    /**
     * Solmun oikea lapsipuu (Solmu tai Lehti).
     */
    public Puu oikea;
    
    /**
     * Solmun konstruktori.
     * @param v vasen lapsipuu
     * @param o oikea lapsipuu
     */
    public Solmu(Puu v, Puu o) {
        super(v.maara + o.maara);
        this.vasen = v;
        this.oikea = o;
    }
    
}
