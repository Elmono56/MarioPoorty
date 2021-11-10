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
    
    static enum Games{
        GATO,
        SOPALETRAS,
        MEMORYPATH,
        SUPERBROSM,
        CATCHTHECAT,
        BOMBERMARIO,
        GUESSWHO,
        COLLECTCOINS,
        MARIOCARDS
    }
    
    
    
    public  static javax.swing.JFrame crearJuego(Games tipo){
        
        switch(tipo){
            case GATO :{
                //Random random = new Random();
                /*
                while (true){
                    
                    int casip2 = random.nextInt(listaPlayers.size());
                    Personajes p2 = listaPlayers.get(casip2);
                    
                    if (p1!=p2){
                        break;
                    }
                    
                }
                */
                
            }
            case COLLECTCOINS: return new CollectCoins();
        }
        return null;
    }
    
    
}
