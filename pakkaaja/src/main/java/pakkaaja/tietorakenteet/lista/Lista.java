
package pakkaaja.tietorakenteet.lista;

/**
 * Listan muodostava luokka, jonka alkiot ovat geneeristä tyyppiä.
 * @param <T> alkion tyyppi
 */
public class Lista<T> {
    
    private final static int OLETUSKOKO = 16;
    private T[] lista;
    private int laskuri;
    
    /**
     * Listan konstruktori, joka alustaa oletuskoon mukaisen taulukon.
     */
    public Lista() {
        this.lista = (T[]) new Object[OLETUSKOKO];
        this.laskuri = 0;
    }
    
    /**
     * Listan konstruktori, joka alustaa halutun kokoisen taulukon.
     * @param koko listan haluttu koko
     */
    public Lista(int koko) {
        this.lista = (T[]) new Object[koko];
        this.laskuri = 0;
    }
    
    /**
     * Lisää uuden alkion listaan. Jos listan koko saavuttaa ylärajan, kasvatetaan listaa kaksinkertaiseksi apumetodin avulla.
     * @param alkio lisättävän alkion arvo
     */
    public void lisaa(T alkio) {
        this.lista[this.laskuri] = alkio;
        this.laskuri++;
        if (this.laskuri >= this.lista.length) {
            kasvataListaa();
        }
    }
    
    /**
     * Palauttaa listan alkiot. Palautettavassa listassa ei ole lopun tyhjiä alkioita.
     * @return arvot taulukkona
     */
    public T[] listaa() {
        T[] palautettavaLista =  (T[]) new Object[this.laskuri];
        for (int i = 0; i < this.laskuri; i++) {
            palautettavaLista[i] = this.lista[i];
        }
        return palautettavaLista;
    }
    
    
    /**
     * Palauttaa listan yksittäisen alkion.
     * @return alkion arvo
     */
    public T hae(int i) {
        return this.lista[i];
    }
    
    /**
     * Palauttaa listan alkioiden määrän.
     * @return alkioiden määrä
     */
    public int koko() {
        return this.laskuri;
    }
    
    /**
    * Apumetodi, joka kasvattaa listan koon kaksinkertaiseksi nykyiseen verrattuna.
    */
    private void kasvataListaa() {
        T[] uusiLista = (T[]) new Object[2 * this.laskuri];
        for (int i = 0; i < lista.length; i++) {
            uusiLista[i] = this.lista[i];
        }
        this.lista = uusiLista;
    }
    
}
