/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juegos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Action;
import javax.swing.JButton;

/**
 *
* @author Andres Chaves y Pablo Hidalgo
*/
public class Cola extends javax.swing.JPanel implements  ActionListener{

    private JButton buttonArray[];
    private boolean getOut=false;
    private int resultado;
    ArrayList<Integer> casillas=null;

    public Cola(ArrayList<Integer> casillas) {
        initComponents();
        initBoard(casillas);
    }
    
    
    private void initBoard(ArrayList<Integer> casillas){
        this.casillas=casillas;
        
        this.buttonArray=new JButton[this.casillas.size()];
        
            for (int i = 0; i < buttonArray.length; i++) {
                buttonArray[i] = new JButton((i)+"");
                jPanel1.add(buttonArray[i]);
                buttonArray[i].setBounds((i*70)+3, 3, 70, 70);
                buttonArray[i].setText(this.casillas.get(i)+"");
                buttonArray[i].addActionListener(this);
            }
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
    
        for (int i = 0; i < buttonArray.length; i++) {
            if(e.getSource()==buttonArray[i]){
                resultado= Integer.parseInt(buttonArray[i].getText());
                break;
            }
        }
        getOut=true;
        for (JButton buttonArray1 : buttonArray) {
            buttonArray1.setEnabled(false);
        }
        
    }

    public int getSeleccionado(){
        return resultado;
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
            .addGap(0, 503, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 155, Short.MAX_VALUE)
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
