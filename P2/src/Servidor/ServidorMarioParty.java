/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author chave
 */
public class ServidorMarioParty {
    FrameServidor ventana;
    private ArrayList<Socket> cliente=new ArrayList<Socket>();
    private ArrayList<threadServidor> user= new ArrayList<threadServidor>();
    
    private ArrayList<Personajes> personajes= new ArrayList<Personajes>();
    private ArrayList<Integer> disponibles = new ArrayList<Integer>();//manera de seleccionar los personajes
    
    private int cant=1,jugadores=6;    
    private boolean accept=true;

    
    private String opciones="0123456789";
    
    
    
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

           
            while(cant<=jugadores){ 
                //crea el socket servidor para aceptar dos conexiones
                if(isAccept()==false)break;
                
                cliente.add(serv.accept());
                ventana.mostrar("Cliente "+cant+" Aceptado");
                user.add(new threadServidor(cliente.get(cant), this,cant));
                user.get(cant).start();
                cant+=1;
            }
            
            ventana.mostrar("partida iniciada");
            
            while (true)
            {
            
            }
            
        } catch (IOException ex) {
            ventana.mostrar("ERROR ... en el servidor");
        }
    }



    private void cargarPersonajes(){
        
        for (int i = 0; i < 10; i++) {
            personajes.add(new Personajes(i));
            disponibles.add(i);
        }
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }
    
    public void setDisponibles(ArrayList<Integer> dispo){
    
    this.disponibles=dispo;
    
    
    }    
    
    
    public void setOpciones(String opciones) {
        this.opciones = opciones;
    }
    
    
    public boolean isAccept() {
        return accept;
    }

    public ArrayList<Integer> getDisponibles(){
    return disponibles;
    }
    
    
    
    public String getInt(){
        String str="";
        for (int i = 0; disponibles !=null; i++) {
            str+= disponibles.get(i);
            
        }
        
        return str;
    }

    public String getOpciones() {
        return opciones;
    }

}
