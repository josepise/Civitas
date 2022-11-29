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
public class SorpresaPagarCasaHotel extends Sorpresa{
    
    int valor;
    
    public SorpresaPagarCasaHotel(String t, int n)
    {
        super(t);
        valor=n;
    }
    
    @Override
    void informe(int actual, ArrayList<Jugador> todos)
    {
       float cantidad_pagar=valor*todos.get(actual).cantidadCasasHoteles();
         
       String cadena = "Se aplico la sopresa PagarPorCasaHotel a " + todos.get(actual)+  "\n" + 
                        "de pagar/cobrar " + cantidad_pagar ;
       Diario.getInstance().ocurreEvento(cadena);
    }
    
    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos)
    {
        float cantidad_pagar=valor*todos.get(actual).cantidadCasasHoteles();
        informe(actual, todos);
        todos.get(actual).modificaSaldo(cantidad_pagar);
    }
    

}
