import java.util.List;

public class FuncionLisp {
    private String nombre;
    private List<String> parametros;
    private Object cuerpo;

    public FuncionLisp(String nombre, List<String> parametros, Object cuerpo) {
        this.nombre = nombre;
        this.parametros = parametros;
        this.cuerpo = cuerpo;
    }

    public String getNombre() { return nombre;}

    public List<String> getParametros() {return parametros;}

    public Object getCuerpo() { return cuerpo;    }

    @Override
    public String toString() {return "(defun " + nombre + " " + parametros + " " + cuerpo + ")"; }
}
