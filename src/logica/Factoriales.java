package logica;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import modelos.ModeloFactorial;
import vistas.Inicio;
import vistas.VistaAnalisis;
import vistas.VistaFactorial;

public class Factoriales implements ActionListener, WindowListener, KeyListener {

    ModeloFactorial modelo;
    public static int tipoFactorial;

    private void inicializar() {
        modelo.getVista().txtExpresion.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                Factoriales.this.keyTyped(e);
            }
        });
        modelo.getVista().txtExpresion.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (tipoFactorial == 4) {
                    String currentText = modelo.getVista().txtExpresion.getText();
                    try {
                        if (!currentText.isEmpty()) {
                            int asigNum = Integer.parseInt(currentText) - 1;
                            modelo.getVista().txtExpresion2.setText(String.valueOf(asigNum));
                        } else {
                            modelo.getVista().txtExpresion2.setText("");
                        }
                    } catch (NumberFormatException ex) {
                        System.out.println(ex.getMessage());
                        modelo.getVista().txtExpresion2.setText("");
                    }
                } else if (tipoFactorial == 3) {
                    String currentText = modelo.getVista().txtExpresion.getText();
                    try {
                        if (!currentText.isEmpty()) {
                            int asigNum = Integer.parseInt(currentText);
                            modelo.getVista().txtExpresion2.setText(String.valueOf(asigNum));
                        } else {
                            modelo.getVista().txtExpresion2.setText("");
                        }
                    } catch (NumberFormatException ex) {
                        System.out.println(ex.getMessage());
                        modelo.getVista().txtExpresion2.setText("");
                    }
                }
            }
        });
    }

    public Factoriales(ModeloFactorial modelo) {
        this.modelo = modelo;
        inicializar();
    }

    public static int factorial(int numero) {
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

    public static BigInteger factorialBig(int numero) {
        try {
            if (numero < 0) {
                throw new IllegalArgumentException("El número debe ser no negativo.");
            } else if (numero == 0 || numero == 1) {
                return BigInteger.ONE; // 0! y 1! son 1
            } else {
                BigInteger resultado = BigInteger.ONE;
                for (int i = 2; i <= numero; i++) {
                    resultado = resultado.multiply(BigInteger.valueOf(i));
                }
                return resultado;
            }
        } catch (Exception e) {
            mensajesWindow(3); // Llamada a tu método para mostrar mensajes
            return BigInteger.ZERO; // Retornar BigInteger.ZERO en caso de error
        }
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
                this.modelo.getVista().setVisible(false);
                Inicio vistaInicio = new Inicio();
                vistaInicio.setVisible(true);
                break;
            default:
                correlativo = 1;
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
                modelo.getVista().txtExpresion2.setEditable(false);
                modelo.getVista().txtTitulo.setText("Factorial caso n! / n = (n-1)!");
                modelo.getVista().txtDividendo.setText("n!");
                modelo.getVista().txtDivisor.setText("n");
                break;
            case 4:
                modelo.getVista().txtDivisor.setVisible(true);
                modelo.getVista().txtDividendo.setVisible(true);
                modelo.getVista().txtExpresion2.setVisible(true);
                modelo.getVista().txtExpresion2.setEditable(false);
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
                modelo.getVista().txtDivisor.setVisible(false);
                modelo.getVista().txtDividendo.setVisible(false);
                modelo.getVista().txtExpresion2.setVisible(false);
                modelo.getVista().txtTitulo.setText("Factorial caso n!");
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

    public void regresar() {
        this.modelo.getVista().setVisible(false);
        Inicio inicio = new Inicio();
        inicio.setVisible(true);
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
        } else if (e.getActionCommand().equals(modelo.getVista().btnRegresar.getActionCommand())) {
            regresar();
        }

        //Verifica si está todo en orden para calcular factorial tipo SIMPLE
        if (e.getActionCommand().equals(modelo.getVista().btnCalcular.getActionCommand()) && tipoFactorial == 1) {
            boolean flag = validarSiError(1);
            if (!flag) {
                try {
                    modelo.getVista().txtResultado.setText(String.valueOf(factorialBig(Integer.parseInt(modelo.getVista().txtExpresion.getText()))));
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
                    int expresion = Integer.parseInt(modelo.getVista().txtExpresion.getText());
                    int expresion2 = Integer.parseInt(modelo.getVista().txtExpresion2.getText());
                    if (expresion <= 30 || expresion2 <= 30) {
                        BigInteger Fn = factorialBig(Integer.parseInt(modelo.getVista().txtExpresion.getText()));
                        BigInteger Fx = factorialBig(Integer.parseInt(modelo.getVista().txtExpresion2.getText()));
                        int n = Integer.parseInt(modelo.getVista().txtExpresion.getText());
                        int x = Integer.parseInt(modelo.getVista().txtExpresion2.getText());
                        if (n == x) {
                            modelo.getVista().txtObservaciones.setText("La condición SÍ se cumple");
                        } else {
                            modelo.getVista().txtObservaciones.setText("NO se cumple la condición.");
                        }
                        modelo.getVista().txtResultado.setText("n! = " + String.valueOf(Fn) + ",\nx! = " + String.valueOf(Fx));
                    } else {
                        BigInteger n = factorialBig(Integer.parseInt(modelo.getVista().txtExpresion.getText()));
                        BigInteger x = factorialBig(Integer.parseInt(modelo.getVista().txtExpresion2.getText()));
                        if (n == x) {
                            modelo.getVista().txtObservaciones.setText("La condición SÍ se cumple");
                        } else {
                            modelo.getVista().txtObservaciones.setText("NO se cumple la condición.");
                        }
                        modelo.getVista().txtResultado.setText("n! = " + String.valueOf(n) + ",\nx! = " + String.valueOf(x));
                    }
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
                    int n = Integer.parseInt(modelo.getVista().txtExpresion.getText());
                    int x = Integer.parseInt(modelo.getVista().txtExpresion2.getText());
                    BigInteger nB = new BigInteger(modelo.getVista().txtExpresion.getText());
                    BigInteger nF = factorialBig(n);
                    BigInteger prueba = nF.divide(nB);
                    if (n <= 30) {
                        modelo.getVista().txtResultado.setText(String.valueOf(prueba));
                        modelo.getVista().txtObservaciones.setText("Condición cumplida, \nEl factorial de " + (x-1)  + " (" + n + "-1) "
                                + "es " + prueba);
                    } else {
                        modelo.getVista().txtResultado.setText(String.valueOf(prueba));
                        modelo.getVista().txtObservaciones.setText("Condición cumplida, \nEl factorial de " + (x-1) + " (" + n + "-1) "
                                + "es demasiado grande para mostrarlo");
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
                    int x = Integer.parseInt(modelo.getVista().txtExpresion2.getText());
                    int n = Integer.parseInt(modelo.getVista().txtExpresion.getText());
                    BigInteger cX = factorialBig(x);
                    BigInteger cN = factorialBig(n);
                    BigInteger prueba = cN.divide(cX);
                    if (x == (n - 1)) {
                        modelo.getVista().txtResultado.setText(String.valueOf(prueba));
                        modelo.getVista().txtObservaciones.setText("Condición cumplida");
                    } else {
                        modelo.getVista().txtResultado.setText(String.valueOf(prueba));
                        modelo.getVista().txtObservaciones.setText("No se cumplió con la condición");
                    }
                    if (x == (n - 1)) {
                        modelo.getVista().txtResultado.setText(String.valueOf(prueba));
                        modelo.getVista().txtObservaciones.setText("Condición cumplida");
                    } else {
                        modelo.getVista().txtResultado.setText(String.valueOf(prueba));
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
                documento.add(new Paragraph("Descripción del problema : " + VistaAnalisis.descripcion));
                // iniciar contenido pdf

                VistaFactorial factorial = new VistaFactorial();

                switch (opcionSeleccionada) {
                    case 0: // Opción a
                        documento.add(new Paragraph("Has seleccionado: n! = n · (n-1)!"));
                        documento.add(new Paragraph("Sustitución de formula : " + "n = " + modelo.getVista().txtExpresion.getText()));
                        documento.add(new Paragraph("Resultado de la operaación : " + " (n-1)! = " + "(" + modelo.getVista().txtExpresion.getText() + "-1)! = " + modelo.getVista().txtResultado.getText()));
                        break;

                    case 1: // Opción b
                        documento.add(new Paragraph("Has seleccionado: x! = n! → x = n"));
                        documento.add(new Paragraph(" Sustitución formula : n! = " + modelo.getVista().txtExpresion.getText() + "  x! = " + modelo.getVista().txtExpresion2.getText()));
                        documento.add(new Paragraph("Resultado de la operación : x = n    " + modelo.getVista().txtResultado.getText()));
                        break;
                    case 2: // Opción c
                        documento.add(new Paragraph("Has seleccionado: n! / n = (n-1)!"));
                        documento.add(new Paragraph("sustitución formula : n = " + modelo.getVista().txtExpresion.getText()));
                        documento.add(new Paragraph(" Resultado de la operación : (n-1!) = " + "(" + modelo.getVista().txtExpresion.getText() + "-1)! = " + modelo.getVista().txtResultado.getText()));
                        break;
                    case 3: // Opción d
                        documento.add(new Paragraph("Has seleccionado: n! / (n-1)! = n"));
                        documento.add(new Paragraph("sustitucion formula : n = " + modelo.getVista().txtExpresion.getText()));
                        documento.add(new Paragraph(" Resultado de la operación : " + modelo.getVista().txtExpresion.getText() + "! / (" + modelo.getVista().txtExpresion.getText() + "-1) =" + modelo.getVista().txtResultado.getText()));
                        break;
                    case 4: // Opción e
                        documento.add(new Paragraph("Has seleccionado: 0! = 1"));
                        documento.add(new Paragraph(" Sustitución formula : " + modelo.getVista().txtExpresion.getText()));
                        documento.add(new Paragraph("Resultado de la operación : " + modelo.getVista().txtResultado.getText()));
                        break;
                    default:
                        documento.add(new Paragraph("Has seleccionado: n! = n · (n-1)!"));
                        documento.add(new Paragraph("Sustitución de formula : " + "n = " + modelo.getVista().txtExpresion.getText()));
                        documento.add(new Paragraph("Resultado de la operaación : " + " (n-1)! = " + "(" + modelo.getVista().txtExpresion.getText() + "-1)! = " + modelo.getVista().txtResultado.getText()));
                        break;
                }
                documento.add(new Paragraph("Observacion : " + modelo.getVista().txtObservaciones.getText()));
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
    public void keyTyped(KeyEvent e) {

        if (tipoFactorial == 4) {
            Object source = e.getSource();
            if (source == modelo.getVista().txtExpresion) {
                modelo.getVista().txtExpresion2.setText("");
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                } else {
                    e.consume();
                    JTextField textField = (JTextField) source;
                    int caretPosition = textField.getCaretPosition();
                    String currentText = textField.getText();
                    String newText = currentText.substring(0, caretPosition) + c + currentText.substring(caretPosition);
                    textField.setText(newText);
                    try {
                        int asigNum = Integer.parseInt(newText) - 1;
                        modelo.getVista().txtExpresion2.setText(String.valueOf(asigNum));
                    } catch (NumberFormatException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        } else if (tipoFactorial == 3) {
            Object source = e.getSource();
            if (source == modelo.getVista().txtExpresion) {
                modelo.getVista().txtExpresion2.setText("");
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                } else {
                    e.consume();
                    JTextField textField = (JTextField) source;
                    int caretPosition = textField.getCaretPosition();
                    String currentText = textField.getText();
                    String newText = currentText.substring(0, caretPosition) + c + currentText.substring(caretPosition);
                    textField.setText(newText);
                    try {
                        int asigNum = Integer.parseInt(newText);
                        modelo.getVista().txtExpresion2.setText(String.valueOf(asigNum));
                    } catch (NumberFormatException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        } else if (tipoFactorial == 5) {
            Object source = e.getSource();
            if (source == modelo.getVista().txtExpresion) {
                char c = e.getKeyChar();
                if (c != '0') {
                    e.consume();
                }
            }
        } else {
            Object source = e.getSource();
            if (source == modelo.getVista().txtExpresion || source == modelo.getVista().txtExpresion2) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
