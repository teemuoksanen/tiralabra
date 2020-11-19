
package pakkaaja.tietorakenteet.hajautustaulu;

/**
 * Hajautustaulun muodostava luokka.
 * @param <K> avaimen tyyppi
 * @param <V> arvon tyyppi
 */
public class Hajautustaulu<K, V> {
    
    private final static int OLETUSKOKO = 80;
    private Alkio<K, V>[] taulu;
    private int koko;
    private int alkioidenMaara;
    
    /**
     * Hajautustaulun konstruktori, joka alustaa oletuskoon mukaisen taulun.
     */
    public Hajautustaulu() {
        this.taulu = new Alkio[OLETUSKOKO];
        this.koko = OLETUSKOKO;
        this.alkioidenMaara = 0;
    }
    
    /**
     * Hajautustaulun konstruktori, joka alustaa halutun kokoisen taulun.
     * @param koko hajautustaulun haluttu koko
     */
    public Hajautustaulu(int koko) {
        this.taulu = new Alkio[koko];
        this.koko = koko;
        this.alkioidenMaara = 0;
    }
    
    /**
     * Lisää uuden alkion hajautustauluun. Jos avain on jo käytössä, korvataan kyseisen alkion arvo annetulla arvolla.
     * @param avain lisättävän alkion avain
     * @param arvo lisättävän alkion arvo
     */
    // Metodin pituus ylittää 20 riviä, mutta mielestäni pilkkominen ennemmin vaikeuttaisi logiikan ymmärtämistä.
    public void lisaa(K avain, V arvo) {
        Alkio<K, V> alkio = new Alkio<>(avain, arvo, null);
        alkioidenMaara++;
        
        if (taulu[indeksi(avain)] == null) {
            taulu[indeksi(avain)] = alkio;
        } else {
            Alkio<K, V> edellinen = null;
            Alkio<K, V> nykyinen = taulu[indeksi(avain)];
            while (nykyinen != null) {
                if (nykyinen.getAvain().equals(avain)) {
                    nykyinen.setArvo(arvo);
                    alkioidenMaara--;
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
        Alkio<K, V> alkio = taulu[indeksi(avain)];
        
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
     * Hakee alkion arvon annetun avaimen perusteella.
     * @param avain haettavan alkion avain
     * @param vakioarvo palautettava arvo, jos haku palauttaa null
     * @return syötteenä annettu vakioarvo, jos haku palauttaa null; muuten haun palauttama arvo
     */
    public V haeTaiPalautaVakio(K avain, V vakioarvo) {
        V arvo = hae(avain);
        return arvo != null ? arvo : vakioarvo;
    }

    /**
     * Palauttaa hajautustaulun sisältämien alkoiden avaimet.
     * @return avaimet taulukkona
     */
    public K[] avaimet() {
        K[] avaimet = (K[]) new Object[alkioidenMaara];
        int alkiolaskuri = 0;
        for (int i = 0; i < koko; i++) {
            Alkio<K, V> alkio = taulu[i];
            while (alkio != null) {
                avaimet[alkiolaskuri] = alkio.getAvain();
                alkiolaskuri++;
                alkio = alkio.getSeuraava();
            }
        }
        return avaimet;
    }
    
    /**
     * Poistaa annettua avainta vastaavan alkion hajautustaulusta.
     * @param avain poistettavan alkion avain
     */
    public void poista(K avain) {
        Alkio<K, V> edellinen = null;
        Alkio<K, V> alkio = taulu[indeksi(avain)];
        while (alkio != null) {
            if (alkio.getAvain().equals(avain)) {
                alkioidenMaara--;
                if (edellinen == null) {
                    alkio = alkio.getSeuraava();
                    taulu[indeksi(avain)] = alkio;
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
    
    /**
     * Palauttaa hajautustaulun sisältämien alkoiden määrän.
     * @return alkioiden määrä
     */
    public int koko() {
        return this.alkioidenMaara;
    }
    
    private int indeksi(K avain) {
        if (avain == null) {
            return 0;
        }
        int indeksi = avain.hashCode() % this.koko;
        return indeksi > 0 ? indeksi : -indeksi;
    }
    
}