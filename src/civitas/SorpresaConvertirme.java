/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package civitas;

import java.util.ArrayList;

/**
 *
 * @author Jose
 */
public class SorpresaConvertirme extends Sorpresa {
    
    SorpresaConvertirme(String t)
    {
        super(t);
    }
    
    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos)
    {
        Jugador rajoy=todos.get(actual).convertir();
        todos.add(actual, rajoy);
    }
    
    @Override
    void informe(int actual, ArrayList<Jugador> todos)
    {
         
       String cadena = "Se aplico la sorpresa Convertirse a Especulador a: " + todos.get(actual)+  "\n" + 
                        " ahora es un Especulador. " ;
       Diario.getInstance().ocurreEvento(cadena);
    }
    
}
