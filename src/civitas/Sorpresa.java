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
public class Sorpresa {
    
    private String texto;
    private int valor;
    private TipoSorpresa tipo;
    
    
    
    public Sorpresa(TipoSorpresa tip, String t, int n)
    {
        
        texto = t;
        valor = n;
        tipo = tip;
        
    }

    private void informe(int actual, ArrayList<Jugador> todos)
    {
       float cantidad_pagar=valor;
       
       if(tipo==TipoSorpresa.PORCASAHOTEL)
           cantidad_pagar=valor*todos.get(actual).cantidadCasasHoteles();
       
       String cadena = "Se aplico la sopresa "+ tipo +" a " + todos.get(actual)+  "\n" + 
                        "de pagar/cobrar " + cantidad_pagar ;
       Diario.getInstance().ocurreEvento(cadena);
        
    }
    
    void aplicarAJugador(int actual, ArrayList<Jugador> todos)
    {
        
        if(tipo == TipoSorpresa.PAGARPORCOBRAR)
            aplicarAJugador_pagarCobrar(actual, todos);
        else aplicarAJugador_porCasaHotel(actual, todos);
        
    }
    
    private void aplicarAJugador_pagarCobrar(int actual, ArrayList<Jugador> todos)
    {
        informe(actual, todos);
        todos.get(actual).modificaSaldo(valor);
    }
    
    private void aplicarAJugador_porCasaHotel(int actual, ArrayList<Jugador> todos)
    {
        float cantidad_pagar=valor*todos.get(actual).cantidadCasasHoteles();
        informe(actual, todos);
        todos.get(actual).modificaSaldo(cantidad_pagar);
    }
    
    public String toString()
    {
        return texto;
    }
    
    
    
}
