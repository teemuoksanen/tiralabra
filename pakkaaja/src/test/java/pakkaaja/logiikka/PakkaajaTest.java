package pakkaaja.logiikka;

import org.junit.*;
import static org.junit.Assert.*;

import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.PriorityQueue;
import pakkaaja.tietorakenteet.hajautustaulu.Hajautustaulu;

import pakkaaja.tietorakenteet.puu.Lehti;
import pakkaaja.tietorakenteet.puu.Puu;
import pakkaaja.tietorakenteet.puu.Solmu;

public class PakkaajaTest {
    
    File testitiedosto1;
    File testitiedosto2;
    Pakkaaja pakkaaja1;
    Pakkaaja pakkaaja2;
    
    AakkostoMock aakkostoMock = new AakkostoMock();
    
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
    public void luoOikeanAakkoston() {
        Hajautustaulu<Character, Integer> aakkosto = pakkaaja1.getAakkosto();
        Hajautustaulu<Character, Integer> vertailuaakkosto = aakkostoMock.getAakkosto(1);
        
        assertEquals(aakkosto.koko(), vertailuaakkosto.koko());
        
        Object[] avaimet = aakkosto.avaimet();
        Object[] vertailuavaimet = vertailuaakkosto.avaimet();
        
        for (int i = 0; i < avaimet.length; i++) {
            char avain = (Character) avaimet[i];
            char vertailuavain = (Character) vertailuavaimet[i];
            assertEquals(avain, vertailuavain);
            assertEquals(aakkosto.hae(avain), vertailuaakkosto.hae(vertailuavain));
        }
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
    public void palauttaaOikeanKoodiston() {
        HuffmanKoodaaja koodaaja = new HuffmanKoodaaja(pakkaaja1.getAakkosto());
        Hajautustaulu<Character, String> koodisto = koodaaja.getKoodisto();
        assertEquals(koodisto.hae('a'), "11");
        assertEquals(koodisto.hae('e'), "010");
    }
    
    @Test
    public void sisaltoMuuttumatonPakkaamisenJaPurkamisenJalkeen() throws IOException {
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
    
}
