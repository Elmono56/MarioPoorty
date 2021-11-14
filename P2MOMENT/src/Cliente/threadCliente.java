/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;
import GamesFactory.CollectCoins;
import GamesFactory.JuegoGato;
import GamesFactory.JuegosFactory;
import GamesFactory.MemoryPath;
//import GamesFactory.MemoryPath;
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
     private int num,acceso;
     private boolean juegoActivo,resultado;
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
      this.juegoActivo=false;
      this.resultado=false;
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
                    String op;
                    while(true){
                    op=(JOptionPane.showInputDialog(null,"Introducir un numero entre 1-1000 :",name,1));                 
                
                        if(isDigit(op)){
                            this.num=Integer.parseInt(op);
                            if(this.num>0 && num<1001)break;
                        }
                        JOptionPane.showMessageDialog(null, "POR FAVOR INGRESE UN NUMERO, ENTRE 1 Y 1000");
                    } 
                    
                    //System.out.println("mande mi puesto... "+this.num);
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
                    this.acceso=entrada.readInt();
                    if(tablero.getTurno()==this.acceso){
                        tablero.setResultadoDados(0);
                        
                         //System.out.println("puedo tirar "+name);
                         
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
                             
                                    //System.out.println("ENTRO A JUEGO");
                                    int turno = acceso;
                                    salida.writeInt(8);//case de juegos 
                                    salida.writeInt(turno);
                                    salida.writeUTF(tablero.getNombrejuego());
                                    tablero.setJuego(false);
                                    break;
                                }

                                if(tablero.getCola()==true){
                                    abrirCola();
                                    break;
                                } 
                                                                     
                                if(tablero.getFuego()==true){
                                    abrirFuego();
                                    break;
                                }  

                                if(tablero.getHielo()==true){
                                    abrirHielo();
                                    break;
                                }
                                
                                
                                if(tablero.getTubo()!=0){
                                    actualizarJuego();
                                    tablero.setTubo(0);
                                    salida.writeInt(3);
                                    break;
                                }                                
                                if(tablero.getRepite()){
                                    
                                    actualizarJuego();
                                    System.out.println("repito turno... "+name);
                                    tablero.setRepite(false);
                                    int turno=this.acceso;//tablero.getTurno();
                                    System.out.println(turno+" ,"+tablero.getId());
                                    salida.writeInt(4);
                                    salida.writeInt(turno);
                                    actualizarme(turno);
                                    salida.writeInt(5);
                                    break;
                                }                             

                                continuar();
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
                    Personajes jugador=jugadores.get(turno);
                    setJuegoActivo(true);
                    
                    
                    
                    switch(nombrejuego){
                        case "GATO" :{
                            juegoGato(jugador,nombrejuego);
                            break;
                        }
                        case "COINS" :{
                            collectCoins(jugador,nombrejuego);
                            break;
                        }
                        case "PATH" :{
                            memoryPath(jugador,nombrejuego);
                            break;
                        }           
                    }
                  esperar();
                  break;
                }

                case 10:{
                    
                    String enemigo = entrada.readUTF();
                    
                    JuegoGato ventanajuego = (JuegoGato) JuegosFactory.crearJuego(JuegosFactory.Games.GATO);
                    ventanajuego.setVisible(true);
                    ventanajuego.setTitle(this.name);
                    ventanajuego.setDefaultCloseOperation(HIDE_ON_CLOSE);
                    ventanajuego.setNumeroJugador(2);
                    
                    while(true){
                        
                        if(ganadorGato(ventanajuego,enemigo))break;
                       
                        turnoP1Gato(ventanajuego, enemigo);
                        
                        if(ventanajuego.fullTablero())break;
                    }
                    break;
                    
                }
                case 11:{
                    continuar();
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
   private  void esperar(){
        try {  
            
            while(getJuegoActivo()){
                System.out.print("");
            }
                        
            System.out.println("sali del juego");
          
            salida.writeInt(11);
            
         } catch (IOException ex) {
             Logger.getLogger(threadCliente.class.getName()).log(Level.SEVERE, null, ex);
         }
   
    }
    
    
    private void continuar(){
         try { 
             actualizarJuego();
             System.out.println(tablero.getTurno());
             salida.writeInt(3);
         } catch (IOException ex) {
             Logger.getLogger(threadCliente.class.getName()).log(Level.SEVERE, null, ex);
         }
   }
    
    private void abrirHielo() throws IOException{
        while(tablero.getEnemigo()==null){
            System.out.print("");                                       
        }
                                    
        if(tablero.getEnemigo()!=null){
            salida.writeInt(7);
            salida.writeInt(tablero.getEnemigo().getTurno());
        }
        tablero.setEnemigo(null);
        tablero.setHielo(false);
        continuar();
    }
    
    private void abrirCola(){
        while(tablero.getCola()){
            System.out.print("");
        } 
        continuar();
    }
    
    private void abrirFuego() throws IOException{
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
            continuar();           
    }     

    private void abrirRepite() throws  IOException{
        
        System.out.println("repito turno... "+name);
        tablero.setRepite(false);
        int turno=this.acceso;//tablero.getTurno();
        System.out.println(turno+" ,"+tablero.getId());
        salida.writeInt(4);
        salida.writeInt(turno);
        actualizarme(turno);
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
  //JUEGOS
    
    private void collectCoins(Personajes jugadorP, String nombreJuego){
         
        CollectCoins ventanajuego = (CollectCoins) JuegosFactory.crearJuego(JuegosFactory.Games.COINS);

        System.out.println("estoy en coins");

        ventanajuego.setVisible(true);       
        ventanajuego.setTitle(this.name);
        ventanajuego.setDefaultCloseOperation(HIDE_ON_CLOSE);
        System.out.println("estoy en coins");
        
        
        while(ventanajuego.isVisible()){
            System.out.print("");
        }
        setJuegoActivo(false);
   
    }
    
    private void memoryPath(Personajes jugadorP, String nombreJuego){
    
        MemoryPath ventanajuego = (MemoryPath) JuegosFactory.crearJuego(JuegosFactory.Games.MEMORYPATH);
        ventanajuego.setVisible(true);       
        ventanajuego.setTitle(this.name);
        ventanajuego.setDefaultCloseOperation(HIDE_ON_CLOSE);
        
        while(ventanajuego.getActivo()){
            System.out.print("");
        }
        
        setJuegoActivo(false);
    }
    
            
    private void juegoGato(Personajes jugadorP, String nombreJuego) throws IOException{
        
        int turno;
        String enemigo="";
        salida.writeInt(9);
        salida.writeUTF(this.name);
        turno=entrada.readInt();//encontrar el nombre del enemigo 
        
        for (int i = 0; i < jugadores.size(); i++) {
            if(jugadores.get(i).getTurno()==turno){ 
                enemigo = jugadores.get(i).getName();
                break;
            }
        } 
             
        JuegoGato ventanajuego=(JuegoGato) JuegosFactory.crearJuego(JuegosFactory.Games.GATO);
        ventanajuego.setVisible(true);       
        ventanajuego.setTitle(this.name);
        ventanajuego.setDefaultCloseOperation(HIDE_ON_CLOSE);
        ventanajuego.setNumeroJugador(1);
        
        int columna,fila,res;         
        
        while(true){
            
            turnoP1Gato(ventanajuego,enemigo);
                        
            if(ventanajuego.fullTablero()){
                perdida();
                setJuegoActivo(false);
                break;
            }
                                   
            res=entrada.readInt();
            columna=entrada.readInt();
            fila=entrada.readInt();

            if(res==1){
                JOptionPane.showInternalMessageDialog(null, "HAS GANADO", name, 1);
                setJuegoActivo(false);
                break;
            }
            
            turnoP2Gato(ventanajuego,columna,fila);
            
            if(ventanajuego.haGanado()||ventanajuego.fullTablero()){//si gano el enemigo 
               JOptionPane.showInternalMessageDialog(null, "HAS PERDIDO", name, 1);//perdida();
               setJuegoActivo(false);            
               break;         
            }                     
        }
    }
    
    private void turnoP1Gato(JuegoGato ventanajuego,String enemigo) throws IOException{
        
        while(ventanajuego.getColumnaA()==-1){
                System.out.print("");
            }
            salida.writeInt(10);
            salida.writeInt(0);
            salida.writeInt(ventanajuego.getColumnaA());
            salida.writeInt(ventanajuego.getFilaA());
            salida.writeUTF(enemigo);                        
            ventanajuego.setColumnaA(-1);
            ventanajuego.setFilaA(-1);
    }
   
    private void turnoP2Gato(JuegoGato ventanajuego, int columna, int fila) throws IOException{

        ventanajuego.marcar(columna, fila);
        System.out.println("MARQUE PUNTO ENEMIGO ");
        System.out.println(ventanajuego.haGanado());//AQUI SE DICE SI GANO EL ENEMIGO 
    }
    
    private boolean ganadorGato(JuegoGato ventanajuego,String enemigo) throws IOException{
        int columna,fila,res;
        res=entrada.readInt();
        columna=entrada.readInt();
        fila=entrada.readInt();
        ventanajuego.marcar(columna, fila);
        ventanajuego.setColumnaA(-1);
        ventanajuego.setFilaA(-1);

        if(ventanajuego.haGanado()){ //si gano el primer jugador 
            salida.writeInt(10);
            salida.writeInt(1);
            salida.writeInt(ventanajuego.getColumnaA());
            salida.writeInt(ventanajuego.getFilaA());
            salida.writeUTF(enemigo);
            return true;
        }

        return false;    

    }

    private void perdida(){
        JOptionPane.showInternalMessageDialog(null, "HAS PERDIDO", name, 1);
    }
    
    private boolean isDigit(String num){
        for (int i = 0; i < num.length(); i++) {
            if (!Character.isDigit(num.charAt(i))) {
                return false;
            }
        }
        return true;
    
    }
   
//GETTER AND SETTER

    public boolean getJuegoActivo() {
        return juegoActivo;
    }

    public void setJuegoActivo(boolean juegoActivo) {
        this.juegoActivo = juegoActivo;
    }

    public boolean getResultado() {
        return resultado;
    }

    public void setResultado(boolean resultado) {
        this.resultado = resultado;
    }
}