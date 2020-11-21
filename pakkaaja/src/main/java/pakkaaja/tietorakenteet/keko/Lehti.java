
package pakkaaja.tietorakenteet.keko;

/**
 * Huffman-puun solmu, jolla ei ole lapsisolmuja, eli edustaa yksittäistä merkkiä.
 * Laajentaa luokkaa Puu.
 * @see Puu
 */
public class Lehti extends Puu {
    
    private char merkki;
    
    /**
     * Lehden konstruktori.
     * @param merkki aakkostoon kuuluva merkki
     * @param maara aakkostoon kuuluvan merkin määrä lähdetiedostossa
     */
    public Lehti(char merkki, int maara) {
        super(maara);
        this.merkki = merkki;
    }
    
    /**
     * Palauttaa aakkoston merkin, jota Puun lehti edustaa.
     * @return merkki
     */
    public char getMerkki() {
        return this.merkki;
    }
    
}
