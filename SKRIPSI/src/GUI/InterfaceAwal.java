/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author robby
 */
public class InterfaceAwal extends javax.swing.JFrame {

    /**
     * Creates new form InterfaceAwal
     */
    public InterfaceAwal() {
        initComponents();
        setLayout(new BorderLayout());
        JLabel background=new JLabel(new ImageIcon("baru.jpg"));
        add(background);
        background.setLayout(new FlowLayout());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttongroup = new javax.swing.ButtonGroup();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        inputtext = new javax.swing.JRadioButton();
        acak = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Perangkat Lunak Pemodelan dan Simulasi");
        setAlwaysOnTop(true);
        setFocusableWindowState(false);
        setForeground(java.awt.Color.white);
        setMinimumSize(new java.awt.Dimension(668, 365));
        setResizable(false);
        getContentPane().setLayout(null);
        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(0, 60, 700, 20);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Pemodelan dan Simulasi Antrian Pasien di RS TNI AU Dr.M.Salamun");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(30, 10, 620, 30);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Pilih metode pemodelan dan simulasi :");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(220, 120, 270, 17);

        buttongroup.add(inputtext);
        inputtext.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        inputtext.setText("Input File");
        getContentPane().add(inputtext);
        inputtext.setBounds(230, 160, 93, 23);

        buttongroup.add(acak);
        acak.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        acak.setText("Pemodelan dan Simulasi Acak");
        getContentPane().add(acak);
        acak.setBounds(230, 190, 220, 23);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("Next");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(300, 240, 90, 23);
        getContentPane().add(jSeparator2);
        jSeparator2.setBounds(180, 100, 320, 10);
        getContentPane().add(jSeparator3);
        jSeparator3.setBounds(180, 280, 320, 10);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(buttongroup.isSelected(inputtext.getModel())){
            InterfaceGUI4 gui=new InterfaceGUI4();
            gui.setVisible(true);
            this.setVisible(false);
//            this.dispose();
        }
        else if(buttongroup.isSelected(acak.getModel())){
            InterfaceGUI1 gui=new InterfaceGUI1();
            gui.setVisible(true);
            this.setVisible(false);
//            this.dispose();
        }
        else{
            JOptionPane.showMessageDialog(this,"Anda harus memilih metode pemodelan terlebih dahulu","Alert",JOptionPane.ERROR_MESSAGE);
                
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(InterfaceAwal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfaceAwal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfaceAwal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfaceAwal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfaceAwal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton acak;
    private javax.swing.ButtonGroup buttongroup;
    private javax.swing.JRadioButton inputtext;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    // End of variables declaration//GEN-END:variables
}
