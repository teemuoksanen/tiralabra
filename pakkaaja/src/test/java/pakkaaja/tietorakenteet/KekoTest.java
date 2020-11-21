package pakkaaja.tietorakenteet;

import org.junit.*;
import static org.junit.Assert.*;
import pakkaaja.tietorakenteet.keko.*;

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
    
    @Test
    public void puullePystyyLisaamaanMaaran() {
        Puu puu = new Puu(5);
        assertEquals(puu.getMaara(), 5);
    }
    
    @Test
    public void puidenVertailuToimii() {
        Puu pieniPuu = new Puu(1);
        Puu keskikokoinenPuu = new Puu(3);
        Puu keskikokoinenPuu2 = new Puu(3);
        Puu isoPuu = new Puu(5);
        
        assertTrue(pieniPuu.compareTo(isoPuu) < 0);
        assertTrue(isoPuu.compareTo(pieniPuu) > 0);
        assertTrue(keskikokoinenPuu.compareTo(keskikokoinenPuu2) == 0);
    }
    
    @Test
    public void pienempiKuinPalauttaaTrueJosPienempi() {
        Puu pieniPuu = new Puu(1);
        Puu isoPuu = new Puu(3);
        
        assertTrue(pieniPuu.pienempiKuin(isoPuu));
    }
    
    @Test
    public void pienempiKuinPalauttaaFalseJosIsompi() {
        Puu pieniPuu = new Puu(1);
        Puu isoPuu = new Puu(3);
        
        assertFalse(isoPuu.pienempiKuin(pieniPuu));
    }
    
    @Test
    public void pienempiKuinPalauttaaFalseJosYhtaSuuret() {
        Puu puu1 = new Puu(3);
        Puu puu2 = new Puu(3);
        
        assertFalse(puu1.pienempiKuin(puu2));
        assertFalse(puu2.pienempiKuin(puu1));
    }
    
    @Test
    public void lehdellePystyyLisaamaanMerkin() {
        Lehti lehti = new Lehti('a', 5);
        assertEquals(lehti.getMerkki(), 'a');
    }
    
    @Test
    public void lehtiPalauttaaMyosMaaran() {
        Puu lehti = new Lehti('a', 5);
        assertEquals(lehti.getMaara(), 5);
    }
    
    @Test
    public void solmullePystyyLisaamaanKaksiLasta() {
        Lehti vasen = new Lehti('v', 1);
        Lehti oikea = new Lehti('o', 2);
        Solmu solmu = new Solmu(vasen, oikea);
        
        assertEquals(solmu.getVasen(), vasen);
        assertEquals(solmu.getOikea(), oikea);
    }
    
    @Test
    public void solmuPalauttaaLastensaYhteismaaran() {
        Lehti vasen = new Lehti('v', 1);
        Lehti oikea = new Lehti('o', 2);
        Solmu solmu = new Solmu(vasen, oikea);
        
        assertEquals(solmu.getMaara(), 3);
    }
    
}