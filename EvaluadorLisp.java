import java.util.ArrayList;
import java.util.List;

public class EvaluadorLisp {

    public static Object procesarExpresion(List<String> expresion, EnviromentLisp entorno) {
        if (expresion.isEmpty()) {
            throw new IllegalArgumentException("Expresión vacía.");
        }

        // Ignorar paréntesis
        List<String> expresionSinParentesis = new ArrayList<>();
        for (String token : expresion) {
            if (!token.equals("(") && !token.equals(")")) {
                expresionSinParentesis.add(token);
            }
        }

        String operador = expresionSinParentesis.get(0);

        // Reemplazar variables con sus valores
        List<String> valores = new ArrayList<>();
        for (String token : expresionSinParentesis) {
            Integer valorVariable = entorno.obtenerValor(token);
            valores.add(valorVariable != null ? valorVariable.toString() : token);
        }

        // Operaciones aritméticas
        if ("+".equals(operador) || "-".equals(operador) || "*".equals(operador) || "/".equals(operador)) {
            return OperacionAritmeticaLisp.procesarOperacion(valores);
        }

        // Asignar variable con SETQ
        if ("SETQ".equalsIgnoreCase(operador)) {
            if (valores.size() != 3) {
                throw new IllegalArgumentException("Uso incorrecto de SETQ. Ejemplo: (SETQ x 5)");
            }
            String variable = valores.get(1);
            int valor = Integer.parseInt(valores.get(2));
            entorno.guardarValor(variable, valor);
            return valor;
        }

        // Obtener valor de una variable almacenada
        Integer valorVariable = entorno.obtenerValor(operador);
        if (valorVariable != null) {
            return valorVariable;
        }

        // En lugar de tirar error, devolvemos un mensaje 
        return "Variable '" + operador + "' no definida.";
    }

    public static void main(String[] args) {
        EnviromentLisp entorno = new EnviromentLisp();
        System.out.println("Resultado (+ 3 5): " + procesarExpresion(List.of("+", "3", "5"), entorno));

        // Prueba con variables definidas
        procesarExpresion(List.of("SETQ", "a", "15"), entorno);
        procesarExpresion(List.of("SETQ", "b", "20"), entorno);
        System.out.println("Valor de a: " + procesarExpresion(List.of("a"), entorno));
        System.out.println("Valor de b: " + procesarExpresion(List.of("b"), entorno));

        // Prueba con operación entre variables
        System.out.println("Resultado (+ a b): " + procesarExpresion(List.of("+", "a", "b"), entorno));
    }
}