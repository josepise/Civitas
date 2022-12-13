/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package civitas;

import java.util.ArrayList;

/**
 *
 * @author carlo
 */
public class Tablero {
    
    private ArrayList<Casilla> casillas=new ArrayList<>();
    private boolean porSalida;
    
    Tablero(){
        Casilla Salida = new Casilla( "Salida");
        casillas.add(Salida);
        
        porSalida = false;
    }
    
    private boolean correcto(int numCasilla){
        
        boolean correcto;

        if(numCasilla >= 0 && numCasilla <= casillas.size())
            correcto = true;
        else correcto = false;
        
        return correcto;
        
    }
    
    boolean computarPasoPorSalida(){
        
        boolean aux = porSalida;
        
        porSalida = false;
        
        return aux;
        
        
    }
    
    void añadeCasilla(Casilla casilla){
        
        casillas.add(casilla);
    }
    
    Casilla getCasilla(int numCasilla){
        
        if(correcto(numCasilla))
            return casillas.get(numCasilla);
        else return null;
       
    }
    
    int nuevaPosicion(int actual, int tirada){
        
        int posFinal;
        
        posFinal = (actual+tirada)%casillas.size();
        
        if(posFinal != actual + tirada)
            porSalida = true;
        
         
       return posFinal;
    }
    
    ArrayList<Casilla> getTablero()
    {
       return casillas; 
    }
    
    
    
    
}