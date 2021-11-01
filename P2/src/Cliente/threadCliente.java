/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;
import java.io.*;
import Juegos.TiroDadosInicio;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.HIDE_ON_CLOSE;

/**
 *
 * @author chave
 */
public class threadCliente extends Thread{
   //solo de lectura
     private DataInputStream entrada;
     private DataOutputStream salida;
     private Juego vcli; //referencia a cliente
     private TiroDadosInicio inicio;
     private String name;
     private int num;
     
     
     
   public threadCliente ( DataInputStream entrada,DataOutputStream salida,Juego vcli,String name) throws IOException
   {
      this.entrada=entrada;
      this.salida=salida;
      this.vcli=vcli;
      this.name=name;
      this.num=-1;
      //this.vtn= new Prueba();
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
                        System.out.print("");//NOTA: no eleiminar, por algun bug o fallo sin esto no funciona.
                        
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
                    //agregar las validaciones de que sea numero y que este entre el rango
                   
                    while(this.num==-1){
                    }
                    System.out.println("mande mi puesto... "+this.num);
                    salida.writeInt(0);
                    salida.writeInt(this.num);
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
