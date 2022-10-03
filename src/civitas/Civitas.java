/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package civitas;
import civitas.Casilla.TipoCasilla;
/**
 *
 * @author LG
 */
public class Civitas {
    
    /**
     * @param args the command line arguments
     */
    
   
    public static void main(String[] args) {
        // TODO code application logic here
        
        int tiradas[]={0,0,0,0};
        
        Dado dadito=Dado.getInstance();
        
        dadito.setDebug(false);
        
        for(int i=0; i<100; i++)
        {
            int numero=dadito.quienEmpieza(4);
        
            if (numero==1) tiradas[0]++;
            if (numero==2) tiradas[1]++;
            if (numero==3) tiradas[2]++;
            if (numero==4) tiradas[3]++;
                
        }
        
        for(int i =0; i<4; i++)
        {
            System.out.println(tiradas[i]);
        }
        
        for(int i =0; i<4; i++)
        {
            System.out.println(dadito.tirar());
            System.out.println("pro");
            System.out.println("Cebollas2");
            System.out.println("po");
            System.out.println("jasdfj");
            System.out.println("snodqw");
        }
    }
    
    
}
