
package GamesFactory;

import MarioThreads.CronoThreadSopa;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JOptionPane;

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
    private int arrayLetras[][];
    private ArrayList<String> array4Palabras = new ArrayList<String>();
    JButton[][] tableroLabels;
    CronoThreadSopa cronometro;
    private float letra=8;
    private int contador=0,y=0,x=0;
    private Random r= new Random();
    private ArrayList<String> arrayList = new ArrayList<>();
    private String palabra = "";
    int buenas = 1;
    private boolean activo;

    private String letras[] = {"a", "b", "c", "d", "e", "f", "g", "h", "i",
        "j", "k", "m", "n", "o", "p", "q", "r", "s",
        "t", "u", "v", "w", "x", "y", "z", "l"
    };
    
    public SopaLetras() {
        initComponents();
        initTablero();
        leerTxt();
        initMatriz();
        setCronometro();
        cronometro.start();
        this.activo=true;
    }
    
    void setCronometro(){
        this.cronometro = new CronoThreadSopa(this);
    }
    
    public void setTextToCrono(String newTempo){
        TextTiempo.setText(newTempo);
        
        if (this.cronometro.getIntSeconds()==0 & this.cronometro.getIntMinutes() == 0){
            JOptionPane.showMessageDialog(this, "PERDISTE","Error", JOptionPane.ERROR_MESSAGE);
            this.activo=false;
            this.setVisible(false);
            
        }
    }
    
    
    
    private void initTablero(){
    size[0]=tam1;
    size[1]=tam2;
    size[2]=tam3;
    tamMatriz=size[r.nextInt(3)];
    tableroLabels= new JButton[tamMatriz][tamMatriz];
    ajuste=ajustarBotones(tamMatriz);
    
    for(int i=0;i<tamMatriz;i++){
        for(int j=0;j<tamMatriz;j++){
            tableroLabels[i][j] = new JButton();
            //tableroLabels[i][j].setText("A");
            tableroLabels[i][j].setFont(tableroLabels[i][j].getFont().deriveFont(letra));
            //tableroLabels[i][j].setBounds(ajuste*i, ajuste*j,ajuste,ajuste);
            tableroLabels[i][j].addActionListener(new ActionListener(){
                
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        for (int j = 0; j < tamMatriz; j++) {
                            for (int m = 0; m < tamMatriz; m++) {
                                if (ae.getSource() == tableroLabels[j][m]) {
                                    palabra += tableroLabels[j][m].getText();
                                    tableroLabels[j][m].setBackground(Color.blue);
                                    //System.out.println(palabra);
                                }
                            }
                        }
                    }
            });
                int indiceLetras = r.nextInt(26);

                PanelP.add(tableroLabels[i][j]);
                tableroLabels[i][j].setBackground(Color.white);
                tableroLabels[i][j].setText(letras[indiceLetras]);

                if (contador == tamMatriz) {
                    y += ajuste;
                    contador = 0;
                    x = 0;
                }
                if (contador <= tamMatriz) {
                    tableroLabels[i][j].setBounds(ajuste*i, ajuste*j,ajuste,ajuste);
                    x += 1;
                }
                contador++;
            }
        }
    }
    
    
    private void initMatriz(){
        
        int posXP1 = r.nextInt(3);
        int posYP1 = 9;

        int posXP2 = 0;
        int posYP2 = r.nextInt(3);

        int posXP3 = r.nextInt(3);
        int posYP3 = r.nextInt(3);

        int posXP4 = r.nextInt(3);
        int posYP4 = r.nextInt(3);
    
        if (tamMatriz == 10) {
            posXP1 = r.nextInt(3);
            posYP1 = 9;

            posXP2 = 0;
            posYP2 = r.nextInt(3);

            posXP3 = r.nextInt(3);
            posYP3 = r.nextInt(3);

            posXP4 = r.nextInt(3);
            posYP4 = r.nextInt(3);

        } else if (tamMatriz == 15) {
            posXP1 = r.nextInt(8);
            posYP1 = 9;

            posXP2 = 0;
            posYP2 = r.nextInt(8);

            posXP3 = r.nextInt(8);
            posYP3 = r.nextInt(8);

            posXP4 = r.nextInt(8);
            posYP4 = r.nextInt(8);
        } else if (tamMatriz == 20) {
            posXP1 = r.nextInt(13);
            posYP1 = 9;

            posXP2 = 0;
            posYP2 = r.nextInt(13);

            posXP3 = r.nextInt(13);
            posYP3 = r.nextInt(13);

            posXP4 = r.nextInt(13);
            posYP4 = r.nextInt(13);
        }

        arrayLetras = new int[20][20];
        for (int i = 0; i < tamMatriz; i++) {
            for (int n = 0; n < tamMatriz; n++) {
                arrayLetras[i][n] = 0;
            }
        }
        int palabra1 = r.nextInt(100);
        int palabra2 = r.nextInt(100);
        int palabra3 = r.nextInt(100);
        int palabra4 = r.nextInt(100);
        
        String sP1 = arrayList.get(palabra1);
        String sP2 = arrayList.get(palabra2);
        String sP3 = arrayList.get(palabra3);
        String sP4 = arrayList.get(palabra4);

        array4Palabras.add(sP1);
        array4Palabras.add(sP2);
        array4Palabras.add(sP3);
        array4Palabras.add(sP4);
        
        if (!validarPosicion(sP1, posXP1, posYP1, "h")) {

            while (!validarPosicion(sP1, posXP1, posYP1, "h")) {
                posXP1 = r.nextInt(1);
                posYP1 = r.nextInt(4);
            }
            for (int n = 0; n < sP1.length(); n++) {
                arrayLetras[posXP1][posYP1] = 1;
                char c = sP1.charAt(n);
                tableroLabels[posXP1][posYP1].setText(String.valueOf(c));
                posXP1++;
            }
        } else {
            for (int n = 0; n < sP1.length(); n++) {
                arrayLetras[posXP1][posYP1] = 1;
                char c = sP1.charAt(n);
                tableroLabels[posXP1][posYP1].setText(String.valueOf(c));
                posXP1++;
            }

        }

        if (!validarPosicion(sP2, posXP2, posYP2, "v")) {

            while (!validarPosicion(sP2, posXP2, posYP2, "v")) {
                posXP2 = r.nextInt(4);
                posYP2 = r.nextInt(1);
            }
            for (int n = 0; n < sP2.length(); n++) {
                arrayLetras[posXP2][posYP2] = 2;
                char c = sP2.charAt(n);
                tableroLabels[posXP2][posYP2].setText(String.valueOf(c));
                posYP2++;
            }
        } else {
            for (int n = 0; n < sP2.length(); n++) {
                arrayLetras[posXP2][posYP2] = 2;
                char c = sP2.charAt(n);
                tableroLabels[posXP2][posYP2].setText(String.valueOf(c));
                posYP2++;
            }

        }

        if (!validarPosicion(sP3, posXP3, posYP3, "d")) {

            while (!validarPosicion(sP3, posXP3, posYP3, "d")) {
                posXP3 = r.nextInt(3);
                posYP3 = r.nextInt(3);
            }
            for (int n = 0; n < sP3.length(); n++) {
                arrayLetras[posXP3][posYP3] = 3;
                char c = sP3.charAt(n);
                tableroLabels[posXP3][posYP3].setText(String.valueOf(c));
                posYP3++;
                posXP3++;
            }

        } else {
            for (int n = 0; n < sP3.length(); n++) {
                arrayLetras[posXP3][posYP3] = 3;
                char c = sP3.charAt(n);
                tableroLabels[posXP3][posYP3].setText(String.valueOf(c));
                posYP3++;
                posXP3++;
            }

        }
        if (!validarPosicion(sP4, posXP4, posYP4, "d")) {

            while (!validarPosicion(sP4, posXP4, posYP4, "d")) {
                posXP4 = r.nextInt(3);
                posYP4 = r.nextInt(3);
            }
            for (int n = 0; n < sP4.length(); n++) {
                arrayLetras[posXP4][posYP4] = 4;
                char c = sP4.charAt(n);
                tableroLabels[posXP4][posYP4].setText(String.valueOf(c));
                posYP4++;
                posXP4++;
            }
        } else {
            for (int n = 0; n < sP4.length(); n++) {
                arrayLetras[posXP4][posYP4] = 4;
                char c = sP4.charAt(n);
                tableroLabels[posXP4][posYP4].setText(String.valueOf(c));
                posYP4++;
                posXP4++;
            }

        }
        
        P1.setText(sP1);
        P2.setText(sP2);
        P3.setText(sP3);
        P4.setText(sP4);


    }
    
    
    private int ajustarBotones(int tamMatriz) {
        if (tamMatriz==10)return 70; 
        if (tamMatriz==15)return 45; 
        if (tamMatriz==20)return 35;
        return 0;
    }
    
    public void leerTxt() {
        try (Scanner s = new Scanner(new File("palabrasSopa.txt")).useDelimiter("\\s*\n\\s*")) {

            while (s.hasNext()) {
                arrayList.add(s.next());
            }
        } catch (FileNotFoundException e) {
        }
    }
     
    
    private boolean validarPosicion(String a, int x, int y, String m) {

        if (m == "d") {
            for (int n = 0; n < a.length(); n++) {
                if (arrayLetras[x][y] != 0) {
                    return false;
                }
                x++;
                y++;
            }
            return true;

        }
        if (m == "v") {
            for (int n = 0; n < a.length(); n++) {
                if (arrayLetras[x][y] != 0) {
                    return false;
                }
                y++;
            }
            return true;

        }
        if (m == "h") {
            for (int n = 0; n < a.length(); n++) {
                if (arrayLetras[x][y] != 0) {
                    return false;
                }
                x++;
            }
            return true;
        } else {
            return true;

        }
    }
    
    public void palabraCorrect() {

        for (String a : array4Palabras) {
            if (PalabrasIguales(a, palabra)) {
                JOptionPane.showMessageDialog(null, "Palabra correcta");
                for (int j = 0; j < tamMatriz; j++) {
                    for (int m = 0; m < tamMatriz; m++) {
                        if (tableroLabels[j][m].getBackground() == Color.blue) {
                            tableroLabels[j][m].setBackground(Color.green);

                        }
                    }
                }
                if (buenas == 4) {
                    JOptionPane.showMessageDialog(null, "Ganaste");
                    this.activo=false;
                    this.setVisible(false);
                    return;
                }

                buenas++;
                return;
            }
        }

        for (int j = 0; j < tamMatriz; j++) {
            for (int m = 0; m < tamMatriz; m++) {
                if (tableroLabels[j][m].getBackground() == Color.blue) {
                    tableroLabels[j][m].setBackground(Color.white);
                }
            }
        }

        JOptionPane.showMessageDialog(null, "Palabra incorrecta");
        return;
    }
    

    public boolean PalabrasIguales(String a, String b) {

        char[] first = a.toCharArray();
        char[] second = b.toCharArray();
        Arrays.sort(first);
        Arrays.sort(second);
        return Arrays.equals(first, second);

    }

    public boolean isActivo() {
        return this.activo;
    }
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelP = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnAceptar = new javax.swing.JButton();
        P1 = new javax.swing.JLabel();
        P3 = new javax.swing.JLabel();
        P4 = new javax.swing.JLabel();
        P2 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        TextTiempo = new javax.swing.JTextField();

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
            .addGap(0, 706, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(153, 51, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("   PALABRAS");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setVerifyInputWhenFocusTarget(false);

        btnAceptar.setFont(new java.awt.Font("SimSun-ExtB", 0, 18)); // NOI18N
        btnAceptar.setText("ACEPTAR");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        P1.setFont(new java.awt.Font("SimSun-ExtB", 0, 14)); // NOI18N
        P1.setForeground(new java.awt.Color(255, 255, 255));
        P1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));

        P3.setFont(new java.awt.Font("SimSun-ExtB", 0, 14)); // NOI18N
        P3.setForeground(new java.awt.Color(255, 255, 255));
        P3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));

        P4.setFont(new java.awt.Font("SimSun-ExtB", 0, 14)); // NOI18N
        P4.setForeground(new java.awt.Color(255, 255, 255));
        P4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));

        P2.setFont(new java.awt.Font("SimSun-ExtB", 0, 14)); // NOI18N
        P2.setForeground(new java.awt.Color(255, 255, 255));
        P2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(P1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                    .addComponent(P3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(P4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(P2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(P1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79)
                .addComponent(P3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(P4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(133, 133, 133)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAceptar, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(82, 82, 82)
                    .addComponent(P2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(350, Short.MAX_VALUE)))
        );

        jLabel2.setFont(new java.awt.Font("Sitka Text", 0, 18)); // NOI18N
        jLabel2.setText("         TIEMPO");

        TextTiempo.setFont(new java.awt.Font("Sitka Text", 0, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PanelP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(TextTiempo)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TextTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        palabraCorrect();
        palabra = "";
    }//GEN-LAST:event_btnAceptarActionPerformed

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
    private javax.swing.JLabel P1;
    private javax.swing.JLabel P2;
    private javax.swing.JLabel P3;
    private javax.swing.JLabel P4;
    private javax.swing.JPanel PanelP;
    private javax.swing.JTextField TextTiempo;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables


}
