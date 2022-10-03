/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package civitas;

public class Civitas {

   
    public static void main(String[] args) {
    /*    
       for(int i = 0; i < 100; i++){
           
          System.out.println(Dado.getInstance().quienEmpieza(4));
           
       }
     */  
       Dado.getInstance().setDebug(true);
       
       for(int i = 0; i < 10; i++)
           System.out.println(Dado.getInstance().tirar());
        
       Dado.getInstance().setDebug(false);
       
       for(int i = 0; i < 10; i++)
           System.out.println(Dado.getInstance().tirar());
       
        System.out.println("El dado esta en " + 
                Dado.getInstance().getUltimoResultado());
        
        
        System.out.println("Tipos enumerados: \n"
                + TipoCasilla.CALLE + "\n"      
                + TipoSorpresa.PAGARPORCOBRAR);
        
        
        Tablero tab = new Tablero();
        
        Casilla cas = new Casilla(TipoCasilla.CALLE, "calle1", 0, 0, 0);
        Casilla cas2 = new Casilla(TipoCasilla.CALLE, "calle2", 500, 0, 0);
        Casilla cas3 = new Casilla(TipoCasilla.CALLE, "calle3", 1000, 0, 0);
        
        tab.añadeCasilla(cas);
        tab.añadeCasilla(cas2);
        tab.añadeCasilla(cas3);
        
        System.out.println(tab.getCasilla(1).toString());
        System.out.println(tab.getCasilla(2).toString());
        System.out.println(tab.getCasilla(3).toString());
        
        Casilla masCara, masBarata;
        
        if(cas.getPrecioCompra() > cas2.getPrecioCompra()){
            masCara = cas;
            
            masBarata = cas2;
            
            if(cas3.getPrecioCompra() > cas.getPrecioCompra())
                masCara = cas3;
            else if (cas3.getPrecioCompra() < cas2.getPrecioCompra())
                masBarata = cas3;
            
        }else{
            
            masCara = cas2;
            
            masBarata = cas;
            
            if(cas3.getPrecioCompra() > cas2.getPrecioCompra())
                masCara = cas3;
            else if (cas3.getPrecioCompra() < cas.getPrecioCompra())
                masBarata = cas3;
            
        }
        
        float precioMedio = (cas.getPrecioCompra() + 
                            cas2.getPrecioCompra() +
                            cas3.getPrecioCompra())/3;
        
        System.out.println("La casilla mas cara es " + masCara + 
                            " y las calles tienen un precio medio de " + 
                            precioMedio);
        
        Diario.getInstance().ocurreEvento("Un jugador ha ganado la partida");
        
        for(int i = 0; i < 3; i++){
            System.out.println("La posicion final es: " + tab.nuevaPosicion(i, Dado.getInstance().tirar()));
        }
    }
    
}
