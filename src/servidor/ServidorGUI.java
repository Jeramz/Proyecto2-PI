/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servidor;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.ServerSocket;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author jesus
 */
public class ServidorGUI extends javax.swing.JFrame {

    /** Creates new form ServidorGUI */
    public ServidorGUI() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        
        listaPreguntas=new ArrayList();
        jDialogFileChooser = new javax.swing.JDialog();
        jFileChooser2 = new javax.swing.JFileChooser();
        lbServidor = new javax.swing.JLabel();
        btCargarPreguntas = new javax.swing.JButton();
        lbTiempoMax = new javax.swing.JLabel();
        tfTiempoMax = new javax.swing.JTextField();
        btGuardarCambios = new javax.swing.JButton();

        jDialogFileChooser.setSize(new java.awt.Dimension(600, 340));

        jFileChooser2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFileChooser2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialogFileChooserLayout = new javax.swing.GroupLayout(jDialogFileChooser.getContentPane());
        jDialogFileChooser.getContentPane().setLayout(jDialogFileChooserLayout);
        jDialogFileChooserLayout.setHorizontalGroup(
            jDialogFileChooserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogFileChooserLayout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(jFileChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jDialogFileChooserLayout.setVerticalGroup(
            jDialogFileChooserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogFileChooserLayout.createSequentialGroup()
                .addGap(0, 14, Short.MAX_VALUE)
                .addComponent(jFileChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbServidor.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lbServidor.setForeground(new java.awt.Color(0, 0, 255));
        lbServidor.setText("Servidor");

        btCargarPreguntas.setText("Cargar preguntas");
        btCargarPreguntas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCargarPreguntasActionPerformed(evt);
            }
        });

        lbTiempoMax.setText("Tiempo Máximo (Minutos)");

        btGuardarCambios.setText("Guardar Cambios");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbServidor)
                    .addComponent(btCargarPreguntas)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbTiempoMax)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfTiempoMax, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btGuardarCambios))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbServidor)
                .addGap(18, 18, 18)
                .addComponent(btCargarPreguntas)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTiempoMax)
                    .addComponent(tfTiempoMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btGuardarCambios)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public void iniciarServidor(){
        try
      {  System.out.println("Binding to port " + 12345 + ", please wait  ...");
         server = new ServerSocket(12345);  
         System.out.println("Server started: " + server);
         iniciarMulticast();
         while (true)
        {      System.out.println("Waiting for a client ..."); 
              client = new ServerThread(this,server.accept());
              System.out.println("Client accepted: " + client.getName());
              try
                {  client.open();
                   client.start();
                }catch(IOException ioe){  
                    System.out.println("Error opening thread: " + ioe); }

                   }
      }catch(IOException ioe){  
          System.out.println(ioe); 
      }
    }

    public void cargarPreguntas(){
        String linea;
        String pregunta[]=new String[4];
        
        try{
            while((linea=brPreguntas.readLine())!= null){
                pregunta=linea.split("//");
                Pregunta preguntas=new Pregunta();
                preguntas.setPregunta(pregunta[0].replaceAll("~", "\n"));
                preguntas.setRespuesta(brPreguntas.readLine());
                preguntas.setOpciones(pregunta[1], pregunta[2], pregunta[3], pregunta[4]);
                try{
                    listaPreguntas.add(preguntas);
                }catch(Exception e){
                    e.printStackTrace();
                System.out.println("error"+e.getMessage());
                }
            }
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("error"+e.getMessage());
        }
    }
    
    public void iniciarMulticast() throws IOException{
    smc = new MulticastSocket ();
     // Creamos el grupo multicast:
     InetAddress group = InetAddress.getByName ("230.0.0.0");
     
    dgp = new DatagramPacket(vacio, 0, group,10000);
   }

    public MulticastSocket getSmc() {
        return smc;
    }

    public DatagramPacket getDgp() {
        return dgp;
    }
    
    private void btCargarPreguntasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCargarPreguntasActionPerformed
        // TODO add your handling code here:
        jDialogFileChooser.setVisible(true);
        
    }//GEN-LAST:event_btCargarPreguntasActionPerformed

    private void jFileChooser2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileChooser2ActionPerformed
        // TODO add your handling code here:
        JFileChooser selectorArchivo =(JFileChooser)evt.getSource();
        String command =evt.getActionCommand();
        if(command.equals(JFileChooser.APPROVE_SELECTION)){
            File archivoSeleccionado;
            archivoSeleccionado=selectorArchivo.getSelectedFile();
            rutaPregunta=archivoSeleccionado.getAbsolutePath();
            try{
                
            fPreguntas=new FileInputStream(rutaPregunta);
            frPreguntas=new DataInputStream(fPreguntas);
            brPreguntas=new BufferedReader(new InputStreamReader(frPreguntas));
            
            this.cargarPreguntas();
            brPreguntas.close();
            fPreguntas.close();
            frPreguntas.close();
            jDialogFileChooser.setVisible(false);
            }catch(IOException e){
                e.printStackTrace();
                System.out.println("error"+e.getMessage());
            }
            
        }else if(command.equals(JFileChooser.CANCEL_SELECTION)){
            jDialogFileChooser.setVisible(false);
        }
    }//GEN-LAST:event_jFileChooser2ActionPerformed

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
            java.util.logging.Logger.getLogger(ServidorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServidorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServidorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServidorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServidorGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCargarPreguntas;
    private javax.swing.JButton btGuardarCambios;
    private javax.swing.JDialog jDialogFileChooser;
    private javax.swing.JFileChooser jFileChooser2;
    private javax.swing.JLabel lbServidor;
    private javax.swing.JLabel lbTiempoMax;
    private javax.swing.JTextField tfTiempoMax;
    String rutaPregunta;
    FileInputStream fPreguntas;
    BufferedReader brPreguntas;
    DataInputStream frPreguntas;
    ArrayList listaPreguntas;
    
    private ServerSocket     server = null;
    private ServerThread client = null;
    
    //para multicast
    //Creamos el MulticastSocket sin especificar puerto.
    private MulticastSocket smc=null;
    // Creamos un datagrama vacío en principio:
    byte [] vacio = new byte [0];
    private DatagramPacket dgp =null;
    // End of variables declaration//GEN-END:variables

}
