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
    private EstadosJuego estado;
    private MazoSorpresas mazo;
    private Tablero tablero;
    
    //Para crear las  calles
    private String nombres_calles[]={"Ruiseñor","Colibri","Pelicano","Pingüino",
                                     "Castor","Lemur","Koala","Ballena",
                                     "Delfin","Pollito","Pulpo","Serpiente",
                                     "Hamster", "Hurón"};
    
    public CivitasJuego(ArrayList<String> nombres, boolean debug)
    {
        jugadores = new ArrayList<>();
        
        for(int i = 0; i < nombres.size(); i++){
            Jugador j = new Jugador(nombres.get(i));
            jugadores.add(j);
        }
               
        gestor = new GestorEstados();
        
        estado = gestor.estadoInicial();
        
        Dado.getInstance().setDebug(debug);
        
        indiceJugadorActual = Dado.getInstance().quienEmpieza(jugadores.size());
            
        mazo = new MazoSorpresas(debug);
        
        tablero = new Tablero();
        
        inicializaTablero(mazo);
        
        inicializaMazoSorpresas();
        
        
    }
    
    
    private void inicializaTablero(MazoSorpresas m)
    {
        
        ArrayList<Casilla> casillas = new ArrayList<>();
        
        for(int i = 0; i < 14; i++){
            
            CasillaCalle calle = new CasillaCalle(nombres_calles[i], (i+1)*500, (i+1)*200, (i+1)*250);
            
            casillas.add(calle);
           
        }
        
        for(int i = 1; i <= 4; i++){
            
                CasillaSorpresa sor = new CasillaSorpresa("Sorpresa", m);
                casillas.add(sor);
        }
        
        Casilla parking = new Casilla("Parking");
        
        casillas.add(parking);
        
        Collections.shuffle(casillas);
        
        for(int i = 0; i < casillas.size(); i++){
            tablero.añadeCasilla(casillas.get(i));
        }
        
    }
    
    
    private void inicializaMazoSorpresas()
    {
        
        for(int i = 1; i <= 6; i++){
            Sorpresa sor = new SorpresaPagarCobrar("Pagar por cobrar: ", (int) (Math.pow(-1,i)*i*250));
    
            mazo.alMazo(sor);
            
        }
        
        for(int i = 1; i <= 4; i++){
            Sorpresa sor = new SorpresaPagarCasaHotel("Pagar por casa y hotel: ",(int) (Math.pow(-1,i)*i*250));
            
            mazo.alMazo(sor);
            
        }
        
        
        mazo.barajar();
       
    }
    
    public Jugador getJugadorActual()
    {
        return jugadores.get(indiceJugadorActual);
    }
    
    public int getIndiceJugadorActual()
    {
        return indiceJugadorActual;
    }
    
    public Tablero getTablero()
    {
        return tablero;
    }
    private void pasarTurno()
    {
        if(indiceJugadorActual < jugadores.size()-1)
            indiceJugadorActual++;
       
        else indiceJugadorActual = 0;
        
    }
    
    
    public void siguientePasoCompletado(OperacionJuego operacion)
    {
        
        estado = gestor.siguienteEstado(jugadores.get(indiceJugadorActual),
                        estado, operacion);
        
    }
    
    public boolean construirCasa(int ip)
    {
        return jugadores.get(indiceJugadorActual).construirCasa(ip);
    }
    
    public boolean construirHotel(int ip)
    {
        return jugadores.get(indiceJugadorActual).construirHotel(ip);

    }
    
    boolean finalDelJuego(){
        boolean fin = false;
        
        for(int i = 0; i< jugadores.size(); i++)
        {
            if(jugadores.get(i).getSaldo() < 0.0)
                fin = true;
        }
        
        return fin;
    }
    
    public boolean finDelJuego(){
        
        boolean fin = finalDelJuego();
        
        return fin;
    }
    private ArrayList<Jugador> ranking(){
        
        ArrayList<Jugador> ranking = new ArrayList<>();
        ranking = jugadores;
        
        ranking.sort((o2, o1) -> o1.compareTo(o2));
        
        return ranking;
    }
    
    public ArrayList<Jugador> ranking_publico(){
        
        return ranking();
    }
    
    public String ranking_string()
    {
        String aux="";
        
        for(int i=0; i<ranking().size(); i++)
        {
            aux=aux+ranking().get(i).getNombre()+ "\t" + Float.toString(ranking().get(i).getSaldo()) + "\n";
        }
        
        return aux;
    }
    
    private void contabilizarPasosPorSalida(){
        
        if(tablero.computarPasoPorSalida()){
            
            jugadores.get(indiceJugadorActual).pasaPorSalida();
            
        }
    }
    
    public void avanzaJugador()
    {
        Jugador jugadorActual = getJugadorActual();
               
        int posicionActual = jugadorActual.getCasillaActual();
        int tirada = Dado.getInstance().tirar();
        int posicionNueva = tablero.nuevaPosicion(posicionActual, tirada);
             
        Casilla casilla = tablero.getCasilla(posicionNueva);
        contabilizarPasosPorSalida();
        jugadorActual.moverACasilla(posicionNueva);
        casilla.recibeJugador(indiceJugadorActual, jugadores);
    }
    
    
    public OperacionJuego siguientePaso()
    {
        
        Jugador jugadorActual = getJugadorActual();
       
        OperacionJuego operacion = gestor.siguienteOperacion(jugadorActual, estado);
        
        switch (operacion)
        {
            case PASAR_TURNO:
                pasarTurno();
                siguientePasoCompletado(operacion);
                break;
            
            case AVANZAR:
                avanzaJugador();
                siguientePasoCompletado(operacion);
                break;         
        }
        
        return operacion;
        
        
    }
    
    
    public boolean comprar(){
        
        Jugador jugadorActual = getJugadorActual();
        int numCasillaActual = jugadorActual.getCasillaActual();
        boolean res = jugadorActual.comprar(((CasillaCalle)tablero.getCasilla(numCasillaActual)));
        
       return res;
        
    }
    
    public ArrayList<Casilla> casillasTablero()
    {
        return tablero.getTablero();
    }
 
    
    
}
