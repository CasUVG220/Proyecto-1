import java.util.List;
import java.util.Scanner;

public class BaseLisp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EnviromentLisp entorno = new EnviromentLisp();

        System.out.println("LISP para java de DinoPythons3000");
        System.out.println("Escribe una expresión en LISP o 'salir' para terminar.");

        while (true) {
            System.out.print("Expresion Lisp: ");
            String entrada = scanner.nextLine().trim();

            if ("salir".equalsIgnoreCase(entrada)) {
                System.out.println("Saliendo del intérprete");
                break;
            }

            try {
                List<String> tokens = ConvertidorLisp.traducirCodigoLisp(entrada);
                Object resultado = EvaluadorLisp.procesarExpresion(tokens, entorno);
                System.out.println("Respuesta: " + resultado);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}