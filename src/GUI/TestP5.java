/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package GUI;

import civitas.CivitasJuego;
import controladorCivitas.Controlador;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Jose
 */
public class TestP5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic her899e
        
        CivitasView vista=new CivitasView();
        CapturaNombres nombres=new CapturaNombres(vista,true);
        ArrayList<String> array=new ArrayList<>();

        array=nombres.getNombres();
        
        CivitasJuego juego=new CivitasJuego(array,false);        
        Controlador controlador=new Controlador(juego,vista);
        
        vista.setCivitasJuego(juego);
        
        controlador.juega();
        
        
    }
    
}
