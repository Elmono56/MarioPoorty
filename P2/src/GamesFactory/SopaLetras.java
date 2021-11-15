
package GamesFactory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author chave
 */
public class SopaLetras extends javax.swing.JFrame {
    
    private int size[] = new int[3];
    private static int tam1 = 10;
    private static int tam2 = 15;
    private static int tam3 = 20;
    private int tamMatriz,ajuste;
    JButton[][] tableroLabels;
    private int [][] tableroDireccion;
    private int [][] tableroPalabra;
    private float letra=10;
    private Random r= new Random();
    
    public SopaLetras() {
        initComponents();
        initTablero();
        ponerPalabras();
        verDireccion();
    }

    private void initTablero(){
    size[0]=tam1;
    size[1]=tam2;
    size[2]=tam3;
    tamMatriz=10;//size[r.nextInt(3)];
    tableroLabels= new JButton[tamMatriz][tamMatriz];
    tableroDireccion= new int[tamMatriz][tamMatriz];
    tableroPalabra= new int[tamMatriz][tamMatriz];
    ajuste=ajustarBotones(tamMatriz);
    for(int i=0;i<tamMatriz;i++){
        for(int j=0;j<tamMatriz;j++){
            tableroLabels[i][j] = new JButton();
            tableroLabels[i][j].setText("A");
            tableroLabels[i][j].setFont(tableroLabels[i][j].getFont().deriveFont(letra));
            PanelP.add(tableroLabels[i][j]);
            tableroLabels[i][j].setBounds(ajuste*i, ajuste*j,ajuste,ajuste);
            }
        }
    }
    
    
    
    private int ajustarBotones(int tamMatriz) {
        if (tamMatriz==10)return 70; 
        if (tamMatriz==15)return 45; 
        if (tamMatriz==20)return 35;
        return 0;
    }
    
    private void verDireccion(){
    for(int i=0;i<tamMatriz;i++){
        for(int j=0;j<tamMatriz;j++){
            System.out.print(tableroDireccion[i][i]);
        }
        System.out.println(" ");
    } 
    
    
    }
    
    
    private void ponerPalabras(){
        
        String palabra="hermano";
        
        for (int i = 0; i < palabra.length(); i++) {//diagonal
            //k fila
            //j col
            for(int j=0;j<tamMatriz;j++){
                for(int k=0;k<tamMatriz;k++){
                    tableroLabels[i][i].setText(""+palabra.charAt(i));
                    tableroDireccion[k][j]=1;
                    //break;
                }
//                    tableroPalabra[k][j]=1;
            //break;
            }
            
            
        }
        
        
//        for (int i = 0; i < palabra.length(); i++) {//horizontal
//            tableroLabels[6][3+i].setText(""+palabra.charAt(i));
//            
//        }
//        
//        for (int i = 0; i < palabra.length(); i++) {//vertical
//            tableroLabels[2+i][3].setText(""+palabra.charAt(i));
//            
//        }
//        
//        
    
    }
    
    
    
    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelP = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PanelP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        javax.swing.GroupLayout PanelPLayout = new javax.swing.GroupLayout(PanelP);
        PanelP.setLayout(PanelPLayout);
        PanelPLayout.setHorizontalGroup(
            PanelPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 714, Short.MAX_VALUE)
        );
        PanelPLayout.setVerticalGroup(
            PanelPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 691, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(153, 51, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 168, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 463, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PanelP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
            java.util.logging.Logger.getLogger(SopaLetras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SopaLetras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SopaLetras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SopaLetras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SopaLetras().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelP;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables


}
