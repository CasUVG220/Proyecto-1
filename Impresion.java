import java.util.*;

public class Impresion {
    // Evalúa una instrucción (print x), imprime el valor y lo retorna
    public static Object evaluar(List<Object> lista, Evaluador evaluador, Environment entorno) {
        if (lista.size() != 2) {
            throw new IllegalArgumentException("Uso incorrecto de print. Ejemplo: (print x)");
        }
        // Se evalúa el valor que se desea imprimir
        Object valor = evaluador.evaluar(lista.get(1));
        System.out.println(valor); // Se imprime a consola
        return valor; // También se retorna, por si se quiere usar en otra evaluación
    }
}
