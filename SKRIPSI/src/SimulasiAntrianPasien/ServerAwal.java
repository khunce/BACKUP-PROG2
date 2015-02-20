/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SimulasiAntrianPasien;

import GUI.InterfaceGUI1;
import java.awt.geom.Point2D;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.ArrayDeque;

/**
 *
 * @author robby
 */
public class ServerAwal extends Thread implements Server{
    private int servernumber;
    private boolean status;
    private double timeServiceBegin;
    private double timeServiceEnd;
    private double totalservicetime;
    private double totalWaitingTime;
    private double totalDelayTime;
    private int counterCustomer;
    private Queue<Customer> serverqueue;
    private String nama;
    private StatisticsGenerator stat;
    private double serverclock;
    private String write;
    private InterfaceGUI1 gui;
    private StatisticsSimulationPoli sim;
    private int i;
    private int slidervalue;
    private Queue<Customer> queuereport;
    private LinkedList<Customer> queuereport2;
    private int counter;
   
    public ServerAwal(int servernumber, StatisticsGenerator stat,InterfaceGUI1 gui ){
        super();
        this.stat=stat;
        this.timeServiceBegin=timeServiceBegin;
        this.timeServiceEnd=timeServiceEnd;
        this.serverqueue=new LinkedList();
        this.servernumber=servernumber;
        this.status=false;
        this.serverclock=0;
        this.write=new String();
        this.gui=gui;
        this.sim=sim;
        i=0;
        this.queuereport=new LinkedList(); 
        this.slidervalue=700;
        this.queuereport2=new LinkedList<Customer>();
        this.counterCustomer=0;
        this.counter=0;
    }
    
