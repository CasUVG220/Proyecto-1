import java.util.*;

public class Comparacion {
// Evalúa operadores de comparación: =, <, >, <=, >=, !=
    public static Object evaluar(String operador, List<Object> argumentos, Evaluador evaluador, Environment entorno) {
        if (argumentos.size() != 2) {
            throw new IllegalArgumentException("El operador " + operador + " necesita exactamente 2 argumentos.");
        }

        Object arg1 = evaluador.evaluar(argumentos.get(0));
        Object arg2 = evaluador.evaluar(argumentos.get(1));
// Se asegura que ambos argumentos sean numéricos
        if (!(arg1 instanceof Number) || !(arg2 instanceof Number)) {
            throw new IllegalArgumentException("Los argumentos de una comparación deben ser numéricos.");
        }

        double val1 = ((Number) arg1).doubleValue();
        double val2 = ((Number) arg2).doubleValue();
// Devuelve el resultado de la comparación correspondiente
        switch (operador) {
            case "=":
                return val1 == val2;
            case "<":
                return val1 < val2;
            case ">":
                return val1 > val2;
            case "<=":
                return val1 <= val2;
            case ">=":
                return val1 >= val2;
            case "!=":
                return val1 != val2;
            default:
                throw new IllegalArgumentException("Operador de comparación no válido: " + operador);
        }
    }
}
