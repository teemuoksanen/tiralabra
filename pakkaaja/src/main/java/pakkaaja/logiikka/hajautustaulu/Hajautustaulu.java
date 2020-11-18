
package pakkaaja.logiikka.hajautustaulu;

/**
 * Hajautustaulun muodostava luokka.
 * @param <K> avain
 * @param <V> arvo
 */
public class Hajautustaulu<K, V> {
    
    private final static int OLETUSKOKO = 16;
    private Alkio<K, V>[] taulu;
    private int koko;
    
    /**
     * Hajautustaulun konstruktori, joka alustaa oletuskoon mukaisen taulun.
     */
    public Hajautustaulu() {
        this.taulu = new Alkio[OLETUSKOKO];
        this.koko = OLETUSKOKO;
    }
    
    /**
     * Hajautustaulun konstruktori, joka alustaa halutun kokoisen taulun.
     * @param koko hajautustaulun haluttu koko
     */
    public Hajautustaulu(int koko) {
        this.taulu = new Alkio[koko];
        this.koko = koko;
    }
    
    /**
     * Lisää uuden alkion hajautustauluun. Jos avain on jo käytössä, korvataan kyseisen alkion arvo annetulla arvolla.
     * @param avain lisättävän alkion avain
     * @param arvo lisättävän alkion arvo
     */
    public void lisaa(K avain, V arvo) {
        Alkio<K,V> alkio = new Alkio<>(avain, arvo, null);
        int indeksi = indeksi(avain);
        
        if (taulu[indeksi] == null) {
            taulu[indeksi] = alkio;
        } else {
            Alkio<K,V> edellinen = null;
            Alkio<K,V> nykyinen = taulu[indeksi];
            while (nykyinen != null) {
                if (nykyinen.getAvain().equals(avain)) {
                    nykyinen.setArvo(arvo);
                    break;
                }
                edellinen = nykyinen;
                nykyinen = nykyinen.getSeuraava();
            }
            if (edellinen != null) {
                edellinen.setSeuraava(alkio);
            }
        }
    }
    
    /**
     * Hakee alkion arvon annetun avaimen perusteella.
     * @param avain haettavan alkion avain
     * @return alkion arvo
     */
    public V hae(K avain) {
        V arvo = null;
        int indeksi = indeksi(avain);
        Alkio<K,V> alkio = taulu[indeksi];
        
        while (alkio != null) {
            if (alkio.getAvain().equals(avain)) {
                arvo = alkio.getArvo();
                break;
            }
            alkio = alkio.getSeuraava();
        }
        return arvo;
    }
    
    /**
     * Poistaa annettua avainta vastaavan alkion hajautustaulusta.
     * @param avain poistettavan alkion avain
     */
    public void poista(K avain) {
        int indeksi = indeksi(avain);
        Alkio<K,V> edellinen = null;
        Alkio<K,V> alkio = taulu[indeksi];
        while (alkio != null) {
            if (alkio.getAvain().equals(avain)) {
                if (edellinen == null) {
                    alkio = alkio.getSeuraava();
                    taulu[indeksi] = alkio;
                    return;
                } else {
                    edellinen.setSeuraava(alkio.getSeuraava());
                    return;
                }
            }
            edellinen = alkio;
            alkio = alkio.getSeuraava();
        }
    }
    
    private int indeksi(K avain) {
        if (avain == null) {
            return 0;
        }
        int indeksi = avain.hashCode() % koko;
        return indeksi > 0 ? indeksi : -indeksi;
    }
    
}
