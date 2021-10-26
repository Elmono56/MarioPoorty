/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author chave
 */
public class Personajes {
    String nombre;
    ImageIcon imagen;
    int numero,casilla;
    boolean inmovil;
    ArrayList<Personajes> personajes =new ArrayList<Personajes>();
   
    public Personajes(int numero){//el nombre e imagenes cargarla del mismo pc
        this.numero=numero;
        this.casilla=0;
        this.inmovil=false;
        imagen=null;//hacer funcion de agragar la imagen desde la carpeta Imagen 
    }
  
    
}


