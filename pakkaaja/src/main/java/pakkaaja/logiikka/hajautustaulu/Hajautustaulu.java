
package pakkaaja.logiikka.hajautustaulu;

/**
 *
 * @author teemu
 */
public class Hajautustaulu<K, V> {
    
    private final static int OLETUSKOKO = 16;
    private Alkio<K, V>[] taulu;
    private int koko;
    
    public Hajautustaulu() {
        this.taulu = new Alkio[OLETUSKOKO];
        this.koko = 0;
    }
    
    public Hajautustaulu(int koko) {
        this.taulu = new Alkio[koko];
        this.koko = 0;
    }
    
    public void put(K avain, V arvo) {
        Alkio<K,V> alkio = new Alkio<>(avain, arvo, null);
    }
    
}
