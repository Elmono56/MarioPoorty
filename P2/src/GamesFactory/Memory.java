/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GamesFactory;

import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author chave
 */
public class Memory extends javax.swing.JFrame {


    private static int col=3;
    private static int fila=6;
    private int contador=0,k=0,l=0,parejas=0,intentos=9;
    private int cartasIguales,turnoJuego=1;
    private int miTurno;
    private boolean listo;
    private JButton[][] tableroLabels= new JButton[fila][col];
    private int math [][]= new int[fila][col];
    Random r= new Random();

    public Memory() {
        initComponents();
        initMatrizLogica();
        initMatrizImagenes();
        eventoCartas();
    }

    private void initMatrizLogica(){
        System.out.println("entre");
        
        int acumulador=0;
        for (int i = 0; i < fila; i++){
            for (int j = 0; j < col; j++){ 
                math[i][j]=0;}
        }

        
        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < col; j++) {
                this.math[i][j]= r.nextInt(9)+1;
                do{
                    acumulador=0;
                    for (int k = 0; k < fila; k++) {
                        for (int l = 0; l < col; l++) {
                            if(this.math[i][j]==this.math[k][l]){
                                acumulador+=1;
                            }

                        }

                    }
                    if(acumulador==3){
                        this.math[i][j]= r.nextInt(9)+1;

                    }
                } while(acumulador == 3);
            }
        }
        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(this.math[i][j]+" ");
            }
            System.out.println(" ");
        }
        
    }
    
    private void initMatrizImagenes(){
    
        for (int i = 0; i < fila; i++){
            for (int j = 0; j < col; j++){ 
                this.tableroLabels[i][j]=new JButton();
                this.tableroLabels[i][j].setIcon(new ImageIcon(getClass().getResource("/Imagenes/signoPregunta.jpg")));
                //poner imagenes con numeros
                this.tableroLabels[i][j].setBounds(70*i+3, 70*j+3, 70, 70);
                this.tableroLabels[i][j].setVisible(true);
                jPanel1.add(tableroLabels[i][j]);
            }
        }
        LabelParejas.setText(parejas+"");
        jLabelTurno.setText(turnoJuego+"");
        JLabelIntentos.setText(intentos+"");
    
    
    
    }
    
    
    private void eventoCartas(){
        for (int i = 0; i < fila; i++){
            for (int j = 0; j < col; j++){ 
                tableroLabels[i][j].addMouseListener(new MouseAdapter() {
                    
                    public void mousePressed(MouseEvent e){
                        if(turnoJuego==miTurno){
                            for (int k = 0; k < fila; k++){
                                 for (int l = 0; l < col; l++){ 
                                     if(e.getSource()==tableroLabels[k][l]){
                                        
                                         if(getContador()==0){
                                            setListo(false);
                                            cartaGuardada(conocerValor(k, l));
                                            setK(k);
                                            setL(l);
                                        }
                                        tableroLabels[k][l].setIcon(new ImageIcon(getClass()
                                                .getResource("/Imagenes/"+conocerValor(k, l)+".jpg")));
                                        
                                        
                                        
                                        if(getContador()==1){ 
                                            if(mismo(tableroLabels[k][l],tableroLabels[getK()][getL()])){
                                                actualizarIntentos();
                                                return;
                                            }
                                            if(getCartaGuardada()==conocerValor(k, l)){
                                                tableroLabels[k][l].setVisible(false);
                                                tableroLabels[getK()][getL()].setVisible(false);
                                                aumentarParejas();
                                                LabelParejas.setText(parejas+"");
                                            }
                                        }
                                        sumarContador();

                                        if(getContador()==2){
                                            actualizarIntentos();
                                            return;
                                            
                                        }

                                     }
                                 }
                            }

                        }
                    
                    }    

                });
                
            }
        }
    
    
    
    
    }
    
    
    private void voltearCartas(){
    
        for (int i = 0; i < fila; i++){
            for (int j = 0; j < col; j++){ 
                tableroLabels[i][j].setIcon(new ImageIcon(getClass().getResource("/Imagenes/signoPregunta.jpg")));
            }
        }
    }
    
    private boolean mismo(JButton jButton, JButton jButton0) {
        

        if((jButton.getX()==jButton0.getX())&&(jButton.getY()==jButton0.getY()))return true;
        return false;
    
    }
    
    //getter and setter
    public int getContador() {
        return contador;
    }
    private  void setK(int K){
        this.k=K;
    }
    private  void setL(int L){
        this.l=L;
    }
    private  int getK(){
        return this.k;
    }
    private int getL(){
        return this.l;
    }

    public void setTurnoJuego(int turnoJuego) {
        this.turnoJuego = turnoJuego;
        jLabelTurno.setText(this.turnoJuego+"");
    }

    public void actualizarIntentos() {
        setListo(true);
        voltearCartas();
        setContador(0);
        this.intentos--;
        JLabelIntentos.setText(this.intentos+"");
    }

    public int getIntentos() {
        return this.intentos;
    }
    
    
    
    public void setMiTurno(int miTurno) {
        this.miTurno = miTurno;
    }

    public int getParejas() {
        return parejas;
    }
    
  
    public void aumentarParejas(){
        this.parejas++;
    }
    
    public void setContador(int contador) {
        this.contador = contador;
    }
    public void sumarContador() {
        this.contador++;
    }

    public int getCartasIguales() {
        return cartasIguales;
    }

    public void cartaGuardada(int cartasIguales) {
        this.cartasIguales = cartasIguales;
    }
    public int getCartaGuardada(){
        return this.cartasIguales;
    }
    
    public int conocerValor(int i,int j){
    
        return this.math[i][j];
    }

    public boolean getListo() {
        return this.listo;
    }

    public void setListo(boolean listo) {
        this.listo = listo;
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        LabelParejas = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabelTurno = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        JLabelIntentos = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 427, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        LabelParejas.setFont(new java.awt.Font("Tempus Sans ITC", 1, 24)); // NOI18N
        LabelParejas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        LabelParejas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel1.setFont(new java.awt.Font("Sitka Text", 0, 18)); // NOI18N
        jLabel1.setText("TURNO");

        jLabelTurno.setFont(new java.awt.Font("Sitka Display", 0, 18)); // NOI18N
        jLabelTurno.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTurno.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jLabelTurno.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel2.setText("INTENTOS");

        JLabelIntentos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LabelParejas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                            .addComponent(jLabelTurno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JLabelIntentos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JLabelIntentos, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(LabelParejas, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            java.util.logging.Logger.getLogger(Memory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Memory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Memory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Memory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Memory().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JLabelIntentos;
    private javax.swing.JLabel LabelParejas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelTurno;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
