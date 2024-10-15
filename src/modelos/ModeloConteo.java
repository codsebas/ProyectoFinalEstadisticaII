/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.util.ArrayList;
import java.util.List;
import vistas.VistaConteo;

/**
 *
 * @author gerso
 */
public class ModeloConteo {
    
    VistaConteo vista;
    List<Double> valores;
    
    
    
    public List <Double> getValores(){
        return valores;
    }

    public ModeloConteo() {
        
        this.valores = new ArrayList<>();
    }
    
    public ModeloConteo(VistaConteo vista){
        this.vista = vista;
        this.valores = new ArrayList<>();
    }

    public VistaConteo getVista() {
        return vista;
    }

    public void setVista(VistaConteo vista) {
        this.vista = vista;
    }

   public void agregarValor(double valor){
       valores.add(valor);
    }
    
   
   public double calcularProducto(){
       double producto = 1.0;
      
       for (Double valor : valores){
           
           System.out.println("Valor :"+valor);
           producto *= valor;
       }
       
       System.out.println("Producto calculado: "+ producto);
       return producto;
   }
    
   
   public void limpiarValores(){
       valores.clear();
   }
    
    
}
