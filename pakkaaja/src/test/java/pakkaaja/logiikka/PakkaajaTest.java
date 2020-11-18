package pakkaaja.logiikka;

import org.junit.*;
import static org.junit.Assert.*;

import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import pakkaaja.logiikka.hajautustaulu.Hajautustaulu;

import pakkaaja.logiikka.huffmanpuu.Lehti;
import pakkaaja.logiikka.huffmanpuu.Puu;
import pakkaaja.logiikka.huffmanpuu.Solmu;

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
        assertEquals(pakkaaja1.getAakkosto(), aakkostoMock.getAakkosto(1));
        assertEquals(pakkaaja2.getAakkosto(), aakkostoMock.getAakkosto(2));
    }
    
    @Test
    public void puunLuontiOnnistuu() {
        Puu puu = new Puu(1);
        assertEquals(puu.maara, 1);
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
        assertEquals(lehti.maara, 1);
        assertEquals(lehti.merkki, 'a');
    }
    
    @Test
    public void solmunLuontiOnnistuu() {
        Puu vasen = new Puu(1);
        Puu oikea = new Puu(2);
        Solmu solmu = new Solmu(vasen, oikea);
        assertEquals(solmu.maara, 3);
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
