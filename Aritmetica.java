import java.util.*;

public class Aritmetica {

    public static Object evaluar(String operador, List<Object> argumentos, Evaluador evaluador, Environment entorno) {
        List<Number> valores = new ArrayList<>();

        // Evalua todos los argumentos y los convierte en numeros
        for (Object arg : argumentos) {
            Object valor = evaluador.evaluar(arg);
            if (!(valor instanceof Number)) {
                throw new IllegalArgumentException("Argumento no numérico en operación aritmética: " + valor);
            }
            valores.add((Number) valor);
        }

        switch (operador) {
            case "+":
                return sumar(valores);
            case "-":
                return restar(valores);
            case "*":
                return multiplicar(valores);
            case "/":
                return dividir(valores);
            default:
                throw new IllegalArgumentException("Operador aritmético no soportado: " + operador);
        }
    }

    private static Number sumar(List<Number> valores) {
        double total = 0;
        for (Number n : valores) {
            total += n.doubleValue();
        }
        return total % 1 == 0 ? (int) total : total;
    }

    private static Number restar(List<Number> valores) {
        if (valores.isEmpty()) return 0;
        double resultado = valores.get(0).doubleValue();
        for (int i = 1; i < valores.size(); i++) {
            resultado -= valores.get(i).doubleValue();
        }
        return resultado % 1 == 0 ? (int) resultado : resultado;
    }

    private static Number multiplicar(List<Number> valores) {
        double total = 1;
        for (Number n : valores) {
            total *= n.doubleValue();
        }
        return total % 1 == 0 ? (int) total : total;
    }

    private static Number dividir(List<Number> valores) {
        if (valores.isEmpty()) throw new IllegalArgumentException("División sin argumentos.");
        double resultado = valores.get(0).doubleValue();
        for (int i = 1; i < valores.size(); i++) {
            double divisor = valores.get(i).doubleValue();
            if (divisor == 0) throw new IllegalArgumentException("División por cero.");
            resultado /= divisor;
        }
        return resultado % 1 == 0 ? (int) resultado : resultado;
    }
}
