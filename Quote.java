import java.util.*;

public class Quote {
        // Implementa el operador 'quote', que devuelve el segundo elemento sin evaluarlo
    public static Object evaluar(List<Object> lista) {
        if (lista.size() != 2) {
            throw new IllegalArgumentException("Uso incorrecto de quote");
        }
        return lista.get(1); // devuelve la expresión sin evaluar
    }
}
