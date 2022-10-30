/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladorCivitas;

import civitas.CivitasJuego;
import civitas.Diario;
import civitas.OperacionJuego;
import vistaTextualCivitas.Vista;
import controladorCivitas.Respuesta;
import civitas.OperacionInmobiliaria;
import civitas.GestionInmobiliaria;

/**
 *
 * @author LG
 */
public class Controlador {
    
    private CivitasJuego juegoModel;
    private Vista vista;
    
    
    public Controlador (CivitasJuego juego, Vista vista)
    {
        this.juegoModel=juego;
        this.vista=vista;
    }
    
    public void juega()
    {
        boolean finJuego=juegoModel.finDelJuego();
        OperacionJuego accion;
        GestionInmobiliaria gestion;
        Respuesta respues;
        OperacionInmobiliaria accion_inm;
        
        while(!finJuego)
        {
            vista.actualiza();
            vista.pausa();
            
            accion=juegoModel.siguientePaso();
            vista.mostrarSiguienteOperacion(accion);
            
            if(accion != OperacionJuego.PASAR_TURNO)
                vista.mostrarEventos();
            
            finJuego=juegoModel.finDelJuego();
            
            if(!finJuego)
            {
                switch(accion)
                {
                    case COMPRAR:
                        respues=vista.comprar();
                        
                        if (respues==Respuesta.SI)
                            juegoModel.comprar();    
                        
                        juegoModel.siguientePasoCompletado(accion);
                        
                        break;                
                    case GESTIONAR:
                        accion_inm=vista.elegirOperacion();
                        
                        if(accion_inm!=OperacionInmobiliaria.TERMINAR)
                        {    
                            gestion=new GestionInmobiliaria(accion_inm,
                                  vista.elegirPropiedad());

                            switch(gestion.getOperacion())
                            {
                                
                                case CONSTRUIR_HOTEL:
                                    juegoModel.construirHotel(gestion.getPropiedad());
                                    break;
                                case CONSTRUIR_CASA:
                                    juegoModel.construirCasa(gestion.getPropiedad());
                                    break;
                            }
                        }
                        else
                        {
                            juegoModel.siguientePasoCompletado(accion);
                        }
                        break;        
                }          
            }
            
        }
        juegoModel.ranking_publico();
        vista.actualiza();
    }
}
