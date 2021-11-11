/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MarioThreads;

import GamesFactory.CollectCoins;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andres chaves y pablo hidalgo
 */

public class CronoThread extends Thread{
    private CollectCoins refPantallaCC;
    //private javax.swing.JFrame refPantallaSP;
    private boolean isRunning = true;
    private int seconds = 0;
    private int minutes = 0;

    public CronoThread(javax.swing.JFrame refPantalla, int segundos, int minutos) {
        this.seconds = segundos;
        this.minutes = minutos;
        
        try{
            this.refPantallaCC = (CollectCoins) refPantalla;
        }
        catch(Exception ex){
            /*
            try{
                this.refPantalla = (SopaLetras) refPantalla;
            }
            catch (Exception ext){
            }
             */
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
