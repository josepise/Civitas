/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package civitas;
import java.util.ArrayList;

/**
 *
 * @author LG
 */
public abstract class Sorpresa {
    
    String texto;
    
    public Sorpresa(String t)
    {
        texto = t;
    }
    
    abstract void informe(int actual, ArrayList<Jugador> todos);
   
    abstract void  aplicarAJugador(int actual, ArrayList<Jugador> todos);
   
    @Override
    public String toString()
    {
        return texto;
    }
    
    
    
}
