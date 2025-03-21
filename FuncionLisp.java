/**
 * Clase FuncionLisp.
 * Representa una función definida por el usuario en Lisp.
 * Almacena el nombre, la lista de parámetros y el cuerpo de la función.
 */
import java.util.List;

public class FuncionLisp {
    private String nombre;
    private List<String> parametros;
    private Object cuerpo;
//constructor de funcion
    public FuncionLisp(String nombre, List<String> parametros, Object cuerpo) {
        this.nombre = nombre;
        this.parametros = parametros;
        this.cuerpo = cuerpo;
    }
//sets y gets
    public String getNombre() { return nombre;}

    public List<String> getParametros() {return parametros;}

    public Object getCuerpo() { return cuerpo;    }

    @Override
    public String toString() {return "(defun " + nombre + " " + parametros + " " + cuerpo + ")"; }
}
