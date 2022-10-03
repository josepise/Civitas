/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package civitas;
import civitas.Casilla.TipoCasilla;
import java.util.ArrayList;
/**
 *
 * @author LG
 */
public class Tablero {
    
    private ArrayList<Casilla> casillas;
    private boolean porSalida;
    
    Tablero()
    {
       casillas=new ArrayList(20);
       Casilla nueva=new Casilla(TipoCasilla.CALLE,"Salida",
               0,0,0);
       casillas.add(nueva);
       porSalida=false;
    }
    
    private boolean correcto(int numCasilla)
    {
        boolean var=true;
        
        if(casillas.size()>=numCasilla) var=false;
        
        return (var);
    }
    
    boolean computarPasoPorSalida()
    {
        boolean var=porSalida;
       
        porSalida=false;
        
        return (var);
    }
    
    void añadeCasilla(Casilla casilla)
    {
        casillas.add(casilla);
        
    }
    
    Casilla getCasilla(int numCasilla)
    {
        Casilla var=null;
        
        if(computarPasoPorSalida()) var=casillas.get(numCasilla);
        
        return (var);
        
    }
    
    int nuevaPosicion(int actual, int tirada)
    {
        int var=actual+tirada;
        
        if (var>=20)
        {
            var=var-20;
            porSalida=true;
        }
        
        return(var);
    }
    
    
}
