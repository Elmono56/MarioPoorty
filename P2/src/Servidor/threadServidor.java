/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;
import Personajes.Personajes;
import Servidor.ServidorMarioParty;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author chave
 */


public class threadServidor extends Thread implements Serializable,Comparable<threadServidor>{
    //variables 
    private Socket cliente = null;   //referencia a socket de comunicacion de cliente
    
    private DataInputStream entrada=null;   //Para leer comunicacion
    public DataOutputStream salida=null;   //Para enviar comunicacion	
    private ObjectOutputStream salidaObj = null;
    private ObjectInputStream entradaObj = null;
    private Personajes personaje=null; //PARA LA INFORMACION DEL CLIENTE

    String nameUser;    //Para el nombre del usuario de esta conexion
    
    private ServidorMarioParty servidor;   // referencia al servidor
    
    ArrayList<threadServidor> enemigos = new ArrayList<threadServidor>(); // para envio de informacion al enemigo
    ArrayList<Personajes> jugadores= new ArrayList<Personajes>();
    
    int numeroDeJugador,opcion;//numero de jugador
    private int puesto=-1; 
     
    



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
            this.personaje=new Personajes(entrada.readInt(),nameUser);

            


         } catch (Exception e) { e.printStackTrace(); }
        while(true){
        
            
            try {
             //Siempre espera leer un int que será la instruccion por hacer
             opcion=entrada.readInt();
             
             switch(opcion)
             {
                case 0:{
                        this.puesto=entrada.readInt();
                        break;
                }

                case 1:{
                    
                    System.out.println("movimeintos recibidos...");
                    int movimiento = entrada.readInt();//lee la cantidad de movimientos 
                    int id= entrada.readInt();//lee el identificador del personaje
                    int tubo=entrada.readInt();
                    for (int i = 0; i < enemigos.size(); i++) {
                        System.out.println("enemigo "+enemigos.get(i).getNameUser()+" encontrado...");
                        enemigos.get(i).salida.writeInt(4);
                        enemigos.get(i).salida.writeInt(movimiento);
                        enemigos.get(i).salida.writeInt(id);
                        enemigos.get(i).salida.writeInt(tubo);
                    }
                    break;
                }
                    
                case 2:{
                    String ganador = entrada.readUTF();//lee el identificador del personaje
                    
                    for (int i = 0; i < enemigos.size(); i++) {
                        enemigos.get(i).salida.writeInt(5);
                        enemigos.get(i).salida.writeUTF(ganador);
                    }
                    
                    break;
                
                }

                case 3:{
                    for (int i = 0; i < enemigos.size(); i++) {
                        enemigos.get(i).salida.writeInt(3);
                        enemigos.get(i).salida.writeInt(enemigos.get(i).getPersonaje().getTurno());
                    }
                    break;
                
                }
                case 4:{
                    
                    int turno= entrada.readInt();
                    for (int i = 0; i < enemigos.size(); i++) {
                        enemigos.get(i).salida.writeInt(6);
                        enemigos.get(i).salida.writeInt(turno);
                    }

                break;
                }
               
                case 5:{
                    salida.writeInt(3);
                    salida.writeInt(this.personaje.getTurno());
                break;
                }
                
                case 6:{
                    int turno= entrada.readInt();
                    int movimientos=entrada.readInt();
                    
                    for (int i = 0; i < enemigos.size(); i++) {
                        enemigos.get(i).salida.writeInt(8);
                        enemigos.get(i).salida.writeInt(turno);
                        enemigos.get(i).salida.writeInt(movimientos);

                    }
                    
                    break;
                }
                
                case 7:{
                    int turno= entrada.readInt();
                    for (int i = 0; i < enemigos.size(); i++) {
                        if(enemigos.get(i).getPersonaje().getTurno()==turno){
                            enemigos.get(i).salida.writeInt(7);
                            enemigos.get(i).salida.writeInt(turno);
                            break;
                        }
                    }
                    
                    break;
                }
                
                case 8:{
                    
                    int turno = entrada.readInt();
                    
                    String juego = entrada.readUTF();
                    System.out.println("DATOS RECIBIDOS");
                    salida.writeInt(9);
                    salida.writeInt(turno);
                    salida.writeUTF(juego);
                    break;
                }  
             
             case 9:{
                 
                String nombre = entrada.readUTF();
                
                Random rant = new Random();
                
                int enemigo = rant.nextInt(enemigos.size());
                                
                salida.writeInt(enemigos.get(enemigo).getPersonaje().getTurno());
             
                enemigos.get(enemigo).salida.writeInt(10);
                
                enemigos.get(enemigo).salida.writeUTF(nombre);
                                
                break;
             }
             case 10:{
                 int res=entrada.readInt();
                 int columna=entrada.readInt();
                 int fila= entrada.readInt();
                 String enemigo=entrada.readUTF();
                 
                 for (int i = 0; i < enemigos.size(); i++) {
                     
                    if(enemigos.get(i).getPersonaje().getName().equals(enemigo)){
                        
                        enemigos.get(i).salida.writeInt(res);
                        enemigos.get(i).salida.writeInt(columna);
                        enemigos.get(i).salida.writeInt(fila);
                        
                        break;
                        }

                    }
                break;
                }
             
             case 11:{
                 salida.writeInt(11);
                 break;
             }
             
            case 12:{
                String nombre = entrada.readUTF();
                
                Random rant = new Random();
                
                int enemigo = rant.nextInt(enemigos.size());
                                
                salida.writeInt(enemigos.get(enemigo).getPersonaje().getTurno());
             
                enemigos.get(enemigo).salida.writeInt(12);
                
                enemigos.get(enemigo).salida.writeUTF(nombre);
                break;
            }
            case 13:{
                String enemigo=entrada.readUTF(); 
                int parejas= entrada.readInt();
                 
                 
                 for (int i = 0; i < enemigos.size(); i++) {
                     
                    if(enemigos.get(i).getPersonaje().getName().equals(enemigo)){

                        enemigos.get(i).salida.writeInt(parejas);
                        
                        break;
                        }

                    }
                break;
            }
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
      
    
//metodos
    
//envia el numero del juego a abrir para el orden 
    public void lanzarOrden(int ordenT){
        
        try {            
            
            if(ordenT==0)
                salida.writeInt(0);
            
            if (ordenT==1)
                salida.writeInt(1);
            
        } catch (Exception e) {
        }

        
    }


//manda la regla de abrir el tablero   
    public void tablero(ArrayList<String>infoCasillas){
        
        try
            {
                salida.writeInt(2);
               
                for (int i = 0; i < enemigos.size(); i++) {
                    jugadores.add(enemigos.get(i).getPersonaje()); 
                }
                jugadores.add(getPersonaje());
                salidaObj.writeObject(jugadores);
                salidaObj.writeObject(infoCasillas);

            }
        catch (Exception e) {e.printStackTrace();}
        }
     

//Getter
     public String getNameUser(){
       return nameUser;
     }

    public Personajes getPersonaje(){
        return this.personaje;
    }
        
    public int getResultadoOrden(){
    
        return this.puesto;
    }   
    
    
//SETTER
    
     public void setNameUser(String name){
       nameUser=name;
     }

    public void setOpcion(int num){
  
        this.opcion=num;
    }

    public void setResultadoOrden(int num){
  
        this.puesto=num;
    }
    
    //sirve para poder reordenar la lista
    public int compareTo(threadServidor compareUser) {
        
        int compareResultado=((threadServidor)compareUser).getResultadoOrden();

        return this.puesto-compareResultado;//para prden ascendente solo intercambiar


    }

}
