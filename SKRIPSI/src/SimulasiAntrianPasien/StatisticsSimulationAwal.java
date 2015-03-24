/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SimulasiAntrianPasien;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.Point2D;
import java.math.BigDecimal;
import java.util.*;
import java.util.Random;
import javax.swing.JPanel;
import GUI.InterfaceGUI1;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author robby
 * @version 1.0 
 */
public class StatisticsSimulationAwal  extends Thread {
    
    public  double clock; 
    private StatisticsGenerator gen;
    private int numOfServer;
    private int numOfCustomer;
    private int slidervalue;
     private ServerAwal[] server;
     public CustomerQ customerqueue;
     public Queue customerqueue2;
     private double counterPasienLama;
     private double counterPasienBaru;
     private double counterPasienEmergency;
     protected Thread animator;
     private InterfaceGUI1 MainGUI;
     private int number;
     private int kapasitasantrian;
     private int counterfindserver;
     private Random rand;
     private StatisticsSimulationPoli poli;
     private double counterPasienBPJSLama;
     private double counterPasienBPJSBaru;
     private double counterPasienEmergency2;
     private double counterPasienBPJSLama2;
     private double counterPasienBPJSBaru2;
     private double counterPasienEmergency3;
     private ServerPetugas[] serverpetugas;
     private ServerPerawat[] serverperawat;
     private ServerDokter[] serverdokter;
     private int paramxchart;
     private int paramxchart2;
     
 public StatisticsSimulationAwal(int numOfCustomer,ServerAwal[] server,StatisticsGenerator gen,double ratio2,InterfaceGUI1 MainGUI,int kapasitasantrian){
        super();
        this.MainGUI=MainGUI;
        this.server=server;
        this.gen=gen;
        this.customerqueue=new CustomerQ();
        this.counterPasienEmergency=ratio2*numOfCustomer;
        BigDecimal bd=new BigDecimal(this.counterPasienEmergency);
        bd = bd.setScale(0,BigDecimal.ROUND_UP); 
        this.counterPasienEmergency = bd.doubleValue();
        //perbandingan pasien BPJS Lama dengan BPJS Baru 3 : 7 selama penelitian
        this.counterPasienLama=(numOfCustomer-this.counterPasienEmergency)*0.3;
        bd = new BigDecimal(this.counterPasienLama); 
        bd = bd.setScale(0,BigDecimal.ROUND_UP); 
        this.counterPasienLama=bd.doubleValue();
        this.counterPasienBaru=numOfCustomer-(this.counterPasienEmergency+this.counterPasienLama);
        bd = new BigDecimal(this.counterPasienBaru); 
        bd = bd.setScale(0,BigDecimal.ROUND_UP); 
        this.counterPasienBaru=bd.doubleValue();
        this.number=1;   
        this.numOfCustomer=numOfCustomer;
        System.out.println(this.counterPasienBaru+" "+this.counterPasienEmergency+" "+this.counterPasienLama);
        this.kapasitasantrian=kapasitasantrian;
        this.slidervalue=700;
        this.counterfindserver=0;
        this.rand=new Random();
        this.counterPasienBPJSBaru=this.counterPasienBaru;
        this.counterPasienBPJSLama=this.counterPasienLama;
        this.counterPasienEmergency2=this.counterPasienEmergency;
        this.counterPasienBPJSBaru2=0;
        this.counterPasienBPJSLama2=0;
        this.counterPasienEmergency3=0;
        this.paramxchart=5;
        this.paramxchart2=5;
        this.animator=new Thread(this);
    }
 
    public StatisticsSimulationAwal(){
        
    }
    
    public void setPetugas(ServerPetugas[] serverpetugas){
        this.serverpetugas=serverpetugas;
    }
    
    public void setPerawat(ServerPerawat[] serverperawat){
        this.serverperawat=serverperawat;
    }
    
    public void setDokter(ServerDokter[] serverdokter){
        this.serverdokter=serverdokter;
    }
 
    public Customer createPatientBPJSBaru(){
       Customer c=new Customer();
       c.setJenis("BPJS Baru");
       c.setPriority(1);
       c.setNumber(this.getNumber());
       this.setCounterPasienBaru(this.getCounterPasienBaru() - 1);
        this.counterPasienBPJSBaru2++;
       return c;
    }
    
