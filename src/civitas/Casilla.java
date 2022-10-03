/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package civitas;

public class Casilla {
    
    
    private TipoCasilla tipo;
    private String nombre;
    private float precioCompra, precioEdificar, precioBaseAlquiler;
    private int numCasas, numHoteles;
    
    Casilla(TipoCasilla unTipo, String unNombre, float unPrecioCompra,
            float unPrecioEdificar, float unPrecioAlquilerBase)
    {
        tipo=unTipo;
        nombre=unNombre;
        precioCompra=unPrecioCompra;
        precioEdificar=unPrecioEdificar;
        precioBaseAlquiler=unPrecioAlquilerBase;
    }
    
    public String getNombre()
    {
        return (nombre);
    }
    
    public float getPrecioCompra()
    {
        return (precioCompra);
    }
    
    public float getPrecioEdificar()
    {
        return(precioEdificar);
    }
    
    public float getPrecioAlquilerCompleto()
    {
        float alquiler=precioBaseAlquiler*(1+numCasas+numHoteles*4);
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