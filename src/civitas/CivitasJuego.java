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
    private EstadoJuego estado;
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
        
        estado = gestor.estadoIncial();
        
        Dado.getInstance().setDebug(debug);
        
        indiceJugadorActual = Dado.getInstance().quienEmpieza(jugadores.size());
            
        mazo = new MazoSorpresas(debug);
        
        tablero = new Tablero();
        
        inicializaTablero(mazo);
        
        inicializaMazoSorpresas();
        
        
    }
    
    
    private void inicializaTablero(MazoSorpresas m)
    {
        
        Casilla inicial = new Casilla("SALIDA");
        
        tablero.añadeCasilla(inicial);
        
        ArrayList<Casilla> casillas = new ArrayList<>();
        
        for(int i = 0; i < 14; i++){
            
            String nombre = i+"";
            Casilla calle = new Casilla(nombre, (i+1)*500, (i+1)*200, (i+1)*250);
            
            casillas.add(calle);
           
        }
        
        for(int i = 1; i <= 4; i++){
            
                Casilla sor = new Casilla(i*15 +"", m);
                casillas.add(sor);
        }
        
        Casilla parking = new Casilla("Parking");
        
        casillas.add(parking);
        
        Collections.shuffle(casillas);
        
        for(int i = 1; i <= 19; i++){
            tablero.añadeCasilla(casillas.get(i));
        }
        
    }
    
    
    private void inicializaMazoSorpresas()
    {
        
        for(int i = 1; i <= 5; i++){
            Sorpresa sor = new Sorpresa(TipoSorpresa.PAGARPORCOBRAR, 
                                        "Pagar por cobrar", i*250);
            
            mazo.alMazo(sor);
            
        }
        
        for(int i = 1; i <= 5; i++){
            Sorpresa sor = new Sorpresa(TipoSorpresa.PORCASAHOTEL, 
                                        "Pagar por casa y hotel", i*250);
            
            mazo.alMazo(sor);
            
        }
        
        
        mazo.barajar();
       
    }
    
    public Jugador getJugadorActual()
    {
        return jugadores.get(indiceJugadorActual);
    }
    
    private void pasarTurno()
    {
        if(indiceJugadorActual != jugadores.size())
            indiceJugadorActual++;
        else indiceJugadorActual = 1;
        
    }
    
    
    public void siguientePasoCompletado(OperacionJuego operacion)
    {
        
        estado = gestor.siguienteEstado(jugadores.get(indiceJugadorActual),
                        estado, operacion);
        
    }
    
    public boolean construirCasa(int ip){
        
        return jugadores.get(indiceJugadorActual).construirCasa(ip);
    }
    
    public boolean construirHotel(int ip){
        return jugadores.get(indiceJugadorActual).construirHotel(ip);

    }
    
    boolean finalDelJuego(){
        boolean fin = false;
        
        for(int i = 1; i<= jugadores.size(); i++){
            if(jugadores.get(i).getSaldo() < 0)
                fin = true;
        }
        
        return true;
    }
    
    private ArrayList<Jugador> ranking(){
        
        ArrayList<Jugador> ranking = new ArrayList<>();
        ranking = jugadores;
        
        ranking.sort((o1, o2) -> o1.compareTo(o2));
        
        return ranking;
    }
    
    private void contabilizarPasosPorSalida(){
        
        if(tablero.computarPasoPorSalida()){
            
            jugadores.get(indiceJugadorActual).pasaPorSalida();
            
        }
    }
    
    
    
    
}
