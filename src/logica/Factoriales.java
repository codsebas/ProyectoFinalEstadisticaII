package logica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

import modelos.ModeloFactorial;
import vistas.Inicio;

public class Factoriales implements ActionListener, WindowListener, FocusListener {

    ModeloFactorial modelo;
    int tipoFactorial;

    public Factoriales(ModeloFactorial modelo) {
        this.modelo = modelo;
    }

    public static long factorial(int numero) {
        if (numero <= 1) {
            return 1;
        } else {
            return numero * factorial(numero - 1);
        }
    }

    //Formato libre 
    public static String procesarExpresion(String expresion) {
        Pattern p = Pattern.compile("(\\d+)!");
        Matcher m = p.matcher(expresion);

        while (m.find()) {
            int numero = Integer.parseInt(m.group(1));
            long resultadoFactorial = factorial(numero);
            expresion = expresion.replace(m.group(0), Long.toString(resultadoFactorial));
        }

        try {
            return evalExpression(expresion);
        } catch (Exception e) {
            return "Error en la expresión: " + e.getMessage();
        }
    }

    public static String evalExpression(String expresion) throws Exception {
        javax.script.ScriptEngineManager manager = new javax.script.ScriptEngineManager();
        javax.script.ScriptEngine engine = manager.getEngineByName("JavaScript");

        Object resultado = engine.eval(expresion);
        return resultado.toString();
    }

    public int Opcion() {
        String[] opciones = {
            "a) n! = n · (n-1)!",
            "b) x! = n! → x = n",
            "c) n! / n = (n-1)!",
            "d) n! / (n-1)! = n",
            "e) 0! = 1",
            "Salir"
        };

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
            case 6:
                modelo.getVista().wdwMensaje.setVisible(false);
                modelo.getVista().setVisible(false);
                Inicio vistaInicio = new Inicio();
                vistaInicio.setVisible(true);
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
        } else if (e.getActionCommand().equals(modelo.getVista().btnAyuda.getActionCommand())) {
            modelo.getVista().txtExpresion.setText("");
            modelo.getVista().txtResultado.setText("");
            if (modelo.getVista().txtExpresion2.equals(true)) {
                modelo.getVista().txtExpresion2.setText("");

            }
        }

        if (e.getActionCommand().equals(modelo.getVista().btnCalcular.getActionCommand()) && tipoFactorial > 5) {
            modelo.getVista().txtResultado.setText(procesarExpresion(modelo.getVista().txtExpresion.getText()));

        } else if (e.getActionCommand().equals(modelo.getVista().btnCalcular.getActionCommand()) && tipoFactorial == 1) {
            modelo.getVista().txtResultado.setText(String.valueOf(factorial(Integer.parseInt(modelo.getVista().txtExpresion.getText()))));

        } else if (e.getActionCommand().equals(modelo.getVista().btnCalcular.getActionCommand()) && tipoFactorial == 2) {
            modelo.getVista().txtResultado.setText(modelo.getVista().txtExpresion.getText() + " = " + modelo.getVista().txtExpresion2.getText()
                    + " factorial = " + procesarExpresion(modelo.getVista().txtExpresion.getText()));
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
                modelo.getVista().txtDivisor.setVisible(false);
                modelo.getVista().txtDividendo.setVisible(false);
                modelo.getVista().txtExpresion2.setVisible(false);
                break;
            case 2:
                modelo.getVista().txtTitulo.setText("Factorial caso x! = n!");
                modelo.getVista().txtDividendo.setText("n!");
                modelo.getVista().txtDivisor.setText("x!");
                break;
            case 3:
                modelo.getVista().txtTitulo.setText("Factorial caso n! / n = (n-1)!");
                modelo.getVista().txtDivisor.setVisible(true);
                modelo.getVista().txtExpresion2.setVisible(true);
                modelo.getVista().txtDividendo.setText("n!");
                modelo.getVista().txtDivisor.setText("n");
                break;
            case 4:
                modelo.getVista().txtTitulo.setText("Factorial caso n! / (n-1)! = n");
                modelo.getVista().txtDividendo.setText("n!");
                modelo.getVista().txtDivisor.setText("(n-1)!");
                break;
            case 5:
                modelo.getVista().txtTitulo.setText("Factorial caso 0! = 1");
                modelo.getVista().txtDivisor.setVisible(false);
                modelo.getVista().txtDividendo.setVisible(false);
                modelo.getVista().txtExpresion2.setVisible(false);
                break;
            default:
                modelo.getVista().txtTitulo.setText("Factorial con formato libre");
                modelo.getVista().txtDivisor.setVisible(false);
                modelo.getVista().txtDividendo.setVisible(false);
                modelo.getVista().txtExpresion2.setVisible(false);
                break;
        }
        modelo.getVista().setVisible(true);
    }

    @Override
    public void windowClosing(WindowEvent e) {
        modelo.getVista().wdwMensaje.dispose();
        modelo.getVista().dispose();
        Inicio vistaInicio = new Inicio();
        vistaInicio.setVisible(true);
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

    @Override
    public void focusGained(FocusEvent e) {
        if(e.getComponent().equals(modelo.getVista().txtExpresion)){
            
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
    }
}
