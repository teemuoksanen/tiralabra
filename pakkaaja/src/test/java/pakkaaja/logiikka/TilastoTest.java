package pakkaaja.logiikka;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class TilastoTest {
    
    private Tilasto pakkausTilasto;
    private Tilasto purkuTilasto;
    
    @Before
    public void setUpClass() {
        pakkausTilasto = new Tilasto(0.001, 10000, 4000);
        purkuTilasto = new Tilasto(0.01);
    }

    @Test
    public void pakkaustehoPalauttaaOikean() {
        double odotettu = 60;
        double tulos = this.pakkausTilasto.pakkausteho();
        assertEquals(odotettu, tulos, 0.0);
    }

    @Test
    public void purkajanPakkausTehoOnNolla() {
        double odotettu = 0;
        double tulos = this.purkuTilasto.pakkausteho();
        assertEquals(odotettu, tulos, 0.0);
    }

    @Test
    public void kokoAlkuperainenKilotavuinaPalauttaaOikein() {
        double odotettu = 1.0 * 10000 / 1024;
        double tulos = this.pakkausTilasto.kokoAlkuperainenKilotavuina();
        assertEquals(odotettu, tulos, 0.0);
    }

    @Test
    public void purkajanKokoAlkuperainenKilotavuinaOnNolla() {
        double odotettu = 0;
        double tulos = this.purkuTilasto.kokoAlkuperainenKilotavuina();
        assertEquals(odotettu, tulos, 0.0);
    }

    @Test
    public void kokoPakattuKilotavuinaPalauttaaOikein() {
        double odotettu = 1.0 * 4000 / 1024;
        double tulos = this.pakkausTilasto.kokoPakattuKilotavuina();
        assertEquals(odotettu, tulos, 0.0);
    }

    @Test
    public void purkajanKokoPakattuKilotavuinaOnNolla() {
        double odotettu = 0;
        double tulos = this.purkuTilasto.kokoPakattuKilotavuina();
        assertEquals(odotettu, tulos, 0.0);
    }
    
}
