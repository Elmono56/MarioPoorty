/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juegos;

import Personajes.Personajes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Action;
import javax.swing.JButton;

/**
 *
 * @author chave
 */



public class regresarJugador extends javax.swing.JPanel implements ActionListener{
    
    private JButton buttonArray[];
    private Personajes enemigo;
    private boolean getOut=false;
    ArrayList<Personajes> enemigos=null;
    

    public regresarJugador(ArrayList<Personajes> enemigos) {
        initComponents();
        initBoard(enemigos);
    }
    
    private void initBoard(ArrayList<Personajes> enemigos){
        this.enemigos=enemigos;
        
        this.buttonArray=new JButton[this.enemigos.size()];
        
            for (int i = 0; i < buttonArray.length; i++) {
                buttonArray[i] = new JButton((i)+"");
                jPanel1.add(buttonArray[i]);
                buttonArray[i].setBounds((i*90)+3, 3, 70, 70);
                buttonArray[i].setIcon(this.enemigos.get(i).getIcon());
                buttonArray[i].addActionListener(this);
            }
    }
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
    
        for (int i = 0; i < buttonArray.length; i++) {
            if(e.getSource()==buttonArray[i]){
                enemigo=this.enemigos.get(i);
                break;
            }
        }
        getOut=true;
        for (JButton buttonArray1 : buttonArray) {
            buttonArray1.setEnabled(false);
        }
        
    }
    
    public Personajes getSeleccionado(){
        return enemigo;
    }
    
    public boolean getSalida(){
    
        return getOut;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 347, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 176, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables


}
