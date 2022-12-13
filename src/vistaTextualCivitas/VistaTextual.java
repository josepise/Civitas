package vistaTextualCivitas;

import GUI.Vista;
import civitas.Casilla;
import civitas.CivitasJuego;
import civitas.CivitasJuego;
import civitas.Diario;
import civitas.OperacionJuego;
import civitas.Jugador;
import controladorCivitas.Respuesta;
import civitas.OperacionInmobiliaria;
import civitas.Jugador;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;



public class VistaTextual implements Vista {
  
    
  private static String separador = "=====================";
  
  private Scanner in;
  
  CivitasJuego juegoModel;
  
  public VistaTextual (CivitasJuego juegoModel) {
    in = new Scanner (System.in);
    this.juegoModel=juegoModel;
  }
  
  
           
 public  void pausa() {
    System.out.print ("\nPulsa una tecla");
    in.nextLine();
  }

  int leeEntero (int max, String msg1, String msg2) {
    Boolean ok;
    String cadena;
    int numero = -1;
    do {
      System.out.print (msg1);
      cadena = in.nextLine();
      try {  
        numero = Integer.parseInt(cadena);
        ok = true;
      } catch (NumberFormatException e) { // No se ha introducido un entero
        System.out.println (msg2);
        ok = false;  
      }
      if (ok && (numero < 0 || numero >= max)) {
        System.out.println (msg2);
        ok = false;
      }
    } while (!ok);

    return numero;
  }

  int menu (String titulo, ArrayList<String> lista) 
  {
    String tab = "  ";
    int opcion;
    System.out.println (titulo);
    for (int i = 0; i < lista.size(); i++) {
      System.out.println (tab+i+"-"+lista.get(i));
    }

    opcion = leeEntero(lista.size(),
                          "\n"+tab+"Elige una opción: ",
                          tab+"Valor erróneo");
    return opcion;
  }
  
  public void actualiza()
  {
      
      if(juegoModel.finDelJuego()){
          
          ArrayList<Jugador> ranking = juegoModel.ranking_publico();
          int k = 1;
          
          
          for(Jugador jugador:ranking){
              
              System.out.println("Jugador numero " + k + ": " + 
                                  jugador.toString());
          }
      }else{
          System.out.println("Jugador actual: " + juegoModel.getJugadorActual());
          
      }  
  }
  
  
 
  public Respuesta comprar()
  {
      String cadena;
      Respuesta resp;
      boolean ok=false;
   
      do{
          System.out.println("¿Desea comprar la calle? Si/No");
          cadena=in.nextLine();
      
          if(cadena.contentEquals("Si")|| cadena.contentEquals("No"))
              ok=true;
      }while(!ok);
      
      if(cadena.contentEquals("Si"))
      {
          resp=Respuesta.SI;
      }
      else
      {
          resp=Respuesta.NO;
      }
      
      return resp;
  }
  
  
  public OperacionInmobiliaria elegirOperacion()
  {
      ArrayList<String> operaciones=new ArrayList<>();
      OperacionInmobiliaria actuacion;
      
      operaciones.add("CONSTRUIR_CASA");
      operaciones.add("CONSTRUIR_HOTEL");
      operaciones.add("TERMINAR");
      
      int num_op=menu("¿Que gestión desea realizar?",operaciones);
      
      
      switch (num_op) {
          case 0:
              actuacion=OperacionInmobiliaria.CONSTRUIR_CASA;
              break;
          case 1:
              actuacion=OperacionInmobiliaria.CONSTRUIR_HOTEL;
              break;
          default:
              actuacion=OperacionInmobiliaria.TERMINAR;
              break;
      }
      
    return actuacion;  
  }
  
  public int elegirPropiedad()
  {
      int propiedad;
      ArrayList<String> propiedades=new ArrayList<>();
      
      for(int i=0; i< juegoModel.getJugadorActual().getPropiedades().size();i++)
      {
          propiedades.add(juegoModel.getJugadorActual().getPropiedades().get(i).toString());
      }
      
      propiedad=menu("¿Sobre que propiedad desea realizar la gestion",
              propiedades);
      
      return propiedad;
  }

  public void mostrarSiguienteOperacion(OperacionJuego operacion)
  {
      System.out.println("La siguiente operacion es : " + operacion);
  }
  
  public void mostrarEventos()
  {
      
       while(Diario.getInstance().eventosPendientes())
       {
           System.out.println(Diario.getInstance().leerEvento());
       }
       
  }
}
