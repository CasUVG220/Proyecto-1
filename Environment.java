import java.util.*;

public class Environment {

    private Map<String, Object> memoria; // Es donde se almazena los Set.

    public Environment() {
        memoria = new HashMap<>();
    }

    // Guarda variables
    public void definir(String simbolo, Object valor) {
        memoria.put(simbolo, valor);
    }

    // Obtiene los valores de algun simbolo
    public Object obtener(String simbolo) {
        if (!memoria.containsKey(simbolo)) {
            throw new IllegalArgumentException("Símbolo no a sido definido: " + simbolo);
        }
        return memoria.get(simbolo);
    }

    // Verificador de simbolos actualesm, es decir si ya esta definido
    public boolean estaDefinido(String simbolo) {
        return memoria.containsKey(simbolo);
    }

    public Set<String> getClaves() {
        return memoria.keySet();
    }

    public void run(List<Object> expresiones, Evaluador evaluador){
        for (Object expresion : expresiones) {
            Object resultado = evaluador.evaluar(expresion);
            System.out.println("Resultado: " + resultado);
        }
    }
}
