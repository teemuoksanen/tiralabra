package pakkaaja.logiikka;

import org.junit.*;
import static org.junit.Assert.*;

import java.io.*;
import java.util.HashMap;
import java.util.PriorityQueue;

import static pakkaaja.logiikka.Pakkaaja.luoAakkosto;
import pakkaaja.logiikka.huffmanpuu.Lehti;
import pakkaaja.logiikka.huffmanpuu.Puu;
import pakkaaja.logiikka.huffmanpuu.Solmu;

public class PakkaajaTest {
    
    HashMap<Character, Integer> aakkosto1;
    HashMap<Character, Integer> aakkosto2;
    
    AakkostoMock aakkostoMock = new AakkostoMock();
    
    @Before
    public void setUpClass() {
        File lahdetiedosto1 = new File("src/test/resources/test.txt");
        File lahdetiedosto2 = new File("src/test/resources/test2.txt");
        try {
            aakkosto1 = luoAakkosto(lahdetiedosto1);
            aakkosto2 = luoAakkosto(lahdetiedosto2);
        } catch (FileNotFoundException ex) {
            System.out.println("VIRHE! Testitiedostoja ei löytynyt.");
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
        assertEquals(aakkosto1, aakkostoMock.getAakkosto(1));
        assertEquals(aakkosto2, aakkostoMock.getAakkosto(2));
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
        HuffmanKoodaaja koodaaja = new HuffmanKoodaaja(aakkosto1);
        HashMap<Character, String> koodisto = koodaaja.getKoodisto();
        assertEquals(koodisto.keySet().size(), 5);
        assertEquals(koodisto.get('a'), "11");
        assertEquals(koodisto.get('e'), "010");
    }
    
    @Test
    public void testinNimi() {
        
    }
    
}
