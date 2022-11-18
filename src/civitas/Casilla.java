
package civitas;

import java.util.ArrayList;

public class Casilla {
    
    private String nombre;
       
    Casilla(String nomb)
    {
        nombre=nomb;
    }
    
    String getNombre()
    {
        return (nombre);
    }
    
    void informe(int iactual, ArrayList<Jugador> todos)
    {
        String evento="El jugador "+ todos.get(iactual) + " ha caido en la casilla"
                      + toString();
        Diario.getInstance().ocurreEvento(evento);
    }
    
    
    
    public String toString()
    {
        String enunciado;
    
        enunciado= "\n \nEsta calle es de tipo Sorpresa \n";
             
        return(enunciado);
    }     
    
    void recibeJugador (int actual, ArrayList<Jugador> todos)
    {
       informe(actual,todos);
    }
}