    public Customer createPatientBPJSLama(){
       Customer c=new Customer();
       c.setJenis("BPJS Lama");
       c.setPriority(1);
       c.setNumber(this.getNumber());
       this.setCounterPasienLama(this.getCounterPasienLama() - 1);
        this.counterPasienBPJSLama2++;
       return c;
    }
    
    public Customer createEmergencyPatient(){
       Customer c=new Customer();
       c.setJenis("Emergency");
       c.setPriority(2);
       c.setNumber(this.getNumber());
       this.setCounterPasienEmergency(this.getCounterPasienEmergency() - 1);
       this.counterPasienEmergency3++;
       return c;
    }
    
    public boolean cekCounterPasienBaru()
    {
        if(this.getCounterPasienBaru()==0){
            return false;
        }
        else{
            return true;
        }
    }  
    
    public boolean cekCounterPasienLama()
    {
        if(this.getCounterPasienLama()==0){
            return false;
        }
        else{
            return true;
        }
    } 
    
    public boolean cekCounterPasienEmergency()
    {
        if(this.getCounterPasienEmergency()==0){
            return false;
        }
        else{
            return true;
        }
    } 
    
    public Customer createRandomPatientnotEmergency(){
         Random rand=new Random();
         int r=rand.nextInt(2)+1;
         Customer ret=new Customer();
         if(r==1){
                if (this.cekCounterPasienBaru()){
                    ret = this.createPatientBPJSBaru();
                }
                else{
                    ret = this.createPatientBPJSLama();
                }
          }
         else if(r==2) {
                if (this.cekCounterPasienLama()){
                    ret = this.createPatientBPJSLama();
                }
                else{
                    ret = this.createPatientBPJSBaru();
                }
            }
        return ret;
    }
    
    
    public Customer createRandomPatient(){
        Random rand=new Random();
        int r=rand.nextInt(3)+1;
        Customer ret=new Customer();
            if(r==1){
                if (this.cekCounterPasienEmergency()){
                    ret = this.createEmergencyPatient();
                }
                else{
                    r=rand.nextInt(2)+1;
                    if(r==1){
                        boolean cek=this.cekCounterPasienBaru();
                        if(cek){
                            ret =this.createPatientBPJSBaru();
                        }
                        else{
                            ret = this.createPatientBPJSLama();
                        }
                    }
                    else if(r==2){
                        boolean cek=this.cekCounterPasienLama();
                        if(cek){
                            ret = this.createPatientBPJSLama();
                        }
                        else{
                            ret = this.createPatientBPJSBaru();
                        }
                    }
                }
            }
            else if(r==2){
                if (this.cekCounterPasienBaru()){
                    ret = this.createPatientBPJSBaru();
                }
                else{
                    r=rand.nextInt(2)+1;
                    if(r==1){
                        boolean cek=this.cekCounterPasienEmergency();
                        if(cek){
                            ret = this.createEmergencyPatient();
                        }
                        else{
                            ret = this.createPatientBPJSLama();
                        }
                    }
                    else if(r==2){
                        boolean cek=this.cekCounterPasienLama();
                        if(cek){
                            ret = this.createPatientBPJSLama();
                        }
                        else{
                            ret = this.createEmergencyPatient();
                        }
                    }
                }
            }
            else if(r==3) {
                if (this.cekCounterPasienLama()){
                    ret = this.createPatientBPJSLama();
                }
                else{
                    r=rand.nextInt(2)+1;
                    if(r==1){
                        boolean cek=this.cekCounterPasienEmergency();
                        if(cek){
                            ret =  this.createEmergencyPatient();
                        }
                        else{
                            ret =  this.createPatientBPJSBaru();
                        }
                    }
                    else if(r==2){
                        boolean cek=this.cekCounterPasienBaru();
                        if(cek){
                           ret =  this.createPatientBPJSBaru();
                        }
                        else{
                            ret =  this.createEmergencyPatient();
                        }
                    }
                }
            }
        return ret;
   }
    
