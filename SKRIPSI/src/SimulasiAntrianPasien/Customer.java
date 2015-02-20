/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SimulasiAntrianPasien;

/**
 *
 * @author robby
 */
public class Customer implements Comparable {
    private int number;
    private int numberinpoli;
    private String jenis;
    private ServerAwal server;
    private double arrivaltime;
    private double servicetime;
    private double timeServiceBegin;
    private double timeServiceEnd;
    private double timeServiceBegin2;
    private double timeServiceEnd2;private double timeServiceEnd3;private double timeServiceEnd4;
    private int priority;
    private double waitingtime;
    private double delaytime;
    private int serverawal;
    private int serverpetugas;
    private int serverperawat;
    private int serverdokter;
    private double arrivaltimepoli;private double arrivaltimepoli2;private double arrivaltimepoli3;
    private double servicetimepoli;private double servicetimepoli2;private double servicetimepoli3;
    private double delaytimepoli;private double delaytimepoli2;private double delaytimepoli3;
    private double waitingtimepoli;private double waitingtimepoli2;private double waitingtimepoli3;
    private double timeServiceBegin3;private double timeServiceBegin4;
    private boolean toPoliklinik;
    
    public Customer(String jenis,int number,double arrivaltime,int priority){
        super();
        this.number=number;
        this.arrivaltime=arrivaltime;
        this.number=number;
        this.jenis=jenis;
        this.priority=priority;
    }
    
