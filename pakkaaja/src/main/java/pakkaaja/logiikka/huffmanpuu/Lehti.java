
package pakkaaja.logiikka.huffmanpuu;

/**
 * Huffman-puun solmu, jolla ei ole lapsisolmuja, eli edustaa yksittäistä merkkiä.
 * @author teemu
 */
public class Lehti extends Puu {
    
    public char merkki;
    
    public Lehti(char merkki, int maara) {
        super(maara);
        this.merkki = merkki;
    }
    
}
