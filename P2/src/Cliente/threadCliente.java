/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;
import java.io.*;

/**
 *
 * @author chave
 */
public class threadCliente extends Thread{
   //solo de lectura
     DataInputStream entrada;
     Juego vcli; //referencia a cliente
     Prueba vtn;
     
     
     
     
   public threadCliente ( DataInputStream entrada,Juego vcli) throws IOException
   {
      this.entrada=entrada;
      this.vcli=vcli;
      this.vtn= new Prueba();
   }
   
   public void run()
   {
       //VARIABLES
      String menser="",amigo="";
      int opcion=0;
      
      // solamente lee lo que el servidor threadServidor le envia
      while(true)
      {         
         try{
             // esta leyendo siempre la instruccion, un int
             opcion=entrada.readInt();
            
            switch(opcion)
            {
                case 0:
                    vtn.setVisible(true);
                    
            }
         }
         catch (IOException e){
            System.out.println("Error en la comunicaci�n "+"Informaci�n para el usuario");
            break;
         }
      }
      System.out.println("se desconecto el servidor");
   }

}
