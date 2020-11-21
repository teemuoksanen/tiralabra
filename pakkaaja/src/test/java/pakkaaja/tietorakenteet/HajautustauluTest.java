package pakkaaja.tietorakenteet;

import org.junit.*;
import static org.junit.Assert.*;
import pakkaaja.tietorakenteet.hajautustaulu.Hajautustaulu;

public class HajautustauluTest {
    
    private Hajautustaulu<Integer, String> numerotaulu;
    private Hajautustaulu<String, String> testitaulu;
    
    @Before
    public void setUpClass() {
    }
    
    @Before
    public void setUp() {
        this.numerotaulu = new Hajautustaulu();
        this.numerotaulu.lisaa(1, "yksi");
        this.numerotaulu.lisaa(2, "kaksi");
        this.numerotaulu.lisaa(3, "kolme");
        
        this.testitaulu = new Hajautustaulu(4);
    }
    
    @After
    public void tearDown() {        
    }
    
    @Test
    public void alkionArvonHakeminenOnnistuu() {
        assertEquals(numerotaulu.hae(1), "yksi");
        assertEquals(numerotaulu.hae(3), "kolme");
    }
    
    @Test
    public void tuntematonAvainPalauttaaTyhjan() {
        assertNull(numerotaulu.hae(4));
    }
    
    @Test
    public void uudenAlkionLisaaminenJaHakuOnnistuu() {
        testitaulu.lisaa("testi1", "ykkönen");
        testitaulu.lisaa("testi2", "kakkonen");
        testitaulu.lisaa("testi3", "kolmonen");
        
        assertEquals(testitaulu.hae("testi1"), "ykkönen");
        assertEquals(testitaulu.hae("testi3"), "kolmonen");
    }
    
    @Test
    public void kokoPalauttaaOikeanKoon() {
        assertEquals(numerotaulu.koko(), 3);
        numerotaulu.lisaa(4, "neljä");
        assertEquals(numerotaulu.koko(), 4);
    }
    
    @Test
    public void avaimetPalauttaaOikeanAvaintaulukon() {
        Object[] avaimet = numerotaulu.avaimet();
        Integer[] tulokset = {1, 2, 3};
        for (int i = 0; i < avaimet.length; i++) {
            assertEquals(avaimet[i], tulokset[i]);
        }
    }
    
    @Test
    public void taulunKokoKasvaaAlkuperaisesta() {
        testitaulu.lisaa("testi1", "ykkönen");
        testitaulu.lisaa("testi2", "kakkonen");
        testitaulu.lisaa("testi3", "kolmonen");
        testitaulu.lisaa("testi4", "nelonen");
        testitaulu.lisaa("testi5", "viitonen");
        
        assertEquals(testitaulu.koko(), 5);
    }
    
    @Test
    public void arvonKorvaaminenUudellaOnnistuu() {
        testitaulu.lisaa("testi1", "ykkönen");
        testitaulu.lisaa("testi2", "kakkonen");        
        assertEquals(testitaulu.hae("testi1"), "ykkönen");
        
        testitaulu.lisaa("testi1", "yksi");
        assertEquals(testitaulu.hae("testi1"), "yksi");
    }
    
    @Test
    public void alkionPoistaminenOnnistuu() {
        numerotaulu.lisaa(4, "neljä");
        assertEquals(numerotaulu.koko(), 4);
        
        numerotaulu.poista(4);
        assertEquals(numerotaulu.koko(), 3);
        assertNull(numerotaulu.hae(4));
    }
    
    @Test
    public void olemattomanAlkionPoistoEiMuutaTaulua() {
        assertEquals(numerotaulu.koko(), 3);
        numerotaulu.poista(5);
        assertEquals(numerotaulu.koko(), 3);
    }
    
    @Test
    public void vakiohakuPalauttaaVakionJosAlkiotaEiOle() {
        assertEquals(numerotaulu.haeTaiPalautaVakio(9, "tyhjä"), "tyhjä");
    }
    
    @Test
    public void vakiohakuPalauttaaAlkionArvonJosLoytyy() {
        assertEquals(numerotaulu.haeTaiPalautaVakio(3, "tyhjä"), "kolme");
    }
    
    @Test
    public void samanHajautusarvonAlkiotLoytyvatHaussa() {
        Hajautustaulu<Integer, String> taulu = new Hajautustaulu(1);
        taulu.lisaa(11, "yksiyksi");
        taulu.lisaa(22, "kaksikaksi");
        taulu.lisaa(33, "kolmekolme");
        taulu.lisaa(44, "neljäneljä");
        taulu.lisaa(55, "viisiviisi");
        assertEquals(taulu.hae(22), "kaksikaksi");
        assertEquals(taulu.hae(55), "viisiviisi");
    }
    
    @Test
    public void samanHajautusarvonAlkioistaVoidaanPoistaaKeskelta() {
        Hajautustaulu<Integer, String> taulu = new Hajautustaulu(1);
        taulu.lisaa(1, "yksi");
        taulu.lisaa(2, "kaksi");
        taulu.lisaa(3, "kolme");
        taulu.poista(2);
        Object[] avaimet = taulu.avaimet();
        Integer[] tulokset = {1, 3};
        for (int i = 0; i < avaimet.length; i++) {
            assertEquals(avaimet[i], tulokset[i]);
        }
    }
    
}
