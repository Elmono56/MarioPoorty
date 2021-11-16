/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//actualizado


package GamesFactory;

import java.awt.event.MouseAdapter;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author hidal
 */
public class GuessWho extends javax.swing.JFrame {

    /**
     * Creates new form GuessWho
     */
    
    
    public static final int DIMENSIONES = 10;
    public static final int CANTBOTONES = 15;
    int intentos;
    JButton[][] botones = new JButton[DIMENSIONES][DIMENSIONES];
    JButton[] opciones = new JButton[CANTBOTONES];
    int[][] valoresM = new int[DIMENSIONES][DIMENSIONES];
    ImageIcon iconoM = new ImageIcon(getClass().getResource("/Imagenes/question4.JPG"));
    int indicef = 0;
    private boolean activo;
    
    
    
    static enum NOMBRES{
        MARIO,
        LUIGI,
        PEACH,
        FLYGREENT,
        BOO,
        KINGBOO,
        TOAD,
        BOWSER,
        YOSHI,
        WALUIGI,
        GOOMBAS,
        FLORUGA,
        MINIKONG,
        SKELETON,
        WARIO
    }
    
    public GuessWho() {
        initComponents();
        setFoto();
        generartablero();
        generarbotones();
        generarIntentos();
        this.activo=true;
    }

    
    void generartablero(){
        
        for(int i=0;i<DIMENSIONES;i++)
        {
            for(int j=0;j<DIMENSIONES;j++)
            {
                // coloca imagen a todos vacio
                botones[i][j] = new JButton(iconoM);
                //añade al panel el boton;
                jPFoto.add(botones[i][j]);
                // coloca dimensiones y localidad
                botones[i][j].setBounds(5+62*i, 5+62*j, 60, 60);
                // coloca el comand como i , j 
                botones[i][j].setActionCommand(i+","+j);//i+","+j
                
                //aclickSobreTablero(evt);ñade el listener al boton
                botones[i][j].addMouseListener(new MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                        
                    setTransparente(evt);
                    
                }
                });
                
            }
        }
        
    }
    
     void generarbotones(){
        
        for(int i=0;i<CANTBOTONES;i++)
        {
            // coloca imagen a todos vacio
            opciones[i] = new JButton();
            //añade al panel el boton;
            jPBotones.add(opciones[i]);
            // coloca dimensiones y localidad
            opciones[i].setBounds(10, 80+35*i, 150, 30);
            // coloca el comand como i , j 
            opciones[i].setActionCommand(10+","+i);//i+","+j

            //aclickSobreTablero(evt);ñade el listener al boton
            opciones[i].addMouseListener(new MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {

                acertar(evt);

            }
            });
            
            switch(i){
            
            case 0: {
                opciones[i].setText("MARIO");
                break;
            }
            case 1: {
                opciones[i].setText("LUIGI");
                break;
            }
            case 2: {
                opciones[i].setText("PEACH");
                break;
            }
            case 3: {
                opciones[i].setText("FLYING GREEN TURTLE");
                break;
            }
            case 4: {
                opciones[i].setText("BOO");
                break;
            }
            case 5: {
                opciones[i].setText("KING BOO");
                break;
            }
            case 6: {
                opciones[i].setText("TOAD");
                break;
            }
            case 7: {
                opciones[i].setText("BOWSER");
                break;
            }
            case 8: {
                opciones[i].setText("YOSHI");
                break;
            }
            case 9: {
                opciones[i].setText("WALUIGI");
                break;
            }
            case 10: {
                opciones[i].setText("GOOMBAS");
                break;
            }
            case 11: {
                opciones[i].setText("FLORUGA");
                break;
            }
            case 12: {
                opciones[i].setText("DIDDY KONG");
                break;
            }
            case 13: {
                opciones[i].setText("SKELETON");
                break;
            }
            case 14: {
                opciones[i].setText("WARIO");
                break;
            }
            }

        }
        
    }
    
   void setTransparente(java.awt.event.MouseEvent evt){
        
        if (intentos!=0){
            
            JButton botonTemp = (JButton)evt.getComponent();
            String identificadorBoton = botonTemp.getActionCommand();

            int columna = Integer.parseInt(identificadorBoton.substring(0,identificadorBoton.indexOf(",")));
            int fila = Integer.parseInt(identificadorBoton.substring(1+identificadorBoton.indexOf(",")));

            botones[columna][fila].disable();
            
            botones[columna][fila].setIcon(null);
            
            botones[columna][fila].setOpaque(false);
            
            botones[columna][fila].setContentAreaFilled(false);
            
            botones[columna][fila].setBorderPainted(false);
            
            intentos--;
            
            lblIntentos.setText("Intentos: " + intentos);
            
        }
        
    }
    
    
    
    void generarIntentos(){
        
        Random aux = new Random();
        
        intentos = (int) aux.nextInt(5) + 4;
        
       lblIntentos.setText("Intentos: " + intentos);
       
    }
    
    
