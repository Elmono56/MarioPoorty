/*
 * JuegoGato.java
 *
 * Created on 2 de diciembre de 2008, 06:36 PM
 */

package GamesFactory;

import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.event.MouseAdapter;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle;

/**
 *
 * @author  hidal
 */
public class JuegoGato extends javax.swing.JFrame {
    
    /** Creates new form JuegoGato */
    
    String amigo;
    String enemigo;
    // cambiar este valor para dimensiones
    public static int DIMENSIONES = 3;
    // Tablero con objetos JButton
    JButton[][] tableroLabels = new JButton[DIMENSIONES][DIMENSIONES];
    // tablero logico, indica el status del boton, si disparado o no
    int[][] tableroLogico = new int[DIMENSIONES][DIMENSIONES];
    // crea imagen blanco
    ImageIcon iconoVacio = new ImageIcon(getClass().getResource("/Imagenes/cvacio.GIF"));
    // crea imagen X
    ImageIcon iconoEquiz = new ImageIcon(getClass().getResource("/Imagenes/cequiz.GIF"));
    // crea la imagen circulo
    ImageIcon iconoCirculo = new ImageIcon(getClass().getResource("/Imagenes/ccirculo.GIF"));
    
    int turnoJugador=1;
    
    //numero de jugador 1 o 2
    int numeroJugador = 0;
    
    int filaA = -1;
    
    int columnaA = -1;
    
    public JuegoGato() {
        // esto es parte del gato
        initComponents();
        jPanel1.setLayout(null);
        generarTablero();
    }

    public String getAmigo() {
        return this.amigo;
    }

    public void setAmigo(String amigo) {
        this.amigo = amigo;
    }
    
    public String getEnemigo() {
        return this.enemigo;
    }

    public void setEnemigo(String enemigo) {
        this.enemigo = enemigo;
    }

    public int getTurnoJugador() {
        return turnoJugador;
    }

    public void setTurnoJugador(int turnoJugador) {
        this.turnoJugador = turnoJugador;
    }
    
    public int getNumeroJugador() {
        return this.numeroJugador;
    }

    public void setNumeroJugador(int numeroJugador) {
        this.numeroJugador = numeroJugador;
    }
    
    
    
    
    
    void generarTablero()
    {
        for(int i=0;i<DIMENSIONES;i++)
        {
            for(int j=0;j<DIMENSIONES;j++)
            {
                // coloca imagen a todos vacio
                tableroLabels[i][j] = new JButton(iconoVacio);
                //añade al panel el boton;
                jPanel1.add(tableroLabels[i][j]);
                // coloca dimensiones y localidad
                tableroLabels[i][j].setBounds(100+50*i, 100+50*j, 50, 50);
                // coloca el comand como i , j 
                tableroLabels[i][j].setActionCommand(i+","+j);//i+","+j
                
                //aclickSobreTablero(evt);ñade el listener al boton
                tableroLabels[i][j].addMouseListener(new MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                        
                    clickSobreTablero(evt);
                    
                }
                });
                // en logico indica estado en disponible
                tableroLogico[i][j]=0;
            }
        }
    }
    
    // este metodo es la respuesta del cliente al clic del enemigo
    public void marcar(int columna, int fila)
    {
        // marca el tablero con num de jugador
        tableroLogico[columna][fila]=turnoJugador;
        // si soy el 1, marco con o que es el 2, sino con X
        // pues es el turno del enemigo que estoy marcando
        if (numeroJugador == 1)
            tableroLabels[columna][fila].setIcon(iconoCirculo);
        else
            tableroLabels[columna][fila].setIcon(iconoEquiz);
            
        // pregunta si gano el enemigo
            if(haGanado())
            {
                JOptionPane.showMessageDialog(null, "Ha ganado el jugador "+turnoJugador);
                
                
            }          
        // este fue el clic del enemigo, marco ahora mi turno
        turnoJugador = numeroJugador;
        jLabel1.setText("Turno del Jugador "+turnoJugador);
        
        
    }
    
    public void clickSobreTablero(java.awt.event.MouseEvent evt)
    {
        // obtiene el boton 
        JButton botonTemp = (JButton)evt.getComponent();
        // obtiene el i,j de action command del boton
        String identificadorBoton = botonTemp.getActionCommand();
        
        // separa el string del action comand para obtener columnas
        columnaA = Integer.parseInt(identificadorBoton.substring(0,identificadorBoton.indexOf(",")));
        
        filaA = Integer.parseInt(identificadorBoton.substring(1+identificadorBoton.indexOf(",")));
        
        // si ya se disparo entonces nada
        if(tableroLogico[columnaA][filaA]!=0)
            return;
        
        // si es mi turno continua, si no return
        if (numeroJugador != turnoJugador)
            return;
        
        if (numeroJugador == 1)
        {
            
            tableroLabels[columnaA][filaA].setIcon(iconoEquiz);
            turnoJugador=2;
        }
        else
        {
            // si era jugador 3, marca circulo y turno jugador 1
            tableroLabels[columnaA][filaA].setIcon(iconoCirculo);
            turnoJugador=1;
        }
        // muestra el turno del jugador
         jLabel1.setText("Turno del Jugador "+turnoJugador);
         
             
    }

    public int getFilaA() {
        return this.filaA;
    }

    public void setFilaA(int filaA) {
        this.filaA = filaA;
    }

    public int getColumnaA() {
        return this.columnaA;
    }

    public void setColumnaA(int columnaA) {
        this.columnaA = columnaA;
    }
    
    
    
    public boolean haGanado()
    {
        
        //Ganó en las filas
        for(int i=0;i<3;i++)
        {
        if ((tableroLogico[i][0]==tableroLogico[i][1])
                &&(tableroLogico[i][1]==tableroLogico[i][2])
                && !(tableroLogico[i][0]==0))
        {
            return true;
        }
        }
        
        //Gano en las columnas
        for(int i=0;i<3;i++)
        {
        if ((tableroLogico[0][i]==tableroLogico[1][i])
                &&(tableroLogico[1][i]==tableroLogico[2][i])
                && !(tableroLogico[0][i]==0))
        {
            return true;
        }
        }
        //Verificar diagonal 1
        if ((tableroLogico[0][0]==tableroLogico[1][1])
                &&(tableroLogico[1][1]==tableroLogico[2][2])
                && !(tableroLogico[0][0]==0))
            return true;
        
        //Verificar diagonal 2
        if ((tableroLogico[2][0]==tableroLogico[1][1])
                &&(tableroLogico[1][1]==tableroLogico[0][2])
                && !(tableroLogico[2][0]==0))
            return true;
        
        return false;
    }
    
    // set el nombre del enemigo
    public void setEnemigoL(String enem)
    {
        lblEnemigo.setText("vs. "+enem);
    }
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblEnemigo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Turno del Jugador: Jugador 1");

        lblEnemigo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblEnemigo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(lblEnemigo, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(166, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblEnemigo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(385, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JuegoGato().setVisible(true);
            }
        });
    }
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblEnemigo;
    // End of variables declaration//GEN-END:variables
    
}
