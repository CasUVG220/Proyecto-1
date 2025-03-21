import java.io.*;
import java.util.*;

//lee el archivo .lisp
public class Lector {

    // devuelve una lista de expresiones LISP 
    public static List<Object> leerArchivo(String ruta) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(ruta));
        StringBuilder contenido = new StringBuilder();
        String linea;

        // Lee el archivo entero linea por linea y se convierte en una sola cadena
        while ((linea = br.readLine()) != null) {
            contenido.append(linea).append(" ");
        }
        br.close();

        // Tokenizador que separa los tokens (paréntesis, comillas y espacios)
        StringTokenizer tokens = new StringTokenizer(contenido.toString(), " ()'", true);

        return parsear(tokens);
    }

    // Convierte listas en tokens
    private static List<Object> parsear(StringTokenizer tokens) {
        List<Object> lista = new ArrayList<>();

        while (tokens.hasMoreTokens()) {
            String token = tokens.nextToken().trim();
            if (token.isEmpty()) continue;

            if (token.equals("(")) {
                lista.add(parsear(tokens));
            } else if (token.equals(")")) {
                break;
            } else if (token.equals("'")) {
                List<Object> quoteLista = new ArrayList<>();
                quoteLista.add("quote");
                Object siguiente = obtenerElemento(tokens);
                quoteLista.add(siguiente);
                lista.add(quoteLista);
            } else {
                lista.add(token);
            }
        }

        return lista;
    }

    private static Object obtenerElemento(StringTokenizer tokens) {
        while (tokens.hasMoreTokens()) {
            String token = tokens.nextToken().trim();
            if (token.isEmpty()) continue;

            if (token.equals("(")) {
                // Devuelve una sublista
                return parsear(tokens);
            } else if (token.equals("'")) {
                // Devuelve una lista (quote ...)
                List<Object> quoteLista = new ArrayList<>();
                quoteLista.add("quote");
                Object siguiente = obtenerElemento(tokens);
                quoteLista.add(siguiente);
                return quoteLista;
            } else {
                // Devuelve un valor simple
                return token;
            }
        }
        return null;
    }
}
