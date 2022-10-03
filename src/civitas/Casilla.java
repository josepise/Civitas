
package civitas;

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
    
    Casilla(String nombre)
    {
        this(TipoCasilla.DESCANSO,nombre,0,0,
                0);
    }
    
    Casilla(TipoCasilla unTipo, String unNombre, float unPrecioCompra,
            float unPrecioEdificar, float unPrecioAlquilerBase)
    {
        tipo=unTipo;
        nombre=unNombre;
        precioCompra=unPrecioCompra;
        precioEdificar=unPrecioEdificar;
        precioBaseAlquiler=unPrecioAlquilerBase;
    }
    
    Casilla(String nombre, MazoSorpresas maz)
    {
        this(TipoCasilla.SORPRESA, nombre, 0,0,
                0);
        mazo=maz;
    }
   
    String getNombre()
    {
        return (nombre);
    }
    
    float getPrecioCompra()
    {
        return (precioCompra);
    }
    
    public float getPrecioEdificar()
    {
        return(precioEdificar);
    }
    
    public float getPrecioAlquilerCompleto()
    {
        float alquiler=precioBaseAlquiler*(FACTORALQUILERCALLE+numCasas
                *FACTORALQUILERCASA+numHoteles*FACTORALQUILERHOTEL);
        return(alquiler);
    }
    
    public boolean construirCasa()
    {
        numCasas++;
        return(true);
    }
    
    public boolean construirHotel()
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