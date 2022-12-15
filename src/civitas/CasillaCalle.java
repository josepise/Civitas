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
public class CasillaCalle extends Casilla{
    
    private static float FACTORALQUILERCALLE=1.0f;
    private static float FACTORALQUILERCASA=1.0f;
    private static float FACTORALQUILERHOTEL=4.0f;
    
    private Jugador propietario;
    
    private float precioCompra, precioEdificar, precioBaseAlquiler;
    private int numCasas, numHoteles;
    
    CasillaCalle()
    {
       super("");
       numCasas = 0;
    }
    
    CasillaCalle(String unNombre, float unPrecioCompra,
            float unPrecioEdificar, float unPrecioAlquilerBase)
    {  
        super(unNombre);
        precioCompra=unPrecioCompra;
        precioEdificar=unPrecioEdificar;
        precioBaseAlquiler=unPrecioAlquilerBase;
        numCasas = 0;
        numHoteles = 0;
        propietario = null;
    }
    
    
    
    
    public float getPrecioCompra()
    {
        return (precioCompra);
    }
    
    public float getPrecioEdificar()
    {
        return(precioEdificar);
    }
    
    public Jugador getPropietario()
    {
        return propietario;
    }
    
    public int getNumCasas()
    {
        return(numCasas);
    }
    
    public int getNumHoteles()
    {
        return(numHoteles);
    }
    
    int cantidadCasasHoteles()
    {
        return (numCasas+numHoteles);
    }
    
    boolean derruirCasas(int n,Jugador jugad)
    {
        boolean completo=false;
        
        if(esEsteElPropietario(jugad) && n<=numCasas)
        {
            numCasas=numCasas-n;
            completo=true;
        }
        
        return(completo);
    }
    
    
    public float getPrecioAlquilerCompleto()
    {
        float alquiler=precioBaseAlquiler*(FACTORALQUILERCALLE+numCasas
                *FACTORALQUILERCASA+numHoteles*FACTORALQUILERHOTEL);
        return(alquiler);
    }
    
    
      boolean construirCasa(Jugador jugador)
    {
        boolean construido;
        
        construido=jugador.paga(precioEdificar);
        numCasas++;
        
        return construido;
    }
    
    boolean construirHotel(Jugador jugador)
    {
        jugador.paga(precioEdificar);
        numHoteles++;
        return true;
    }
    
    
    @Override
    public String toString()
    {
        
        String enunciado;
        
        if (propietario==null)
        {
            
                enunciado= "\n" + "\n"
                        +"Nombre:" + getNombre() + "\n" +"Precios: " + "\n"
                        +"  Compra:" + precioCompra + "\n"
                        +"  Edificar:" + precioEdificar + "\n"
                        +"  Alquiler base:" + precioBaseAlquiler + "\n"
                        +"  Casas:" + numCasas + "\n"
                        +"  Hoteles:" + numHoteles +"\n";
        }
        else
        { 
            enunciado="La calle " + getNombre() + " tiene el propietario " 
                    +propietario.getNombre() + "\n" ;
           
        }
        
        return enunciado;
    }
    
    public void tramitarAlquiler(Jugador jugad)
    {

        if(!esEsteElPropietario(jugad))
        {
            jugad.pagaAlquiler(getPrecioAlquilerCompleto());
            propietario.recibe(getPrecioAlquilerCompleto());
        }
    }
    
    public boolean tienePropietario()
    {
        return propietario!=null;
    }
    
    
    boolean comprar(Jugador jugador)
    {
       propietario=jugador;
       return (propietario.paga(precioCompra));
    }
    
    @Override
    public void recibeJugador (int actual, ArrayList<Jugador> todos)
    {
        informe(actual,todos);
        
        Jugador jugador=todos.get(actual);
        
        if(!tienePropietario())
        {
            jugador.puedeComprarCasilla();
        }
        else
        {
            tramitarAlquiler(jugador);
        }
    }
    
    
    public boolean esEsteElPropietario(Jugador jugad)
    {
        boolean espropietario=false;
        
        if(propietario==jugad)
        {
            espropietario=true;
        }
        
        return (espropietario);
    }
    
    public void actualizaPropietarioPorConversion(JugadorEspeculador jugad)
    {
        propietario=jugad;
    }
    
    
}

