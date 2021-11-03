/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.HIDE_ON_CLOSE;

/**
 *
 * @author chave
 */

public class Cliente {
   
   public static String IP_SERVER = "localhost"; //IP del Servidor
  
   Juego ventanaCliente; // Ventana del cliente
   private DataInputStream entrada = null;//leer comunicacion
   private DataOutputStream salida = null;//escribir comunicacion
   private Socket cliente = null;//para la comunicacion
   private EscogerPersonajes vtnEscoger;
   private String nomCliente;// nombre del user
   private ArrayList<Integer> disponibles= new ArrayList <Integer>();
   private int miPersonaje;
   private ObjectInputStream entradaObj=null;
   private ObjectOutputStream salidaObj=null;
 
   /** Creates a new instance of Cliente */
   
   
   
   
   public Cliente(Juego vent) throws IOException
   {      
      this.ventanaCliente=vent;
   }
   
      
   public void conexion() throws IOException, ClassNotFoundException 
   {
        try {
            
// se conecta con dos sockets al server, uno comunicacion otro msjes
            cliente = new Socket(Cliente.IP_SERVER, 8081);
         
            
// inicializa las entradas-lectura y salidas-escritura
            entrada = new DataInputStream(cliente.getInputStream());
            salida = new DataOutputStream(cliente.getOutputStream());

            
// solicita el nombre del user
            nomCliente = JOptionPane.showInputDialog("Introducir Nick :");
            ventanaCliente.setTitle(nomCliente);
            
            
//envia el nombre
            salida.writeUTF(nomCliente);
            System.out.println("1. Envia el nombre del cliente: "+nomCliente);
         
            
//si es el primero le pide la cantidad de jugadores 
             if(entrada.readInt()==0){
                int cant;
                cant=Integer.parseInt(JOptionPane.showInputDialog("Introducir Cantidad de Jugadores :"));
                
                //NOTA: agregar validaciones de entero y que sea menor O IGUAL a 6
                
                salida.writeInt(cant);
            }
         
                  
            
//inicializa para entrada y salida de objetos
          
            this.entradaObj=new ObjectInputStream(cliente.getInputStream());
            this.salidaObj=new ObjectOutputStream(cliente.getOutputStream());
        
            
//proceso de escoger personaje
            this.disponibles=(ArrayList<Integer>)entradaObj.readObject();
            System.out.println("personajes recibidos...");
            
      
            vtnEscoger= new EscogerPersonajes(this.disponibles);


            while(true){
                
                JOptionPane.showInternalMessageDialog(null, vtnEscoger, "PERSONAJES", HIDE_ON_CLOSE);
                if(vtnEscoger.getOut()!=0){
                    //envia la lista actualizada
                    this.salidaObj.writeObject(vtnEscoger.getPersonajes());
                    break;
                }
            }
            
            //envia el personaje seleccionado
            this.miPersonaje=vtnEscoger.getSeleccionado();
            this.salida.writeInt(this.miPersonaje);
            
            
            
      } catch (IOException e) {
         System.out.println("\tEl servidor no esta levantado");
         System.out.println("\t=============================");
      }
      
      // solo se le pasa entrada pues es solo para leer mensajes
      // el hiloCliente lee lo que el servidor le envia, opciones y como tiene referencia
      // a la ventana gato puede colocar en la pantalla cualquier cosa, como las
      //imagenes de X o O, llamar a metodo marcar, colocar el nombre de enemigo
      // o el suyo propio
      new threadCliente(entrada,entradaObj,salida, ventanaCliente,nomCliente).start();
   }
   
   //GETTET AND SETTER
   public String getNombre()
   {
      return nomCliente;
   }
    public ArrayList<Integer> getListaDisponibles() {
        return this.disponibles;
    }

    public int getMiPersonaje() {
        return miPersonaje;
    }
    
}