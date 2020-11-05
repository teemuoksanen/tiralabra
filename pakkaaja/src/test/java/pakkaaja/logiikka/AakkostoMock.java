package pakkaaja.logiikka;

import java.util.HashMap;

public class AakkostoMock {
    
    private HashMap<Character, Integer> aakkosto1;
    private HashMap<Character, Integer> aakkosto2;
    
    public AakkostoMock() {
        aakkosto1 = new HashMap<>();
        aakkosto1.put('a', 5);
        aakkosto1.put('b', 4);
        aakkosto1.put('c', 3);
        aakkosto1.put('d', 2);
        aakkosto1.put('e', 1);
        aakkosto2 = new HashMap<>();
        aakkosto2.put('a', 1);
        aakkosto2.put('b', 2);
        aakkosto2.put('c', 3);
        aakkosto2.put('d', 4);
        aakkosto2.put('e', 5);
    }
    
    public HashMap<Character, Integer> getAakkosto(int a) {
        if (a == 1) {
            return aakkosto1;
        }
        if (a == 2) {
            return aakkosto2;
        }
        return null;
    }
    
}
