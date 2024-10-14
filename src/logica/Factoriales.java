package logica;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import modelos.ModeloFactorial;

public class Factoriales implements ActionListener, WindowListener{
    
    ModeloFactorial modelo;

    public Factoriales(ModeloFactorial modelo) {
        this.modelo = modelo;
    }
    
    
    public static long factorial(int numero){
        if(numero <= 1){
            return 1;
        } else {
            return numero * factorial(numero - 1);
        }
    }
    
    public static String procesarExpresion(String expresion) {
        // Buscar los factoriales usando Regex y reemplazarlos con su valor
        Pattern p = Pattern.compile("(\\d+)!");
        Matcher m = p.matcher(expresion);

        while (m.find()) {
            // Capturamos el número antes del símbolo de factorial
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(modelo.getVista().btnCalcular.getActionCommand())){
            modelo.getVista().txtResultado.setText(procesarExpresion(modelo.getVista().txtExpresion.getText()));
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
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
