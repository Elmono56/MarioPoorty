/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;
import java.util.ArrayList;
/**
 *
 * @author chave
 */
public class EscogerPersonajes extends javax.swing.JPanel {
    
    private ArrayList<Integer> personajes;
    private int out=0;
    private int seleccionado;
    
    
    public EscogerPersonajes(){
        initComponents();
    }
    public EscogerPersonajes(ArrayList<Integer> personajes) {
        initComponents();   
        this.personajes=personajes;   
        initComponentes();
    }




    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Btn0 = new javax.swing.JButton();
        Btn1 = new javax.swing.JButton();
        Btn6 = new javax.swing.JButton();
        Btn7 = new javax.swing.JButton();
        Btn2 = new javax.swing.JButton();
        Btn3 = new javax.swing.JButton();
        Btn8 = new javax.swing.JButton();
        Btn4 = new javax.swing.JButton();
        Btn9 = new javax.swing.JButton();
        Btn5 = new javax.swing.JButton();

        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        Btn0.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/peach.png"))); // NOI18N
        Btn0.setText("0");
        Btn0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn0ActionPerformed(evt);
            }
        });

        Btn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/browser.jpg"))); // NOI18N
        Btn1.setText("1");
        Btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn1ActionPerformed(evt);
            }
        });

        Btn6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/wario.jpg"))); // NOI18N
        Btn6.setText("6");
        Btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn6ActionPerformed(evt);
            }
        });

        Btn7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Yoshi.png"))); // NOI18N
        Btn7.setText("7");
        Btn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn7ActionPerformed(evt);
            }
        });

        Btn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/koopa.png"))); // NOI18N
        Btn2.setText("2");
        Btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn2ActionPerformed(evt);
            }
        });

        Btn3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/mario.jpg"))); // NOI18N
        Btn3.setText("3");
        Btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn3ActionPerformed(evt);
            }
        });

        Btn8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/skeleton.jpg"))); // NOI18N
        Btn8.setText("8");
        Btn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn8ActionPerformed(evt);
            }
        });

        Btn4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Toad.jpg"))); // NOI18N
        Btn4.setText("4");
        Btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn4ActionPerformed(evt);
            }
        });

        Btn9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/luigi.png"))); // NOI18N
        Btn9.setText("9");
        Btn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn9ActionPerformed(evt);
            }
        });

        Btn5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/miniKong.png"))); // NOI18N
        Btn5.setText("5");
        Btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Btn6, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Btn7, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(Btn8, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Btn9, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Btn0, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Btn5, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Btn5, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Btn6, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Btn8, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Btn9, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Btn0, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Btn7, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn1ActionPerformed
        
        actualizar(1);
        this.Btn1.setVisible(false);
    }//GEN-LAST:event_Btn1ActionPerformed

    private void Btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn2ActionPerformed
        actualizar(2);
        this.Btn2.setVisible(false);
    }//GEN-LAST:event_Btn2ActionPerformed

    private void Btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn3ActionPerformed
        actualizar(3);
        this.Btn3.setVisible(false);

    }//GEN-LAST:event_Btn3ActionPerformed

    private void Btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn4ActionPerformed
        actualizar(4);
        this.Btn4.setVisible(false);    }//GEN-LAST:event_Btn4ActionPerformed

    private void Btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn5ActionPerformed
        actualizar(5);
        this.Btn5.setVisible(false);
    }//GEN-LAST:event_Btn5ActionPerformed

    private void Btn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn6ActionPerformed
        actualizar(6);
        this.Btn6.setVisible(false);
    }//GEN-LAST:event_Btn6ActionPerformed

    private void Btn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn7ActionPerformed
        actualizar(7);
        this.Btn7.setVisible(false);
    }//GEN-LAST:event_Btn7ActionPerformed

    private void Btn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn8ActionPerformed
        actualizar(8);
        this.Btn8.setVisible(false);
    }//GEN-LAST:event_Btn8ActionPerformed

    private void Btn9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn9ActionPerformed
        actualizar(9);
        this.Btn9.setVisible(false);
    }//GEN-LAST:event_Btn9ActionPerformed

    private void Btn0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn0ActionPerformed
        actualizar(0);
        this.Btn0.setVisible(false);
    }//GEN-LAST:event_Btn0ActionPerformed
  
    private void initComponentes(){
    
        this.Btn0.setVisible(false);
        this.Btn1.setVisible(false);
        this.Btn2.setVisible(false);
        this.Btn3.setVisible(false);
        this.Btn4.setVisible(false);
        this.Btn5.setVisible(false);
        this.Btn6.setVisible(false);
        this.Btn7.setVisible(false);
        this.Btn8.setVisible(false);
        this.Btn9.setVisible(false);
       
        for (int i = 0; i < this.personajes.size(); i++) {
            //int opcion=this.personajes.get(i).getNum();
            int opcion=this.personajes.get(i); //.value puede ser 
            switch(opcion){
                case 0: Btn0.setVisible(true);break;
                case 1: Btn1.setVisible(true);break;
                case 2: Btn2.setVisible(true);break;
                case 3: Btn3.setVisible(true);break;
                case 4: Btn4.setVisible(true);break;
                case 5: Btn5.setVisible(true);break;
                case 6: Btn6.setVisible(true);break;
                case 7: Btn7.setVisible(true);break;
                case 8: Btn8.setVisible(true);break;
                case 9: Btn9.setVisible(true);break;
            
            }
            
        }
    
    }

    private void actualizar(int num){
        for (int i = 0; i < personajes.size(); i++) {
            //if(personajes.get(i).getNum()==num){ //.value pude ser 
            if(personajes.get(i)==num){
                personajes.remove(i);
                break;}
        }
        this.setOut(1);
        this.seleccionado=num;
        
        this.Btn0.setEnabled(false);
        this.Btn1.setEnabled(false);
        this.Btn2.setEnabled(false);
        this.Btn3.setEnabled(false);
        this.Btn4.setEnabled(false);
        this.Btn5.setEnabled(false);
        this.Btn6.setEnabled(false);
        this.Btn7.setEnabled(false);
        this.Btn8.setEnabled(false);
        this.Btn9.setEnabled(false);
        
        
        
        
        
    }
    
    
//    public ArrayList<Personajes> getPersonajes(){
//
//        return this.personajes;
//
//    }
      public ArrayList<Integer> getPersonajes(){

        return this.personajes;

    }

    public int getOut() {
        return this.out;
    }
    
    public int getSeleccionado() {
        return this.seleccionado;
    }

    public void setOut(int out) {
        this.out = out;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn0;
    private javax.swing.JButton Btn1;
    private javax.swing.JButton Btn2;
    private javax.swing.JButton Btn3;
    private javax.swing.JButton Btn4;
    private javax.swing.JButton Btn5;
    private javax.swing.JButton Btn6;
    private javax.swing.JButton Btn7;
    private javax.swing.JButton Btn8;
    private javax.swing.JButton Btn9;
    // End of variables declaration//GEN-END:variables
}
