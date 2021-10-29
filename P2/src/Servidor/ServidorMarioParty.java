/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import Personajes.Personajes;
import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


/**
 *
 * @author chave
 */
public class ServidorMarioParty implements Serializable{
    FrameServidor ventana;
    
    private ArrayList<Socket> cliente=new ArrayList<Socket>();//coneccion con el puerto 
    
    private ArrayList<threadServidor> user= new ArrayList<threadServidor>();//coneccion de cada usuario con el servidor 
        
    private ArrayList<Integer> disponibles = new ArrayList<Integer>();
        
    private int cant,jugadores=6;    
    
    
    
public ServidorMarioParty(FrameServidor padre){
        // asigna la ventana
        this.ventana = padre;
        cargarPersonajes();
    }

public void runServer(){
        
        try {
                
                ServerSocket serv = new ServerSocket(8081);
                ventana.mostrar(".::Servidor Activo");
                ventana.mostrar(".::Esperando dos o más usuarios");

           
            while(cant+1<=jugadores){ 
                //crea el socket servidor para aceptar los jugadores 
                
                cliente.add(serv.accept());
                ventana.mostrar("Cliente "+(cant+1)+" Aceptado");
                user.add(new threadServidor(cliente.get(cant), this,(cant+1)));
                user.get(cant).start();
                cant+=1; 
            }
            
            while(true){
                int finish=0;
                for (int i = 0; i < user.size(); i++) {
                    //if(!user.get(i).getNameUser().equals(""));
                    if(user.get(i).getPersonaje()!=null)
                    finish++;
                }
                if(finish==user.size())break;
            }
            
            
            //anadir los contrincantes
            addContrincantes();
            ventana.mostrar("se asignaron los enemigos");
           // verContrincantes();
            
           
           //iniciar tablero
            ventana.mostrar("partida iniciada");
            
            
            
            for (int i = 0; i < user.size(); i++) {
                System.out.println(user.get(i).getPersonaje().getNum());
                
            }
            
            while (true)
            {
            
            }
            
        } catch (IOException ex) {
            ventana.mostrar("ERROR ... en el servidor");
        }
    }



    private void cargarPersonajes(){
        for (int i = 0; i < 10; i++) {
            disponibles.add(i);
        }
    }

    //SETTERS

    public void setDisponibles(ArrayList<Integer> disponibles) {
        this.disponibles=disponibles;
    }
    
    public void setJugadores(int cantJuagdores) { //cantidad de jugadores a jugar
        this.jugadores = cantJuagdores;
    }
   

    //GETTERS 
    
    public ArrayList<Integer> getDisponibles() {
        return disponibles;
    }
    
    public ArrayList<threadServidor> getUser(){
    
        return user;
    }
    
    
   //metodos
    void addContrincantes(){
        
        for (int i = 0; i < user.size(); i++) {
            for(int y=0; y<user.size();y++){
                if(i!=y)
                 user.get(i).enemigos.add(user.get(y));
            
            }
            
        }
    }
    
    public void verContrincantes(){
        String str="";
        for (int i = 0; i < user.size(); i++) {
            ventana.mostrar("enemigos de: "+user.get(i).getNameUser());
            for(int y=0; y<user.get(i).enemigos.size();y++){
                 str+=user.get(i).enemigos.get(y).getNameUser()+" "+user.get(i).enemigos.get(y).getPersonaje().getNum();
            }
            ventana.mostrar(str);
            str="";
        }

    }

}
