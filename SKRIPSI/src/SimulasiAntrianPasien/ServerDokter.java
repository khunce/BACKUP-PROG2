/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SimulasiAntrianPasien;

import GUI.InterfaceGUI1;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author robby
 */
public class ServerDokter extends Thread implements Server  {
    private boolean status;
    private int servernumber;
    private double timeServiceBegin;
    private double timeServiceEnd;
    private double totalservicetime;
    private double totalWaitingTime;
    private double totalDelayTime;
    private int counterCustomer;
    private java.util.Queue<Customer> serverqueue;
    private String nama;
    private StatisticsGenerator stat;
    private double serverclock;
    private String write;
    private InterfaceGUI1 gui;
    private int i;
    private int slidervalue;
    private Queue<Customer> queuereport;
    private LinkedList<Customer> queuereport2;
    private Customer temp;
    private int counter;
    private int counterPasienLama;
    private int counterPasienBaru;
    private int counterPasienEmergency;
    public ServerDokter(int servernumber, StatisticsGenerator stat,InterfaceGUI1 gui){
        this.servernumber=servernumber;
        this.stat=stat;
        this.gui=gui;
        this.status=false;
        this.serverclock=0;
        this.serverqueue=new LinkedList();
        i=0;
        this.slidervalue=500;
        this.queuereport=new LinkedList();
        this.queuereport2=new LinkedList<Customer>();
        this.temp=new Customer();
        this.counter=0;
        this.counterPasienBaru=0;
        this.counterPasienLama=0;
        this.counterPasienEmergency=0;
    }
    
    ServerDokter(){
        super();
        this.serverqueue=new LinkedList();
        this.counterCustomer=0;
        this.queuereport=new LinkedList();
    }
    
    @Override
    public synchronized void doServeCustomer() {
         while(this.getServerqueue().size()==0){
            try{
                    wait();
                }
            catch (InterruptedException ex) {
                    System.out.println("wait error " + ex.getMessage());
                }
            
        }
        this.setStatus(true);
        while(this.serverqueue.size()>0){
            System.out.println("i : "+i);
            this.setStatus(true);
            temp=(Customer)this.removeCustomerfromQueue();
            double waitingtime=0;
            double delaytime=0;
            double servicetime=0;
            if(getTemp().equals("BPJS Lama")){
                    this.counterPasienLama++;
                }
            else if(getTemp().equals("BPJS Baru")){
                    this.counterPasienBaru++;
            }
            else if(getTemp().equals("Emergency")){
                    this.counterPasienEmergency++;
            }
            if(getServerclock()<temp.getArrivaltimepoli3()){
                   setServerclock(temp.getArrivaltimepoli3());
                   if(i==0){
                            this.setTimeServiceBegin(this.getServerclock());
                       }
                   temp.setTimeServiceBegin4(this.serverclock);
                   if(temp.getJenis().equals("Emergency")){
                     getTemp().setTimeServiceBegin2(this.serverclock);
                   }
                   servicetime=stat.generateServiceTimePoli();
                   delaytime=(Math.abs(temp.getArrivaltimepoli3()-(getServerclock())));
                   temp.setDelaytimepoli3(delaytime);
                   setServerclock(getServerclock() + servicetime);
                   waitingtime=delaytime+servicetime;
                   temp.setWaitingtimepoli3(waitingtime);
                   temp.setServicetimepoli3(servicetime);
                   totalservicetime+=servicetime;
                   totalWaitingTime+=waitingtime;
                   totalDelayTime+=delaytime;
                   temp.setTimeServiceEnd4(this.getServerclock());
            }
            else if(getServerclock()>=temp.getArrivaltimepoli3()){
                   if(i==0){
                            this.setTimeServiceBegin(this.getServerclock());
                   }
                   temp.setTimeServiceBegin4(this.serverclock);
                   if(temp.getJenis().equals("Emergency")){
                        getTemp().setTimeServiceBegin2(this.serverclock);
                   }
                   servicetime=stat.generateServiceTimePoli();
                   delaytime=(Math.abs(temp.getArrivaltimepoli3()-(getServerclock())));
                   temp.setDelaytimepoli3(delaytime);
                   setServerclock(getServerclock() + servicetime);
                   waitingtime=delaytime+servicetime;
                   temp.setWaitingtimepoli3(waitingtime);
                   temp.setServicetimepoli3(servicetime);
                   totalservicetime+=servicetime;
                   totalWaitingTime+=waitingtime;
                   totalDelayTime+=delaytime;
                   temp.setTimeServiceEnd4(this.getServerclock());

            }
            i++;
            this.counter++;
            //temp.setTimeServiceEnd2(this.serverclock);
            this.setTimeServiceEnd(this.serverclock);
            temp.setServerdokter(servernumber);
            this.addToReportQueue(temp);
                BigDecimal bd = new BigDecimal(this.getServerclock()); 
                bd = bd.setScale(2,BigDecimal.ROUND_UP); 
                this.setServerclock(bd.doubleValue());
                String realtime=stat.convertSeconds(servicetime);
                String realtime2=stat.convertSeconds(this.getServerclock());
                //gui.setOutputValue6("Pasien nomot urut "+temp.getNumberinpoli()+" -"+"Server Clock : "+realtime2+"-"+"Service Time : "+realtime,this.servernumber);
                gui.setOutputValue11("Dokter ke - "+this.getServernumber(),this.servernumber+5);
                gui.setOutputValue12(getTemp().getNumberinpoli()+"",this.servernumber+5);
                gui.setOutputValue13(getTemp().getJenis()+"",this.servernumber+5);
                gui.setOutputValue14(realtime2+"",this.servernumber+5);
                gui.setOutputValue142(realtime+"",this.servernumber+5);
                try {
                    Thread.sleep(this.slidervalue);
                } catch (Exception ex) {
                            System.out.println(ex.getMessage());;
                }
        }
        this.setStatus(false);
        notifyAll();
    }
    
