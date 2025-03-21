import java.util.*;

public class Setq {

    public static Object evaluar(List<Object> lista, Evaluador evaluador, Environment entorno) {
        if (lista.size() != 3) {
            throw new IllegalArgumentException("Uso incorrecto de setq");
        }

        Object simbolo = lista.get(1);
        Object valorRaw = lista.get(2);

        if (!(simbolo instanceof String)) {
            throw new IllegalArgumentException("El nombre de la variable debe ser un símbolo (String).");
        }

        String nombreVar = (String) simbolo;

        // Evaluamos el valor (puede ser número, lista, otra variable, etc.)
        Object valor = evaluador.evaluar(valorRaw);

        // Guardamos en el entorno
        entorno.definir(nombreVar, valor);

        // En Lisp, setq devuelve el valor asignado
        return valor;
    }
}
