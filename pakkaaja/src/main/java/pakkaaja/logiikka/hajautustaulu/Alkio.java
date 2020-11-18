
package pakkaaja.logiikka.hajautustaulu;

import java.util.Objects;

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
    public Alkio(K avain, V arvo, Alkio<K,V> seuraava) {
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
     * Asettaa saman hajautusarvon omaavan alkion seuraavaksi alkioksi.
     * @param seuraava
     */
    public void setSeuraava(Alkio<K, V> seuraava) {
        this.seuraava = seuraava;
    }

    /**
     * Palauttaa hajautuskoodin.
     * @return hajautuskoodi
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.avain);
        return hash;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Alkio<?, ?> other = (Alkio<?, ?>) obj;
        if (!Objects.equals(this.avain, other.avain)) {
            return false;
        }
        return true;
    }
    
}
