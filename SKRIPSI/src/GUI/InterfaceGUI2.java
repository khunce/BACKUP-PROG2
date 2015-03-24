/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import ExcelInputSimulation.ExcelReader;
import ExcelInputSimulation.ExcelWriter;
import ExcelInputSimulation.ServerAwal;
import ExcelInputSimulation.ServerDokter;
import ExcelInputSimulation.ServerPerawat;
import ExcelInputSimulation.ServerPetugas;
import ExcelInputSimulation.StatisticsSimulationAwal;
import ExcelInputSimulation.StatisticsSimulationPoli;
import SimulasiAntrianPasien.Customer;
import SimulasiAntrianPasien.StatisticsGenerator;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.GridPane;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 *
 * @author robby
 */
public class InterfaceGUI2 extends javax.swing.JFrame {

    int banyakServer;
    int banyakCustomer;
    StatisticsSimulationAwal sim;
    StatisticsSimulationPoli sim2;
    StatisticsGenerator gen;
    ServerAwal[] arrayServer;
    ServerPetugas[] arrayPetugas;
    ServerPerawat[] arrayPerawat;
    ServerDokter[] arrayDokter;
    JLabel[] jlabel;
    JLabel[] jlabel2;
    JLabel[] jlabel3;
    JLabel[] jlabel4;
    int counter;
    Queue<Customer> queuereport;
    Queue<Customer> queuereport2;
    Object[][] data;
    Object[][] data2;
    ExcelReader excel;
    ExcelWriter writer;
    JFXPanel fxPanel;
    JFXPanel fxPanel2;
    JFXPanel fxPanel3;
    JFXPanel fxPanel4;
    JFXPanel fxPanel5;
    JFXPanel fxPanel6;
    JFXPanel fxPanel7;
    JFXPanel fxPanel8;
    JFXPanel fxPanel9;
    JFXPanel fxPanel10;
    /**
     * Creates new form InterfaceGUI4
     */
    public InterfaceGUI2() {
        initComponents();
        counter=0;
        jLabel20.setVisible(false);
        jLabel21.setVisible(false);
        jLabel22.setVisible(false);
        jLabel23.setVisible(false);
        jLabel24.setVisible(false);
        jLabel25.setVisible(false);
        jLabel26.setVisible(false);
        jLabel27.setVisible(false);
        jLabel28.setVisible(false);
        jLabel29.setVisible(false);
        jLabel30.setVisible(false);
        jLabel31.setVisible(false);
        jLabel32.setVisible(false);
        jLabel39.setVisible(false);
        jLabel40.setVisible(false);
        jLabel41.setVisible(false);
        jLabel42.setVisible(false);
        jLabel43.setVisible(false);
        jLabel44.setVisible(false);
        jLabel45.setVisible(false);
        jLabel46.setVisible(false);
        jLabel47.setVisible(false);
        jLabel50.setVisible(false);
        jLabel51.setVisible(false);
        jLabel52.setVisible(false);
        jFrame1.setVisible(true);
        queuereport=new LinkedList();
        queuereport2=new LinkedList();
        this.excel=new ExcelReader();
        fxPanel=new JFXPanel();
        fxPanel2=new JFXPanel();
        fxPanel3=new JFXPanel();
        fxPanel4=new JFXPanel();
        fxPanel5=new JFXPanel();
        fxPanel6=new JFXPanel();
        fxPanel7=new JFXPanel();
        fxPanel8=new JFXPanel();
        fxPanel9=new JFXPanel();
        fxPanel10=new JFXPanel();
        hideLoading();
        hideLoadingPoli();
        DefaultTableCellRenderer centerRenderer29 = new DefaultTableCellRenderer();
        centerRenderer29.setHorizontalAlignment( JLabel.CENTER );
        tabel1.setDefaultRenderer(String.class, centerRenderer29);
        JTableHeader headernew = tabel1.getTableHeader();
        headernew.setDefaultRenderer(centerRenderer29);
        for (int i = 0; i < tabel1.getColumnCount(); i++) {
             tabel1.getColumnModel().getColumn(i).setCellRenderer( centerRenderer29);
         }
        tabel2.setDefaultRenderer(String.class, centerRenderer29);
        JTableHeader headernew2 = tabel2.getTableHeader();
        headernew2.setDefaultRenderer(centerRenderer29);
        for (int i = 0; i < tabel2.getColumnCount(); i++) {
             tabel2.getColumnModel().getColumn(i).setCellRenderer( centerRenderer29);
         }
        loadAwal();
        disableServer();
        disableSave();
        disableParamGrafik();
    }
    
    public void disableParamGrafik(){
        param_grafik.setEnabled(false);
        jButton3.setEnabled(false);
        param_grafik2.setEnabled(false);
        jButton4.setEnabled(false);
    }
    
    public void enableParamGrafik(){
        param_grafik.setEnabled(true);
        jButton3.setEnabled(true);
        param_grafik2.setEnabled(true);
        jButton4.setEnabled(true);
    }
    
    public void disableSave(){
        save_table1.setEnabled(false);
        save_table2.setEnabled(false);
    }
    
    public void enableSave(){
        save_table1.setEnabled(true);
        save_table2.setEnabled(true);
    }
    
    public void disableServer(){
        ser_awal.setVisible(false);
        ser_pet.setVisible(false);
        ser_per.setVisible(false);
        ser_dok.setVisible(false);
    }
    
    public void enableServer(){
        ser_awal.setVisible(true);
        ser_pet.setVisible(true);
        ser_per.setVisible(true);
        ser_dok.setVisible(true);
    }
    
    
    public void loadAwal(){
        server_awal.setEnabled(false);
        server_poli.setEnabled(false);
        server_poli2.setEnabled(false);
        server_poli3.setEnabled(false);
        queue_capacity.setEnabled(false);
        open.setEnabled(false);
    }
    
    public void disableAcak(){
        server_awal.setEnabled(false);
        server_poli.setEnabled(false);
        server_poli2.setEnabled(false);
        server_poli3.setEnabled(false);
        queue_capacity.setEnabled(false);
        open.setEnabled(true);
    }
    
     public void enableAcak(){
        server_awal.setEnabled(true);
        server_poli.setEnabled(true);
        server_poli2.setEnabled(true);
        server_poli3.setEnabled(true);
        queue_capacity.setEnabled(true);
        open.setEnabled(true);
    }
     
     public void showLoadingChart(){
         loadingpoli3.setVisible(true);
         loadingpoli4.setVisible(true);
         loadingpoli5.setVisible(true);
     }
     
     public void hideLoadingChart(){
         loadingpoli3.setVisible(false);
         loadingpoli4.setVisible(false);
         loadingpoli5.setVisible(false);
     }
    
    
  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fc = new javax.swing.JFileChooser();
        jFrame1 = new javax.swing.JFrame();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        output_poli = new javax.swing.JTextArea();
        jSeparator14 = new javax.swing.JSeparator();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        panel_grafik_poli = new javax.swing.JPanel();
        loadingpoli = new javax.swing.JLabel();
        panel_grafik_poli2 = new javax.swing.JPanel();
        loadingpoli1 = new javax.swing.JLabel();
        panel_grafik_poli3 = new javax.swing.JPanel();
        loadingpoli2 = new javax.swing.JLabel();
        panel_grafik_poli4 = new javax.swing.JPanel();
        loadingpoli3 = new javax.swing.JLabel();
        panel_grafik_poli5 = new javax.swing.JPanel();
        loadingpoli4 = new javax.swing.JLabel();
        panel_grafik_poli6 = new javax.swing.JPanel();
        loadingpoli5 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tabel2 = new javax.swing.JTable();
        param_grafik2 = new javax.swing.JSpinner();
        jButton4 = new javax.swing.JButton();
        label_grafik5 = new javax.swing.JLabel();
        label_grafik6 = new javax.swing.JLabel();
        label_grafik4 = new javax.swing.JLabel();
        jSeparator19 = new javax.swing.JSeparator();
        frame_report = new javax.swing.JFrame();
        panel_tabel = new javax.swing.JPanel();
        jLabel55 = new javax.swing.JLabel();
        jSeparator17 = new javax.swing.JSeparator();
        pasien_lama = new javax.swing.JLabel();
        pasien_baru = new javax.swing.JLabel();
        pasien_emergency = new javax.swing.JLabel();
        total = new javax.swing.JLabel();
        save_table1 = new javax.swing.JButton();
        frame_report2 = new javax.swing.JFrame();
        total_emergency_poli = new javax.swing.JLabel();
        total_bpjsl_poli = new javax.swing.JLabel();
        total_bpjsb_poli = new javax.swing.JLabel();
        total_poli = new javax.swing.JLabel();
        panel_poli = new javax.swing.JPanel();
        jLabel56 = new javax.swing.JLabel();
        jSeparator18 = new javax.swing.JSeparator();
        save_table2 = new javax.swing.JButton();
        frame_report3 = new javax.swing.JFrame();
        jScrollPane3 = new javax.swing.JScrollPane();
        hasil = new javax.swing.JTextArea();
        panel_service_rate = new javax.swing.JPanel();
        panel_delay_time = new javax.swing.JPanel();
        panel_waiting_time = new javax.swing.JPanel();
        panel_interarrival = new javax.swing.JPanel();
        panel_arrival_rate = new javax.swing.JPanel();
        panel_average = new javax.swing.JPanel();
        panel_tabel_utility = new javax.swing.JPanel();
        panel_pasien = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jSeparator15 = new javax.swing.JSeparator();
        save_report_awal = new javax.swing.JButton();
        frame_report4 = new javax.swing.JFrame();
        jLabel54 = new javax.swing.JLabel();
        jSeparator16 = new javax.swing.JSeparator();
        tab1 = new javax.swing.JTabbedPane();
        panel_petugas = new javax.swing.JPanel();
        panel1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        output_petugas = new javax.swing.JTextArea();
        panel2 = new javax.swing.JPanel();
        panel3 = new javax.swing.JPanel();
        panel4 = new javax.swing.JPanel();
        panel5 = new javax.swing.JPanel();
        panel6 = new javax.swing.JPanel();
        panel7 = new javax.swing.JPanel();
        panel8 = new javax.swing.JPanel();
        save_report_petugas = new javax.swing.JButton();
        panel_perawat = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        output_perawat = new javax.swing.JTextArea();
        panel9 = new javax.swing.JPanel();
        panel10 = new javax.swing.JPanel();
        panel11 = new javax.swing.JPanel();
        panel12 = new javax.swing.JPanel();
        panel13 = new javax.swing.JPanel();
        panel14 = new javax.swing.JPanel();
        panel15 = new javax.swing.JPanel();
        panel16 = new javax.swing.JPanel();
        save_report_perawat = new javax.swing.JButton();
        panel_dokter = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        output_dokter = new javax.swing.JTextArea();
        panel17 = new javax.swing.JPanel();
        panel18 = new javax.swing.JPanel();
        panel19 = new javax.swing.JPanel();
        panel20 = new javax.swing.JPanel();
        panel21 = new javax.swing.JPanel();
        panel22 = new javax.swing.JPanel();
        panel23 = new javax.swing.JPanel();
        panel24 = new javax.swing.JPanel();
        save_report_dokter = new javax.swing.JButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        fs = new javax.swing.JFileChooser();
        fs2 = new javax.swing.JFileChooser();
        fs3 = new javax.swing.JFileChooser();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        server_poli = new javax.swing.JSpinner();
        server_awal = new javax.swing.JSpinner();
        jScrollPane2 = new javax.swing.JScrollPane();
        output2 = new javax.swing.JTextArea();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        queue_capacity = new javax.swing.JSpinner();
        jLabel35 = new javax.swing.JLabel();
        server_poli2 = new javax.swing.JSpinner();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        server_poli3 = new javax.swing.JSpinner();
        jLabel38 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator13 = new javax.swing.JSeparator();
        slider = new javax.swing.JSlider();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        play = new javax.swing.JToggleButton();
        jButton1 = new javax.swing.JButton();
        pause = new javax.swing.JToggleButton();
        stop = new javax.swing.JToggleButton();
        report = new javax.swing.JButton();
        back = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        open = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        file_name = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel29 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        panelanimasi = new javax.swing.JPanel();
        loading = new javax.swing.JLabel();
        panelanimasi2 = new javax.swing.JPanel();
        loading1 = new javax.swing.JLabel();
        panelanimasi3 = new javax.swing.JPanel();
        loading2 = new javax.swing.JLabel();
        panelanimasi4 = new javax.swing.JPanel();
        loading3 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tabel1 = new javax.swing.JTable();
        file = new javax.swing.JRadioButton();
        acak = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        ser_awal = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        ser_pet = new javax.swing.JLabel();
        ser_per = new javax.swing.JLabel();
        ser_dok = new javax.swing.JLabel();
        label_grafik1 = new javax.swing.JLabel();
        label_grafik2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        param_grafik = new javax.swing.JSpinner();
        label_grafik3 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator12 = new javax.swing.JSeparator();

        fc.setApproveButtonText("Open");
        fc.setBackground(java.awt.Color.lightGray);
        fc.setCurrentDirectory(new java.io.File("C:\\Users\\SpongeBob\\Dropbox\\BACKUP PROG2\\SKRIPSI"));
        fc.setDialogTitle("Choose File");

        jFrame1.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        jFrame1.setBounds(new java.awt.Rectangle(500, 200, 600, 768));
        jFrame1.setMinimumSize(new java.awt.Dimension(800, 400));
        jFrame1.getContentPane().setLayout(null);

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel39.setText("jLabel39");
        jFrame1.getContentPane().add(jLabel39);
        jLabel39.setBounds(640, 160, 400, 13);

        jLabel40.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel40.setText("jLabel40");
        jFrame1.getContentPane().add(jLabel40);
        jLabel40.setBounds(640, 190, 400, 13);

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel41.setText("jLabel41");
        jFrame1.getContentPane().add(jLabel41);
        jLabel41.setBounds(640, 220, 400, 13);

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel42.setText("jLabel42");
        jFrame1.getContentPane().add(jLabel42);
        jLabel42.setBounds(640, 250, 400, 13);

        jLabel43.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel43.setText("jLabel43");
        jFrame1.getContentPane().add(jLabel43);
        jLabel43.setBounds(640, 280, 400, 13);

        jLabel44.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel44.setText("jLabel44");
        jFrame1.getContentPane().add(jLabel44);
        jLabel44.setBounds(640, 310, 400, 13);

        jLabel45.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel45.setText("jLabel45");
        jFrame1.getContentPane().add(jLabel45);
        jLabel45.setBounds(640, 340, 400, 13);

        jLabel46.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel46.setText("jLabel46");
        jFrame1.getContentPane().add(jLabel46);
        jLabel46.setBounds(640, 370, 410, 13);

        jLabel47.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel47.setText("jLabel47");
        jFrame1.getContentPane().add(jLabel47);
        jLabel47.setBounds(640, 400, 410, 13);

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel48.setText("Pemodelan dan Simulasi Antrian Pasien di Poliklinik");
        jFrame1.getContentPane().add(jLabel48);
        jLabel48.setBounds(120, 0, 660, 29);

        jLabel49.setText("Output Pelayanan Poliklinik : ");
        jFrame1.getContentPane().add(jLabel49);
        jLabel49.setBounds(710, 40, 138, 14);

        jLabel50.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel50.setText("jLabel50");
        jFrame1.getContentPane().add(jLabel50);
        jLabel50.setBounds(10, 460, 120, 13);

        jLabel51.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel51.setText("jLabel51");
        jFrame1.getContentPane().add(jLabel51);
        jLabel51.setBounds(150, 460, 100, 13);

        jLabel52.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel52.setText("jLabel52");
        jFrame1.getContentPane().add(jLabel52);
        jLabel52.setBounds(290, 460, 150, 13);

        output_poli.setEditable(false);
        output_poli.setColumns(20);
        output_poli.setRows(5);
        jScrollPane1.setViewportView(output_poli);

        jFrame1.getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(640, 70, 270, 70);
        jFrame1.getContentPane().add(jSeparator14);
        jSeparator14.setBounds(0, 27, 1090, 10);

        panel_grafik_poli.setLayout(null);

        loadingpoli.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        loadingpoli.setText("Generating chart ...");
        panel_grafik_poli.add(loadingpoli);
        loadingpoli.setBounds(276, 129, 160, 17);

        jTabbedPane2.addTab("Grafik 1", panel_grafik_poli);

        panel_grafik_poli2.setLayout(null);

        loadingpoli1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        loadingpoli1.setText("Generating chart ...");
        panel_grafik_poli2.add(loadingpoli1);
        loadingpoli1.setBounds(276, 129, 160, 17);

        jTabbedPane2.addTab("Grafik 2", panel_grafik_poli2);

        panel_grafik_poli3.setLayout(null);

        loadingpoli2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        loadingpoli2.setText("Generating chart ...");
        panel_grafik_poli3.add(loadingpoli2);
        loadingpoli2.setBounds(276, 129, 160, 17);

        jTabbedPane2.addTab("Grafik 3", panel_grafik_poli3);

        panel_grafik_poli4.setLayout(null);

        loadingpoli3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        loadingpoli3.setText("Generating chart ...");
        panel_grafik_poli4.add(loadingpoli3);
        loadingpoli3.setBounds(276, 129, 160, 17);

        jTabbedPane2.addTab("Grafik 4", panel_grafik_poli4);

        panel_grafik_poli5.setLayout(null);

        loadingpoli4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        loadingpoli4.setText("Generating chart ...");
        panel_grafik_poli5.add(loadingpoli4);
        loadingpoli4.setBounds(276, 129, 160, 17);

        jTabbedPane2.addTab("Grafik 5", panel_grafik_poli5);

        panel_grafik_poli6.setLayout(null);

        loadingpoli5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        loadingpoli5.setText("Generating chart ...");
        panel_grafik_poli6.add(loadingpoli5);
        loadingpoli5.setBounds(276, 129, 160, 17);

        jTabbedPane2.addTab("Grafik 6", panel_grafik_poli6);

        jFrame1.getContentPane().add(jTabbedPane2);
        jTabbedPane2.setBounds(0, 40, 640, 410);

        tabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tabel2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Server", "Pasien Ke", "Jenis", "Server Clock", "Service Time"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabel2.setRowHeight(29);
        jScrollPane8.setViewportView(tabel2);
        if (tabel2.getColumnModel().getColumnCount() > 0) {
            tabel2.getColumnModel().getColumn(1).setPreferredWidth(5);
        }

        jFrame1.getContentPane().add(jScrollPane8);
        jScrollPane8.setBounds(640, 160, 450, 310);

        param_grafik2.setFont(new java.awt.Font("Comic Sans MS", 0, 10)); // NOI18N
        param_grafik2.setModel(new javax.swing.SpinnerNumberModel(4, 4, 15, 1));
        jFrame1.getContentPane().add(param_grafik2);
        param_grafik2.setBounds(150, 530, 50, 20);

        jButton4.setFont(new java.awt.Font("Comic Sans MS", 0, 10)); // NOI18N
        jButton4.setText("Apply");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jFrame1.getContentPane().add(jButton4);
        jButton4.setBounds(100, 560, 57, 20);

        label_grafik5.setFont(new java.awt.Font("Comic Sans MS", 0, 10)); // NOI18N
        label_grafik5.setText("Jumlah pasien dilayani per");
        jFrame1.getContentPane().add(label_grafik5);
        label_grafik5.setBounds(10, 530, 140, 14);

        label_grafik6.setFont(new java.awt.Font("Comic Sans MS", 0, 10)); // NOI18N
        label_grafik6.setText("menit");
        jFrame1.getContentPane().add(label_grafik6);
        label_grafik6.setBounds(210, 530, 40, 14);

        label_grafik4.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        label_grafik4.setText("Ubah Parameter X Grafik :");
        jFrame1.getContentPane().add(label_grafik4);
        label_grafik4.setBounds(10, 500, 220, 17);
        jFrame1.getContentPane().add(jSeparator19);
        jSeparator19.setBounds(0, 488, 640, 2);

        frame_report.setBounds(new java.awt.Rectangle(0, 0, 800, 660));
        frame_report.getContentPane().setLayout(null);

