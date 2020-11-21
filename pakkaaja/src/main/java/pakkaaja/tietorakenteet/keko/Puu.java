package pakkaaja.tietorakenteet.keko;

/**
 * Huffman-koodauksessa käytettävän puun yleisluokkaa, joka laajentuu joko Solmuksi tai Lehdeksi.
 * @see Solmu
 * @see Lehti
 */
public class Puu implements Comparable<Puu> {
    
    private int maara;
    
    /**
     * Puun konstruktori.
     * @param maara merkkien määrä
     */
    public Puu(int maara) {
        this.maara = maara;
    }
    
    /**
     * Palauttaa merkkien määrän. Jos kyse on Lehdestä, palautetaan yksittäisen merkin määrä;
     * jos kyse on Solmusta, palautetaan Solmun lapsina olevien Lehtien merkkien yhteismäärä.
     * @return merkkien määrä
     */
    public int getMaara() {
        return this.maara;
    }
    
    /**
     * Kertoo, onko tämä Puu pienempi kuin syötteenä annettu Puu.
     * @param puu puu, jonka kokoon verrataan
     * @return true, jos tämä Puu on pienempi; muuten false
     */
    public boolean pienempiKuin(Puu puu) {
        return this.compareTo(puu) < 0;
    }
    
    /**
     * Vertaa syötteenä annettavan Puun merkkien määrää tämän Puun merkkien määrään.
     * @param puu verrattava Puu
     * @return negatiivinen luku, jos verrattavan Puun merkkien määrä on suurempi;
     * nolla (0), jos Puiden merkkien määrä on sama; positiivinen luku muuten
     */
    @Override
    public int compareTo(Puu puu) {
        return this.maara - puu.getMaara();
    }
    
}
