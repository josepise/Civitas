
package civitas;

import java.util.ArrayList;

public class Casilla {
    
    private static float FACTORALQUILERCALLE=1.0f;
    private static float FACTORALQUILERCASA=1.0f;
    private static float FACTORALQUILERHOTEL=4.0f;
    
    private TipoCasilla tipo;
    private MazoSorpresas mazo;
    private Jugador propietario;
    
    private String nombre;
    private float precioCompra, precioEdificar, precioBaseAlquiler;
    private int numCasas, numHoteles;
    
    Casilla(String nomb)
    {
        init();
        tipo=TipoCasilla.DESCANSO;
        nombre=nomb;
    }
    
    Casilla(String unNombre, float unPrecioCompra,
            float unPrecioEdificar, float unPrecioAlquilerBase)
    {  
        init();
        tipo=TipoCasilla.CALLE;
        nombre=unNombre;
        precioCompra=unPrecioCompra;
        precioEdificar=unPrecioEdificar;
        precioBaseAlquiler=unPrecioAlquilerBase;
    }
    
    Casilla(String nomb, MazoSorpresas maz)
    {
        init();
        tipo=TipoCasilla.SORPRESA;
        nombre=nomb;
        mazo=maz;
    }
   
    private void init ()
    {
        tipo=null;
        mazo=null;
        propietario=null;
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
    
    int getNumCasas()
    {
        return(numCasas);
    }
    
    int getNumHoteles()
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
    
    public boolean esEsteElPropietario(Jugador jugad)
    {
        boolean espropietario=false;
        
        if(propietario==jugad)
        {
            espropietario=true;
        }
        
        return (espropietario);
    }
    
    public String toString()
    {
        String enunciado;
        System.out.println(propietario);
        
        if (propietario==null)
        {
            enunciado=nombre+ " Precios:Compra:" + precioCompra +", "
                   + "Edificar:" + precioEdificar + ", Alquiler base:" 
                   + precioBaseAlquiler + ", Casas:" + numCasas + ", Hoteles:"
                   + numHoteles;
        }
        else
        { 
            enunciado="Esta calle tiene propietario con nombre:"
                    +propietario.getNombre();
           
        }
        
        return(enunciado);
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
        System.out.println("Paga Funcion comprar Casilla");
       return (propietario.paga(precioCompra));
    }
    
    void recibeJugador (int actual, ArrayList<Jugador> todos)
    {
       switch (tipo) 
       {
            case CALLE:
                recibeJugador_calle(actual,todos);
                break;
            case SORPRESA:
                recibeJugador_calle(actual,todos);
                break;
            case DESCANSO:
                informe(actual,todos);
                break;
        }
    }
    
    void recibeJugador_calle (int actual, ArrayList<Jugador> todos)
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
    
    void recibeJugador_sorpresa (int actual, ArrayList<Jugador> todos)
    {
        Sorpresa sorpresa= mazo.siguiente();
        
        informe(actual, todos);
        
        sorpresa.aplicarAJugador(actual, todos);
        
    }
    
    
}