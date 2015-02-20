/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SimulasiAntrianPasien;

import GUI.InterfaceGUI1;
import java.math.BigDecimal;
import java.util.LinkedList;

/**
 *
 * @author robby
 */
public class ServerPerawat extends Thread implements Server {
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
    private ServerDokter[] serverdokter;
    private int i;
    private int slidervalue;
    private int batasserver;
    private int counterfindserver;
    private Customer temp;
    private LinkedList<Customer> queuereport2;
    private int counter;
    public ServerPerawat(int servernumber, StatisticsGenerator stat,InterfaceGUI1 gui,ServerDokter[] serverdokter){
        this.servernumber=servernumber;
        this.stat=stat;
        this.gui=gui;
        this.status=false;
        this.serverclock=0;
        this.serverqueue=new LinkedList();
        this.serverdokter=serverdokter;
        i=0;
        this.slidervalue=500;
        this.counterfindserver=0;
        this.batasserver=0;
        this.temp=new Customer();
        this.counter=0;
        this.totalDelayTime=0;
        this.totalWaitingTime=0;
        this.totalservicetime=0;
        this.queuereport2=new LinkedList<Customer>();
    }
    
    ServerPerawat(){
        super();
        this.serverqueue=new LinkedList();
        this.counterCustomer=0;
        this.totalDelayTime=0;
        this.totalWaitingTime=0;
        this.totalservicetime=0;
        this.queuereport2=new LinkedList<Customer>();
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
    
      public int getQueueSize(){
        return this.getServerqueue().size();
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
                setTemp((Customer)this.removeCustomerfromQueue());
                double waitingtime=0;
                double delaytime=0;
                double servicetime=0;
                if(getServerclock()<getTemp().getArrivaltimepoli2()){
                       setServerclock(getTemp().getArrivaltimepoli2());
                       if(i==0){
                            this.setTimeServiceBegin(this.getServerclock());
                       }
                       getTemp().setTimeServiceBegin3(this.serverclock);
                       servicetime=stat.generateServiceTimePoli();
                       delaytime=(Math.abs(getTemp().getArrivaltimepoli2()-(getServerclock())));
                       getTemp().setDelaytimepoli2(delaytime);
                       setServerclock(getServerclock() + servicetime);
                       waitingtime=delaytime+servicetime;
                       getTemp().setWaitingtimepoli2(waitingtime);
                       getTemp().setServicetimepoli2(servicetime);
                       setTotalservicetime(getTotalservicetime() + servicetime);
                       totalWaitingTime+=waitingtime;
                       totalDelayTime+=delaytime;
                       getTemp().setArrivaltimepoli3(getServerclock());
                       getTemp().setTimeServiceEnd3(this.getServerclock());
                }
                else if(getServerclock()>=getTemp().getArrivaltimepoli2()){
                       servicetime=stat.generateServiceTimePoli();
                       if(i==0){
                            this.setTimeServiceBegin(this.getServerclock());
                       }
                       getTemp().setTimeServiceBegin3(this.serverclock);
                       delaytime=(Math.abs(getTemp().getArrivaltimepoli2()-(getServerclock())));
                       getTemp().setDelaytimepoli2(delaytime);
                       setServerclock(getServerclock() + servicetime);
                       waitingtime=delaytime+servicetime;
                       getTemp().setWaitingtimepoli2(waitingtime);
                       getTemp().setServicetimepoli2(servicetime);
                       setTotalservicetime(getTotalservicetime() + servicetime);
                       totalWaitingTime+=waitingtime;
                       totalDelayTime+=delaytime;
                       getTemp().setArrivaltimepoli3(getServerclock());
                       getTemp().setTimeServiceEnd3(this.getServerclock());

                }
                i++;
                this.counter++;
                this.setTimeServiceEnd(this.serverclock);
                getTemp().setServerperawat(this.servernumber);
                BigDecimal bd = new BigDecimal(this.getServerclock()); 
                bd = bd.setScale(2,BigDecimal.ROUND_UP); 
                this.setServerclock(bd.doubleValue());
                String realtime=stat.convertSeconds(servicetime);
                String realtime2=stat.convertSeconds(this.getServerclock());
                gui.setOutputValue5("Pasien nomot urut "+getTemp().getNumberinpoli()+" -"+"Server Clock : "+realtime2+"-"+"Service Time : "+realtime,this.servernumber);
                try {
                    Thread.sleep(this.slidervalue);
                } catch (Exception ex) {
                            System.out.println(ex.getMessage());;
                }
                int server=this.findServer();
                this.addToReportQueue(getTemp());
                batasserver=server;
                this.serverdokter[server].setStatus(true);
                this.serverdokter[server].addCustomertoQueue(getTemp());
//                this.serverdokter[server].setStatus(false);
                
         }
         this.setStatus(false);
         notifyAll();
    }
    
