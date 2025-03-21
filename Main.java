/**

Clase principal del intérprete Lisp.
Se encarga de iniciar el entorno, leer el archivo de código Lisp
y evaluar cada expresión utilizando el Evaluador.
Solo se imprime en consola si el usuario utiliza la función (print ...).

**/ 

import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            List<Object> expresiones = Lector.leerArchivo("Lisp.txt");
            Environment entorno = new Environment();
            Evaluador evaluador = new Evaluador(entorno);

            for (Object expresion : expresiones) {
                try {
                    evaluador.evaluar(expresion);
                } catch (Exception e) {
                    System.err.println("Error general: " + e.getMessage());
                }
            }

        } catch (Exception e) {
            System.err.println("Error al leer archivo: " + e.getMessage());
        }
    }
}
