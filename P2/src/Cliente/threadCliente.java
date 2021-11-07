/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;
import Personajes.Personajes;
import Juegos.Tablero;
import java.io.*;
import Juegos.TiroDadosInicio;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
     private DataOutputStream salida;
     private Juego vcli; //referencia a cliente
     private TiroDadosInicio inicio;
     private String name;
     private int num;
     ArrayList<Personajes> jugadores;
     ArrayList<String> infoCasillas;
     Tablero tablero=null;
     
     
     
   public threadCliente ( DataInputStream entrada,ObjectInputStream entradaObj
           ,DataOutputStream salida,Juego vcli,String name) throws IOException
   {
      this.entradaObj=entradaObj;
      this.entrada=entrada;
      this.salida=salida;
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

                                if(tablero.haGanado()==true){
                                    salida.writeInt(2);
                                    salida.writeUTF(name);
                                    JOptionPane.showMessageDialog(null, "GANADOR: "+name, "GANADOR DEL JUEGO", 1);
                                    tablero.setVisible(false);  
                                }
                               
                               
                                 //poner if de si es un juego

                                actualizarJuego();

                                if(tablero.getRepite()){
                                    System.out.println("repito turno... "+name);
                                    tablero.setRepite(false);
                                  
                                    int turno=acceso;//tablero.getTurno();
                                    //if(turno==-1)turno=0;
                                    System.out.println(turno+" ,"+tablero.getId());
                                    
                                    salida.writeInt(4);
                                    salida.writeInt(turno);
                                    actualizarme(turno);
                                    salida.writeInt(5);
                                    
                                    break;
                                }
                                
                                
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
                    tablero.actualizarEnemigos(movimiento,id);
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
            
            System.out.println("informacion de turno enviada");
         
         } catch (IOException ex) {
             Logger.getLogger(threadCliente.class.getName()).log(Level.SEVERE, null, ex);
         }

    
    
    }
  
   
}
