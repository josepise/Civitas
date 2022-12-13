/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package civitas;

import java.util.Random;

/**
 *
 * @author LG
 */
public class Dado {
    
    private Random random;
    private int ultimoResultado;
    private boolean debug;
    private static Dado instance=new Dado();
    
    private Dado()
    {
        random=new Random();
        ultimoResultado=0;
        debug=false;
    }
    
    static Dado getInstance()
    {
        return(instance);
    }
    
    public int getUltimoResultado()
    {
        return(ultimoResultado);
    }
    
    public int tirar()
    {
        if(debug)
        {
            ultimoResultado=1;
        }
        else
        {
            ultimoResultado=random.nextInt(5)+1;
        }
        
        return(ultimoResultado);
    }
    
    int quienEmpieza(int n)
    {
        return(random.nextInt(n));
    }
    
    public void setDebug(boolean d)
    {
        debug=d;
    }
    
    public boolean getDebug()
    {
        return debug;
    }
    
   
}