    public synchronized void addToReportQueue(Customer temp){
        System.out.println("counter report queue");
        queuereport.add(temp);
        queuereport2.add(temp);
        notifyAll();
    }
    
    public Queue<Customer> getQueueReport(){
        return this.queuereport;
    }
    
    public LinkedList<Customer> getQueueReport2(){
        return this.queuereport2;
    }

    public int getQueueSize(){
        return this.getServerqueue().size();
    }
    
    @Override
    public void run(){
        while(true){
            doServeCustomer();
            try {
                       Thread.sleep(this.slidervalue);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());;
                }
        }
    }
    
     public  synchronized void addCustomertoQueue(Customer cust){
        this.getServerqueue().add(cust);
        this.setCounterCustomer(this.getCounterCustomer() + 1);
        notifyAll();
    }
     
     public synchronized Customer removeCustomerfromQueue(){
          while(this.getServerqueue().size()==0){
            try{
                    wait();
                }
            catch (InterruptedException ex) {
                    System.out.println("wait error " + ex.getMessage());
                }
            
        }
          notifyAll();
          this.setCounterCustomer(this.getCounterCustomer() - 1);
          return this.serverqueue.remove();
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
     * @return the serverqueue
     */
    public java.util.Queue<Customer> getServerqueue() {
        return serverqueue;
    }

    /**
     * @param serverqueue the serverqueue to set
     */
    public void setServerqueue(java.util.Queue<Customer> serverqueue) {
        this.serverqueue = serverqueue;
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
     * @return the temp
     */
    public Customer getTemp() {
        return temp;
    }

    /**
     * @param temp the temp to set
     */
    public void setTemp(Customer temp) {
        this.temp = temp;
    }

    /**
     * @return the counter
     */
    public int getCounter() {
        return counter;
    }

    /**
     * @param counter the counter to set
     */
    public void setCounter(int counter) {
        this.counter = counter;
    }

    /**
     * @return the counterPasienLama
     */
    public int getCounterPasienLama() {
        return counterPasienLama;
    }

    /**
     * @return the counterPasienBaru
     */
    public int getCounterPasienBaru() {
        return counterPasienBaru;
    }

    /**
     * @return the counterPasienEmergency
     */
    public int getCounterPasienEmergency() {
        return counterPasienEmergency;
    }
    
}
