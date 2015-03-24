/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExcelInputSimulation;

import GUI.InterfaceGUI2;
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
     private InterfaceGUI2 MainGUI;
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
     private ServerPetugas[] serverpetugas;
     private ServerPerawat[] serverperawat;
     private ServerDokter[] serverdokter;
     private String cekInput;
     private int paramxchart;
     private int paramxchart2;
     
 public StatisticsSimulationAwal(ExcelReader excel,ServerAwal[] server,StatisticsGenerator gen,InterfaceGUI2 MainGUI,int kapasitasantrian){
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
        this.paramxchart=5;
        this.paramxchart2=5;
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
        if(temp.getJenis().equals("Emergency")){
            temp.setArrivaltimepoli(temp.getArrivaltime());
            temp.setNumber(0);
        }
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
                        if(this.getCounterPasienBPJSLama()>0||this.getCounterPasienBPJSBaru()>0||this.getCounterPasienEmergency()>0){
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
                    System.out.println("Server ke- "+server);
                    this.server[server].setStatus(true);
                    if(i==(getNumOfCustomer()-sisa)){
                            int l=0;
                            while(l<sisa){
                                if(this.getCounterPasienBPJSLama()>0||this.getCounterPasienBPJSBaru()>0||this.getCounterPasienEmergency()>0){
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
                        System.out.println("Server ke- "+server);
                        this.server[server].setStatus(true);
                        int l=0;
                        while(l<this.kapasitasantrian){
                            if(this.getCounterPasienBPJSLama()>0||this.getCounterPasienBPJSBaru()>0||this.getCounterPasienEmergency()>0){
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
        while(o<30000){
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
        if(cekInput.equals("acak")){
            MainGUI.enableResetButton();
        }
        else{
            MainGUI.enableResetButton2();
        }
        
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
                    MainGUI.setChart(paramxchart,(int)this.counterPasienBaru,(int)this.counterPasienLama,(int)this.counterPasienEmergency2,listcounter,utility,this.server.length,listdelay,listcounter2,listcounter3,listcounter4,maxtime,maxpasien);
                    
    }
     
     public void displayChart4(){
         LinkedList<int[]> listcounter4=this.gen.getCounterPasienperServer(this.server,this.paramxchart);
         int maxtime=this.gen.getMaxPasien(server);
         int maxpasien=this.gen.getMaxJumlahPasienperServer(listcounter4);
         MainGUI.setChartAwal(this.paramxchart,listcounter4,maxtime,maxpasien);
         
     }
    
    public void displayChart2(){
        LinkedList<int[]> listdelay=this.gen.getPasienDelayTimePetugas(getServerpetugas());
        LinkedList<int[]> listdelay2=this.gen.getPasienDelayTimePerawat(getServerperawat());
        LinkedList<int[]> listdelay3=this.gen.getPasienDelayTimeDokter(getServerdokter());
        LinkedList<int[]> counterpetugas=this.gen.getCounterPasienPetugas(getServerpetugas());
        LinkedList<int[]> counterperawat=this.gen.getCounterPasienPerawat(getServerperawat());
        LinkedList<int[]> counterdokter=this.gen.getCounterPasienDokter(getServerdokter());
        LinkedList<int[]>waitingtime1=this.gen.getWaitingTimeServerPetugas(getServerpetugas());
        LinkedList<int[]>waitingtime2=this.gen.getWaitingTimeServerPerawat(getServerperawat());
        LinkedList<int[]>waitingtime3=this.gen.getWaitingTimeServerDokter(getServerdokter());
        LinkedList<int[]>service1=this.gen.getServiceTimeServerPetugas(getServerpetugas());
        LinkedList<int[]>service2=this.gen.getServiceTimeServerPerawat(getServerperawat());
        LinkedList<int[]>service3=this.gen.getServiceTimeServerDokter(getServerdokter());
        MainGUI.setChartPoli(this.poli.getCounterpasien1(),this.poli.getCounterpasien2(),this.poli.getCounterpasien3(),counterpetugas,counterperawat,counterdokter);
        double[][] utility1=this.gen.generateUtilityServer2forChart(getServerpetugas());
        double[][] utility2=this.gen.generateUtilityServer3forChart(getServerperawat());
        double[][] utility3=this.gen.generateUtilityServer4forChart(getServerdokter());
        LinkedList<int[]> listcounter4=this.gen.getCounterPasienperServer2(getServerpetugas(),paramxchart2);
        LinkedList<int[]> listcounter5=this.gen.getCounterPasienperServer3(getServerperawat(),paramxchart2);
        LinkedList<int[]> listcounter6=this.gen.getCounterPasienperServer4(getServerdokter(),paramxchart2);
        int maxtime=this.gen.getMaxPasien2(getServerpetugas());
        int maxpasien=this.gen.getMaxJumlahPasienperServer(listcounter4);
        int maxtime2=this.gen.getMaxPasien3(getServerperawat());
        int maxpasien2=this.gen.getMaxJumlahPasienperServer(listcounter5);
        int maxtime3=this.gen.getMaxPasien4(getServerdokter());
        int maxpasien3=this.gen.getMaxJumlahPasienperServer(listcounter6);
        MainGUI.setChartPoli2(utility1,utility2,utility3,this.getServerpetugas().length,this.getServerperawat().length,this.getServerdokter().length,listdelay,listdelay2,listdelay3);
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

    /**
     * @return the serverpetugas
     */
    public ServerPetugas[] getServerpetugas() {
        return serverpetugas;
    }

    /**
     * @param serverpetugas the serverpetugas to set
     */
    public void setServerpetugas(ServerPetugas[] serverpetugas) {
        this.serverpetugas = serverpetugas;
    }

    /**
     * @return the serverperawat
     */
    public ServerPerawat[] getServerperawat() {
        return serverperawat;
    }

    /**
     * @param serverperawat the serverperawat to set
     */
    public void setServerperawat(ServerPerawat[] serverperawat) {
        this.serverperawat = serverperawat;
    }

    /**
     * @return the serverdokter
     */
    public ServerDokter[] getServerdokter() {
        return serverdokter;
    }

    /**
     * @param serverdokter the serverdokter to set
     */
    public void setServerdokter(ServerDokter[] serverdokter) {
        this.serverdokter = serverdokter;
    }

    /**
     * @return the cekInput
     */
    public String getCekInput() {
        return cekInput;
    }

    /**
     * @param cekInput the cekInput to set
     */
    public void setCekInput(String cekInput) {
        this.cekInput = cekInput;
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
