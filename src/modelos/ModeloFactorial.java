/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;
import vistas.VistaFactorial;

public class ModeloFactorial {
    
    VistaFactorial vista;

    public ModeloFactorial() {
    }
    
    public ModeloFactorial(VistaFactorial vista){
        this.vista = vista;
    }

    public VistaFactorial getVista() {
        return vista;
    }

    public void setVista(VistaFactorial vista) {
        this.vista = vista;
    }
    
    
}
