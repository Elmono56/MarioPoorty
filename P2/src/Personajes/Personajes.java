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

public class Personajes implements Serializable{
        boolean inmovil;
        int numero,casilla;
        ImageIcon imagen;
   
    
    public Personajes(int numero){//el nombre e imagenes cargarla del mismo pc
        inmovil=false;
        this.numero=numero;//se podría eliminar
        this.casilla=-1;
        setImage(numero);//hacer funcion de agragar la imagen desde la carpeta Imagen con el numero 
    }
    
    public int getNum(){
    
        return this.numero;
    }
    
    public ImageIcon getIcon(){
    
        return this.imagen;
    }
    
    
    private void setImage(int num){
        switch(num)
        {
            case 1 -> this.imagen=new ImageIcon(getClass().getResource("/Imagenes/browser.jpg"));
            case 2 -> this.imagen=new ImageIcon(getClass().getResource("/Imagenes/koopa.png"));
            case 3 -> this.imagen=new ImageIcon(getClass().getResource("/Imagenes/mario.jpg"));
            case 4 -> this.imagen=new ImageIcon(getClass().getResource("/Imagenes/Toad.jpg"));
            case 5 -> this.imagen=new ImageIcon(getClass().getResource("/Imagenes/miniKong.png"));
            case 6 -> this.imagen=new ImageIcon(getClass().getResource("/Imagenes/wario.jpg"));
            case 7 -> this.imagen=new ImageIcon(getClass().getResource("/Imagenes/Yoshi.png"));
            case 8 -> this.imagen=new ImageIcon(getClass().getResource("/Imagenes/skeleton.jpg"));
            case 9 -> this.imagen=new ImageIcon(getClass().getResource("/Imagenes/luigi.png"));
            case 0 -> this.imagen=new ImageIcon(getClass().getResource("/Imagenes/peach.png"));

        }
    }
    
    
}
