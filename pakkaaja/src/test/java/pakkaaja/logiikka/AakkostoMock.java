package pakkaaja.logiikka;

import pakkaaja.tietorakenteet.hajautustaulu.Hajautustaulu;

public class AakkostoMock {
    
    private Hajautustaulu<Character, Integer> aakkosto1;
    private Hajautustaulu<Character, Integer> aakkosto2;
    
    public AakkostoMock() {
        aakkosto1 = new Hajautustaulu<>();
        aakkosto1.lisaa('a', 5);
        aakkosto1.lisaa('b', 4);
        aakkosto1.lisaa('c', 3);
        aakkosto1.lisaa('d', 2);
        aakkosto1.lisaa('e', 1);
        aakkosto2 = new Hajautustaulu<>();
        aakkosto2.lisaa('a', 1);
        aakkosto2.lisaa('b', 2);
        aakkosto2.lisaa('c', 3);
        aakkosto2.lisaa('d', 4);
        aakkosto2.lisaa('e', 5);
    }
    
    public Hajautustaulu<Character, Integer> getAakkosto(int a) {
        if (a == 1) {
            return aakkosto1;
        }
        if (a == 2) {
            return aakkosto2;
        }
        return null;
    }
    
}
