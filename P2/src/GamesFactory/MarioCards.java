
package GamesFactory;

import java.awt.event.MouseAdapter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
* @author Andres Chaves y Pablo Hidalgo
*/
public class MarioCards extends javax.swing.JFrame {

    
    public static final int DIFCARTAS = 13;
    public static final int TIPOSCARTAS = 4;
    JButton[][] cartas = new JButton[DIFCARTAS][TIPOSCARTAS];
    ImageIcon iconoM = new ImageIcon(getClass().getResource("/Imagenes/cartareverso.PNG"));
    
    //CARTA JUGADOR
    int tipo = -1;
    int valor = -1;
    

    public MarioCards() {
        initComponents();
        generarCartas();
    }
    
    
    
    void generarCartas(){
        
        for(int i=0;i<DIFCARTAS;i++)
        {
            for(int j=0;j<TIPOSCARTAS;j++)
            {
                // coloca imagen a todos vacio
                cartas[i][j] = new JButton(iconoM);
                //añade al panel el boton;
                jPanel1.add(cartas[i][j]);
                // coloca dimensiones y localidad
                cartas[i][j].setBounds(20+40*i, 50+60*j, 38, 58);
                // coloca el comand como i , j 
                cartas[i][j].setActionCommand(i+","+j);//i+","+j
                
                //aclickSobreTablero(evt);ñade el listener al boton
                cartas[i][j].addMouseListener(new MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                        
                    darInfoCarta(evt);
                    
                }
                });
            }
        }
    }
    
    
    void darInfoCarta(java.awt.event.MouseEvent evt){
        JButton botonTemp = (JButton)evt.getComponent();
        String identificadorBoton = botonTemp.getActionCommand();
        int columna = Integer.parseInt(identificadorBoton.substring(0,identificadorBoton.indexOf(",")));
        int fila = Integer.parseInt(identificadorBoton.substring(1+identificadorBoton.indexOf(",")));
        
        String info = getValorCarta(columna)+ " de " + getTipoCarta(fila);
        
        tipo = columna;
        valor = fila;
        
        JOptionPane.showMessageDialog(this, info, "Tu carta es", JOptionPane.INFORMATION_MESSAGE);
        this.setVisible(false);

    }
   
    
    String getTipoCarta(int num){
        
        switch(num){
            
            case 0: {
                return "Rombos";//Rombos
            }
            case 1: {
                return "Picas";//Picas
            }
            case 2: {
                return "Treboles";//Treboles
            }
            case 3: {
                return "Corazones";//Corazones
            }
        }
        return "";
    }
    
    String getValorCarta(int num){
        switch(num){
            
            case 0 : {
                return "2";
            }
            case 1 : {
                return "3";
            }
            case 2 : {
                return "4";
            }
            case 3 : {
                return "5";
            }
            case 4 : {
                return "6";
            }
            case 5 : {
                return "7";
            }
            case 6 : {
                return "8";
            }
            case 7 : {
                return "9";
            }
            case 8 : {
                return "10";
            }
            case 9 : {
                return "Jota";
            }
            case 10 : {
                return "Reina";
            }
            case 11 : {
                return "Rey";
            }case 12 : {
                return "As";
            }
        }
        return "";
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 560, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 340, Short.MAX_VALUE)
        );

        jLabel1.setText("SELECCIONA UNA CARTA");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(173, 173, 173))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            java.util.logging.Logger.getLogger(MarioCards.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MarioCards.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MarioCards.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MarioCards.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MarioCards().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
