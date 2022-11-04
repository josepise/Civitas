/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package vistaTextualCivitas;

import civitas.CivitasJuego;
import controladorCivitas.Controlador;
import java.util.ArrayList;


/**
 *
 * @author LG
 */
public class JuegoTexto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ArrayList<String> jugadores=new ArrayList<>();
        jugadores.add("Benito");
        //jugadores.add("Potito");
        //jugadores.add("OHMR");
        //jugadores.add("nawie");
        
       
        CivitasJuego juego=new CivitasJuego(jugadores,true);
        VistaTextual vista=new VistaTextual(juego);
        
        Controlador cont=new Controlador(juego,vista);
        
        cont.juega();
        
       
    }
    
}
