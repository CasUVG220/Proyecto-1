import java.util.*;

public class Cond {

    public static Object evaluar(List<Object> lista, Evaluador evaluador, Environment entorno) {
        // Ejemplo de lista: [cond, [cond1, resultado1], [cond2, resultado2], ...]

        if (lista.size() < 2) {
            throw new IllegalArgumentException("Uso incorrecto de cond. Debe haber al menos una condición.");
        }

        // Recorremos los pares de condiciones
        for (int i = 1; i < lista.size(); i++) {
            Object parObj = lista.get(i);

            if (!(parObj instanceof List)) {
                throw new IllegalArgumentException("Cada cond debe ser una lista.");
            }

            List<Object> par = (List<Object>) parObj;

            if (par.size() != 2) {
                throw new IllegalArgumentException("Cada cond debe tener una condición y un resultado.");
            }

            Object condicion = par.get(0);
            Object resultado = par.get(1);

            // t → se considera condición por defecto
            if (condicion instanceof String && condicion.equals("t")) {
                return evaluador.evaluar(resultado);
            }

            Object valorCond = evaluador.evaluar(condicion);

            // Si la condición es booleana verdadera o diferente de 0
            if (valorCond instanceof Boolean && (Boolean) valorCond) {
                return evaluador.evaluar(resultado);
            }

            if (valorCond instanceof Number && ((Number) valorCond).doubleValue() != 0) {
                return evaluador.evaluar(resultado);
            }
        }

        // Si ninguna condición se cumple y no hay 't'
        return null;
    }
}
