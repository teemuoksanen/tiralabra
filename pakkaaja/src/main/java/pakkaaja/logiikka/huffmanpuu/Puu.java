package pakkaaja.logiikka.huffmanpuu;

/**
 * Huffman-koodauksessa käytettävän puun yleisluokkaa, joka laajentuu joko Solmuksi tai Lehdeksi.
 * @author teemu
 */
public class Puu implements Comparable<Puu> {
    
    public int maara;
    
    public Puu(int maara) {
        this.maara = maara;
    }
    
    @Override
    public int compareTo(Puu puu) {
        return this.maara - puu.maara;
    }
    
}
