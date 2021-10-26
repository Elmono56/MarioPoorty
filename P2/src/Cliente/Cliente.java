/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;
import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;

/**
 *
 * @author chave
 */

public class Cliente {
   public static String IP_SERVER = "localhost"; //IP del Servidor
   Juego ventanaCliente; // Ventana del cliente
   DataInputStream entrada = null;//leer comunicacion
   DataOutputStream salida = null;//escribir comunicacion
   Socket cliente = null;//para la comunicacion
   String nomCliente;// nombre del user
   String personaje,opciones,aux;
   
   
//   ObjectInputStream entrada=null;
//   ObjectOutputStream salida=null;
   

   

   
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
                   
         
         
         
         //inicializa para entrada y salida de objetos
          
//          entrada=new ObjectInputStream(cliente.getInputStream());
//          salida=new ObjectOutputStream(cliente.getOutputStream());
        





         // solicita el nombre del user
         nomCliente = JOptionPane.showInputDialog("Introducir Nick :");
         
         //Lo coloca en la ventana
         ventanaCliente.setTitle(nomCliente);
         
         // es lo primero que envia al server
         // el thread servidor esta pendiente de leer el nombre antes de entrar
         // al while para leer opciones
         
         salida.writeUTF(nomCliente);
         System.out.println("1. Envia el nombre del cliente: "+nomCliente);
         
         
         
         //proceso de escoger personaje
         opciones=entrada.readUTF();
         personaje= JOptionPane.showInputDialog(opciones);
         
          for (int i = 0; i < opciones.length(); i++) {
                    
              if(!opciones.substring(i,i+1).equals(personaje))
                  aux+=opciones.substring(i,i+1);
          }
         salida.writeUTF(personaje);
         salida.writeUTF(aux);
         
         
//         
//         entradaObj.readObject();
//         System.out.println("personajes recibidos");
         
          
         
      
      } catch (IOException e) {
         System.out.println("\tEl servidor no esta levantado");
         System.out.println("\t=============================");
      }
      
      // solo se le pasa entrada pues es solo para leer mensajes
      // el hiloCliente lee lo que el servidor le envia, opciones y como tiene referencia
      // a la ventana gato puede colocar en la pantalla cualquier cosa, como las
      //imagenes de X o O, llamar a metodo marcar, colocar el nombre de enemigo
      // o el suyo propio
      new threadCliente(entrada, ventanaCliente).start();
   }
   
   //GETTET AND SETTER
   public String getNombre()
   {
      return nomCliente;
   }
   
}
