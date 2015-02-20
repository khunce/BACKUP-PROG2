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
     
 public StatisticsSimulationAwal(int numOfCustomer,ServerAwal[] server,StatisticsGenerator gen,double ratio2,InterfaceGUI1 MainGUI,int kapasitasantrian){
        super();
        this.MainGUI=MainGUI;
        System.out.println("initialized");
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
        this.animator=new Thread(this);
    }
 
    public StatisticsSimulationAwal(){
        
    }
 
    public Customer createPatientBPJSBaru(){
       Customer c=new Customer();
       c.setJenis("BPJS Baru");
       c.setPriority(1);
       c.setNumber(this.getNumber());
       this.setCounterPasienBaru(this.getCounterPasienBaru() - 1);
       return c;
    }
    
    public Customer createPatientBPJSLama(){
       Customer c=new Customer();
       c.setJenis("BPJS Lama");
       c.setPriority(1);
       c.setNumber(this.getNumber());
       this.setCounterPasienLama(this.getCounterPasienLama() - 1);
       return c;
    }
    
    public Customer createEmergencyPatient(){
       Customer c=new Customer();
       c.setJenis("Emergency");
       c.setPriority(2);
       c.setNumber(this.getNumber());
       this.setCounterPasienEmergency(this.getCounterPasienEmergency() - 1);
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
        //System.out.print(r);
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
        temp.setNumber(this.getNumber());
        customerqueue.add(temp);
        System.out.println(temp.getNumber()+" "+temp.getJenis()+" "+clock+" "+arrivaltime);
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
    
     public Customer processArrival2(){
        Customer temp=this.createRandomPatientnotEmergency();
        double arrivaltime=gen.generateArrivalTime();
        clock=clock+arrivaltime;
        temp.setArrivaltime(clock);
        temp.setNumber(this.getNumber());
        customerqueue.add(temp);
        System.out.println(temp.getNumber()+" "+temp.getJenis()+" "+clock+" "+arrivaltime);
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
             //server=-1;
            System.out.println("Counter server : "+this.counterfindserver);
            for(int i=batascounterserver;i<this.server.length;i++){
                System.out.println("cari server 1");
                //System.out.println("Clock : "+this.clock+" Status :  "+this.server[i].isStatus()+" No :  "+i+" Server clock :  "+this.server[i].getServerclock()+" queue size : "+this.server[i].getQueueSize());
                if(this.server[i].isStatus()==false&&this.server[i].getServerclock()<=this.clock&&this.server[i].getQueueSize()==0){
                    server=i;
                    System.out.println("Clock : "+this.clock+" Status :  "+this.server[i].isStatus()+" No :  "+i+" Server clock :  "+this.server[i].getServerclock()+" queue size : "+this.server[i].getQueueSize());
                    i=this.server.length;
                }
            }
            System.out.println("server  berapa ? "+server);
            if(server==-1){
              for(int i=0;i<batascounterserver;i++){
                   System.out.println("cari server 2");
                   System.out.println("Clock : "+this.clock+" Status :  "+this.server[i].isStatus()+" No :  "+i+" Server clock :  "+this.server[i].getServerclock()+" queue size : "+this.server[i].getQueueSize());
                    if(this.server[i].isStatus()==false&&this.server[i].getServerclock()<=this.clock&&this.server[i].getQueueSize()==0){
                        server=i;
                        // System.out.println("Clock : "+this.clock+" Status :  "+this.server[i].isStatus()+" No :  "+i+" Server clock :  "+this.server[i].getServerclock()+" queue size : "+this.server[i].getQueueSize());
                        i=this.server.length;
                    }
                }
            }
            System.out.println("server  berapa ?? "+server);
            if(server==-1){
                System.out.println("cari server 3");
                server=getSmallestServerClock(this.server);
            }
            this.counterfindserver++;
        }
        else{
            System.out.println("Counter server : "+this.counterfindserver);
            for(int i=batascounterserver+1;i<this.server.length;i++){
                System.out.println("cari server 1");
                //System.out.println("Clock : "+this.clock+" Status :  "+this.server[i].isStatus()+" No :  "+i+" Server clock :  "+this.server[i].getServerclock()+" queue size : "+this.server[i].getQueueSize());
                if(this.server[i].isStatus()==false&&this.server[i].getServerclock()<=this.clock&&this.server[i].getQueueSize()==0){
                    server=i;
                    System.out.println("Clock : "+this.clock+" Status :  "+this.server[i].isStatus()+" No :  "+i+" Server clock :  "+this.server[i].getServerclock()+" queue size : "+this.server[i].getQueueSize());
                    i=this.server.length;
                }
            }
            System.out.println("server  berapa ? "+server);
            if(server==-1){
              for(int i=0;i<batascounterserver;i++){
                   System.out.println("cari server 2");
                   System.out.println("Clock : "+this.clock+" Status :  "+this.server[i].isStatus()+" No :  "+i+" Server clock :  "+this.server[i].getServerclock()+" queue size : "+this.server[i].getQueueSize());
                    if(this.server[i].isStatus()==false&&this.server[i].getServerclock()<=this.clock&&this.server[i].getQueueSize()==0){
                        server=i;
                        // System.out.println("Clock : "+this.clock+" Status :  "+this.server[i].isStatus()+" No :  "+i+" Server clock :  "+this.server[i].getServerclock()+" queue size : "+this.server[i].getQueueSize());
                        i=this.server.length;
                    }
                }
            }
            System.out.println("server  berapa ?? "+server);
            if(server==-1){
                System.out.println("cari server 3");
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
        while(i<getNumOfCustomer()){
                System.out.println("lll");
                Customer temp=new Customer();
                if((getNumOfCustomer()%this.kapasitasantrian)==0){
                    int server=this.findServer(batascounterserver);
                    batascounterserver=server;
                    System.out.println("Server ke- "+server);
                    this.server[server].setStatus(true);
                    int l=0;
                    while(l<this.kapasitasantrian){
                        temp=this.processArrival();
                        if(temp.getJenis().equals("Emergency")){
                             int random=rand.nextInt(2)+1;
                             if(random==1){
                                 this.addToPoliQueue(temp);
                             }
                             temp=this.processArrival2();
                             this.server[server].addCustomertoQueue(temp);
                             l+=2;
                        }
                        else{
                            this.server[server].addCustomertoQueue(temp);
                            l++;
                        }
                        
                    }
                    this.server[server].setStatus(false);
                    i+=l;
                }
                else{
                    int sisa=getNumOfCustomer()%this.kapasitasantrian;
                    int server=this.findServer(batascounterserver);
                    batascounterserver=server;
                    System.out.println("Server ke- "+server);
                    this.server[server].setStatus(true);
                    if(i==(getNumOfCustomer()-sisa)){
                            int l=0;
                            while(l<sisa){
                                temp=this.processArrival();
                                if(temp.getJenis().equals("Emergency")){
                                     int random=rand.nextInt(2)+1;
                                     if(random==1){
                                         this.addToPoliQueue(temp);
                                     }
                                     temp=this.processArrival2();
                                     this.server[server].addCustomertoQueue(temp);
                                     l+=2;
                                }
                                else{
                                    this.server[server].addCustomertoQueue(temp);
                                    l++;
                                }
                            }
                        this.server[server].setStatus(false);
                        i+=l;
                    }
                    else{
                        System.out.println("Server ke- "+server);
                        this.server[server].setStatus(true);
                        int l=0;
                        while(l<this.kapasitasantrian){
                            temp=this.processArrival();
                            if(temp.getJenis().equals("Emergency")){
                                 int random=rand.nextInt(2)+1;
                                 if(random==1){
                                     this.addToPoliQueue(temp);
                                 }
                                    temp=this.processArrival2();
                                    this.server[server].addCustomertoQueue(temp);
                                    l+=2;
                            }
                            else{
                                this.server[server].addCustomertoQueue(temp);
                                l++;
                            }

                        }
                        this.server[server].setStatus(false);
                        i+=l;
                    }
                    
                }
                if(i==getNumOfCustomer()-1||i==getNumOfCustomer()){
                    MainGUI.enableResetButton();
                }
        }
        
        
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
                 System.out.println("over"+number);
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
}