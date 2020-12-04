package pakkaaja.logiikka;

import org.junit.*;
import static org.junit.Assert.*;

import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.PriorityQueue;
import pakkaaja.logiikka.io.BittiKirjoittaja;
import pakkaaja.logiikka.io.BittiLukija;

import pakkaaja.tietorakenteet.keko.Lehti;
import pakkaaja.tietorakenteet.keko.Puu;
import pakkaaja.tietorakenteet.keko.Solmu;

public class PakkaajaTest {
    
    File testitiedosto1;
    File testitiedosto2;
    Pakkaaja pakkaaja1;
    Pakkaaja pakkaaja2;
    
    @Before
    public void setUpClass() {
        testitiedosto1 = new File("src/test/resources/test.txt");
        testitiedosto2 = new File("src/test/resources/test2.txt");
        
        try {
            pakkaaja1 = new Pakkaaja(testitiedosto1);
            pakkaaja2 = new Pakkaaja(testitiedosto2);
        } catch (FileNotFoundException ex) {
            System.out.println("VIRHE! Testitiedostoja ei löytynyt.");
        } catch (IOException ex) {
            System.out.println("VIRHE! Bittivirran lukeminen tai kirjoittaminen ei onnistunut.");
        }
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {        
    }
    
    @Test
    public void puunLuontiOnnistuu() {
        Puu puu = new Puu(1);
        assertEquals(puu.getMaara(), 1);
    }
    
    @Test
    public void puutJarjestyvatMaaranMukaan() {
        PriorityQueue<Puu> puut = new PriorityQueue<>();
        Puu puuSuurin = new Puu(5);
        puut.add(puuSuurin);
        Puu puuPienin = new Puu(1);
        puut.add(puuPienin);
        Puu puuKeski = new Puu(3);
        puut.add(puuKeski);
        assertEquals(puut.poll(), puuPienin);
    }
    
    @Test
    public void lehdenLuontiOnnistuu() {
        Lehti lehti = new Lehti('a', 1);
        assertEquals(lehti.getMaara(), 1);
        assertEquals(lehti.getMerkki(), 'a');
    }
    
    @Test
    public void solmunLuontiOnnistuu() {
        Puu vasen = new Puu(1);
        Puu oikea = new Puu(2);
        Solmu solmu = new Solmu(vasen, oikea);
        assertEquals(solmu.getMaara(), 3);
    }
    
    @Test
    public void sisaltoMuuttumatonPakkaamisenJaPurkamisenJalkeen() throws IOException, FileNotFoundException, TiedostoOlemassaPoikkeus {
        File alkuperainen = testitiedosto1;
        pakkaaja1.pakkaaTiedosto();
        File pakattu = pakkaaja1.getTiedostoPakattu();
        Purkaja purkaja1 = new Purkaja(pakattu);
        purkaja1.puraTiedosto();
        File purettu = purkaja1.getTiedostoPurettu();
        byte[] alkuperainenByte = Files.readAllBytes(alkuperainen.toPath());
        byte[] purettuByte = Files.readAllBytes(purettu.toPath());
        assertTrue(Arrays.equals(alkuperainenByte, purettuByte));
        pakattu.delete();
        purettu.delete();
    }
    
    @Test(expected = TiedostoOlemassaPoikkeus.class)
    public void joPakatunTiedostonPakkaaminenHeittaaPoikkeuksen() throws IOException, FileNotFoundException, TiedostoOlemassaPoikkeus {
        File tiedosto = new File("src/test/resources/olemassa.txt");
        Pakkaaja olemassa = new Pakkaaja(tiedosto);
        olemassa.pakkaaTiedosto();
    }
    
    @Test(expected = TiedostoOlemassaPoikkeus.class)
    public void joPuretunTiedostonPurkaminenHeittaaPoikkeuksen() throws IOException, FileNotFoundException, TiedostoOlemassaPoikkeus {
        File tiedosto = new File("src/test/resources/olemassa.txt.huff");
        Purkaja olemassa = new Purkaja(tiedosto);
        olemassa.puraTiedosto();
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void epakelpoKirjoitettavaBittiHeittaaPoikkeuksen() throws FileNotFoundException, IOException {
        File tiedosto = new File("src/test/resources/bittikirjoittaja.txt");
        BittiKirjoittaja kirjoittaja = new BittiKirjoittaja(tiedosto);
        tiedosto.delete();
        kirjoittaja.kirjoitaBitti(2);
    }
    
    @Test
    public void tyhjastaTiedostostaLukeminenEiPalautaBittia() throws FileNotFoundException, IOException {
        File tiedosto = new File("src/test/resources/tyhja.txt");
        BittiLukija lukija = new BittiLukija(tiedosto);
        int tulos = lukija.lueBitti();
        assertEquals(tulos, -1);
    }
    
}
