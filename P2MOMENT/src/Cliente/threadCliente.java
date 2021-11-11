/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;
import GamesFactory.CollectCoins;
import GamesFactory.JuegoGato;
import GamesFactory.JuegosFactory;
import Personajes.*;
import Juegos.*;
import java.io.*;
import Juegos.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.HIDE_ON_CLOSE;

/**
 *
 * @author chave
 */
public class threadCliente extends Thread{
   //solo de lectura
     private DataInputStream entrada;
     private ObjectInputStream entradaObj;
     private ObjectOutputStream salidaObj;
     private DataOutputStream salida;
     private Juego vcli; //referencia a cliente
     private TiroDadosInicio inicio;
     private String name;
     private int num;
     ArrayList<Personajes> jugadores;
     ArrayList<String> infoCasillas;
     Tablero tablero=null;
     
     
     
   public threadCliente ( DataInputStream entrada,ObjectInputStream entradaObj
           ,DataOutputStream salida,ObjectOutputStream salidaObj,Juego vcli,String name) throws IOException
   {
      this.entradaObj=entradaObj;
      this.entrada=entrada;
      this.salida=salida;
      this.salidaObj=salidaObj;
      this.vcli=vcli;
      this.name=name;
      this.num=-1;
   }
   
   public void run()
   {

     
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
                {
                    inicio=new TiroDadosInicio();
                    inicio.setTitle(name);
                    inicio.setVisible(true);
                    inicio.setDefaultCloseOperation(HIDE_ON_CLOSE);
                    //enviarPuesto();
                    
                    while (true){
                        this.num=inicio.getNum();
                        System.out.print("");//NOTA: no eliminar, por algun bug o fallo sin esto no funciona.
                        
                        if(this.num!=-1){
                            System.out.println("mande mi puesto... "+this.num);
                            salida.writeInt(0);
                            salida.writeInt(this.num);
                            break;
                        }
                    }
                    break;
                } 
                    
                case 1:
                {
                    this.num=Integer.parseInt(JOptionPane.showInputDialog(null,"Introducir un numero entre 1-1000 :",name,1));
                    //NOTA: agregar las validaciones de que sea numero y que este entre el rango
                   
                    while(this.num==-1){
                    }
                    System.out.println("mande mi puesto... "+this.num);
                    salida.writeInt(0);
                    salida.writeInt(this.num);
                    break;
                } 
                case 2:
                 {
                     try {
                        jugadores = (ArrayList<Personajes>)entradaObj.readObject();   
                        infoCasillas=(ArrayList<String>)entradaObj.readObject();   
                        tablero = new Tablero(jugadores,infoCasillas);
                        tablero.setVisible(true);
                        tablero.setTitle(name);

                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(threadCliente.class.getName()).log(Level.SEVERE, null, ex);
                     }
                     break;
                 }
                case 3:
                 {  
                    int acceso=entrada.readInt();
                    if(tablero.getTurno()==acceso){
                        tablero.setResultadoDados(0);
                        
                         System.out.println("puedo tirar "+name);
                         
                         while(tablero.getBtnLanzar()==true){                            
                             System.out.print("");//no eliminar porque no funciona el codigo 
                             
                             if(!tablero.getBtnLanzar()){
                                 System.out.println("tire dados");
                                 break;
                             }
                         }
 
                        try {     
                                /*
                                if(tablero.haGanado()==true){
                                    salida.writeInt(2);
                                    salida.writeUTF(name);
                                    JOptionPane.showMessageDialog(null, "GANADOR: "+name, "GANADOR DEL JUEGO", 1);
                                    tablero.setVisible(false);  
                                }
                                */
                            
                                if (tablero.getJuego()==true){
                                    System.out.println("ENTRO A JUEGO");
                                    System.out.println(tablero.getNombrejuego());
                                    int turno = acceso;
                                    
                                    salida.writeInt(8);
                                    salida.writeInt(turno);
                                    salida.writeUTF(tablero.getNombrejuego());
                                    tablero.setJuego(false);
                                    //actualizarJuego();
                                    break;
                                }

                                if(tablero.getCola()==true){
                                    
                                    
                                    System.out.println("ENTRE EN COLA.......");
                                    
                                    while(tablero.getCola()){
                                        System.out.print("");
                                    }
                                    
                                    actualizarJuego();
                                    salida.writeInt(3);
                                    break;
                                } 
                                                                     
                                if(tablero.getFuego()==true){
                                    
                                    System.out.println("ENTRE A FUEGO");                                    
                                    while(tablero.getEnemigo()==null){
                                        System.out.print("");
                                       
                                    }
                                    if(tablero.getEnemigo()!=null){
                                        salida.writeInt(6);
                                        salida.writeInt(tablero.getEnemigo().getTurno());
                                        System.out.println(tablero.getEnemigo().getNum()+" "+tablero.getEnemigo().getName());
                                        salida.writeInt(tablero.getEnemigo().getNum());    
                                    }
                                    
                                    tablero.setFuego(false);
                                    tablero.setEnemigo(null);
                                    actualizarJuego();
                                    salida.writeInt(3);
                                    break;
                                }  

                                
                                
                                if(tablero.getHielo()==true){
                                    System.out.println("ENTRE A HIELO");                                    
                                    while(tablero.getEnemigo()==null){
                                        System.out.print("");
                                       
                                    }
                                    
                                    if(tablero.getEnemigo()!=null){
                                        salida.writeInt(7);
                                        salida.writeInt(tablero.getEnemigo().getTurno());
                                    }
                                    tablero.setEnemigo(null);
                                    tablero.setHielo(false);
                                    actualizarJuego();
                                    salida.writeInt(3);
                                    break;
                                }
                                
                                
                                if(tablero.getTubo()!=0){
                                    actualizarJuego();
                                    tablero.setTubo(0);
                                    salida.writeInt(3);
                                    break;
                                }
//                                
                                if(tablero.getRepite()){
                                    
                                    actualizarJuego();
                                    System.out.println("repito turno... "+name);
                                    tablero.setRepite(false);
                                    int turno=acceso;//tablero.getTurno();
//                                    if(turno==-1)turno=0;
                                    System.out.println(turno+" ,"+tablero.getId());
                                    salida.writeInt(4);
                                    salida.writeInt(turno);
                                    
                                    actualizarme(turno);
                                    salida.writeInt(5);
                                    break;
                                }
//                             

                                
                                actualizarJuego();
                                salida.writeInt(3);
                                break;
                        }catch (IOException ex){         
                             Logger.getLogger(threadCliente.class.getName()).log(Level.SEVERE, null, ex);
                            }         

                    }

                    break;
                }

                case 4:
                {
                    System.out.println("actualizando enemigo...");
                    int movimiento=entrada.readInt();
                    int id=entrada.readInt();
                    int tubo=entrada.readInt();
                    tablero.actualizarEnemigos(movimiento,id,tubo);
                    break;
                }
                
                case 5:
                {
                String ganador=entrada.readUTF();
                JOptionPane.showMessageDialog(null, "GANADOR: "+ganador, "GANADOR DEL JUEGO", 3);
                tablero.setVisible(false);
                
                break;
                }
                case 6:
                {
                    int turno=entrada.readInt();
                    System.out.println("ENTRE "+tablero.getTurno());
                    while(tablero.getTurno()!=turno){
                        tablero.actualizarTurno();
                    }
                    tablero.setRepite(false);
                    System.out.println("SALI "+tablero.getTurno());
                break;
                }
                
                case 7:
                {
                    int turno=entrada.readInt();
                    int cantidad=0;
                    for (int i = 0; i < jugadores.size(); i++) {
                        if(jugadores.get(i).getTurno()==turno){
                            jugadores.get(i).setInmovil(true);
                            cantidad=jugadores.get(i).getCantInmovil();
                            jugadores.get(i).setCantInmovil(cantidad+2);
                            break;
                        }
                        
                    }
                    break;
                }
                case 8:
                {
                    int turno=entrada.readInt();
                    int movimientos=entrada.readInt();
                    for (int i = 0; i < jugadores.size(); i++) {
                        if(jugadores.get(i).getTurno()==turno){
                            System.out.println(jugadores.get(i).getCasillaActual());
                            tablero.moverFicha(jugadores.get(i), movimientos, 0);
                            System.out.println(jugadores.get(i).getCasillaActual());
                            break;
                        }
                        
                    }
                    
                    break;
                }
                case 9:
                {
                    int turno=entrada.readInt();
                    
                    String nombrejuego=entrada.readUTF();
                    System.out.println("ENTRANDO A JUEGO");
                    
                    String enemigo = "";
                    //boolean auxiliar = false;
                    /*
                    if (nombrejuego.equals("GATO")){
                        //while (auxiliar==false){
                            salida.writeInt(9);
                            salida.writeUTF(this.name);
                            turno=entrada.readInt();
                            for (int i = 0; i < jugadores.size(); i++) {
                                if(jugadores.get(i).getTurno()==turno){ 
                                    enemigo = jugadores.get(i).getName();
                                    break;
                                }
                            }
                            //auxiliar = entrada.readBoolean();
                        //}
                        
                    }
                    */
                    
                    JFrame ventanajuego = new JFrame();
                    
                    ventanajuego = JuegosFactory.crearJuego(JuegosFactory.Games.COINS);
                    
                    ventanajuego.setVisible(true);
                    
                    
                   /*
                    JuegoGato ventanajuego = (JuegoGato) JuegosFactory.crearJuego(JuegosFactory.Games.COINS);
                    ventanajuego.setVisible(true);
                    ventanajuego.setAmigo(this.name);
                    ventanajuego.setEnemigo(enemigo);
                    ventanajuego.setNumeroJugador(1);
                    ventanajuego.setTurnoJugador(1);
                    //ventanajuego.setVisible(true);
                    /*
                    while(ventanajuego.haGanado()){
                        if (ventanajuego.getColumnaA()!=-1 & ventanajuego.getFilaA()!=-1){
                            salida.writeInt(ventanajuego.getColumnaA());
                            salida.writeInt(ventanajuego.getFilaA());
                            ventanajuego.setColumnaA(-1);
                            ventanajuego.setFilaA(-1);
                            
                        }
                        ventanajuego.marcar(entrada.readInt(), entrada.readInt());
                    }
                    */
                    
                    break;
                }
                
                case 10:{
                    
                    String nombre = entrada.readUTF();
                    
                    JuegoGato ventanajuego = (JuegoGato) JuegosFactory.crearJuego(JuegosFactory.Games.GATO);
                    ventanajuego.setVisible(true);
                    
                    /*ventanajuego.setAmigo(this.name);
                    ventanajuego.setEnemigo(nombre);
                    ventanajuego.setNumeroJugador(2);
                    ventanajuego.setTurnoJugador(1);*/
                    
                    break;
                    
                }
            }
         }
         catch (IOException e){
            System.out.println("Error en la comunicaci�n "+"Informaci�n para el usuario");
            break;
         }
      }
      System.out.println("se desconecto el servidor");
   }
   

   
    private void actualizarme(int turno){
       
        System.out.println("ENTRE "+tablero.getTurno());
        while(tablero.getTurno()!=turno){
            tablero.actualizarTurno();
        }
        tablero.setRepite(false);
        System.out.println("SALI "+tablero.getTurno());
        
       
    }
    
    private void actualizarJuego(){
         try {
            salida.writeInt(1);//envia la señal de 1 
            System.out.println("señal enviada");
            salida.writeInt(tablero.getResultadoDados());//LE ENVIA LA CANTIDAD DE CASIILAS QUE TIENE QUE MOVER
            System.out.println("resultado enviada");
            salida.writeInt(tablero.getId());//el identificador del jugador
            salida.writeInt(tablero.getTubo());
            
            System.out.println("informacion de turno enviada");
         
         } catch (IOException ex) {
             Logger.getLogger(threadCliente.class.getName()).log(Level.SEVERE, null, ex);
         }

    
    
    }
  
   
}
