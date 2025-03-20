import java.util.List;
import java.util.Scanner;

import java.util.*;


public class Main {

    public static void main(String[] args) {
        try {
            String ruta = "Lisp.txt"; // o el nombre real de tu archivo
            List<Object> expresiones = Lector.leerArchivo(ruta);

            Environment entorno = new Environment();
            Evaluador evaluador = new Evaluador(entorno);

            for (Object expresion : expresiones) {
                Object resultado = evaluador.evaluar(expresion);
                if (resultado != null && !resultado.toString().trim().isEmpty()) {
                    System.out.println("Resultado: " + resultado);
                }
            }
            

        } catch (Exception e) {
            System.err.println("Error general: " + e.getMessage());
        }
    }

    
}
        }

        scanner.close();
    }
}
