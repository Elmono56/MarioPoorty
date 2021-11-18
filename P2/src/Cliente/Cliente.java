
package Cliente;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.HIDE_ON_CLOSE;

/**
 *
 * @author Andres Chaves y Pablo Hidalgo 
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
                String cant;
                int num;
                while(true){   
                    cant=JOptionPane.showInputDialog("Introducir Cantidad de Jugadores :");
                    if(isDigit(cant)){
                        num=Integer.parseInt(cant);
                        if(num>1 && num<7)break;
                    }
                    JOptionPane.showMessageDialog(null, "POR FAVOR INGRESE UN NUMERO, ENTRE 2 Y 6");
                }

                    salida.writeInt(num);
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

      new threadCliente(entrada,entradaObj,salida,ventanaCliente,nomCliente).start();
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
    
        private boolean isDigit(String num){
        for (int i = 0; i < num.length(); i++) {
            if (!Character.isDigit(num.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
}