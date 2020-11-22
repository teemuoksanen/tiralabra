
package pakkaaja.logiikka;

import pakkaaja.tietorakenteet.keko.Lehti;
import pakkaaja.tietorakenteet.keko.Solmu;
import pakkaaja.tietorakenteet.keko.Puu;
import pakkaaja.tietorakenteet.hajautustaulu.Hajautustaulu;
import pakkaaja.tietorakenteet.keko.Keko;
import pakkaaja.tietorakenteet.lista.Lista;

/**
 * Luokka muodostaa aakkostolle Huffman-koodiston (merkki- ja Huffman-binäärikoodi -pari hajautustauluna).
 */
public class Huffman {
    
    private int[] aakkosto;
    private Hajautustaulu<Character, String> koodisto;
    private Puu puu;
    private Lista<Character> merkkilista;
    private Lista<Object> avain;
    
    /**
     * HuffmanKoodaajan konstruktori, joka kutsuttaessa samalla luo Huffman-puun sekä -koodiston sille syötteenä annetusta aakkostosta.
     * @param merkkilista merkkilista, josta Huffman-koodisto muodostetaan
     */
    public Huffman(Lista<Character> merkkilista) {
        this.merkkilista = merkkilista;
        this.aakkosto = luoAakkosto();
        this.puu = rakennaPuu();
        this.koodisto = new Hajautustaulu();
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
    
    /**
     * Apumetodi, joka laskee kunkin merkin määrät annetusta merkkilistasta.
     */
    private int[] luoAakkosto() {
        int[] abc = new int[256];
        
        for (int i = 0; i < this.merkkilista.koko(); i++) {
            char merkki = (char) this.merkkilista.hae(i);
            abc[merkki]++;
        }
        
        return abc;
    }
    
    /**
     * Apumetodi, joka rakentaa Puun ja palauttaa sen juurisolmun.
     */
    private Puu rakennaPuu() {
        Keko puut = new Keko();
        
        for (int i = 0; i < aakkosto.length; i++) {
            char merkki = (char) i;
            Lehti lehti = new Lehti(merkki, aakkosto[i]);
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

    /**
     * Apumetodi, joka luo Huffman-koodiston ja -avaimen.
     */
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
