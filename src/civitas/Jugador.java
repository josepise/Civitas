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
public class Jugador implements Comparable<Jugador> {
    
    private static final int CasasMax = 4;
    private static final int PorHotel = 4;
    private static final int HotelesMax = 4;
    private static final float PasoPorSalida = 1000;
    
    
    private int casillaActual;
    private String nombre;
    private boolean puedeComprar;
    private float saldo;
    private final float SaldoInicial = 7500;
    
    
    private ArrayList<Casilla> propiedades;
    
    
    Jugador(String nom){
        nombre = nom;
        casillaActual = 0;
        puedeComprar = false;
        saldo = 0;
    }
    
    final String getNombre()
    {
        return nombre;
    }
            
            
    
    final float getSaldo()
    {
        return saldo;
    }
    
    private boolean existeLaPropiedad(int ip)
    {
        
        return (propiedades.size() <= ip);
        
    }
    
    boolean puedeComprarCasilla()
    {
        boolean aux = puedeComprar;
        
        puedeComprar = true;
        
        return aux;
    }
    
    boolean paga(float cantidad)
    {
        boolean aux;
        
        aux = modificaSaldo(cantidad*(-1));
        
        return aux;
    }
    
    boolean pagaAlquiler(float cantidad)
    {
        return paga(cantidad);
    }
    
    boolean recibe(float cantidad)
    {
        return modificaSaldo(cantidad);
    }
    
    boolean modificaSaldo(float cantidad)
    {
        saldo = saldo + cantidad;
        Diario.getInstance().ocurreEvento("El saldo de " + nombre + "ha "
                + " cambiado a " + saldo);
        return true;
    }
    
    boolean moverACasilla(int c)
    {
        casillaActual = c;
        puedeComprar = false;
        Diario.getInstance().ocurreEvento("El jugador " + nombre + " esta ahora"
                + " en la casilla " + c);
        return true;
    }
    
    boolean puedoGastar(float precio)
    {
        
        return saldo <= precio;
        
    }
    
    boolean tieneAlgoQueGestionar()
    {
        return !propiedades.isEmpty();
    }
    
    boolean pasaPorSalida()
    {
        recibe(PasoPorSalida);
        Diario.getInstance().ocurreEvento("El jugador " + nombre + " pasa por"
                + " la salida");
        
        return true;
    }
    
    @Override
    public int compareTo(Jugador otro)
    {
        
        int ret;
        
        if(saldo < otro.getSaldo())
            ret = -1;
        else if(saldo > otro.getSaldo())
            ret = 1;
        else ret = 0;
        
        return ret;
            
    }
    
    @Override
    public String toString()
    {
        String cadena = "Nombre: " + nombre + " Saldo: " + saldo;
        return cadena;
    }
    
    
    
    
    
    
    
    
    
    
}
