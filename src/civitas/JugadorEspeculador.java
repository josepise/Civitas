/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package civitas;

/**
 *
 * @author Jose
 */
public class JugadorEspeculador extends Jugador {
    
    private static final int FactorEspeculador=2;
    private static final int CasaMaxEspeculador=CasasMax*FactorEspeculador;
    private static final int HotelesMaxEspeculador=HotelesMax*FactorEspeculador;
    
    protected JugadorEspeculador(Jugador jugador)
    {
        super(jugador);
        actualizaPropiedadesPorConversion(jugador);
    }
    
    
    @Override
    int casasMax()
    {
        return CasaMaxEspeculador;
    }
    
    
    @Override
    int hotelMax()
    {
        return HotelesMaxEspeculador;
    }
    
    void actualizaPropiedadesPorConversion(Jugador jug) 
    {
        for(int i=0; i<jug.getPropiedades().size(); i++)
        {
            getPropiedades().add(jug.getPropiedades().get(i));
            jug.getPropiedades().get(i).actualizaPropietarioPorConversion(this);
        }
        
        jug.getPropiedades().clear();
    }
    
    
    
}
