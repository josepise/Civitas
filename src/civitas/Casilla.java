
package civitas;

import java.util.ArrayList;

public class Casilla {
    
    private static float FACTORALQUILERCALLE=1.0f;
    private static float FACTORALQUILERCASA=1.0f;
    private static float FACTORALQUILERHOTEL=4.0f;
    
    private TipoCasilla tipo;
    private MazoSorpresas mazo;
    private Jugador jugador;
    
    private String nombre;
    private float precioCompra, precioEdificar, precioBaseAlquiler;
    private int numCasas, numHoteles;
    
    Casilla(String nomb)
    {
        init();
        nombre=nomb;
    }
    
    Casilla(TipoCasilla unTipo, String unNombre, float unPrecioCompra,
            float unPrecioEdificar, float unPrecioAlquilerBase)
    {  
        init();
        tipo=unTipo;
        nombre=unNombre;
        precioCompra=unPrecioCompra;
        precioEdificar=unPrecioEdificar;
        precioBaseAlquiler=unPrecioAlquilerBase;
    }
    
    Casilla(String nomb, MazoSorpresas maz)
    {
        init();
        nombre=nomb;
        mazo=maz;
    }
   
    void init ()
    {
        tipo=null;
        mazo=null;
        jugador=null;
        nombre="";
        precioCompra=0;
        precioEdificar=0;
        precioBaseAlquiler=0;
        numCasas=0;
        numHoteles=0;
    }
    
    String getNombre()
    {
        return (nombre);
    }
    
    float getPrecioCompra()
    {
        return (precioCompra);
    }
    
    float getPrecioEdificar()
    {
        return(precioEdificar);
    }
    
    int cantidadCasasHoteles()
    {
        return (numCasas+numHoteles);
    }
    
    float getPrecioAlquilerCompleto()
    {
        float alquiler=precioBaseAlquiler*(FACTORALQUILERCALLE+numCasas
                *FACTORALQUILERCASA+numHoteles*FACTORALQUILERHOTEL);
        return(alquiler);
    }
    
    void informe(int iactual, ArrayList<Jugador> todos)
    {
        String evento="El jugador "+ todos.get(iactual) + "ha caido en la casilla"
                      + toString();
        Diario.getInstance().ocurreEvento(evento);
    }
    
    
    boolean construirCasa(Jugador jugador)
    {
        numCasas++;
        return(true);
    }
    
    boolean construirHotel()
    {
        numHoteles++;
        return(true);
    }
    
    public boolean esEsteElPropietario(Jugador jugad)
    {
        boolean espropietario=false;
        
        if(jugador==jugad)
        {
            espropietario=true;
        }
        
        return (espropietario);
    }
    
    public String toString()
    {
        String enunciado;
        
        if (jugador!=null)
        {
            enunciado=nombre+ " Precios:Compra:" + precioCompra +", "
                   + "Edificar:" + precioEdificar + ", Alquiler base:" 
                   + precioBaseAlquiler + ", Casas:" + numCasas + ", Hoteles:"
                   + numHoteles;
        }
        else
        { 
            enunciado="Esta calle tiene propietario con nombre:"
                    +jugador.getNombre();
           
        }
        
        return(enunciado);
    }    
    
    public void tramitarAlquiler(Jugador jugad)
    {

        if(!esEsteElPropietario(jugad))
        {
            jugad.pagaAlquiler(getPrecioAlquilerCompleto());
            jugador.recibe(getPrecioAlquilerCompleto());
        }
    }

    
    
    
}