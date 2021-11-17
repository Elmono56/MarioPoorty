
package MarioThreads;

import GamesFactory.SopaLetras;
import static java.lang.Thread.sleep;
import javax.swing.JFrame;

/**
 *
* @author Andres Chaves y Pablo Hidalgo
*/

public class CronoThreadSopa extends Thread{
    private SopaLetras refPantallaCC;
    private boolean isRunning = true;
    private int seconds = 0;
    private int minutes = 0;

    public CronoThreadSopa(JFrame refPantalla) {
        this.seconds =59;
        this.minutes =01;
        
        try{
            this.refPantallaCC = (SopaLetras) refPantalla;
        }
        catch(Exception ex){

        }
    }
    
    public void run(){
        
        while(isRunning){
            
            try {
                
                
                seconds--;
                if(seconds == 0 & minutes>0){
                    minutes--;
                    seconds=59;
                }
                
                
                String newTime = setNiceFormat(minutes) + ":" + setNiceFormat(seconds); 
                refPantallaCC.setTextToCrono(newTime);
              
                
                if (minutes == 0 & seconds == 0){
                    //FINAL TIEMPO
                    isRunning = false;
                    break;
                }
                
               
                sleep(1000);
                
            } catch (InterruptedException ex) {
                
            }
        } 
    }
    
    private String setNiceFormat(int number){
        if (number < 10)
            return "0" + number;
        return "" + number;
    }
    
    public void setIsRunning(boolean estado){
        this.isRunning = estado;
    }

    public String getSeconds() {
        return ""+seconds;
    }

    public String getMinutes() {
        return ""+minutes;
    }
    
    public int getIntSeconds() {
        return seconds;
    }

    public int getIntMinutes() {
        return minutes;
    }
    
    public void setSegundos(int seg){
        this.seconds = seg;
    }
    
    public void setMinutos(int min){
        this.minutes = min;
    }
    
}
