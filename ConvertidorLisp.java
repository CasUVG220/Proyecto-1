import java.util.ArrayList;
import java.util.List;

// Clase encargada de convertir código LISP en estructuras manejables en Java mediante TOKENS
public class ConvertidorLisp {

    // Convierte una cadena de texto en una lista de tokens LISP 
    public static List<String> traducirCodigoLisp(String codigo) {
        List<String> tokens = new ArrayList<>();
        StringBuilder tokenActual = new StringBuilder();

        for (char c : codigo.toCharArray()) {
            if (c == '(' || c == ')') {
                if (tokenActual.length() > 0) {
                    tokens.add(tokenActual.toString());
                    tokenActual.setLength(0);
                }
                tokens.add(String.valueOf(c));  // Agregar paréntesis como token separado
            } else if (Character.isWhitespace(c)) {
                if (tokenActual.length() > 0) {
                    tokens.add(tokenActual.toString());
                    tokenActual.setLength(0);
                }
            } else {
                tokenActual.append(c); // Acumular caracteres en el token
            }
        }

        if (tokenActual.length() > 0) {
            tokens.add(tokenActual.toString());
        }

        return tokens;
    }

    public static void main(String[] args) {
        // Prueba final del convertidor
        String codigoEjemplo = "(+ 3 (* 2 4))";
        List<String> tokens = traducirCodigoLisp(codigoEjemplo);

        System.out.println("Código LISP: " + codigoEjemplo);
        System.out.print("Tokens generados: [");
        for (int i = 0; i < tokens.size(); i++) {
            System.out.print("\"" + tokens.get(i) + "\"");
            if (i < tokens.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}