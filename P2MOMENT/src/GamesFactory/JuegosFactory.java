/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GamesFactory;

import Personajes.Personajes;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author hidal
 */
public class JuegosFactory {
    
    
    public static enum Games{
        GATO,
        SOPALETRAS,
        MEMORYPATH,
        SUPERBROSM,
        CATCHTHECAT,
        BOMBERMARIO,
        GUESSWHO,
        COINS,
        MARIOCARDS
    }
    
    
    
    public static javax.swing.JFrame crearJuego(Games tipo){
        
        switch(tipo){
            case GATO :{
                return new JuegoGato();
            }
            case COINS: {
                return new CollectCoins();
            }
        }
        return null;
    }
    
    
}
