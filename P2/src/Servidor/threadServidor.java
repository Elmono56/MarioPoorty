/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author chave
 */


public class threadServidor extends Thread implements Serializable{
     Socket cliente = null;   //referencia a socket de comunicacion de cliente
     
     DataInputStream entrada=null;   //Para leer comunicacion
     DataOutputStream salida=null;   //Para enviar comunicacion	
     
//     ObjectOutputStream salida = null;
//     ObjectInputStream entrada = null;
     
     String nameUser;    //Para el nombre del usuario de esta conexion
     ServidorMarioParty servidor;   // referencia al servidor
     

     // para envio de informacion al enemigo
     ArrayList<threadServidor> enemigos= new ArrayList<threadServidor>();
     
     // identificar el numero de jugador
     int numeroDeJugador;
     
     
     public threadServidor(Socket cliente,ServidorMarioParty serv, int num)
     {
        this.cliente = cliente;
        this.servidor = serv;
        this.numeroDeJugador = num;
        nameUser="";    // inicialmente se desconoce, hasta el primer read del hilo
     }
     
     @Override
     public void run(){
         
         try {
             
          // inicializa para lectura y escritura con stream de cliente
          entrada=new DataInputStream(cliente.getInputStream());
          salida=new DataOutputStream(cliente.getOutputStream());
          

          
          //inicializa para entrada y salida de objetos
//          
//          entradaObj=new ObjectInputStream(cliente.getInputStream());
//          salidaObj=new ObjectOutputStream(cliente.getOutputStream());
//          




        // Es el primer read que hace, para el nombre del useR  
          System.out.println("lee el nombre");
          
          this.setNameUser(entrada.readUTF());
          
          System.out.println("1. Leyo nombre: " + nameUser);
          
          servidor.ventana.mostrar("El jugador " + this.numeroDeJugador + " se llama "+ nameUser);
          
          salida.writeUTF(servidor.getOpciones());
          
          servidor.ventana.mostrar("escogio al personaje: "+entrada.readUTF());
          
          servidor.setOpciones(entrada.readUTF());
          
 
             
         } catch (Exception e) { e.printStackTrace(); }
        int opcion;
       
        
        while(true){
        
            
            try {
             //Siempre espera leer un int que será la instruccion por hacer
             opcion=entrada.readInt();
             
             switch(opcion)
             {
//                 case 0:
//                     servidor.setDisponibles((ArrayList<Integer>) entradaObj.readObject());
//                     System.out.println(servidor.getInt());
//                         break;
             }
                
                
                
                
             
             
             
             
            } catch (Exception e) { 
                
                System.out.println("El cliente termino la conexion\n");break;
                }
            }
            servidor.ventana.mostrar("Se removio un usuario\n");

            try{
                servidor.ventana.mostrar("Se desconecto un usuario: "+nameUser+"\n");
                cliente.close();
            }	
            catch(Exception et){
                servidor.ventana.mostrar("no se puede cerrar el socket\n");   
            }
        
     }
      
// Envia su informacion a todos los demas usuarios excepto él
//     public void enviaInfo()
//     {
//        if (enemigos != null)
//        {
//        try
//        {
//            enemigo.salida.writeInt(2);//escribe opcion de agregar 2
//            enemigo.salida.writeUTF(this.getNameUser());//escribe nombre
//            
//            System.out.println("2. Envia 2 y username "+ "2" +getNameUser());
//        }
//        catch (IOException e) {e.printStackTrace();}
//        }
//     }




//Getter an Setter...
     public String getNameUser()
     {
       return nameUser;
     }
     public void setNameUser(String name)
     {
       nameUser=name;
     }
    
     public void setPersonaje(int personaje){
     
         
     
     }
}
