
package GamesFactory;

import MarioThreads.CronoThread;
import java.awt.event.MouseAdapter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.lang.Math;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
* @author Andres Chaves y Pablo Hidalgo

*/
public class CollectCoins extends javax.swing.JFrame {

    public static final int DIMENSIONES = 25;
    
    JButton[][] botones = new JButton[DIMENSIONES][DIMENSIONES];
    int[][] valoresM = new int[DIMENSIONES][DIMENSIONES];
    ImageIcon iconoM = new ImageIcon(getClass().getResource("/Imagenes/moneda2.PNG"));
    boolean activo = true;
    CronoThread cronometro;
        
    public CollectCoins() {
        initComponents();
        generarMonedas();
        setCronometro();
        this.cronometro.start();
    }
    
    void setCronometro(){
        int tiempos[]={30,45,60};
        
        Random rant = new Random();
        
        int indice = (int) rant.nextInt(3);
        
        this.cronometro = new CronoThread(this,tiempos[indice],0);
        
    }
    
    void generarMonedas(){
        for(int i=0;i<DIMENSIONES;i++)
        {
            for(int j=0;j<DIMENSIONES;j++)
            {
                // coloca imagen a todos vacio
                botones[i][j] = new JButton(iconoM);
                //añade al panel el boton;
                jPanel1.add(botones[i][j]);
                // coloca dimensiones y localidad
                botones[i][j].setBounds(10+20*i, 10+20*j, 15, 15);
                // coloca el comand como i , j 
                botones[i][j].setActionCommand(i+","+j);//i+","+j
                
                //aclickSobreTablero(evt);ñade el listener al boton
                botones[i][j].addMouseListener(new MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                        
                    clickMoneda(evt);
                    
                }
                });
                
                //valor de la moneda
                
                int min = -10;  
                int max = 10;  
                if (j%2==0){
                    valoresM[i][j]  = (int)(Math.random()*(max-0+1)+0);  
                }
                else{
                    valoresM[i][j]  = (int)(Math.random()*(0-min+1)+min);  
                }
            }
        }
    }
    
    void clickMoneda(java.awt.event.MouseEvent evt){
        
        JButton botonTemp = (JButton)evt.getComponent();
        String identificadorBoton = botonTemp.getActionCommand();
        
        int columna = Integer.parseInt(identificadorBoton.substring(0,identificadorBoton.indexOf(",")));
        int fila = Integer.parseInt(identificadorBoton.substring(1+identificadorBoton.indexOf(",")));
        
        String actuales = txtPuntos.getText();
        
        int valorM = valoresM[columna][fila];
        
        int suma = Integer.parseInt(actuales);
        
        txtPuntos.setText((valorM+suma)+"");
        
        botones[columna][fila].setEnabled(false);
        
        botones[columna][fila].setBackground(null);
        
    }

    public boolean getActivo() {
        return this.activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    boolean finJuego(){
        
        for(int i=0;i<DIMENSIONES;i++){
            for(int j=0;j<DIMENSIONES;j++){
                botones[i][j].setEnabled(false);
            }
        }
        
        int puntos = Integer.parseInt(txtPuntos.getText());
        
        return puntos>=0;
        
    }
    
    public void setTextToCrono(String newTempo){
        txtCrono.setText(newTempo);
        
        if (this.cronometro.getIntSeconds()==0 & this.cronometro.getIntMinutes() == 0){
            if(finJuego()){
                JOptionPane.showMessageDialog(this, "GANASTE","FELICIDADES", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(this, "PERDISTE","Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblPuntos = new javax.swing.JLabel();
        txtPuntos = new javax.swing.JTextField();
        txtCrono = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 516, Short.MAX_VALUE)
        );

        lblPuntos.setText("Puntos");

        txtPuntos.setEditable(false);
        txtPuntos.setText("0");

        txtCrono.setEditable(false);

        jLabel1.setText("TIEMPO RESTANTE");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPuntos, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPuntos, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCrono))
                .addGap(129, 129, 129))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPuntos)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPuntos, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCrono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        lblPuntos.getAccessibleContext().setAccessibleName("");

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
            java.util.logging.Logger.getLogger(CollectCoins.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CollectCoins.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CollectCoins.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CollectCoins.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CollectCoins().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblPuntos;
    private javax.swing.JTextField txtCrono;
    private javax.swing.JTextField txtPuntos;
    // End of variables declaration//GEN-END:variables
}
