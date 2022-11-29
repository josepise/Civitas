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
public class SorpresaPagarCobrar extends Sorpresa {
    
    int valor;
    
    public SorpresaPagarCobrar(String t, int n)
    {
        super(t);
        valor=n;
    }
    
    @Override
    void informe(int actual, ArrayList<Jugador> todos)
    {
       
       String cadena = "Se aplico la sorpresa PagarCobrar  a " + todos.get(actual)+  "\n" + 
                        "de pagar/cobrar " + valor;
       Diario.getInstance().ocurreEvento(cadena);
        
    }
    
    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos)
    {
        informe(actual, todos);
        todos.get(actual).modificaSaldo(valor);
    }
    
    
}
