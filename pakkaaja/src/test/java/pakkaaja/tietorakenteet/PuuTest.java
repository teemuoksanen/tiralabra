
package pakkaaja.tietorakenteet;

import org.junit.*;
import static org.junit.Assert.*;
import pakkaaja.tietorakenteet.puu.*;

public class PuuTest {
    
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
