package pakkaaja.tietorakenteet;

import org.junit.*;
import static org.junit.Assert.*;
import pakkaaja.tietorakenteet.lista.Lista;

public class ListaTest {
    
    private Lista<Integer> numerolista;
    private Lista<String> testilista;
    
    @Before
    public void setUpClass() {
    }
    
    @Before
    public void setUp() {
        this.numerolista = new Lista();
        this.numerolista.lisaa(1);
        this.numerolista.lisaa(2);
        this.numerolista.lisaa(3);
        
        this.testilista = new Lista(4);
    }
    
    @After
    public void tearDown() {        
    }
    
    @Test
    public void uudenAlkionLisaaminenTallentuuListaan() {
        assertEquals(testilista.koko(), 0);
        
        testilista.lisaa("testi1");
        testilista.lisaa("testi2");
        testilista.lisaa("testi3");
        
        assertEquals(testilista.koko(), 3);
        
        Object[] lista = testilista.listaa();
        String[] vertailulista = {"testi1", "testi2", "testi3"};
        
        Assert.assertArrayEquals(lista, vertailulista);
    }
    
    @Test
    public void kokoPalauttaaOikeanKoon() {
        assertEquals(numerolista.koko(), 3);
        numerolista.lisaa(4);
        assertEquals(numerolista.koko(), 4);
    }
    
    @Test
    public void listanKokoKasvaaAlkuperaisesta() {
        testilista.lisaa("testi1");
        testilista.lisaa("testi2");
        testilista.lisaa("testi3");
        testilista.lisaa("testi4");
        testilista.lisaa("testi5");
        
        assertEquals(testilista.koko(), 5);
    }
    
}
