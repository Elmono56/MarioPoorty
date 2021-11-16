/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GamesFactory;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author hidal
 */
public class CircletheCat extends javax.swing.JFrame {
    
    
    public static final int DIMENSIONES = 13;
    JButton[][] botones = new JButton[DIMENSIONES][DIMENSIONES];
    ImageIcon iconoM = new ImageIcon(getClass().getResource("/Imagenes/tanookimario.PNG"));
    
    

    /**
     * Creates new form CircletheCat
     */
    public CircletheCat() {
        initComponents();
        generarTablero();
        pintarTablero();
    }
    
    
    
    
    
    
    void generarTablero(){
        
        for(int i=0;i<DIMENSIONES;i++)
        {
            for(int j=0;j<DIMENSIONES;j++)
            {
                // coloca imagen a todos vacio
                if(i==6 & j==6){
                    botones[i][j] = new JButton(iconoM);
                }
                else{
                    botones[i][j] = new JButton();
                }
                //añade al panel el boton;
                jPanel1.add(botones[i][j]);
                // coloca dimensiones y localidad
                botones[i][j].setBounds(10+40*i, 10+40*j, 45, 45);
                // coloca el comand como i , j 
                botones[i][j].setActionCommand(i+","+j);//i+","+j
                
                //aclickSobreTablero(evt);ñade el listener al boton
                botones[i][j].addMouseListener(new MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                        
                    colorearBoton(evt);
                    
                }
                });
            }
        }
    }
    
    
    void pintarTablero(){
        
        for(int i=0;i<DIMENSIONES;i++){
            
            for(int j=0;j<DIMENSIONES;j++){
                
                if (i==0 | i==12){
                    botones[i][j].setBackground(Color.BLACK);
                }
                else if (j==0 | j==12){
                    botones[i][j].setBackground(Color.BLACK);
                }
                else{
                    botones[i][j].setBackground(Color.YELLOW);
                }
            }
        }
    }
    
    
    void colorearBoton(java.awt.event.MouseEvent evt){
        
        JButton botonTemp = (JButton)evt.getComponent();
        String identificadorBoton = botonTemp.getActionCommand();
        
        int columna = Integer.parseInt(identificadorBoton.substring(0,identificadorBoton.indexOf(",")));
        int fila = Integer.parseInt(identificadorBoton.substring(1+identificadorBoton.indexOf(",")));
        
        //colorear el boton
        
        if (botonTemp.getBackground()==Color.YELLOW){
            botonTemp.setBackground(Color.GREEN);
            moverGato();
        }
        else{
            
        }
        
    }
    
    
    
    void moverGato(){
        
        JButton botonTemp = null;
        int x=0;
        int y=0;
        
        for(int i=0;i<DIMENSIONES;i++){
            
            for(int j=0;j<DIMENSIONES;j++){
              
                if (botones[i][j].getIcon()!=null){
                    botonTemp = botones[i][j];
                    x = i;
                    y = j;
                    break;
                }
            }
        }
        
        
        if (DIMENSIONES-x<=6){
            
            moverDerecha(x,y);
            
        }
        else if(DIMENSIONES-x>6){

            botonTemp.setIcon(null);
            botones[x-1][y].setIcon(iconoM);

        
        }
        /*
        else if (DIMENSIONES-y<=6){

            botonTemp.setIcon(null);
            botones[x][y-1].setIcon(iconoM);

        }
        else if (DIMENSIONES-y>6){

            botonTemp.setIcon(null);
            botones[x][y+1].setIcon(iconoM);

        }
        */
            
        
        
        if (botones[x-1][y].getBackground()==Color.GREEN &
        botones[x+1][y].getBackground()==Color.GREEN &
        botones[x][y-1].getBackground()==Color.GREEN &
        botones[x][y+1].getBackground()==Color.GREEN){
            
            JOptionPane.showMessageDialog(this, "GANASTE","FELICIDADES", JOptionPane.INFORMATION_MESSAGE);
            
        }
        
    }
        
    
    boolean analizarPerdedor(int x, int y){
        if(botones[x][y].getBackground()==Color.BLACK){
                 JOptionPane.showMessageDialog(this, "PERDISTE","Error", JOptionPane.ERROR_MESSAGE);
                 return true;
        }
        return false;
    }
    
    boolean validarMovimiento(int x, int y){
        
        return (botones[x][y].getBackground()!=Color.GREEN);
        
        
    }
    
    
    void moverDerecha(int x, int y){
        
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 577, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 539, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CircletheCat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CircletheCat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CircletheCat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CircletheCat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CircletheCat().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
