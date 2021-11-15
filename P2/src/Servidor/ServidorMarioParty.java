/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

//import Personajes.Personajes;
import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.*;
import java.util.Random;


/**
 *
 * @author chave
 */
public class ServidorMarioParty implements Serializable{
    FrameServidor ventana;
    
    private ArrayList<Socket> cliente=new ArrayList<Socket>();//coneccion con el puerto 
    
    private ArrayList<threadServidor> user= new ArrayList<threadServidor>();//coneccion de cada usuario con el servidor 
        
    private ArrayList<Integer> disponibles = new ArrayList<Integer>();
    
    private ArrayList<String> casillas= new ArrayList<String>();
    
    private int cant,jugadores=6; 
    
    private Random r=new Random();
    
    
    
public ServidorMarioParty(FrameServidor padre){
        // asigna la ventana
        this.ventana = padre;
        cargarPersonajes();
        cargarCasillas();
    }

public void runServer(){
        
        try {
                
                ServerSocket serv = new ServerSocket(8081);
                ventana.mostrar(".::Servidor Activo");
                ventana.mostrar(".::Esperando dos o más usuarios");
                System.out.println(casillas);

                

           
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
                    if(user.get(i).getPersonaje()!=null)
                    finish++;
                }
                if(finish==user.size())break;
            }
            
            
            //anadir los contrincantes
            addContrincantes();
            ventana.mostrar("se asignaron los enemigos");
           // verContrincantes();

            ventana.mostrar("partida iniciada");
           
//            // proceso de orden de turnos
//            int ordenT=r.nextInt(2);
//            if(ordenT==0){
//            
//                ordenDados(ordenT);
//                Collections.sort(user);
//                Collections.copy(user, user);
//                Collections.reverse(user);
//                
//
//            }else{
//                int corte=r.nextInt(1000)+1;
//                ventana.mostrar("el corte es: "+corte);
//                ordenNum(ordenT,corte);
//                Collections.sort(user);
//                Collections.copy(user, user);
//            }
//            
            //ya user esta ordenada por turnos 
            ventana.mostrar("el nuevo orden es: ");
            for (int i = 0; i < user.size(); i++) {
            user.get(i).getPersonaje().setTurno(i);
            ventana.mostrar(user.get(i).getNameUser()+" "+user.get(i).getResultadoOrden()+" "+user.get(i).getPersonaje().getIcon());
            }
            
            //iniciar tablero
             for (int i = 0; i < user.size(); i++) {
                 user.get(i).tablero(casillas);
                 user.get(i).salida.writeInt(3);
                 user.get(i).salida.writeInt(user.get(i).getPersonaje().getTurno());
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
    
    
    private void cargarCasillas(){
        for (int i = 0; i < 9; i++) {
            for(int j=0;j<2;j++){
                casillas.add(getJuego(i));
            }
        }
        for(int i=9;i<17;i++)                
            casillas.add(getJuego(i));
        
        Collections.shuffle(casillas);
        int tubo=1;
        for (int i = 0; i < casillas.size(); i++) {
            if(casillas.get(i).equals("TUBO")){
                casillas.remove(i);
                casillas.add(i, "TUBO"+tubo);
                tubo++;
            }
            
        }


    }   
    
    private String getJuego(int opcion){
        
        switch (opcion){
            
            case 0:return  "COINS";//"GATO";
            case 1:return  "COINS";//SOPA";
            case 2:return  "COINS";//"PATH";
            case 3:return  "COINS";//"MEMORY";
            case 4:return  "COINS";//"CAT";
            case 5:return  "COINS";//"BOMBER";
            case 6:return  "COINS";//"WHO?";
            case 7:return  "COINS";//"COINS";
            case 8:return  "COINS";//"CARDS";
            case 9:return  "COINS";//"CARCEL";
            case 10:return "COINS";//"TUBO";//CORREGIR ESTE CODIGO
            case 11:return "COINS";//"TUBO";
            case 12:return "COINS";//"TUBO";
            case 13:return "COINS";//"ESTRELLA";
            case 14:return "COINS";//"FUEGO";
            case 15:return "COINS";//"HIELO";
            case 16:return "COINS";//"COLA";
        
        }
        return null;
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
    private void addContrincantes(){
        
        for (int i = 0; i < user.size(); i++) {
            for(int y=0; y<user.size();y++){
                if(i!=y)
                 user.get(i).enemigos.add(user.get(y));
            
            }
        }
    }
    
    private void verContrincantes(){
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
    
    
    private void ordenDados(int ordenT){

        
        for (int i = 0; i < user.size(); i++) {
            user.get(i).lanzarOrden(ordenT);
        }
        
        while(true){
            int finish=0;
            for (int i = 0; i < user.size(); i++) {
                if(user.get(i).getResultadoOrden()!=-1)
                    finish++;
            }
            if(finish==user.size())break;
        }
    }
    
    private void ordenNum(int ordenT,int corte){

        for (int i = 0; i < user.size(); i++) {
            user.get(i).lanzarOrden(ordenT);
        }
        
        while(true){
            int finish=0;
            for (int i = 0; i < user.size(); i++) {
                int res=user.get(i).getResultadoOrden();
                if(res!=-1){
                    if(res>corte)user.get(i).setResultadoOrden(res-corte);
                    if(corte>res)user.get(i).setResultadoOrden(corte-res);
                    if(corte==res)user.get(i).setResultadoOrden(0);
                    finish++;
                    res=-1;
                }
            }
            if(finish==user.size())break;
        }

    }
    
}
