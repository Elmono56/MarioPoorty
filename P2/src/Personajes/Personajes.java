
package Personajes;
import javax.swing.ImageIcon;
import java.io.Serializable;
import javax.swing.JButton;

/**
 *
 * @author chave
 */

public class Personajes implements Serializable{
        boolean inmovil;
        String nombre;
        private JButton refButton;
        int numero,casilla,turno,cantInmovil;
        ImageIcon imagen;
        
    
    public Personajes(int numero,String nombre){
        inmovil=false;
        this.nombre=nombre;
        this.numero=numero;// ver en que utilizarlo 
        this.casilla=0;
        this.turno=0;
        this.cantInmovil=0;
        this.refButton=null;
        setImage(numero);
    }
    
    
    //metodos
    
     public void avanzarCasillaActual(int avance) {
        this.casilla += avance;
    }  
    
    
    
//getter
    public int getNum(){
    
        return this.numero;
    }
    
    public String getName(){
    
    return this.nombre;
    }
    public ImageIcon getIcon(){
    
        return this.imagen;
    }

    public int getTurno() {
        return turno;
    }
     public int getCasillaActual() {
        return casilla;
    }

    public JButton getRefButton() {
        return refButton;
    }

    public boolean getInmovil() {
        return inmovil;
    }

    public int getCantInmovil() {
        return cantInmovil;
    }
    
    
    
    
//setter
    
    public void setCantInmovil(int cantInmovil) {
        this.cantInmovil = cantInmovil;
    }


    public void setInmovil(boolean inmovil) {
        this.inmovil = inmovil;
    }

    public void setRefButton(JButton refButton) {
        this.refButton = refButton;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public void setCasillaActual(int casillaActual) {
        this.casilla= casillaActual;
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
