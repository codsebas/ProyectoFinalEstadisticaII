package logica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import modelos.ModeloFactorial;

public class Factoriales implements ActionListener, WindowListener {

    ModeloFactorial modelo;
    int tipoFactorial;

    public Factoriales(ModeloFactorial modelo) {
        this.modelo = modelo;
    }

    // Función con el método general de los factoriales utilizando recursividad
    public static long factorial(int numero) {
        if (numero <= 1) {
            return 1;
        } else {
            return numero * factorial(numero - 1);
        }
    }

    //Formato libre 
    public static String procesarExpresion(String expresion) {
        // Buscar los factoriales usando Regex y reemplazarlos con su valor
        Pattern p = Pattern.compile("(\\d+)!");
        Matcher m = p.matcher(expresion);

        while (m.find()) {
            // Capturar número antes del símbolo de factorial
            int numero = Integer.parseInt(m.group(1));
            // Calculamos el factorial
            long resultadoFactorial = factorial(numero);
            // Reemplazamos "n!" por el valor calculado del factorial
            expresion = expresion.replace(m.group(0), Long.toString(resultadoFactorial));
        }

        // Ahora que hemos reemplazado los factoriales, evaluamos el resto de la expresión
        try {
            // Usamos el JavaScript Engine para evaluar la expresión restante
            return evalExpression(expresion);
        } catch (Exception e) {
            return "Error en la expresión: " + e.getMessage();
        }
    }

    // Método que usa JavaScript Engine para evaluar la expresión
    public static String evalExpression(String expresion) throws Exception {
        // Usar el motor JavaScript para evaluar la expresión matemática
        javax.script.ScriptEngineManager manager = new javax.script.ScriptEngineManager();
        javax.script.ScriptEngine engine = manager.getEngineByName("JavaScript");

        Object resultado = engine.eval(expresion);
        return resultado.toString();
    }

    //Método para verificar que es lo que se quiere resolver
    public int Opcion() {
        String[] opciones = {
            "a) n! = n · (n-1)!",
            "b) x! = n! → x = n",
            "c) n! / n = (n-1)!",
            "d) n! / (n-1)! = n",
            "e) 0! = 1"
        };

        // Mostrar JOptionPane con las opciones
        int seleccion = JOptionPane.showOptionDialog(
                null,
                "Seleccione una opción para calcular el factorial",
                "Cálculo de Factorial",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        // Asignar un correlativo dependiendo de la selección del usuario
        int correlativo = 0;

        switch (seleccion) {
            case 0:
                correlativo = 1; // Opción a
                break;
            case 1:
                correlativo = 2; // Opción b
                break;
            case 2:
                correlativo = 3; // Opción c
                break;
            case 3:
                correlativo = 4; // Opción d
                break;
            case 4:
                correlativo = 5; // Opción e
                break;
            default:
                correlativo = 6;
                break;
        }
        return correlativo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(modelo.getVista().btnAyuda.getActionCommand())) {
            modelo.getVista().wdwMensaje.setTitle("Ayuda");
            modelo.getVista().wdwMensaje.setLocationRelativeTo(null);
            modelo.getVista().wdwMensaje.setVisible(true);
        }
        if (e.getActionCommand().equals(modelo.getVista().btnCalcular.getActionCommand()) && tipoFactorial > 5) {
            modelo.getVista().txtResultado.setText(procesarExpresion(modelo.getVista().txtExpresion.getText()));
        } else if (e.getActionCommand().equals(modelo.getVista().btnCalcular.getActionCommand()) && tipoFactorial == 1) {
            modelo.getVista().txtResultado.setText(String.valueOf(factorial(Integer.parseInt(modelo.getVista().txtExpresion.getText()))));
        } else if (e.getActionCommand().equals(modelo.getVista().btnCalcular.getActionCommand()) && tipoFactorial == 2) {
            modelo.getVista().txtResultado.setText(modelo.getVista().txtExpresion.getText() + " = " + modelo.getVista().txtExpresion2.getText() +
                    " factorial = " + procesarExpresion(modelo.getVista().txtExpresion.getText()));
        } else if (e.getActionCommand().equals(modelo.getVista().btnCalcular.getActionCommand()) && tipoFactorial == 3) {
        } else if (e.getActionCommand().equals(modelo.getVista().btnCalcular.getActionCommand()) && tipoFactorial == 4) {
        } else if (e.getActionCommand().equals(modelo.getVista().btnCalcular.getActionCommand()) && tipoFactorial == 5) {
            modelo.getVista().txtResultado.setText(String.valueOf(factorial(Integer.parseInt(modelo.getVista().txtExpresion.getText()))));
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        modelo.getVista().setLocationRelativeTo(null);
        modelo.getVista().setVisible(false);
        tipoFactorial = Opcion();
        switch (tipoFactorial) {
            case 1:
                modelo.getVista().txtTitulo.setText("Factorial caso n!");
                break;
            case 2:
                modelo.getVista().txtTitulo.setText("Factorial caso x! = n!");
                break;
            case 3:
                modelo.getVista().txtTitulo.setText("Factorial caso n! / n = (n-1)!");
                break;
            case 4:
                modelo.getVista().txtTitulo.setText("Factorial caso n! / (n-1)! = n");
                break;
            case 5:
                modelo.getVista().txtTitulo.setText("Factorial caso 0! = 1");
                break;
            default:
                modelo.getVista().txtTitulo.setText("Factorial con formato libre");
                break;
        }
        modelo.getVista().setVisible(true);
    }

    @Override
    public void windowClosing(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}
