/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package civitas;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author LG
 */
public class MazoSorpresas {
    
    private ArrayList<Sorpresa> sorpresas;
    private boolean barajada;
    private int usadas;
    private boolean debug;
    
    private void init()
    {
        sorpresas=new ArrayList<Sorpresa>();
        barajada=false;
        usadas=0;
    }
    
    MazoSorpresas()
    {
        init();
        debug=false;
    }
    
    MazoSorpresas(boolean db)
    {
        debug=db;
        init();
        
        if(db)
        {
            Diario diary=Diario.getInstance();
            diary.ocurreEvento("El modo debug está activado");
        }
    }
    
    void alMazo(Sorpresa s)
    {
        if(!barajada) sorpresas.add(s);
    }
    
    Sorpresa siguiente ()
    {
        Sorpresa carta;
        
        if(!barajada && usadas==sorpresas.size())
        {
            if(!debug)
            {    
                barajar();
                usadas=0;
                barajada=true;
            }
        }
  
        usadas++;
        carta=sorpresas.get(0);
        sorpresas.remove(0);
        sorpresas.add(carta);
        
        return(carta);
    }
    
    
    void barajar()
    {
        Collections.shuffle(sorpresas);
    }
}
