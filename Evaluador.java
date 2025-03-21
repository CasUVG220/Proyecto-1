/**
 * Clase Evaluador.
 * Se encarga de analizar las expresiones Lisp y delegar su evaluación
 * a las clases correspondientes según el tipo de instrucción.
 */

import java.util.*;

public class Evaluador {

    private Environment entorno;

    public Evaluador(Environment entorno) {
        this.entorno = entorno;
    }
/** Evalúa una expresión de Lisp, ya sea un átomo o una lista. **/

    public Object evaluar(Object expresion) {
        if (expresion instanceof String) {
            String simbolo = (String) expresion;

            if (esNumero(simbolo)) {
                return convertirNumero(simbolo);
            }

            if (entorno.estaDefinido(simbolo)) {
                return entorno.obtener(simbolo);
            } else {
                throw new IllegalArgumentException("Símbolo no definido: " + simbolo);
            }
        }

        if (expresion instanceof Number) {
            return expresion;
        }

        if (expresion instanceof List) {
            List<Object> lista = (List<Object>) expresion;
            if (lista.isEmpty()) return null;

            Object operador = lista.get(0);
            if (!(operador instanceof String)) {
                throw new IllegalArgumentException("Operador inválido: " + operador);
            }

            String simbolo = (String) operador;

            switch (simbolo) {
                case "+":
                case "-":
                case "*":
                case "/":
                    return Aritmetica.evaluar(simbolo, lista.subList(1, lista.size()), this, entorno);
                case "setq":
                    return Setq.evaluar(lista, this, entorno);
                case "defun":
                    return Defun.evaluar(lista, entorno);
                case "cond":
                    return Cond.evaluar(lista, this, entorno);
                case "=":
                case "<":
                case ">":
                case "<=":
                case ">=":
                case "!=":
                    return Comparacion.evaluar(simbolo, lista.subList(1, lista.size()), this, entorno);
                case "quote":
                    return Quote.evaluar(lista);
                    case "atom":
                    return Predicados.evaluarAtom(lista, this, entorno);
                case "list":
                    return Predicados.evaluarList(lista, this, entorno);
                case "equal":
                    return Predicados.evaluarEqual(lista, this, entorno);
                    case "print":
                    return Impresion.evaluar(lista, this, entorno);
                
            }

            if (entorno.estaDefinido(simbolo)) {
                Object posibleFuncion = entorno.obtener(simbolo);

                if (posibleFuncion instanceof FuncionLisp) {
                    FuncionLisp funcion = (FuncionLisp) posibleFuncion;
                    List<String> parametros = funcion.getParametros();
                    Object cuerpo = funcion.getCuerpo();

                    if (lista.size() - 1 != parametros.size()) {
                        throw new IllegalArgumentException("Cantidad de argumentos incorrecta para la función " + funcion.getNombre());
                    }

                    Environment entornoLocal = new Environment();
                    for (String clave : entorno.getClaves()) {
                        entornoLocal.definir(clave, entorno.obtener(clave));
                    }

                    for (int i = 0; i < parametros.size(); i++) {
                        Object valorArg = evaluar(lista.get(i + 1));
                        entornoLocal.definir(parametros.get(i), valorArg);
                    }

                    Evaluador evalLocal = new Evaluador(entornoLocal);
                    return evalLocal.evaluar(cuerpo);
                }
            }

            throw new IllegalArgumentException("Símbolo no soportado aún: " + simbolo);
        }

        throw new IllegalArgumentException("Expresión inválida: " + expresion);
    }

    private boolean esNumero(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private Object convertirNumero(String str) {
        if (str.contains(".")) {
            return Double.parseDouble(str);
        } else {
            return Integer.parseInt(str);
        }
    }
} 