void setFoto(){
        
        Random rant = new Random();
        
        indicef = (int) rant.nextInt(CANTBOTONES);
        
        System.out.println(indicef);
        
        switch(indicef){
            
            case 0:{
                jPFoto.insertIcon(new ImageIcon(getClass().getResource("/Imagenes/marioGW.PNG")));
                break;
            }
            case 1:{
                jPFoto.insertIcon(new ImageIcon(getClass().getResource("/Imagenes/luigiGW.PNG")));
                break;
            }
            case 2:{
                jPFoto.insertIcon(new ImageIcon(getClass().getResource("/Imagenes/peachGW.PNG")));
                break;
            }
            case 3:{
                jPFoto.insertIcon(new ImageIcon(getClass().getResource("/Imagenes/greenflyingturtleGW.PNG")));
                break;
            }
            case 4:{
                jPFoto.insertIcon(new ImageIcon(getClass().getResource("/Imagenes/booGW.PNG")));
                break;
            }
            case 5:{
                jPFoto.insertIcon(new ImageIcon(getClass().getResource("/Imagenes/kingbooGW.PNG")));
                break;
            }
            case 6:{
                jPFoto.insertIcon(new ImageIcon(getClass().getResource("/Imagenes/toadGW.PNG")));
                break;
            }
            case 7:{
                jPFoto.insertIcon(new ImageIcon(getClass().getResource("/Imagenes/bowserGW.PNG")));
                break;
            }
            case 8:{
                jPFoto.insertIcon(new ImageIcon(getClass().getResource("/Imagenes/yoshiGW.PNG")));
                break;
            }
            case 9:{
                jPFoto.insertIcon(new ImageIcon(getClass().getResource("/Imagenes/waluigiGW.PNG")));
                break;
            }
            case 10:{
                jPFoto.insertIcon(new ImageIcon(getClass().getResource("/Imagenes/goombaGW.PNG")));
                break;
            }
            case 11:{
                jPFoto.insertIcon(new ImageIcon(getClass().getResource("/Imagenes/florugaGW.PNG")));
                break;
            }
            case 12:{
                jPFoto.insertIcon(new ImageIcon(getClass().getResource("/Imagenes/diddykongGW.PNG")));
                break;
            }
            case 13:{
                jPFoto.insertIcon(new ImageIcon(getClass().getResource("/Imagenes/skeletonGW.PNG")));
                break;
            }
            case 14:{
                jPFoto.insertIcon(new ImageIcon(getClass().getResource("/Imagenes/warioGW.PNG")));
                break;
            }
        }   
    }
    
    
    
    void acertar(java.awt.event.MouseEvent evt){
        
        JButton botonTemp = (JButton)evt.getComponent();
        String identificadorBoton = botonTemp.getActionCommand();
        int fila = Integer.parseInt(identificadorBoton.substring(1+identificadorBoton.indexOf(",")));
        
        if (fila == indicef){
            JOptionPane.showMessageDialog(this, "GANASTE","FELICIDADES", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(this, "PERDISTE","Error", JOptionPane.ERROR_MESSAGE);
        }
        
        this.activo=false;
        this.setVisible(false);
        
        
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPBotones = new javax.swing.JPanel();
        lblIntentos = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPFoto = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPBotones.setBackground(new java.awt.Color(204, 204, 255));

        lblIntentos.setFont(new java.awt.Font("Rockwell", 0, 24)); // NOI18N
        lblIntentos.setText("Intentos: -");

        javax.swing.GroupLayout jPBotonesLayout = new javax.swing.GroupLayout(jPBotones);
        jPBotones.setLayout(jPBotonesLayout);
        jPBotonesLayout.setHorizontalGroup(
            jPBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPBotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblIntentos, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPBotonesLayout.setVerticalGroup(
            jPBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPBotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblIntentos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(592, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPFoto);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 628, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
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
            java.util.logging.Logger.getLogger(GuessWho.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GuessWho.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GuessWho.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GuessWho.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GuessWho().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPBotones;
    private javax.swing.JTextPane jPFoto;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblIntentos;
    // End of variables declaration//GEN-END:variables
}
