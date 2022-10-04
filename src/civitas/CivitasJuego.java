/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package civitas;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author carlo
 */
public class CivitasJuego {
    
    private int indiceJugadorActual;
    private ArrayList<Jugador> jugadores;
    private GestorEstados gestor;
    private MazoSorpresas mazo;
    private Tablero tablero;
    
    public CivitasJuego(ArrayList<String> nombres, boolean debug)
    {
        
        jugadores = new ArrayList<Jugador>();
        
        for(int i = 0; i < nombres.size(); i++){
            Jugador j = new Jugador(nombres.get(i));
            jugadores.add(j);
        }
        
        gestor = new GestorEstados();
        
        gestor.estadoInicial();
        
        Dado.getInstance().setDebug(debug);
        
        indiceJugadorActual = Dado.getInstance().quienEmpieza(jugadores.size());
            
        mazo = new MazoSorpresas(debug);
        
        tablero = new Tablero();
        
        inicializaTablero(tablero);
        
        inicializaMazoSorpresas(mazo);
        
        
    }
    
    
    private void inicializaTablero(Tablero m){
        
        Casilla inicial = new Casilla("SALIDA", (i + 1) * 500, (i + 1) * 200, (i + 1) * 250);
        
        m.añadeCasilla(inicial);
        
        ArrayList<Casilla> casillas = new ArrayList<Casilla>();
        
        for(int i = 0; i < 14; i++){
            
            String nombre = i+"";
            Casilla calle = new Casilla(nombre, (i+1)*500, (i+1)*200, (i+1)*250);
            
            casillas.add(calle);
           
        }
        
        for(int i = 1; i <= 4; i++){
            
                Casilla sor = new Casilla(i*15 +"", mazo);
                casillas.add(sor);
        }
        
        Casilla parking = new Casilla("Parking");
        
        casillas.add(parking);
        
        Collections.shuffle(casillas);
        
        for(int i = 1; i <= 19; i++){
            m.añadeCasilla(casillas.get(i));
        }
        
    }
    
    
}
