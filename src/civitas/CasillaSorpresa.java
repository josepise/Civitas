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
public class CasillaSorpresa extends Casilla {
    
    
    private MazoSorpresas mazo;
    
    CasillaSorpresa(String nomb, MazoSorpresas maz)
    {
        super(nomb);
        mazo=maz;
    }
    
    @Override
    void recibeJugador (int actual, ArrayList<Jugador> todos)
    {
        Sorpresa sorpresa= mazo.siguiente();
        
        informe(actual, todos);
        
        sorpresa.aplicarAJugador(actual, todos);
        
    }
}
