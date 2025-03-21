/**
 * Clase PruebaEvaluador.
 * Contiene pruebas JUnit para verificar que el interprete funciona correctamente
 */
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

public class PruebasJUnit {
    private Evaluador evaluador;
    private Environment entorno;

    @Before
    public void configurar() {
        entorno = new Environment();
        evaluador = new Evaluador(entorno);
    }

    @Test
    public void pruebaSetq() {
        List<Object> expr = Arrays.asList("setq", "x", 10);
        Object resultado = evaluador.evaluar(expr);
        assertEquals(10, resultado);
    }

    @Test
    public void pruebaSuma() {
        evaluador.evaluar(Arrays.asList("setq", "a", 5));
        evaluador.evaluar(Arrays.asList("setq", "b", 3));
        Object suma = evaluador.evaluar(Arrays.asList("+", "a", "b"));
        assertEquals(8.0, ((Number) suma).doubleValue(), 0.001);
    }

    @Test
    public void pruebaFuncionDefinida() {
        List<Object> def = Arrays.asList("defun", "cuadrado", Arrays.asList("n"), Arrays.asList("*", "n", "n"));
        evaluador.evaluar(def);
        Object resultado = evaluador.evaluar(Arrays.asList("cuadrado", 4));
        assertEquals(16.0, ((Number) resultado).doubleValue(), 0.001);
    }

    @Test
    public void pruebaCondicional() {
        evaluador.evaluar(Arrays.asList("setq", "x", 8));
        List<Object> cond = Arrays.asList("cond",
            Arrays.asList(Arrays.asList("<", "x", 5), Arrays.asList("quote", "pequeno")),
            Arrays.asList(Arrays.asList("=", "x", 8), Arrays.asList("quote", "medio")),
            Arrays.asList("t", Arrays.asList("quote", "grande"))
        );
        Object resultado = evaluador.evaluar(cond);
        assertEquals("medio", resultado);
    }


    @Test
    public void pruebaRecursividadFactorial() {
        List<Object> def = Arrays.asList("defun", "factorial", Arrays.asList("n"),
            Arrays.asList("cond",
                Arrays.asList(Arrays.asList("=", "n", 0), 1),
                Arrays.asList("t", Arrays.asList("*", "n", Arrays.asList("factorial", Arrays.asList("-", "n", 1))))
            )
        );
        evaluador.evaluar(def);
        Object resultado = evaluador.evaluar(Arrays.asList("factorial", 5));
        assertEquals(120.0, ((Number) resultado).doubleValue(), 0.001);
    }
}
