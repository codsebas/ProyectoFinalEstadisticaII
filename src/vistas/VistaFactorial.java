/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vistas;
import javax.swing.ImageIcon;
import modelos.ModeloFactorial;
import logica.Factoriales;

/**
 *
 * @author javie
 */
public class VistaFactorial extends javax.swing.JFrame {

    /**
     * Creates new form VistaFactorial
     */
    public VistaFactorial() {
        initComponents();
        ModeloFactorial modelo = new ModeloFactorial(this);
        Factoriales factoriales = new Factoriales(modelo);
        setFactoriales(factoriales);
        ImageIcon help_icon = new ImageIcon("../iconos/help_icon.png");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        wdwMensaje = new javax.swing.JDialog();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        panelFactorialSimple = new javax.swing.JPanel();
        txtTitulo = new javax.swing.JLabel();
        txtExpresion = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtResultado = new javax.swing.JTextArea();
        btnAyuda = new javax.swing.JButton();
        btnCalcular = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        txtExpresion2 = new javax.swing.JTextField();
        txtDivisor = new javax.swing.JLabel();
        txtDividendo = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JButton();
        btnCambiar = new javax.swing.JButton();
        btnGenerarPDF = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtObservaciones = new javax.swing.JTextArea();

        wdwMensaje.setTitle("Ayuda");
        wdwMensaje.setLocation(new java.awt.Point(0, 0));
        wdwMensaje.setSize(new java.awt.Dimension(629, 330));

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("Ingresa un número entero positivo:\nAsegúrate de que el número que ingresas sea un entero no negativo. El factorial solo se puede calcular para números enteros. Por ejemplo:\n\nCorrecto: 0, 1, 5, 10\nIncorrecto: -1, 3.5, texto, 5.5\nHaz clic en el botón \"Calcular\":\nDespués de ingresar el número, presiona el botón \"Calcular\" para obtener el resultado.\n\nVisualiza el resultado:\nEl resultado del cálculo se mostrará en la pantalla. Si el número es correcto, verás el factorial correspondiente. Si el número es inválido, aparecerá un mensaje de error indicando que solo se permiten números enteros no negativos.");
        jScrollPane1.setViewportView(jTextArea1);

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jLabel2.setText("Ayuda");

        javax.swing.GroupLayout wdwMensajeLayout = new javax.swing.GroupLayout(wdwMensaje.getContentPane());
        wdwMensaje.getContentPane().setLayout(wdwMensajeLayout);
        wdwMensajeLayout.setHorizontalGroup(
            wdwMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wdwMensajeLayout.createSequentialGroup()
                .addGap(279, 279, 279)
                .addComponent(jLabel2)
                .addContainerGap(281, Short.MAX_VALUE))
            .addGroup(wdwMensajeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        wdwMensajeLayout.setVerticalGroup(
            wdwMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, wdwMensajeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelFactorialSimple.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtTitulo.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 36)); // NOI18N
        txtTitulo.setText("Factoriales simples");

        txtExpresion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtExpresionFocusGained(evt);
            }
        });

        txtResultado.setEditable(false);
        txtResultado.setColumns(20);
        txtResultado.setRows(5);
        jScrollPane2.setViewportView(txtResultado);

        btnAyuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/help_question_32.png"))); // NOI18N
        btnAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAyudaActionPerformed(evt);
            }
        });

        btnCalcular.setText("calcular");

        btnLimpiar.setText("Limpiar");

        txtDivisor.setText("Divisor:");

        txtDividendo.setText("Dividendo");

        btnRegresar.setText("regresar");

        btnCambiar.setText("cambiar resolución");

        btnGenerarPDF.setText("Generar reporte");

        txtObservaciones.setEditable(false);
        txtObservaciones.setColumns(20);
        txtObservaciones.setRows(5);
        jScrollPane3.setViewportView(txtObservaciones);

        javax.swing.GroupLayout panelFactorialSimpleLayout = new javax.swing.GroupLayout(panelFactorialSimple);
        panelFactorialSimple.setLayout(panelFactorialSimpleLayout);
        panelFactorialSimpleLayout.setHorizontalGroup(
            panelFactorialSimpleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFactorialSimpleLayout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addGroup(panelFactorialSimpleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFactorialSimpleLayout.createSequentialGroup()
                        .addComponent(btnAyuda)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFactorialSimpleLayout.createSequentialGroup()
                        .addComponent(txtTitulo)
                        .addGap(175, 175, 175))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFactorialSimpleLayout.createSequentialGroup()
                        .addGroup(panelFactorialSimpleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelFactorialSimpleLayout.createSequentialGroup()
                                .addComponent(btnCambiar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRegresar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCalcular))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelFactorialSimpleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFactorialSimpleLayout.createSequentialGroup()
                                    .addComponent(txtDividendo)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtExpresion, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFactorialSimpleLayout.createSequentialGroup()
                                    .addComponent(txtDivisor)
                                    .addGap(47, 47, 47)
                                    .addComponent(txtExpresion2, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(panelFactorialSimpleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelFactorialSimpleLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLimpiar))
                            .addGroup(panelFactorialSimpleLayout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
            .addGroup(panelFactorialSimpleLayout.createSequentialGroup()
                .addGap(244, 244, 244)
                .addComponent(btnGenerarPDF)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelFactorialSimpleLayout.setVerticalGroup(
            panelFactorialSimpleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFactorialSimpleLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(btnAyuda)
                .addGap(12, 12, 12)
                .addComponent(txtTitulo)
                .addGap(18, 18, 18)
                .addGroup(panelFactorialSimpleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelFactorialSimpleLayout.createSequentialGroup()
                        .addGroup(panelFactorialSimpleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtExpresion, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDividendo))
                        .addGap(18, 18, 18)
                        .addGroup(panelFactorialSimpleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtExpresion2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDivisor))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(panelFactorialSimpleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCalcular)
                    .addComponent(btnRegresar)
                    .addComponent(btnLimpiar)
                    .addComponent(btnCambiar))
                .addGap(18, 18, 18)
                .addComponent(btnGenerarPDF)
                .addGap(45, 45, 45))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelFactorialSimple, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelFactorialSimple, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtExpresionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtExpresionFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtExpresionFocusGained

    private void btnAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAyudaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAyudaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VistaFactorial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaFactorial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaFactorial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaFactorial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaFactorial().setVisible(true);
            }
        });
    }
    
    public void setFactoriales(Factoriales factorial){
        btnCalcular.addActionListener(factorial);
        btnAyuda.addActionListener(factorial);
        btnLimpiar.addActionListener(factorial);
        btnCambiar.addActionListener(factorial);
        btnRegresar.addActionListener(factorial);
        btnGenerarPDF.addActionListener(factorial);
        this.addWindowListener(factorial);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAyuda;
    public javax.swing.JButton btnCalcular;
    public javax.swing.JButton btnCambiar;
    public javax.swing.JButton btnGenerarPDF;
    public javax.swing.JButton btnLimpiar;
    public javax.swing.JButton btnRegresar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    public javax.swing.JPanel panelFactorialSimple;
    public javax.swing.JLabel txtDividendo;
    public javax.swing.JLabel txtDivisor;
    public javax.swing.JTextField txtExpresion;
    public javax.swing.JTextField txtExpresion2;
    public javax.swing.JTextArea txtObservaciones;
    public javax.swing.JTextArea txtResultado;
    public javax.swing.JLabel txtTitulo;
    public javax.swing.JDialog wdwMensaje;
    // End of variables declaration//GEN-END:variables
}
