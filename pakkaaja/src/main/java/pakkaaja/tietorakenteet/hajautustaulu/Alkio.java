
package pakkaaja.tietorakenteet.hajautustaulu;

/**
 * Hajautustaulun yhtä alkiota vastaava olio.
 * @param <K> avain
 * @param <V> arvo
 */
public class Alkio<K, V> {
    
    private final K avain;
    private V arvo;
    private Alkio<K, V> seuraava;
    
    /**
     * Hajautustaulun Alkion konstruktori.
     * @param avain alkion avain
     * @param arvo alkion arvo
     * @param seuraava saman hajautusarvon omaava alkio
     */
    public Alkio(K avain, V arvo, Alkio<K, V> seuraava) {
        this.avain = avain;
        this.arvo = arvo;
        this.seuraava = seuraava;
    }
    
    /**
     * Palauttaa alkion avaimen.
     * @return avain
     */
    public K getAvain() {
        return this.avain;
    }

    /**
     * Palauttaa alkion arvon.
     * @return arvo
     */
    public V getArvo() {
        return this.arvo;
    }

    /**
     * Asettaa alkion arvon.
     * @param arvo alkion uusi arvo
     */
    public void setArvo(V arvo) {
        this.arvo = arvo;
    }

    /**
     * Palauttaa seuraavan saman hajautusarvon omaavan alkion.
     * @return seuraava alkio
     */
    public Alkio<K, V> getSeuraava() {
        return this.seuraava;
    }

    /**
     * Asettaa saman hajautusarvon omaavan alkion tätä alkiota seuraavaksi alkioksi.
     * @param seuraava seuraava alkio
     */
    public void setSeuraava(Alkio<K, V> seuraava) {
        this.seuraava = seuraava;
    }
    
}