    public Customer processArrival(){
        Customer temp=this.createRandomPatient();
        double arrivaltime=gen.generateArrivalTime();
        clock=clock+arrivaltime;
        temp.setArrivaltime(clock);
        if(temp.getJenis().equals("Emergency")){
            temp.setArrivaltimepoli(temp.getArrivaltime());
        }
        if(temp.getJenis().equals("Emergency")){
            temp.setNumber(0);
        }
        else{
            temp.setNumber(this.getNumber());
        }
        customerqueue.add(temp);
        String realtime=gen.convertSeconds(clock);
        MainGUI.setOutputValue(temp.getNumber()+" "+temp.getJenis()+" "+realtime);
        MainGUI.setOutputCounter((int)this.getCounterPasienBaru(),(int)this.getCounterPasienLama(),(int)this.getCounterPasienEmergency());
        try {
                       Thread.sleep(this.slidervalue);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());;
                }
        if(!temp.getJenis().equals("Emergency")){
            this.setNumber(this.getNumber() + 1);
        }
        return temp;
    }
    
     public Customer processArrival2(){
        Customer temp=this.createRandomPatientnotEmergency();
        double arrivaltime=gen.generateArrivalTime();
        clock=clock+arrivaltime;
        temp.setArrivaltime(clock);
        temp.setNumber(this.getNumber());
        customerqueue.add(temp);
        String realtime=gen.convertSeconds(clock);
        MainGUI.setOutputValue(temp.getNumber()+" "+temp.getJenis()+" "+realtime);
        MainGUI.setOutputCounter((int)this.getCounterPasienBaru(),(int)this.getCounterPasienLama(),(int)this.getCounterPasienEmergency());
        try {
                       Thread.sleep(this.slidervalue);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());;
                }
        this.setNumber(this.getNumber() + 1);
        return temp;
    }
    
    public int findServer(int batascounterserver){
        int server=-1;
        if(this.counterfindserver==0){
            for(int i=batascounterserver;i<this.server.length;i++){
                if(this.server[i].isStatus()==false&&this.server[i].getServerclock()<=this.clock&&this.server[i].getQueueSize()==0){
                    server=i;
                    i=this.server.length;
                }
            }
            if(server==-1){
              for(int i=0;i<batascounterserver;i++){
                   if(this.server[i].isStatus()==false&&this.server[i].getServerclock()<=this.clock&&this.server[i].getQueueSize()==0){
                        server=i;
                        i=this.server.length;
                    }
                }
            }
            if(server==-1){
                server=getSmallestServerClock(this.server);
            }
            this.counterfindserver++;
        }
        else{
            for(int i=batascounterserver+1;i<this.server.length;i++){
               if(this.server[i].isStatus()==false&&this.server[i].getServerclock()<=this.clock&&this.server[i].getQueueSize()==0){
                    server=i;
                    i=this.server.length;
                }
            }
            if(server==-1){
              for(int i=0;i<batascounterserver;i++){
                  if(this.server[i].isStatus()==false&&this.server[i].getServerclock()<=this.clock&&this.server[i].getQueueSize()==0){
                        server=i;
                        i=this.server.length;
                    }
                }
            }
            if(server==-1){
                server=getSmallestServerClock(this.server);
            }
            this.counterfindserver++;
        }
        return server;
    }
    
    public int getSmallestServerClock(ServerAwal[] server){
        int i=0;
        double serverclock=server[i].getServerclock();
        for(int k=1;k<server.length;k++){
            if(serverclock>server[k].getServerclock()){
                serverclock=server[k].getServerclock();
                i=k;
            }
        }
        return i;
    }
    
    public void runSimulation(){
        int i=0;
        int batascounterserver=0;
        int counterpasien=getNumOfCustomer();
        while(i<getNumOfCustomer()){
                Customer temp=new Customer();
                if((getNumOfCustomer()%this.kapasitasantrian)==0){
                    int server=this.findServer(batascounterserver);
                    batascounterserver=server;
                    this.server[server].setStatus(true);
                    int l=0;
                    while(l<this.kapasitasantrian){
                        if(this.getCounterPasienLama()>0||this.getCounterPasienBaru()>0||this.getCounterPasienEmergency()>0){
                            temp=this.processArrival();
                            if(temp.getJenis().equals("Emergency")){
                                 int random=rand.nextInt(2)+1;
                                 if(random==1){
                                     this.addToPoliQueue(temp);
                                 }
                            }
                            else{
                                this.server[server].addCustomertoQueue(temp);
                                l++;
                            }
                            if((i+l)>=numOfCustomer){
                                l=kapasitasantrian;
                            }
                        }
                        else{
                            l=kapasitasantrian;
                        }
                    }
                    this.server[server].setStatus(false);
                    if((i+l)>=numOfCustomer){
                                i=numOfCustomer;
                    }
                    else{
                        i+=l;
                    }
                }
                else{
                    int sisa=getNumOfCustomer()%this.kapasitasantrian;
                    int server=this.findServer(batascounterserver);
                    batascounterserver=server;
                    this.server[server].setStatus(true);
                    if(i==(getNumOfCustomer()-(sisa))){
                            int l=0;
                            while(l<sisa){
                               if(this.getCounterPasienLama()>0||this.getCounterPasienBaru()>0||this.getCounterPasienEmergency()>0){
                                    temp=this.processArrival();
                                    if(temp.getJenis().equals("Emergency")){
                                         int random=rand.nextInt(2)+1;
                                         if(random==1){
                                             this.addToPoliQueue(temp);
                                         }
                                    }
                                    else{
                                        this.server[server].addCustomertoQueue(temp);
                                        l++;
                                    }
                                    if((i+l)>=numOfCustomer){
                                        l=sisa;
                                    }
                               }
                               else{
                                   l=sisa;
                               }
                            }
                        this.server[server].setStatus(false);
                        if((i+l)>=numOfCustomer){
                                i=numOfCustomer;
                        }
                        else{
                            i+=l;
                        }
                    }
                    else{
                        this.server[server].setStatus(true);
                        int l=0;
                        while(l<this.kapasitasantrian){
                               if(this.getCounterPasienLama()>0||this.getCounterPasienBaru()>0||this.getCounterPasienEmergency()>0){  
                                    temp=this.processArrival();
                                    if(temp.getJenis().equals("Emergency")){
                                         int random=rand.nextInt(2)+1;
                                         if(random==1){
                                             this.addToPoliQueue(temp);
                                         }
                                    }
                                    else{
                                        this.server[server].addCustomertoQueue(temp);
                                        l++;
                                    }
                                    if((i+l)>=numOfCustomer){
                                        l=kapasitasantrian;
                                    }
                               }
                               else{
                                   l=kapasitasantrian;
                               }
                        }
                        this.server[server].setStatus(false);
                        if((i+l)>=numOfCustomer){
                                i=numOfCustomer;
                        }
                        else{
                            i+=l;
                        }
                    }
                    
                }
        }
        int o=0;
        while(o<20000){
            o++;
            System.out.println("do loading");
            MainGUI.showLoading();
        }
        MainGUI.hideLoading();
        displayChart1();
        int k=20000;
        int l=0;
        while(l<k){
            l++;
            System.out.println("do loading");
            MainGUI.showLoadingPoli();
        }
        MainGUI.hideLoadingPoli();
        displayChart2();
        MainGUI.enableResetButton();     
    }
    
    public void displayChart1(){
                    LinkedList<double[]> listcounter2=this.gen.getWaitingTimeServer(server);
                    LinkedList<int[]> listcounter=this.gen.getPasienCounter(this.server);
                    LinkedList<int[]> listdelay=this.gen.getPasienDelayTime(server);
                    LinkedList<double[]> listcounter3=this.gen.getServiceTimeServer(server);
                    LinkedList<int[]> listcounter4=this.gen.getCounterPasienperServer(this.server,this.paramxchart);
                    double[][] utility=this.gen.generateUtilityServerforChart(server);
                    int maxtime=this.gen.getMaxPasien(server);
                    int maxpasien=this.gen.getMaxJumlahPasienperServer(listcounter4);
                    MainGUI.setChart(paramxchart,(int)this.counterPasienBPJSBaru2,(int)this.counterPasienBPJSLama2,(int)this.counterPasienEmergency3,listcounter,utility,this.server.length,listdelay,listcounter2,listcounter3,listcounter4,maxtime,maxpasien);             
    }
    
    public void displayChart2(){
        MainGUI.hideLoading();
        LinkedList<int[]> listdelay=this.gen.getPasienDelayTimePetugas(serverpetugas);
        LinkedList<int[]> listdelay2=this.gen.getPasienDelayTimePerawat(serverperawat);
        LinkedList<int[]> listdelay3=this.gen.getPasienDelayTimeDokter(serverdokter);
        LinkedList<int[]> counterpetugas=this.gen.getCounterPasienPetugas(serverpetugas);
        LinkedList<int[]> counterperawat=this.gen.getCounterPasienPerawat(serverperawat);
        LinkedList<int[]> counterdokter=this.gen.getCounterPasienDokter(serverdokter);
        LinkedList<int[]>waitingtime1=this.gen.getWaitingTimeServerPetugas(serverpetugas);
        LinkedList<int[]>waitingtime2=this.gen.getWaitingTimeServerPerawat(serverperawat);
        LinkedList<int[]>waitingtime3=this.gen.getWaitingTimeServerDokter(serverdokter);
        LinkedList<int[]>service1=this.gen.getServiceTimeServerPetugas(serverpetugas);
        LinkedList<int[]>service2=this.gen.getServiceTimeServerPerawat(serverperawat);
        LinkedList<int[]>service3=this.gen.getServiceTimeServerDokter(serverdokter);
        MainGUI.setChartPoli(this.poli.getCounterpasien1(),this.poli.getCounterpasien2(),this.poli.getCounterpasien3(),counterpetugas,counterperawat,counterdokter);
        double[][] utility1=this.gen.generateUtilityServer2forChart(serverpetugas);
        double[][] utility2=this.gen.generateUtilityServer3forChart(serverperawat);
        double[][] utility3=this.gen.generateUtilityServer4forChart(serverdokter);
        LinkedList<int[]> listcounter4=this.gen.getCounterPasienperServer2(serverpetugas,paramxchart2);
        LinkedList<int[]> listcounter5=this.gen.getCounterPasienperServer3(serverperawat,paramxchart2);
        LinkedList<int[]> listcounter6=this.gen.getCounterPasienperServer4(serverdokter,paramxchart2);
        int maxtime=this.gen.getMaxPasien2(serverpetugas);
        int maxpasien=this.gen.getMaxJumlahPasienperServer(listcounter4);
        int maxtime2=this.gen.getMaxPasien3(serverperawat);
        int maxpasien2=this.gen.getMaxJumlahPasienperServer(listcounter5);
        int maxtime3=this.gen.getMaxPasien4(serverdokter);
        int maxpasien3=this.gen.getMaxJumlahPasienperServer(listcounter6);
        MainGUI.setChartPoli2(utility1,utility2,utility3,this.serverpetugas.length,this.serverperawat.length,this.serverdokter.length,listdelay,listdelay2,listdelay3);
        MainGUI.setChartPoli3(waitingtime1,waitingtime2,waitingtime3,service1,service2,service3);
        MainGUI.setChartPoli4(paramxchart2,listcounter4,maxtime,maxpasien);
        MainGUI.setChartPoli5(paramxchart2,listcounter5,maxtime2,maxpasien2);
        MainGUI.setChartPoli6(paramxchart2,listcounter6,maxtime3,maxpasien3);
    }
    
    public void displayChart3(){
        LinkedList<int[]> listcounter4=this.gen.getCounterPasienperServer2(serverpetugas,paramxchart2);
        LinkedList<int[]> listcounter5=this.gen.getCounterPasienperServer3(serverperawat,paramxchart2);
        LinkedList<int[]> listcounter6=this.gen.getCounterPasienperServer4(serverdokter,paramxchart2);
        int maxtime=this.gen.getMaxPasien2(serverpetugas);
        int maxpasien=this.gen.getMaxJumlahPasienperServer(listcounter4);
        int maxtime2=this.gen.getMaxPasien3(serverperawat);
        int maxpasien2=this.gen.getMaxJumlahPasienperServer(listcounter5);
        int maxtime3=this.gen.getMaxPasien4(serverdokter);
        int maxpasien3=this.gen.getMaxJumlahPasienperServer(listcounter6);
        MainGUI.setChartPoli42(paramxchart2,listcounter4,maxtime,maxpasien);
        MainGUI.setChartPoli52(paramxchart2,listcounter5,maxtime2,maxpasien2);
        MainGUI.setChartPoli62(paramxchart2,listcounter6,maxtime3,maxpasien3);
        
    }
    
    public void displayChart4(){
         LinkedList<int[]> listcounter4=this.gen.getCounterPasienperServer(this.server,this.paramxchart);
         int maxtime=this.gen.getMaxPasien(server);
         int maxpasien=this.gen.getMaxJumlahPasienperServer(listcounter4);
         MainGUI.setChartAwal(this.paramxchart,listcounter4,maxtime,maxpasien);
         
     }
    
    private synchronized void addToPoliQueue(Customer temp){
        this.poli.addCustomer(temp);
        notifyAll();
    }
    //@Override
    public void paint(Graphics g){
        
    }

    //@Override
    public void run() {
                 this.runSimulation();
    }
     
    
    /**
     * @return the counterPasienLama
     */
    public double getCounterPasienLama() {
        return counterPasienLama;
    }

    /**
     * @param counterPasienLama the counterPasienLama to set
     */
    public void setCounterPasienLama(double counterPasienLama) {
        this.counterPasienLama = counterPasienLama;
    }

    /**
     * @return the counterPasienBaru
     */
    public double getCounterPasienBaru() {
        return counterPasienBaru;
    }

    /**
     * @param counterPasienBaru the counterPasienBaru to set
     */
    public void setCounterPasienBaru(double counterPasienBaru) {
        this.counterPasienBaru = counterPasienBaru;
    }

    /**
     * @return the counterPasienEmergency
     */
    public double getCounterPasienEmergency() {
        return counterPasienEmergency;
    }

    /**
     * @param counterPasienEmergency the counterPasienEmergency to set
     */
    public void setCounterPasienEmergency(double counterPasienEmergency) {
        this.counterPasienEmergency = counterPasienEmergency;
    }

    /**
     * @return the number
     */
    public int getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * @return the slidervalue
     */
    public int getSlidervalue() {
        return slidervalue;
    }

    /**
     * @param slidervalue the slidervalue to set
     */
    public void setSlidervalue(int slidervalue) {
        this.slidervalue = slidervalue;
    }

    /**
     * @return the poli
     */
    public StatisticsSimulationPoli getPoli() {
        return poli;
    }

    /**
     * @param poli the poli to set
     */
    public void setPoli(StatisticsSimulationPoli poli) {
        this.poli = poli;
    }

    /**
     * @return the counterPasienBPJSLama
     */
    public double getCounterPasienBPJSLama() {
        return counterPasienBPJSLama;
    }

    /**
     * @param counterPasienBPJSLama the counterPasienBPJSLama to set
     */
    public void setCounterPasienBPJSLama(double counterPasienBPJSLama) {
        this.counterPasienBPJSLama = counterPasienBPJSLama;
    }

    /**
     * @return the counterPasienBPJSBaru
     */
    public double getCounterPasienBPJSBaru() {
        return counterPasienBPJSBaru;
    }

    /**
     * @param counterPasienBPJSBaru the counterPasienBPJSBaru to set
     */
    public void setCounterPasienBPJSBaru(double counterPasienBPJSBaru) {
        this.counterPasienBPJSBaru = counterPasienBPJSBaru;
    }

    /**
     * @return the counterPasienEmergency2
     */
    public double getCounterPasienEmergency2() {
        return counterPasienEmergency2;
    }

    /**
     * @param counterPasienEmergency2 the counterPasienEmergency2 to set
     */
    public void setCounterPasienEmergency2(double counterPasienEmergency2) {
        this.counterPasienEmergency2 = counterPasienEmergency2;
    }

    /**
     * @return the numOfCustomer
     */
    public int getNumOfCustomer() {
        return numOfCustomer;
    }

    /**
     * @param numOfCustomer the numOfCustomer to set
     */
    public void setNumOfCustomer(int numOfCustomer) {
        this.numOfCustomer = numOfCustomer;
    }

    /**
     * @return the counterPasienBPJSLama2
     */
    public double getCounterPasienBPJSLama2() {
        return counterPasienBPJSLama2;
    }

    /**
     * @return the counterPasienBPJSBaru2
     */
    public double getCounterPasienBPJSBaru2() {
        return counterPasienBPJSBaru2;
    }

    /**
     * @return the counterPasienEmergency3
     */
    public double getCounterPasienEmergency3() {
        return counterPasienEmergency3;
    }

    /**
     * @return the paramxchart
     */
    public int getParamxchart() {
        return paramxchart;
    }

    /**
     * @param paramxchart the paramxchart to set
     */
    public void setParamxchart(int paramxchart) {
        this.paramxchart = paramxchart;
    }

    /**
     * @return the paramxchart2
     */
    public int getParamxchart2() {
        return paramxchart2;
    }

    /**
     * @param paramxchart2 the paramxchart2 to set
     */
    public void setParamxchart2(int paramxchart2) {
        this.paramxchart2 = paramxchart2;
    }
}