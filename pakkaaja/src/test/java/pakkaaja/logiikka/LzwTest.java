package pakkaaja.logiikka;

import pakkaaja.logiikka.lzw.*;

import org.junit.*;
import static org.junit.Assert.*;

import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.PriorityQueue;

import pakkaaja.io.BittiKirjoittaja;
import pakkaaja.io.BittiLukija;

public class LzwTest {
    
    File testitiedosto1;
    File testitiedosto2;
    LzwPakkaaja pakkaaja1;
    LzwPakkaaja pakkaaja2;
    
    @Before
    public void setUpClass() {
        testitiedosto1 = new File("src/test/resources/test.txt");
        testitiedosto2 = new File("src/test/resources/test2.txt");
        
        pakkaaja1 = new LzwPakkaaja(testitiedosto1);
        pakkaaja2 = new LzwPakkaaja(testitiedosto2);
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {        
    }
    
    @Test
    public void sisaltoMuuttumatonPakkaamisenJaPurkamisenJalkeen() throws IOException, FileNotFoundException {
        File alkuperainen = testitiedosto1;
        File pakattu = pakkaaja1.pakkaaTiedosto();
        LzwPurkaja purkaja1 = new LzwPurkaja(pakattu);
        File purettu = purkaja1.puraTiedosto();
        byte[] alkuperainenByte = Files.readAllBytes(alkuperainen.toPath());
        byte[] purettuByte = Files.readAllBytes(purettu.toPath());
        assertTrue(Arrays.equals(alkuperainenByte, purettuByte));
        pakattu.delete();
        purettu.delete();
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void joPakatunTiedostonPakkaaminenHeittaaPoikkeuksen() throws IOException, FileNotFoundException {
        File tiedosto = new File("src/test/resources/olemassa.txt");
        LzwPakkaaja olemassa = new LzwPakkaaja(tiedosto);
        olemassa.pakkaaTiedosto();
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void joPuretunTiedostonPurkaminenHeittaaPoikkeuksen() throws IOException, FileNotFoundException {
        File tiedosto = new File("src/test/resources/olemassa.txt.lzw");
        LzwPurkaja olemassa = new LzwPurkaja(tiedosto);
        olemassa.puraTiedosto();
    }
    
}
