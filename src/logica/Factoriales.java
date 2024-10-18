package logica;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

import modelos.ModeloFactorial;
import vistas.Inicio;
import vistas.VistaAnalisis;
import vistas.VistaFactorial;

public class Factoriales implements ActionListener, WindowListener, FocusListener {

    ModeloFactorial modelo;
    public static int tipoFactorial;

    public Factoriales(ModeloFactorial modelo) {
        this.modelo = modelo;
    }

    public static long factorial(int numero) {
        try {

            if (numero <= 1) {
                return 1;
            } else {
                return numero * factorial(numero - 1);
            }
        } catch (Exception e) {
            mensajesWindow(3);
            return 0;
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
            mensajesWindow(3);
            return "";
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
        opcionSeleccionada = seleccion;

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
            case 5:
                modelo.getVista().wdwMensaje.setVisible(false);
                modelo.getVista().setVisible(false);
                Inicio vistaInicio = new Inicio();
                vistaInicio.setVisible(true);
                correlativo = 6;
                break;
            default:
                correlativo = 6;
                break;
        }
        return correlativo;
    }

    public void limpiar(int n) {

        if (n != 1) {
            modelo.getVista().txtExpresion.setText("");
            modelo.getVista().txtResultado.setText("");
            modelo.getVista().txtObservaciones.setText("");
            if (modelo.getVista().txtExpresion2.isVisible()) {
                modelo.getVista().txtExpresion2.setText("");
            }
        } else {
            modelo.getVista().txtObservaciones.setText("");
            modelo.getVista().txtExpresion.setText("");
            modelo.getVista().txtResultado.setText("");
            modelo.getVista().txtExpresion2.setText("");
        }
    }

    public void cambiarCalculo() {
        tipoFactorial = Opcion();
        switch (tipoFactorial) {
            case 1:
                modelo.getVista().txtDivisor.setVisible(false);
                modelo.getVista().txtDividendo.setVisible(false);
                modelo.getVista().txtExpresion2.setVisible(false);
                modelo.getVista().txtTitulo.setText("Factorial caso n!");

                break;
            case 2:
                modelo.getVista().txtDivisor.setVisible(true);
                modelo.getVista().txtDividendo.setVisible(true);
                modelo.getVista().txtExpresion2.setVisible(true);
                modelo.getVista().txtTitulo.setText("Factorial caso x! = n!");
                modelo.getVista().txtDividendo.setText("n!");
                modelo.getVista().txtDivisor.setText("x!");
                break;
            case 3:
                modelo.getVista().txtDividendo.setVisible(true);
                modelo.getVista().txtDivisor.setVisible(true);
                modelo.getVista().txtExpresion2.setVisible(true);
                modelo.getVista().txtTitulo.setText("Factorial caso n! / n = (n-1)!");
                modelo.getVista().txtDividendo.setText("n!");
                modelo.getVista().txtDivisor.setText("n");
                break;
            case 4:
                modelo.getVista().txtDivisor.setVisible(true);
                modelo.getVista().txtDividendo.setVisible(true);
                modelo.getVista().txtExpresion2.setVisible(true);
                modelo.getVista().txtTitulo.setText("Factorial caso n! / (n-1)! = n");
                modelo.getVista().txtDividendo.setText("n!");
                modelo.getVista().txtDivisor.setText("(n-1)!");
                break;
            case 5:
                modelo.getVista().txtDivisor.setVisible(false);
                modelo.getVista().txtDividendo.setVisible(false);
                modelo.getVista().txtExpresion2.setVisible(false);
                modelo.getVista().txtTitulo.setText("Factorial caso 0! = 1");
                break;
            default:
                modelo.getVista().txtTitulo.setText("Factorial con formato libre");
                modelo.getVista().txtDivisor.setVisible(false);
                modelo.getVista().txtDividendo.setVisible(false);
                modelo.getVista().txtExpresion2.setVisible(false);
                break;
        }
    }

