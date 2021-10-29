/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;
import Personajes.Personajes;
import java.io.*;
import java.net.*;
import java.util.ArrayList;


/**
 *
 * @author chave
 */


public class threadServidor extends Thread implements Serializable{
    //variables 
    private Socket cliente = null;   //referencia a socket de comunicacion de cliente
    
    private DataInputStream entrada=null;   //Para leer comunicacion
    private DataOutputStream salida=null;   //Para enviar comunicacion	
   
    private ObjectOutputStream salidaObj = null;
    private ObjectInputStream entradaObj = null;
    
    private Personajes personaje=null; //PARA LA INFORMACION DEL CLIENTE
     
    String nameUser;    //Para el nombre del usuario de esta conexion
    
    private ServidorMarioParty servidor;   // referencia al servidor
    
    ArrayList<threadServidor> enemigos= new ArrayList<threadServidor>(); // para envio de informacion al enemigo
    
    int numeroDeJugador;//numero de jugador
     
     
    



//constructor 
    public threadServidor(Socket cliente,ServidorMarioParty serv, int num){
        
        this.cliente = cliente;
        this.servidor = serv;
        this.numeroDeJugador = num ;
        nameUser="";    // inicialmente se desconoce, hasta el primer read del hilo
     }
     
     
    @Override
    public void run(){
        
        try {
           
            
//inicializa para lectura y escritura con stream de cliente
            entrada=new DataInputStream(cliente.getInputStream());
            salida=new DataOutputStream(cliente.getOutputStream());

// Es el primer read que hace, para el nombre del useR  
            System.out.println("lee el nombre");
          
            this.setNameUser(entrada.readUTF());
          
            System.out.println("1. Leyo nombre: " + nameUser);
          
            servidor.ventana.mostrar("El jugador " + this.numeroDeJugador + " se llama "+ nameUser);


//solicita la cantidad de jugadores a la primera conexion 
            if(this.numeroDeJugador==1){
                salida.writeInt(0);
                servidor.setJugadores(entrada.readInt());
            }else salida.writeInt(-1);


//inicializa para lectura y escritura con stream de cliente

            salidaObj = new ObjectOutputStream(cliente.getOutputStream());
            
            salidaObj.writeObject(servidor.getDisponibles());
            servidor.ventana.mostrar("personajes enviados...");
           
            entradaObj=new ObjectInputStream(cliente.getInputStream());
            servidor.setDisponibles((ArrayList<Integer>)entradaObj.readObject());
            servidor.ventana.mostrar("se actualizo la lista de personajes...");
            
            
            this.personaje=new Personajes(entrada.readInt());
            //System.out.println(this.personaje.getNum());
            


            //enviarAContrincantes();

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
      
 //Envia su informacion a todos los demas usuarios excepto él
   
     public void enviarAContrincantes(){
         if (enemigos != null)
        {
        try
            {
            for (int i = 0; i <enemigos.size() ; i++) {
                    salida.writeInt(0);
                }
            }
        catch (Exception e) {e.printStackTrace();}
        }
    }
     

    
    
    
    
//Getter an Setter...
     public String getNameUser(){
       return nameUser;
     }
    
     public void setNameUser(String name){
       nameUser=name;
     }

    public Personajes getPersonaje(){
        return this.personaje;
    }

}
