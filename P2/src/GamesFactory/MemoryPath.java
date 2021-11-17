
package GamesFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
/**
 *
* @author Andres Chaves y Pablo Hidalgo
*/
public class MemoryPath extends javax.swing.JFrame implements ActionListener {

    public MemoryPath() {
        initComponents();
        generarTablero();
    }
    public static final int fila =3 ;
    public static final int col =6 ;
    public static final int size =70 ;
    private boolean activo=true;
    private int filas=5;
    JButton[][] botones = new JButton[fila][col];
    ImageIcon inicio = new ImageIcon(getClass().getResource("/Imagenes/signoPregunta.jpg"));
    ImageIcon bueno = new ImageIcon(getClass().getResource("/Imagenes/ccirculo.GIF"));
    
     private void generarTablero(){

        for(int i=0;i<fila;i++){
            for(int j=0;j<col;j++){
                botones[i][j] = new JButton(inicio);
                jPanel1.add(botones[i][j]);
                botones[i][j].setBounds(size*i, size*j, size, size);
                botones[i][j].addActionListener(this);
                botones[i][j].setEnabled(true);
            }
        }
        asignarBuenos(botones);
        habilitar(botones,col-1);
    }
    
    private void habilitar(JButton [][] botones,int columna){
        
        for (int i = 0; i < 6; i++) {
            for(int j=0; j<3;j++){
                 botones[j][i].setEnabled(false);
                if(i==columna){
                botones[j][i].setEnabled(true);
                }
            }
        }
        setFilas(1);        
    }
    
    private void asignarBuenos(JButton[][] botones){
        Random r=new Random();
        int columna;
        for (int i = 0; i < 6; i++) {
            columna=r.nextInt(3);
            for(int j=0; j<3;j++){
                if(columna==j){
                    botones[j][i].setRolloverIcon(bueno);
                    botones[j][i].setRolloverSelectedIcon(bueno);
                    
                    break;
                }
            }
        }
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        JButton seleccionado=(JButton) e.getSource();
        
        if(seleccionado.getRolloverSelectedIcon()!=null){
            seleccionado.setIcon(bueno);
            
            if(getFilas()<0){
                JOptionPane.showInternalMessageDialog(null, "HAS GANADO");
                setActivo(false);
                return;
            }
            
            habilitar(botones, getFilas());
            return;
        }
        JOptionPane.showInternalMessageDialog(null, "HAS PERDIDO");
        setActivo(false);
        return;
    }

    public boolean getActivo() {
        return this.activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getFilas() {
        return this.filas;
    }

    public void setFilas(int filas) {
        this.filas -= filas;
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 217, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 449, Short.MAX_VALUE)
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
            java.util.logging.Logger.getLogger(MemoryPath.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MemoryPath.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MemoryPath.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MemoryPath.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MemoryPath().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
