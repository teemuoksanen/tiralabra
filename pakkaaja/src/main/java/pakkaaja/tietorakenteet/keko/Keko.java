
package pakkaaja.tietorakenteet.keko;

import pakkaaja.tietorakenteet.puu.Puu;

/**
 * Keon muodostava luokka. Toteutukseltaan minimikeko, jonka alkiot ovat Puita.
 * @see Puu
 */
public class Keko {
    
    private final static int OLETUSKOKO = 80;
    private Puu[] keko;
    private int laskuri;
    
    /**
     * Keon konstruktori, joka alustaa oletuskoon mukaisen taulun kekoa varten.
     */
    public Keko() {
        this.keko = new Puu[OLETUSKOKO + 1];
        this.laskuri = 0;
    }
    
    /**
     * Keon konstruktori, joka alustaa halutun kokoisen taulun kekoa varten.
     * @param koko keon haluttu koko
     */
    public Keko(int koko) {
        this.keko = new Puu[koko + 1];
        this.laskuri = 0;
    }
    
    /**
     * Lisää uuden alkion kekoon.
     * @param puu lisättävä puu
     */
    public void lisaa(Puu puu) {
        this.laskuri++;
        this.keko[this.laskuri] = puu;
        
        if (this.laskuri >= this.keko.length - 1) {
            kasvataKekoa();
        }
        
        if (this.laskuri != 1) {
            jarjestaYlospain();
        }
    }
    
    /**
     * Palauttaa keon juurialkion.
     * @return juurialkiona oleva Puu
     */
    public Puu kurkista() {
        return this.keko[1];
    }
    
    /**
     * Palauttaa keon juurialkion, poistaa sen ja järjestää keon uudelleen.
     * @return juurialkiona oleva Puu
     */
    public Puu poista() {
        Puu poistettava = this.keko[1];
        this.keko[1] = this.keko[this.laskuri];
        this.keko[this.laskuri--] = null;
        jarjestaAlaspain(1);
        return poistettava;
    }
    
    /**
     * Palauttaa keon sisältämien alkoiden määrän.
     * @return alkioiden määrä
     */
    public int koko() {
        return this.laskuri;
    }
    
    /**
    * Apumetodi, joka järjestää alkiot uudelleen, kun kekoon lisätään uusi alkio.
    */
    private void jarjestaYlospain() {
        int solmu = this.laskuri;
        while (solmu > 1 && this.keko[solmu].pienempiKuin(this.keko[vanhempi(solmu)])) {
            vaihda(solmu, vanhempi(solmu));
            solmu = vanhempi(solmu);
        }
    }
    
    /**
    * Apumetodi, joka järjestää alkiot uudelleen, kun keosta poistetaan juurialkio.
    */
    private void jarjestaAlaspain(int solmu) {
        int pienin = solmu;
        int vasen = vasenLapsi(solmu);
        int oikea = oikeaLapsi(solmu);
        
        if (vasen <= this.laskuri && this.keko[vasen].pienempiKuin(this.keko[pienin])) {
            pienin = vasen;
        }
        if (oikea <= this.laskuri && this.keko[oikea].pienempiKuin(this.keko[pienin])) {
            pienin = oikea;
        }
        if (pienin != solmu) {
            vaihda(solmu, pienin);
            jarjestaAlaspain(pienin);
        }
    }
    
    /**
    * Apumetodi, joka vaihtaa syötteenä annettujen indeksien sisällön keossa keskenään.
    */
    private void vaihda(int a, int b) {
        Puu apuri = this.keko[a];
        this.keko[a] = this.keko[b];
        this.keko[b] = apuri;
    }
    
    /**
    * Apumetodit, jotka palauttavat syötteenä annetussa indeksissä sijaitsevan alkion 
    * vanhemman, vasemman lapsen tai oikean lapsen indeksinumeron.
    */
    private int vanhempi(int solmu) {
        return solmu / 2;
    }
    private int vasenLapsi(int solmu) {
        return solmu * 2;
    }
    private int oikeaLapsi(int solmu) {
        return solmu * 2 + 1;
    }
    
    /**
    * Apumetodi, joka kasvattaa keon koon kaksinkertaiseksi nykyiseen verrattuna.
    */
    private void kasvataKekoa() {
        Puu[] uusiKeko = new Puu[2 * this.laskuri];
        for (int i = 0; i < this.keko.length; i++) {
            uusiKeko[i] = this.keko[i];
        }
        this.keko = uusiKeko;
    }
    
}