        javax.swing.GroupLayout panel_tabelLayout = new javax.swing.GroupLayout(panel_tabel);
        panel_tabel.setLayout(panel_tabelLayout);
        panel_tabelLayout.setHorizontalGroup(
            panel_tabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 740, Short.MAX_VALUE)
        );
        panel_tabelLayout.setVerticalGroup(
            panel_tabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 430, Short.MAX_VALUE)
        );

        frame_report.getContentPane().add(panel_tabel);
        panel_tabel.setBounds(0, 37, 740, 430);

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel55.setText("Tabel Hasil Pemodelan dan Simulasi Antrian Pasien Pendaftaran Awal");
        frame_report.getContentPane().add(jLabel55);
        jLabel55.setBounds(130, 0, 480, 20);
        frame_report.getContentPane().add(jSeparator17);
        jSeparator17.setBounds(0, 28, 740, 2);

        pasien_lama.setText("FF");
        frame_report.getContentPane().add(pasien_lama);
        pasien_lama.setBounds(10, 510, 190, 14);

        pasien_baru.setText("jLabel56");
        frame_report.getContentPane().add(pasien_baru);
        pasien_baru.setBounds(230, 510, 230, 14);

        pasien_emergency.setText("jLabel56");
        frame_report.getContentPane().add(pasien_emergency);
        pasien_emergency.setBounds(500, 510, 200, 14);

        total.setText("jLabel56");
        frame_report.getContentPane().add(total);
        total.setBounds(10, 480, 220, 14);

        save_table1.setText("Save Table");
        save_table1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_table1ActionPerformed(evt);
            }
        });
        frame_report.getContentPane().add(save_table1);
        save_table1.setBounds(310, 560, 120, 23);

        frame_report2.setBounds(new java.awt.Rectangle(500, 0, 798, 660));
        frame_report2.setMinimumSize(new java.awt.Dimension(250, 100));
        frame_report2.getContentPane().setLayout(null);

        total_emergency_poli.setText("jLabel57");
        frame_report2.getContentPane().add(total_emergency_poli);
        total_emergency_poli.setBounds(560, 530, 270, 14);

        total_bpjsl_poli.setText("jLabel57");
        frame_report2.getContentPane().add(total_bpjsl_poli);
        total_bpjsl_poli.setBounds(290, 530, 260, 14);

        total_bpjsb_poli.setText("jLabel57");
        frame_report2.getContentPane().add(total_bpjsb_poli);
        total_bpjsb_poli.setBounds(10, 530, 260, 14);

        total_poli.setText("jLabel57");
        frame_report2.getContentPane().add(total_poli);
        total_poli.setBounds(10, 490, 230, 14);

        javax.swing.GroupLayout panel_poliLayout = new javax.swing.GroupLayout(panel_poli);
        panel_poli.setLayout(panel_poliLayout);
        panel_poliLayout.setHorizontalGroup(
            panel_poliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 910, Short.MAX_VALUE)
        );
        panel_poliLayout.setVerticalGroup(
            panel_poliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 420, Short.MAX_VALUE)
        );

        frame_report2.getContentPane().add(panel_poli);
        panel_poli.setBounds(0, 60, 910, 420);

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel56.setText("Tabel Hasil Pemodelan dan Simulasi Antrian Pasien di Poliklinik");
        frame_report2.getContentPane().add(jLabel56);
        jLabel56.setBounds(200, 0, 470, 30);
        frame_report2.getContentPane().add(jSeparator18);
        jSeparator18.setBounds(0, 40, 910, 10);

        save_table2.setText("Save Table");
        save_table2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_table2ActionPerformed(evt);
            }
        });
        frame_report2.getContentPane().add(save_table2);
        save_table2.setBounds(260, 580, 120, 23);

        frame_report3.setBounds(new java.awt.Rectangle(0, 0, 987, 660));
        frame_report3.getContentPane().setLayout(null);

        hasil.setEditable(false);
        hasil.setColumns(20);
        hasil.setRows(5);
        jScrollPane3.setViewportView(hasil);

        frame_report3.getContentPane().add(jScrollPane3);
        jScrollPane3.setBounds(740, 50, 300, 410);

        javax.swing.GroupLayout panel_service_rateLayout = new javax.swing.GroupLayout(panel_service_rate);
        panel_service_rate.setLayout(panel_service_rateLayout);
        panel_service_rateLayout.setHorizontalGroup(
            panel_service_rateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
        );
        panel_service_rateLayout.setVerticalGroup(
            panel_service_rateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        frame_report3.getContentPane().add(panel_service_rate);
        panel_service_rate.setBounds(10, 640, 720, 70);

        javax.swing.GroupLayout panel_delay_timeLayout = new javax.swing.GroupLayout(panel_delay_time);
        panel_delay_time.setLayout(panel_delay_timeLayout);
        panel_delay_timeLayout.setHorizontalGroup(
            panel_delay_timeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
        );
        panel_delay_timeLayout.setVerticalGroup(
            panel_delay_timeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        frame_report3.getContentPane().add(panel_delay_time);
        panel_delay_time.setBounds(10, 550, 720, 70);

        javax.swing.GroupLayout panel_waiting_timeLayout = new javax.swing.GroupLayout(panel_waiting_time);
        panel_waiting_time.setLayout(panel_waiting_timeLayout);
        panel_waiting_timeLayout.setHorizontalGroup(
            panel_waiting_timeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
        panel_waiting_timeLayout.setVerticalGroup(
            panel_waiting_timeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        frame_report3.getContentPane().add(panel_waiting_time);
        panel_waiting_time.setBounds(0, 480, 730, 70);

        javax.swing.GroupLayout panel_interarrivalLayout = new javax.swing.GroupLayout(panel_interarrival);
        panel_interarrival.setLayout(panel_interarrivalLayout);
        panel_interarrivalLayout.setHorizontalGroup(
            panel_interarrivalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
        panel_interarrivalLayout.setVerticalGroup(
            panel_interarrivalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        frame_report3.getContentPane().add(panel_interarrival);
        panel_interarrival.setBounds(0, 400, 730, 70);

        javax.swing.GroupLayout panel_arrival_rateLayout = new javax.swing.GroupLayout(panel_arrival_rate);
        panel_arrival_rate.setLayout(panel_arrival_rateLayout);
        panel_arrival_rateLayout.setHorizontalGroup(
            panel_arrival_rateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
        panel_arrival_rateLayout.setVerticalGroup(
            panel_arrival_rateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        frame_report3.getContentPane().add(panel_arrival_rate);
        panel_arrival_rate.setBounds(0, 310, 730, 70);

        javax.swing.GroupLayout panel_averageLayout = new javax.swing.GroupLayout(panel_average);
        panel_average.setLayout(panel_averageLayout);
        panel_averageLayout.setHorizontalGroup(
            panel_averageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
        panel_averageLayout.setVerticalGroup(
            panel_averageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        frame_report3.getContentPane().add(panel_average);
        panel_average.setBounds(0, 230, 730, 70);

        panel_tabel_utility.setInheritsPopupMenu(true);
        panel_tabel_utility.setMinimumSize(new java.awt.Dimension(750, 400));
        panel_tabel_utility.setPreferredSize(new java.awt.Dimension(0, 0));

        javax.swing.GroupLayout panel_tabel_utilityLayout = new javax.swing.GroupLayout(panel_tabel_utility);
        panel_tabel_utility.setLayout(panel_tabel_utilityLayout);
        panel_tabel_utilityLayout.setHorizontalGroup(
            panel_tabel_utilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );
        panel_tabel_utilityLayout.setVerticalGroup(
            panel_tabel_utilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );

        frame_report3.getContentPane().add(panel_tabel_utility);
        panel_tabel_utility.setBounds(0, 140, 730, 70);

        javax.swing.GroupLayout panel_pasienLayout = new javax.swing.GroupLayout(panel_pasien);
        panel_pasien.setLayout(panel_pasienLayout);
        panel_pasienLayout.setHorizontalGroup(
            panel_pasienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
        panel_pasienLayout.setVerticalGroup(
            panel_pasienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        frame_report3.getContentPane().add(panel_pasien);
        panel_pasien.setBounds(0, 50, 730, 70);

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel53.setText("Analisis Hasil Pemodelan dan Simulasi Antrian Pasien Pendaftaran Awal");
        frame_report3.getContentPane().add(jLabel53);
        jLabel53.setBounds(320, 0, 500, 30);
        frame_report3.getContentPane().add(jSeparator15);
        jSeparator15.setBounds(0, 40, 1110, 10);

        save_report_awal.setText("Save to Excel");
        save_report_awal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_report_awalActionPerformed(evt);
            }
        });
        frame_report3.getContentPane().add(save_report_awal);
        save_report_awal.setBounds(840, 480, 120, 23);

        frame_report4.getContentPane().setLayout(null);

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel54.setText("HASIL ANALISIS PEMODELAN DAN SIMULASI ANTRIAN PASIEN DI POLIKLINIK");
        frame_report4.getContentPane().add(jLabel54);
        jLabel54.setBounds(150, 0, 730, 40);
        frame_report4.getContentPane().add(jSeparator16);
        jSeparator16.setBounds(0, 40, 1090, 10);

        panel_petugas.setLayout(null);

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        panel_petugas.add(panel1);
        panel1.setBounds(10, 0, 730, 70);

        output_petugas.setEditable(false);
        output_petugas.setColumns(20);
        output_petugas.setRows(5);
        jScrollPane4.setViewportView(output_petugas);

        panel_petugas.add(jScrollPane4);
        jScrollPane4.setBounds(749, 1, 310, 390);

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        panel_petugas.add(panel2);
        panel2.setBounds(10, 70, 730, 70);

        javax.swing.GroupLayout panel3Layout = new javax.swing.GroupLayout(panel3);
        panel3.setLayout(panel3Layout);
        panel3Layout.setHorizontalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
        panel3Layout.setVerticalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        panel_petugas.add(panel3);
        panel3.setBounds(10, 150, 730, 70);

        javax.swing.GroupLayout panel4Layout = new javax.swing.GroupLayout(panel4);
        panel4.setLayout(panel4Layout);
        panel4Layout.setHorizontalGroup(
            panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
        panel4Layout.setVerticalGroup(
            panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        panel_petugas.add(panel4);
        panel4.setBounds(10, 230, 730, 70);

        javax.swing.GroupLayout panel5Layout = new javax.swing.GroupLayout(panel5);
        panel5.setLayout(panel5Layout);
        panel5Layout.setHorizontalGroup(
            panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
        panel5Layout.setVerticalGroup(
            panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        panel_petugas.add(panel5);
        panel5.setBounds(10, 310, 730, 70);

        javax.swing.GroupLayout panel6Layout = new javax.swing.GroupLayout(panel6);
        panel6.setLayout(panel6Layout);
        panel6Layout.setHorizontalGroup(
            panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
        panel6Layout.setVerticalGroup(
            panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        panel_petugas.add(panel6);
        panel6.setBounds(10, 390, 730, 70);

        javax.swing.GroupLayout panel7Layout = new javax.swing.GroupLayout(panel7);
        panel7.setLayout(panel7Layout);
        panel7Layout.setHorizontalGroup(
            panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
        panel7Layout.setVerticalGroup(
            panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        panel_petugas.add(panel7);
        panel7.setBounds(10, 470, 730, 70);

        javax.swing.GroupLayout panel8Layout = new javax.swing.GroupLayout(panel8);
        panel8.setLayout(panel8Layout);
        panel8Layout.setHorizontalGroup(
            panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
        panel8Layout.setVerticalGroup(
            panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        panel_petugas.add(panel8);
        panel8.setBounds(10, 550, 730, 70);

        save_report_petugas.setText("Save to Excel");
        save_report_petugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_report_petugasActionPerformed(evt);
            }
        });
        panel_petugas.add(save_report_petugas);
        save_report_petugas.setBounds(860, 420, 120, 23);

        tab1.addTab("Petugas", panel_petugas);

        panel_perawat.setLayout(null);

        output_perawat.setEditable(false);
        output_perawat.setColumns(20);
        output_perawat.setRows(5);
        jScrollPane5.setViewportView(output_perawat);

        panel_perawat.add(jScrollPane5);
        jScrollPane5.setBounds(755, 0, 320, 380);

        javax.swing.GroupLayout panel9Layout = new javax.swing.GroupLayout(panel9);
        panel9.setLayout(panel9Layout);
        panel9Layout.setHorizontalGroup(
            panel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );
        panel9Layout.setVerticalGroup(
            panel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        panel_perawat.add(panel9);
        panel9.setBounds(0, 0, 750, 70);

        javax.swing.GroupLayout panel10Layout = new javax.swing.GroupLayout(panel10);
        panel10.setLayout(panel10Layout);
        panel10Layout.setHorizontalGroup(
            panel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );
        panel10Layout.setVerticalGroup(
            panel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        panel_perawat.add(panel10);
        panel10.setBounds(0, 80, 750, 70);

        javax.swing.GroupLayout panel11Layout = new javax.swing.GroupLayout(panel11);
        panel11.setLayout(panel11Layout);
        panel11Layout.setHorizontalGroup(
            panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );
        panel11Layout.setVerticalGroup(
            panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        panel_perawat.add(panel11);
        panel11.setBounds(0, 160, 750, 70);

        javax.swing.GroupLayout panel12Layout = new javax.swing.GroupLayout(panel12);
        panel12.setLayout(panel12Layout);
        panel12Layout.setHorizontalGroup(
            panel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );
        panel12Layout.setVerticalGroup(
            panel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        panel_perawat.add(panel12);
        panel12.setBounds(0, 240, 750, 70);

        javax.swing.GroupLayout panel13Layout = new javax.swing.GroupLayout(panel13);
        panel13.setLayout(panel13Layout);
        panel13Layout.setHorizontalGroup(
            panel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );
        panel13Layout.setVerticalGroup(
            panel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        panel_perawat.add(panel13);
        panel13.setBounds(0, 320, 750, 70);

        javax.swing.GroupLayout panel14Layout = new javax.swing.GroupLayout(panel14);
        panel14.setLayout(panel14Layout);
        panel14Layout.setHorizontalGroup(
            panel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );
        panel14Layout.setVerticalGroup(
            panel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        panel_perawat.add(panel14);
        panel14.setBounds(0, 400, 750, 70);

        javax.swing.GroupLayout panel15Layout = new javax.swing.GroupLayout(panel15);
        panel15.setLayout(panel15Layout);
        panel15Layout.setHorizontalGroup(
            panel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );
        panel15Layout.setVerticalGroup(
            panel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        panel_perawat.add(panel15);
        panel15.setBounds(0, 470, 750, 70);

        javax.swing.GroupLayout panel16Layout = new javax.swing.GroupLayout(panel16);
        panel16.setLayout(panel16Layout);
        panel16Layout.setHorizontalGroup(
            panel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );
        panel16Layout.setVerticalGroup(
            panel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        panel_perawat.add(panel16);
        panel16.setBounds(0, 550, 750, 70);

        save_report_perawat.setText("Save to Excel");
        save_report_perawat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_report_perawatActionPerformed(evt);
            }
        });
        panel_perawat.add(save_report_perawat);
        save_report_perawat.setBounds(860, 400, 110, 23);

        tab1.addTab("Perawat", panel_perawat);

        panel_dokter.setLayout(null);

        output_dokter.setEditable(false);
        output_dokter.setColumns(20);
        output_dokter.setRows(5);
        jScrollPane6.setViewportView(output_dokter);

        panel_dokter.add(jScrollPane6);
        jScrollPane6.setBounds(735, 0, 350, 400);

        javax.swing.GroupLayout panel17Layout = new javax.swing.GroupLayout(panel17);
        panel17.setLayout(panel17Layout);
        panel17Layout.setHorizontalGroup(
            panel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
        panel17Layout.setVerticalGroup(
            panel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        panel_dokter.add(panel17);
        panel17.setBounds(0, 0, 730, 70);

        javax.swing.GroupLayout panel18Layout = new javax.swing.GroupLayout(panel18);
        panel18.setLayout(panel18Layout);
        panel18Layout.setHorizontalGroup(
            panel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
        panel18Layout.setVerticalGroup(
            panel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        panel_dokter.add(panel18);
        panel18.setBounds(0, 70, 730, 70);

        javax.swing.GroupLayout panel19Layout = new javax.swing.GroupLayout(panel19);
        panel19.setLayout(panel19Layout);
        panel19Layout.setHorizontalGroup(
            panel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
        panel19Layout.setVerticalGroup(
            panel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        panel_dokter.add(panel19);
        panel19.setBounds(0, 140, 730, 70);

        javax.swing.GroupLayout panel20Layout = new javax.swing.GroupLayout(panel20);
        panel20.setLayout(panel20Layout);
        panel20Layout.setHorizontalGroup(
            panel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
        panel20Layout.setVerticalGroup(
            panel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        panel_dokter.add(panel20);
        panel20.setBounds(0, 210, 730, 70);

        javax.swing.GroupLayout panel21Layout = new javax.swing.GroupLayout(panel21);
        panel21.setLayout(panel21Layout);
        panel21Layout.setHorizontalGroup(
            panel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
        panel21Layout.setVerticalGroup(
            panel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        panel_dokter.add(panel21);
        panel21.setBounds(0, 290, 730, 70);

        javax.swing.GroupLayout panel22Layout = new javax.swing.GroupLayout(panel22);
        panel22.setLayout(panel22Layout);
        panel22Layout.setHorizontalGroup(
            panel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
        panel22Layout.setVerticalGroup(
            panel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        panel_dokter.add(panel22);
        panel22.setBounds(0, 370, 730, 70);

        javax.swing.GroupLayout panel23Layout = new javax.swing.GroupLayout(panel23);
        panel23.setLayout(panel23Layout);
        panel23Layout.setHorizontalGroup(
            panel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
        panel23Layout.setVerticalGroup(
            panel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        panel_dokter.add(panel23);
        panel23.setBounds(0, 450, 730, 70);

        javax.swing.GroupLayout panel24Layout = new javax.swing.GroupLayout(panel24);
        panel24.setLayout(panel24Layout);
        panel24Layout.setHorizontalGroup(
            panel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
        panel24Layout.setVerticalGroup(
            panel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        panel_dokter.add(panel24);
        panel24.setBounds(0, 540, 730, 70);

        save_report_dokter.setText("Save to Excel");
        save_report_dokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_report_dokterActionPerformed(evt);
            }
        });
        panel_dokter.add(save_report_dokter);
        save_report_dokter.setBounds(850, 430, 120, 23);

        tab1.addTab("Dokter", panel_dokter);

        frame_report4.getContentPane().add(tab1);
        tab1.setBounds(2, 50, 1090, 670);

        fs.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);

        fs2.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);

        fs3.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);

        jSeparator11.setOrientation(javax.swing.SwingConstants.VERTICAL);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 1152, 768));
        setMinimumSize(new java.awt.Dimension(1000, 768));
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel1.setText("Jumlah Server  Pendaftaran Awal : ");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 50, 230, 13);

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("PEMODELAN & SIMULASI ANTRIAN PASIEN  RS TNI AU DR.M.SALAMUN");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(200, 0, 750, 23);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel3.setText("Jumlah Server Poilklinik  :  ");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(48, 110, 200, 13);

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel13.setText("Loket");
        getContentPane().add(jLabel13);
        jLabel13.setBounds(300, 50, 60, 13);

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel14.setText("Dokter");
        getContentPane().add(jLabel14);
        jLabel14.setBounds(300, 110, 90, 13);

        server_poli.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        server_poli.setModel(new javax.swing.SpinnerNumberModel(1, 1, 3, 1));
        getContentPane().add(server_poli);
        server_poli.setBounds(250, 110, 39, 20);

        server_awal.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        server_awal.setModel(new javax.swing.SpinnerNumberModel(1, 1, 10, 1));
        getContentPane().add(server_awal);
        server_awal.setBounds(250, 50, 39, 20);

        output2.setEditable(false);
        output2.setColumns(20);
        output2.setRows(5);
        output2.setText("Output Statistik Kedatangan: ");
        jScrollPane2.setViewportView(output2);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(730, 180, 279, 70);

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel20.setText("jLabel20");
        getContentPane().add(jLabel20);
        jLabel20.setBounds(690, 290, 380, 13);

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel21.setText("jLabel21");
        getContentPane().add(jLabel21);
        jLabel21.setBounds(690, 320, 380, 13);

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel22.setText("jLabel22");
        getContentPane().add(jLabel22);
        jLabel22.setBounds(690, 350, 380, 13);

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel23.setText("jLabel23");
        getContentPane().add(jLabel23);
        jLabel23.setBounds(690, 380, 380, 13);

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel24.setText("jLabel24");
        getContentPane().add(jLabel24);
        jLabel24.setBounds(690, 410, 380, 13);

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel25.setText("jLabel25");
        getContentPane().add(jLabel25);
        jLabel25.setBounds(690, 440, 380, 13);

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel26.setText("jLabel26");
        getContentPane().add(jLabel26);
        jLabel26.setBounds(690, 470, 380, 13);

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel27.setText("jLabel27");
        getContentPane().add(jLabel27);
        jLabel27.setBounds(690, 500, 380, 13);

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel28.setText("jLabel28");
        getContentPane().add(jLabel28);
        jLabel28.setBounds(690, 530, 380, 13);

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel30.setText("jLabel30");
        getContentPane().add(jLabel30);
        jLabel30.setBounds(730, 250, 90, 13);

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel31.setText("jLabel31");
        getContentPane().add(jLabel31);
        jLabel31.setBounds(820, 250, 100, 13);

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel32.setText("jLabel32");
        getContentPane().add(jLabel32);
        jLabel32.setBounds(940, 250, 120, 13);

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel33.setText("Kapasitas Antrian Awal :");
        getContentPane().add(jLabel33);
        jLabel33.setBounds(390, 50, 160, 13);

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel34.setText("pasien");
        getContentPane().add(jLabel34);
        jLabel34.setBounds(600, 50, 70, 13);

        queue_capacity.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        queue_capacity.setModel(new javax.swing.SpinnerNumberModel(1, 1, 10, 1));
        getContentPane().add(queue_capacity);
        queue_capacity.setBounds(550, 50, 39, 20);

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel35.setText("Jumlah Server Poliklinik :");
        getContentPane().add(jLabel35);
        jLabel35.setBounds(52, 80, 190, 13);

        server_poli2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        server_poli2.setModel(new javax.swing.SpinnerNumberModel(1, 1, 3, 1));
        getContentPane().add(server_poli2);
        server_poli2.setBounds(250, 80, 40, 20);

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel36.setText("Perawat");
        getContentPane().add(jLabel36);
        jLabel36.setBounds(300, 80, 80, 13);

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel37.setText("Jumlah Server Poliklinik :");
        getContentPane().add(jLabel37);
        jLabel37.setBounds(50, 140, 190, 13);

        server_poli3.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        server_poli3.setModel(new javax.swing.SpinnerNumberModel(1, 1, 3, 1));
        getContentPane().add(server_poli3);
        server_poli3.setBounds(250, 140, 40, 20);

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel38.setText("Petugas");
        getContentPane().add(jLabel38);
        jLabel38.setBounds(300, 140, 80, 13);

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator4);
        jSeparator4.setBounds(360, 30, 20, 140);
        getContentPane().add(jSeparator6);
        jSeparator6.setBounds(0, 170, 280, 2);
        getContentPane().add(jSeparator7);
        jSeparator7.setBounds(270, 170, 400, 10);
        getContentPane().add(jSeparator9);
        jSeparator9.setBounds(670, 170, 440, 20);
        getContentPane().add(jSeparator13);
        jSeparator13.setBounds(0, 30, 1110, 10);

        slider.setMaximum(900);
        slider.setMinimum(100);
        slider.setValue(500);
        slider.setEnabled(false);
        slider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderStateChanged(evt);
            }
        });
        getContentPane().add(slider);
        slider.setBounds(10, 620, 390, 26);

        jLabel9.setText("Slower");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(10, 650, 60, 10);

        jLabel8.setText("Real Time");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(180, 650, 80, 14);

        jLabel10.setText("Faster");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(370, 650, 40, 14);

        play.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        play.setText("Play");
        play.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playActionPerformed(evt);
            }
        });
        getContentPane().add(play);
        play.setBounds(440, 620, 60, 21);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jButton1.setText("GET TABLE");
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(440, 650, 110, 21);

        pause.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        pause.setText("Pause");
        pause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pauseActionPerformed(evt);
            }
        });
        getContentPane().add(pause);
        pause.setBounds(520, 620, 59, 21);

        stop.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        stop.setText("Stop");
        stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopActionPerformed(evt);
            }
        });
        getContentPane().add(stop);
        stop.setBounds(600, 620, 60, 21);

        report.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        report.setText("GET REPORT");
        report.setEnabled(false);
        report.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportActionPerformed(evt);
            }
        });
        getContentPane().add(report);
        report.setBounds(560, 650, 100, 21);

        back.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        back.setText("Back to Main Menu");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });
        getContentPane().add(back);
        back.setBounds(950, 630, 150, 30);

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton2.setText("RESET");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(960, 590, 130, 23);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel4.setText("Pilih File Excel :");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(390, 90, 110, 20);

        open.setText("OPEN FILE");
        open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openActionPerformed(evt);
            }
        });
        getContentPane().add(open);
        open.setBounds(550, 90, 100, 23);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel5.setText("Nama File : ");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(390, 140, 80, 13);

        file_name.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        getContentPane().add(file_name);
        file_name.setBounds(490, 130, 350, 30);
        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(390, 130, 460, 10);
        getContentPane().add(jSeparator2);
        jSeparator2.setBounds(390, 160, 460, 10);

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel29.setText("jLabel29");
        getContentPane().add(jLabel29);
        jLabel29.setBounds(690, 560, 340, 20);

        panelanimasi.setLayout(null);

        loading.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        loading.setText("Generating chart ...");
        panelanimasi.add(loading);
        loading.setBounds(280, 150, 200, 22);

        jTabbedPane1.addTab("Grafik 1", panelanimasi);

        panelanimasi2.setLayout(null);

        loading1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        loading1.setText("Generating chart ...");
        panelanimasi2.add(loading1);
        loading1.setBounds(280, 150, 210, 22);

        jTabbedPane1.addTab("Grafik 2", panelanimasi2);

        panelanimasi3.setLayout(null);

        loading2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        loading2.setText("Generating chart ...");
        panelanimasi3.add(loading2);
        loading2.setBounds(280, 150, 210, 22);

        jTabbedPane1.addTab("Grafik 3", panelanimasi3);

        panelanimasi4.setLayout(null);

        loading3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        loading3.setText("Generating chart ...");
        panelanimasi4.add(loading3);
        loading3.setBounds(280, 150, 210, 22);

        jTabbedPane1.addTab("Grafik 4", panelanimasi4);

        getContentPane().add(jTabbedPane1);
        jTabbedPane1.setBounds(0, 180, 680, 430);

        tabel1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Server Ke", "Pasien Ke", "Jenis", "Server Clock", "Service Time"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabel1.setRowHeight(27);
        tabel1.getTableHeader().setReorderingAllowed(false);
        jScrollPane7.setViewportView(tabel1);
        if (tabel1.getColumnModel().getColumnCount() > 0) {
            tabel1.getColumnModel().getColumn(0).setPreferredWidth(5);
            tabel1.getColumnModel().getColumn(1).setPreferredWidth(5);
            tabel1.getColumnModel().getColumn(2).setPreferredWidth(8);
        }

        getContentPane().add(jScrollPane7);
        jScrollPane7.setBounds(680, 270, 440, 310);

        buttonGroup1.add(file);
        file.setText("input file");
        file.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileActionPerformed(evt);
            }
        });
        getContentPane().add(file);
        file.setBounds(760, 50, 90, 20);

        buttonGroup1.add(acak);
        acak.setText("input acak");
        acak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acakActionPerformed(evt);
            }
        });
        getContentPane().add(acak);
        acak.setBounds(760, 80, 90, 20);

        jLabel11.setText("Pilih Input : ");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(690, 70, 100, 14);

        ser_awal.setText("jLabel6");
        getContentPane().add(ser_awal);
        ser_awal.setBounds(860, 50, 260, 14);

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator3);
        jSeparator3.setBounds(850, 30, 10, 140);

        ser_pet.setText("jLabel6");
        getContentPane().add(ser_pet);
        ser_pet.setBounds(860, 80, 260, 14);

        ser_per.setText("jLabel6");
        getContentPane().add(ser_per);
        ser_per.setBounds(860, 110, 260, 14);

        ser_dok.setText("jLabel6");
        getContentPane().add(ser_dok);
        ser_dok.setBounds(860, 140, 250, 14);

        label_grafik1.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        label_grafik1.setText("Ubah Parameter X Grafik ");
        getContentPane().add(label_grafik1);
        label_grafik1.setBounds(690, 590, 160, 17);

        label_grafik2.setFont(new java.awt.Font("Comic Sans MS", 0, 10)); // NOI18N
        label_grafik2.setText("jumlah pasien dilayani per ");
        getContentPane().add(label_grafik2);
        label_grafik2.setBounds(690, 620, 140, 20);

        jButton3.setFont(new java.awt.Font("Comic Sans MS", 0, 10)); // NOI18N
        jButton3.setText("Apply");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(770, 650, 60, 23);

        param_grafik.setFont(new java.awt.Font("Comic Sans MS", 0, 10)); // NOI18N
        param_grafik.setModel(new javax.swing.SpinnerNumberModel(4, 4, 15, 1));
        getContentPane().add(param_grafik);
        param_grafik.setBounds(820, 620, 50, 20);

        label_grafik3.setFont(new java.awt.Font("Comic Sans MS", 0, 10)); // NOI18N
        label_grafik3.setText("menit");
        getContentPane().add(label_grafik3);
        label_grafik3.setBounds(880, 620, 26, 14);

        jSeparator8.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator8);
        jSeparator8.setBounds(918, 582, 10, 100);

        jSeparator10.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator10);
        jSeparator10.setBounds(410, 610, 20, 80);

        jSeparator12.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator12);
        jSeparator12.setBounds(680, 610, 10, 70);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderStateChanged
        // TODO add your handling code here:
        int value=slider.getValue();
        int temp=0;
        if(value>500){
            temp=value-500;
            value=500-temp;
        }
        else if(value<500){
            temp=500-value;
            value=500+temp;
        }
        sim.setSlidervalue(value);
        for(int i=0;i<arrayServer.length;i++){
            arrayServer[i].setSlidervalue(value);
        }
        sim2.setSlidervalue(value);
        for(int i=0;i<arrayPetugas.length;i++){
            arrayPetugas[i].setSlidervalue(value);
        }
        for(int i=0;i<arrayPerawat.length;i++){
            arrayPerawat[i].setSlidervalue(value);
        }
        for(int i=0;i<arrayDokter.length;i++){
            arrayDokter[i].setSlidervalue(value);
        }
        System.out.println(value);
        
    }//GEN-LAST:event_sliderStateChanged

    private void playActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playActionPerformed
        // TODO add your handling code here:
         //Simulation sim;
        if(counter==0){
            if(buttonGroup1.isSelected(acak.getModel())){
                   if(file_name.getText().equals("")||file_name.getText().equals(null)){
                        JOptionPane.showMessageDialog(this,"Anda belum memilih file excel!!","Alert",JOptionPane.ERROR_MESSAGE);
                    }
                   else{
                       disableParamGrafik();     
                       if(this.excel.createFile()){
                                if(this.excel.readExcelLimited()){
                                    disableTable();
                                    disablePanel();
                                    pasien_lama.setText("");
                                    pasien_baru.setText("");
                                    pasien_emergency.setText("");
                                    output_poli.setText("");
                                    hasil.setText("");
                                    total_bpjsl_poli.setText("");
                                    total_bpjsb_poli.setText("");
                                    total_emergency_poli.setText("");
                                    total.setText("");
                                    total_poli.setText("");
                                    output_petugas.setText("");
                                    output_perawat.setText("");
                                    output_dokter.setText("");
                                    hideLoading();
                                    hideLoadingPoli();
                                    disableSave();
                                    disableServer();
                                    play.setEnabled(false);
                                    jButton2.setEnabled(false);
                                    pause.setEnabled(true);
                                    stop.setEnabled(true);
                                    slider.setEnabled(true);
                                    this.disableReportTable();
                                    this.disableAllInput();
                                    back.setEnabled(false);
                                    jLabel30.setVisible(true);
                                    jLabel31.setVisible(true);
                                    jLabel32.setVisible(true);
                                    gen=new StatisticsGenerator();
                                    gen.setExcel(excel);
                                    int serverawalvalue=(int)server_awal.getValue();
                                    int serverpolivalue=(int)server_poli.getValue();
                                    int serverpolivalue2=(int)server_poli2.getValue();
                                    int serverpolivalue3=(int)server_poli3.getValue();
                                    arrayServer=new ServerAwal[serverawalvalue];
                                    arrayDokter=new ServerDokter[serverpolivalue];
                                    arrayPerawat=new ServerPerawat[serverpolivalue2];
                                    arrayPetugas=new ServerPetugas[serverpolivalue3];
                                    for(int i=0;i<arrayServer.length;i++){
                                            arrayServer[i]=new ServerAwal(i+1,gen,this);

                                    }
                                    sim=new StatisticsSimulationAwal(this.excel,arrayServer,gen,this,this.excel.getKapasitasantrian());
                                    for(int i=0;i<arrayDokter.length;i++){
                                            arrayDokter[i]=new ServerDokter(i+1,gen,this);
                                            arrayDokter[i].start();
                                    }
                                    for(int i=0;i<arrayPerawat.length;i++){
                                            arrayPerawat[i]=new ServerPerawat(i+1,gen,this,arrayDokter);
                                            arrayPerawat[i].start();
                                    }
                                    for(int i=0;i<arrayPetugas.length;i++){
                                            arrayPetugas[i]=new ServerPetugas(i+1,gen,this,arrayPerawat);
                                            arrayPetugas[i].start();
                                    }
                                    sim2=new StatisticsSimulationPoli(arrayPetugas,gen,this,this.excel);
                                    sim2.setServerperawat(arrayPerawat);
                                    sim2.setServerdokter(arrayDokter);
                                    sim2.start();
                                    for(int i=0;i<arrayServer.length;i++){
                                            arrayServer[i].setSim(sim2);
                                            arrayServer[i].start();
                                    }
                                    sim.setCekInput("acak");
                                    sim.setPoli(sim2);
                                    sim.setServerpetugas(arrayPetugas);
                                    sim.setServerperawat(arrayPerawat);
                                    sim.setServerdokter(arrayDokter);
                                    sim.start();
                                    System.out.println("played");
                                }
                                else{
                                    showErrorReading();
                                }
                            }
                            else{
                                showErrorReading();
                            }

                        }
            }
            else if(buttonGroup1.isSelected(file.getModel())){
                    if(file_name.getText().equals("")||file_name.getText().equals(null)){
                        JOptionPane.showMessageDialog(this,"Anda belum memilih file excel!!","Alert",JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                            if(this.excel.createFile()){
                                if(this.excel.readExcel()){
                                    disableTable();
                                    disablePanel();
                                    pasien_lama.setText("");
                                    pasien_baru.setText("");
                                    pasien_emergency.setText("");
                                    output_poli.setText("");
                                    hasil.setText("");
                                    total_bpjsl_poli.setText("");
                                    total_bpjsb_poli.setText("");
                                    total_emergency_poli.setText("");
                                    total.setText("");
                                    total_poli.setText("");
                                    output_petugas.setText("");
                                    output_perawat.setText("");
                                    output_dokter.setText("");
                                    hideLoading();
                                    hideLoadingPoli();
                                    disableSave();
                                    play.setEnabled(false);
                                    jButton2.setEnabled(false);
                                    pause.setEnabled(true);
                                    stop.setEnabled(true);
                                    slider.setEnabled(true);
                                    this.disableReportTable();
                                    this.disableAllInput();
                                    back.setEnabled(false);
                                    enableServer();
                                    jLabel30.setVisible(true);
                                    jLabel31.setVisible(true);
                                    jLabel32.setVisible(true);
                                    ser_awal.setText("Server Pendaftaran Awal : "+this.excel.getServerawal()+" loket");
                                    ser_pet.setText("Petugas : "+this.excel.getServerpetugas()+" orang");
                                    ser_per.setText("Perawat : "+this.excel.getServerperawat()+" orang");
                                    ser_dok.setText("Dokter : "+this.excel.getServerdokter()+" orang");
                                    gen=new StatisticsGenerator();
                                    gen.setExcel(excel);
                                    int serverawalvalue=(int)this.excel.getServerawal();
                                    int serverpolivalue=(int)this.excel.getServerpetugas();
                                    int serverpolivalue2=(int)this.excel.getServerperawat();
                                    int serverpolivalue3=(int)this.excel.getServerdokter();
                                    arrayServer=new ServerAwal[serverawalvalue];
                                    arrayDokter=new ServerDokter[serverpolivalue3];
                                    arrayPerawat=new ServerPerawat[serverpolivalue2];
                                    arrayPetugas=new ServerPetugas[serverpolivalue];
                                    for(int i=0;i<arrayServer.length;i++){
                                            arrayServer[i]=new ServerAwal(i+1,gen,this);

                                    }
                                    sim=new StatisticsSimulationAwal(this.excel,arrayServer,gen,this,this.excel.getKapasitasantrian());
                                    for(int i=0;i<arrayDokter.length;i++){
                                            arrayDokter[i]=new ServerDokter(i+1,gen,this);
                                            arrayDokter[i].start();
                                    }
                                    for(int i=0;i<arrayPerawat.length;i++){
                                            arrayPerawat[i]=new ServerPerawat(i+1,gen,this,arrayDokter);
                                            arrayPerawat[i].start();
                                    }
                                    for(int i=0;i<arrayPetugas.length;i++){
                                            arrayPetugas[i]=new ServerPetugas(i+1,gen,this,arrayPerawat);
                                            arrayPetugas[i].start();
                                    }
                                    sim2=new StatisticsSimulationPoli(arrayPetugas,gen,this,this.excel);
                                    sim2.setServerperawat(arrayPerawat);
                                    sim2.setServerdokter(arrayDokter);
                                    sim2.start();
                                    for(int i=0;i<arrayServer.length;i++){
                                            arrayServer[i].setSim(sim2);
                                            arrayServer[i].start();
                                    }
                                    sim.setCekInput("file");
                                    sim.setPoli(sim2);
                                    sim.setServerpetugas(arrayPetugas);
                                    sim.setServerperawat(arrayPerawat);
                                    sim.setServerdokter(arrayDokter);
                                    sim.start();
                                    System.out.println("played");
                                }
                                else{
                                    showErrorReading();
                                }
                            }
                            else{
                                showErrorReading();
                            }
                    }
            }
            else{
                JOptionPane.showMessageDialog(this,"Anda harus memilih metode pemodelan terlebih dahulu","Alert",JOptionPane.ERROR_MESSAGE);

            }
        }
        else if (counter==1){
            for(int i=0;i<arrayDokter.length;i++){
                        arrayDokter[i].resume();
            }
            for(int i=0;i<arrayPerawat.length;i++){
                        arrayPerawat[i].resume();
            }
            for(int i=0;i<arrayPetugas.length;i++){
                        arrayPetugas[i].resume();
            }
            sim2.resume();
            for(int i=0;i<arrayServer.length;i++){
                        arrayServer[i].resume();
            }
            sim.resume();
            counter=0;
        }
        

    }//GEN-LAST:event_playActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        enableSave();
        this.setState(Frame.ICONIFIED);
        jFrame1.setState(Frame.ICONIFIED);
        frame_report3.setState(Frame.ICONIFIED);
        frame_report4.setState(Frame.ICONIFIED);
        String[] columnNames={"Nomor urut","Jenis Pasien","Arrival Time","Service time","Waktu mulai dilayani","Delay time","Departure time","Waiting time","Loket"};
        LinkedList<Customer> queuereport3=new LinkedList<Customer>();
        for(int i=0;i<arrayServer.length;i++){
            LinkedList<Customer> queuetempreport=arrayServer[i].getQueueReport2();
            System.out.println("Jumlah Server : "+arrayServer.length);
            System.out.println("Size  : "+queuetempreport.size());
            int k=0;
            while(k<queuetempreport.size()){
                Customer temp=queuetempreport.get(k);
                System.out.println("queue report : "+temp.getNumber()+" "+temp.getArrivaltime()+" "+temp.getServicetime());
                queuereport3.add(temp);
                k++;
            }
        }
       Customer[] arrayreport1=new Customer[queuereport3.size()];
       for(int i=0;i<arrayreport1.length;i++){
           arrayreport1[i]=queuereport3.get(i);
       }
       Arrays.sort(arrayreport1);
       panel_tabel.setVisible(true);
       panel_tabel.setLayout( new BorderLayout() );
       panel_tabel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Tabel Hasil Pemodelan dan Simulasi Pendaftaran Awal",TitledBorder.CENTER,TitledBorder.TOP));
       data=gen.generateStatisticsReportAwal4(arrayreport1);
       final JTable tabel_awal=new JTable(data,columnNames);
       tabel_awal.setSize(750,300);
       DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
       centerRenderer.setHorizontalAlignment( JLabel.CENTER );
       tabel_awal.setDefaultRenderer(String.class, centerRenderer);
       JTableHeader header = tabel_awal.getTableHeader();
       header.setDefaultRenderer(centerRenderer);
       for (int i = 0; i < 9; i++) {
            tabel_awal.getColumnModel().getColumn(i).setMaxWidth(120);
            tabel_awal.getColumnModel().getColumn(i).setWidth(120);
            tabel_awal.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
        }
       panel_tabel.removeAll();
       JScrollPane scrollPane = new JScrollPane(tabel_awal);
       scrollPane.setVisible(true);
       getContentPane().add(scrollPane);
       panel_tabel.add( scrollPane );
       int count1=0;
        int count2=0;
        for(int i=0;i<arrayServer.length;i++){
            count1+=arrayServer[i].getCounterPasienLama();
            count2+=arrayServer[i].getCounterPasienBaru();
        }
        pasien_lama.setText("Jumlah pasien BPJS Lama : "+(int)count1+"");
        pasien_baru.setText("Jumlah pasien BPJS Baru : "+(int)count2+"");
        pasien_emergency.setText("Jumlah pasien emergency : "+(int)0);
        total.setText("Jumlah total pasien : "+(count1+count2));
        frame_report.pack();
        frame_report.setSize(750,660);
        frame_report.setBounds(0,0,750,660);
        frame_report.setVisible(true);      
        
       
       String[] columnNames2={"Nomor urut","Jenis Pasien","Arrival Time","Service time","Waktu mulai dilayani","Delay time","Departure time","Waiting time"};
       LinkedList<Customer> queuereport4=new LinkedList<Customer>();
       for(int i=0;i<arrayDokter.length;i++){
           LinkedList<Customer> queuetempreport2=arrayDokter[i].getQueueReport2();
           System.out.println("Jumlah Server : "+arrayDokter.length);
           System.out.println("Size  : "+queuetempreport2.size());
           int k=0;
            while(k<queuetempreport2.size()){
                Customer temp=queuetempreport2.get(k);
                queuereport4.add(temp);
                k++;
            }
       }
       Customer[] arrayreport2=new Customer[queuereport4.size()];
       for(int i=0;i<arrayreport2.length;i++){
           arrayreport2[i]=queuereport4.get(i);
       }
       Arrays.sort(arrayreport2);
       panel_poli.setVisible(true);
       panel_poli.setLayout( new BorderLayout() );
       panel_poli.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Tabel Hasil Pemodelan dan Simulasi Poliklinik",TitledBorder.CENTER,TitledBorder.TOP));
       data2=gen.generateStatisticsReportPoli4(arrayreport2);
        String[] columnNames3={"Nomor urut pend.awal","Nomor urut","Jenis Pasien","Arrival Time","Service time","Waktu mulai dilayani","Delay time","Departure time","Waiting time","Petugas","Perawat","Dokter"};
       final JTable tabel_poli=new JTable(data2,columnNames3);
       tabel_poli.setSize(900,300);
       DefaultTableCellRenderer centerRenderer2 = new DefaultTableCellRenderer();
       centerRenderer.setHorizontalAlignment( JLabel.CENTER );
       tabel_poli.setDefaultRenderer(String.class, centerRenderer2);
       JTableHeader header2 = tabel_poli.getTableHeader();
       header2.setDefaultRenderer(centerRenderer);
       for (int i = 0; i < 11; i++) {
            tabel_poli.getColumnModel().getColumn(i).setMaxWidth(120);
            tabel_poli.getColumnModel().getColumn(i).setWidth(120);
            tabel_poli.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
        }
       panel_poli.removeAll();
       JScrollPane scrollPane2 = new JScrollPane(tabel_poli);
       scrollPane2.setVisible(true);
       getContentPane().add(scrollPane2);
       panel_poli.add( scrollPane2 );
       total_bpjsl_poli.setText("Jumlah pasien BPJS Lama : "+(int)sim2.getCounterpasien2());
       total_bpjsb_poli.setText("Jumlah pasien BPJS Baru : "+(int)sim2.getCounterpasien1());
       total_emergency_poli.setText("Jumlah pasien Emergency : "+(int)sim2.getCounterpasien3());
       int total=sim2.getCounterpasien1()+sim2.getCounterpasien2()+sim2.getCounterpasien3();
       total_poli.setText("Jumlah total pasien Poliklinik : "+total);
       frame_report2.pack();
       frame_report2.setSize(700,660);
       frame_report2.setBounds(500,0,700,660);
       frame_report2.setVisible(true);     
    }//GEN-LAST:event_jButton1ActionPerformed

    private void pauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pauseActionPerformed

        for(int i=0;i<arrayDokter.length;i++){
                        arrayDokter[i].suspend();
            }
            for(int i=0;i<arrayPerawat.length;i++){
                        arrayPerawat[i].suspend();
            }
            for(int i=0;i<arrayPetugas.length;i++){
                        arrayPetugas[i].suspend();
            }
            sim.suspend();
            for(int i=0;i<arrayServer.length;i++){
                        arrayServer[i].suspend();
            }
            sim.suspend();   
        play.setEnabled(true);
        pause.setEnabled(false);
        slider.setEnabled(false);
        stop.setEnabled(true);
        jButton1.setEnabled(false);
        report.setEnabled(false);
        back.setEnabled(false);
        disableParamGrafik();
        counter=1;

    }//GEN-LAST:event_pauseActionPerformed

    private void stopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopActionPerformed
        // TODO add your handling code here:
        
        for(int i=0;i<arrayDokter.length;i++){
                    arrayDokter[i].stop();
        }
        for(int i=0;i<arrayPerawat.length;i++){
                    arrayPerawat[i].stop();
        }
        for(int i=0;i<arrayPetugas.length;i++){
                    arrayPetugas[i].stop();
        }
        sim2.stop();
        for(int i=0;i<arrayServer.length;i++){
                    arrayServer[i].stop();
        }
        sim.stop();
        for(int i=0;i<20.000;i++){
            System.out.println("do loading");
            showLoading();
        }
        hideLoading();
        sim.displayChart1();
        for(int i=0;i<10.000;i++){
            System.out.println("do loading");
            showLoadingPoli();
        }
        hideLoadingPoli();
        sim.displayChart2();
        counter=0;
        this.excel.setQueuecustomer(new LinkedList<Customer>());
        this.excel.setTotalbaru(0);
        this.excel.setTotallama(0);
        this.excel.setEmergency(0);
        play.setEnabled(true);
        slider.setEnabled(false);
        stop.setEnabled(false);
        pause.setEnabled(false);
        jButton2.setEnabled(true);
        this.enableAllInput();
        back.setEnabled(true);
        enableParamGrafik();
        enableReportTable();
    }//GEN-LAST:event_stopActionPerformed

    private void reportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportActionPerformed
        // TODO add your handling code here:
        this.setState(Frame.ICONIFIED);
        jFrame1.setState(Frame.ICONIFIED);
        frame_report.setState(Frame.ICONIFIED);
        frame_report2.setState(Frame.ICONIFIED);
        //frame report pendaftaran awal
        String[] columnNames2=gen.generateColumnNamesServer5(arrayServer);
        data=gen.generateUtilityServer5(arrayServer);
        panel_tabel_utility.setVisible(true);
        panel_tabel_utility.setLayout( new BorderLayout() );
        panel_tabel_utility.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Tabel Utilitas Server Pendaftaran Awal",TitledBorder.CENTER,TitledBorder.TOP));
        final JTable tabel_utilitas=new JTable(data,columnNames2);
        tabel_utilitas.setSize(500,300);
        DefaultTableCellRenderer centerRenderer2 = new DefaultTableCellRenderer();
        centerRenderer2.setHorizontalAlignment( JLabel.CENTER );
        tabel_utilitas.setDefaultRenderer(String.class, centerRenderer2);
        JTableHeader header2 = tabel_utilitas.getTableHeader();
        header2.setDefaultRenderer(centerRenderer2);
        for (int i = 0; i < tabel_utilitas.getColumnCount(); i++) {
             tabel_utilitas.getColumnModel().getColumn(i).setMaxWidth(100);
             tabel_utilitas.getColumnModel().getColumn(i).setWidth(100);
             tabel_utilitas.getColumnModel().getColumn(i).setCellRenderer( centerRenderer2);
         }
        panel_tabel_utility.removeAll();
        JScrollPane scrollPane2 = new JScrollPane(tabel_utilitas);
        scrollPane2.setVisible(true);
        getContentPane().add(scrollPane2);
        panel_tabel_utility.add( scrollPane2 );
        
        
        panel_arrival_rate.setVisible(true);
        panel_arrival_rate.setLayout( new BorderLayout() );
        panel_arrival_rate.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Tabel Arrival Rate Pendaftaran Awal",TitledBorder.CENTER,TitledBorder.TOP));
        String[] columnNames3={"BPJS Lama","BPJS Baru","Summary"};
        LinkedList<Customer> queuereport3=new LinkedList<Customer>();
        for(int i=0;i<arrayServer.length;i++){
            LinkedList<Customer> queuetempreport=arrayServer[i].getQueueReport2();
            System.out.println("Jumlah Server : "+arrayServer.length);
            System.out.println("Size  : "+queuetempreport.size());
            int k=0;
            while(k<queuetempreport.size()){
                Customer temp=queuetempreport.get(k);
                System.out.println("queue report : "+temp.getNumber()+" "+temp.getArrivaltime()+" "+temp.getServicetime());
                queuereport3.add(temp);
                k++;
            }
        }
       Customer[] arrayreport1=new Customer[queuereport3.size()];
       for(int i=0;i<arrayreport1.length;i++){
           arrayreport1[i]=queuereport3.get(i);
       }
       Arrays.sort(arrayreport1);
       double summary=gen.generateSummaryArrivalTime(arrayreport1);
       double bpjslama=gen.generateArrivalTimeBPJSLama(arrayreport1);
       double bpjsbaru=gen.generateArrivalTimeBPJSBaru(arrayreport1);
       Object[][] data2=new Object[1][3];
       data2[0][0]=bpjslama +" pasien per 2 menit";
       data2[0][1]=bpjsbaru +" pasien per 2 menit";
       data2[0][2]=summary+" pasien per 2 menit";
        final JTable tabel_average=new JTable(data2,columnNames3);
        tabel_average.setSize(500,300);
        DefaultTableCellRenderer centerRenderer3 = new DefaultTableCellRenderer();
        centerRenderer2.setHorizontalAlignment( JLabel.CENTER );
        tabel_average.setDefaultRenderer(String.class, centerRenderer3);
        JTableHeader header3 = tabel_average.getTableHeader();
        header3.setDefaultRenderer(centerRenderer3);
        for (int i = 0; i < tabel_average.getColumnCount(); i++) {
             tabel_average.getColumnModel().getColumn(i).setMaxWidth(150);
             tabel_average.getColumnModel().getColumn(i).setWidth(150);
             tabel_average.getColumnModel().getColumn(i).setCellRenderer( centerRenderer3);
         }
        panel_arrival_rate.removeAll();
        JScrollPane scrollPane3 = new JScrollPane(tabel_average);
        scrollPane3.setVisible(true);
        getContentPane().add(scrollPane3);
        panel_arrival_rate.add( scrollPane3 );
        
        
        panel_interarrival.setVisible(true);
        panel_interarrival.setLayout( new BorderLayout() );
        panel_interarrival.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Tabel Average of Interarrival Time Pendaftaran Awal",TitledBorder.CENTER,TitledBorder.TOP));
        String[] columnNames4={"BPJS Lama","BPJS Baru","Summary"};
        LinkedList<Customer> queuereport4=new LinkedList<Customer>();
        for(int i=0;i<arrayServer.length;i++){
            LinkedList<Customer> queuetempreport=arrayServer[i].getQueueReport2();
            System.out.println("Jumlah Server : "+arrayServer.length);
            System.out.println("Size  : "+queuetempreport.size());
            int k=0;
            while(k<queuetempreport.size()){
                Customer temp=queuetempreport.get(k);
                System.out.println("queue report : "+temp.getNumber()+" "+temp.getArrivaltime()+" "+temp.getServicetime());
                queuereport4.add(temp);
                k++;
            }
        }
       Customer[] arrayreport2=new Customer[queuereport4.size()];
       for(int i=0;i<arrayreport2.length;i++){
           arrayreport2[i]=queuereport4.get(i);
       }
       Arrays.sort(arrayreport2);
       double summary2=gen.generateAverageInterArrivalTime(arrayreport2);
       double bpjslama2=gen.generateAverageInterArrivalTimeBPJSLama(arrayreport2);
       double bpjsbaru2=gen.generateAverageInterArrivalTimeBPJSBaru(arrayreport2);
       Object[][] data3=new Object[1][3];
       data3[0][0]=gen.convertSeconds(bpjslama2) +"";
       data3[0][1]=gen.convertSeconds(bpjsbaru2) +"";
       data3[0][2]=gen.convertSeconds(summary2) +"";
        final JTable tabel_inter=new JTable(data3,columnNames4);
        tabel_inter.setSize(500,300);
        DefaultTableCellRenderer centerRenderer4 = new DefaultTableCellRenderer();
        centerRenderer4.setHorizontalAlignment( JLabel.CENTER );
        tabel_inter.setDefaultRenderer(String.class, centerRenderer4);
        JTableHeader header4 = tabel_inter.getTableHeader();
        header3.setDefaultRenderer(centerRenderer4);
        for (int i = 0; i < tabel_inter.getColumnCount(); i++) {
             tabel_inter.getColumnModel().getColumn(i).setMaxWidth(100);
             tabel_inter.getColumnModel().getColumn(i).setWidth(100);
             tabel_inter.getColumnModel().getColumn(i).setCellRenderer( centerRenderer4);
         }
        panel_interarrival.removeAll();
        JScrollPane scrollPane4 = new JScrollPane(tabel_inter);
        scrollPane4.setVisible(true);
        getContentPane().add(scrollPane4);
        panel_interarrival.add( scrollPane4 );
        
       panel_service_rate.setVisible(true); 
       panel_service_rate.setLayout( new BorderLayout() );
       panel_service_rate.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Tabel Service Rate Pendaftaran Awal",TitledBorder.CENTER,TitledBorder.TOP));
       double summary22=gen.generateServiceRate5(arrayServer);
       double bpjslama22=gen.generateServiceRateBPJSLama5(arrayServer);
       double bpjsbaru22=gen.generateServiceRateBPJSBaru5(arrayServer);
       Object[][] data32=new Object[1][3];
       data32[0][0]=(bpjslama22) +" pasien per menit";
       data32[0][1]=(bpjsbaru22) +" pasien per menit";
       data32[0][2]=(summary22) +" pasien per menit";
        final JTable tabel_service_rate=new JTable(data32,columnNames4);
        tabel_service_rate.setSize(500,300);
        tabel_service_rate.setDefaultRenderer(String.class, centerRenderer4);
        JTableHeader header44 = tabel_service_rate.getTableHeader();
        header44.setDefaultRenderer(centerRenderer4);
        for (int i = 0; i < tabel_service_rate.getColumnCount(); i++) {
             tabel_service_rate.getColumnModel().getColumn(i).setMaxWidth(100);
             tabel_service_rate.getColumnModel().getColumn(i).setWidth(100);
             tabel_service_rate.getColumnModel().getColumn(i).setCellRenderer( centerRenderer4);
         }
         panel_service_rate.removeAll();
        JScrollPane scrollPane44 = new JScrollPane(tabel_service_rate);
        scrollPane44.setVisible(true);
        getContentPane().add(scrollPane44);
        panel_service_rate.add( scrollPane44 );
        
       
        panel_average.setVisible(true);
        panel_average.setLayout( new BorderLayout() );
        panel_average.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Tabel Average of Service Time Pendaftaran Awal",TitledBorder.CENTER,TitledBorder.TOP));
        String[] columnNames5=gen.generateColumnNamesServer5(arrayServer);
        Object[][] averageservicetime=gen.generateAverageServiceTime5(arrayServer);
        final JTable tabel_service=new JTable(averageservicetime,columnNames5);
        tabel_service.setSize(500,300);
        DefaultTableCellRenderer centerRenderer5 = new DefaultTableCellRenderer();
        centerRenderer5.setHorizontalAlignment( JLabel.CENTER );
        tabel_service.setDefaultRenderer(String.class, centerRenderer5);
        JTableHeader header5 = tabel_service.getTableHeader();
        header5.setDefaultRenderer(centerRenderer4);
        for (int i = 0; i < tabel_service.getColumnCount(); i++) {
             tabel_service.getColumnModel().getColumn(i).setMaxWidth(100);
             tabel_service.getColumnModel().getColumn(i).setWidth(100);
             tabel_service.getColumnModel().getColumn(i).setCellRenderer( centerRenderer5);
         }
        panel_average.removeAll();
        JScrollPane scrollPane5 = new JScrollPane(tabel_service);
        scrollPane5.setVisible(true);
        getContentPane().add(scrollPane5);
        panel_average.add( scrollPane5 );
        
        
        panel_waiting_time.setVisible(true);
        panel_waiting_time.setLayout( new BorderLayout() );
        panel_waiting_time.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Tabel Average of Waiting Time Pendaftaran Awal",TitledBorder.CENTER,TitledBorder.TOP));
        String[] columnNames6=gen.generateColumnNamesServer5(arrayServer);
        Object[][] averagewaitingtime=gen.generateAverageWaitingTime5(arrayServer);
        final JTable tabel_waiting=new JTable(averagewaitingtime,columnNames6);
        tabel_waiting.setSize(500,300);
        DefaultTableCellRenderer centerRenderer6 = new DefaultTableCellRenderer();
        centerRenderer6.setHorizontalAlignment( JLabel.CENTER );
        tabel_waiting.setDefaultRenderer(String.class, centerRenderer6);
        JTableHeader header6 = tabel_waiting.getTableHeader();
        header6.setDefaultRenderer(centerRenderer6);
        for (int i = 0; i < tabel_waiting.getColumnCount(); i++) {
             tabel_waiting.getColumnModel().getColumn(i).setMaxWidth(100);
             tabel_waiting.getColumnModel().getColumn(i).setWidth(100);
             tabel_waiting.getColumnModel().getColumn(i).setCellRenderer( centerRenderer6);
         }
        panel_waiting_time.removeAll();
        JScrollPane scrollPane6 = new JScrollPane(tabel_waiting);
        scrollPane6.setVisible(true);
        getContentPane().add(scrollPane6);
        panel_waiting_time.add( scrollPane6 );
        
        panel_delay_time.setVisible(true);
        panel_delay_time.setLayout( new BorderLayout() );
        panel_delay_time.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Tabel Average of Delay Time Pendaftaran Awal",TitledBorder.CENTER,TitledBorder.TOP));
        String[] columnNames7=gen.generateColumnNamesServer5(arrayServer);
        Object[][] averagedelaytime=gen.generateAverageDelayTime5(arrayServer);
        final JTable tabel_delay=new JTable(averagedelaytime,columnNames7);
        tabel_delay.setSize(500,300);
        DefaultTableCellRenderer centerRenderer7 = new DefaultTableCellRenderer();
        centerRenderer7.setHorizontalAlignment( JLabel.CENTER );
        tabel_delay.setDefaultRenderer(String.class, centerRenderer7);
        JTableHeader header7 = tabel_delay.getTableHeader();
        header7.setDefaultRenderer(centerRenderer7);
        for (int i = 0; i < tabel_delay.getColumnCount(); i++) {
             tabel_delay.getColumnModel().getColumn(i).setMaxWidth(100);
             tabel_delay.getColumnModel().getColumn(i).setWidth(100);
             tabel_delay.getColumnModel().getColumn(i).setCellRenderer( centerRenderer7);
         }
        panel_delay_time.removeAll();
        JScrollPane scrollPane7 = new JScrollPane(tabel_delay);
        scrollPane7.setVisible(true);
        getContentPane().add(scrollPane7);
        panel_delay_time.add( scrollPane7 );
        
        
         panel_pasien.setVisible(true);
        panel_pasien.setLayout( new BorderLayout() );
        panel_pasien.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Tabel Total Spent Time in Server",TitledBorder.CENTER,TitledBorder.TOP));
        String[] columnNames8=gen.generateColumnNamesServer5(arrayServer);
        Object[][] totalspenttime=gen.generateTotalSpentTimeServer5(arrayServer);
        final JTable tabel_spent=new JTable(totalspenttime,columnNames8);
        tabel_spent.setSize(500,300);
        DefaultTableCellRenderer centerRenderer8 = new DefaultTableCellRenderer();
        centerRenderer8.setHorizontalAlignment( JLabel.CENTER );
        tabel_spent.setDefaultRenderer(String.class, centerRenderer8);
        JTableHeader header8 = tabel_spent.getTableHeader();
        header8.setDefaultRenderer(centerRenderer8);
        for (int i = 0; i < tabel_spent.getColumnCount(); i++) {
             tabel_spent.getColumnModel().getColumn(i).setMaxWidth(100);
             tabel_spent.getColumnModel().getColumn(i).setWidth(100);
             tabel_spent.getColumnModel().getColumn(i).setCellRenderer( centerRenderer8);
         }
        panel_pasien.removeAll();
        JScrollPane scrollPane8 = new JScrollPane(tabel_spent);
        scrollPane8.setVisible(true);
        getContentPane().add(scrollPane8);
        panel_pasien.add( scrollPane8 );
        hasil.setBounds(550,0,300,300);
        hasil.setText(gen.generateSummaryOutput2(arrayServer));
        frame_report3.pack();
        frame_report3.setSize(987,523);
        frame_report3.setBounds(0,0,987,575);
        frame_report3.setVisible(true);
        
        
        //frame report petugas
        panel1.setVisible(true);
        panel1.setLayout( new BorderLayout() );
        panel1.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Tabel Total Spent Time Petugas",TitledBorder.CENTER,TitledBorder.TOP));
        String[] columnNames9=gen.generateColumnNamesServer6(arrayPetugas);
        Object[][] totalspenttime2=gen.generateTotalSpentTimeServer6(arrayPetugas);
        final JTable tabel_spent2=new JTable(totalspenttime2,columnNames9);
        tabel_spent2.setSize(500,300);
        DefaultTableCellRenderer centerRenderer9 = new DefaultTableCellRenderer();
        centerRenderer9.setHorizontalAlignment( JLabel.CENTER );
        tabel_spent2.setDefaultRenderer(String.class, centerRenderer9);
        JTableHeader header9 = tabel_spent2.getTableHeader();
        header9.setDefaultRenderer(centerRenderer9);
        for (int i = 0; i < tabel_spent2.getColumnCount(); i++) {
             tabel_spent2.getColumnModel().getColumn(i).setMaxWidth(100);
             tabel_spent2.getColumnModel().getColumn(i).setWidth(100);
             tabel_spent2.getColumnModel().getColumn(i).setCellRenderer( centerRenderer9);
         }
        panel1.removeAll();
        JScrollPane scrollPane9 = new JScrollPane(tabel_spent2);
        scrollPane9.setVisible(true);
        getContentPane().add(scrollPane9);
        panel1.add( scrollPane9 );
        panel_petugas.add(panel1);
        
        Object[][] data4=gen.generateUtilityServer6(arrayPetugas);
        panel2.setVisible(true);
        panel2.setLayout( new BorderLayout() );
        panel2.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Tabel Utilitas Server Petugas",TitledBorder.CENTER,TitledBorder.TOP));
        final JTable tabel_utilitas2=new JTable(data4,columnNames9);
        tabel_utilitas2.setSize(500,300);
        tabel_utilitas2.setDefaultRenderer(String.class, centerRenderer2);
        JTableHeader header10 = tabel_utilitas2.getTableHeader();
        header10.setDefaultRenderer(centerRenderer2);
        for (int i = 0; i < tabel_utilitas2.getColumnCount(); i++) {
             tabel_utilitas2.getColumnModel().getColumn(i).setMaxWidth(100);
             tabel_utilitas2.getColumnModel().getColumn(i).setWidth(100);
             tabel_utilitas2.getColumnModel().getColumn(i).setCellRenderer( centerRenderer2);
         }
        panel2.removeAll();
        JScrollPane scrollPane10 = new JScrollPane(tabel_utilitas2);
        scrollPane10.setVisible(true);
        getContentPane().add(scrollPane10);
        panel2.add( scrollPane10 );
        panel_petugas.add(panel2);
        
        panel3.setVisible(true);
        panel3.setLayout( new BorderLayout() );
        panel3.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Tabel Average of Service Time Petugas",TitledBorder.CENTER,TitledBorder.TOP));
        Object[][] averageservicetime2=gen.generateAverageServiceTime6(arrayPetugas);
        final JTable tabel_service2=new JTable(averageservicetime2,columnNames9);
        tabel_service2.setSize(500,300);
        tabel_service2.setDefaultRenderer(String.class, centerRenderer5);
        JTableHeader header11 = tabel_service2.getTableHeader();
        header11.setDefaultRenderer(centerRenderer4);
        for (int i = 0; i < tabel_service2.getColumnCount(); i++) {
             tabel_service2.getColumnModel().getColumn(i).setMaxWidth(100);
             tabel_service2.getColumnModel().getColumn(i).setWidth(100);
             tabel_service2.getColumnModel().getColumn(i).setCellRenderer( centerRenderer5);
         }
         panel3.removeAll();
        JScrollPane scrollPane11 = new JScrollPane(tabel_service2);
        scrollPane11.setVisible(true);
        getContentPane().add(scrollPane11);
        panel3.add( scrollPane11 );
        panel_petugas.add(panel3);
        
        panel4.setVisible(true);
        panel4.setLayout( new BorderLayout() );
        panel4.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Tabel Arrival Rate Petugas",TitledBorder.CENTER,TitledBorder.TOP));
        LinkedList<Customer> queuereport5=new LinkedList<Customer>();
        for(int i=0;i<arrayPetugas.length;i++){
            LinkedList<Customer> queuetempreport=arrayPetugas[i].getQueueReport2();
            System.out.println("Jumlah Server petugas : "+arrayPetugas.length);
            System.out.println("Size petugas  : "+queuetempreport.size());
            int k=0;
            while(k<queuetempreport.size()){
                Customer temp=queuetempreport.get(k);
                System.out.println("queue report : "+temp.getNumberinpoli()+" "+temp.getArrivaltimepoli()+" "+temp.getServicetimepoli());
                queuereport5.add(temp);
                k++;
            }
        }
       Customer[] arrayreport3=new Customer[queuereport5.size()];
       for(int i=0;i<arrayreport3.length;i++){
           arrayreport3[i]=queuereport5.get(i);
       }
       Arrays.sort(arrayreport3);
       double summary3=gen.generateSummaryArrivalTime2(arrayreport3);
       double bpjslama3=gen.generateArrivalTimeBPJSLama2(arrayreport3);
       double bpjsbaru3=gen.generateArrivalTimeBPJSBaru2(arrayreport3);
       Object[][] data5=new Object[1][3];
       data5[0][0]=bpjslama3 +" pasien per 2 menit";
       data5[0][1]=bpjsbaru3 +" pasien per 2 menit";
       data5[0][2]=summary3+" pasien per 2 menit";
       String[] columnNames10={"BPJS Lama","BPJS Baru","Summary"};
        final JTable tabel_average2=new JTable(data5,columnNames10);
        tabel_average2.setSize(500,300);
        tabel_average2.setDefaultRenderer(String.class, centerRenderer3);
        JTableHeader header12 = tabel_average2.getTableHeader();
        header12.setDefaultRenderer(centerRenderer3);
        for (int i = 0; i < tabel_average2.getColumnCount(); i++) {
             tabel_average2.getColumnModel().getColumn(i).setMaxWidth(150);
             tabel_average2.getColumnModel().getColumn(i).setWidth(150);
             tabel_average2.getColumnModel().getColumn(i).setCellRenderer( centerRenderer3);
         }
        panel4.removeAll();
        JScrollPane scrollPane12 = new JScrollPane(tabel_average2);
        scrollPane12.setVisible(true);
        getContentPane().add( scrollPane12);
        panel4.add( scrollPane12 );
        panel_petugas.add(panel4);
        
        panel5.setVisible(true);
        panel5.setLayout( new BorderLayout() );
        panel5.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Tabel Average of Interarrival Time Petugas",TitledBorder.CENTER,TitledBorder.TOP));
        LinkedList<Customer> queuereport6=new LinkedList<Customer>();
        for(int i=0;i<arrayPetugas.length;i++){
            LinkedList<Customer> queuetempreport=arrayPetugas[i].getQueueReport2();
            System.out.println("Jumlah Server : "+arrayPetugas.length);
            System.out.println("Size  : "+queuetempreport.size());
            int k=0;
            while(k<queuetempreport.size()){
                Customer temp=queuetempreport.get(k);
                System.out.println("queue report : "+temp.getNumberinpoli()+" "+temp.getArrivaltimepoli()+" "+temp.getServicetimepoli());
                queuereport6.add(temp);
                k++;
            }
        }
       Customer[] arrayreport4=new Customer[queuereport6.size()];
       for(int i=0;i<arrayreport4.length;i++){
           arrayreport4[i]=queuereport6.get(i);
       }
       Arrays.sort(arrayreport4);
       double summary4=gen.generateAverageInterArrivalTime2(arrayreport4);
       double bpjslama4=gen.generateAverageInterArrivalTimeBPJSLama2(arrayreport4);
       double bpjsbaru4=gen.generateAverageInterArrivalTimeBPJSBaru2(arrayreport4);
       Object[][] data6=new Object[1][3];
       data6[0][0]=gen.convertSeconds(bpjslama4) +"";
       data6[0][1]=gen.convertSeconds(bpjsbaru4) +"";
       data6[0][2]=gen.convertSeconds(summary4) +"";
        final JTable tabel_inter2=new JTable(data6,columnNames10);
        tabel_inter2.setSize(500,300);
        tabel_inter2.setDefaultRenderer(String.class, centerRenderer4);
        JTableHeader header13 = tabel_inter2.getTableHeader();
        header13.setDefaultRenderer(centerRenderer4);
        for (int i = 0; i < tabel_inter2.getColumnCount(); i++) {
             tabel_inter2.getColumnModel().getColumn(i).setMaxWidth(100);
             tabel_inter2.getColumnModel().getColumn(i).setWidth(100);
             tabel_inter2.getColumnModel().getColumn(i).setCellRenderer( centerRenderer4);
         }
        panel5.removeAll();
        JScrollPane scrollPane13 = new JScrollPane(tabel_inter2);
        scrollPane13.setVisible(true);
        getContentPane().add(scrollPane13);
        panel5.add( scrollPane13 );
        panel_petugas.add(panel5);
        
        panel6.setVisible(true);
        panel6.setLayout( new BorderLayout() );
        panel6.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Tabel Average of Waiting Time Petugas",TitledBorder.CENTER,TitledBorder.TOP));
        Object[][] averagewaitingtime2=gen.generateAverageWaitingTime6(arrayPetugas);
        final JTable tabel_waiting2=new JTable(averagewaitingtime2,columnNames9);
        tabel_waiting2.setSize(500,300);
        tabel_waiting2.setDefaultRenderer(String.class, centerRenderer6);
        JTableHeader header14 = tabel_waiting.getTableHeader();
        header14.setDefaultRenderer(centerRenderer6);
        for (int i = 0; i < tabel_waiting2.getColumnCount(); i++) {
             tabel_waiting2.getColumnModel().getColumn(i).setMaxWidth(100);
             tabel_waiting2.getColumnModel().getColumn(i).setWidth(100);
             tabel_waiting2.getColumnModel().getColumn(i).setCellRenderer( centerRenderer6);
         }
        panel6.removeAll();
        JScrollPane scrollPane14 = new JScrollPane(tabel_waiting2);
        scrollPane14.setVisible(true);
        getContentPane().add(scrollPane14);
        panel6.add( scrollPane14 );
        panel_petugas.add(panel6);
        
        
        panel7.setVisible(true);
        panel7.setLayout( new BorderLayout() );
        panel7.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Tabel Average of Delay Time Petugas",TitledBorder.CENTER,TitledBorder.TOP));
        Object[][] averagedelaytime2=gen.generateAverageDelayTime6(arrayPetugas);
        final JTable tabel_delay2=new JTable(averagedelaytime2,columnNames9);
        tabel_delay2.setSize(500,300);
        tabel_delay2.setDefaultRenderer(String.class, centerRenderer7);
        JTableHeader header15 = tabel_delay2.getTableHeader();
        header15.setDefaultRenderer(centerRenderer7);
        for (int i = 0; i < tabel_delay2.getColumnCount(); i++) {
             tabel_delay2.getColumnModel().getColumn(i).setMaxWidth(100);
             tabel_delay2.getColumnModel().getColumn(i).setWidth(100);
             tabel_delay2.getColumnModel().getColumn(i).setCellRenderer( centerRenderer7);
         }
        panel7.removeAll();
        JScrollPane scrollPane15 = new JScrollPane(tabel_delay2);
        scrollPane15.setVisible(true);
        getContentPane().add(scrollPane15);
        panel7.add( scrollPane15 );
        panel_petugas.add(panel7);
        
       panel8.setVisible(true);
       panel8.setLayout( new BorderLayout() );
       panel8.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Tabel Service Rate Petugas",TitledBorder.CENTER,TitledBorder.TOP));
       double summary5=gen.generateServiceRate6(arrayPetugas);
       double bpjslama5=gen.generateServiceRateBPJSLama6(arrayPetugas);
       double bpjsbaru5=gen.generateServiceRateBPJSBaru2(arrayPetugas);
       Object[][] service_rate2=new Object[1][3];
       service_rate2[0][0]=(bpjslama5) +" pasien per menit";
       service_rate2[0][1]=(bpjsbaru5) +" pasien per menit";
       service_rate2[0][2]=(summary5) +" pasien per menit";
        final JTable tabel_service_rate2=new JTable(service_rate2,columnNames4);
        tabel_service_rate2.setSize(500,300);
        tabel_service_rate2.setDefaultRenderer(String.class, centerRenderer4);
        JTableHeader header16 = tabel_service_rate2.getTableHeader();
        header16.setDefaultRenderer(centerRenderer4);
        for (int i = 0; i < tabel_service_rate2.getColumnCount(); i++) {
             tabel_service_rate2.getColumnModel().getColumn(i).setMaxWidth(100);
             tabel_service_rate2.getColumnModel().getColumn(i).setWidth(100);
             tabel_service_rate2.getColumnModel().getColumn(i).setCellRenderer( centerRenderer4);
         }
        panel8.removeAll();
        JScrollPane scrollPane16 = new JScrollPane(tabel_service_rate2);
        scrollPane16.setVisible(true);
        getContentPane().add(scrollPane16);
        panel8.add( scrollPane16 );
        panel_petugas.add(panel8);
        output_petugas.setText(gen.generateSummaryOutputPetugas(arrayPetugas));
        
        
        //frame report perawat
        panel9.setVisible(true);
        panel9.setLayout( new BorderLayout() );
        panel9.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Tabel Total Spent Time Perawat",TitledBorder.CENTER,TitledBorder.TOP));
        String[] columnNames11=gen.generateColumnNamesServer3(arrayPerawat);
        Object[][] totalspenttime3=gen.generateTotalSpentTimeServer3(arrayPerawat);
        final JTable tabel_spent3=new JTable(totalspenttime3,columnNames11);
        tabel_spent3.setSize(500,300);
        tabel_spent3.setDefaultRenderer(String.class, centerRenderer9);
        JTableHeader header17 = tabel_spent3.getTableHeader();
        header17.setDefaultRenderer(centerRenderer9);
        for (int i = 0; i < tabel_spent3.getColumnCount(); i++) {
             tabel_spent3.getColumnModel().getColumn(i).setMaxWidth(100);
             tabel_spent3.getColumnModel().getColumn(i).setWidth(100);
             tabel_spent3.getColumnModel().getColumn(i).setCellRenderer( centerRenderer9);
         }
        panel9.removeAll();
        JScrollPane scrollPane17 = new JScrollPane(tabel_spent3);
        scrollPane17.setVisible(true);
        getContentPane().add(scrollPane17);
        panel9.add( scrollPane17 );
        panel_perawat.add(panel9);
        

        Object[][] data7=gen.generateUtilityServer3(arrayPerawat);
        panel10.setVisible(true);
        panel10.setLayout( new BorderLayout() );
        panel10.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Tabel Utilitas Server Perawat",TitledBorder.CENTER,TitledBorder.TOP));
        getContentPane().add(  panel10 );
        final JTable tabel_utilitas3=new JTable(data7,columnNames11);
        tabel_utilitas3.setSize(500,300);
        tabel_utilitas3.setDefaultRenderer(String.class, centerRenderer2);
        JTableHeader header18 = tabel_utilitas3.getTableHeader();
        header18.setDefaultRenderer(centerRenderer2);
        for (int i = 0; i < tabel_utilitas3.getColumnCount(); i++) {
             tabel_utilitas3.getColumnModel().getColumn(i).setMaxWidth(100);
             tabel_utilitas3.getColumnModel().getColumn(i).setWidth(100);
             tabel_utilitas3.getColumnModel().getColumn(i).setCellRenderer( centerRenderer2);
         }
        panel10.removeAll();
        JScrollPane scrollPane18 = new JScrollPane(tabel_utilitas3);
        scrollPane18.setVisible(true);
        getContentPane().add(scrollPane18);
        panel10.add( scrollPane18 );
        panel_perawat.add(panel10);
        
        panel11.setVisible(true);
        panel11.setLayout( new BorderLayout() );
        panel11.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Tabel Average of Service Time Perawat",TitledBorder.CENTER,TitledBorder.TOP));
        Object[][] averageservicetime3=gen.generateAverageServiceTime3(arrayPerawat);
        final JTable tabel_service3=new JTable(averageservicetime3,columnNames11);
        tabel_service3.setSize(500,300);
        tabel_service3.setDefaultRenderer(String.class, centerRenderer5);
        JTableHeader header19 = tabel_service3.getTableHeader();
        header19.setDefaultRenderer(centerRenderer4);
        for (int i = 0; i < tabel_service3.getColumnCount(); i++) {
             tabel_service3.getColumnModel().getColumn(i).setMaxWidth(100);
             tabel_service3.getColumnModel().getColumn(i).setWidth(100);
             tabel_service3.getColumnModel().getColumn(i).setCellRenderer( centerRenderer5);
         }
        panel11.removeAll();
        JScrollPane scrollPane19 = new JScrollPane(tabel_service3);
        scrollPane19.setVisible(true);
        getContentPane().add(scrollPane19);
        panel11.add( scrollPane19 );
        panel_perawat.add(panel11);
        
        
        panel12.setVisible(true);
        panel12.setLayout( new BorderLayout() );
        panel12.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Tabel Arrival Rate Perawat",TitledBorder.CENTER,TitledBorder.TOP));
        LinkedList<Customer> queuereport7=new LinkedList<Customer>();
        for(int i=0;i<arrayPerawat.length;i++){
            LinkedList<Customer> queuetempreport=arrayPerawat[i].getQueueReport2();
            System.out.println("Jumlah Server perawat : "+arrayPerawat.length);
            System.out.println("Size perawat  : "+queuetempreport.size());
            int k=0;
            while(k<queuetempreport.size()){
                Customer temp=queuetempreport.get(k);
                System.out.println("queue report : "+temp.getNumberinpoli()+" "+temp.getArrivaltimepoli2()+" "+temp.getServicetimepoli2());
                queuereport7.add(temp);
                k++;
            }
        }
       Customer[] arrayreport5=new Customer[queuereport7.size()];
       for(int i=0;i<arrayreport5.length;i++){
           arrayreport5[i]=queuereport7.get(i);
       }
       Arrays.sort(arrayreport5);
       double summary6=gen.generateSummaryArrivalTime3(arrayreport5);
       double bpjslama6=gen.generateArrivalTimeBPJSLama3(arrayreport5);
       double bpjsbaru6=gen.generateArrivalTimeBPJSBaru3(arrayreport5);
       Object[][] data8=new Object[1][3];
       data8[0][0]=bpjslama6 +" pasien per 2 menit";
       data8[0][1]=bpjsbaru6 +" pasien per 2 menit";
       data8[0][2]=summary6+" pasien per 2 menit";
       String[] columnNames12={"BPJS Lama","BPJS Baru","Summary"};
        final JTable tabel_average3=new JTable(data8,columnNames12);
        tabel_average3.setSize(500,300);
        tabel_average3.setDefaultRenderer(String.class, centerRenderer3);
        JTableHeader header20 = tabel_average3.getTableHeader();
        header20.setDefaultRenderer(centerRenderer3);
        for (int i = 0; i < tabel_average3.getColumnCount(); i++) {
             tabel_average3.getColumnModel().getColumn(i).setMaxWidth(150);
             tabel_average3.getColumnModel().getColumn(i).setWidth(150);
             tabel_average3.getColumnModel().getColumn(i).setCellRenderer( centerRenderer3);
         }
        panel12.removeAll();
        JScrollPane scrollPane20 = new JScrollPane(tabel_average3);
        scrollPane20.setVisible(true);
        getContentPane().add(scrollPane20);
        panel12.add( scrollPane20 );
        panel_perawat.add(panel12);
        
        
        panel13.setVisible(true);
        panel13.setLayout( new BorderLayout() );
        panel13.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Tabel Average of Interarrival Time Perawat",TitledBorder.CENTER,TitledBorder.TOP));
        LinkedList<Customer> queuereport8=new LinkedList<Customer>();
        for(int i=0;i<arrayPerawat.length;i++){
            LinkedList<Customer> queuetempreport=arrayPerawat[i].getQueueReport2();
            System.out.println("Jumlah Server : "+arrayPerawat.length);
            System.out.println("Size  : "+queuetempreport.size());
            int k=0;
            while(k<queuetempreport.size()){
                Customer temp=queuetempreport.get(k);
                System.out.println("queue report : "+temp.getNumberinpoli()+" "+temp.getArrivaltimepoli()+" "+temp.getServicetimepoli());
                queuereport8.add(temp);
                k++;
            }
        }
       Customer[] arrayreport6=new Customer[queuereport8.size()];
       for(int i=0;i<arrayreport6.length;i++){
           arrayreport6[i]=queuereport8.get(i);
       }
       Arrays.sort(arrayreport6);
       double summary7=gen.generateAverageInterArrivalTime3(arrayreport6);
       double bpjslama7=gen.generateAverageInterArrivalTimeBPJSLama3(arrayreport6);
       double bpjsbaru7=gen.generateAverageInterArrivalTimeBPJSBaru3(arrayreport6);
       //double emergency2=gen.generateAverageInterArrivalTimeEmergency(arrayreport4);
       Object[][] data9=new Object[1][3];
       data9[0][0]=gen.convertSeconds(bpjslama7) +"";
       data9[0][1]=gen.convertSeconds(bpjsbaru7) +"";
       //data6[0][2]=gen.convertSeconds(emergency2) +"";
       data9[0][2]=gen.convertSeconds(summary7) +"";
        final JTable tabel_inter3=new JTable(data9,columnNames12);
        tabel_inter3.setSize(500,300);
        tabel_inter3.setDefaultRenderer(String.class, centerRenderer4);
        JTableHeader header21 = tabel_inter3.getTableHeader();
        header21.setDefaultRenderer(centerRenderer4);
        for (int i = 0; i < tabel_inter3.getColumnCount(); i++) {
             tabel_inter3.getColumnModel().getColumn(i).setMaxWidth(100);
             tabel_inter3.getColumnModel().getColumn(i).setWidth(100);
             tabel_inter3.getColumnModel().getColumn(i).setCellRenderer( centerRenderer4);
         }
        panel13.removeAll();
        JScrollPane scrollPane21 = new JScrollPane(tabel_inter3);
        scrollPane21.setVisible(true);
        getContentPane().add(scrollPane21);
        panel13.add( scrollPane21 );
        panel_perawat.add(panel13);
        
        panel14.setVisible(true);
        panel14.setLayout( new BorderLayout() );
        panel14.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Tabel Average of Waiting Time Perawat",TitledBorder.CENTER,TitledBorder.TOP));
        Object[][] averagewaitingtime3=gen.generateAverageWaitingTime3(arrayPerawat);
        final JTable tabel_waiting3=new JTable(averagewaitingtime3,columnNames11);
        tabel_waiting3.setSize(500,300);
        tabel_waiting3.setDefaultRenderer(String.class, centerRenderer6);
        JTableHeader header22 = tabel_waiting3.getTableHeader();
        header22.setDefaultRenderer(centerRenderer6);
        for (int i = 0; i < tabel_waiting3.getColumnCount(); i++) {
             tabel_waiting3.getColumnModel().getColumn(i).setMaxWidth(100);
             tabel_waiting3.getColumnModel().getColumn(i).setWidth(100);
             tabel_waiting3.getColumnModel().getColumn(i).setCellRenderer( centerRenderer6);
         }
        panel14.removeAll();
        JScrollPane scrollPane22 = new JScrollPane(tabel_waiting3);
        scrollPane22.setVisible(true);
        getContentPane().add(scrollPane22);
        panel14.add( scrollPane22 );
        panel_perawat.add(panel14);
        
        
        panel15.setVisible(true);
        panel15.setLayout( new BorderLayout() );
        panel15.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Tabel Average of Delay Time Perawat",TitledBorder.CENTER,TitledBorder.TOP));
        Object[][] averagedelaytime3=gen.generateAverageDelayTime3(arrayPerawat);
        final JTable tabel_delay3=new JTable(averagedelaytime3,columnNames11);
        tabel_delay3.setSize(500,300);
        tabel_delay3.setDefaultRenderer(String.class, centerRenderer7);
        JTableHeader header23 = tabel_delay3.getTableHeader();
        header23.setDefaultRenderer(centerRenderer7);
        for (int i = 0; i < tabel_delay3.getColumnCount(); i++) {
             tabel_delay3.getColumnModel().getColumn(i).setMaxWidth(100);
             tabel_delay3.getColumnModel().getColumn(i).setWidth(100);
             tabel_delay3.getColumnModel().getColumn(i).setCellRenderer( centerRenderer7);
         }
        panel15.removeAll();
        JScrollPane scrollPane23 = new JScrollPane(tabel_delay3);
        scrollPane23.setVisible(true);
        getContentPane().add(scrollPane23);
        panel15.add( scrollPane23 );
        panel_perawat.add(panel15);
        
       panel16.setVisible(true); 
       panel16.setLayout( new BorderLayout() );
       panel16.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Tabel Service Rate Perawat",TitledBorder.CENTER,TitledBorder.TOP));
       double summary8=gen.generateServiceRate3(arrayPerawat);
       double bpjslama8=gen.generateServiceRateBPJSLama3(arrayPerawat);
       double bpjsbaru8=gen.generateServiceRateBPJSBaru3(arrayPerawat);
       Object[][] service_rate3=new Object[1][3];
       service_rate3[0][0]=(bpjslama8) +" pasien per menit";
       service_rate3[0][1]=(bpjsbaru8) +" pasien per menit";
       service_rate3[0][2]=(summary8) +" pasien per menit";
        final JTable tabel_service_rate3=new JTable(service_rate3,columnNames12);
        tabel_service_rate3.setSize(500,300);
        tabel_service_rate3.setDefaultRenderer(String.class, centerRenderer4);
        JTableHeader header24 = tabel_service_rate3.getTableHeader();
        header24.setDefaultRenderer(centerRenderer4);
        for (int i = 0; i < tabel_service_rate3.getColumnCount(); i++) {
             tabel_service_rate3.getColumnModel().getColumn(i).setMaxWidth(100);
             tabel_service_rate3.getColumnModel().getColumn(i).setWidth(100);
             tabel_service_rate3.getColumnModel().getColumn(i).setCellRenderer( centerRenderer4);
         }
        panel16.removeAll();
        JScrollPane scrollPane24 = new JScrollPane(tabel_service_rate3);
        scrollPane24.setVisible(true);
        getContentPane().add(scrollPane24);
        panel16.add( scrollPane24 );
        panel_perawat.add(panel16);
        output_perawat.setText(gen.generateSummaryOutputPerawat(arrayPerawat));
        
        
        //frame report dokter
        panel17.setVisible(true);
        panel17.setLayout( new BorderLayout() );
        panel17.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Tabel Total Spent Time Dokter",TitledBorder.CENTER,TitledBorder.TOP));
        String[] columnNames13=gen.generateColumnNamesServer4(arrayDokter);
        Object[][] totalspenttime4=gen.generateTotalSpentTimeServer4(arrayDokter);
        final JTable tabel_spent4=new JTable(totalspenttime4,columnNames13);
        tabel_spent4.setSize(500,300);
        tabel_spent4.setDefaultRenderer(String.class, centerRenderer9);
        JTableHeader header25 = tabel_spent4.getTableHeader();
        header25.setDefaultRenderer(centerRenderer9);
        for (int i = 0; i < tabel_spent4.getColumnCount(); i++) {
             tabel_spent4.getColumnModel().getColumn(i).setMaxWidth(100);
             tabel_spent4.getColumnModel().getColumn(i).setWidth(100);
             tabel_spent4.getColumnModel().getColumn(i).setCellRenderer( centerRenderer9);
         }
        panel17.removeAll();
        JScrollPane scrollPane25 = new JScrollPane(tabel_spent4);
        scrollPane25.setVisible(true);
        getContentPane().add(scrollPane25);
        panel17.add( scrollPane25 );
        panel_dokter.add(panel17);
        

        Object[][] data10=gen.generateUtilityServer4(arrayDokter);
        panel18.setVisible(true);
        panel18.setLayout( new BorderLayout() );
        panel18.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Tabel Utilitas Server Dokter",TitledBorder.CENTER,TitledBorder.TOP));
        final JTable tabel_utilitas4=new JTable(data10,columnNames13);
        tabel_utilitas4.setSize(500,300);
        tabel_utilitas4.setDefaultRenderer(String.class, centerRenderer2);
        JTableHeader header26 = tabel_utilitas4.getTableHeader();
        header26.setDefaultRenderer(centerRenderer2);
        for (int i = 0; i < tabel_utilitas4.getColumnCount(); i++) {
             tabel_utilitas4.getColumnModel().getColumn(i).setMaxWidth(100);
             tabel_utilitas4.getColumnModel().getColumn(i).setWidth(100);
             tabel_utilitas4.getColumnModel().getColumn(i).setCellRenderer( centerRenderer2);
         }
        panel18.removeAll();
        JScrollPane scrollPane26 = new JScrollPane(tabel_utilitas4);
        scrollPane26.setVisible(true);
        getContentPane().add(scrollPane26);
        panel18.add( scrollPane26 );
        panel_dokter.add(panel18);
        
        panel19.setVisible(true);
        panel19.setLayout( new BorderLayout() );
        panel19.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Tabel Average of Service Time Dokter",TitledBorder.CENTER,TitledBorder.TOP));
        Object[][] averageservicetime4=gen.generateAverageServiceTime4(arrayDokter);
        final JTable tabel_service4=new JTable(averageservicetime4,columnNames13);
        tabel_service4.setSize(500,300);
        tabel_service4.setDefaultRenderer(String.class, centerRenderer5);
        JTableHeader header27 = tabel_service4.getTableHeader();
        header27.setDefaultRenderer(centerRenderer4);
        for (int i = 0; i < tabel_service4.getColumnCount(); i++) {
             tabel_service4.getColumnModel().getColumn(i).setMaxWidth(100);
             tabel_service4.getColumnModel().getColumn(i).setWidth(100);
             tabel_service4.getColumnModel().getColumn(i).setCellRenderer( centerRenderer5);
         }
        panel19.removeAll();
        JScrollPane scrollPane27 = new JScrollPane(tabel_service4);
        scrollPane27.setVisible(true);
        getContentPane().add(scrollPane27);
        panel19.add( scrollPane27 );
        panel_dokter.add(panel19);
        
        
        panel20.setVisible(true);
        panel20.setLayout( new BorderLayout() );
        panel20.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Tabel Arrival Rate Dokter",TitledBorder.CENTER,TitledBorder.TOP));
        LinkedList<Customer> queuereport9=new LinkedList<Customer>();
        for(int i=0;i<arrayDokter.length;i++){
            LinkedList<Customer> queuetempreport=arrayDokter[i].getQueueReport2();
            System.out.println("Jumlah Server perawat : "+arrayDokter.length);
            System.out.println("Size perawat  : "+queuetempreport.size());
            int k=0;
            while(k<queuetempreport.size()){
                Customer temp=queuetempreport.get(k);
                System.out.println("queue report : "+temp.getNumberinpoli()+" "+temp.getArrivaltimepoli2()+" "+temp.getServicetimepoli2());
                queuereport9.add(temp);
                k++;
            }
        }
       Customer[] arrayreport7=new Customer[queuereport9.size()];
       for(int i=0;i<arrayreport7.length;i++){
           arrayreport7[i]=queuereport9.get(i);
       }
       Arrays.sort(arrayreport7);
       double summary9=gen.generateSummaryArrivalTime4(arrayreport7);
       double bpjslama9=gen.generateArrivalTimeBPJSLama4(arrayreport7);
       double bpjsbaru9=gen.generateArrivalTimeBPJSBaru4(arrayreport7);
       double emergency=gen.generateArrivalTimeEmergency(arrayreport7);
       Object[][] data11=new Object[1][4];
       data11[0][0]=bpjslama9 +" pasien per 2 menit";
       data11[0][1]=bpjsbaru9 +" pasien per 2 menit";
       data11[0][2]=emergency +" pasien per 2 menit";
       data11[0][3]=summary9+" pasien per 2 menit";
       String[] columnNames14={"BPJS Lama","BPJS Baru","Emergency","Summary"};
       final JTable tabel_average4=new JTable(data11,columnNames14);
       tabel_average4.setSize(500,300);
       tabel_average4.setDefaultRenderer(String.class, centerRenderer3);
       JTableHeader header28 = tabel_average3.getTableHeader();
       header28.setDefaultRenderer(centerRenderer3);
       for (int i = 0; i < tabel_average4.getColumnCount(); i++) {
             tabel_average4.getColumnModel().getColumn(i).setMaxWidth(150);
             tabel_average4.getColumnModel().getColumn(i).setWidth(150);
             tabel_average4.getColumnModel().getColumn(i).setCellRenderer( centerRenderer3);
        }
       panel20.removeAll();
       JScrollPane scrollPane28 = new JScrollPane(tabel_average4);
       scrollPane28.setVisible(true);
       getContentPane().add(scrollPane28);
       panel20.add( scrollPane28 );
       panel_dokter.add(panel20);
        
        
       panel21.setVisible(true);
       panel21.setLayout( new BorderLayout() );
       panel21.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Tabel Average of Interarrival Time Dokter",TitledBorder.CENTER,TitledBorder.TOP));
        LinkedList<Customer> queuereport10=new LinkedList<Customer>();
        for(int i=0;i<arrayDokter.length;i++){
            LinkedList<Customer> queuetempreport=arrayDokter[i].getQueueReport2();
            System.out.println("Jumlah Server : "+arrayDokter.length);
            System.out.println("Size  : "+queuetempreport.size());
            int k=0;
            while(k<queuetempreport.size()){
                Customer temp=queuetempreport.get(k);
                System.out.println("queue report : "+temp.getNumberinpoli()+" "+temp.getArrivaltimepoli()+" "+temp.getServicetimepoli());
                queuereport10.add(temp);
                k++;
            }
        }
       Customer[] arrayreport8=new Customer[queuereport10.size()];
       for(int i=0;i<arrayreport8.length;i++){
           arrayreport8[i]=queuereport10.get(i);
       }
       Arrays.sort(arrayreport8);
       double summary10=gen.generateAverageInterArrivalTime4(arrayreport8);
       double bpjslama10=gen.generateAverageInterArrivalTimeBPJSLama4(arrayreport8);
       double bpjsbaru10=gen.generateAverageInterArrivalTimeBPJSBaru4(arrayreport8);
       double emergency2=gen.generateAverageInterArrivalTimeEmergency(arrayreport8);
       Object[][] data12=new Object[1][4];
       data12[0][0]=gen.convertSeconds(bpjslama10) +"";
       data12[0][1]=gen.convertSeconds(bpjsbaru10) +"";
       data12[0][2]=gen.convertSeconds(emergency2) +"";
       data12[0][3]=gen.convertSeconds(summary10) +"";
        final JTable tabel_inter4=new JTable(data12,columnNames14);
        tabel_inter4.setSize(500,300);
        tabel_inter4.setDefaultRenderer(String.class, centerRenderer4);
        JTableHeader header29 = tabel_inter4.getTableHeader();
        header29.setDefaultRenderer(centerRenderer4);
        for (int i = 0; i < tabel_inter4.getColumnCount(); i++) {
             tabel_inter4.getColumnModel().getColumn(i).setMaxWidth(100);
             tabel_inter4.getColumnModel().getColumn(i).setWidth(100);
             tabel_inter4.getColumnModel().getColumn(i).setCellRenderer( centerRenderer4);
         }
        panel21.removeAll();
        JScrollPane scrollPane29 = new JScrollPane(tabel_inter4);
        scrollPane29.setVisible(true);
        getContentPane().add(scrollPane29);
        panel21.add( scrollPane29 );
        panel_dokter.add(panel21);
        
        panel22.setVisible(true);
        panel22.setLayout( new BorderLayout() );
        panel22.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Tabel Average of Waiting Time Dokter",TitledBorder.CENTER,TitledBorder.TOP));
        Object[][] averagewaitingtime4=gen.generateAverageWaitingTime4(arrayDokter);
        final JTable tabel_waiting4=new JTable(averagewaitingtime4,columnNames13);
        tabel_waiting4.setSize(500,300);
        tabel_waiting4.setDefaultRenderer(String.class, centerRenderer6);
        JTableHeader header30 = tabel_waiting4.getTableHeader();
        header30.setDefaultRenderer(centerRenderer6);
        for (int i = 0; i < tabel_waiting4.getColumnCount(); i++) {
             tabel_waiting4.getColumnModel().getColumn(i).setMaxWidth(100);
             tabel_waiting4.getColumnModel().getColumn(i).setWidth(100);
             tabel_waiting4.getColumnModel().getColumn(i).setCellRenderer( centerRenderer6);
         }
        panel22.removeAll();
        JScrollPane scrollPane30 = new JScrollPane(tabel_waiting4);
        scrollPane30.setVisible(true);
        getContentPane().add(scrollPane30);
        panel22.add( scrollPane30 );
        panel_dokter.add(panel22);
        
        
        panel23.setVisible(true);
        panel23.setLayout( new BorderLayout() );
        panel23.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Tabel Average of Delay Time Dokter",TitledBorder.CENTER,TitledBorder.TOP));
        Object[][] averagedelaytime4=gen.generateAverageDelayTime4(arrayDokter);
        final JTable tabel_delay4=new JTable(averagedelaytime4,columnNames13);
        tabel_delay4.setSize(500,300);
        tabel_delay4.setDefaultRenderer(String.class, centerRenderer7);
        JTableHeader header31 = tabel_delay4.getTableHeader();
        header31.setDefaultRenderer(centerRenderer7);
        for (int i = 0; i < tabel_delay4.getColumnCount(); i++) {
             tabel_delay4.getColumnModel().getColumn(i).setMaxWidth(100);
             tabel_delay4.getColumnModel().getColumn(i).setWidth(100);
             tabel_delay4.getColumnModel().getColumn(i).setCellRenderer( centerRenderer7);
         }
        panel23.removeAll();
        JScrollPane scrollPane31 = new JScrollPane(tabel_delay4);
        scrollPane31.setVisible(true);
        getContentPane().add(scrollPane31);
        panel23.add( scrollPane31);
        panel_dokter.add(panel23);
        
       panel24.setVisible(true);
       panel24.setLayout( new BorderLayout() );
       panel24.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Tabel Service Rate Dokter",TitledBorder.CENTER,TitledBorder.TOP));
       double summary11=gen.generateServiceRate4(arrayDokter);
       double bpjslama11=gen.generateServiceRateBPJSLama4(arrayDokter);
       double bpjsbaru11=gen.generateServiceRateBPJSBaru4(arrayDokter);
       double emergency3=gen.generateServiceRateEmergency(arrayDokter);
       Object[][] service_rate4=new Object[1][4];
       service_rate4[0][0]=(bpjslama11) +" pasien per menit";
       service_rate4[0][1]=(bpjsbaru11) +" pasien per menit";
       service_rate4[0][2]=(emergency3) +" pasien per menit";
       service_rate4[0][3]=(summary11) +" pasien per menit";
        final JTable tabel_service_rate4=new JTable(service_rate4,columnNames14);
        tabel_service_rate4.setSize(500,300);
        tabel_service_rate4.setDefaultRenderer(String.class, centerRenderer4);
        JTableHeader header32 = tabel_service_rate4.getTableHeader();
        header24.setDefaultRenderer(centerRenderer4);
        for (int i = 0; i < tabel_service_rate4.getColumnCount(); i++) {
             tabel_service_rate4.getColumnModel().getColumn(i).setMaxWidth(100);
             tabel_service_rate4.getColumnModel().getColumn(i).setWidth(100);
             tabel_service_rate4.getColumnModel().getColumn(i).setCellRenderer( centerRenderer4);
         }
        panel24.removeAll();
        JScrollPane scrollPane32 = new JScrollPane(tabel_service_rate4);
        scrollPane32.setVisible(true);
        getContentPane().add(scrollPane32);
        panel24.add( scrollPane32 );
        panel_dokter.add(panel24);
        output_dokter.setText(gen.generateSummaryOutputDokter(arrayDokter));
        
        
        frame_report4.pack();
        frame_report4.setSize(950,487);
        frame_report4.setBounds(500,500,950,487);
        frame_report4.setVisible(true);
      
    }//GEN-LAST:event_reportActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // TODO add your handling code here:
        InterfaceAwal home=new InterfaceAwal();
        home.pack();
        home.setVisible(true);
        jFrame1.setVisible(false);
        frame_report.setVisible(false);
        frame_report2.setVisible(false);
        frame_report3.setVisible(false);
        frame_report4.setVisible(false);
        this.setVisible(false);
        
    }//GEN-LAST:event_backActionPerformed

     public void setOutputValue(String customer){
        String temp="Output Kedatangan : \n";
        output2.setText(temp+"\n"+customer+"");
    }
    
    public void setOutputValue2(String customer){
        String temp="Output Kedatangan : \n";
        output_poli.setText(temp+"\n"+customer+"");
    }
    
    public void setOutputValue3(String server,int number){
        jlabel[number-1].setVisible(true);
        jlabel[number-1].setText("Server ke "+(number)+"-"+server);
    }
    
    public void setOutputValue4(String server,int number){
        jlabel2[number-1].setVisible(true);
        jlabel2[number-1].setText("Petugas ke "+number+"-"+server);
    }
    
    public void setOutputValue5(String server,int number){
        jlabel3[number-1].setVisible(true);
        jlabel3[number-1].setText("Perawat ke "+number+"-"+server);
    }
    
    public void setOutputValue6(String server,int number){
        jlabel4[number-1].setVisible(true);
        jlabel4[number-1].setText("Dokter ke "+number+"-"+server);
    }
    
     public void setOutputValue7(String content,int number){
        tabel1.setValueAt(content, number, 0);
    }
    
    public void setOutputValue8(String content,int number){
        tabel1.setValueAt(content, number, 1);
    }
    
    public void setOutputValue9(String content,int number){
        tabel1.setValueAt(content, number, 2);
    }
    
    public void setOutputValue10(String content,int number){
        tabel1.setValueAt(content, number, 3);
    }
    
     public void setOutputValue102(String content,int number){
        tabel1.setValueAt(content, number, 4);
    }
     
    public void setOutputValue11(String content,int number){
        tabel2.setValueAt(content, number, 0);
    }
    
    public void setOutputValue12(String content,int number){
        tabel2.setValueAt(content, number, 1);
    }
    
    public void setOutputValue13(String content,int number){
        tabel2.setValueAt(content, number, 2);
    }
    
    public void setOutputValue14(String content,int number){
        tabel2.setValueAt(content, number, 3);
    }
    
    public void setOutputValue142(String content,int number){
        tabel2.setValueAt(content, number, 4);
    }
    
    public void setOutputCounter(int bpjsb,int bpjsl,int emergency){

        jLabel30.setText("BPJS Baru : "+bpjsb);
        jLabel31.setText("BPJS Lama : "+bpjsl);
        jLabel32.setText("Emergency : "+emergency);
    }
    
    public void setOutputCounter2(int bpjsb,int bpjsl,int emergency){
        jLabel50.setVisible(true);
        jLabel51.setVisible(true);
        jLabel52.setVisible(true);
        jLabel50.setText("BPJS Baru : "+bpjsb);
        jLabel51.setText("BPJS Lama : "+bpjsl);
        jLabel52.setText("Emergency : "+emergency);
    }
    
    public void enableResetButton(){
        jButton2.setEnabled(true);
        slider.setValue(500);
        slider.setEnabled(false);
        play.setEnabled(true);
        this.enableAllInput();
        back.setEnabled(true);
        pause.setEnabled(false);
        stop.setEnabled(false);
        this.excel.setQueuecustomer(new LinkedList<Customer>());
        this.excel.setTotalbaru(0);
        this.excel.setTotallama(0);
        this.excel.setEmergency(0);
        this.counter=0;
        this.disableServer();
        enableSave();
        enableParamGrafik();
        enableReportTable();
    }
    
    public void enableResetButton2(){
        jButton2.setEnabled(true);
        slider.setEnabled(false);
        play.setEnabled(true);
        slider.setValue(500);
        back.setEnabled(true);
        pause.setEnabled(false);
        stop.setEnabled(false);
        this.excel.setQueuecustomer(new LinkedList<Customer>());
        this.excel.setTotalbaru(0);
        this.excel.setTotallama(0);
        this.excel.setEmergency(0);
        this.counter=0;
        enableSave();
        enableParamGrafik();
        enableReportTable();
    }
    
    
    
    public void enableReportTable(){
        jButton1.setEnabled(true);
        report.setEnabled(true);
    }
    
    public void disableAllInput(){
        server_awal.setEnabled(false);
        server_poli2.setEnabled(false);
        server_poli.setEnabled(false);
        server_poli3.setEnabled(false);
        queue_capacity.setEnabled(false);
    }
    
     public void enableAllInput(){
        server_awal.setEnabled(true);
        server_poli2.setEnabled(true);
        server_poli.setEnabled(true);
        server_poli3.setEnabled(true);
        queue_capacity.setEnabled(true);
    }
     
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        disableParamGrafik();
        this.server_awal.setValue(1);
        this.server_poli.setValue(1);
        this.server_poli2.setValue(1);
        this.server_poli3.setValue(1);
        this.queue_capacity.setValue(1);
        jLabel20.setVisible(false);
        jLabel21.setVisible(false);
        jLabel22.setVisible(false);
        jLabel23.setVisible(false);
        jLabel24.setVisible(false);
        jLabel25.setVisible(false);
        jLabel26.setVisible(false);
        jLabel27.setVisible(false);
        jLabel28.setVisible(false);
        jLabel29.setVisible(false);
        jLabel30.setVisible(false);
        jLabel31.setVisible(false);
        jLabel32.setVisible(false);
        jLabel39.setVisible(false);
        jLabel40.setVisible(false);
        jLabel41.setVisible(false);
        jLabel42.setVisible(false);
        jLabel43.setVisible(false);
        jLabel44.setVisible(false);
        jLabel45.setVisible(false);
        jLabel46.setVisible(false);
        jLabel47.setVisible(false);
        jLabel50.setVisible(false);
        jLabel51.setVisible(false);
        jLabel52.setVisible(false);
        slider.setValue(500);
        output2.setText("Output Statistik Kedatangan:");
        output_poli.setText("");
        this.play.setEnabled(true);
        file_name.setText("");
        disableTable();
        disablePanel();
        disableServer();
        loadAwal();
        disableSave();
        buttonGroup1.clearSelection();
        
    }//GEN-LAST:event_jButton2ActionPerformed

    public void disableTable(){
            int row=tabel1.getRowCount();
            int col=tabel1.getColumnCount(); 
            for(int i=0;i<row;i++){
                for(int j=0;j<col;j++){
                    tabel1.setValueAt("", i, j);
                }
            }
            int row2=tabel2.getRowCount();
            int col2=tabel2.getColumnCount(); 
            for(int i=0;i<row2;i++){
                for(int j=0;j<col2;j++){
                    tabel2.setValueAt("", i, j);
                }
            }
     }
     
     public void disablePanel(){
           panelanimasi.setVisible(false);
            panelanimasi2.setVisible(false);
            panelanimasi3.setVisible(false);
            panel_tabel.setVisible(false);
            panel_poli.setVisible(false);
            panel_petugas.setVisible(false);
            fxPanel.setVisible(false);
            fxPanel2.setVisible(false);
            fxPanel3.setVisible(false);
            fxPanel4.setVisible(false);
            fxPanel5.setVisible(false);
            fxPanel6.setVisible(false);
            fxPanel7.setVisible(false);
            fxPanel8.setVisible(false);
            fxPanel9.setVisible(false);
            fxPanel10.setVisible(false);
            panel_grafik_poli.setVisible(false);
            panel_grafik_poli2.setVisible(false);
            panel_grafik_poli3.setVisible(false);
            panel_grafik_poli4.setVisible(false);
            panel_grafik_poli5.setVisible(false);
            panel_grafik_poli6.setVisible(false);
            panel1.setVisible(false);
            panel10.setVisible(false);
            panel11.setVisible(false);
            panel12.setVisible(false);
            panel13.setVisible(false);
            panel14.setVisible(false);
            panel15.setVisible(false);
            panel16.setVisible(false);
            panel17.setVisible(false);
            panel18.setVisible(false);
            panel19.setVisible(false);
            panel20.setVisible(false);
            panel21.setVisible(false);
            panel22.setVisible(false);
            panel23.setVisible(false);
            panel24.setVisible(false);
            panel2.setVisible(false);
            panel3.setVisible(false);
            panel4.setVisible(false);
            panel5.setVisible(false);
            panel6.setVisible(false);
            panel7.setVisible(false);
            panel8.setVisible(false);
            panel9.setVisible(false);
            panel_arrival_rate.setVisible(false);
            panel_average.setVisible(false);
            panel_delay_time.setVisible(false);
            panel_interarrival.setVisible(false);
            panel_service_rate.setVisible(false);
            panel_tabel_utility.setVisible(false);
            panel_waiting_time.setVisible(false);
            panel_pasien.setVisible(false);
     }
     
     public void initFxComponents(final int bpjsb,final int bpjsl,final int emr,final LinkedList<int[]> listcounterpasien ){

        Platform.runLater(new Runnable() {
          @Override
          public void run() {
              GridPane grid = new GridPane();
              Scene scene = new Scene(grid,500, 300);
                ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("Pasien BPJS Lama ("+bpjsl+")", bpjsl),
                new PieChart.Data("Pasien BPJS Baru ("+bpjsb+")",bpjsb));
                final PieChart chart = new PieChart(pieChartData);
                chart.setTitle("Proporsi Jumlah Pasien");
                chart.setPrefWidth(240);
                grid.add(chart,0,0);

                final CategoryAxis xAxis = new CategoryAxis();
                final NumberAxis yAxis = new NumberAxis();
                final BarChart<String,Number> bc =
                new BarChart<String,Number>(xAxis,yAxis);
                bc.setTitle("Grafik Jumlah Pasien per Loket");
                xAxis.setLabel("Loket");
                yAxis.setLabel("Jumlah Pasien");
                XYChart.Series series1 = new XYChart.Series();
                series1.setName("BPJS Lama");
                int size=listcounterpasien.size();
                for(int i=0;i<listcounterpasien.size();i++){
                    int[] temp=listcounterpasien.get(i);
                    series1.getData().add(new XYChart.Data("Loket-"+(i+1), temp[0]));
                    System.out.println(temp[0]+"lama");
                }
                XYChart.Series series2 = new XYChart.Series();
                series2.setName("BPJS Baru");
                for(int i=0;i<listcounterpasien.size();i++){
                    int[] temp=listcounterpasien.get(i);
                    series2.getData().add(new XYChart.Data("Loket-"+(i+1), temp[1]));
                    System.out.println(temp[1]+"baru");
                }
                bc.getData().addAll(series1, series2);
                grid.setVgap(20);
                grid.setHgap(20);
                grid.add(bc, 2, 0);
                fxPanel.setScene(scene);
            }
          });

  }
     
     
    public void initFxComponents2(final int bpjsb,final int bpjsl,final int emr,final LinkedList<int[]> counterpetugas,final LinkedList<int[]> counterperawat,final LinkedList<int[]> counterdokter){

        Platform.runLater(new Runnable() {
          @Override
          public void run() {
              GridPane grid = new GridPane();
              Scene scene = new Scene(grid,650, 400);
                ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("Pasien BPJS Lama ("+bpjsl+")", bpjsl),
                new PieChart.Data("Pasien BPJS Baru ("+bpjsb+")",bpjsb),
                new PieChart.Data("Pasien Emergency ("+emr+")", emr));
                final PieChart chart = new PieChart(pieChartData);
                chart.setTitle("Proporsi Jumlah Pasien");
                chart.setPrefWidth(250);
                grid.add(chart,0,0);
                
                final CategoryAxis xAxis = new CategoryAxis();
                final NumberAxis yAxis = new NumberAxis();
                final BarChart<String,Number> bc =
                new BarChart<String,Number>(xAxis,yAxis);
                bc.setTitle("Grafik Jumlah Pasien per Server");
                xAxis.setLabel("Server");
                yAxis.setLabel("Jumlah Pasien");
                XYChart.Series series1 = new XYChart.Series();
                series1.setName("BPJS Lama");
                int size=counterpetugas.size();
                for(int i=0;i<size;i++){
                    int[] temp=counterpetugas.get(i);
                    series1.getData().add(new XYChart.Data("Petugas - "+(i+1), temp[0]));
                    System.out.println(temp[0]+"lama poli 1");
                }
                for(int i=0;i<counterperawat.size();i++){
                    int[] temp=counterperawat.get(i);
                    series1.getData().add(new XYChart.Data("Perawat - "+(i+1), temp[0]));
                    System.out.println(temp[0]+"lama poli 2");
                }
                for(int i=0;i<counterdokter.size();i++){
                    int[] temp=counterdokter.get(i);
                    series1.getData().add(new XYChart.Data("Dokter - "+(i+1), temp[0]));
                    System.out.println(temp[0]+"lama poli 3");
                }
                XYChart.Series series2 = new XYChart.Series();
                series2.setName("BPJS Baru");
                for(int i=0;i<counterpetugas.size();i++){
                    int[] temp=counterpetugas.get(i);
                    series2.getData().add(new XYChart.Data("Petugas - "+(i+1), temp[1]));
                    System.out.println(temp[1]+"baru poli 1");
                }
                for(int i=0;i<counterperawat.size();i++){
                    int[] temp=counterperawat.get(i);
                    series2.getData().add(new XYChart.Data("Perawat - "+(i+1), temp[1]));
                    System.out.println(temp[1]+"baru poli 2");
                }
                for(int i=0;i<counterdokter.size();i++){
                    int[] temp=counterdokter.get(i);
                    series2.getData().add(new XYChart.Data("Dokter - "+(i+1), temp[1]));
                    System.out.println(temp[1]+"baru poli 3");
                }
                XYChart.Series series3 = new XYChart.Series();
                series3.setName("Emergency");
                for(int i=0;i<counterpetugas.size();i++){
                    series3.getData().add(new XYChart.Data("Petugas - "+(i+1),0)); 
                }
                for(int i=0;i<counterperawat.size();i++){
                    series3.getData().add(new XYChart.Data("Perawat - "+(i+1),0));
                }
                for(int i=0;i<counterdokter.size();i++){
                    int[] temp=counterdokter.get(i);
                    series3.getData().add(new XYChart.Data("Dokter - "+(i+1),temp[2]));
                     System.out.println(temp[1]+"emergency poli 3");
                }
                bc.getData().addAll(series1, series2,series3);
                grid.add(bc,2,0);
                fxPanel2.setScene(scene);
            }
          });

  }
    
     public void initFxComponents3(final double[][] utility,final int length,final LinkedList<int[]> delaytime){

      Platform.runLater(new Runnable() {
          @Override
         public void run() {
            GridPane grid = new GridPane();
            Scene scene = new Scene(grid,600, 400);
            final CategoryAxis xAxis = new CategoryAxis();
                final NumberAxis yAxis = new NumberAxis();
                final BarChart<String,Number> bc =
                new BarChart<String,Number>(xAxis,yAxis);
                bc.setTitle("Grafik Utilitas Server (Loket)");
                xAxis.setLabel("Loket");
                yAxis.setLabel("Utility");
                XYChart.Series series1 = new XYChart.Series();
                series1.setName("Utilitas Loket (%)");
                int size=length;
                for(int i=0;i<size;i++){
                   double temp=(double)utility[0][i]; 
                   series1.getData().add(new XYChart.Data("Loket-"+(i+1), temp));
                   System.out.println(temp+"utility");  
                }
                bc.getData().addAll(series1);
                grid.add(bc, 0, 0);
                
                final CategoryAxis xAxis2 = new CategoryAxis();
                final NumberAxis yAxis2 = new NumberAxis();
                final BarChart<String,Number> bc2 =
                new BarChart<String,Number>(xAxis2,yAxis2);
                bc2.setTitle("Grafik Total Delay Time per Loket");
                xAxis2.setLabel("Loket");
                yAxis2.setLabel("DelayTime (menit)");
                XYChart.Series series2 = new XYChart.Series();
                series2.setName("BPJS Lama");
                int size2=delaytime.size();
                for(int i=0;i<size2;i++){
                    int[] temp=delaytime.get(i);
                    series2.getData().add(new XYChart.Data("Loket-"+(i+1),temp[0]));
                    System.out.println(temp[0]+"lama delay");
                }
                XYChart.Series series3 = new XYChart.Series();
                series3.setName("BPJS Baru");
                for(int i=0;i<size2;i++){
                    int[] temp=delaytime.get(i);
                    series3.getData().add(new XYChart.Data("Loket-"+(i+1), temp[1]));
                    System.out.println(temp[1]+"baru delay");
                }
                bc2.getData().addAll(series2, series3);
                grid.add(bc2, 2, 0);
                fxPanel3.setScene(scene);
                
            }
          });

  }
     
     public void initFxComponents6(final double[][] utility1,final double[][] utility2,final double[][] utility3,final int length1,final int length2,final int length3,final LinkedList<int[]> delaytime1,final LinkedList<int[]> delaytime2,final LinkedList<int[]> delaytime3){

      Platform.runLater(new Runnable() {
          @Override
         public void run() {
            GridPane grid = new GridPane();
            Scene scene = new Scene(grid,600, 400);
            final CategoryAxis xAxis = new CategoryAxis();
                final NumberAxis yAxis = new NumberAxis();
                final BarChart<String,Number> bc =
                new BarChart<String,Number>(xAxis,yAxis);
                bc.setTitle("Grafik Utilitas Server Poliklinik");
                xAxis.setLabel("Loket");
                yAxis.setLabel("Utilitas");
                XYChart.Series series1 = new XYChart.Series();
                series1.setName("Utilitas Petugas(%)");
                for(int i=0;i<length1;i++){
                    double temp=(double)utility1[0][i];
                    series1.getData().add(new XYChart.Data("Petugas-"+(i+1), temp));
                    System.out.println(temp+"utility");
                }
                XYChart.Series series2 = new XYChart.Series();
                series2.setName("Utilitas Perawat (%)");
                for(int i=0;i<length2;i++){
                    double temp=(double)utility2[0][i];
                    series2.getData().add(new XYChart.Data("Perawat-"+(i+1), temp));
                    System.out.println(temp+"utility");
                }
                XYChart.Series series3 = new XYChart.Series();
                series3.setName("Utilitas Dokter (%)");
                for(int i=0;i<length3;i++){
                    double temp=(double)utility3[0][i];
                    series3.getData().add(new XYChart.Data("Dokter-"+(i+1), temp));
                    System.out.println(temp+"utility");
                }
                bc.getData().addAll(series1,series2,series3);
                grid.add(bc, 0, 0);
                
                final CategoryAxis xAxis2 = new CategoryAxis();
                final NumberAxis yAxis2 = new NumberAxis();
                final BarChart<String,Number> bc2 =
                new BarChart<String,Number>(xAxis2,yAxis2);
                bc2.setTitle("Grafik Delay Time pada Server Poliklinik");
                xAxis2.setLabel("Server");
                yAxis2.setLabel("Delay Time");
                XYChart.Series series4 = new XYChart.Series();
                series4.setName("BPJS Lama");
                for(int i=0;i<delaytime1.size();i++){
                    int[] temp=delaytime1.get(i);
                    series4.getData().add(new XYChart.Data("Petugas-"+(i+1), temp[0]));
                    System.out.println(temp[0]+"delay lama");
                }
                XYChart.Series series5 = new XYChart.Series();
                series5.setName("BPJS Baru");
                for(int i=0;i<delaytime2.size();i++){
                    int[]  temp=delaytime2.get(i);
                    series5.getData().add(new XYChart.Data("Perawat"+(i+1), temp[1]));
                    System.out.println(temp[1]+"Delay Baru");
                }
                XYChart.Series series6 = new XYChart.Series();
                series6.setName("Emergency");
                for(int i=0;i<delaytime3.size();i++){
                    int[]  temp=delaytime3.get(i);
                    series6.getData().add(new XYChart.Data("Dokter-"+(i+1), temp[2]));
                    System.out.println(temp[2]+"Delay Emergency");
                }
                bc2.getData().addAll(series4,series5,series6);
                grid.add(bc2, 2, 0);
               
                fxPanel6.setScene(scene);
                
            }
          });

  }
     
     public void initFxComponents7(final LinkedList<int[]> waiting1,final LinkedList<int[]> waiting2,final LinkedList<int[]> waiting3,final LinkedList<int[]> service1,final LinkedList<int[]> service2,final LinkedList<int[]> service3){

      Platform.runLater(new Runnable() {
          @Override
         public void run() {
            GridPane grid = new GridPane();
            Scene scene = new Scene(grid,600, 400);
                final CategoryAxis xAxis2 = new CategoryAxis();
                final NumberAxis yAxis2 = new NumberAxis();
                final BarChart<String,Number> bc2 =
                new BarChart<String,Number>(xAxis2,yAxis2);
                bc2.setTitle("Grafik Waiting Time pada Server Poliklinik");
                xAxis2.setLabel("Server");
                yAxis2.setLabel("Waiting Time");
                XYChart.Series series4 = new XYChart.Series();
                series4.setName("BPJS Lama");
                for(int i=0;i<waiting1.size();i++){
                    int[] temp=waiting1.get(i);
                    series4.getData().add(new XYChart.Data("Petugas-"+(i+1), temp[0]));
                    System.out.println(temp[0]+"waiting lama");
                }
                XYChart.Series series5 = new XYChart.Series();
                series5.setName("BPJS Baru");
                for(int i=0;i<waiting2.size();i++){
                    int[]  temp=waiting2.get(i);
                    series5.getData().add(new XYChart.Data("Perawat"+(i+1), temp[1]));
                    System.out.println(temp[1]+"waiting Baru");
                }
                XYChart.Series series6 = new XYChart.Series();
                series6.setName("Emergency");
                for(int i=0;i<waiting3.size();i++){
                    int[]  temp=waiting3.get(i);
                    series6.getData().add(new XYChart.Data("Dokter-"+(i+1), temp[2]));
                    System.out.println(temp[2]+"Waiting Emergency");
                }
                bc2.getData().addAll(series4,series5,series6);
                grid.add(bc2, 0, 0);
               
                final CategoryAxis xAxis3 = new CategoryAxis();
                final NumberAxis yAxis3 = new NumberAxis();
                final BarChart<String,Number> bc3 =
                new BarChart<String,Number>(xAxis3,yAxis3);
                bc3.setTitle("Grafik Service Time pada Server Poliklinik");
                xAxis3.setLabel("Server");
                yAxis3.setLabel("Service Time");
                XYChart.Series series7 = new XYChart.Series();
                series7.setName("BPJS Lama");
                for(int i=0;i<service1.size();i++){
                    int[] temp=service1.get(i);
                    series7.getData().add(new XYChart.Data("Petugas-"+(i+1), temp[0]));
                    System.out.println(temp[0]+"service lama");
                }
                XYChart.Series series8 = new XYChart.Series();
                series8.setName("BPJS Baru");
                for(int i=0;i<service2.size();i++){
                    int[]  temp=service2.get(i);
                    series8.getData().add(new XYChart.Data("Perawat"+(i+1), temp[1]));
                    System.out.println(temp[1]+"service Baru");
                }
                XYChart.Series series9 = new XYChart.Series();
                series9.setName("Emergency");
                for(int i=0;i<service3.size();i++){
                    int[]  temp=service3.get(i);
                    series9.getData().add(new XYChart.Data("Dokter-"+(i+1), temp[2]));
                    System.out.println(temp[2]+"service Emergency");
                }
                bc3.getData().addAll(series7,series8,series9);
                grid.add(bc3, 2, 0);
                fxPanel7.setScene(scene);
                
            }
          });

  }
     
      public void initFxComponents4(final LinkedList<double[]> waitingtime,final LinkedList<double[]> servicetime){

      Platform.runLater(new Runnable() {
          @Override
         public void run() {
            GridPane grid = new GridPane();
            Scene scene = new Scene(grid,600, 400);
            final CategoryAxis xAxis2 = new CategoryAxis();
                final NumberAxis yAxis2 = new NumberAxis();
                final BarChart<String,Number> bc2 =
                new BarChart<String,Number>(xAxis2,yAxis2);
                bc2.setTitle("Grafik Total Waiting Time per Loket");
                xAxis2.setLabel("Loket");
                yAxis2.setLabel("Waiting Time (menit)");
                XYChart.Series series2 = new XYChart.Series();
                series2.setName("BPJS Lama");
                int size2=waitingtime.size();
                for(int i=0;i<size2;i++){
                    double[] temp=waitingtime.get(i);
                    series2.getData().add(new XYChart.Data("Loket-"+(i+1),temp[0]));
                    System.out.println(temp[0]+"lama waiting");
                }
                XYChart.Series series3 = new XYChart.Series();
                series3.setName("BPJS Baru");
                for(int i=0;i<size2;i++){
                    double[] temp=waitingtime.get(i);
                    series3.getData().add(new XYChart.Data("Loket-"+(i+1), temp[1]));
                    System.out.println(temp[1]+"baru waiting");
                }
                bc2.getData().addAll(series2, series3);
                grid.add(bc2, 0, 0);
                
                final CategoryAxis xAxis3 = new CategoryAxis();
                final NumberAxis yAxis3 = new NumberAxis();
                final BarChart<String,Number> bc3 =
                new BarChart<String,Number>(xAxis3,yAxis3);
                bc3.setTitle("Grafik Total Service Time per Loket");
                xAxis3.setLabel("Loket");
                yAxis3.setLabel("Service Time (menit)");
                XYChart.Series series4 = new XYChart.Series();
                series4.setName("BPJS Lama");
                int size3=servicetime.size();
                for(int i=0;i<size3;i++){
                    double[] temp=servicetime.get(i);
                    series4.getData().add(new XYChart.Data("Loket-"+(i+1),temp[0]));
                    System.out.println(temp[0]+"lama service");
                }
                XYChart.Series series5 = new XYChart.Series();
                series5.setName("BPJS Baru");
                for(int i=0;i<size3;i++){
                    double[] temp=servicetime.get(i);
                    series5.getData().add(new XYChart.Data("Loket-"+(i+1), temp[1]));
                    System.out.println(temp[1]+"baru service");
                }
                bc3.getData().addAll(series4, series5);
                grid.add(bc3, 2, 0);
                fxPanel4.setScene(scene);
                
            }
          });

  }
      
     public void initFxComponents5(final LinkedList<int[]> pasien,final int max,final int max2,final int paramx){

      Platform.runLater(new Runnable() {
          @Override
         public void run() {
            GridPane grid = new GridPane();
            Scene scene = new Scene(grid,600, 400);
            final NumberAxis xAxis = new NumberAxis(0,max,paramx);
                final NumberAxis yAxis = new NumberAxis(0,max2,5);
                final LineChart<Number,Number> sc = new
                LineChart<Number,Number>(xAxis,yAxis);
                xAxis.setLabel("Menit ke-");
                yAxis.setLabel("Jumlah pasien");
                sc.setTitle("Jumlah pasien dilayani per "+paramx+" menit");
                XYChart.Series series1 = new XYChart.Series();
                series1.setName("BPJS Lama");
                int size3=pasien.size();
                int begin=0;
                for(int i=0;i<size3;i++,begin+=5){
                    int[] temp=pasien.get(i);
                    series1.getData().add(new XYChart.Data(begin,temp[0]));
                    System.out.println(temp[0]+"lama counter");
                }
                XYChart.Series series2 = new XYChart.Series();
                series2.setName("BPJS Baru");
                int begin2=0;
                 for(int i=0;i<size3;i++,begin2+=5){
                    int[] temp=pasien.get(i);
                    series2.getData().add(new XYChart.Data(begin2,temp[1]));
                    System.out.println(temp[1]+"baru counter");
                }
                sc.getData().addAll(series1, series2);
                grid.add(sc, 2, 0);
                fxPanel5.setScene(scene);
                
            }
          });

  }
     
      public void initFxComponents8(final LinkedList<int[]> pasien,final int max,final int max2,final int paramx){

      Platform.runLater(new Runnable() {
          @Override
         public void run() {
            GridPane grid = new GridPane();
            Scene scene = new Scene(grid,600, 400);
            final NumberAxis xAxis = new NumberAxis(0,max,paramx);
                final NumberAxis yAxis = new NumberAxis(0,max2,2);
                final LineChart<Number,Number> sc = new
                LineChart<Number,Number>(xAxis,yAxis);
                sc.setPrefSize(600,300);
                System.out.println("max 1 : "+max+" max 2 : "+max2);
                xAxis.setLabel("Menit ke-");
                yAxis.setLabel("Jumlah pasien");
                sc.setTitle("Jumlah pasien dilayani per "+paramx+" menit oleh petugas");
                XYChart.Series series1 = new XYChart.Series();
                series1.setName("BPJS Lama");
                int size3=pasien.size();
                int begin=0;
                for(int i=0;i<size3;i++,begin+=5){
                    int[] temp=pasien.get(i);
                    series1.getData().add(new XYChart.Data(begin,temp[0]));
                    System.out.println(temp[0]+"lama counter");
                }
                XYChart.Series series2 = new XYChart.Series();
                series2.setName("BPJS Baru");
                int begin2=0;
                 for(int i=0;i<size3;i++,begin2+=5){
                    int[] temp=pasien.get(i);
                    series2.getData().add(new XYChart.Data(begin2,temp[1]));
                    System.out.println(temp[1]+"baru counter");
                }
                sc.getData().addAll(series1, series2);
                grid.add(sc, 2, 0);
                fxPanel8.setScene(scene);
                
            }
          });

  }
      
      public void initFxComponents9(final LinkedList<int[]> pasien,final int max,final int max2,final int paramx){

      Platform.runLater(new Runnable() {
          @Override
         public void run() {
            GridPane grid = new GridPane();
            Scene scene = new Scene(grid,600, 400);
            final NumberAxis xAxis = new NumberAxis(0,max,paramx);
                final NumberAxis yAxis = new NumberAxis(0,max2,2);
                final LineChart<Number,Number> sc = new
                LineChart<Number,Number>(xAxis,yAxis);
                sc.setPrefSize(600,300);
                System.out.println("max 1 : "+max+" max 2 : "+max2);
                xAxis.setLabel("Menit ke-");
                yAxis.setLabel("Jumlah pasien");
                sc.setTitle("Jumlah pasien dilayani per "+paramx+" menit oleh perawat");
                XYChart.Series series1 = new XYChart.Series();
                series1.setName("BPJS Lama");
                int size3=pasien.size();
                int begin=0;
                for(int i=0;i<size3;i++,begin+=5){
                    int[] temp=pasien.get(i);
                    series1.getData().add(new XYChart.Data(begin,temp[0]));
                    System.out.println(temp[0]+"lama counter");
                }
                XYChart.Series series2 = new XYChart.Series();
                series2.setName("BPJS Baru");
                int begin2=0;
                 for(int i=0;i<size3;i++,begin2+=5){
                    int[] temp=pasien.get(i);
                    series2.getData().add(new XYChart.Data(begin2,temp[1]));
                    System.out.println(temp[1]+"baru counter");
                }
                sc.getData().addAll(series1, series2);
                grid.add(sc, 2, 0);
                fxPanel9.setScene(scene);
                
            }
          });

  }
      
      public void initFxComponents10(final LinkedList<int[]> pasien,final int max,final int max2,final int paramx){

      Platform.runLater(new Runnable() {
          @Override
         public void run() {
            GridPane grid = new GridPane();
            Scene scene = new Scene(grid,600, 400);
            final NumberAxis xAxis = new NumberAxis(0,max,paramx);
                final NumberAxis yAxis = new NumberAxis(0,max2,2);
                final LineChart<Number,Number> sc = new
                LineChart<Number,Number>(xAxis,yAxis);
                System.out.println("max 1 : "+max+" max 2 : "+max2);
                sc.setPrefSize(600,300);
                xAxis.setLabel("Menit ke-");
                yAxis.setLabel("Jumlah pasien");
                sc.setTitle("Jumlah pasien dilayani per "+paramx+" menit oleh dokter");
                XYChart.Series series1 = new XYChart.Series();
                series1.setName("BPJS Lama");
                int size3=pasien.size();
                int begin=0;
                for(int i=0;i<size3;i++,begin+=5){
                    int[] temp=pasien.get(i);
                    series1.getData().add(new XYChart.Data(begin,temp[0]));
                    System.out.println(temp[0]+"lama counter");
                }
                XYChart.Series series2 = new XYChart.Series();
                series2.setName("BPJS Baru");
                int begin2=0;
                 for(int i=0;i<size3;i++,begin2+=5){
                    int[] temp=pasien.get(i);
                    series2.getData().add(new XYChart.Data(begin2,temp[1]));
                    System.out.println(temp[1]+"baru counter");
                }
                XYChart.Series series3 = new XYChart.Series();
                series3.setName("Emergency");
                int begin3=0;
                for(int i=0;i<size3;i++,begin3+=5){
                    int[] temp=pasien.get(i);
                    series3.getData().add(new XYChart.Data(begin3,temp[2]));
                    System.out.println(temp[2]+"emergency counter");
                }
                sc.getData().addAll(series1, series2,series3);
                grid.add(sc, 2, 0);
                fxPanel10.setScene(scene);
                
            }
          });

  }


  public XYChart.Data getData(double x, double y){
    XYChart.Data data = new XYChart.Data<>();
    data.setXValue(x);
    data.setYValue(y);
    return data;
  }

  public XYChart.Data getData(double x, String y){
    XYChart.Data data = new XYChart.Data<>();
    data.setYValue(x);
    data.setXValue(y);
    return data;
  }
  
  public void setChart(int paramx,int bpjsb,int bpjsl,int emr, LinkedList<int[]> listcounterpasien,double[][] utility,int length,LinkedList<int[]> delaytime,LinkedList<double[]> waitingtime,LinkedList<double[]> servicetime,LinkedList<int[]> counterpasien,int maxtime,int maxpasien){
        panelanimasi.setVisible(true);
        panelanimasi.setLayout( new BorderLayout() );
        panelanimasi.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Grafik Pendaftaran Awal",TitledBorder.CENTER,TitledBorder.TOP));
        panelanimasi2.setVisible(true);
        panelanimasi2.setLayout( new BorderLayout() );
        panelanimasi2.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Grafik Pendaftaran Awal",TitledBorder.CENTER,TitledBorder.TOP));
        panelanimasi3.setVisible(true);
        panelanimasi3.setLayout( new BorderLayout() );
        panelanimasi3.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Grafik Pendaftaran Awal",TitledBorder.CENTER,TitledBorder.TOP));
        panelanimasi4.setVisible(true);
        panelanimasi4.setLayout( new BorderLayout() );
        panelanimasi4.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Grafik Pendaftaran Awal",TitledBorder.CENTER,TitledBorder.TOP));
        fxPanel.setVisible(true);
        fxPanel5.setVisible(true);
        fxPanel3.setVisible(true);
        fxPanel4.setVisible(true);
        getContentPane().add(fxPanel);
        getContentPane().add(fxPanel3);
        getContentPane().add(fxPanel4);
        getContentPane().add(fxPanel5);
        initFxComponents(bpjsb,bpjsl,emr,listcounterpasien);
        initFxComponents3(utility,length,delaytime);
        initFxComponents4(waitingtime,servicetime);
        initFxComponents5(counterpasien,maxtime,maxpasien,paramx);
        panelanimasi.add(fxPanel,BorderLayout.CENTER);
        panelanimasi2.add(fxPanel3,BorderLayout.CENTER);
        panelanimasi3.add(fxPanel4,BorderLayout.CENTER);
        panelanimasi4.add(fxPanel5,BorderLayout.CENTER);
  }

  public void setChartPoli(int bpjsb,int bpjsl,int emr,LinkedList<int[]> counterpetugas,LinkedList<int[]> counterperawat,LinkedList<int[]> counterdokter){
        panel_grafik_poli.setVisible(true);
        panel_grafik_poli.setLayout( new BorderLayout() );
        panel_grafik_poli.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Grafik Poliklinik",TitledBorder.CENTER,TitledBorder.TOP));
        fxPanel2.setVisible(true);
        getContentPane().add(  fxPanel2 );
        initFxComponents2(bpjsb,bpjsl,emr,counterpetugas,counterperawat,counterdokter);
        panel_grafik_poli.add(fxPanel2,BorderLayout.CENTER);
  }
  
  
  public void setChartPoli2(double[][] utility1,double[][] utility2,double[][] utility3,int length1,int length2,int length3,LinkedList<int[]> delaypetugas,LinkedList<int[]> delayperawat,LinkedList<int[]> delaydokter){
        panel_grafik_poli2.setVisible(true);
        panel_grafik_poli2.setLayout( new BorderLayout() );
        panel_grafik_poli2.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Grafik Poliklinik",TitledBorder.CENTER,TitledBorder.TOP));
        fxPanel6.setVisible(true);
        getContentPane().add(  fxPanel6 );
        initFxComponents6(utility1,utility2,utility3,length1,length2,length3,delaypetugas,delayperawat,delaydokter);
        panel_grafik_poli2.add(fxPanel6,BorderLayout.CENTER);
  }
  
  public void setChartPoli3(LinkedList<int[]> waiting1,LinkedList<int[]> waiting2,LinkedList<int[]> waiting3,LinkedList<int[]> service1,LinkedList<int[]> service2,LinkedList<int[]> service3){
        panel_grafik_poli3.setVisible(true);
        panel_grafik_poli3.setLayout( new BorderLayout() );
        panel_grafik_poli3.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Grafik Poliklinik",TitledBorder.CENTER,TitledBorder.TOP));
        fxPanel7.setVisible(true);
        getContentPane().add(  fxPanel7 );
        initFxComponents7(waiting1,waiting2,waiting3,service1,service2,service3);
        panel_grafik_poli3.add(fxPanel7,BorderLayout.CENTER);
  }
  
   public void setChartPoli4(int paramx,LinkedList<int[]> counter1,int max1,int max2){
        panel_grafik_poli4.setVisible(true);
        panel_grafik_poli4.setLayout( new BorderLayout() );
        panel_grafik_poli4.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Grafik Poliklinik",TitledBorder.CENTER,TitledBorder.TOP));
        fxPanel8.setVisible(true);
        getContentPane().add(  fxPanel8 );
        initFxComponents8(counter1,max1,max2,paramx);
        panel_grafik_poli4.add(fxPanel8,BorderLayout.CENTER);
  }
   
    public void setChartPoli5(int paramx,LinkedList<int[]> counter1,int max1,int max2){
        panel_grafik_poli5.setVisible(true);
        panel_grafik_poli5.setLayout( new BorderLayout() );
        panel_grafik_poli5.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Grafik Poliklinik",TitledBorder.CENTER,TitledBorder.TOP));
        fxPanel9.setVisible(true);
        getContentPane().add(  fxPanel9 );
        initFxComponents9(counter1,max1,max2,paramx);
        panel_grafik_poli5.add(fxPanel9,BorderLayout.CENTER);
  }
    
     public void setChartPoli6(int paramx,LinkedList<int[]> counter1,int max1,int max2)  { 
        panel_grafik_poli6.setVisible(true);
        panel_grafik_poli6.setLayout( new BorderLayout() );
        panel_grafik_poli6.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Grafik Poliklinik",TitledBorder.CENTER,TitledBorder.TOP));
        fxPanel10.setVisible(true);
        getContentPane().add(  fxPanel10 );
        initFxComponents10(counter1,max1,max2,paramx);
        panel_grafik_poli6.add(fxPanel10,BorderLayout.CENTER);
  }
     
  public void setChartPoli42(int paramx,LinkedList<int[]> counter1,int max1,int max2){
        initFxComponents8(counter1,max1,max2,paramx);
  }
   
    public void setChartPoli52(int paramx,LinkedList<int[]> counter1,int max1,int max2){
        initFxComponents9(counter1,max1,max2,paramx);
  }
    
     public void setChartPoli62(int paramx,LinkedList<int[]> counter1,int max1,int max2)  { 
        initFxComponents10(counter1,max1,max2,paramx);
  }
     
     public void setChartAwal(int paramx,LinkedList<int[]> counter1,int max1,int max2){
         initFxComponents5(counter1,max1,max2,paramx);
     }
      public void showLoading(){
         loading.setVisible(true);
         loading1.setVisible(true);
         loading2.setVisible(true);
         loading3.setVisible(true);
     }
     
     public void hideLoading(){
         loading.setVisible(false);
         loading1.setVisible(false);
         loading2.setVisible(false);
         loading3.setVisible(false);
     }
     
     public void showLoadingPoli(){
         loadingpoli.setVisible(true);
         loadingpoli1.setVisible(true);
         loadingpoli2.setVisible(true);
         loadingpoli3.setVisible(true);
         loadingpoli4.setVisible(true);
         loadingpoli5.setVisible(true);
     }
     
     public void hideLoadingPoli(){
         loadingpoli.setVisible(false);
         loadingpoli1.setVisible(false);
         loadingpoli2.setVisible(false);
         loadingpoli3.setVisible(false);
         loadingpoli4.setVisible(false);
         loadingpoli5.setVisible(false);
     }
     
    public void disableReportTable(){
        jButton1.setEnabled(false);
        report.setEnabled(false);
    }
     
     public void showErrorReading(){
         JOptionPane.showMessageDialog(this,"Gagal membaca file","Alert",JOptionPane.ERROR_MESSAGE);
     }
     
    private void openActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openActionPerformed
        // TODO add your handling code here:
        if(evt.getSource()==open){
            int returnVal = fc.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //This is where a real application would open the file.
                System.out.println(file.getName());
                System.out.println(file.getPath());
                file_name.setText(file.getName());
                this.excel=new ExcelReader(file.getPath(),file.getName());
            } else {
                fc.cancelSelection();
            }
        }
    }//GEN-LAST:event_openActionPerformed

    private void fileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileActionPerformed
        // TODO add your handling code here:
       disableAcak();
    }//GEN-LAST:event_fileActionPerformed

    private void acakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acakActionPerformed
        // TODO add your handling code here:
        enableAcak();
        disableServer();
    }//GEN-LAST:event_acakActionPerformed

    private void save_table1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_table1ActionPerformed
        // TODO add your handling code here:
        if(evt.getSource()==save_table1){
            int returnVal = fs.showSaveDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fs.getSelectedFile();
                //This is where a real application would open the file.
                System.out.println(file.getName());
                System.out.println(file.getPath());
                this.writer=new ExcelWriter(file.getPath());
                LinkedList<Object[]> data=gen.saveIntoTable(arrayServer);
                boolean cek=writer.writeExcelFile(data);
                if(cek==true){
                    JOptionPane.showMessageDialog(this,"Sukses menyimpan file ke disk","Alert",JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                     JOptionPane.showMessageDialog(this,"Gagal menyimpan file ke disk","Alert",JOptionPane.ERROR_MESSAGE);
                }
            } else {
                fs.cancelSelection();
            }
        }
    }//GEN-LAST:event_save_table1ActionPerformed

    private void save_table2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_table2ActionPerformed
        // TODO add your handling code here:
        if(evt.getSource()==save_table2){
            int returnVal = fs2.showSaveDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fs2.getSelectedFile();
                //This is where a real application would open the file.
                System.out.println(file.getName());
                System.out.println(file.getPath());
                this.writer=new ExcelWriter(file.getPath());
                LinkedList<Object[]> data=gen.saveIntoTable2(arrayDokter);
                boolean cek=writer.writeExcelFilePoli(data);
                if(cek==true){
                    JOptionPane.showMessageDialog(this,"Sukses menyimpan file ke disk","Alert",JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                     JOptionPane.showMessageDialog(this,"Gagal menyimpan file ke disk","Alert",JOptionPane.ERROR_MESSAGE);
                }
            } else {
                fs2.cancelSelection();
            }
        }
    }//GEN-LAST:event_save_table2ActionPerformed

    private void save_report_awalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_report_awalActionPerformed
        // TODO add your handling code here:
         if(evt.getSource()==save_report_awal){
            int returnVal = fs3.showSaveDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fs3.getSelectedFile();
                //This is where a real application would open the file.
                System.out.println(file.getName());
                System.out.println(file.getPath());
                this.writer=new ExcelWriter(file.getPath());
                Object[][] data=gen.generateUtilityServer5(arrayServer);
                Customer[] arrayreport1=gen.sortCustomer(arrayServer); 
                
               String[] columnNames2=gen.generateColumnNamesServer5(arrayServer);
               double summary=gen.generateSummaryArrivalTime(arrayreport1);
               double bpjslama=gen.generateArrivalTimeBPJSLama(arrayreport1);
               double bpjsbaru=gen.generateArrivalTimeBPJSBaru(arrayreport1);
               Object[] arrivaltime=new Object[3];
               arrivaltime[0]=summary+" pasien per 2 menit";
               arrivaltime[1]=bpjslama+" pasien per 2 menit ";
               arrivaltime[2]=bpjsbaru+" pasien per 2 menit ";
               
               double summary2=gen.generateAverageInterArrivalTime(arrayreport1);
               double bpjslama2=gen.generateAverageInterArrivalTimeBPJSLama(arrayreport1);
               double bpjsbaru2=gen.generateAverageInterArrivalTimeBPJSBaru(arrayreport1);
               Object[] inter=new Object[3];
               inter[0]=gen.convertSeconds(summary2)+" ";
               inter[1]=gen.convertSeconds(bpjslama2)+" ";
               inter[2]=gen.convertSeconds(bpjsbaru2)+" ";
                       
               double summary22=gen.generateServiceRate5(arrayServer);
               double bpjslama22=gen.generateServiceRateBPJSLama5(arrayServer);
               double bpjsbaru22=gen.generateServiceRateBPJSBaru5(arrayServer);
               Object[] service=new Object[3];
               service[0]=summary22+" pasien per menit";
               service[1]=bpjslama22+" pasien per menit";
               service[2]=bpjsbaru22+" pasien per menit";
               
               Object[][] averageservicetime=gen.generateAverageServiceTime5(arrayServer);
               Object[][] averagewaitingtime=gen.generateAverageWaitingTime5(arrayServer);
               Object[][] averagedelaytime=gen.generateAverageDelayTime5(arrayServer);
               Object[][] totalspenttime=gen.generateTotalSpentTimeServer5(arrayServer);
               String s=gen.generateSummaryOutput2(arrayServer);
               boolean cek=writer.writeExcelFileReportAwal(columnNames2,data,arrivaltime,inter,service,averageservicetime,averagewaitingtime,averagedelaytime,totalspenttime,s);
                if(cek==true){
                    JOptionPane.showMessageDialog(this,"Sukses menyimpan file ke disk","Alert",JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                     JOptionPane.showMessageDialog(this,"Gagal menyimpan file ke disk","Alert",JOptionPane.ERROR_MESSAGE);
                }
            } else {
                fs3.cancelSelection();
            }
        }
    }//GEN-LAST:event_save_report_awalActionPerformed

    private void save_report_petugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_report_petugasActionPerformed
        // TODO add your handling code here:
        if(evt.getSource()==save_report_petugas){
            int returnVal = fs3.showSaveDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fs3.getSelectedFile();
                //This is where a real application would open the file.
                System.out.println(file.getName());
                System.out.println(file.getPath());
                this.writer=new ExcelWriter(file.getPath());
                Object[][] data=gen.generateUtilityServer6(arrayPetugas);
                Customer[] arrayreport1=gen.sortCustomer2(arrayPetugas); 
                
               String[] columnNames2=gen.generateColumnNamesServer6(arrayPetugas);
               double summary=gen.generateSummaryArrivalTime2(arrayreport1);
               double bpjslama=gen.generateArrivalTimeBPJSLama2(arrayreport1);
               double bpjsbaru=gen.generateArrivalTimeBPJSBaru2(arrayreport1);
               Object[] arrivaltime=new Object[3];
               arrivaltime[0]=summary+" pasien per 2 menit ";
               arrivaltime[1]=bpjslama+" pasien per 2 menit ";
               arrivaltime[2]=bpjsbaru+" pasien per 2 menit";
               
               double summary2=gen.generateAverageInterArrivalTime2(arrayreport1);
               double bpjslama2=gen.generateAverageInterArrivalTimeBPJSLama2(arrayreport1);
               double bpjsbaru2=gen.generateAverageInterArrivalTimeBPJSBaru2(arrayreport1);
               Object[] inter=new Object[3];
               inter[0]=gen.convertSeconds(summary2)+" ";
               inter[1]=gen.convertSeconds(bpjslama2)+" ";
               inter[2]=gen.convertSeconds(bpjsbaru2)+" ";
                       
               double summary22=gen.generateServiceRate6(arrayPetugas);
               double bpjslama22=gen.generateServiceRateBPJSLama6(arrayPetugas);
               double bpjsbaru22=gen.generateServiceRateBPJSBaru2(arrayPetugas);
               Object[] service=new Object[3];
               service[0]=summary22+"  pasien per menit ";
               service[1]=bpjslama22+" pasien per menit";
               service[2]=bpjsbaru22+" pasien per menit";
               
               Object[][] averageservicetime=gen.generateAverageServiceTime6(arrayPetugas);
               Object[][] averagewaitingtime=gen.generateAverageWaitingTime6(arrayPetugas);
               Object[][] averagedelaytime=gen.generateAverageDelayTime6(arrayPetugas);
               Object[][] totalspenttime=gen.generateTotalSpentTimeServer6(arrayPetugas);
               String s=gen.generateSummaryOutputPetugas(arrayPetugas);
               boolean cek=writer.writeExcelFileReportPetugas(columnNames2,data,arrivaltime,inter,service,averageservicetime,averagewaitingtime,averagedelaytime,totalspenttime,s);
                if(cek==true){
                    JOptionPane.showMessageDialog(this,"Sukses menyimpan file ke disk","Alert",JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                     JOptionPane.showMessageDialog(this,"Gagal menyimpan file ke disk","Alert",JOptionPane.ERROR_MESSAGE);
                }
            } else {
                fs3.cancelSelection();
            }
        }
    }//GEN-LAST:event_save_report_petugasActionPerformed

    private void save_report_perawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_report_perawatActionPerformed
        // TODO add your handling code here:
        if(evt.getSource()==save_report_perawat){
            int returnVal = fs3.showSaveDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fs3.getSelectedFile();
                //This is where a real application would open the file.
                System.out.println(file.getName());
                System.out.println(file.getPath());
                this.writer=new ExcelWriter(file.getPath());
                Object[][] data=gen.generateUtilityServer3(arrayPerawat);
                Customer[] arrayreport1=gen.sortCustomer3(arrayPerawat); 
                
               String[] columnNames2=gen.generateColumnNamesServer3(arrayPerawat);
               double summary=gen.generateSummaryArrivalTime3(arrayreport1);
               double bpjslama=gen.generateArrivalTimeBPJSLama3(arrayreport1);
               double bpjsbaru=gen.generateArrivalTimeBPJSBaru3(arrayreport1);
               Object[] arrivaltime=new Object[3];
               arrivaltime[0]=summary+" pasien per 2 menit ";
               arrivaltime[1]=bpjslama+" pasien per 2 menit ";
               arrivaltime[2]=bpjsbaru+" pasien per 2 menit ";
               
               double summary2=gen.generateAverageInterArrivalTime3(arrayreport1);
               double bpjslama2=gen.generateAverageInterArrivalTimeBPJSLama3(arrayreport1);
               double bpjsbaru2=gen.generateAverageInterArrivalTimeBPJSBaru3(arrayreport1);
               Object[] inter=new Object[3];
               inter[0]=gen.convertSeconds(summary2)+" ";
               inter[1]=gen.convertSeconds(bpjslama2)+" ";
               inter[2]=gen.convertSeconds(bpjsbaru2)+" ";
                       
               double summary22=gen.generateServiceRate3(arrayPerawat);
               double bpjslama22=gen.generateServiceRateBPJSLama3(arrayPerawat);
               double bpjsbaru22=gen.generateServiceRateBPJSBaru3(arrayPerawat);
               Object[] service=new Object[3];
               service[0]=summary22+" pasien per menit";
               service[1]=bpjslama22+" pasien per menit";
               service[2]=bpjsbaru22+" pasien per menit";
               
               Object[][] averageservicetime=gen.generateAverageServiceTime3(arrayPerawat);
               Object[][] averagewaitingtime=gen.generateAverageWaitingTime3(arrayPerawat);
               Object[][] averagedelaytime=gen.generateAverageDelayTime3(arrayPerawat);
               Object[][] totalspenttime=gen.generateTotalSpentTimeServer3(arrayPerawat);
               String s=gen.generateSummaryOutputPerawat(arrayPerawat);
               boolean cek=writer.writeExcelFileReportPerawat(columnNames2,data,arrivaltime,inter,service,averageservicetime,averagewaitingtime,averagedelaytime,totalspenttime,s);
                if(cek==true){
                    JOptionPane.showMessageDialog(this,"Sukses menyimpan file ke disk","Alert",JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                     JOptionPane.showMessageDialog(this,"Gagal menyimpan file ke disk","Alert",JOptionPane.ERROR_MESSAGE);
                }
            } else {
                fs3.cancelSelection();
            }
        }
    }//GEN-LAST:event_save_report_perawatActionPerformed

    private void save_report_dokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_report_dokterActionPerformed
        // TODO add your handling code here:
         if(evt.getSource()==save_report_dokter){
            int returnVal = fs3.showSaveDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fs3.getSelectedFile();
                //This is where a real application would open the file.
                System.out.println(file.getName());
                System.out.println(file.getPath());
                this.writer=new ExcelWriter(file.getPath());
                Object[][] data=gen.generateUtilityServer4(arrayDokter);
                Customer[] arrayreport1=gen.sortCustomer4(arrayDokter); 
                
               String[] columnNames2=gen.generateColumnNamesServer4(arrayDokter);
               double summary=gen.generateSummaryArrivalTime4(arrayreport1);
               double bpjslama=gen.generateArrivalTimeBPJSLama4(arrayreport1);
               double bpjsbaru=gen.generateArrivalTimeBPJSBaru4(arrayreport1);
               double emergency=gen.generateArrivalTimeEmergency(arrayreport1);
               Object[] arrivaltime=new Object[4];
               arrivaltime[0]=summary+" per 2 menit ";
               arrivaltime[1]=bpjslama+" per 2 menit ";
               arrivaltime[2]=bpjsbaru+" per 2 menit ";
               arrivaltime[3]=emergency+" per 2 menit ";
               
               double summary2=gen.generateAverageInterArrivalTime4(arrayreport1);
               double bpjslama2=gen.generateAverageInterArrivalTimeBPJSLama4(arrayreport1);
               double bpjsbaru2=gen.generateAverageInterArrivalTimeBPJSBaru4(arrayreport1);
               double emergency2=gen.generateAverageInterArrivalTimeEmergency(arrayreport1);
               Object[] inter=new Object[4];
               inter[0]=gen.convertSeconds(summary2)+" ";
               inter[1]=gen.convertSeconds(bpjslama2)+" ";
               inter[2]=gen.convertSeconds(bpjsbaru2)+" ";
               inter[3]=gen.convertSeconds(emergency2)+" ";
                
               double summary22=gen.generateServiceRate4(arrayDokter);
               double bpjslama22=gen.generateServiceRateBPJSLama4(arrayDokter);
               double bpjsbaru22=gen.generateServiceRateBPJSBaru4(arrayDokter);
               double emergency3=gen.generateServiceRateEmergency(arrayDokter);
               Object[] service=new Object[4];
               service[0]=summary22+" pasien per menit";
               service[1]=bpjslama22+" pasien per menit";
               service[2]=bpjsbaru22+" pasien per menit";
               service[3]=emergency3+" pasien per menit";
               
               Object[][] averageservicetime=gen.generateAverageServiceTime4(arrayDokter);
               Object[][] averagewaitingtime=gen.generateAverageWaitingTime4(arrayDokter);
               Object[][] averagedelaytime=gen.generateAverageDelayTime4(arrayDokter);
               Object[][] totalspenttime=gen.generateTotalSpentTimeServer4(arrayDokter);
               String s=gen.generateSummaryOutputDokter(arrayDokter);
               boolean cek=writer.writeExcelFileReportPerawat(columnNames2,data,arrivaltime,inter,service,averageservicetime,averagewaitingtime,averagedelaytime,totalspenttime,s);
                if(cek==true){
                    JOptionPane.showMessageDialog(this,"Sukses menyimpan file ke disk","Alert",JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                     JOptionPane.showMessageDialog(this,"Gagal menyimpan file ke disk","Alert",JOptionPane.ERROR_MESSAGE);
                }
            } else {
                fs3.cancelSelection();
            }
        }
    }//GEN-LAST:event_save_report_dokterActionPerformed

    public void showLoadingAwal(){
        loading3.setVisible(true);
        
    }
    
    public void hideLoadingAwal(){
        loading3.setVisible(false);
        
    }
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int param=(int)param_grafik.getValue();
        sim.setParamxchart(param);
        for(int i=0;i<10.000;i++){
            System.out.println("loading");
            showLoadingAwal();
        }
        hideLoadingAwal();
        sim.displayChart4();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        int param=(int)param_grafik2.getValue();
        sim.setParamxchart2(param);
        for(int i=0;i<10.000;i++){
            System.out.println("loading");
            showLoadingChart();
        }
        hideLoadingChart();
        sim.displayChart3();
    }//GEN-LAST:event_jButton4ActionPerformed

    public void showSaveError(){
         JOptionPane.showMessageDialog(this,"gagal untuk menyimpan file","Alert",JOptionPane.ERROR_MESSAGE);         
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
            java.util.logging.Logger.getLogger(InterfaceGUI2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfaceGUI2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfaceGUI2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfaceGUI2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfaceGUI2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton acak;
    private javax.swing.JButton back;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JFileChooser fc;
    private javax.swing.JRadioButton file;
    private javax.swing.JLabel file_name;
    private javax.swing.JFrame frame_report;
    private javax.swing.JFrame frame_report2;
    private javax.swing.JFrame frame_report3;
    private javax.swing.JFrame frame_report4;
    private javax.swing.JFileChooser fs;
    private javax.swing.JFileChooser fs2;
    private javax.swing.JFileChooser fs3;
    private javax.swing.JTextArea hasil;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel label_grafik1;
    private javax.swing.JLabel label_grafik2;
    private javax.swing.JLabel label_grafik3;
    private javax.swing.JLabel label_grafik4;
    private javax.swing.JLabel label_grafik5;
    private javax.swing.JLabel label_grafik6;
    private javax.swing.JLabel loading;
    private javax.swing.JLabel loading1;
    private javax.swing.JLabel loading2;
    private javax.swing.JLabel loading3;
    private javax.swing.JLabel loadingpoli;
    private javax.swing.JLabel loadingpoli1;
    private javax.swing.JLabel loadingpoli2;
    private javax.swing.JLabel loadingpoli3;
    private javax.swing.JLabel loadingpoli4;
    private javax.swing.JLabel loadingpoli5;
    private javax.swing.JButton open;
    private javax.swing.JTextArea output2;
    private javax.swing.JTextArea output_dokter;
    private javax.swing.JTextArea output_perawat;
    private javax.swing.JTextArea output_petugas;
    private javax.swing.JTextArea output_poli;
    private javax.swing.JPanel panel1;
    private javax.swing.JPanel panel10;
    private javax.swing.JPanel panel11;
    private javax.swing.JPanel panel12;
    private javax.swing.JPanel panel13;
    private javax.swing.JPanel panel14;
    private javax.swing.JPanel panel15;
    private javax.swing.JPanel panel16;
    private javax.swing.JPanel panel17;
    private javax.swing.JPanel panel18;
    private javax.swing.JPanel panel19;
    private javax.swing.JPanel panel2;
    private javax.swing.JPanel panel20;
    private javax.swing.JPanel panel21;
    private javax.swing.JPanel panel22;
    private javax.swing.JPanel panel23;
    private javax.swing.JPanel panel24;
    private javax.swing.JPanel panel3;
    private javax.swing.JPanel panel4;
    private javax.swing.JPanel panel5;
    private javax.swing.JPanel panel6;
    private javax.swing.JPanel panel7;
    private javax.swing.JPanel panel8;
    private javax.swing.JPanel panel9;
    private javax.swing.JPanel panel_arrival_rate;
    private javax.swing.JPanel panel_average;
    private javax.swing.JPanel panel_delay_time;
    private javax.swing.JPanel panel_dokter;
    private javax.swing.JPanel panel_grafik_poli;
    private javax.swing.JPanel panel_grafik_poli2;
    private javax.swing.JPanel panel_grafik_poli3;
    private javax.swing.JPanel panel_grafik_poli4;
    private javax.swing.JPanel panel_grafik_poli5;
    private javax.swing.JPanel panel_grafik_poli6;
    private javax.swing.JPanel panel_interarrival;
    private javax.swing.JPanel panel_pasien;
    private javax.swing.JPanel panel_perawat;
    private javax.swing.JPanel panel_petugas;
    private javax.swing.JPanel panel_poli;
    private javax.swing.JPanel panel_service_rate;
    private javax.swing.JPanel panel_tabel;
    private javax.swing.JPanel panel_tabel_utility;
    private javax.swing.JPanel panel_waiting_time;
    private javax.swing.JPanel panelanimasi;
    private javax.swing.JPanel panelanimasi2;
    private javax.swing.JPanel panelanimasi3;
    private javax.swing.JPanel panelanimasi4;
    private javax.swing.JSpinner param_grafik;
    private javax.swing.JSpinner param_grafik2;
    private javax.swing.JLabel pasien_baru;
    private javax.swing.JLabel pasien_emergency;
    private javax.swing.JLabel pasien_lama;
    private javax.swing.JToggleButton pause;
    private javax.swing.JToggleButton play;
    private javax.swing.JSpinner queue_capacity;
    private javax.swing.JButton report;
    private javax.swing.JButton save_report_awal;
    private javax.swing.JButton save_report_dokter;
    private javax.swing.JButton save_report_perawat;
    private javax.swing.JButton save_report_petugas;
    private javax.swing.JButton save_table1;
    private javax.swing.JButton save_table2;
    private javax.swing.JLabel ser_awal;
    private javax.swing.JLabel ser_dok;
    private javax.swing.JLabel ser_per;
    private javax.swing.JLabel ser_pet;
    private javax.swing.JSpinner server_awal;
    private javax.swing.JSpinner server_poli;
    private javax.swing.JSpinner server_poli2;
    private javax.swing.JSpinner server_poli3;
    private javax.swing.JSlider slider;
    private javax.swing.JToggleButton stop;
    private javax.swing.JTabbedPane tab1;
    private javax.swing.JTable tabel1;
    private javax.swing.JTable tabel2;
    private javax.swing.JLabel total;
    private javax.swing.JLabel total_bpjsb_poli;
    private javax.swing.JLabel total_bpjsl_poli;
    private javax.swing.JLabel total_emergency_poli;
    private javax.swing.JLabel total_poli;
    // End of variables declaration//GEN-END:variables
}
