/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.util.List;

/**
 *
 * @author gerso
 */
public class Conteo {
    
    public static double calcularProducto(List<Double> valores) {
        double producto = 1.0; 

        
        for (Double valor : valores) {
            producto *= valor;
        }

        return producto; 
}
    
    
}

