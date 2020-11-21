package pakkaaja.tietorakenteet;

import org.junit.*;
import static org.junit.Assert.*;
import pakkaaja.tietorakenteet.keko.Keko;
import pakkaaja.tietorakenteet.puu.Puu;

public class KekoTest {
    
    private Keko keko;
    
    @Before
    public void setUpClass() {
    }
    
    @Before
    public void setUp() {
        this.keko = new Keko();
    }
    
    @After
    public void tearDown() {        
    }
    
    @Test
    public void ensimmaisenAlkionLisaaminenOnnistuu() {
        this.keko.lisaa(new Puu(1));
        assertEquals(this.keko.koko(), 1);
    }
    
    @Test
    public void ensimmaisenAlkionKurkistaminenOnnistuu() {
        this.keko.lisaa(new Puu(1));
        assertEquals(this.keko.kurkista().getMaara(), 1);
    }
    
    @Test
    public void ensimmaisenAlkionPoistaminenOnnistuu() {
        this.keko.lisaa(new Puu(1));
        assertEquals(this.keko.koko(), 1);
        
        assertEquals(this.keko.poista().getMaara(), 1);
        assertEquals(this.keko.koko(), 0);
    }
    
    @Test
    public void alkiotPalautetaanPienimmastaSuurimpaan() {
        this.keko.lisaa(new Puu(1));
        this.keko.lisaa(new Puu(9));
        this.keko.lisaa(new Puu(12));
        this.keko.lisaa(new Puu(5));
        this.keko.lisaa(new Puu(5));
        this.keko.lisaa(new Puu(5));
        this.keko.lisaa(new Puu(0));
        this.keko.lisaa(new Puu(2));
        this.keko.lisaa(new Puu(8));
        this.keko.lisaa(new Puu(8));
        this.keko.lisaa(new Puu(30));
        
        int edellinen = Integer.MIN_VALUE;
        int nykyinen;
        while (this.keko.kurkista() != null) {
            nykyinen = this.keko.poista().getMaara();
            assertTrue(edellinen <= nykyinen);
            edellinen = nykyinen;
        }
    }
    
    @Test
    public void keonKokoKasvattaminenOnnistuu() {
        Keko pienikeko = new Keko(3);
        pienikeko.lisaa(new Puu(1));
        pienikeko.lisaa(new Puu(2));
        pienikeko.lisaa(new Puu(3));
        pienikeko.lisaa(new Puu(4));
        
        assertEquals(pienikeko.koko(), 4);
    }
    
}