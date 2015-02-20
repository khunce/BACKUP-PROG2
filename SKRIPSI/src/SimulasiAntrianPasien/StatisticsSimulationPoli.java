/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SimulasiAntrianPasien;

import GUI.InterfaceGUI1;
import java.math.BigDecimal;

/**
 *
 * @author robby
 */
public class StatisticsSimulationPoli extends Thread{
    private CustomerQ customerqueue;
    private int number;
    private double counterPasien;
    private StatisticsGenerator gen;
    private int numOfCustomer;
    private ServerPetugas[] serverpetugasqueue;
    private InterfaceGUI1 MainGUI;
    private int counterpasien1;
    private int counterpasien2;
    private int counterpasien3;
    private double clock;
    private int slidervalue;
    private ServerDokter[] serverdokter;
    private ServerPerawat[] serverperawat;
    private int batasserver;
    private int counterfindserver;
    private int batasserver2;
    private int counterfindserver2;
    private Customer temp;
    public StatisticsSimulationPoli(ServerPetugas[] ServerPetugas,StatisticsGenerator gen,InterfaceGUI1 MainGUI,int numOfCustomer){
        this.customerqueue=new CustomerQ();
        this.number=1;
        this.numOfCustomer=numOfCustomer;
        this.setCounterPasien();
        this.serverpetugasqueue=ServerPetugas;
        this.clock=0;
        this.MainGUI=MainGUI;
        this.counterpasien1=0;
        this.counterpasien2=0;
        this.counterpasien3=0;
        this.gen=gen;
        this.slidervalue=500;
        this.batasserver=0;
        this.counterfindserver=0;
        this.batasserver2=0;
        this.counterfindserver2=0;
        this.temp=new Customer();
    }
    
    StatisticsSimulationPoli(){
        this.customerqueue=new CustomerQ();
        this.number=1;
        this.counterPasien=0;
        this.numOfCustomer=numOfCustomer;
        this.clock=0;
        this.setCounterPasien();
    }
    
    public  synchronized void addCustomer(Customer cust){
        this.customerqueue.add(cust);
        System.out.println("poli");
        notifyAll();
    }
    
    public void setCounterPasien(){
        this.setCounterPasien(numOfCustomer*0.1);
        BigDecimal bd=new BigDecimal(this.getCounterPasien());
        bd = bd.setScale(0,BigDecimal.ROUND_UP); 
        this.setCounterPasien(bd.doubleValue());
        System.out.println("Jumlah pasien poli : "+this.getCounterPasien());
    }
    
