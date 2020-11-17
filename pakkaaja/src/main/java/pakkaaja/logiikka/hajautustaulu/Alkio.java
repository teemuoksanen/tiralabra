
package pakkaaja.logiikka.hajautustaulu;

import java.util.Objects;

/**
 *
 */
public class Alkio<K, V> {
    
    private final K avain;
    private V arvo;
    private Alkio<K, V> seuraava;
    
    public Alkio(K avain, V arvo, Alkio<K,V> seuraava) {
        this.avain = avain;
        this.arvo = arvo;
        this.seuraava = seuraava;
    }
    
    public K getAvain() {
        return this.avain;
    }

    public V getArvo() {
        return this.arvo;
    }

    public void setArvo(V arvo) {
        this.arvo = arvo;
    }

    public Alkio<K, V> getSeuraava() {
        return this.seuraava;
    }

    public void setSeuraava(Alkio<K, V> seuraava) {
        this.seuraava = seuraava;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.avain);
        return hash;
    }

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
