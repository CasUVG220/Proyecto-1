/**
 * Clase Defun.
 * Implementa la definición de funciones en Lisp mediante la instrucción (defun ...).
 * Permite almacenar funciones definidas por el usuario, incluyendo recursividad y paso de parámetros.
 */
import java.util.*;

public class Defun {
    /**
     * Evalúa una definición de función.
     * @param lista Lista con la definición: (defun nombre (parametros) cuerpo).
     * @param evaluador Referencia al evaluador principal.
     * @param entorno Entorno de ejecución donde se almacenará la función.
     * @return null (no se imprime nada al definir una función).
     */
    public static Object evaluar(List<Object> lista, Environment entorno) {
        if (lista.size() != 4) {
            throw new IllegalArgumentException("Uso incorrecto de defun");
        }

        Object nombreObj = lista.get(1);
        Object parametrosObj = lista.get(2);
        Object cuerpo = lista.get(3);

        if (!(nombreObj instanceof String)) {
            throw new IllegalArgumentException("El nombre de la función debe ser un String");
        }

        if (!(parametrosObj instanceof List)) {
            throw new IllegalArgumentException("Los parámetros deben ser una lista.");
        }

        String nombreFuncion = (String) nombreObj;
        List<Object> parametrosRaw = (List<Object>) parametrosObj;
        List<String> parametros = new ArrayList<>();

        for (Object param : parametrosRaw) {
            if (!(param instanceof String)) {
                throw new IllegalArgumentException("Todos los parámetros deben ser String.");
            }
            parametros.add((String) param);
        }

        FuncionLisp f = new FuncionLisp(nombreFuncion, parametros, cuerpo);

        entorno.definir(nombreFuncion, f);

        return null; // retornamos nombre de la funcion para confirmar que se definio correctamente
    }
}