    public Customer processArrival(){
        temp=this.removeCustomer();
        double arrivaltime=temp.getArrivaltime();
        clock=clock+arrivaltime;
        temp.setNumberinpoli(number);
        System.out.println("Jenis pasien "+temp.getJenis());
        String realtime=gen.convertSeconds(temp.getArrivaltime());
        MainGUI.setOutputValue2(temp.getNumber()+" "+temp.getJenis()+" "+realtime);
        if(temp.getJenis().equals("BPJS Lama")){
            this.counterpasien2++;
        }
        else if(temp.getJenis().equals("BPJS Baru")){
            this.counterpasien1++;
        }
        else if(temp.getJenis().equals("Emergency")){
            this.counterpasien3++;
        }
        MainGUI.setOutputCounter2(this.counterpasien1,this.counterpasien2,this.counterpasien3);
         try {
                       Thread.sleep(this.slidervalue);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());;
                }
        number++;
        return temp;
    }
    
    public int findServer(){
        int cari=-1;
        if(this.counterfindserver==0){
            for(int i=batasserver;i<this.serverpetugasqueue.length;i++){
                System.out.println("CARI PETUGAS : "+i+" clock sekarang : "+this.clock);
                System.out.println("STATUS PETUGAS : "+this.serverpetugasqueue[i].isStatus()+" queue size : "+this.serverpetugasqueue[i].getQueueSize()+" server clock : "+this.serverpetugasqueue[i].getServerclock());
                if(this.serverpetugasqueue[i].isStatus()==false&&this.serverpetugasqueue[i].getQueueSize()==0&&(this.serverpetugasqueue[i].getServerclock()+this.serverpetugasqueue[i].getTemp().getServicetimepoli())<=this.clock){
                    cari=i;
                    i=this.serverpetugasqueue.length;
                }
            }
            System.out.println("DAPAT PETUGAS : "+cari);
            if(cari==-1){
                for(int i=0;i<batasserver;i++){
                    if(this.serverpetugasqueue[i].isStatus()==false&&this.serverpetugasqueue[i].getQueueSize()==0&&(this.serverpetugasqueue[i].getServerclock()+this.serverpetugasqueue[i].getTemp().getServicetimepoli())<=this.clock){
                        cari=i;
                        i=this.serverpetugasqueue.length;
                    }
                }
            }
            if(cari==-1){
                System.out.println("cari server petugas 3");
                cari=getSmallestServerClock(this.serverpetugasqueue);
            }
            this.counterfindserver++;
        }
        else{
            for(int i=batasserver+1;i<this.serverpetugasqueue.length;i++){
                System.out.println("CARI PETUGAS : "+i+" clock sekarang : "+this.clock);
                System.out.println("STATUS PETUGAS : "+this.serverpetugasqueue[i].isStatus()+" queue size : "+this.serverpetugasqueue[i].getQueueSize()+" server clock : "+this.serverpetugasqueue[i].getServerclock());
                if(this.serverpetugasqueue[i].isStatus()==false&&this.serverpetugasqueue[i].getQueueSize()==0&&(this.serverpetugasqueue[i].getServerclock()+this.serverpetugasqueue[i].getTemp().getServicetimepoli())<=this.clock){
                    cari=i;
                    i=this.serverpetugasqueue.length;
                }
            }
            System.out.println("DAPAT PETUGAS : "+cari);
            if(cari==-1){
                for(int i=0;i<batasserver;i++){
                    if(this.serverpetugasqueue[i].isStatus()==false&&this.serverpetugasqueue[i].getQueueSize()==0&&(this.serverpetugasqueue[i].getServerclock()+this.serverpetugasqueue[i].getTemp().getServicetimepoli())<=this.clock){
                        cari=i;
                        i=this.serverpetugasqueue.length;
                    }
                }
            }
            if(cari==-1){
                System.out.println("cari server petugas 3");
                cari=getSmallestServerClock(this.serverpetugasqueue);
            }
            this.counterfindserver++;
        }
        return cari;
   }
    
    
    public int getSmallestServerClock(ServerPetugas[] server){
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
    
    public int findServerDokter(){
        int cari=-1;
        if(this.counterfindserver2==0){
            for(int i=batasserver2;i<this.serverdokter.length;i++){
                System.out.println("CARI DOKTER EMERGENCY : "+i+" clock sekarang : "+this.clock);
                System.out.println("STATUS DOKTER EMERGENCY : "+this.serverdokter[i].isStatus()+" clock : "+this.serverdokter[i].getServerclock()+" queue size : "+this.serverdokter[i].getQueueSize());
                if(this.serverdokter[i].isStatus()==false&&this.serverdokter[i].getQueueSize()==0&&this.serverdokter[i].getServerclock()<=this.clock){
                    cari=i;
                    i=this.serverdokter.length;
                }
            }
            System.out.println("DAPAT DOKTER EMERGENCY : "+cari);
            if(cari==-1){
                for(int i=0;i<batasserver2;i++){
                    System.out.println("CARI DOKTER EMERGENCY : "+i+" clock sekarang : "+this.clock);
                    System.out.println("STATUS DOKTER EMERGENCY : "+this.serverdokter[i].isStatus()+" clock : "+this.serverdokter[i].getServerclock()+" queue size : "+this.serverdokter[i].getQueueSize());
                    if(this.serverdokter[i].isStatus()==false&&this.serverdokter[i].getQueueSize()==0&&this.serverdokter[i].getServerclock()<=this.clock){
                        cari=i;
                        i=this.serverdokter.length;
                    }
                }
            }
            if(cari==-1){
                cari=this.getSmallestServerClockDokter(serverdokter);
            }
            this.counterfindserver2++;
            
        }
        else{
            for(int i=batasserver2+1;i<this.serverdokter.length;i++){
                System.out.println("CARI DOKTER EMERGENCY : "+i+" clock sekarang : "+this.clock);
                System.out.println("STATUS DOKTER EMERGENCY : "+this.serverdokter[i].isStatus()+" clock : "+this.serverdokter[i].getServerclock()+" queue size : "+this.serverdokter[i].getQueueSize());
                if(this.serverdokter[i].isStatus()==false&&this.serverdokter[i].getQueueSize()==0&&this.serverdokter[i].getServerclock()<=this.clock){
                    cari=i;
                    i=this.serverdokter.length;
                }
            }
            System.out.println("DAPAT DOKTER EMERGENCY : "+cari);
            if(cari==-1){
                for(int i=0;i<batasserver2;i++){
                    System.out.println("CARI DOKTER EMERGENCY : "+i+" clock sekarang : "+this.clock);
                    System.out.println("STATUS DOKTER EMERGENCY : "+this.serverdokter[i].isStatus()+" clock : "+this.serverdokter[i].getServerclock()+" queue size : "+this.serverdokter[i].getQueueSize());
                    if(this.serverdokter[i].isStatus()==false&&this.serverdokter[i].getQueueSize()==0&&this.serverdokter[i].getServerclock()<=this.clock){
                        cari=i;
                        i=this.serverdokter.length;
                    }
                }
            }
            if(cari==-1){
                cari=this.getSmallestServerClockDokter(serverdokter);
            }
            this.counterfindserver2++;
        }
        return cari;
    }
    
    
    public int getSmallestServerClockDokter(ServerDokter[] server){
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
            System.out.println("Customer poli:"+this.customerqueue.getSize());
            Customer temp=new Customer();
            int server=this.findServer();
            temp=this.processArrival();
            if(temp.getJenis().equals("Emergency")){
                server=this.findServerDokter();
                batasserver2=server;
                this.serverdokter[server].setStatus(true);
                temp.setArrivaltimepoli(temp.getArrivaltime());
                temp.setArrivaltimepoli3(temp.getArrivaltime());
                this.serverdokter[server].addCustomertoQueue(temp);
            }
            else{
                batasserver=server;
                this.serverpetugasqueue[server].setStatus(true);
                this.serverpetugasqueue[server].addCustomertoQueue(temp);
            }
             
    }
    
    public synchronized Customer removeCustomer(){
        while(this.customerqueue.getSize()==0){
            try{
                wait();
            }
            catch (InterruptedException ex) {
                System.out.println("wait error " + ex.getMessage());
            }
        }
        notifyAll();
        Customer temp=(Customer)this.customerqueue.poll();
        this.setCounterPasien(this.getCounterPasien()-1);
        return temp;
    }

    /**
     * @return the counterPasien
     */
    public double getCounterPasien() {
        return counterPasien;
    }

    /**
     * @param counterPasien the counterPasien to set
     */
    public void setCounterPasien(double counterPasien) {
        this.counterPasien = counterPasien;
    }
    
    @Override
    public void run(){
        while(true){    
            this.runSimulation();
        }
    }

    /**
     * @return the counterpasien1
     */
    public int getCounterpasien1() {
        return counterpasien1;
    }

    /**
     * @param counterpasien1 the counterpasien1 to set
     */
    public void setCounterpasien1(int counterpasien1) {
        this.counterpasien1 = counterpasien1;
    }

    /**
     * @return the counterpasien2
     */
    public int getCounterpasien2() {
        return counterpasien2;
    }

    /**
     * @param counterpasien2 the counterpasien2 to set
     */
    public void setCounterpasien2(int counterpasien2) {
        this.counterpasien2 = counterpasien2;
    }

    /**
     * @return the counterpasien3
     */
    public int getCounterpasien3() {
        return counterpasien3;
    }

    /**
     * @param counterpasien3 the counterpasien3 to set
     */
    public void setCounterpasien3(int counterpasien3) {
        this.counterpasien3 = counterpasien3;
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
}
