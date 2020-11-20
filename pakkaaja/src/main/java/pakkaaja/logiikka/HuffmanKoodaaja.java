
package pakkaaja.logiikka;

import pakkaaja.tietorakenteet.puu.Lehti;
import pakkaaja.tietorakenteet.puu.Solmu;
import pakkaaja.tietorakenteet.puu.Puu;
import pakkaaja.tietorakenteet.hajautustaulu.Hajautustaulu;
import pakkaaja.tietorakenteet.keko.Keko;
import pakkaaja.tietorakenteet.lista.Lista;

/**
 * Luokka muodostaa aakkostolle Huffman-koodiston (merkki- ja Huffman-binäärikoodi -pari hajautustauluna).
 */
public class HuffmanKoodaaja {
    
    private Hajautustaulu<Character, Integer> aakkosto;
    private Hajautustaulu<Character, String> koodisto;
    private Puu puu;
    private Lista<Object> avain;
    
    /**
     * HuffmanKoodaajan konstruktori, joka kutsuttaessa samalla luo Huffman-puun sekä -koodiston sille syötteenä annetusta aakkostosta.
     * @param aakkosto  aakkosto, josta Huffman-koodisto muodostetaan
     */
    public HuffmanKoodaaja(Hajautustaulu<Character, Integer> aakkosto) {
        this.aakkosto = aakkosto;
        this.puu = rakennaPuu();
        this.koodisto = new Hajautustaulu<>();
        this.avain = new Lista();
        luoKoodisto(this.puu, "");
    }
    
    /**
     * Palauttaa Huffman-koodiston.
     * @return koodisto eli merkki- ja Huffman-binäärikoodi -pari hajautustauluna
     */
    public Hajautustaulu<Character, String> getKoodisto() {
        return this.koodisto;
    }
    /**
     * Palauttaa Huffman-avaimen.
     * @return avain eli Huffman-puu lyhyeksi koodattuna
     */
    public Lista<Object> getAvain() {
        return this.avain;
    }
    
    private Puu rakennaPuu() {
        Keko puut = new Keko();
        
        Object[] merkit = this.aakkosto.avaimet();
        for (int i = 0; i < merkit.length; i++) {
            char merkki = (Character) merkit[i];
            Lehti lehti = new Lehti(merkki, this.aakkosto.hae(merkki));
            puut.lisaa(lehti);
        }
        
        while (puut.koko() > 1) {
            Puu a = puut.poista();
            Puu b = puut.poista();
            
            Solmu uusiSolmu = new Solmu(a, b);
            puut.lisaa(uusiSolmu);
        }
        
        return puut.poista();
    }

    private void luoKoodisto(Puu puu, String koodijono) {
        if (puu instanceof Lehti) {
            Lehti lehti = (Lehti) puu;
            this.koodisto.lisaa(lehti.getMerkki(), koodijono);
            this.avain.lisaa((int) 1);
            this.avain.lisaa((char) lehti.getMerkki());
        } else {
            Solmu solmu = (Solmu) puu;
            
            this.avain.lisaa((int) 0);
            
            String koodijonoVasen = koodijono + "0";
            luoKoodisto(solmu.getVasen(), koodijonoVasen);
            
            String koodijonoOikea = koodijono + "1";
            luoKoodisto(solmu.getOikea(), koodijonoOikea);
        }
    }
    
}
