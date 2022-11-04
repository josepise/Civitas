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
    private static final int CasasPorHotel = 4;
    private static final int HotelesMax = 4;
    private static final float PasoPorSalida = 1000;
    
    
    private int casillaActual;
    private String nombre;
    private boolean puedeComprar;
    private float saldo;
    private static float SaldoInicial = 7500;
    
    
    private ArrayList<Casilla> propiedades;
    
    
    Jugador(String nom){
        nombre = nom;
        casillaActual = 0;
        puedeComprar = false;
        saldo = SaldoInicial;
        propiedades=new ArrayList<>();
    }
    
    final String getNombre()
    {
        return nombre;
    }
    
    int cantidadCasasHoteles()
    {
        int num_casashoteles=0;
        
        for(int i=0; i< propiedades.size(); i++)
            num_casashoteles+=propiedades.get(i).cantidadCasasHoteles();
        
        return num_casashoteles;
    }
    
    final float getSaldo()
    {
        return saldo;
    }
    
    private boolean existeLaPropiedad(int ip)
    {
        
        return (ip < propiedades.size());
        
    }
    
    boolean puedeComprarCasilla()
    {
        boolean aux = puedeComprar;
        
        puedeComprar = true;
        
        return aux;
    }
    
    boolean getPuedeComprar()
    {
        return puedeComprar;
    }
    
    int getCasillaActual() {
        return casillaActual;
    }
    
    boolean paga(float cantidad)
    {
        boolean aux;
        cantidad=cantidad*(-1);
        aux = modificaSaldo(cantidad);
        
        return aux;
    }
    
    public final ArrayList<Casilla> getPropiedades()
    {
        return propiedades;
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
        Diario.getInstance().ocurreEvento("El saldo de " + nombre + " ha "
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
        boolean ok=false;
        if(precio<=saldo)         
            ok=true;    
                    
        return ok;
        
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
    
    
    boolean construirCasa (int ip)
    {
        boolean result=false;
        boolean existe=existeLaPropiedad(ip);
        
        if(existe)
        {
            Casilla propiedad=propiedades.get(ip);
            boolean puedoEdificar=puedoEdificarCasa(propiedad);
            
            if(puedoEdificar)
            {
                result=propiedad.construirCasa(this);
                
                Diario.getInstance().ocurreEvento("El jugador"+ nombre +
                        " construye una casa en la propiedad " + propiedad.getNombre());
            }
           
        }
        
        return result;
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
        String cadena = "\n Nombre: " + nombre  
                +"\n" + "   Saldo: " + saldo +" \n";
        return cadena;
    }
    
   
    
    public boolean comprar (Casilla titulo)
    {
        boolean result=false;
        float precio;
       
        if(puedeComprarCasilla())
        {
            precio=titulo.getPrecioCompra();
            
            if(puedoGastar(precio))
            {
                
                result=titulo.comprar(this);
                
                propiedades.add(titulo);
                
                Diario.getInstance().ocurreEvento("El jugador "+nombre+
                        " compra la propiedad "+titulo.getNombre());
                
                puedeComprar=false;
            }
            else
            {
                Diario.getInstance().ocurreEvento("El jugador "+nombre+
                        " no tiene saldo para comprar la propiedad "+titulo.getNombre());
            }
        }
        
        return (result);
    }
    
    boolean construirHotel(int ip)
    {
        boolean result=false;
        
        if(existeLaPropiedad(ip))
        {
            Casilla propiedad=propiedades.get(ip);
            
            boolean puedoEdificarHotel=puedoEdificarHotel(propiedad);
            
            if(puedoEdificarHotel)
            {
                result=propiedad.construirHotel(this);
                
                propiedad.derruirCasas(CasasPorHotel,this);
                
                Diario.getInstance().ocurreEvento("El jugador "+ nombre
                + " construye hotel en la propiedad "+ propiedad.getNombre());
            }
        }
        
        return result;
    }

    private boolean puedoEdificarCasa(Casilla propiedad)
    {
        boolean puedo=false;
        float precioEdificar=propiedad.getPrecioEdificar();
            
        if(puedoGastar(precioEdificar) && propiedad.getNumCasas()<CasasMax)
        {
            puedo=true;
        }
        
        return puedo;
    }
    
    private boolean puedoEdificarHotel(Casilla propiedad)
    {
        boolean puedoEdificarHotel=false;
        
        float precio=propiedad.getPrecioEdificar();
        
        if(puedoGastar(precio)&& propiedad.getNumHoteles()<HotelesMax
            && propiedad.getNumCasas()>=CasasPorHotel)
        {
            puedoEdificarHotel=true;
        }
        
        return puedoEdificarHotel;
    }
    
    
    
   
}
