import java.util.*;

public class Predicados {
// Evalúa si un elemento es un átomo 
    public static Object evaluarAtom(List<Object> lista, Evaluador evaluador, Environment entorno) {
        if (lista.size() != 2) {
            throw new IllegalArgumentException("Uso incorrecto de atom");
        }
        Object valor = evaluador.evaluar(lista.get(1));
        return !(valor instanceof List); // Si no es lista, es átomo
    }
// Evalúa si un elemento es una lista
    public static Object evaluarList(List<Object> lista, Evaluador evaluador, Environment entorno) {
        if (lista.size() != 2) {
            throw new IllegalArgumentException("Uso incorrecto de list");
        }
        Object valor = evaluador.evaluar(lista.get(1));
        return (valor instanceof List); // Devuelve true si es lista
    }
// Evalúa si dos elementos son iguales
    public static Object evaluarEqual(List<Object> lista, Evaluador evaluador, Environment entorno) {
        if (lista.size() != 3) {
            throw new IllegalArgumentException("Uso incorrecto de equal");
        }
        Object val1 = evaluador.evaluar(lista.get(1));
        Object val2 = evaluador.evaluar(lista.get(2));
// Si ambos son listas, usamos equals. Si no, comparamos con equals genérico
        if (val1 instanceof List && val2 instanceof List) {
            return val1.equals(val2);
        }
        return Objects.equals(val1, val2);
    }
} 
