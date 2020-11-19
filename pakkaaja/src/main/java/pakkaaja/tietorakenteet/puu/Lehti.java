
package pakkaaja.tietorakenteet.puu;

/**
 * Huffman-puun solmu, jolla ei ole lapsisolmuja, eli edustaa yksittäistä merkkiä.
 * Laajentaa luokkaa Puu.
 * @see Puu
 */
public class Lehti extends Puu {
    
    /**
     * Yksittäinen aakkoston merkki, jota puun lehti edustaa.
     */
    public char merkki;
    
    /**
     * Lehden konstruktori.
     * @param merkki aakkostoon kuuluva merkki
     * @param maara aakkostoon kuuluvan merkin määrä lähdetiedostossa
     */
    public Lehti(char merkki, int maara) {
        super(maara);
        this.merkki = merkki;
    }
    
}