    public synchronized void addToReportQueue(Customer temp){
        System.out.println("counter report queue");
        queuereport2.add(temp);
        notifyAll();
    }
    
    public LinkedList<Customer> getQueueReport2(){
        return this.queuereport2;
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

     public int findServer(){
        int cari=-1;
        if(this.counterfindserver==0){
            for(int i=batasserver;i<this.serverdokter.length;i++){
                System.out.println("CARI DOKTER KE : "+i+" clock sekarang : "+this.serverclock);
                System.out.println("STATUS DOKTER : "+this.serverdokter[i].isStatus()+" clock : "+this.serverdokter[i].getServerclock()+" queue size : "+this.serverdokter[i].getQueueSize());
                if(this.serverdokter[i].isStatus()==false&&this.serverdokter[i].getQueueSize()==0&&this.serverdokter[i].getServerclock()<=this.serverclock){
                    cari=i;
                    i=this.serverdokter.length;
                }
            }
            if(cari==-1){
                for(int i=0;i<this.batasserver;i++){
                    System.out.println("CARI DOKTER 2 KE : "+i+" clock sekarang : "+this.serverclock);
                    System.out.println("STATUS DOKTER 2: "+this.serverdokter[i].isStatus()+" clock : "+this.serverdokter[i].getServerclock()+" queue size : "+this.serverdokter[i].getQueueSize());
                    if(this.serverdokter[i].isStatus()==false&&this.serverdokter[i].getQueueSize()==0&&this.serverdokter[i].getServerclock()<=this.serverclock){
                        cari=i;
                        i=this.serverdokter.length;
                    }
                }
            }
            if(cari==-1){
                cari=this.getSmallestServerClock(serverdokter);
            }  
            this.counterfindserver++;
        }
        else{
            for(int i=batasserver+1;i<this.serverdokter.length;i++){
                System.out.println("CARI DOKTER KE : "+i+" clock sekarang : "+this.serverclock);
                System.out.println("STATUS DOKTER : "+this.serverdokter[i].isStatus()+" clock : "+this.serverdokter[i].getServerclock()+" queue size : "+this.serverdokter[i].getQueueSize());
                if(this.serverdokter[i].isStatus()==false&&this.serverdokter[i].getQueueSize()==0&&this.serverdokter[i].getServerclock()<=this.serverclock){
                    cari=i;
                    i=this.serverdokter.length;
                }
            }
            if(cari==-1){
                for(int i=0;i<this.batasserver;i++){
                    System.out.println("CARI DOKTER 2 KE : "+i+" clock sekarang : "+this.serverclock);
                    System.out.println("STATUS DOKTER 2: "+this.serverdokter[i].isStatus()+" clock : "+this.serverdokter[i].getServerclock()+" queue size : "+this.serverdokter[i].getQueueSize());
                    if(this.serverdokter[i].isStatus()==false&&this.serverdokter[i].getQueueSize()==0&&this.serverdokter[i].getServerclock()<=this.serverclock){
                        cari=i;
                        i=this.serverdokter.length;
                    }
                }
            }
            if(cari==-1){
                cari=this.getSmallestServerClock(serverdokter);
            } 
            this.counterfindserver++;
        }
        System.out.println("DAPAT DOKTER : "+cari);
        return cari;
    }
     
     
    public int getSmallestServerClock(ServerDokter[] server){
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
     * @return the batasserver
     */
    public int getBatasserver() {
        return batasserver;
    }

    /**
     * @param batasserver the batasserver to set
     */
    public void setBatasserver(int batasserver) {
        this.batasserver = batasserver;
    }

    /**
     * @return the counterfindserver
     */
    public int getCounterfindserver() {
        return counterfindserver;
    }

    /**
     * @param counterfindserver the counterfindserver to set
     */
    public void setCounterfindserver(int counterfindserver) {
        this.counterfindserver = counterfindserver;
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
    
}
