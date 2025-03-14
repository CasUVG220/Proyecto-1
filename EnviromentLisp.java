import java.util.HashMap;
import java.util.Map;

public class EnviromentLisp {
    private final Map<String, Integer> memoria;

    public EnviromentLisp() {
        this.memoria = new HashMap<>();
    }

    public void guardarValor(String nombre, int valor) {
        memoria.put(nombre, valor);
    }

    // Cambiar `int` por `Integer` para permitir valores `null`
    public Integer obtenerValor(String nombre) {
        return memoria.get(nombre);  // Retornará `null` si la variable no existe
    }

    public static void main(String[] args) {
        EnviromentLisp entorno = new EnviromentLisp();
        entorno.guardarValor("x", 10);
        System.out.println("Valor de x: " + entorno.obtenerValor("x"));

        // Prueba con variable no existente
        System.out.println("Valor de y: " + entorno.obtenerValor("y")); // Debe imprimir `null`
    }
}