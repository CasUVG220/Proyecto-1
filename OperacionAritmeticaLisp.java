import java.util.List;

// Clase encargada de ejecutar operaciones aritméticas en LISP
public class OperacionAritmeticaLisp {

    // Evalúa una operación aritmética en LISP
    public static int procesarOperacion(List<String> expresion) {
        if (expresion.size() < 3) {
            throw new IllegalArgumentException("No se puede. Ejemplo: (+ 3 5)");
        }

        String operador = expresion.get(0);
        int resultado = Integer.parseInt(expresion.get(1));

        for (int i = 2; i < expresion.size(); i++) {
            int valor = Integer.parseInt(expresion.get(i));
            resultado = calcular(resultado, valor, operador);
        }
        return resultado;
    }

    private static int calcular(int a, int b, String operador) {
        switch (operador) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b == 0) throw new ArithmeticException("División por cero");
                return a / b;
            default:
                throw new IllegalArgumentException("Operador desconocido: " + operador);
        }
    }

    public static void main(String[] args) {
        System.out.println("Resultado (+ 3 5): " + procesarOperacion(List.of("+", "3", "5")));
        System.out.println("Resultado (* 2 4): " + procesarOperacion(List.of("*", "2", "4")));
    }
}