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
                    
                    
                case 1:
                    this.num=Integer.parseInt(JOptionPane.showInputDialog(null,"Introducir un numero entre 1-1000 :",name,1));
                    //NOTA: agregar las validaciones de que sea numero y que este entre el rango
                   
                    while(this.num==-1){
                    }
                    System.out.println("mande mi puesto... "+this.num);
                    salida.writeInt(0);
                    salida.writeInt(this.num);
                    break;
                    
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
                     if(tablero.getBtnLanzar()){
                         while(true){
                             System.out.print("");//no eliminar porque no funciona el codigo 
                             if(tablero.getBtnLanzar()==false){
                                 salida.writeInt(1);//envia la señal de 1 
                                 System.out.println("señal enviada");
                                 salida.writeInt(tablero.getResultadoDados());//LE ENVIA LA CANTIDAD DE CASIILAS QUE TIENE QUE MOVER
                                 System.out.println("resultado enviada");
                                 salida.writeInt(tablero.getId());//el identificador del jugador
                                 System.out.println("informacion de turno enviada");
                                 break;
                             }
                         }
                     }
                     break;
                 }
                 
                case 4:
                        int movimiento=entrada.readInt();
                        int id=entrada.readInt();
                        tablero.actualizarEnemigos(movimiento,id);
                    break;
                 
                 

    
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
