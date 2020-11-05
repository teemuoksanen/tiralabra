package pakkaaja.logiikka.huffmanpuu;

/**
 * Huffman-koodauksessa käytettävän puun yleisluokkaa, joka laajentuu joko Solmuksi tai Lehdeksi.
 * @see Solmu
 * @see Lehti
 */
public class Puu implements Comparable<Puu> {
    
    /**
     * Merkkien määrä (yksittäisen merkin määrä, jos kyse on Lehdestä, tai Solmun lapsina olevien Lehtien merkkien yhteismäärä).
     */
    public int maara;
    
    /**
     * Puun konstruktori.
     * @param maara merkkien määrä
     */
    public Puu(int maara) {
        this.maara = maara;
    }
    
    @Override
    public int compareTo(Puu puu) {
        return this.maara - puu.maara;
    }
    
}