    ServerAwal(){
        super();
        this.serverqueue=new LinkedList();
        this.counterCustomer=0;
        this.sim=sim;
        this.queuereport=new LinkedList(); 
        this.queuereport2=new LinkedList<Customer>();
    }
 
    
    @Override
    public void run(){
        while(true){
            doServeCustomer();
            try {
                       Thread.sleep(this.getSlidervalue());
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());;
                }
        }
        
    }
    
    public int getQueueSize(){
        return this.serverqueue.size();
    }
    
    @Override
    public synchronized void doServeCustomer(){
        while(this.serverqueue.size()==0){
            try{
                    wait();
                }
            catch (InterruptedException ex) {
                    System.out.println("wait error " + ex.getMessage());
                }
            
        }
        while(this.serverqueue.size()>0){
                    System.out.println("yyy");
                    //this.setStatus(true);
                    Random rand=new Random();
                    Customer temp=(Customer)this.removeCustomerfromQueue();
                    double servicetime=0;
                    double waitingtime=0;
                    double delaytime=0;
                    System.out.println("i : "+i);
                    if(temp.getJenis().equals("BPJS Lama")){
                        if(getServerclock()<temp.getArrivaltime()){
                            setServerclock(temp.getArrivaltime());
                            temp.setTimeServiceBegin(getServerclock());
                            if(i==0){
                                this.setTimeServiceBegin(getServerclock());
                            }
                            servicetime=stat.generateServiceTime2();
                            System.out.println(servicetime +" BPJS-L-1");
                            delaytime=(Math.abs(temp.getArrivaltime()-(getServerclock())));
                            temp.setDelaytime(delaytime);
                            setServerclock(getServerclock() + servicetime);
                            waitingtime=delaytime+servicetime;
                            temp.setWaitingtime(waitingtime);
                            temp.setServicetime(servicetime);
                            totalservicetime+=servicetime;
                            totalWaitingTime+=waitingtime;
                            totalDelayTime+=delaytime;
                            temp.setArrivaltimepoli(getServerclock());
                            temp.setTimeServiceEnd(getServerclock());
                            double cek=getSim().getCounterPasien();
                            int random=rand.nextInt(10)+1;
                            this.setTimeServiceEnd(getServerclock());
                            temp.setServerawal(this.servernumber);
                            this.addToReportQueue(temp);
                            System.out.println("cek form "+temp.getNumber()+" "+temp.getArrivaltime()+" "+temp.getServicetime());
                            if(random==2){
                                if(cek!=0){
                                    addToPoliQueue(temp);
                                }
                            }
                            System.out.println("cek "+cek+" random "+random);
                        }
                        else if(getServerclock()>=temp.getArrivaltime()){
                            servicetime=stat.generateServiceTime2();
                            temp.setTimeServiceBegin(getServerclock());
                            if(i==0){
                                this.setTimeServiceBegin(getServerclock());
                            }
                            System.out.println(servicetime +" BPJS-L-2");
                            delaytime=(Math.abs(temp.getArrivaltime()-(getServerclock())));
                            temp.setDelaytime(delaytime);
                            setServerclock(getServerclock() + servicetime);
                            waitingtime=delaytime+servicetime;
                            temp.setWaitingtime(waitingtime);
                            temp.setServicetime(servicetime);
                            totalservicetime+=servicetime;
                            totalWaitingTime+=waitingtime;
                            totalDelayTime+=delaytime;
                            temp.setArrivaltimepoli(getServerclock());
                            temp.setTimeServiceEnd(getServerclock());
                            this.setTimeServiceEnd(getServerclock());
                            double cek=getSim().getCounterPasien();
                            temp.setServerawal(this.servernumber);
                            this.addToReportQueue(temp);
                            System.out.println("cek form "+temp.getNumber()+" "+temp.getArrivaltime()+" "+temp.getServicetime());
                            int random=rand.nextInt(10)+1;
                            if(random==2){
                                if(cek!=0){
                                    addToPoliQueue(temp);
                                }
                            }
                            System.out.println("cek "+cek+" random "+random);
                           
                        }
                    }
                    else if(temp.getJenis().equals("BPJS Baru")){
                            if(getServerclock()<temp.getArrivaltime()){
                                setServerclock(temp.getArrivaltime());
                                temp.setTimeServiceBegin(getServerclock());
                                if(i==0){
                                    this.setTimeServiceBegin(getServerclock());
                                }
                                System.out.println(servicetime +" BPJS-B-1");
                                servicetime=stat.generateServiceTime();
                                delaytime=(Math.abs(temp.getArrivaltime()-(getServerclock())));
                                temp.setDelaytime(delaytime);
                                setServerclock(getServerclock() + servicetime);
                                waitingtime=delaytime+servicetime;
                                temp.setWaitingtime(waitingtime);
                                temp.setServicetime(servicetime);
                                totalservicetime+=servicetime;
                                totalWaitingTime+=waitingtime;
                                totalDelayTime+=delaytime;
                                temp.setArrivaltimepoli(getServerclock());
                                temp.setTimeServiceEnd(getServerclock());
                                double cek=getSim().getCounterPasien();
                                this.setTimeServiceEnd(getServerclock());
                                temp.setServerawal(this.servernumber);
                                this.addToReportQueue(temp);
                                System.out.println("cek form "+temp.getNumber()+" "+temp.getArrivaltime()+" "+temp.getServicetime());
                                int random=rand.nextInt(10)+1;
                                if(random==2){
                                    if(cek!=0){
                                        addToPoliQueue(temp);
                                    }
                                }
                                System.out.println("cek "+cek+" random "+random);
                            }
                            else if(getServerclock()>=temp.getArrivaltime()){
                                servicetime=stat.generateServiceTime();
                                temp.setTimeServiceBegin(getServerclock());
                                if(i==0){
                                    this.setTimeServiceBegin(getServerclock());
                                }
                                System.out.println(servicetime +" BPJS-B-2");
                                delaytime=(Math.abs(temp.getArrivaltime()-(getServerclock())));
                                temp.setDelaytime(delaytime);
                                setServerclock(getServerclock() + servicetime);
                                waitingtime=delaytime+servicetime;
                                temp.setWaitingtime(waitingtime);
                                temp.setServicetime(servicetime);
                                totalservicetime+=servicetime;
                                totalWaitingTime+=waitingtime;
                                totalDelayTime+=delaytime;
                                temp.setArrivaltimepoli(getServerclock());
                                temp.setTimeServiceEnd(getServerclock());
                                double cek=getSim().getCounterPasien();
                                this.setTimeServiceEnd(getServerclock());
                                temp.setServerawal(this.servernumber);
                                System.out.println("cek form "+temp.getNumber()+" "+temp.getArrivaltime()+" "+temp.getServicetime());
                                this.addToReportQueue(temp);
                                int random=rand.nextInt(10)+1;
                                if(random==2){
                                    if(cek!=0){
                                        addToPoliQueue(temp);
                                    }
                                }
                                System.out.println("cek "+cek+" random "+random);
                            }
                    }
                    else if(temp.getJenis().equals("Emergency")){
                                temp.setDelaytime(delaytime);
                                temp.setWaitingtime(waitingtime);
                                temp.setServicetime(servicetime);
                                temp.setServerawal(this.servernumber);
                                System.out.println("kasus emergency server awal : "+temp.getArrivaltime());
                                temp.setArrivaltimepoli(temp.getArrivaltime());
                                temp.setArrivaltimepoli3(temp.getArrivaltime());
                                this.addToReportQueue(temp);
                                System.out.println("cek form "+temp.getNumber()+" "+temp.getArrivaltime()+" "+temp.getServicetime());
                                double cek=getSim().getCounterPasien();
                                int random=rand.nextInt(10)+1;
                                if(random==2){
                                    if(cek!=0){
                                        addToPoliQueue(temp);
                                    }
                                }
                                System.out.println("cek "+cek+" random "+random);
                    }
                    i++;
                    counter++;
                    BigDecimal bd = new BigDecimal(this.getServerclock()); 
                    bd = bd.setScale(2,BigDecimal.ROUND_UP); 
                    this.setServerclock(bd.doubleValue());
                    String realtime=stat.convertSeconds(servicetime);
                    String realtime2=stat.convertSeconds(this.getServerclock());
                    gui.setOutputValue3("Pasien nomot urut "+temp.getNumber()+" -"+"Server Clock : "+realtime2+"-"+"Service Time : "+realtime,this.servernumber);
                        try {
                             Thread.sleep(this.slidervalue);
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());;
                        }
           }
        notifyAll();
    }
    
    public synchronized void addToReportQueue(Customer temp){
        System.out.println("counter report queue");
        queuereport.add(temp);
        queuereport2.add(temp);
        notifyAll();
    }
    
    public synchronized void addToPoliQueue(Customer temp){
        this.sim.addCustomer(temp);
        notifyAll();
    }
    
    public LinkedList<Customer> getQueueReport2(){
        return this.queuereport2;
    }
    
    public Queue<Customer> getQueueReport(){
        return this.queuereport;
    }
    
    public void setStatistics(String write){
        this.write=write;
    }
    
    public String getStatistics(){
        return this.write;
    }
    
    public  synchronized void addCustomertoQueue(Customer cust){
        this.serverqueue.add(cust);
        this.setCounterCustomer(this.getCounterCustomer() + 1);
        notifyAll();
    }
    
    public synchronized Customer removeCustomerfromQueue(){
         while(this.serverqueue.size()==0){
            try{
                wait();
            }
            catch (InterruptedException ex) {
                System.out.println("wait error " + ex.getMessage());
            }
        }
        notifyAll();
        Customer temp=(Customer)this.serverqueue.poll();
        this.setCounterCustomer(this.getCounterCustomer() - 1);
        return temp;
    }
    /**
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * @return the timeServiceBegin
     */
    public double getTimeServiceBegin() {
        return timeServiceBegin;
    }

    /**
     * @param timeServiceBegin the timeServiceBegin to set
     */
    public void setTimeServiceBegin(double timeServiceBegin) {
        this.timeServiceBegin = timeServiceBegin;
    }

    /**
     * @return the timeServiceEnd
     */
    public double getTimeServiceEnd() {
        return timeServiceEnd;
    }

    /**
     * @param timeServiceEnd the timeServiceEnd to set
     */
    public void setTimeServiceEnd(double timeServiceEnd) {
        this.timeServiceEnd = timeServiceEnd;
    }

    /**
     * @return the servernumber
     */
    public int getServernumber() {
        return servernumber;
    }

    /**
     * @param servernumber the servernumber to set
     */
    public void setServernumber(int servernumber) {
        this.servernumber = servernumber;
    }

    /**
     * @return the counter
     */
    public int getCounter() {
        return this.counter;
    }

    /**
     * @return the totalservicetime
     */
    public double getTotalservicetime() {
        return totalservicetime;
    }

    /**
     * @param totalservicetime the totalservicetime to set
     */
    public void setTotalservicetime(double totalservicetime) {
        this.totalservicetime = totalservicetime;
    }

    /**
     * @return the totalWaitingTime
     */
    public double getTotalWaitingTime() {
        return totalWaitingTime;
    }

    /**
     * @param totalWaitingTime the totalWaitingTime to set
     */
    public void setTotalWaitingTime(double totalWaitingTime) {
        this.totalWaitingTime = totalWaitingTime;
    }

    /**
     * @return the totalDelayTime
     */
    public double getTotalDelayTime() {
        return totalDelayTime;
    }

    /**
     * @param totalDelayTime the totalDelayTime to set
     */
    public void setTotalDelayTime(double totalDelayTime) {
        this.totalDelayTime = totalDelayTime;
    }

    /**
     * @return the nama
     */
    public String getNama() {
        return nama;
    }

    /**
     * @param nama the nama to set
     */
    public void setNama(String nama) {
        this.nama = nama;
    }

    /**
     * @return the serverclock
     */
    public double getServerclock() {
        return serverclock;
    }

    /**
     * @param serverclock the serverclock to set
     */
    public void setServerclock(double serverclock) {
        this.serverclock = serverclock;
    }

    /**
     * @return the sim
     */
    public StatisticsSimulationPoli getSim() {
        return sim;
    }

    /**
     * @param sim the sim to set
     */
    public void setSim(StatisticsSimulationPoli sim) {
        this.sim = sim;
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
     * @return the counterCustomer
     */
    public int getCounterCustomer() {
        return counterCustomer;
    }

    /**
     * @param counterCustomer the counterCustomer to set
     */
    public void setCounterCustomer(int counterCustomer) {
        this.counterCustomer = counterCustomer;
    }

    /**
     * @param counter the counter to set
     */
    public void setCounter(int counter) {
        this.counter = counter;
    }
}