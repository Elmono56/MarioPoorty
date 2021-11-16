
package Juegos;

import Personajes.Personajes;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.HIDE_ON_CLOSE;

/**
 *
 * @author chave
 */
public class Tablero extends javax.swing.JFrame {
    
    public static final int BUTTON_SIZE =70 ;
    public static final int BOARD_SIZE = 28;
    private JButton buttonArray[] = new JButton[BOARD_SIZE];
    private int turno =0;
    private int max,resultadoDados,id,tubo=0;
    private  Random r= new Random();
    private ArrayList <Personajes> jugadores=null;
    private ArrayList <String> infoCasillas=null;
    private Personajes enemigo =null;
    private boolean finish,repite,fuego,hielo,cola;
    private Personajes jugadorEnTurno=null;
    private boolean juego=false;
    private String nombrejuego="";
   
    
    
    public Tablero(ArrayList <Personajes> jugadores,ArrayList<String> infoCasillas) {
        initComponents();
        this.jugadores=jugadores;
        this.infoCasillas=infoCasillas;
        this.max=this.jugadores.size()-1;
        initUsers();
        initBoard();
    }

    public Tablero() {
        initComponents();
        initUsers();
        initBoard();
    }



    
    private void initUsers(){
    
        int playersQty= jugadores.size();
        for (int i = 0; i < playersQty; i++) {
            JButton newButton = new JButton("" + (i+1));
            newButton.setIcon(jugadores.get(i).getIcon());
            id=jugadores.get(i).getTurno();
            jPanel1.add(newButton);
            newButton.setBounds(3, i*20, 40, 20);
            jugadores.get(i).setRefButton(newButton);
        }
    }

