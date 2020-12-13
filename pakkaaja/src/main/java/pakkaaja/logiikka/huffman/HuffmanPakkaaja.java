
package pakkaaja.logiikka.huffman;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import pakkaaja.logiikka.Pakkaaja;
import pakkaaja.tietorakenteet.hajautustaulu.Hajautustaulu;
import pakkaaja.tietorakenteet.lista.Lista;
import pakkaaja.tietorakenteet.keko.*;
import pakkaaja.io.BittiKirjoittaja;

/**
 * Luokka pakkaa sille syötteenä annetun tiedoston Huffman-algoritmin mukaisesti.
 */
public class HuffmanPakkaaja extends Pakkaaja {
    
    private Lista<Character> merkkilista;
    private int[] aakkosto;
    private Puu juurisolmu;
    private Hajautustaulu<Character, String> koodisto;
    private Lista<Object> avain;
    
    /**
     * Pakkaajan konstruktori.
     * @param tiedosto pakattava tiedosto
     */
    public HuffmanPakkaaja(File tiedosto) {
        this.tiedostoPakattava = tiedosto;
        this.koodisto = new Hajautustaulu<Character, String>();
        this.avain = new Lista();
    }
    
    /**
     * Metodi hoitaa tiedoston pakkaamisen apumetodiensa avulla ja palauttaa pakatun tiedoston, kun pakkaminen on valmis.
     * @return pakattu tiedosto
     * @throws IllegalArgumentException Heittää IllegalArgumentException-poikkeuksen, jos samanniminen pakattu tiedosto on jo olemassa.
     * @throws FileNotFoundException Heittää FileNotFoundException-poikkeuksen, jos tiedostoa ei löydy.
     * @throws IOException Heittää IOException-poikkeuksen, jos bittivirran kirjoittaminen ei onnistu.
     */
    @Override
    public File suoritaPakkaaminen() throws FileNotFoundException, IOException {
        File tiedostoPakattu = muodostaPakattuTiedosto(this.tiedostoPakattava, "huff");
        this.merkkilista = lueTiedostoMerkkilistaksi(this.tiedostoPakattava);
        
        // Luodaan Huffman-algoritmiin liittyvä aakkosto, puu ja koodisto
        this.aakkosto = this.luoAakkosto();
        this.juurisolmu = this.rakennaPuu();
        this.luoKoodisto(juurisolmu, "");
        
        // Kirjoitetaan pakattu tiedosto
        BittiKirjoittaja kirjoittaja = new BittiKirjoittaja(tiedostoPakattu);
        kirjoitaMerkkimaara(kirjoittaja);
        kirjoitaAvain(kirjoittaja);
        kirjoitaTiedosto(kirjoittaja);
        kirjoittaja.close();
        
        return tiedostoPakattu;
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
    
    /**
     * Apumetodi, joka kirjoituttaa pakattavan tiedoston merkkimäärän pakattuun tiedstoon.
     */
    private void kirjoitaMerkkimaara(BittiKirjoittaja kirjoittaja) throws IOException {
        String merkitBitteina = String.format("%32s", Integer.toBinaryString(this.merkkilista.koko())).replaceAll(" ", "0");
        kirjoittaja.kirjoitaBittijono(merkitBitteina);
    }
    
    /**
     * Apumetodi, joka kirjoituttaa Huffman-puun avaimen pakattuun tiedstoon.
     */
    private void kirjoitaAvain(BittiKirjoittaja kirjoittaja) throws IOException {
        for (int i = 0; i < this.avain.koko(); i++) {
            Object o = this.avain.listaa()[i];
            if (o instanceof Integer) {
                kirjoittaja.kirjoitaBitti((int) o);
            } else {
                kirjoittaja.kirjoitaTavu((char) o);
            }
        }
    }
    
    /**
     * Apumetodi, joka kirjoituttaa varsinaisen tiedoston sisällön pakattuun tiedstoon.
     */
    private void kirjoitaTiedosto(BittiKirjoittaja kirjoittaja) throws IOException {
        for (int i = 0; i < this.merkkilista.koko(); i++) {
            char merkki = (char) this.merkkilista.hae(i);
            kirjoittaja.kirjoitaBittijono(this.koodisto.hae(merkki));
        }
    }
    
}
