/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personajes;
import javax.swing.ImageIcon;
import java.io.Serializable;
/**
 *
 * @author chave
 */

//hacer enum con todas las imagenes y asignarlas

public class Personajes implements Serializable{
        boolean inmovil;
        int numero,casilla;
        ImageIcon imagen;
   
    
    public Personajes(int numero){//el nombre e imagenes cargarla del mismo pc
        inmovil=false;
        this.numero=numero;
        this.casilla=-1;
        ImageIcon imagen=null;//hacer funcion de agragar la imagen desde la carpeta Imagen con el numero 
    }
    
    public int getNum(){
    
        return this.numero;
    }
    
    
}
