
package pakkaaja.logiikka.huffmanpuu;

/**
 * Huffman-puun solmu, jolla on vasen ja oikea lapsisolmu.
 * @author teemu
 */
public class Solmu extends Puu {
    
    public Puu vasen, oikea;
    
    public Solmu(Puu v, Puu o) {
        super(v.maara + o.maara);
        this.vasen = v;
        this.oikea = o;
    }
    
}
