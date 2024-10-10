package logica;

public class Factoriales {
    
    public int factorial(int numero){
        if(numero <= 1){
            return 1;
        } else {
            return numero * factorial(numero - 1);
        }
    }
}