    public static void mensajesWindow(int n) {
        switch (n) {
            case 1:
                JOptionPane.showMessageDialog(null, "Error: Uno o más campos están en blanco.", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case 2:
                JOptionPane.showMessageDialog(null, "PDF no se puede generar sin resultado", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case 3:
                JOptionPane.showMessageDialog(null, "Ha ingresado caracteres inválidos, operaciones no se pueden realizar", "Error", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }

    public boolean validarSiError(int n) {
        boolean error = true;
        if (n == 1) {
            if (modelo.getVista().txtExpresion.getText().equals("") || Integer.parseInt(modelo.getVista().txtExpresion.getText()) < 0) {
                error = true;
            } else {
                error = false;
            }
        } else {
            if (modelo.getVista().txtExpresion.getText().equals("") || modelo.getVista().txtExpresion2.equals("")
                    || Integer.parseInt(modelo.getVista().txtExpresion.getText()) < 0 || Integer.parseInt(modelo.getVista().txtExpresion2.getText()) < 0) {
                error = true;
            } else {
                error = false;
            }
        }
        return error;
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
        } else if (e.getActionCommand().equals(modelo.getVista().btnLimpiar.getActionCommand())) {
            limpiar(0);
        } else if (e.getActionCommand().equals(modelo.getVista().btnCambiar.getActionCommand())) {
            limpiar(1);
            cambiarCalculo();
        }

        //Verifica si está todo en orden para calcular factorial formato LIBRE
        if (e.getActionCommand().equals(modelo.getVista().btnCalcular.getActionCommand()) && tipoFactorial > 5) {
            boolean flag = validarSiError(1);
            if (!flag) {
                try {
                    modelo.getVista().txtResultado.setText(procesarExpresion(modelo.getVista().txtExpresion.getText()));
                    modelo.getVista().txtObservaciones.setText("Operación realizada exitosamente");
                } catch (NumberFormatException a) {
                    mensajesWindow(3);
                    limpiar(1);
                }
            } else {
                modelo.getVista().txtObservaciones.setText("Existen errores en los campos");
                modelo.getVista().txtResultado.setText("");
                mensajesWindow(1);
            }

            //Verifica si está todo en orden para calcular factorial tipo SIMPLE
        } else if (e.getActionCommand().equals(modelo.getVista().btnCalcular.getActionCommand()) && tipoFactorial == 1) {
            boolean flag = validarSiError(1);
            if (!flag) {
                try {
                    modelo.getVista().txtResultado.setText(String.valueOf(factorial(Integer.parseInt(modelo.getVista().txtExpresion.getText()))));
                    modelo.getVista().txtObservaciones.setText("Operación realizada exitosamente");
                } catch (NumberFormatException a) {
                    mensajesWindow(3);
                    limpiar(1);
                }
            } else {
                modelo.getVista().txtObservaciones.setText("Existen errores en los campos");
                modelo.getVista().txtResultado.setText("");
                mensajesWindow(1);
            }

            //Verifica si está en orden para calcular factorial tipo x! = n!
        } else if (e.getActionCommand().equals(modelo.getVista().btnCalcular.getActionCommand()) && tipoFactorial == 2) {
            boolean flag = validarSiError(2);
            if (!flag) {
                try {
                    long n = factorial(Integer.parseInt(modelo.getVista().txtExpresion.getText()));
                    long x = factorial(Integer.parseInt(modelo.getVista().txtExpresion2.getText()));
                    if (n == x) {
                        modelo.getVista().txtObservaciones.setText("La condición SÍ se cumple");
                    } else {
                        modelo.getVista().txtObservaciones.setText("NO se cumple la condición.");
                    }
                    modelo.getVista().txtResultado.setText("n! = " + String.valueOf(n) + ", x! = " + String.valueOf(x));
                } catch (NumberFormatException a) {
                    mensajesWindow(3);
                    limpiar(1);
                }
            } else {
                mensajesWindow(1);
            }

            //Verifica si está en orden para calcular factorial de tipo n!/n = (n-1)!
        } else if (e.getActionCommand().equals(modelo.getVista().btnCalcular.getActionCommand()) && tipoFactorial == 3) {
            boolean flag = validarSiError(2);
            if (!flag) {
                try {
                    long nFac = factorial(Integer.parseInt(modelo.getVista().txtExpresion.getText()));
                    long x = Integer.parseInt(modelo.getVista().txtExpresion2.getText());
                    long n = Integer.parseInt(modelo.getVista().txtExpresion.getText());
                    long nPr = n - 1;
                    long xFac = factorial((int) nPr);
                    long resultado = nFac / x;
                    if (n == x) {
                        modelo.getVista().txtResultado.setText(String.valueOf(resultado));
                        modelo.getVista().txtObservaciones.setText("Condición cumplida, \nEl factorial de " + nPr + " osea (" + n + "-1) "
                                + "es " + xFac);
                    } else {
                        modelo.getVista().txtResultado.setText(String.valueOf(resultado));
                        modelo.getVista().txtObservaciones.setText("No se cumplió con la condición");
                    }
                } catch (NumberFormatException a) {
                    mensajesWindow(3);
                    limpiar(1);
                }
            } else {
                mensajesWindow(1);
            }

            //Verifica si está en orden para factorial del caso n!/(n-1)! = n
        } else if (e.getActionCommand().equals(modelo.getVista().btnCalcular.getActionCommand()) && tipoFactorial == 4) {
            boolean flag = validarSiError(1);
            if (!flag) {
                try {
                    long nFac = factorial(Integer.parseInt(modelo.getVista().txtExpresion.getText()));
                    long x = Integer.parseInt(modelo.getVista().txtExpresion2.getText());
                    long n = Integer.parseInt(modelo.getVista().txtExpresion.getText());
                    long nPr = n - 1;
                    long xFac = factorial((int) nPr);
                    long resultado = nFac / xFac;
                    System.out.println(x);
                    System.out.println(n);
                    if (x == (n - 1)) {
                        modelo.getVista().txtResultado.setText(String.valueOf(resultado));
                        modelo.getVista().txtObservaciones.setText("Condición cumplida");
                    } else {
                        modelo.getVista().txtResultado.setText(String.valueOf(resultado));
                        modelo.getVista().txtObservaciones.setText("No se cumplió con la condición");
                    }
                } catch (NumberFormatException a) {
                    mensajesWindow(3);
                    limpiar(1);
                }
            } else {
                mensajesWindow(1);
            }

            //Revisa y calcula si está todo en orden para N! = 0
        } else if (e.getActionCommand().equals(modelo.getVista().btnCalcular.getActionCommand()) && tipoFactorial == 5) {
            boolean flag = validarSiError(1);
            if (!flag) {
                try {
                    long prueba = factorial(Integer.parseInt(modelo.getVista().txtExpresion.getText()));
                    if (prueba != 1) {
                        modelo.getVista().txtResultado.setText(String.valueOf(prueba));
                        modelo.getVista().txtObservaciones.setText("La condición NO se cumple, \n"
                                + modelo.getVista().txtExpresion.getText() + " es diferente de cero.");
                    } else {
                        modelo.getVista().txtResultado.setText(String.valueOf(prueba));
                        modelo.getVista().txtObservaciones.setText("La condición SÍ se cumple");
                    }
                } catch (NumberFormatException a) {
                    mensajesWindow(3);
                    limpiar(1);
                }
            } else {
                mensajesWindow(1);
            }

            //Reporte
        } else if (e.getActionCommand().equals(modelo.getVista().btnGenerarPDF.getActionCommand())) {
            if (modelo.getVista().txtResultado.getText().equals("")) {
                mensajesWindow(2);
            } else {
                generar();

            }
        }
    }

    private int opcionSeleccionada = -1; // -1 indica que no se ha seleccionado ninguna opción

    public void generar() {

        if (!(modelo.getVista().txtResultado.equals(""))) {
            String carpetaDescargas = System.getProperty("user.home") + File.separator + "Downloads";
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String timestamp = formatter.format(new Date());
            String nombreArchivo = carpetaDescargas + File.separator + "EjercicioFactorial" + timestamp + ".pdf";
            FileOutputStream archivo = null; // Inicializa la variable
            try {
                archivo = new FileOutputStream(nombreArchivo); // Asigna el archivo

                Document documento = new Document();

                PdfWriter.getInstance(documento, archivo);
                documento.open();
                // Imprime la ruta del archivo para verificar
                System.out.println("Ruta del archivo PDF: " + nombreArchivo);

                formatter = new SimpleDateFormat("dd/MM/yyyy");
                String currentDate = formatter.format(new Date());
                Paragraph dateParagraph = new Paragraph("Fecha: " + currentDate);
                dateParagraph.setAlignment(Paragraph.ALIGN_RIGHT);
                documento.add(dateParagraph);
                com.lowagie.text.Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
                // Crear el párrafo con la fuente personalizada
                Paragraph titulo = new Paragraph("Resolución del problema", tituloFont);
                // Centrar el párrafo
                titulo.setAlignment(Element.ALIGN_CENTER);
                // Agregar el párrafo al documento
                documento.add(titulo);
                documento.add(new Paragraph(" "));
                documento.add(new Paragraph("Descripcion del problema : " + VistaAnalisis.descripcion));
                // iniciar contenido pdf

                VistaFactorial factorial = new VistaFactorial();

                switch (opcionSeleccionada) {
                    case 0: // Opción a

                        documento.add(new Paragraph("Has seleccionado: n! = n · (n-1)!"));
                        documento.add(new Paragraph("Sustitucion de formula : " + "n = " + modelo.getVista().txtExpresion.getText()));
                        documento.add(new Paragraph("Resultado de la operaacion : " + " (n-1)! = " + "(" + modelo.getVista().txtExpresion.getText() + "-1)! = " + modelo.getVista().txtResultado.getText()));
                        break;

                    case 1: // Opción b
                        documento.add(new Paragraph("Has seleccionado: x! = n! → x = n"));
                        documento.add(new Paragraph(" Sustitucion formula : n! = " + modelo.getVista().txtExpresion.getText() + "  x! = " + modelo.getVista().txtExpresion2.getText()));
                        documento.add(new Paragraph("Resultado de la operacion : !x = !n   x = n      " + modelo.getVista().txtResultado.getText()));
                        break;
                    case 2: // Opción c
                        documento.add(new Paragraph("Has seleccionado: n! / n = (n-1)!"));
                        documento.add(new Paragraph("sustitucion formula : n = " + modelo.getVista().txtExpresion.getText()));
                        documento.add(new Paragraph(" Resultado de la operacion : (n-1!) = " + "(" + modelo.getVista().txtExpresion.getText() + "-1)!" + modelo.getVista().txtResultado.getText()));
                        break;
                    case 3: // Opción d
                        documento.add(new Paragraph("Has seleccionado: n! / (n-1)! = n"));
                        documento.add(new Paragraph("sustitucion formula : n = " + modelo.getVista().txtExpresion.getText()));
                        documento.add(new Paragraph(" Resultado de la operacion :n! / (n-1)! = n     " + modelo.getVista().txtResultado.getText()));
                        break;
                    case 4: // Opción e
                        documento.add(new Paragraph("Has seleccionado: 0! = 1"));
                        documento.add(new Paragraph(" Sustitucion formula : " + modelo.getVista().txtExpresion.getText()));
                        documento.add(new Paragraph("Resultado de la operacion : "));

                        break;
                    case 5: // formato libre
                        documento.add(new Paragraph("Has seleccionado el formato libre "));
                        documento.add(new Paragraph("Sustitucion factorial : " + modelo.getVista().txtExpresion.getText()));
                        documento.add(new Paragraph("Resultado de la operacion : " + modelo.getVista().txtExpresion.getText()));
                        break;
                    default:
                        documento.add(new Paragraph("No se ha seleccionado ninguna opción válida."));
                        break;
                }
                JOptionPane.showMessageDialog(null, "Guardado en: " + nombreArchivo, "Reporte generado exitosamente", JOptionPane.INFORMATION_MESSAGE);

                documento.close();
                archivo.close();

            } catch (Exception e) {
            }
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        modelo.getVista().setLocationRelativeTo(null);
        modelo.getVista().setVisible(false);
        cambiarCalculo();
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
        if (e.getComponent().equals(modelo.getVista().txtExpresion)) {

        }
    }

    @Override
    public void focusLost(FocusEvent e) {
    }
}
