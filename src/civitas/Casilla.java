
package civitas;

import java.util.ArrayList;

public class Casilla {
    
    private static final float FACTORALQUILERCALLE=1.0f;
    private static final float FACTORALQUILERCASA=1.0f;
    private static final float FACTORALQUILERHOTEL=4.0f;
    
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
    
    public String toString()
    {
        String enunciado=nombre+ " Precios:Compra:" + precioCompra +", "
               + "Edificar:" + precioEdificar + ", Alquiler base:" 
               + precioBaseAlquiler + ", Casas:" + numCasas + ", Hoteles:"
               + numHoteles;
        
        return(enunciado);
    }
    
    
}