    public Customer(){
        
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
     * @return the arrivaltime
     */
    public double getArrivaltime() {
        return arrivaltime;
    }

    /**
     * @param arrivaltime the arrivaltime to set
     */
    public void setArrivaltime(double arrivaltime) {
        this.arrivaltime = arrivaltime;
    }

    /**
     * @return the jenis
     */
    public String getJenis() {
        return jenis;
    }

    /**
     * @param jenis the jenis to set
     */
    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    /**
     * @return the priority
     */
    public int getPriority() {
        return priority;
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * @return the servicetime
     */
    public double getServicetime() {
        return servicetime;
    }

    /**
     * @param servicetime the servicetime to set
     */
    public void setServicetime(double servicetime) {
        this.servicetime = servicetime;
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
     * @return the waitingtime
     */
    public double getWaitingtime() {
        return waitingtime;
    }

    /**
     * @param waitingtime the waitingtime to set
     */
    public void setWaitingtime(double waitingtime) {
        this.waitingtime = waitingtime;
    }

    /**
     * @return the delaytime
     */
    public double getDelaytime() {
        return delaytime;
    }

    /**
     * @param delaytime the delaytime to set
     */
    public void setDelaytime(double delaytime) {
        this.delaytime = delaytime;
    }

    /**
     * @return the numberinpoli
     */
    public int getNumberinpoli() {
        return numberinpoli;
    }

    /**
     * @param numberinpoli the numberinpoli to set
     */
    public void setNumberinpoli(int numberinpoli) {
        this.numberinpoli = numberinpoli;
    }

    /**
     * @return the timeServiceBegin2
     */
    public double getTimeServiceBegin2() {
        return timeServiceBegin2;
    }

    /**
     * @param timeServiceBegin2 the timeServiceBegin2 to set
     */
    public void setTimeServiceBegin2(double timeServiceBegin2) {
        this.timeServiceBegin2 = timeServiceBegin2;
    }

    /**
     * @return the serverawal
     */
    public int getServerawal() {
        return serverawal;
    }

    /**
     * @param serverawal the serverawal to set
     */
    public void setServerawal(int serverawal) {
        this.serverawal = serverawal;
    }

    /**
     * @return the serverpetugas
     */
    public int getServerpetugas() {
        return serverpetugas;
    }

    /**
     * @param serverpetugas the serverpetugas to set
     */
    public void setServerpetugas(int serverpetugas) {
        this.serverpetugas = serverpetugas;
    }

    /**
     * @return the serverperawat
     */
    public int getServerperawat() {
        return serverperawat;
    }

    /**
     * @param serverperawat the serverperawat to set
     */
    public void setServerperawat(int serverperawat) {
        this.serverperawat = serverperawat;
    }

    /**
     * @return the serverdokter
     */
    public int getServerdokter() {
        return serverdokter;
    }

    /**
     * @param serverdokter the serverdokter to set
     */
    public void setServerdokter(int serverdokter) {
        this.serverdokter = serverdokter;
    }

    /**
     * @return the timeServiceEnd2
     */
    public double getTimeServiceEnd2() {
        return timeServiceEnd2;
    }

    /**
     * @param timeServiceEnd2 the timeServiceEnd2 to set
     */
    public void setTimeServiceEnd2(double timeServiceEnd2) {
        this.timeServiceEnd2 = timeServiceEnd2;
    }

    /**
     * @return the arrivaltimepoli
     */
    public double getArrivaltimepoli() {
        return arrivaltimepoli;
    }

    /**
     * @param arrivaltimepoli the arrivaltimepoli to set
     */
    public void setArrivaltimepoli(double arrivaltimepoli) {
        this.arrivaltimepoli = arrivaltimepoli;
    }

    /**
     * @return the servicetimepoli
     */
    public double getServicetimepoli() {
        return servicetimepoli;
    }

    /**
     * @param servicetimepoli the servicetimepoli to set
     */
    public void setServicetimepoli(double servicetimepoli) {
        this.servicetimepoli = servicetimepoli;
    }

    /**
     * @return the delaytimepoli
     */
    public double getDelaytimepoli() {
        return delaytimepoli;
    }

    /**
     * @param delaytimepoli the delaytimepoli to set
     */
    public void setDelaytimepoli(double delaytimepoli) {
        this.delaytimepoli = delaytimepoli;
    }

    /**
     * @return the waitingtimepoli
     */
    public double getWaitingtimepoli() {
        return waitingtimepoli;
    }

    /**
     * @param waitingtimepoli the waitingtimepoli to set
     */
    public void setWaitingtimepoli(double waitingtimepoli) {
        this.waitingtimepoli = waitingtimepoli;
    }

    /**
     * @return the arrivaltimepoli2
     */
    public double getArrivaltimepoli2() {
        return arrivaltimepoli2;
    }

    /**
     * @param arrivaltimepoli2 the arrivaltimepoli2 to set
     */
    public void setArrivaltimepoli2(double arrivaltimepoli2) {
        this.arrivaltimepoli2 = arrivaltimepoli2;
    }

    /**
     * @return the arrivaltimepoli3
     */
    public double getArrivaltimepoli3() {
        return arrivaltimepoli3;
    }

    /**
     * @param arrivaltimepoli3 the arrivaltimepoli3 to set
     */
    public void setArrivaltimepoli3(double arrivaltimepoli3) {
        this.arrivaltimepoli3 = arrivaltimepoli3;
    }

    /**
     * @return the servicetimepoli2
     */
    public double getServicetimepoli2() {
        return servicetimepoli2;
    }

    /**
     * @param servicetimepoli2 the servicetimepoli2 to set
     */
    public void setServicetimepoli2(double servicetimepoli2) {
        this.servicetimepoli2 = servicetimepoli2;
    }

    /**
     * @return the servicetimepoli3
     */
    public double getServicetimepoli3() {
        return servicetimepoli3;
    }

    /**
     * @param servicetimepoli3 the servicetimepoli3 to set
     */
    public void setServicetimepoli3(double servicetimepoli3) {
        this.servicetimepoli3 = servicetimepoli3;
    }

    /**
     * @return the delaytimepoli2
     */
    public double getDelaytimepoli2() {
        return delaytimepoli2;
    }

    /**
     * @param delaytimepoli2 the delaytimepoli2 to set
     */
    public void setDelaytimepoli2(double delaytimepoli2) {
        this.delaytimepoli2 = delaytimepoli2;
    }

    /**
     * @return the delaytimepoli3
     */
    public double getDelaytimepoli3() {
        return delaytimepoli3;
    }

    /**
     * @param delaytimepoli3 the delaytimepoli3 to set
     */
    public void setDelaytimepoli3(double delaytimepoli3) {
        this.delaytimepoli3 = delaytimepoli3;
    }

    /**
     * @return the waitingtimepoli2
     */
    public double getWaitingtimepoli2() {
        return waitingtimepoli2;
    }

    /**
     * @param waitingtimepoli2 the waitingtimepoli2 to set
     */
    public void setWaitingtimepoli2(double waitingtimepoli2) {
        this.waitingtimepoli2 = waitingtimepoli2;
    }

    /**
     * @return the waitingtimepoli3
     */
    public double getWaitingtimepoli3() {
        return waitingtimepoli3;
    }

    /**
     * @param waitingtimepoli3 the waitingtimepoli3 to set
     */
    public void setWaitingtimepoli3(double waitingtimepoli3) {
        this.waitingtimepoli3 = waitingtimepoli3;
    }

    /**
     * @return the timeServiceBegin3
     */
    public double getTimeServiceBegin3() {
        return timeServiceBegin3;
    }

    /**
     * @param timeServiceBegin3 the timeServiceBegin3 to set
     */
    public void setTimeServiceBegin3(double timeServiceBegin3) {
        this.timeServiceBegin3 = timeServiceBegin3;
    }

    /**
     * @return the timeServiceBegin4
     */
    public double getTimeServiceBegin4() {
        return timeServiceBegin4;
    }

    /**
     * @param timeServiceBegin4 the timeServiceBegin4 to set
     */
    public void setTimeServiceBegin4(double timeServiceBegin4) {
        this.timeServiceBegin4 = timeServiceBegin4;
    }

    /**
     * @return the timeServiceEnd3
     */
    public double getTimeServiceEnd3() {
        return timeServiceEnd3;
    }

    /**
     * @param timeServiceEnd3 the timeServiceEnd3 to set
     */
    public void setTimeServiceEnd3(double timeServiceEnd3) {
        this.timeServiceEnd3 = timeServiceEnd3;
    }

    /**
     * @return the timeServiceEnd4
     */
    public double getTimeServiceEnd4() {
        return timeServiceEnd4;
    }

    /**
     * @param timeServiceEnd4 the timeServiceEnd4 to set
     */
    public void setTimeServiceEnd4(double timeServiceEnd4) {
        this.timeServiceEnd4 = timeServiceEnd4;
    }

    @Override
    public int compareTo(Object o) {
        int compare=((Customer)o).getNumber();
        return this.number-compare;
    }

    /**
     * @return the toPoliklinik
     */
    public boolean isToPoliklinik() {
        return toPoliklinik;
    }

    /**
     * @param toPoliklinik the toPoliklinik to set
     */
    public void setToPoliklinik(boolean toPoliklinik) {
        this.toPoliklinik = toPoliklinik;
    }

}
