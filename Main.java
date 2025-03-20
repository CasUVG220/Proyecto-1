import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            List<Object> expresiones = Lector.leerArchivo("codigo.lisp");
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


    
}
        }

        scanner.close();
    }
}
