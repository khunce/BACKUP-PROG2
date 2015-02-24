/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExcelInputSimulation;

import GUI.InterfaceGUI4;
import SimulasiAntrianPasien.Customer;
import SimulasiAntrianPasien.CustomerQ;
import SimulasiAntrianPasien.StatisticsGenerator;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.Point2D;
import java.math.BigDecimal;
import java.util.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
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
     public LinkedList<Customer> customerqueue;
     private double counterPasienLama;
     private double counterPasienBaru;
     private double counterPasienEmergency;
     protected Thread animator;
     private InterfaceGUI4 MainGUI;
     private int number;
     private int kapasitasantrian;
     private int counterfindserver;
     private Random rand;
     private StatisticsSimulationPoli poli;
     private double counterPasienBPJSLama;
     private double counterPasienBPJSBaru;
     private double counterPasienEmergency2;
     private ExcelReader excel;
     private int i;
 public StatisticsSimulationAwal(ExcelReader excel,ServerAwal[] server,StatisticsGenerator gen,InterfaceGUI4 MainGUI,int kapasitasantrian){
        super();
        this.MainGUI=MainGUI;
        System.out.println("initialized");
        this.server=server;
        this.gen=gen;
        this.excel=excel;
        this.number=0;
        this.customerqueue=new LinkedList<Customer>();
        for(int i=0;i<this.excel.getQueueOfCustomer().size();i++){
            this.customerqueue.add(this.excel.getQueueOfCustomer().get(i));
        }
        this.counterPasienBPJSBaru=this.excel.getTotalbaru();
        this.counterPasienBPJSLama=this.excel.getTotallama();
        this.counterPasienEmergency=this.excel.getEmergency();
        this.numOfCustomer=this.excel.getQueueOfCustomer().size();
        this.counterPasienBaru=this.excel.getTotalbaru();
        this.counterPasienLama=this.excel.getTotallama();
        this.counterPasienEmergency2=this.excel.getEmergency();
        this.kapasitasantrian=kapasitasantrian;
        this.animator=new Thread(this);
        this.slidervalue=700;
        this.i=0;
    }
 
    public StatisticsSimulationAwal(){
        
    }
 
   
    
    public Customer processArrival(int counter){
        Customer temp=this.customerqueue.get(counter);
        if(temp.getJenis().equals("BPJS Lama")){
            this.counterPasienBPJSLama--;
        }
        else if (temp.getJenis().equals("BPJS Baru")){
            this.counterPasienBPJSBaru--;
        }
        else{
            this.counterPasienEmergency--;
        }
        double arrivaltime=temp.getArrivaltime();
        clock=arrivaltime;
        System.out.println(temp.getNumber()+" "+temp.getJenis()+" "+clock+" "+arrivaltime);
        String realtime=gen.convertSeconds(temp.getArrivaltime());
        MainGUI.setOutputValue(temp.getNumber()+" "+temp.getJenis()+" "+realtime);
        MainGUI.setOutputCounter((int)this.getCounterPasienBPJSBaru(),(int)this.getCounterPasienBPJSLama(),(int)this.getCounterPasienEmergency());
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
        int batascounterserver=0;
        int i=0;
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
                        temp=this.processArrival(i+l);
                        if(temp.getJenis().equals("Emergency")){
                             if(temp.isToPoliklinik()){
                                 this.addToPoliQueue(temp);
                             }
                        }
                        else {
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
                                temp=this.processArrival(i+l);
                                if(temp.getJenis().equals("Emergency")){
                                     if(temp.isToPoliklinik()){
                                         this.addToPoliQueue(temp);
                                     }
                                }
                                else {
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
                            temp=this.processArrival(i+l);
                            if(temp.getJenis().equals("Emergency")){
                                 if(temp.isToPoliklinik()){
                                     this.addToPoliQueue(temp);
                                 }
                            }
                            else {
                                this.server[server].addCustomertoQueue(temp);
                                l++;
                            }

                        }
                        this.server[server].setStatus(false);
                        i+=l;
                    }
                    
                }
                if(i==getNumOfCustomer()){
                    MainGUI.enableResetButton();
                    MainGUI.setChart((int)this.counterPasienBaru,(int)this.counterPasienLama,(int)this.counterPasienEmergency2);
                }
        }
         MainGUI.setChartPoli(this.poli.getCounterpasien1(),this.poli.getCounterpasien2(),this.poli.getCounterpasien3()); 
    }
    
     public void setPoli(StatisticsSimulationPoli poli) {
        this.poli = poli;
    }
    
    private synchronized void addToPoliQueue(Customer temp){
        this.getPoli().addCustomer(temp);
        notifyAll();
    }

    @Override
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
     * @return the excel
     */
    public ExcelReader getExcel() {
        return excel;
    }

    /**
     * @param excel the excel to set
     */
    public void setExcel(ExcelReader excel) {
        this.excel = excel;
    }

    /**
     * @return the poli
     */
    public StatisticsSimulationPoli getPoli() {
        return poli;
    }
}
