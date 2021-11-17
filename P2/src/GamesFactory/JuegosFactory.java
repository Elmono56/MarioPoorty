
package GamesFactory;

/**
 *
* @author Andres Chaves y Pablo Hidalgo
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
            case GUESSWHO:{
                return new GuessWho();
            }
            case MEMORYPATH:{
                return new MemoryPath();
            }
            case SUPERBROSM:{
                return new Memory();
            }
            case SOPALETRAS:{
                return new SopaLetras();
            }
            case MARIOCARDS:{
                return new MarioCards();
            }
        }
        return null;
    }
    
    
}
