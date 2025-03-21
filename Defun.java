import java.util.*;

public class Defun {

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