    private void initBoard(){
        for (int i = 0; i < buttonArray.length; i++) {
            buttonArray[i] = new JButton((i)+"");
            jPanel1.add(buttonArray[i]);
           
            if (i <=7){
                buttonArray[i].setBounds((i*BUTTON_SIZE)+3, 3, BUTTON_SIZE, BUTTON_SIZE);
                if(i==0){
                    buttonArray[i].setText("START");
                }else{
                    buttonArray[i].setText(infoCasillas.get(i-1));
                }
            
            }else if (i >=8 && i <= 14){
                buttonArray[i].setBounds(493, ((i-7) * BUTTON_SIZE)+3, BUTTON_SIZE, BUTTON_SIZE);
                buttonArray[i].setText(infoCasillas.get(i-1));

            }else if (i >=14 && i <= 20){
                buttonArray[i].setBounds(493-(i-14)* BUTTON_SIZE, 493 , BUTTON_SIZE, BUTTON_SIZE);
                buttonArray[i].setText(infoCasillas.get(i-1));

            }else{
                    buttonArray[i].setBounds(3, 423-(i-22)* BUTTON_SIZE, BUTTON_SIZE, BUTTON_SIZE);
                    if(i==27){
                        buttonArray[i].setText("FINISH");
                    }else{
                        buttonArray[i].setText(infoCasillas.get(i-1));
                    }
                }
    
        }
        //habilita el boton de lanzar a la persona que tenga el turno
        for (int i = 0; i < jugadores.size(); i++) {
            if(this.jugadores.get(i).getTurno()==this.turno)jButtonLanzar.setEnabled(true);
            else{jButtonLanzar.setEnabled(false);}
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButtonLanzar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        TxtTurno = new javax.swing.JTextField();
        TxtDados = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 204, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 4, true));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 573, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 570, Short.MAX_VALUE)
        );

        jButtonLanzar.setBackground(new java.awt.Color(255, 21, 114));
        jButtonLanzar.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        jButtonLanzar.setText("LANZAR");
        jButtonLanzar.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        jButtonLanzar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLanzarActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(0, 153, 153));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 4, true));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.setToolTipText("");
        jPanel3.setFont(new java.awt.Font("Tempus Sans ITC", 0, 18)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tempus Sans ITC", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("TURNO:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
        );

        TxtTurno.setEditable(false);
        TxtTurno.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        TxtTurno.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TxtTurno.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));

        TxtDados.setEditable(false);
        TxtDados.setFont(new java.awt.Font("Tempus Sans ITC", 1, 36)); // NOI18N
        TxtDados.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TxtDados.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonLanzar, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtTurno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(28, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TxtDados, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(TxtTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(TxtDados, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonLanzar, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(124, 124, 124))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonLanzarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLanzarActionPerformed
        int dado1,dado2;
       
        for (int i = 0; i < jugadores.size(); i++) {   
            if(jugadores.get(i).getTurno()==this.turno ){
                jugadorEnTurno=jugadores.get(i);
                break;
            }
        }
        
        //System.out.println(jugadorEnTurno.getName()+" "+jugadorEnTurno.getInmovil());
        
        if(jugadorEnTurno.getInmovil()==true){
            System.out.println("estoy inmovil "+jugadorEnTurno.getName());
            actualizarJugadorInmovil(jugadorEnTurno);
            resultadoDados=0;
            actualizarTurno();
            return;
        }
                
        dado1=r.nextInt(6)+1;//resultadoDados
        dado2=r.nextInt(6)+1;
        if(verificarDados(jugadorEnTurno,dado1,dado2)){
            resultadoDados=dado1+dado2;
            TxtDados.setText(resultadoDados+"");
            moverFicha(jugadorEnTurno,resultadoDados,1);
        }
        

        actualizarTurno();
    }//GEN-LAST:event_jButtonLanzarActionPerformed





//metodos
    public void actualizarTurno(){
        this.turno++;
        if(this.turno>max){
            this.turno=0;
        }
        
        //System.out.print("");
        for (int i = 0; i < jugadores.size(); i++) {
            if(this.jugadores.get(i).getTurno()==this.turno){                    
                    jButtonLanzar.setEnabled(true);
                    TxtTurno.setText(this.jugadores.get(i).getName());
            }
            else jButtonLanzar.setEnabled(false);
        }
    }
    
    
    public void actualizarEnemigos(int movimiento, int id,int tubo){
       
        for (int i = 0; i < jugadores.size(); i++) {
            if(this.jugadores.get(i).getTurno()==id){
                    if(movimiento!=0) 
                        moverFicha(this.jugadores.get(i),movimiento,tubo);
                    break;
            }
            
        }
        actualizarTurno();
    }
    
    
    private boolean verificarDados(Personajes jugador,int dado1,int dado2){
        
        if(dado1==6 && dado2==6){
            jugador.setInmovil(true);
            jugador.setCantInmovil(2);
            TxtDados.setText("X2");
            resultadoDados=0;
            return false;
        }
        if(dado1==6 || dado2==6){
            jugador.setInmovil(true);
            jugador.setCantInmovil(1);
            TxtDados.setText("X1");
            resultadoDados=0;
            return false;
        }
        
        return true;
    }
    
    
    private void actualizarJugadorInmovil(Personajes jugador){
    
        if(jugador.getCantInmovil()>1){
            jugador.setCantInmovil((jugador.getCantInmovil()-1));
            TxtDados.setText("X"+jugador.getCantInmovil());
        }else{
            jugador.setCantInmovil(0);
            jugador.setInmovil(false);
        }
    }
    
    public void moverFicha(Personajes jugador, int resultadoDados,int tipo){
       
        
        int avanzar=jugador.getCasillaActual()+resultadoDados;
        Point ptoCasilla=null;

        if(avanzar>BOARD_SIZE)
            avanzar=(BOARD_SIZE-(avanzar-BOARD_SIZE))-2;
        
        jugador.setCasillaActual(avanzar);
        
        if(jugador.getCasillaActual()==28)jugador.setCasillaActual(27);

        if(tipo==1)jugador=verificarCasilla(buttonArray[jugador.getCasillaActual()],jugador); 
        
        JButton botonFicha = jugador.getRefButton();        
        ptoCasilla=buttonArray[jugador.getCasillaActual()].getLocation();
        botonFicha.setLocation(ptoCasilla.x, ptoCasilla.y+jugador.getTurno()*20);
    }


        
    
    private Personajes verificarCasilla(JButton casilla,Personajes jugador){
        
      String casillaActual=casilla.getText();
        
        switch(casillaActual){
            case "CARCEL":{
                jugador.setInmovil(true);
                jugador.setCantInmovil(2);
                break;
            }
            case "ESTRELLA":{
                this.setRepite(true);
                break;
            }
            case "FUEGO":{
                this.setFuego(true);            
                jButtonLanzar.setEnabled(false);
                atacarEnemigo(jugador,0);//0 IDENTIFICADOR DE FUEGO
                break;
            }
            case "HIELO":{
                this.setHielo(true);
                jButtonLanzar.setEnabled(false);
                atacarEnemigo(jugador,1);//1 IDENTIFICADOR DE HIELO 
                break;
            }
            case "COLA":{  
                this.setCola(true);
                int num;
                jButtonLanzar.setEnabled(false);
                num=cola(jugador);
                jugador.setCasillaActual(jugador.getCasillaActual()+num);
                resultadoDados+=num;
                this.setCola(false);
                break;
            }
            case "TUBO1":{
                this.tubo=1;
                jugador.setCasillaActual(encontrarTubo("TUBO2"));
                break;
            } 
            case "TUBO2":{
                this.tubo=1;
                jugador.setCasillaActual(encontrarTubo("TUBO3"));
                break;
            }
            case "TUBO3":{
                this.tubo=1;
                jugador.setCasillaActual(encontrarTubo("TUBO1"));
                break;
            }
            case "FINISH":{
                this.finish=true;
                break;
            }
            case "START":{break;}
            
            default:{
                setJuego(true);    
                setNombrejuego(casilla.getText());
                break;
            }
        }
        return jugador;
    }
        
    private int cola(Personajes jugador){
        
        ArrayList<Integer> casillas=new ArrayList<Integer>();
        
        int inicio=jugador.getCasillaActual()-3;
        int stop=jugador.getCasillaActual()+3;
        int cantidad=-3;
        for (int i = 0; i < buttonArray.length; i++) { //hacer validaciones de si hay menos casillas de las permitidas
            
            if(i>=inicio && i<=stop){
                casillas.add(cantidad);
                cantidad++;
            }
            if(i>stop)break;
        }
        
        Cola vtnCola=new Cola(casillas);
        while(!vtnCola.getSalida()){
            JOptionPane.showInternalMessageDialog(null, vtnCola, "CASILLAS", HIDE_ON_CLOSE);            
            if(vtnCola.getSalida())
                break;
        }
        return vtnCola.getSeleccionado();
    
    }
    
    
    private int encontrarTubo(String tubo){
    
        for (int i = 0; i < buttonArray.length; i++) {
            if(buttonArray[i].getText().equals(tubo)) 
                return i;
        }
        return 0;
    }
    
    
    private Personajes atacarEnemigo(Personajes jugador,int tipo) {
       
        regresarJugador vtnRegresar=null;
        ArrayList<Personajes> enemigos=new ArrayList<Personajes>();
       
        
        for (int i = 0; i < jugadores.size(); i++) {
            if(jugadores.get(i)!=jugador)
                enemigos.add(jugadores.get(i));
        }
        
        vtnRegresar=new regresarJugador(enemigos);
        while(getEnemigo()==null){
            
            JOptionPane.showInternalMessageDialog(null, vtnRegresar, "PERSONAJES", HIDE_ON_CLOSE);            
            setEnemigo(vtnRegresar.getSeleccionado());
        }
        
        System.out.println("voy a retroceder a : "+this.enemigo.getName());
              
        if(tipo==0){
            int regresar=(BOARD_SIZE-1)+((BOARD_SIZE)-this.enemigo.getCasillaActual())-1;
            this.enemigo.setNumero(regresar);
            moverFicha(this.enemigo,regresar,0);
        }
        
        if(tipo==1){// se puede eliminar 
            this.enemigo.setInmovil(true);
            this.enemigo.setCantInmovil(2);
        }

        return this.enemigo;
        
    }
    
    
    
    //GETTER
    public int getId(){
        return this.id;
    }
    public int getTubo(){
        return  this.tubo;
    }
    public int getTurno() {
        return this.turno;
    }

    public boolean getActive(){
        return this.jButtonLanzar.isEnabled();
    }

    public int getResultadoDados() {
        return this.resultadoDados;
    }
    
    public boolean getBtnLanzar(){
        return this.jButtonLanzar.isEnabled();
    }    

    public Personajes getEnemigo() {
        return this.enemigo;
    }

    public boolean getJuego() {
        return this.juego;
    }

    public void setJuego(boolean juego) {
        this.juego = juego;
    }

    public String getNombrejuego() {
        return this.nombrejuego;
    }

    public void setNombrejuego(String nombrejuego) {
        this.nombrejuego = nombrejuego;
    }
    
    
    
    public boolean haGanado(){
    return this.finish;
    }

    public boolean getRepite() {
        return this.repite;
    }

    public boolean getFuego() {
        return this.fuego;
    }
    public boolean getHielo() {
        return this.hielo;
    }
    
    public boolean getCola(){
        return this.cola;
    }
    

    //SETTER

    public void setTubo(int tubo){
        this.tubo=tubo;
    }
    public void setEnemigo(Personajes enemigo) {
        this.enemigo = enemigo;
    }

    public void setFuego(boolean fuego) {
        this.fuego = fuego;
    }

    public void setHielo(boolean hielo) {
        this.hielo = hielo;
    }

    public void setRepite(boolean repite) {
        this.repite = repite;
    }
    
    public void activarBtnLanzar(){
        this.jButtonLanzar.setVisible(true);
    }
   
    public void setTxtTurno(String nombre){
    
        TxtTurno.setText(nombre);
    }
    
    public void setTurnoTxt(String nombre){
        TxtTurno.setText(nombre);
    }
    
    public void setTurno(int turno){
        this.turno=turno;
    }

    public void setResultadoDados(int resultadoDados) {
        this.resultadoDados = resultadoDados;
    }
    public void setCola(boolean cola) {
        this.cola=cola;
    }


    
    
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
            java.util.logging.Logger.getLogger(Tablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tablero().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TxtDados;
    private javax.swing.JTextField TxtTurno;
    private javax.swing.JButton jButtonLanzar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables


}
