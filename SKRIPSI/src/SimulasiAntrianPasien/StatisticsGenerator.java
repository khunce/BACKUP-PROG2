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
import ExcelInputSimulation.ExcelReader;
import RandomVariate.Exponential;
import RandomVariate.LCGRandom;
import RandomVariate.Poisson;
import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
public class StatisticsGenerator  {

    private double servicetime;
    private Poisson poisson;
    private Exponential exponential;
    private Exponential exponential2;
    private Exponential exponential3;
    private Random rand;
    private double meanservicetime;
    private double meanarrivaltime;
    private double meanservicetime2;
    private double meanservicetimepoli;
    private Object[][] data;
    private ExcelReader excel;
    BigDecimal bd;
    public StatisticsGenerator(double meanarrivaltime,double meanservicetime,  double meanservicetime2,double meanservicetimepoli){
        double temp=1/meanarrivaltime;
        this.meanarrivaltime=temp;
        bd = new BigDecimal(this.meanarrivaltime); 
        bd = bd.setScale(5,BigDecimal.ROUND_UP);  
        this.meanarrivaltime=bd.doubleValue();
        
        temp=1/meanservicetime;
        this.meanservicetime=temp;
        System.out.println("mean service time 1 : "+this.meanservicetime);
        bd = new BigDecimal(this.meanservicetime); 
        bd = bd.setScale(2,BigDecimal.ROUND_UP);  
        this.meanservicetime=bd.doubleValue();
        System.out.println("mean service time 1-2 : "+this.meanservicetime);
        
        temp=1/meanservicetime2;
        this.meanservicetime2=temp;
        System.out.println("mean service time 2 : "+this.meanservicetime2);
        bd = new BigDecimal(this.meanservicetime2);
        bd = bd.setScale(2,BigDecimal.ROUND_UP);  
        this.meanservicetime2=bd.doubleValue();
        System.out.println("mean service time 2-2 : "+this.meanservicetime2);
        
        temp=1/meanservicetimepoli;
        this.meanservicetimepoli=temp;
        System.out.println("mean service time poli : "+this.meanservicetimepoli);
        bd = new BigDecimal(this.meanservicetimepoli);
        bd = bd.setScale(2,BigDecimal.ROUND_UP);  
        this.meanservicetimepoli=bd.doubleValue();
        System.out.println("mean service time poli 2 : "+this.meanservicetimepoli);
        
        rand=new Random();
        this.poisson=new Poisson(this.meanarrivaltime,(int)rand.nextDouble());
        this.exponential=new Exponential(this.meanservicetime,1);
        this.exponential2=new Exponential(this.meanservicetime2,1);
        this.exponential3=new Exponential(this.meanservicetimepoli,1);
    }
    
    public StatisticsGenerator( double meanservicetimepoli){
        rand=new Random();
        double temp=1/meanservicetimepoli;
        this.meanservicetimepoli=temp;
        System.out.println("mean service time poli : "+this.meanservicetimepoli);
        bd = new BigDecimal(this.meanservicetimepoli);
        bd = bd.setScale(2,BigDecimal.ROUND_UP);  
        this.meanservicetimepoli=bd.doubleValue();
        this.exponential3=new Exponential(this.meanservicetimepoli,1);
        System.out.println("mean service time poli 2 : "+this.meanservicetimepoli);
    }
    
    public StatisticsGenerator(){
        
    }
    
    public double convertToRealTime(String time){
        String[] split=time.split(":");
        double seconds=(Double.parseDouble(split[2])/60);
        double minutes=Double.parseDouble(split[1]);
        double hour=Double.parseDouble(split[0])*60;
        bd = new BigDecimal(hour+minutes+seconds); 
        bd = bd.setScale(2,BigDecimal.ROUND_UP);
        return bd.doubleValue();
        
    }

     public double generateServiceTimePoli(){
        //double servicetime=this.exponential(this.rand,this.meanservicetimepoli);
        double servicetime=this.exponential3.gen();
        bd = new BigDecimal(servicetime); 
        bd = bd.setScale(2,BigDecimal.ROUND_UP);
        return bd.doubleValue();
    }

    public double generateArrivalTime(){
        bd = new BigDecimal(this.poisson.gen()); 
        bd = bd.setScale(2,BigDecimal.ROUND_UP);
        return bd.doubleValue();
    }
    
    public double generateServiceTime(){
        //double servicetime=this.exponential(this.rand,this.meanservicetime);
        double servicetime=exponential.gen();
        bd = new BigDecimal(servicetime); 
        bd = bd.setScale(2,BigDecimal.ROUND_UP);
        return bd.doubleValue();
    }
    
    public double generateServiceTime2(){
        //double servicetime=this.exponential(this.rand,this.meanservicetime2);
        double servicetime=this.exponential2.gen();
        bd = new BigDecimal(servicetime); 
        bd = bd.setScale(2,BigDecimal.ROUND_UP);
        return bd.doubleValue();
    }
    
    public Object[][] generateStatisticsReportAwal(Queue<Customer> queuecustomer){
         data=new Object[queuecustomer.size()][9];
         int i=0;
         while(queuecustomer.isEmpty()==false){
             Customer temp=queuecustomer.remove();
             data[i][0]=temp.getNumber();
             data[i][1]=temp.getJenis();
             data[i][2]=this.convertSeconds(temp.getArrivaltime());
              System.out.println("cek generate : "+temp.getNumber()+" "+this.convertSeconds(temp.getArrivaltime())+" "+this.convertSeconds(temp.getServicetime()));
             data[i][3]=this.convertSeconds(temp.getServicetime());
             data[i][4]=this.convertSeconds(temp.getTimeServiceBegin());
             data[i][5]=this.convertSeconds(temp.getDelaytime());
             data[i][6]=this.convertSeconds(temp.getTimeServiceEnd());
             data[i][7]=this.convertSeconds(temp.getWaitingtime());
             data[i][8]=temp.getServerawal();
             i++;
          }
         return data;
    }
    
      public Object[][] generateStatisticsReportAwal2(LinkedList<Customer> queuecustomer){
         data=new Object[queuecustomer.size()][9];
         int i=0;
         while(i<queuecustomer.size()){
             Customer temp=queuecustomer.get(i);
             data[i][0]=temp.getNumber();
             data[i][1]=temp.getJenis();
             data[i][2]=this.convertSeconds(temp.getArrivaltime());
              System.out.println("cek generate : "+temp.getNumber()+" "+this.convertSeconds(temp.getArrivaltime())+" "+this.convertSeconds(temp.getServicetime()));
             data[i][3]=this.convertSeconds(temp.getServicetime());
             data[i][4]=this.convertSeconds(temp.getTimeServiceBegin());
             data[i][5]=this.convertSeconds(temp.getDelaytime());
             data[i][6]=this.convertSeconds(temp.getTimeServiceEnd());
             data[i][7]=this.convertSeconds(temp.getWaitingtime());
             data[i][8]=temp.getServerawal();
             i++;
          }
         return data;
    }
      
       public Object[][] generateStatisticsReportAwal3(Customer[] queuecustomer){
         data=new Object[queuecustomer.length][9];
         int i=0;
         while(i<queuecustomer.length){
             Customer temp=queuecustomer[i];
             data[i][0]=temp.getNumber();
             data[i][1]=temp.getJenis();
             data[i][2]=this.convertSeconds(temp.getArrivaltime());
              System.out.println("cek generate : "+temp.getNumber()+" "+this.convertSeconds(temp.getArrivaltime())+" "+this.convertSeconds(temp.getServicetime()));
             data[i][3]=this.convertSeconds(temp.getServicetime());
             data[i][4]=this.convertSeconds(temp.getTimeServiceBegin());
             data[i][5]=this.convertSeconds(temp.getDelaytime());
             data[i][6]=this.convertSeconds(temp.getTimeServiceEnd());
             data[i][7]=this.convertSeconds(temp.getWaitingtime());
             data[i][8]=temp.getServerawal();
             i++;
          }
         return data;
    }
       
        public Object[][] generateStatisticsReportAwal4(Customer[] queuecustomer){
         data=new Object[queuecustomer.length][9];
         int i=0;
         LinkedList<String> arrtime=this.excel.getArrivaltime();
         LinkedList<String> sertime=this.excel.getServicetime();
         while(i<queuecustomer.length){
             Customer temp=queuecustomer[i];
             data[i][0]=temp.getNumber();
             data[i][1]=temp.getJenis();
             data[i][2]=(arrtime.get(i));
              System.out.println("cek generate : "+temp.getNumber()+" "+this.convertSeconds(temp.getArrivaltime())+" "+this.convertSeconds(temp.getServicetime()));
             data[i][3]=(sertime.get(i));
             data[i][4]=this.convertSeconds(temp.getTimeServiceBegin());
             data[i][5]=this.convertSeconds(temp.getDelaytime());
             data[i][6]=this.convertSeconds(temp.getTimeServiceEnd());
             data[i][7]=this.convertSeconds(temp.getWaitingtime());
             data[i][8]=temp.getServerawal();
             i++;
          }
         return data;
    }
    
     public Object[][] generateStatisticsReportPoli(Queue<Customer> queuecustomer){
         data=new Object[queuecustomer.size()][8];
         int i=0;
         while(queuecustomer.isEmpty()==false){
             Customer temp=queuecustomer.remove();
             data[i][0]=temp.getNumberinpoli();
             data[i][1]=temp.getJenis();
             data[i][2]=this.convertSeconds(temp.getArrivaltimepoli());
             System.out.println("cek generate 1 :"+" "+this.convertSeconds(temp.getArrivaltimepoli())+" "+this.convertSeconds(temp.getServicetimepoli())+" "+this.convertSeconds(temp.getTimeServiceBegin2())+" "+this.convertSeconds(temp.getDelaytimepoli())+" "+this.convertSeconds(temp.getWaitingtimepoli())+" "+this.convertSeconds(temp.getTimeServiceEnd2()));
              System.out.println("cek generate 2 :"+" "+this.convertSeconds(temp.getArrivaltimepoli2())+" "+this.convertSeconds(temp.getServicetimepoli2())+" "+this.convertSeconds(temp.getTimeServiceBegin3())+" "+this.convertSeconds(temp.getDelaytimepoli2())+" "+this.convertSeconds(temp.getWaitingtimepoli2())+" "+this.convertSeconds(temp.getTimeServiceEnd3()));
              System.out.println("cek generate 3 :"+" "+this.convertSeconds(temp.getArrivaltimepoli3())+" "+this.convertSeconds(temp.getServicetimepoli3())+" "+this.convertSeconds(temp.getTimeServiceBegin4())+" "+this.convertSeconds(temp.getDelaytimepoli3())+" "+this.convertSeconds(temp.getWaitingtimepoli3())+" "+this.convertSeconds(temp.getTimeServiceEnd4()));
             data[i][3]=this.convertSeconds(temp.getServicetimepoli()+temp.getServicetimepoli2()+temp.getServicetimepoli3());
             data[i][4]=this.convertSeconds(temp.getTimeServiceBegin2());
             data[i][5]=this.convertSeconds(temp.getDelaytime()+temp.getDelaytimepoli2()+temp.getDelaytimepoli3());
             data[i][6]=this.convertSeconds(temp.getTimeServiceEnd4());
             data[i][7]=this.convertSeconds(temp.getWaitingtimepoli()+temp.getWaitingtimepoli2()+temp.getWaitingtimepoli3());
             i++;
          }
         return data;
    }
     
     
     public Object[][] generateStatisticsReportPoli2(LinkedList<Customer> queuecustomer){
         data=new Object[queuecustomer.size()][8];
         int i=0;
         while(i<queuecustomer.size()){
             Customer temp=queuecustomer.get(i);
             data[i][0]=temp.getNumberinpoli();
             data[i][1]=temp.getJenis();
             data[i][2]=this.convertSeconds(temp.getArrivaltimepoli());
             System.out.println("cek generate 1 :"+" "+this.convertSeconds(temp.getArrivaltimepoli())+" "+this.convertSeconds(temp.getServicetimepoli())+" "+this.convertSeconds(temp.getTimeServiceBegin2())+" "+this.convertSeconds(temp.getDelaytimepoli())+" "+this.convertSeconds(temp.getWaitingtimepoli())+" "+this.convertSeconds(temp.getTimeServiceEnd2()));
              System.out.println("cek generate 2 :"+" "+this.convertSeconds(temp.getArrivaltimepoli2())+" "+this.convertSeconds(temp.getServicetimepoli2())+" "+this.convertSeconds(temp.getTimeServiceBegin3())+" "+this.convertSeconds(temp.getDelaytimepoli2())+" "+this.convertSeconds(temp.getWaitingtimepoli2())+" "+this.convertSeconds(temp.getTimeServiceEnd3()));
              System.out.println("cek generate 3 :"+" "+this.convertSeconds(temp.getArrivaltimepoli3())+" "+this.convertSeconds(temp.getServicetimepoli3())+" "+this.convertSeconds(temp.getTimeServiceBegin4())+" "+this.convertSeconds(temp.getDelaytimepoli3())+" "+this.convertSeconds(temp.getWaitingtimepoli3())+" "+this.convertSeconds(temp.getTimeServiceEnd4()));
             data[i][3]=this.convertSeconds(temp.getServicetimepoli()+temp.getServicetimepoli2()+temp.getServicetimepoli3());
             data[i][4]=this.convertSeconds(temp.getTimeServiceBegin2());
             data[i][5]=this.convertSeconds(temp.getDelaytime()+temp.getDelaytimepoli2()+temp.getDelaytimepoli3());
             data[i][6]=this.convertSeconds(temp.getTimeServiceEnd4());
             data[i][7]=this.convertSeconds(temp.getWaitingtimepoli()+temp.getWaitingtimepoli2()+temp.getWaitingtimepoli3());
             i++;
          }
         return data;
    }
     
     public Object[][] generateStatisticsReportPoli3(Customer[] queuecustomer){
         data=new Object[queuecustomer.length][8];
         int i=0;
         while(i<queuecustomer.length){
             Customer temp=queuecustomer[i];
             if(temp.getJenis().equals("BPJS Lama")||temp.getJenis().equals("BPJS Baru")){
                data[i][0]=temp.getNumberinpoli();
                data[i][1]=temp.getJenis();
                data[i][2]=this.convertSeconds(temp.getArrivaltimepoli());
                System.out.println("cek generate 1 :"+" "+this.convertSeconds(temp.getArrivaltimepoli())+" "+this.convertSeconds(temp.getServicetimepoli())+" "+this.convertSeconds(temp.getTimeServiceBegin2())+" "+this.convertSeconds(temp.getDelaytimepoli())+" "+this.convertSeconds(temp.getWaitingtimepoli())+" "+this.convertSeconds(temp.getTimeServiceEnd2()));
                 System.out.println("cek generate 2 :"+" "+this.convertSeconds(temp.getArrivaltimepoli2())+" "+this.convertSeconds(temp.getServicetimepoli2())+" "+this.convertSeconds(temp.getTimeServiceBegin3())+" "+this.convertSeconds(temp.getDelaytimepoli2())+" "+this.convertSeconds(temp.getWaitingtimepoli2())+" "+this.convertSeconds(temp.getTimeServiceEnd3()));
                 System.out.println("cek generate 3 :"+" "+this.convertSeconds(temp.getArrivaltimepoli3())+" "+this.convertSeconds(temp.getServicetimepoli3())+" "+this.convertSeconds(temp.getTimeServiceBegin4())+" "+this.convertSeconds(temp.getDelaytimepoli3())+" "+this.convertSeconds(temp.getWaitingtimepoli3())+" "+this.convertSeconds(temp.getTimeServiceEnd4()));
                data[i][3]=this.convertSeconds(temp.getServicetimepoli()+temp.getServicetimepoli2()+temp.getServicetimepoli3());
                data[i][4]=this.convertSeconds(temp.getTimeServiceBegin2());
                data[i][5]=this.convertSeconds(temp.getDelaytimepoli()+temp.getDelaytimepoli2()+temp.getDelaytimepoli3());
                data[i][6]=this.convertSeconds(temp.getTimeServiceEnd4());
                data[i][7]=this.convertSeconds(temp.getWaitingtimepoli()+temp.getWaitingtimepoli2()+temp.getWaitingtimepoli3());
                i++;
             }
             else{
                System.out.println("kasus emergency :"+temp.getArrivaltime());
                data[i][0]=temp.getNumberinpoli();
                data[i][1]=temp.getJenis();
                data[i][2]=this.convertSeconds(temp.getArrivaltimepoli3());
                System.out.println("cek generate 1 :"+" "+this.convertSeconds(temp.getArrivaltimepoli())+" "+this.convertSeconds(temp.getServicetimepoli())+" "+this.convertSeconds(temp.getTimeServiceBegin2())+" "+this.convertSeconds(temp.getDelaytimepoli())+" "+this.convertSeconds(temp.getWaitingtimepoli())+" "+this.convertSeconds(temp.getTimeServiceEnd2()));
                System.out.println("cek generate 2 :"+" "+this.convertSeconds(temp.getArrivaltimepoli2())+" "+this.convertSeconds(temp.getServicetimepoli2())+" "+this.convertSeconds(temp.getTimeServiceBegin3())+" "+this.convertSeconds(temp.getDelaytimepoli2())+" "+this.convertSeconds(temp.getWaitingtimepoli2())+" "+this.convertSeconds(temp.getTimeServiceEnd3()));
                System.out.println("cek generate 3 :"+" "+this.convertSeconds(temp.getArrivaltimepoli3())+" "+this.convertSeconds(temp.getServicetimepoli3())+" "+this.convertSeconds(temp.getTimeServiceBegin4())+" "+this.convertSeconds(temp.getDelaytimepoli3())+" "+this.convertSeconds(temp.getWaitingtimepoli3())+" "+this.convertSeconds(temp.getTimeServiceEnd4()));
                data[i][3]=this.convertSeconds(temp.getServicetimepoli()+temp.getServicetimepoli2()+temp.getServicetimepoli3());
                data[i][4]=this.convertSeconds(temp.getTimeServiceBegin2());
                data[i][5]=this.convertSeconds(temp.getDelaytimepoli()+temp.getDelaytimepoli2()+temp.getDelaytimepoli3());
                data[i][6]=this.convertSeconds(temp.getTimeServiceEnd4());
                data[i][7]=this.convertSeconds(temp.getWaitingtimepoli()+temp.getWaitingtimepoli2()+temp.getWaitingtimepoli3());
                i++;
             }
          }
         return data;
    }
     
      public Object[][] generateStatisticsReportPoli4(Customer[] queuecustomer){
         data=new Object[queuecustomer.length][8];
         int i=0;
         while(i<queuecustomer.length){
             Customer temp=queuecustomer[i];
             if(temp.getJenis().contains("BPJS (Lama)")||temp.getJenis().contains("BPJS (Baru)")){
                data[i][0]=temp.getNumberinpoli();
                data[i][1]=temp.getJenis();
                data[i][2]=this.convertSeconds(temp.getArrivaltimepoli());
                System.out.println("cek generate 1 :"+" "+this.convertSeconds(temp.getArrivaltimepoli())+" "+this.convertSeconds(temp.getServicetimepoli())+" "+this.convertSeconds(temp.getTimeServiceBegin2())+" "+this.convertSeconds(temp.getDelaytimepoli())+" "+this.convertSeconds(temp.getWaitingtimepoli())+" "+this.convertSeconds(temp.getTimeServiceEnd2()));
                 System.out.println("cek generate 2 :"+" "+this.convertSeconds(temp.getArrivaltimepoli2())+" "+this.convertSeconds(temp.getServicetimepoli2())+" "+this.convertSeconds(temp.getTimeServiceBegin3())+" "+this.convertSeconds(temp.getDelaytimepoli2())+" "+this.convertSeconds(temp.getWaitingtimepoli2())+" "+this.convertSeconds(temp.getTimeServiceEnd3()));
                 System.out.println("cek generate 3 :"+" "+this.convertSeconds(temp.getArrivaltimepoli3())+" "+this.convertSeconds(temp.getServicetimepoli3())+" "+this.convertSeconds(temp.getTimeServiceBegin4())+" "+this.convertSeconds(temp.getDelaytimepoli3())+" "+this.convertSeconds(temp.getWaitingtimepoli3())+" "+this.convertSeconds(temp.getTimeServiceEnd4()));
                data[i][3]=this.convertSeconds(temp.getServicetimepoli()+temp.getServicetimepoli2()+temp.getServicetimepoli3());
                data[i][4]=this.convertSeconds(temp.getTimeServiceBegin2());
                data[i][5]=this.convertSeconds(temp.getDelaytimepoli()+temp.getDelaytimepoli2()+temp.getDelaytimepoli3());
                data[i][6]=this.convertSeconds(temp.getTimeServiceEnd4());
                data[i][7]=this.convertSeconds(temp.getWaitingtimepoli()+temp.getWaitingtimepoli2()+temp.getWaitingtimepoli3());
                i++;
             }
             else{
                System.out.println("kasus emergency :"+temp.getArrivaltime());
                data[i][0]=temp.getNumberinpoli();
                data[i][1]=temp.getJenis();
                data[i][2]=this.convertSeconds(temp.getArrivaltimepoli3());
                System.out.println("cek generate 1 :"+" "+this.convertSeconds(temp.getArrivaltimepoli())+" "+this.convertSeconds(temp.getServicetimepoli())+" "+this.convertSeconds(temp.getTimeServiceBegin2())+" "+this.convertSeconds(temp.getDelaytimepoli())+" "+this.convertSeconds(temp.getWaitingtimepoli())+" "+this.convertSeconds(temp.getTimeServiceEnd2()));
                System.out.println("cek generate 2 :"+" "+this.convertSeconds(temp.getArrivaltimepoli2())+" "+this.convertSeconds(temp.getServicetimepoli2())+" "+this.convertSeconds(temp.getTimeServiceBegin3())+" "+this.convertSeconds(temp.getDelaytimepoli2())+" "+this.convertSeconds(temp.getWaitingtimepoli2())+" "+this.convertSeconds(temp.getTimeServiceEnd3()));
                System.out.println("cek generate 3 :"+" "+this.convertSeconds(temp.getArrivaltimepoli3())+" "+this.convertSeconds(temp.getServicetimepoli3())+" "+this.convertSeconds(temp.getTimeServiceBegin4())+" "+this.convertSeconds(temp.getDelaytimepoli3())+" "+this.convertSeconds(temp.getWaitingtimepoli3())+" "+this.convertSeconds(temp.getTimeServiceEnd4()));
                data[i][3]=this.convertSeconds(temp.getServicetimepoli()+temp.getServicetimepoli2()+temp.getServicetimepoli3());
                data[i][4]=this.convertSeconds(temp.getTimeServiceBegin2());
                data[i][5]=this.convertSeconds(temp.getDelaytimepoli()+temp.getDelaytimepoli2()+temp.getDelaytimepoli3());
                data[i][6]=this.convertSeconds(temp.getTimeServiceEnd4());
                data[i][7]=this.convertSeconds(temp.getWaitingtimepoli()+temp.getWaitingtimepoli2()+temp.getWaitingtimepoli3());
                i++;
             }
          }
         return data;
    }
     
     public double generateSummaryArrivalTime(Customer[] queuecustomer){
          if(queuecustomer.length>0){
            double sumArrivalTime=queuecustomer[queuecustomer.length-1].getArrivaltime()/2.0;
            bd = new BigDecimal(((double)queuecustomer.length*1.0)/sumArrivalTime*1.0); 
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
          }
          else {
              return 0;
          }
      }
     
     public double getLastArrivalPoli(ServerAwal[] arrayServer){
         double lastarrivalpoli=arrayServer[0].getQueueReport2().get(arrayServer[0].getQueueSize()-1).getArrivaltimepoli();
         for(int i=0;i<arrayServer.length;i++){
             if(lastarrivalpoli<arrayServer[i].getQueueReport2().get(arrayServer[i].getQueueSize()-1).getArrivaltimepoli()){
                 lastarrivalpoli=arrayServer[i].getQueueReport2().get(arrayServer[i].getQueueSize()-1).getArrivaltimepoli();
             }
         }
         return lastarrivalpoli;
     }
     
     public LinkedList<int[]> getPasienCounter(ServerAwal[] arrayServer){
         LinkedList<int[]> counterpasien=new LinkedList<int[]>();
         for(int i=0;i<arrayServer.length;i++){
             counterpasien.add(this.getPasienCounter(arrayServer[i].getQueueReport2()));
         }
         return counterpasien;
    }
     
    public LinkedList<int[]> getPasienCounter(ExcelInputSimulation.ServerAwal[] arrayServer){
         LinkedList<int[]> counterpasien=new LinkedList<int[]>();
         for(int i=0;i<arrayServer.length;i++){
             counterpasien.add(this.getPasienCounter(arrayServer[i].getQueueReport2()));
         }
         return counterpasien;
    }
     
     public int[] getPasienCounter(LinkedList<Customer> customer){
         int[] pasien=new int[3];
         pasien[0]=0;
         pasien[1]=0;
         pasien[2]=0;
         for(int i=0;i<customer.size();i++){
             if(customer.get(i).getJenis().equals("BPJS Lama")){
                 pasien[0]++;
             }
             else if(customer.get(i).getJenis().equals("BPJS Baru")){
                 pasien[1]++;
             }
             else if(customer.get(i).getJenis().equals("Emergency")){
                 pasien[2]++;
             }
         }
         return pasien;
     }
     
     public LinkedList<int[]> getPasienDelayTime(ServerAwal[] arrayServer){
         LinkedList<int[]> pasien=new LinkedList<int[]>();
         for(int i=0;i<arrayServer.length;i++){
             pasien.add(this.getPasienDelayTime(arrayServer[i].getQueueReport2()));
         }
         return pasien;
     }
     
      public LinkedList<int[]> getPasienDelayTime(ExcelInputSimulation.ServerAwal[] arrayServer){
         LinkedList<int[]> pasien=new LinkedList<int[]>();
         for(int i=0;i<arrayServer.length;i++){
             pasien.add(this.getPasienDelayTime(arrayServer[i].getQueueReport2()));
         }
         return pasien;
     }
     
     public int[] getPasienDelayTime(LinkedList<Customer> customer){
         int[] pasien=new int[3];
         pasien[0]=0;
         pasien[1]=0;
         pasien[2]=0;
         for(int i=0;i<customer.size();i++){
             if(customer.get(i).getJenis().equals("BPJS Lama")){
                 pasien[0]+=this.convertSecondsForChart(customer.get(i).getDelaytime());
             }
             else if(customer.get(i).getJenis().equals("BPJS Baru")){
                 pasien[1]+=this.convertSecondsForChart(customer.get(i).getDelaytime());
             }
             else if(customer.get(i).getJenis().equals("Emergency")){
                 pasien[2]+=this.convertSecondsForChart(customer.get(i).getDelaytime());
             }
         }
         return pasien;
     }
     
      public LinkedList<int[]> getPasienDelayTimePetugas(ServerPetugas[] arrayServer){
         LinkedList<int[]> pasien=new LinkedList<int[]>();
         for(int i=0;i<arrayServer.length;i++){
             pasien.add(this.getPasienDelayTimePetugas(arrayServer[i].getQueueReport2()));
         }
         return pasien;
     }
      
      public LinkedList<int[]> getPasienDelayTimePetugas(ExcelInputSimulation.ServerPetugas[] arrayServer){
         LinkedList<int[]> pasien=new LinkedList<int[]>();
         for(int i=0;i<arrayServer.length;i++){
             pasien.add(this.getPasienDelayTimePetugas(arrayServer[i].getQueueReport2()));
         }
         return pasien;
     }
     
     public int[] getPasienDelayTimePetugas(LinkedList<Customer> customer){
         int[] pasien=new int[3];
         pasien[0]=0;
         pasien[1]=0;
         pasien[2]=0;
         for(int i=0;i<customer.size();i++){
             if(customer.get(i).getJenis().equals("BPJS Lama")){
                 pasien[0]+=this.convertSecondsForChart(customer.get(i).getDelaytimepoli());
             }
             else if(customer.get(i).getJenis().equals("BPJS Baru")){
                 pasien[1]+=this.convertSecondsForChart(customer.get(i).getDelaytimepoli());
             }
             else if(customer.get(i).getJenis().equals("Emergency")){
                 pasien[2]+=this.convertSecondsForChart(customer.get(i).getDelaytimepoli());
             }
         }
         return pasien;
     }
     
       public LinkedList<int[]> getPasienDelayTimePerawat(ServerPerawat[] arrayServer){
         LinkedList<int[]> pasien=new LinkedList<int[]>();
         for(int i=0;i<arrayServer.length;i++){
             pasien.add(this.getPasienDelayTimePerawat(arrayServer[i].getQueueReport2()));
         }
         return pasien;
     }
       
        public LinkedList<int[]> getPasienDelayTimePerawat(ExcelInputSimulation.ServerPerawat[] arrayServer){
         LinkedList<int[]> pasien=new LinkedList<int[]>();
         for(int i=0;i<arrayServer.length;i++){
             pasien.add(this.getPasienDelayTimePerawat(arrayServer[i].getQueueReport2()));
         }
         return pasien;
     }
     
     public int[] getPasienDelayTimePerawat(LinkedList<Customer> customer){
         int[] pasien=new int[3];
         pasien[0]=0;
         pasien[1]=0;
         pasien[2]=0;
         for(int i=0;i<customer.size();i++){
             if(customer.get(i).getJenis().equals("BPJS Lama")){
                 pasien[0]+=this.convertSecondsForChart(customer.get(i).getDelaytimepoli2());
             }
             else if(customer.get(i).getJenis().equals("BPJS Baru")){
                 pasien[1]+=this.convertSecondsForChart(customer.get(i).getDelaytimepoli2());
             }
             else if(customer.get(i).getJenis().equals("Emergency")){
                 pasien[2]+=this.convertSecondsForChart(customer.get(i).getDelaytimepoli2());
             }
         }
         return pasien;
     }
     
    public LinkedList<int[]> getPasienDelayTimeDokter(ServerDokter[] arrayServer){
         LinkedList<int[]> pasien=new LinkedList<int[]>();
         for(int i=0;i<arrayServer.length;i++){
             pasien.add(this.getPasienDelayTimeDokter(arrayServer[i].getQueueReport2()));
         }
         return pasien;
     }
       
    public LinkedList<int[]> getPasienDelayTimeDokter(ExcelInputSimulation.ServerDokter[] arrayServer){
         LinkedList<int[]> pasien=new LinkedList<int[]>();
         for(int i=0;i<arrayServer.length;i++){
             pasien.add(this.getPasienDelayTimeDokter(arrayServer[i].getQueueReport2()));
         }
         return pasien;
     }
     
     public int[] getPasienDelayTimeDokter(LinkedList<Customer> customer){
         int[] pasien=new int[3];
         pasien[0]=0;
         pasien[1]=0;
         pasien[2]=0;
         for(int i=0;i<customer.size();i++){
             if(customer.get(i).getJenis().equals("BPJS Lama")){
                 pasien[0]+=this.convertSecondsForChart(customer.get(i).getDelaytimepoli3());
             }
             else if(customer.get(i).getJenis().equals("BPJS Baru")){
                 pasien[1]+=this.convertSecondsForChart(customer.get(i).getDelaytimepoli3());
             }
             else if(customer.get(i).getJenis().equals("Emergency")){
                 pasien[2]+=this.convertSecondsForChart(customer.get(i).getDelaytimepoli3());
             }
         }
         return pasien;
     }
     
     public int getMaxArrivalTimePoli(ServerAwal[] arrayServer){
         double max=this.getMaxArrivalTimeCustomer(arrayServer[0].getQueueReport2());
         for(int i=1;i<arrayServer.length;i++){
             if(max<this.getMaxArrivalTimeCustomer(arrayServer[i].getQueueReport2())){
                 max=this.getMaxArrivalTimeCustomer(arrayServer[i].getQueueReport2());
             }
         }
         int max2=this.convertSecondsForChart(max);
         return max2;
     }
     
      public int getMaxArrivalTimePoli(ExcelInputSimulation.ServerAwal[] arrayServer){
         double max=this.getMaxArrivalTimeCustomer(arrayServer[0].getQueueReport2());
         for(int i=1;i<arrayServer.length;i++){
             if(max<this.getMaxArrivalTimeCustomer(arrayServer[i].getQueueReport2())){
                 max=this.getMaxArrivalTimeCustomer(arrayServer[i].getQueueReport2());
             }
         }
         int max2=this.convertSecondsForChart(max);
         return max2;
     }
     
     public double getMaxArrivalTimeCustomer(LinkedList<Customer> cust){
         double cari=0;
         if(cust.size()>0){
            cari=cust.get(0).getArrivaltimepoli();
            for(int i=1;i<cust.size();i++){
                if(cari<cust.get(i).getArrivaltimepoli()){
                    cari=cust.get(i).getArrivaltimepoli();
                }
            }
         }
         return cari;
     }
     
      public int getMaxArrivalTimePoli2(ServerPetugas[] arrayServer){
            double max=this.getMaxArrivalTimeCustomer2(arrayServer[0].getQueueReport2());
            System.out.println("max petugas 0 : "+max);
            for(int i=1;i<arrayServer.length;i++){
                if(max<this.getMaxArrivalTimeCustomer2(arrayServer[i].getQueueReport2())){
                    max=this.getMaxArrivalTimeCustomer2(arrayServer[i].getQueueReport2());
                    System.out.println("max petugas "+i+" : "+max);
                }
            }
            System.out.println("max petugas last : "+max);
            int max2=this.convertSecondsForChart(max);
            return max2;
     }
      
      public int getMaxArrivalTimePoli2(ExcelInputSimulation.ServerPetugas[] arrayServer){
            double max=this.getMaxArrivalTimeCustomer2(arrayServer[0].getQueueReport2());
            for(int i=1;i<arrayServer.length;i++){
                if(max<this.getMaxArrivalTimeCustomer2(arrayServer[i].getQueueReport2())){
                    max=this.getMaxArrivalTimeCustomer2(arrayServer[i].getQueueReport2());
                }
            }
            int max2=this.convertSecondsForChart(max);
            return max2;
     }
      
       public double getMaxArrivalTimeCustomer2(LinkedList<Customer> cust){
         double cari=0;
         if(cust.size()>0){
            cari=cust.get(0).getArrivaltimepoli2();
            for(int i=1;i<cust.size();i++){
                if(cari<cust.get(i).getArrivaltimepoli2()){
                    cari=cust.get(i).getArrivaltimepoli2();
                }
            }
         }
         System.out.println("cari 2 : "+cari);
         return cari;
     }
       
       public int getMaxArrivalTimePoli3(ServerPerawat[] arrayServer){
            double max=this.getMaxArrivalTimeCustomer3(arrayServer[0].getQueueReport2());
            System.out.println("max perawat 0 : "+max);
            for(int i=1;i<arrayServer.length;i++){
                if(max<this.getMaxArrivalTimeCustomer3(arrayServer[i].getQueueReport2())){
                    max=this.getMaxArrivalTimeCustomer3(arrayServer[i].getQueueReport2());
                    System.out.println("max perawat "+i+" : "+max);
                }
            }
            System.out.println("max perawat last : "+max);
            int max2=this.convertSecondsForChart(max);
            return max2;
     }
       
        public int getMaxArrivalTimePoli3(ExcelInputSimulation.ServerPerawat[] arrayServer){
            double max=this.getMaxArrivalTimeCustomer3(arrayServer[0].getQueueReport2());
            for(int i=1;i<arrayServer.length;i++){
                if(max<this.getMaxArrivalTimeCustomer3(arrayServer[i].getQueueReport2())){
                    max=this.getMaxArrivalTimeCustomer3(arrayServer[i].getQueueReport2());
                }
            }
            int max2=this.convertSecondsForChart(max);
            return max2;
     }
      
       public double getMaxArrivalTimeCustomer3(LinkedList<Customer> cust){
         double cari=0;
         if(cust.size()>0){
            cari=cust.get(0).getArrivaltimepoli3();
            for(int i=1;i<cust.size();i++){
                if(cari<cust.get(i).getArrivaltimepoli3()){
                    cari=cust.get(i).getArrivaltimepoli3();
                }
            }
         }
         System.out.println("cari 3 : "+cari);
         return cari;
     }
       
    public int getMaxArrivalTimePoli4(ServerDokter[] arrayServer){
        if(arrayServer.length>1){
            double max=this.getMaxArrivalTimeCustomer4(arrayServer[0].getQueueReport2());
            System.out.println("max dokter 0 : "+max);
            for(int i=1;i<arrayServer.length;i++){
                if(max<this.getMaxArrivalTimeCustomer4(arrayServer[i].getQueueReport2())){
                    max=this.getMaxArrivalTimeCustomer4(arrayServer[i].getQueueReport2());
                    System.out.println("max petugas "+i+" : "+max);
                }
            }
            System.out.println("max dokter last : "+max);
            int max2=this.convertSecondsForChart(max);
            System.out.println("cari 4 : "+max);
            return max2;
        }
        else{
            double max=this.getMaxArrivalTimeCustomer4(arrayServer[0].getQueueReport2());
            System.out.println("cari 4 : "+max);
            return this.convertSecondsForChart(max);
        }
     }
    
    public int getMaxArrivalTimePoli4(ExcelInputSimulation.ServerDokter[] arrayServer){
        if(arrayServer.length>1){
            double max=this.getMaxArrivalTimeCustomer4(arrayServer[0].getQueueReport2());
            for(int i=1;i<arrayServer.length;i++){
                if(max<this.getMaxArrivalTimeCustomer4(arrayServer[i].getQueueReport2())){
                    max=this.getMaxArrivalTimeCustomer4(arrayServer[i].getQueueReport2());
                }
            }
            int max2=this.convertSecondsForChart(max);
            return max2;
        }
        else{
            double max=this.getMaxArrivalTimeCustomer4(arrayServer[0].getQueueReport2());
            return this.convertSecondsForChart(max);
        }
     }
      
       public double getMaxArrivalTimeCustomer4(LinkedList<Customer> cust){
         double cari=0;
         if(cust.size()>0){
            cari=cust.get(0).getTimeServiceEnd4();
            for(int i=1;i<cust.size();i++){
                if(cari<cust.get(i).getTimeServiceEnd4()){
                    cari=cust.get(i).getTimeServiceEnd4();
                }
            }
         }
         return cari;
     }
     
     public LinkedList<double[]> getWaitingTimeServer(ServerAwal[] arrayServer){
         LinkedList<double[]> customer=new LinkedList<double[]>();
         for(int i=0;i<arrayServer.length;i++){
             double[] waiting=new double[2];
             waiting[0]=this.convertSecondsForChart(arrayServer[i].getTotalWaitingTimeBPJSLama());
             System.out.println("waiting time lama : "+arrayServer[i].getTotalWaitingTimeBPJSLama());
             waiting[1]=this.convertSecondsForChart(arrayServer[i].getTotalWaitingTimeBPJSBaru());
             System.out.println("waiting time baru : "+arrayServer[i].getTotalWaitingTimeBPJSBaru());
             customer.add(waiting);
         }
         return customer;
     }
     
     public LinkedList<double[]> getWaitingTimeServer(ExcelInputSimulation.ServerAwal[] arrayServer){
         LinkedList<double[]> customer=new LinkedList<double[]>();
         for(int i=0;i<arrayServer.length;i++){
             double[] waiting=new double[2];
             waiting[0]=this.convertSecondsForChart(arrayServer[i].getTotalWaitingTimeBPJSLama());
             System.out.println("waiting time lama : "+arrayServer[i].getTotalWaitingTimeBPJSLama());
             waiting[1]=this.convertSecondsForChart(arrayServer[i].getTotalWaitingTimeBPJSBaru());
             System.out.println("waiting time baru : "+arrayServer[i].getTotalWaitingTimeBPJSBaru());
             customer.add(waiting);
         }
         return customer;
     }
     
      public LinkedList<int[]> getWaitingTimeServerPetugas(ServerPetugas[] arrayServer){
         LinkedList<int[]> customer=new LinkedList<int[]>();
         for(int i=0;i<arrayServer.length;i++){
             customer.add(this.getWaitingTimePetugas(arrayServer[i].getQueueReport2()));
         }
         return customer;
         
     }
      
     public LinkedList<int[]> getWaitingTimeServerPetugas(ExcelInputSimulation.ServerPetugas[] arrayServer){
         LinkedList<int[]> customer=new LinkedList<int[]>();
         for(int i=0;i<arrayServer.length;i++){
             customer.add(this.getWaitingTimePetugas(arrayServer[i].getQueueReport2()));
         }
         return customer;
         
     }
      
      public int[] getWaitingTimePetugas(LinkedList<Customer> customer){
         int[] pasien=new int[3];
         pasien[0]=0;
         pasien[1]=0;
         pasien[2]=0;
         for(int i=0;i<customer.size();i++){
             if(customer.get(i).getJenis().equals("BPJS Lama")){
                 pasien[0]+=this.convertSecondsForChart(customer.get(i).getWaitingtimepoli());
             }
             else if(customer.get(i).getJenis().equals("BPJS Baru")){
                 pasien[1]+=this.convertSecondsForChart(customer.get(i).getWaitingtimepoli());
             }
             else if(customer.get(i).getJenis().equals("Emergency")){
                 pasien[2]+=this.convertSecondsForChart(customer.get(i).getWaitingtimepoli());
             }
         }
         return pasien;
     }
      
      public LinkedList<int[]> getWaitingTimeServerPerawat(ServerPerawat[] arrayServer){
         LinkedList<int[]> customer=new LinkedList<int[]>();
         for(int i=0;i<arrayServer.length;i++){
             customer.add(this.getWaitingTimePerawat(arrayServer[i].getQueueReport2()));
         }
         return customer;
         
     }
      
       public LinkedList<int[]> getWaitingTimeServerPerawat(ExcelInputSimulation.ServerPerawat[] arrayServer){
         LinkedList<int[]> customer=new LinkedList<int[]>();
         for(int i=0;i<arrayServer.length;i++){
             customer.add(this.getWaitingTimePerawat(arrayServer[i].getQueueReport2()));
         }
         return customer;
         
     }
      
      public int[] getWaitingTimePerawat(LinkedList<Customer> customer){
         int[] pasien=new int[3];
         pasien[0]=0;
         pasien[1]=0;
         pasien[2]=0;
         for(int i=0;i<customer.size();i++){
             if(customer.get(i).getJenis().equals("BPJS Lama")){
                 pasien[0]+=this.convertSecondsForChart(customer.get(i).getWaitingtimepoli2());
             }
             else if(customer.get(i).getJenis().equals("BPJS Baru")){
                 pasien[1]+=this.convertSecondsForChart(customer.get(i).getWaitingtimepoli2());
             }
             else if(customer.get(i).getJenis().equals("Emergency")){
                 pasien[2]+=this.convertSecondsForChart(customer.get(i).getWaitingtimepoli2());
             }
         }
         return pasien;
     }
      
    public LinkedList<int[]> getWaitingTimeServerDokter(ServerDokter[] arrayServer){
         LinkedList<int[]> customer=new LinkedList<int[]>();
         for(int i=0;i<arrayServer.length;i++){
             customer.add(this.getWaitingTimeDokter(arrayServer[i].getQueueReport2()));
         }
         return customer;
         
     }
      
     public LinkedList<int[]> getWaitingTimeServerDokter(ExcelInputSimulation.ServerDokter[] arrayServer){
         LinkedList<int[]> customer=new LinkedList<int[]>();
         for(int i=0;i<arrayServer.length;i++){
             customer.add(this.getWaitingTimeDokter(arrayServer[i].getQueueReport2()));
         }
         return customer;
         
     }
      
      public int[] getWaitingTimeDokter(LinkedList<Customer> customer){
         int[] pasien=new int[3];
         pasien[0]=0;
         pasien[1]=0;
         pasien[2]=0;
         for(int i=0;i<customer.size();i++){
             if(customer.get(i).getJenis().equals("BPJS Lama")){
                 pasien[0]+=this.convertSecondsForChart(customer.get(i).getWaitingtimepoli3());
             }
             else if(customer.get(i).getJenis().equals("BPJS Baru")){
                 pasien[1]+=this.convertSecondsForChart(customer.get(i).getWaitingtimepoli3());
             }
             else if(customer.get(i).getJenis().equals("Emergency")){
                 pasien[2]+=this.convertSecondsForChart(customer.get(i).getWaitingtimepoli3());
             }
         }
         return pasien;
     }
      
      
    public LinkedList<int[]> getServiceTimeServerPetugas(ServerPetugas[] arrayServer){
         LinkedList<int[]> customer=new LinkedList<int[]>();
         for(int i=0;i<arrayServer.length;i++){
             customer.add(this.getServiceTimePetugas(arrayServer[i].getQueueReport2()));
         }
         return customer;
         
     }
       
    public LinkedList<int[]> getServiceTimeServerPetugas(ExcelInputSimulation.ServerPetugas[] arrayServer){
         LinkedList<int[]> customer=new LinkedList<int[]>();
         for(int i=0;i<arrayServer.length;i++){
             customer.add(this.getServiceTimePetugas(arrayServer[i].getQueueReport2()));
         }
         return customer;
         
     }
      
      public int[] getServiceTimePetugas(LinkedList<Customer> customer){
         int[] pasien=new int[3];
         pasien[0]=0;
         pasien[1]=0;
         pasien[2]=0;
         for(int i=0;i<customer.size();i++){
             if(customer.get(i).getJenis().equals("BPJS Lama")){
                 pasien[0]+=this.convertSecondsForChart(customer.get(i).getServicetimepoli());
             }
             else if(customer.get(i).getJenis().equals("BPJS Baru")){
                 pasien[1]+=this.convertSecondsForChart(customer.get(i).getServicetimepoli());
             }
             else if(customer.get(i).getJenis().equals("Emergency")){
                 pasien[2]+=this.convertSecondsForChart(customer.get(i).getServicetimepoli());
             }
         }
         return pasien;
     }
      
       public LinkedList<int[]> getServiceTimeServerPerawat(ServerPerawat[] arrayServer){
         LinkedList<int[]> customer=new LinkedList<int[]>();
         for(int i=0;i<arrayServer.length;i++){
             customer.add(this.getServiceTimePerawat(arrayServer[i].getQueueReport2()));
         }
         return customer;
         
     }
       
    public LinkedList<int[]> getServiceTimeServerPerawat(ExcelInputSimulation.ServerPerawat[] arrayServer){
         LinkedList<int[]> customer=new LinkedList<int[]>();
         for(int i=0;i<arrayServer.length;i++){
             customer.add(this.getServiceTimePerawat(arrayServer[i].getQueueReport2()));
         }
         return customer;
         
     }
      
      public int[] getServiceTimePerawat(LinkedList<Customer> customer){
         int[] pasien=new int[3];
         pasien[0]=0;
         pasien[1]=0;
         pasien[2]=0;
         for(int i=0;i<customer.size();i++){
             if(customer.get(i).getJenis().equals("BPJS Lama")){
                 pasien[0]+=this.convertSecondsForChart(customer.get(i).getServicetimepoli2());
             }
             else if(customer.get(i).getJenis().equals("BPJS Baru")){
                 pasien[1]+=this.convertSecondsForChart(customer.get(i).getServicetimepoli2());
             }
             else if(customer.get(i).getJenis().equals("Emergency")){
                 pasien[2]+=this.convertSecondsForChart(customer.get(i).getServicetimepoli2());
             }
         }
         return pasien;
     }
      
    public LinkedList<int[]> getServiceTimeServerDokter(ServerDokter[] arrayServer){
         LinkedList<int[]> customer=new LinkedList<int[]>();
         for(int i=0;i<arrayServer.length;i++){
             customer.add(this.getServiceTimeDokter(arrayServer[i].getQueueReport2()));
         }
         return customer;
         
     }
    
    public LinkedList<int[]> getServiceTimeServerDokter(ExcelInputSimulation.ServerDokter[] arrayServer){
         LinkedList<int[]> customer=new LinkedList<int[]>();
         for(int i=0;i<arrayServer.length;i++){
             customer.add(this.getServiceTimeDokter(arrayServer[i].getQueueReport2()));
         }
         return customer;
         
     }
      
      public int[] getServiceTimeDokter(LinkedList<Customer> customer){
         int[] pasien=new int[3];
         pasien[0]=0;
         pasien[1]=0;
         pasien[2]=0;
         for(int i=0;i<customer.size();i++){
             if(customer.get(i).getJenis().equals("BPJS Lama")){
                 pasien[0]+=this.convertSecondsForChart(customer.get(i).getServicetimepoli3());
             }
             else if(customer.get(i).getJenis().equals("BPJS Baru")){
                 pasien[1]+=this.convertSecondsForChart(customer.get(i).getServicetimepoli3());
             }
             else if(customer.get(i).getJenis().equals("Emergency")){
                 pasien[2]+=this.convertSecondsForChart(customer.get(i).getServicetimepoli3());
             }
         }
         return pasien;
     }
      
     
     public LinkedList<double[]> getServiceTimeServer(ServerAwal[] arrayServer){
         LinkedList<double[]> customer=new LinkedList<double[]>();
         for(int i=0;i<arrayServer.length;i++){
             double[] service=new double[2];
             service[0]=this.convertSecondsForChart(arrayServer[i].getTotalServiceTimeBPJSLama());
             System.out.println("waiting time lama : "+arrayServer[i].getTotalServiceTimeBPJSLama());
             service[1]=this.convertSecondsForChart(arrayServer[i].getTotalServiceTimeBPJSBaru());
             System.out.println("waiting time baru : "+arrayServer[i].getTotalServiceTimeBPJSBaru());
             customer.add(service);
         }
         return customer;   
     }
     
      public LinkedList<double[]> getServiceTimeServer(ExcelInputSimulation.ServerAwal[] arrayServer){
         LinkedList<double[]> customer=new LinkedList<double[]>();
         for(int i=0;i<arrayServer.length;i++){
             double[] service=new double[2];
             service[0]=this.convertSecondsForChart(arrayServer[i].getTotalServiceTimeBPJSLama());
             System.out.println("waiting time lama : "+arrayServer[i].getTotalServiceTimeBPJSLama());
             service[1]=this.convertSecondsForChart(arrayServer[i].getTotalServiceTimeBPJSBaru());
             System.out.println("waiting time baru : "+arrayServer[i].getTotalServiceTimeBPJSBaru());
             customer.add(service);
         }
         return customer;   
     }
     
     public LinkedList<int[]> getCounterPasienPetugas(ServerPetugas[] serverpetugas){
         LinkedList<int[]> counter=new LinkedList<int[]>();
         for(int i=0;i<serverpetugas.length;i++){
             counter.add(this.getPasienCounter(serverpetugas[i].getQueueReport2()));
         }
         return counter;
     }
     
     
     public LinkedList<int[]> getCounterPasienPetugas(ExcelInputSimulation.ServerPetugas[] serverpetugas){
         LinkedList<int[]> counter=new LinkedList<int[]>();
         for(int i=0;i<serverpetugas.length;i++){
             counter.add(this.getPasienCounter(serverpetugas[i].getQueueReport2()));
         }
         return counter;
     }
     
    
     
      public LinkedList<int[]> getCounterPasienPerawat(ServerPerawat[] serverperawat){
         LinkedList<int[]> counter=new LinkedList<int[]>();
         for(int i=0;i<serverperawat.length;i++){
            counter.add(this.getPasienCounter(serverperawat[i].getQueueReport2()));
         }
         return counter;
     }
      
       public LinkedList<int[]> getCounterPasienPerawat(ExcelInputSimulation.ServerPerawat[] serverperawat){
         LinkedList<int[]> counter=new LinkedList<int[]>();
         for(int i=0;i<serverperawat.length;i++){
            counter.add(this.getPasienCounter(serverperawat[i].getQueueReport2()));
         }
         return counter;
     }
      
   public LinkedList<int[]> getCounterPasienDokter(ServerDokter[] serverdokter){
         LinkedList<int[]> counter=new LinkedList<int[]>();
         for(int i=0;i<serverdokter.length;i++){
             counter.add(this.getPasienCounter(serverdokter[i].getQueueReport2()));
         }
         return counter;
     }
       
    public LinkedList<int[]> getCounterPasienDokter(ExcelInputSimulation.ServerDokter[] serverdokter){
         LinkedList<int[]> counter=new LinkedList<int[]>();
         for(int i=0;i<serverdokter.length;i++){
             counter.add(this.getPasienCounter(serverdokter[i].getQueueReport2()));
         }
         return counter;
     }
     
     public LinkedList<int[]> getCounterPasienperServer(ServerAwal[] arrayServer){
        int max=getMaxArrivalTimePoli(arrayServer);
        System.out.println(" max poli :"+max);
        LinkedList<int[]> realcounter=new LinkedList<int[]>();
        int begin=0;
        if(max%5!=0){
            int temp=max;
            int mod=max%5;
            int kurang=5-mod;
            max=temp+kurang;
        }
        while(begin<=(max)){
            int i=0;
            int[] counterpasien=new int[3];
            while(i<arrayServer.length){
                LinkedList<Customer> custtemp=arrayServer[i].getQueueReport2();
                for(int k=0;k<custtemp.size();k++){
                    if(custtemp.get(k).getArrivaltimepoli()<=begin){
                        if(custtemp.get(k).getJenis().equals("BPJS Lama")){
                            System.out.println("lama generate : "+custtemp.get(k).getArrivaltimepoli()+" begin : "+begin);
                            counterpasien[0]++;
                            System.out.println("Counter pasien lama : "+counterpasien[0]);
                        }
                        else if(custtemp.get(k).getJenis().equals("BPJS Baru")){
                            System.out.println("baru generate : "+custtemp.get(k).getArrivaltimepoli()+" begin : "+begin);
                            counterpasien[1]++;
                            System.out.println("Counter pasien baru : "+counterpasien[1]);
                        }
                        else{
                            counterpasien[2]++;
                        }
                    }
                    if(custtemp.get(k).getArrivaltimepoli()>begin){
                        k=custtemp.size();
                    }
                }
                i++;
                System.out.println(" i ke "+i);
            }
            System.out.println("counter pasien b4 queue : "+counterpasien[0]+" , "+counterpasien[1]);
            realcounter.add(counterpasien);
            for(int k=0;k<realcounter.size();k++){
                System.out.println("cek realcounter dlm loop  : "+realcounter.get(k)[0]+" "+realcounter.get(k)[1]);
            }
            System.out.println("First element : "+realcounter.getFirst()[0]+" "+realcounter.getFirst()[1]);
            System.out.println("Size counter : "+realcounter.size());
            begin+=5;
        }
        for(int i=0;i<realcounter.size();i++){
            System.out.println("cek realcounter : "+realcounter.get(i)[0]+" "+realcounter.get(i)[1]);
        }
        int[] counterpasien1=realcounter.get(0);
        int compare1=counterpasien1[0];
        int compare2=counterpasien1[1];
        System.out.println("comp1 0 :"+compare1);
        System.out.println("comp1 1 :"+compare2);
        LinkedList<int[]> realcounter2=new LinkedList<int[]>();
        realcounter2.add(counterpasien1);
        for(int k=1;k<realcounter.size();k++){
            int[] temp=realcounter.get(k);
            int x=temp[0];
            int y=temp[1];
            System.out.println("temp 00 :"+temp[0]);
            System.out.println("temp 11 :"+temp[1]);
            temp[0]=x-compare1;
            temp[1]=y-compare2;
            System.out.println("temp 0 :"+temp[0]);
            System.out.println("temp 1 :"+temp[1]);
            realcounter2.add(temp);
            compare1=x;
            compare2=y;
            System.out.println("comp 0 :"+compare1);
            System.out.println("comp 1 :"+compare2);
        }
        return realcounter2;
         
     }
     
     public LinkedList<int[]> getCounterPasienperServer(ExcelInputSimulation.ServerAwal[] arrayServer){
        int max=getMaxArrivalTimePoli(arrayServer);
        System.out.println(" max poli :"+max);
        LinkedList<int[]> realcounter=new LinkedList<int[]>();
        int begin=0;
        if(max%5!=0){
            int temp=max;
            int mod=max%5;
            int kurang=5-mod;
            max=temp+kurang;
        }
        while(begin<=(max)){
            int i=0;
            int[] counterpasien=new int[3];
            while(i<arrayServer.length){
                LinkedList<Customer> custtemp=arrayServer[i].getQueueReport2();
                for(int k=0;k<custtemp.size();k++){
                    if(custtemp.get(k).getArrivaltimepoli()<=begin){
                        if(custtemp.get(k).getJenis().equals("BPJS Lama")){
                            System.out.println("lama generate : "+custtemp.get(k).getArrivaltimepoli()+" begin : "+begin);
                            counterpasien[0]++;
                            System.out.println("Counter pasien lama : "+counterpasien[0]);
                        }
                        else if(custtemp.get(k).getJenis().equals("BPJS Baru")){
                            System.out.println("baru generate : "+custtemp.get(k).getArrivaltimepoli()+" begin : "+begin);
                            counterpasien[1]++;
                            System.out.println("Counter pasien baru : "+counterpasien[1]);
                        }
                        else{
                            counterpasien[2]++;
                        }
                    }
                    if(custtemp.get(k).getArrivaltimepoli()>begin){
                        k=custtemp.size();
                    }
                }
                i++;
                System.out.println(" i ke "+i);
            }
            System.out.println("counter pasien b4 queue : "+counterpasien[0]+" , "+counterpasien[1]);
            realcounter.add(counterpasien);
            for(int k=0;k<realcounter.size();k++){
                System.out.println("cek realcounter dlm loop  : "+realcounter.get(k)[0]+" "+realcounter.get(k)[1]);
            }
            System.out.println("First element : "+realcounter.getFirst()[0]+" "+realcounter.getFirst()[1]);
            System.out.println("Size counter : "+realcounter.size());
            begin+=5;
        }
        for(int i=0;i<realcounter.size();i++){
            System.out.println("cek realcounter : "+realcounter.get(i)[0]+" "+realcounter.get(i)[1]);
        }
        int[] counterpasien1=realcounter.get(0);
        int compare1=counterpasien1[0];
        int compare2=counterpasien1[1];
        System.out.println("comp1 0 :"+compare1);
        System.out.println("comp1 1 :"+compare2);
        LinkedList<int[]> realcounter2=new LinkedList<int[]>();
        realcounter2.add(counterpasien1);
        for(int k=1;k<realcounter.size();k++){
            int[] temp=realcounter.get(k);
            int x=temp[0];
            int y=temp[1];
            System.out.println("temp 00 :"+temp[0]);
            System.out.println("temp 11 :"+temp[1]);
            temp[0]=x-compare1;
            temp[1]=y-compare2;
            System.out.println("temp 0 :"+temp[0]);
            System.out.println("temp 1 :"+temp[1]);
            realcounter2.add(temp);
            compare1=x;
            compare2=y;
            System.out.println("comp 0 :"+compare1);
            System.out.println("comp 1 :"+compare2);
        }
        return realcounter2;
         
     }
     
     public LinkedList<int[]> getCounterPasienperServer2(ServerPetugas[] arrayServer){
        int max=getMaxArrivalTimePoli2(arrayServer);
        System.out.println(" max poli 2 :"+max);
        LinkedList<int[]> realcounter=new LinkedList<int[]>();
        int begin=0;
        if(max%5!=0){
            int temp=max;
            int mod=max%5;
            int kurang=5-mod;
            max=temp+kurang;
        }
        max+=5;
        while(begin<=(max)){
            int i=0;
            int[] counterpasien=new int[3];
            while(i<arrayServer.length){
                LinkedList<Customer> custtemp=arrayServer[i].getQueueReport2();
                for(int k=0;k<custtemp.size();k++){
                    if(custtemp.get(k).getArrivaltimepoli2()<=begin){
                        if(custtemp.get(k).getJenis().equals("BPJS Lama")){
                            System.out.println("lama generate 2 : "+custtemp.get(k).getArrivaltimepoli2()+" begin : "+begin);
                            counterpasien[0]++;
                            System.out.println("Counter pasien lama  2: "+counterpasien[0]);
                        }
                        else if(custtemp.get(k).getJenis().equals("BPJS Baru")){
                            System.out.println("baru generate 2 : "+custtemp.get(k).getArrivaltimepoli2()+" begin : "+begin);
                            counterpasien[1]++;
                            System.out.println("Counter pasien baru 2 : "+counterpasien[1]);
                        }
                        else{
                            counterpasien[2]++;
                        }
                    }
                    if(custtemp.get(k).getArrivaltimepoli2()>begin){
                        k=custtemp.size();
                    }
                }
                i++;
                System.out.println(" i ke "+i);
            }
            System.out.println("counter pasien b4 queue 2 : "+counterpasien[0]+" , "+counterpasien[1]);
            realcounter.add(counterpasien);
            for(int k=0;k<realcounter.size();k++){
                System.out.println("cek realcounter dlm loop  2: "+realcounter.get(k)[0]+" "+realcounter.get(k)[1]);
            }
            System.out.println("First element 2: "+realcounter.getFirst()[0]+" "+realcounter.getFirst()[1]);
            System.out.println("Size counter 2: "+realcounter.size());
            begin+=5;
        }
        for(int i=0;i<realcounter.size();i++){
            System.out.println("cek realcounter 2: "+realcounter.get(i)[0]+" "+realcounter.get(i)[1]);
        }
        int[] counterpasien1=realcounter.get(0);
        int compare1=counterpasien1[0];
        int compare2=counterpasien1[1];
        System.out.println("comp1 0 2:"+compare1);
        System.out.println("comp1 1 2:"+compare2);
        LinkedList<int[]> realcounter2=new LinkedList<int[]>();
        realcounter2.add(counterpasien1);
        for(int k=1;k<realcounter.size();k++){
            int[] temp=realcounter.get(k);
            int x=temp[0];
            int y=temp[1];
            System.out.println("temp 00 2:"+temp[0]);
            System.out.println("temp 11 2:"+temp[1]);
            System.out.println("temp 22 2: "+temp[2]);
            temp[0]=x-compare1;
            temp[1]=y-compare2;
            System.out.println("temp 0 2:"+temp[0]);
            System.out.println("temp 1 2:"+temp[1]);
            realcounter2.add(temp);
            compare1=x;
            compare2=y;
            System.out.println("comp 0 2:"+compare1);
            System.out.println("comp 1 2:"+compare2);
        }
        return realcounter2;
         
     }
     
     public LinkedList<int[]> getCounterPasienperServer2(ExcelInputSimulation.ServerPetugas[] arrayServer){
        int max=getMaxArrivalTimePoli2(arrayServer);
        System.out.println(" max poli 2 :"+max);
        LinkedList<int[]> realcounter=new LinkedList<int[]>();
        int begin=0;
        if(max%5!=0){
            int temp=max;
            int mod=max%5;
            int kurang=5-mod;
            max=temp+kurang;
        }
        while(begin<=(max)){
            int i=0;
            int[] counterpasien=new int[3];
            while(i<arrayServer.length){
                LinkedList<Customer> custtemp=arrayServer[i].getQueueReport2();
                for(int k=0;k<custtemp.size();k++){
                    if(custtemp.get(k).getArrivaltimepoli2()<=begin){
                        if(custtemp.get(k).getJenis().equals("BPJS Lama")){
                            System.out.println("lama generate 2 : "+custtemp.get(k).getArrivaltimepoli2()+" begin : "+begin);
                            counterpasien[0]++;
                            System.out.println("Counter pasien lama  2: "+counterpasien[0]);
                        }
                        else if(custtemp.get(k).getJenis().equals("BPJS Baru")){
                            System.out.println("baru generate 2 : "+custtemp.get(k).getArrivaltimepoli2()+" begin : "+begin);
                            counterpasien[1]++;
                            System.out.println("Counter pasien baru 2 : "+counterpasien[1]);
                        }
                        else{
                            counterpasien[2]++;
                        }
                    }
                    if(custtemp.get(k).getArrivaltimepoli2()>begin){
                        k=custtemp.size();
                    }
                }
                i++;
                System.out.println(" i ke "+i);
            }
            System.out.println("counter pasien b4 queue 2 : "+counterpasien[0]+" , "+counterpasien[1]);
            realcounter.add(counterpasien);
            for(int k=0;k<realcounter.size();k++){
                System.out.println("cek realcounter dlm loop  2: "+realcounter.get(k)[0]+" "+realcounter.get(k)[1]);
            }
            System.out.println("First element 2: "+realcounter.getFirst()[0]+" "+realcounter.getFirst()[1]);
            System.out.println("Size counter 2: "+realcounter.size());
            begin+=5;
        }
        for(int i=0;i<realcounter.size();i++){
            System.out.println("cek realcounter 2: "+realcounter.get(i)[0]+" "+realcounter.get(i)[1]);
        }
        int[] counterpasien1=realcounter.get(0);
        int compare1=counterpasien1[0];
        int compare2=counterpasien1[1];
        System.out.println("comp1 0 2:"+compare1);
        System.out.println("comp1 1 2:"+compare2);
        LinkedList<int[]> realcounter2=new LinkedList<int[]>();
        realcounter2.add(counterpasien1);
        for(int k=1;k<realcounter.size();k++){
            int[] temp=realcounter.get(k);
            int x=temp[0];
            int y=temp[1];
            System.out.println("temp 00 2:"+temp[0]);
            System.out.println("temp 11 2:"+temp[1]);
            System.out.println("temp 22 2: "+temp[2]);
            temp[0]=x-compare1;
            temp[1]=y-compare2;
            System.out.println("temp 0 2:"+temp[0]);
            System.out.println("temp 1 2:"+temp[1]);
            realcounter2.add(temp);
            compare1=x;
            compare2=y;
            System.out.println("comp 0 2:"+compare1);
            System.out.println("comp 1 2:"+compare2);
        }
        return realcounter2;
         
     }
     
      public LinkedList<int[]> getCounterPasienperServer3(ServerPerawat[] arrayServer){
        int max=getMaxArrivalTimePoli3(arrayServer);
        System.out.println(" max poli 3:"+max);
        LinkedList<int[]> realcounter=new LinkedList<int[]>();
        int begin=0;
        if(max%5!=0){
            int temp=max;
            int mod=max%5;
            int kurang=5-mod;
            max=temp+kurang;
        }
        while(begin<=(max+5)){
            int i=0;
            int[] counterpasien=new int[3];
            while(i<arrayServer.length){
                LinkedList<Customer> custtemp=arrayServer[i].getQueueReport2();
                for(int k=0;k<custtemp.size();k++){
                    if(custtemp.get(k).getArrivaltimepoli3()<=begin){
                        if(custtemp.get(k).getJenis().equals("BPJS Lama")){
                            System.out.println("lama generate 3: "+custtemp.get(k).getArrivaltimepoli3()+" begin : "+begin);
                            counterpasien[0]++;
                            System.out.println("Counter pasien lama 3: "+counterpasien[0]);
                        }
                        else if(custtemp.get(k).getJenis().equals("BPJS Baru")){
                            System.out.println("baru generate 3: "+custtemp.get(k).getArrivaltimepoli3()+" begin : "+begin);
                            counterpasien[1]++;
                            System.out.println("Counter pasien baru 3: "+counterpasien[1]);
                        }
                        else{
                            counterpasien[2]++;
                        }
                    }
                    if(custtemp.get(k).getArrivaltimepoli3()>begin){
                        k=custtemp.size();
                    }
                }
                i++;
                System.out.println(" i ke "+i);
            }
            System.out.println("counter pasien b4 queue 3: "+counterpasien[0]+" , "+counterpasien[1]);
            realcounter.add(counterpasien);
            for(int k=0;k<realcounter.size();k++){
                System.out.println("cek realcounter dlm loop  3: "+realcounter.get(k)[0]+" "+realcounter.get(k)[1]);
            }
            System.out.println("First element 3: "+realcounter.getFirst()[0]+" "+realcounter.getFirst()[1]);
            System.out.println("Size counter 3: "+realcounter.size());
            begin+=5;
        }
        for(int i=0;i<realcounter.size();i++){
            System.out.println("cek realcounter 3: "+realcounter.get(i)[0]+" "+realcounter.get(i)[1]);
        }
        int[] counterpasien1=realcounter.get(0);
        int compare1=counterpasien1[0];
        int compare2=counterpasien1[1];
        System.out.println("comp1 0 3:"+compare1);
        System.out.println("comp1 1 3:"+compare2);
        LinkedList<int[]> realcounter2=new LinkedList<int[]>();
        realcounter2.add(counterpasien1);
        for(int k=1;k<realcounter.size();k++){
            int[] temp=realcounter.get(k);
            int x=temp[0];
            int y=temp[1];
            System.out.println("temp 00 3:"+temp[0]);
            System.out.println("temp 11 3:"+temp[1]);
            temp[0]=x-compare1;
            temp[1]=y-compare2;
            System.out.println("temp 0 3:"+temp[0]);
            System.out.println("temp 1 3:"+temp[1]);
            realcounter2.add(temp);
            compare1=x;
            compare2=y;
            System.out.println("comp 0 3:"+compare1);
            System.out.println("comp 1 3:"+compare2);
        }
        return realcounter2;
         
     }
      
      public LinkedList<int[]> getCounterPasienperServer3(ExcelInputSimulation.ServerPerawat[] arrayServer){
        int max=getMaxArrivalTimePoli3(arrayServer);
        System.out.println(" max poli 3:"+max);
        LinkedList<int[]> realcounter=new LinkedList<int[]>();
        int begin=0;
        if(max%5!=0){
            int temp=max;
            int mod=max%5;
            int kurang=5-mod;
            max=temp+kurang;
        }
        while(begin<=(max)){
            int i=0;
            int[] counterpasien=new int[3];
            while(i<arrayServer.length){
                LinkedList<Customer> custtemp=arrayServer[i].getQueueReport2();
                for(int k=0;k<custtemp.size();k++){
                    if(custtemp.get(k).getArrivaltimepoli3()<=begin){
                        if(custtemp.get(k).getJenis().equals("BPJS Lama")){
                            System.out.println("lama generate 3: "+custtemp.get(k).getArrivaltimepoli3()+" begin : "+begin);
                            counterpasien[0]++;
                            System.out.println("Counter pasien lama 3: "+counterpasien[0]);
                        }
                        else if(custtemp.get(k).getJenis().equals("BPJS Baru")){
                            System.out.println("baru generate 3: "+custtemp.get(k).getArrivaltimepoli3()+" begin : "+begin);
                            counterpasien[1]++;
                            System.out.println("Counter pasien baru 3: "+counterpasien[1]);
                        }
                        else{
                            counterpasien[2]++;
                        }
                    }
                    if(custtemp.get(k).getArrivaltimepoli3()>begin){
                        k=custtemp.size();
                    }
                }
                i++;
                System.out.println(" i ke "+i);
            }
            System.out.println("counter pasien b4 queue 3: "+counterpasien[0]+" , "+counterpasien[1]);
            realcounter.add(counterpasien);
            for(int k=0;k<realcounter.size();k++){
                System.out.println("cek realcounter dlm loop  3: "+realcounter.get(k)[0]+" "+realcounter.get(k)[1]);
            }
            System.out.println("First element 3: "+realcounter.getFirst()[0]+" "+realcounter.getFirst()[1]);
            System.out.println("Size counter 3: "+realcounter.size());
            begin+=5;
        }
        for(int i=0;i<realcounter.size();i++){
            System.out.println("cek realcounter 3: "+realcounter.get(i)[0]+" "+realcounter.get(i)[1]);
        }
        int[] counterpasien1=realcounter.get(0);
        int compare1=counterpasien1[0];
        int compare2=counterpasien1[1];
        System.out.println("comp1 0 3:"+compare1);
        System.out.println("comp1 1 3:"+compare2);
        LinkedList<int[]> realcounter2=new LinkedList<int[]>();
        realcounter2.add(counterpasien1);
        for(int k=1;k<realcounter.size();k++){
            int[] temp=realcounter.get(k);
            int x=temp[0];
            int y=temp[1];
            System.out.println("temp 00 3:"+temp[0]);
            System.out.println("temp 11 3:"+temp[1]);
            temp[0]=x-compare1;
            temp[1]=y-compare2;
            System.out.println("temp 0 3:"+temp[0]);
            System.out.println("temp 1 3:"+temp[1]);
            realcounter2.add(temp);
            compare1=x;
            compare2=y;
            System.out.println("comp 0 3:"+compare1);
            System.out.println("comp 1 3:"+compare2);
        }
        return realcounter2;
         
     }
     
      public LinkedList<int[]> getCounterPasienperServer4(ServerDokter[] arrayServer){
        int max=getMaxArrivalTimePoli4(arrayServer);
        System.out.println(" max poli 4:"+max);
        LinkedList<int[]> realcounter=new LinkedList<int[]>();
        int begin=0;
        if(max%5!=0){
            int temp=max;
            int mod=max%5;
            int kurang=5-mod;
            max=temp+kurang;
        }
        while(begin<=(max+5)){
            int i=0;
            int[] counterpasien=new int[3];
            while(i<arrayServer.length){
                LinkedList<Customer> custtemp=arrayServer[i].getQueueReport2();
                for(int k=0;k<custtemp.size();k++){
                    if(custtemp.get(k).getTimeServiceEnd4()<=begin){
                        if(custtemp.get(k).getJenis().equals("BPJS Lama")){
                            System.out.println("lama generate 4: "+custtemp.get(k).getTimeServiceEnd4()+" begin 4: "+begin);
                            counterpasien[0]++;
                            System.out.println("Counter pasien lama 4: "+counterpasien[0]);
                        }
                        else if(custtemp.get(k).getJenis().equals("BPJS Baru")){
                            System.out.println("baru generate 4: "+custtemp.get(k).getTimeServiceEnd4()+" begin 4: "+begin);
                            counterpasien[1]++;
                            System.out.println("Counter pasien baru 4: "+counterpasien[1]);
                        }
                        else if(custtemp.get(k).getJenis().equals("Emergency")){
                            System.out.println("emer generate 4: "+custtemp.get(k).getTimeServiceEnd4()+" begin 4: "+begin);
                            counterpasien[2]++;
                            System.out.println("Counter pasien emergency 4: "+counterpasien[2]);
                        }
                    }
                    if(custtemp.get(k).getTimeServiceEnd4()>begin){
                        k=custtemp.size();
                    }
                }
                i++;
                System.out.println(" i ke "+i);
            }
            System.out.println("counter pasien b4 queue 4: "+counterpasien[0]+" , "+counterpasien[1]+" , "+counterpasien[2]);
            realcounter.add(counterpasien);
            for(int k=0;k<realcounter.size();k++){
                System.out.println("cek realcounter dlm loop  4: "+realcounter.get(k)[0]+" "+realcounter.get(k)[1]+" "+realcounter.get(k)[2]);
            }
            System.out.println("First element 4: "+realcounter.getFirst()[0]+" "+realcounter.getFirst()[1]+" "+realcounter.getFirst()[2]);
            System.out.println("Size counter 4: "+realcounter.size());
            begin+=5;
        }
        for(int i=0;i<realcounter.size();i++){
            System.out.println("cek realcounter : "+realcounter.get(i)[0]+" "+realcounter.get(i)[1]+" "+realcounter.get(i)[2]);
        }
        int[] counterpasien1=realcounter.get(0);
        int compare1=counterpasien1[0];
        int compare2=counterpasien1[1];
        int compare3=counterpasien1[2];
        System.out.println("comp1 0 4:"+compare1);
        System.out.println("comp1 1 4:"+compare2);
        System.out.println("comp1 2 4:"+compare3);
        LinkedList<int[]> realcounter2=new LinkedList<int[]>();
        realcounter2.add(counterpasien1);
        for(int k=1;k<realcounter.size();k++){
            int[] temp=realcounter.get(k);
            int x=temp[0];
            int y=temp[1];
            int z=temp[2];
            System.out.println("temp 00 4:"+temp[0]);
            System.out.println("temp 11 4:"+temp[1]);
            System.out.println("temp 22 4:"+temp[2]);
            temp[0]=x-compare1;
            temp[1]=y-compare2;
            temp[2]=z-compare3;
            System.out.println("temp 0 4:"+temp[0]);
            System.out.println("temp 1 4:"+temp[1]);
            System.out.println("temp 2 4:"+temp[2]);
            realcounter2.add(temp);
            compare1=x;
            compare2=y;
            compare3=z;
            System.out.println("comp 0 4:"+compare1);
            System.out.println("comp 1 4:"+compare2);
            System.out.println("comp 2 4:"+compare3);
        }
        return realcounter2;
         
     }
      
     public LinkedList<int[]> getCounterPasienperServer4(ExcelInputSimulation.ServerDokter[] arrayServer){
        int max=getMaxArrivalTimePoli4(arrayServer);
        System.out.println(" max poli 4:"+max);
        LinkedList<int[]> realcounter=new LinkedList<int[]>();
        int begin=0;
        if(max%5!=0){
            int temp=max;
            int mod=max%5;
            int kurang=5-mod;
            max=temp+kurang;
        }
        while(begin<=(max)){
            int i=0;
            int[] counterpasien=new int[3];
            while(i<arrayServer.length){
                LinkedList<Customer> custtemp=arrayServer[i].getQueueReport2();
                for(int k=0;k<custtemp.size();k++){
                    if(custtemp.get(k).getTimeServiceEnd4()<=begin){
                        if(custtemp.get(k).getJenis().equals("BPJS Lama")){
                            System.out.println("lama generate 4: "+custtemp.get(k).getTimeServiceEnd4()+" begin 4: "+begin);
                            counterpasien[0]++;
                            System.out.println("Counter pasien lama 4: "+counterpasien[0]);
                        }
                        else if(custtemp.get(k).getJenis().equals("BPJS Baru")){
                            System.out.println("baru generate 4: "+custtemp.get(k).getTimeServiceEnd4()+" begin 4: "+begin);
                            counterpasien[1]++;
                            System.out.println("Counter pasien baru 4: "+counterpasien[1]);
                        }
                        else if(custtemp.get(k).getJenis().equals("Emergency")){
                            System.out.println("emer generate 4: "+custtemp.get(k).getTimeServiceEnd4()+" begin 4: "+begin);
                            counterpasien[2]++;
                            System.out.println("Counter pasien emergency 4: "+counterpasien[2]);
                        }
                    }
                    if(custtemp.get(k).getTimeServiceEnd4()>begin){
                        k=custtemp.size();
                    }
                }
                i++;
                System.out.println(" i ke "+i);
            }
            System.out.println("counter pasien b4 queue 4: "+counterpasien[0]+" , "+counterpasien[1]+" , "+counterpasien[2]);
            realcounter.add(counterpasien);
            for(int k=0;k<realcounter.size();k++){
                System.out.println("cek realcounter dlm loop  4: "+realcounter.get(k)[0]+" "+realcounter.get(k)[1]+" "+realcounter.get(k)[2]);
            }
            System.out.println("First element 4: "+realcounter.getFirst()[0]+" "+realcounter.getFirst()[1]+" "+realcounter.getFirst()[2]);
            System.out.println("Size counter 4: "+realcounter.size());
            begin+=5;
        }
        for(int i=0;i<realcounter.size();i++){
            System.out.println("cek realcounter : "+realcounter.get(i)[0]+" "+realcounter.get(i)[1]+" "+realcounter.get(i)[2]);
        }
        int[] counterpasien1=realcounter.get(0);
        int compare1=counterpasien1[0];
        int compare2=counterpasien1[1];
        int compare3=counterpasien1[2];
        System.out.println("comp1 0 4:"+compare1);
        System.out.println("comp1 1 4:"+compare2);
        System.out.println("comp1 2 4:"+compare3);
        LinkedList<int[]> realcounter2=new LinkedList<int[]>();
        realcounter2.add(counterpasien1);
        for(int k=1;k<realcounter.size();k++){
            int[] temp=realcounter.get(k);
            int x=temp[0];
            int y=temp[1];
            int z=temp[2];
            System.out.println("temp 00 4:"+temp[0]);
            System.out.println("temp 11 4:"+temp[1]);
            System.out.println("temp 22 4:"+temp[2]);
            temp[0]=x-compare1;
            temp[1]=y-compare2;
            temp[2]=z-compare3;
            System.out.println("temp 0 4:"+temp[0]);
            System.out.println("temp 1 4:"+temp[1]);
            System.out.println("temp 2 4:"+temp[2]);
            realcounter2.add(temp);
            compare1=x;
            compare2=y;
            compare3=z;
            System.out.println("comp 0 4:"+compare1);
            System.out.println("comp 1 4:"+compare2);
            System.out.println("comp 2 4:"+compare3);
        }
        return realcounter2;
         
     }
      
     public int getMaxPasien(ServerAwal[] arrayServer){
        int max=getMaxArrivalTimePoli(arrayServer);
        System.out.println(" max poli :"+max);
        if(max%5!=0){
            int temp=max;
            int mod=max%5;
            int kurang=5-mod;
            max=temp+kurang;
        }
        return max;
     }
     
     public int getMaxPasien(ExcelInputSimulation.ServerAwal[] arrayServer){
        int max=getMaxArrivalTimePoli(arrayServer);
        System.out.println(" max poli :"+max);
        if(max%5!=0){
            int temp=max;
            int mod=max%5;
            int kurang=5-mod;
            max=temp+kurang;
        }
        return max;
     }
     
      public int getMaxPasien2(ServerPetugas[] arrayServer){
        int max=getMaxArrivalTimePoli2(arrayServer);
        System.out.println(" max poli petugas:"+max);
        if(max%5!=0){
            int temp=max;
            int mod=max%5;
            int kurang=5-mod;
            max=temp+kurang;
        }
         System.out.println(" max poli :"+max);
        return max;
     }
      
      public int getMaxPasien2(ExcelInputSimulation.ServerPetugas[] arrayServer){
        int max=getMaxArrivalTimePoli2(arrayServer);
        System.out.println(" max poli  :"+max);
        if(max%5!=0){
            int temp=max;
            int mod=max%5;
            int kurang=5-mod;
            max=temp+kurang;
        }
        System.out.println(" max poli :"+max);
        return max;
     }
      
    public int getMaxPasien3(ServerPerawat[] arrayServer){
        int max=getMaxArrivalTimePoli3(arrayServer);
        System.out.println(" max poli perawat :"+max);
        if(max%5!=0){
            int temp=max;
            int mod=max%5;
            int kurang=5-mod;
            max=temp+kurang;
        }
        return max;
     }
    
    public int getMaxPasien3(ExcelInputSimulation.ServerPerawat[] arrayServer){
        int max=getMaxArrivalTimePoli3(arrayServer);
        System.out.println(" max poli :"+max);
        if(max%5!=0){
            int temp=max;
            int mod=max%5;
            int kurang=5-mod;
            max=temp+kurang;
        }
        return max;
     }
    
    public int getMaxPasien4(ServerDokter[] arrayServer){
        int max=getMaxArrivalTimePoli4(arrayServer);
        System.out.println(" max poli :"+max);
        if(max%5!=0){
            int temp=max;
            int mod=max%5;
            int kurang=5-mod;
            max=temp+kurang;
        }
        return max;
     }
    
    public int getMaxPasien4(ExcelInputSimulation.ServerDokter[] arrayServer){
        int max=getMaxArrivalTimePoli4(arrayServer);
        System.out.println(" max poli :"+max);
        if(max%5!=0){
            int temp=max;
            int mod=max%5;
            int kurang=5-mod;
            max=temp+kurang;
        }
        return max;
     }
        
        
     
     public int countPasienLama(ServerAwal[] arrayServer){
         int count=0;
         for(int i=0;i<arrayServer.length;i++){
             LinkedList<Customer> temp=arrayServer[i].getQueueReport2();
             for(int k=0;k<temp.size();k++){
                 if(temp.get(k).getJenis().equals("BPJS Lama")){
                     count++;
                 }
             }
         }
         return count;
     }
     
     public int countPasienBaru(ServerAwal[] arrayServer){
         int count=0;
         for(int i=0;i<arrayServer.length;i++){
             LinkedList<Customer> temp=arrayServer[i].getQueueReport2();
             for(int k=0;k<temp.size();k++){
                 if(temp.get(k).getJenis().equals("BPJS Baru")){
                     count++;
                 }
             }
         }
         return count;
     }
      
     
     
     public double generateSummaryArrivalTime2(Customer[] queuecustomer){
         if(queuecustomer.length>0){
            double sumArrivalTime=queuecustomer[queuecustomer.length-1].getArrivaltimepoli()/2.0;
            bd = new BigDecimal(((double)queuecustomer.length*1.0)/sumArrivalTime*1.0); 
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else {
             return 0;
         }
      }
     
     public double generateSummaryArrivalTime3(Customer[] queuecustomer){
         if(queuecustomer.length>0){
            double sumArrivalTime=queuecustomer[queuecustomer.length-1].getArrivaltimepoli2()/2.0;
            bd = new BigDecimal(((double)queuecustomer.length*1.0)/sumArrivalTime*1.0); 
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else {
             return 0;
         }
      }
     
     public double generateSummaryArrivalTime4(Customer[] queuecustomer){
         if(queuecustomer.length>0){
            double sumArrivalTime=queuecustomer[queuecustomer.length-1].getArrivaltimepoli3()/2.0;
            bd = new BigDecimal(((double)queuecustomer.length*1.0)/sumArrivalTime*1.0); 
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else {
             return 0;
         }
      }
     
      public double generateArrivalTimeBPJSLama(Customer[] queuecustomer){
         if(queuecustomer.length>0){
            int cari=0;
            for(int i=0;i<queuecustomer.length;i++){
                if(queuecustomer[i].getJenis().equals("BPJS Lama")){
                    cari=i;
                }
            }
            int total=0;
            for(int i=0;i<queuecustomer.length;i++){
                if(queuecustomer[i].getJenis().equals("BPJS Lama")){
                    total++;
                }
            }
            double sumArrivalTime=queuecustomer[cari].getArrivaltime()/2.0;
            bd = new BigDecimal(((double)total*1.0)/sumArrivalTime*1.0); 
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
             return 0;
         }
     }
      
      public double generateArrivalTimeBPJSLama2(Customer[] queuecustomer){
         int cari=0;
         if(queuecustomer.length>0){
            for(int i=0;i<queuecustomer.length;i++){
                if(queuecustomer[i].getJenis().equals("BPJS Lama")){
                    cari=i;
                }
            }
            int total=0;
            for(int i=0;i<queuecustomer.length;i++){
                if(queuecustomer[i].getJenis().equals("BPJS Lama")){
                    total++;
                }
            }
            double sumArrivalTime=queuecustomer[cari].getArrivaltimepoli()/2.0;
            bd = new BigDecimal(((double)total*1.0)/sumArrivalTime*1.0); 
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else {
             return 0;
         }
     }
      
      public double generateArrivalTimeBPJSLama3(Customer[] queuecustomer){
         int cari=0;
         if(queuecustomer.length>0){
            for(int i=0;i<queuecustomer.length;i++){
                if(queuecustomer[i].getJenis().equals("BPJS Lama")){
                    cari=i;
                }
            }
            int total=0;
            for(int i=0;i<queuecustomer.length;i++){
                if(queuecustomer[i].getJenis().equals("BPJS Lama")){
                    total++;
                }
            }
            double sumArrivalTime=queuecustomer[cari].getArrivaltimepoli2()/2.0;
            bd = new BigDecimal(((double)total*1.0)/sumArrivalTime*1.0); 
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else {
             return 0;
         }
     }
      
      public double generateArrivalTimeBPJSLama4(Customer[] queuecustomer){
         int cari=0;
         if(queuecustomer.length>0){
            for(int i=0;i<queuecustomer.length;i++){
                if(queuecustomer[i].getJenis().equals("BPJS Lama")){
                    cari=i;
                }
            }
            int total=0;
            for(int i=0;i<queuecustomer.length;i++){
                if(queuecustomer[i].getJenis().equals("BPJS Lama")){
                    total++;
                }
            }
            if(queuecustomer[cari].getArrivaltimepoli3()>0){
                double sumArrivalTime=queuecustomer[cari].getArrivaltimepoli3()/2.0;
                bd = new BigDecimal(((double)total*1.0)/sumArrivalTime*1.0); 
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                return bd.doubleValue();
            }
            else{
                return 0;
            }
         }
         else {
             return 0;
         }
     }
      
      public double generateArrivalTimeBPJSBaru(Customer[] queuecustomer){
         if(queuecustomer.length>0){
            int cari=0;
            for(int i=0;i<queuecustomer.length;i++){
                if(queuecustomer[i].getJenis().equals("BPJS Baru")){
                    cari=i;
                }
            }
            int total=0;
            for(int i=0;i<queuecustomer.length;i++){
                if(queuecustomer[i].getJenis().equals("BPJS Baru")){
                    total++;
                }
            }
            if(queuecustomer[cari].getArrivaltime()>0&&total>0){
                double sumArrivalTime=queuecustomer[cari].getArrivaltime()/2.0;
                bd = new BigDecimal(((double)total*1.0)/sumArrivalTime*1.0); 
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                return bd.doubleValue();
            }
            else{
                return 0;
            }
         }
         else{
             return 0;
         }
      }
      
      public double generateArrivalTimeBPJSBaru2(Customer[] queuecustomer){
         int cari=0;
         if(queuecustomer.length>0){
                for(int i=0;i<queuecustomer.length;i++){
                    if(queuecustomer[i].getJenis().equals("BPJS Baru")){
                        cari=i;
                    }
                }
                int total=0;
                for(int i=0;i<queuecustomer.length;i++){
                    if(queuecustomer[i].getJenis().equals("BPJS Baru")){
                        total++;
                    }
                }
                if(queuecustomer[cari].getArrivaltimepoli()>0&&total>0){
                    double sumArrivalTime=queuecustomer[cari].getArrivaltimepoli()/2.0;
                    bd = new BigDecimal(((double)total*1.0)/sumArrivalTime*1.0); 
                    bd = bd.setScale(2,BigDecimal.ROUND_UP);
                    return bd.doubleValue();
                }
                else{
                    return 0;
                }
         }
         else{
             return 0;
         }
        
      }
      
      public double generateArrivalTimeBPJSBaru3(Customer[] queuecustomer){
         int cari=0;
         if(queuecustomer.length>0){
                for(int i=0;i<queuecustomer.length;i++){
                    if(queuecustomer[i].getJenis().equals("BPJS Baru")){
                        cari=i;
                    }
                }
                int total=0;
                for(int i=0;i<queuecustomer.length;i++){
                    if(queuecustomer[i].getJenis().equals("BPJS Baru")){
                        total++;
                    }
                }
                if(queuecustomer[cari].getArrivaltimepoli2()>0&&total>0){
                    double sumArrivalTime=queuecustomer[cari].getArrivaltimepoli2()/2.0;
                    bd = new BigDecimal(((double)total*1.0)/sumArrivalTime*1.0); 
                    bd = bd.setScale(2,BigDecimal.ROUND_UP);
                    return bd.doubleValue();
                }
                else{
                    return 0;
                }
         }
         else{
             return 0;
         }
        
      }
      
      public double generateArrivalTimeBPJSBaru4(Customer[] queuecustomer){
         int cari=0;
         if(queuecustomer.length>0){
                for(int i=0;i<queuecustomer.length;i++){
                    if(queuecustomer[i].getJenis().equals("BPJS Baru")){
                        cari=i;
                    }
                }
                int total=0;
                for(int i=0;i<queuecustomer.length;i++){
                    if(queuecustomer[i].getJenis().equals("BPJS Baru")){
                        total++;
                    }
                }
                if(queuecustomer[cari].getArrivaltimepoli3()>0&&total>0){
                    double sumArrivalTime=queuecustomer[cari].getArrivaltimepoli3()/2.0;
                    bd = new BigDecimal(((double)total*1.0)/sumArrivalTime*1.0); 
                    bd = bd.setScale(2,BigDecimal.ROUND_UP);
                    return bd.doubleValue();
                }
                else{
                    return 0;
                }
         }
         else{
             return 0;
         }
        
      }
      
      public double generateArrivalTimeEmergency(Customer[] queuecustomer){
         int cari=0;
         if(queuecustomer.length>0){
                for(int i=0;i<queuecustomer.length;i++){
                    if(queuecustomer[i].getJenis().equals("Emergency")){
                        cari=i;
                    }
                }
                int total=0;
                for(int i=0;i<queuecustomer.length;i++){
                    if(queuecustomer[i].getJenis().equals("Emergency")){
                        total++;
                    }
                }
                double sumArrivalTime=queuecustomer[cari].getArrivaltimepoli()/2.0;
                bd = new BigDecimal(((double)total*1.0)/sumArrivalTime*1.0); 
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                return bd.doubleValue();
         }
         else{
             return 0;
         }
        
      }
      
      public double generateAverageInterArrivalTime(Customer[] queuecustomer){
          double arrivaltime=this.generateSummaryArrivalTime(queuecustomer);
          if(arrivaltime>0){
                bd = new BigDecimal(((double)1.0)/arrivaltime*1.0); 
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                return bd.doubleValue();
          }
          else{
              return 0;
          }
      }
      
      public double generateAverageInterArrivalTime2(Customer[] queuecustomer){
          double arrivaltime=this.generateSummaryArrivalTime2(queuecustomer);
          if(arrivaltime>0){
                bd = new BigDecimal(((double)1.0)/arrivaltime*1.0); 
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                return bd.doubleValue();
          }
          else{
              return 0;
          }
      }
      
      public double generateAverageInterArrivalTime3(Customer[] queuecustomer){
          double arrivaltime=this.generateSummaryArrivalTime3(queuecustomer);
          if(arrivaltime>0){
                bd = new BigDecimal(((double)1.0)/arrivaltime*1.0); 
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                return bd.doubleValue();
          }
          else{
              return 0;
          }
      }
      
      public double generateAverageInterArrivalTime4(Customer[] queuecustomer){
          double arrivaltime=this.generateSummaryArrivalTime4(queuecustomer);
          if(arrivaltime>0){
                bd = new BigDecimal(((double)1.0)/arrivaltime*1.0); 
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                return bd.doubleValue();
          }
          else{
              return 0;
          }
      }
      
      public double generateAverageInterArrivalTimeBPJSLama(Customer[] queuecustomer){
          double arrivaltime=this.generateArrivalTimeBPJSLama(queuecustomer);
          if(arrivaltime>0){
            bd = new BigDecimal(((double)1.0)/arrivaltime*1.0); 
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
          }
          else{
              return 0;
          }
      }
      
      public double generateAverageInterArrivalTimeBPJSLama2(Customer[] queuecustomer){
          double arrivaltime=this.generateArrivalTimeBPJSLama2(queuecustomer);
          if(arrivaltime>0){
            bd = new BigDecimal(((double)1.0)/arrivaltime*1.0); 
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
          }
          else{
              return 0;
          }
      }
      
      public double generateAverageInterArrivalTimeBPJSLama3(Customer[] queuecustomer){
          double arrivaltime=this.generateArrivalTimeBPJSLama3(queuecustomer);
          if(arrivaltime>0){
            bd = new BigDecimal(((double)1.0)/arrivaltime*1.0); 
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
          }
          else{
              return 0;
          }
      }
      
      public double generateAverageInterArrivalTimeBPJSLama4(Customer[] queuecustomer){
          double arrivaltime=this.generateArrivalTimeBPJSLama4(queuecustomer);
          if(arrivaltime>0){
            bd = new BigDecimal(((double)1.0)/arrivaltime*1.0); 
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
          }
          else{
              return 0;
          }
      }
      
       public double generateAverageInterArrivalTimeBPJSBaru(Customer[] queuecustomer){
          double arrivaltime=this.generateArrivalTimeBPJSBaru(queuecustomer);
          if(arrivaltime>0){
            bd = new BigDecimal(((double)1.0)/arrivaltime*1.0); 
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
          }
          else{
              return 0;
          }
      }
       
        public double generateAverageInterArrivalTimeBPJSBaru2(Customer[] queuecustomer){
          double arrivaltime=this.generateArrivalTimeBPJSBaru2(queuecustomer);
          if(arrivaltime>0){
            bd = new BigDecimal(((double)1.0)/arrivaltime*1.0); 
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
          }
          else{
              return 0;
          }
      }
        
        public double generateAverageInterArrivalTimeBPJSBaru3(Customer[] queuecustomer){
          double arrivaltime=this.generateArrivalTimeBPJSBaru3(queuecustomer);
          if(arrivaltime>0){
            bd = new BigDecimal(((double)1.0)/arrivaltime*1.0); 
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
          }
          else{
              return 0;
          }
      }
        
     public double generateAverageInterArrivalTimeBPJSBaru4(Customer[] queuecustomer){
          double arrivaltime=this.generateArrivalTimeBPJSBaru4(queuecustomer);
          if(arrivaltime>0){
            bd = new BigDecimal(((double)1.0)/arrivaltime*1.0); 
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
          }
          else{
              return 0;
          }
      }
        
        
       public double generateAverageInterArrivalTimeEmergency(Customer[] queuecustomer){
          double arrivaltime=this.generateArrivalTimeEmergency(queuecustomer);
          if(arrivaltime>0){
            bd = new BigDecimal(((double)1.0)/arrivaltime*1.0); 
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
          }
          else{
              return 0;
          }
      }

   
    
    public String convertSeconds(double value){
        String ret="";
        if(value<1){
            double seconds=value*60;
            if(seconds<10){
                 ret="00:00:0"+Math.round(seconds);;
            }
            else{
                ret="00:00:"+Math.round(seconds);;
            }
        }
        else if(value>=1&&value<60){
            int up=(int)value;
            double kurang=value-up;
            bd = new BigDecimal(kurang); 
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            //kurang=bd.doubleValue();
            double seconds=kurang*60;
            if(up<10&&seconds<10){
                ret="00:0"+up+":0"+Math.round(seconds);
            }
            else if(up>=10&&seconds<10){
                ret="00:"+up+":0"+Math.round(seconds);;
            }
            else if(up<10&&seconds>=10){
                ret="00:0"+up+":"+Math.round(seconds);;
            }
            else{
                ret="00:"+up+":"+Math.round(seconds);;
            }
        }
        else if(value>=60){
            int up=(int)value;
            double kurang=value-up;
            bd = new BigDecimal(kurang); 
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            //kurang=bd.doubleValue();
            double seconds=kurang*60;
            int hours=up/60;
            int minutes=up%60;
            String hours2="";
            String minutes2="";
            String seconds2="";
            if(hours<10){
                hours2="0"+hours;
            }
            else{
                hours2=hours+"";
            }
            if(minutes<10){
                minutes2="0"+minutes;
            }
            else{
                 minutes2=minutes+"";
            }
            if(seconds<10){
                seconds2="0"+Math.round(seconds);
            }
            else{
                 seconds2=""+Math.round(seconds);
            }
            ret=hours2+":"+minutes2+":"+seconds2;
            
        }
        return ret;
    }
    
    public int convertSecondsForChart(double value){
        int ret=0;
        if(value>=1&&value<60){
            int up=(int)value;
            double kurang=value-up;
            bd = new BigDecimal(kurang); 
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            ret=up;
        }
        else if(value>=60){
            int up=(int)value;
            double kurang=value-up;
            bd = new BigDecimal(kurang); 
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            //kurang=bd.doubleValue();
            double seconds=kurang*60;
            int hours=up/60;
            int minutes=up%60;
            String hours2="";
            String minutes2="";
            String seconds2="";
            ret=up;
            
        }
        return ret;
    }
    
    public int getMaxJumlahPasienperServer(LinkedList<int[]> customer){
        int max=customer.get(0)[0];
        for(int i=1;i<customer.size();i++){
            if(max<customer.get(i)[0]){
                max=customer.get(i)[0];
            }
        }
        int max2=customer.get(0)[1];
        for(int i=1;i<customer.size();i++){
            if(max2<customer.get(i)[1]){
                max2=customer.get(i)[1];
            }
        }
        int max3=customer.get(0)[2];
        for(int i=1;i<customer.size();i++){
            if(max3<customer.get(i)[2]){
                max3=customer.get(i)[2];
            }
        }
        if(max>max2&&max>max3){
            System.out.println("Max pasien per server : "+max);
            return max;
            
        }
        else if(max2>max3&&max2>max){
            System.out.println("Max pasien per server : "+max2);
            return max2;
            
        }
        else{
            return max3;
        }
         
    }
    
    
    
    public Object[][] generateUtilityServer(ServerAwal[] arrayServer){
        Object[][] utility=new Object[1][arrayServer.length];
        for(int i=0;i<arrayServer.length;i++){
           System.out.println("Service time : "+arrayServer[i].getTotalservicetime()+" Countercustomer :"+arrayServer[i].getCounter());
           if(arrayServer[i].getTotalservicetime()>0&&arrayServer[i].getCounter()>0){
                double temp=(1/(arrayServer[i].getTotalservicetime()/arrayServer[i].getCounter()))*100;
                bd = new BigDecimal(temp); 
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                temp=bd.doubleValue();
                System.out.println("temp : "+temp);
                if(temp>100){
                    temp=100;
                }
                utility[0][i]=temp+" %";
             }
           else{
               utility[0][i]="0 %";
           }
        }
        return utility;
        
    }
    
    public double[][] generateUtilityServerforChart(ServerAwal[] arrayServer){
        double[][] utility=new double[1][arrayServer.length];
        for(int i=0;i<arrayServer.length;i++){
           System.out.println("Service time : "+arrayServer[i].getTotalservicetime()+" Countercustomer :"+arrayServer[i].getCounter());
           if(arrayServer[i].getTotalservicetime()>0&&arrayServer[i].getCounter()>0){
                double temp=(1/(arrayServer[i].getTotalservicetime()/arrayServer[i].getCounter()))*100;
                bd = new BigDecimal(temp); 
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                temp=bd.doubleValue();
                System.out.println("temp : "+temp);
                if(temp>100){
                    temp=100;
                }
                utility[0][i]=temp;
             }
           else{
               utility[0][i]=0;
           }
        }
        return utility;
        
    }
    
    public double[][] generateUtilityServerforChart(ExcelInputSimulation.ServerAwal[] arrayServer){
        double[][] utility=new double[1][arrayServer.length];
        for(int i=0;i<arrayServer.length;i++){
           System.out.println("Service time : "+arrayServer[i].getTotalservicetime()+" Countercustomer :"+arrayServer[i].getCounter());
           if(arrayServer[i].getTotalservicetime()>0&&arrayServer[i].getCounter()>0){
                double temp=(1/(arrayServer[i].getTotalservicetime()/arrayServer[i].getCounter()))*100;
                bd = new BigDecimal(temp); 
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                temp=bd.doubleValue();
                System.out.println("temp : "+temp);
                if(temp>100){
                    temp=100;
                }
                utility[0][i]=temp;
             }
           else{
               utility[0][i]=0;
           }
        }
        return utility;
        
    }
    
    public Object[][] generateUtilityServer2(ServerPetugas[] arrayServer){
        Object[][] utility=new Object[1][arrayServer.length];
        for(int i=0;i<arrayServer.length;i++){
            System.out.println("Service time : "+arrayServer[i].getTotalservicetime()+" Countercustomer :"+arrayServer[i].getCounter());
            if(arrayServer[i].getTotalservicetime()==0||arrayServer[i].getCounter()==0){
                utility[0][i]="0 %";
            }
            else{
                double temp=(1/(arrayServer[i].getTotalservicetime()/arrayServer[i].getCounter()))*100;
                System.out.println("temp ? :"+temp);
                bd = new BigDecimal(temp); 
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                temp=bd.doubleValue();
                System.out.println("temp : "+temp);
                if(temp>100){
                        temp=100;
                }
                utility[0][i]=temp+" %";
            }
            
        }
        return utility;
        
    }
    
     public double[][] generateUtilityServer2forChart(ServerPetugas[] arrayServer){
        double[][] utility=new double[1][arrayServer.length];
        for(int i=0;i<arrayServer.length;i++){
            System.out.println("Service time : "+arrayServer[i].getTotalservicetime()+" Countercustomer :"+arrayServer[i].getCounter());
            if(arrayServer[i].getTotalservicetime()==0||arrayServer[i].getCounter()==0){
                utility[0][i]=0.0;
            }
            else{
                double temp=(1/(arrayServer[i].getTotalservicetime()/arrayServer[i].getCounter()))*100;
                System.out.println("temp ? :"+temp);
                bd = new BigDecimal(temp); 
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                temp=bd.doubleValue();
                System.out.println("temp : "+temp);
                if(temp>100){
                        temp=100;
                }
                utility[0][i]=temp;
            }
            
        }
        return utility;
        
    }
     
      public double[][] generateUtilityServer2forChart(ExcelInputSimulation.ServerPetugas[] arrayServer){
        double[][] utility=new double[1][arrayServer.length];
        for(int i=0;i<arrayServer.length;i++){
            System.out.println("Service time : "+arrayServer[i].getTotalservicetime()+" Countercustomer :"+arrayServer[i].getCounter());
            if(arrayServer[i].getTotalservicetime()==0||arrayServer[i].getCounter()==0){
                utility[0][i]=0.0;
            }
            else{
                double temp=(1/(arrayServer[i].getTotalservicetime()/arrayServer[i].getCounter()))*100;
                System.out.println("temp ? :"+temp);
                bd = new BigDecimal(temp); 
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                temp=bd.doubleValue();
                System.out.println("temp : "+temp);
                if(temp>100){
                        temp=100;
                }
                utility[0][i]=temp;
            }
            
        }
        return utility;
        
    }
    
     public Object[][] generateUtilityServer3(ServerPerawat[] arrayServer){
        Object[][] utility=new Object[1][arrayServer.length];
        for(int i=0;i<arrayServer.length;i++){
            System.out.println("Service time : "+arrayServer[i].getTotalservicetime()+" Countercustomer :"+arrayServer[i].getCounter());
            if(arrayServer[i].getTotalservicetime()==0||arrayServer[i].getCounter()==0){
                utility[0][i]="0 %";
            }
            else{
                double temp=(1/(arrayServer[i].getTotalservicetime()/arrayServer[i].getCounter()))*100;
                System.out.println("temp ? :"+temp);
                bd = new BigDecimal(temp); 
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                temp=bd.doubleValue();
                System.out.println("temp : "+temp);
                if(temp>100){
                        temp=100;
                }
                utility[0][i]=temp+" %";
            }
            
        }
        return utility;
        
    }
     
     public double[][] generateUtilityServer3forChart(ServerPerawat[] arrayServer){
        double[][] utility=new double[1][arrayServer.length];
        for(int i=0;i<arrayServer.length;i++){
            System.out.println("Service time : "+arrayServer[i].getTotalservicetime()+" Countercustomer :"+arrayServer[i].getCounter());
            if(arrayServer[i].getTotalservicetime()==0||arrayServer[i].getCounter()==0){
                utility[0][i]=0.0;
            }
            else{
                double temp=(1/(arrayServer[i].getTotalservicetime()/arrayServer[i].getCounter()))*100;
                System.out.println("temp ? :"+temp);
                bd = new BigDecimal(temp); 
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                temp=bd.doubleValue();
                System.out.println("temp : "+temp);
                if(temp>100){
                        temp=100;
                }
                utility[0][i]=temp;
            }
            
        }
        return utility;
        
    }
     
     public double[][] generateUtilityServer3forChart(ExcelInputSimulation.ServerPerawat[] arrayServer){
        double[][] utility=new double[1][arrayServer.length];
        for(int i=0;i<arrayServer.length;i++){
            System.out.println("Service time : "+arrayServer[i].getTotalservicetime()+" Countercustomer :"+arrayServer[i].getCounter());
            if(arrayServer[i].getTotalservicetime()==0||arrayServer[i].getCounter()==0){
                utility[0][i]=0.0;
            }
            else{
                double temp=(1/(arrayServer[i].getTotalservicetime()/arrayServer[i].getCounter()))*100;
                System.out.println("temp ? :"+temp);
                bd = new BigDecimal(temp); 
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                temp=bd.doubleValue();
                System.out.println("temp : "+temp);
                if(temp>100){
                        temp=100;
                }
                utility[0][i]=temp;
            }
            
        }
        return utility;
        
    }
     
     public Object[][] generateUtilityServer3(ExcelInputSimulation.ServerPerawat[] arrayServer){
        Object[][] utility=new Object[1][arrayServer.length];
        for(int i=0;i<arrayServer.length;i++){
            System.out.println("Service time : "+arrayServer[i].getTotalservicetime()+" Countercustomer :"+arrayServer[i].getCounter());
            if(arrayServer[i].getTotalservicetime()==0||arrayServer[i].getCounter()==0){
                utility[0][i]="0 %";
            }
            else{
                double temp=(1/(arrayServer[i].getTotalservicetime()/arrayServer[i].getCounter()))*100;
                System.out.println("temp ? :"+temp);
                bd = new BigDecimal(temp); 
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                temp=bd.doubleValue();
                System.out.println("temp : "+temp);
                if(temp>100){
                        temp=100;
                }
                utility[0][i]=temp+" %";
            }
            
        }
        return utility;
        
    }
     
     
      public Object[][] generateUtilityServer4(ServerDokter[] arrayServer){
        Object[][] utility=new Object[1][arrayServer.length];
        for(int i=0;i<arrayServer.length;i++){
            System.out.println("Service time : "+arrayServer[i].getTotalservicetime()+" Countercustomer :"+arrayServer[i].getCounter());
            if(arrayServer[i].getTotalservicetime()==0||arrayServer[i].getCounter()==0){
                utility[0][i]="0 %";
            }
            else{
                double temp=(1/(arrayServer[i].getTotalservicetime()/arrayServer[i].getCounter()))*100;
                System.out.println("temp ? :"+temp);
                bd = new BigDecimal(temp); 
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                temp=bd.doubleValue();
                System.out.println("temp : "+temp);
                if(temp>100){
                        temp=100;
                }
                utility[0][i]=temp+" %";
            }
            
        }
        return utility;
        
    }
      
      public double[][] generateUtilityServer4forChart(ServerDokter[] arrayServer){
        double[][] utility=new double[1][arrayServer.length];
        for(int i=0;i<arrayServer.length;i++){
            System.out.println("Service time : "+arrayServer[i].getTotalservicetime()+" Countercustomer :"+arrayServer[i].getCounter());
            if(arrayServer[i].getTotalservicetime()==0||arrayServer[i].getCounter()==0){
                utility[0][i]=0;
            }
            else{
                double temp=(1/(arrayServer[i].getTotalservicetime()/arrayServer[i].getCounter()))*100;
                System.out.println("temp ? :"+temp);
                bd = new BigDecimal(temp); 
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                temp=bd.doubleValue();
                System.out.println("temp : "+temp);
                if(temp>100){
                        temp=100;
                }
                utility[0][i]=temp;
            }
            
        }
        return utility;
        
    }
      
    public double[][] generateUtilityServer4forChart(ExcelInputSimulation.ServerDokter[] arrayServer){
        double[][] utility=new double[1][arrayServer.length];
        for(int i=0;i<arrayServer.length;i++){
            System.out.println("Service time : "+arrayServer[i].getTotalservicetime()+" Countercustomer :"+arrayServer[i].getCounter());
            if(arrayServer[i].getTotalservicetime()==0||arrayServer[i].getCounter()==0){
                utility[0][i]=0;
            }
            else{
                double temp=(1/(arrayServer[i].getTotalservicetime()/arrayServer[i].getCounter()))*100;
                System.out.println("temp ? :"+temp);
                bd = new BigDecimal(temp); 
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                temp=bd.doubleValue();
                System.out.println("temp : "+temp);
                if(temp>100){
                        temp=100;
                }
                utility[0][i]=temp;
            }
            
        }
        return utility;
        
    }
      
      
       public Object[][] generateUtilityServer4(ExcelInputSimulation.ServerDokter[] arrayServer){
        Object[][] utility=new Object[1][arrayServer.length];
        for(int i=0;i<arrayServer.length;i++){
            System.out.println("Service time : "+arrayServer[i].getTotalservicetime()+" Countercustomer :"+arrayServer[i].getCounter());
            if(arrayServer[i].getTotalservicetime()==0||arrayServer[i].getCounter()==0){
                utility[0][i]="0 %";
            }
            else{
                double temp=(1/(arrayServer[i].getTotalservicetime()/arrayServer[i].getCounter()))*100;
                System.out.println("temp ? :"+temp);
                bd = new BigDecimal(temp); 
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                temp=bd.doubleValue();
                System.out.println("temp : "+temp);
                if(temp>100){
                        temp=100;
                }
                utility[0][i]=temp+" %";
            }
            
        }
        return utility;
        
    }
      
       public Object[][] generateUtilityServer5(ExcelInputSimulation.ServerAwal[] arrayServer){
        Object[][] utility=new Object[1][arrayServer.length];
        for(int i=0;i<arrayServer.length;i++){
           System.out.println("Service time : "+arrayServer[i].getTotalservicetime()+" Countercustomer :"+arrayServer[i].getCounter());
           if(arrayServer[i].getTotalservicetime()>0&&arrayServer[i].getCounter()>0){
                double temp=(1/(arrayServer[i].getTotalservicetime()/arrayServer[i].getCounter()))*100;
                bd = new BigDecimal(temp); 
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                temp=bd.doubleValue();
                System.out.println("temp : "+temp);
                if(temp>100){
                    temp=100;
                }
                utility[0][i]=temp+" %";
             }
           else{
               utility[0][i]="0 %";
           }
        }
        return utility;
        
    }
       
    public Object[][] generateUtilityServer6(ExcelInputSimulation.ServerPetugas[] arrayServer){
        Object[][] utility=new Object[1][arrayServer.length];
        for(int i=0;i<arrayServer.length;i++){
            System.out.println("Service time : "+arrayServer[i].getTotalservicetime()+" Countercustomer :"+arrayServer[i].getCounter());
            if(arrayServer[i].getTotalservicetime()==0||arrayServer[i].getCounter()==0){
                utility[0][i]="0 %";
            }
            else{
                double temp=(1/(arrayServer[i].getTotalservicetime()/arrayServer[i].getCounter()))*100;
                System.out.println("temp ? :"+temp);
                bd = new BigDecimal(temp); 
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                temp=bd.doubleValue();
                System.out.println("temp : "+temp);
                if(temp>100){
                        temp=100;
                }
                utility[0][i]=temp+" %";
            }
            
        }
        return utility;
        
    }
    
    public Object[][] generateAverageServiceTime(ServerAwal[] arrayServer){
        Object[][] res=new Object[1][arrayServer.length];
        for(int i=0;i<arrayServer.length;i++){
            if(arrayServer[i].getTotalservicetime()>0&&arrayServer[i].getCounter()>0){
                double temp=arrayServer[i].getTotalservicetime()*1.0/((double)arrayServer[i].getCounter()*1.0);
                bd = new BigDecimal(temp); 
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                res[0][i]=this.convertSeconds(bd.doubleValue());
            }
            else{
                res[0][i]="00:00:00";
            }
        }
        return res;
    }
    
    public Object[][] generateAverageServiceTime2(ServerPetugas[] arrayPetugas){
        Object[][] res=new Object[1][arrayPetugas.length];
        for(int i=0;i<arrayPetugas.length;i++){
            System.out.println("total service time : "+arrayPetugas[i].getTotalservicetime()+" Counter : "+arrayPetugas[i].getCounter());
            if(arrayPetugas[i].getTotalservicetime()>0&&arrayPetugas[i].getCounter()>0){
                double temp=arrayPetugas[i].getTotalservicetime()*1.0/((double)arrayPetugas[i].getCounter()*1.0);
                bd = new BigDecimal(temp); 
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                res[0][i]=this.convertSeconds(bd.doubleValue());
            }
            else{
                res[0][i]="00:00:00";
            }
        }
        return res;
    }
    
    public Object[][] generateAverageServiceTime3(ServerPerawat[] arrayPetugas){
        Object[][] res=new Object[1][arrayPetugas.length];
        for(int i=0;i<arrayPetugas.length;i++){
            System.out.println("total service time : "+arrayPetugas[i].getTotalservicetime()+" Counter : "+arrayPetugas[i].getCounter());
            if(arrayPetugas[i].getTotalservicetime()>0&&arrayPetugas[i].getCounter()>0){
                double temp=arrayPetugas[i].getTotalservicetime()*1.0/((double)arrayPetugas[i].getCounter()*1.0);
                bd = new BigDecimal(temp); 
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                res[0][i]=this.convertSeconds(bd.doubleValue());
            }
            else{
                res[0][i]="00:00:00";
            }
        }
        return res;
    }
    
    public Object[][] generateAverageServiceTime3(ExcelInputSimulation.ServerPerawat[] arrayPetugas){
        Object[][] res=new Object[1][arrayPetugas.length];
        for(int i=0;i<arrayPetugas.length;i++){
            System.out.println("total service time : "+arrayPetugas[i].getTotalservicetime()+" Counter : "+arrayPetugas[i].getCounter());
            if(arrayPetugas[i].getTotalservicetime()>0&&arrayPetugas[i].getCounter()>0){
                double temp=arrayPetugas[i].getTotalservicetime()*1.0/((double)arrayPetugas[i].getCounter()*1.0);
                bd = new BigDecimal(temp); 
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                res[0][i]=this.convertSeconds(bd.doubleValue());
            }
            else{
                res[0][i]="00:00:00";
            }
        }
        return res;
    }
    
    
    public Object[][] generateAverageServiceTime4(ServerDokter[] arrayPetugas){
        Object[][] res=new Object[1][arrayPetugas.length];
        for(int i=0;i<arrayPetugas.length;i++){
            System.out.println("total service time : "+arrayPetugas[i].getTotalservicetime()+" Counter : "+arrayPetugas[i].getCounter());
            if(arrayPetugas[i].getTotalservicetime()>0&&arrayPetugas[i].getCounter()>0){
                double temp=arrayPetugas[i].getTotalservicetime()*1.0/((double)arrayPetugas[i].getCounter()*1.0);
                bd = new BigDecimal(temp); 
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                res[0][i]=this.convertSeconds(bd.doubleValue());
            }
            else{
                res[0][i]="00:00:00";
            }
        }
        return res;
    }
    
    public Object[][] generateAverageServiceTime4(ExcelInputSimulation.ServerDokter[] arrayPetugas){
        Object[][] res=new Object[1][arrayPetugas.length];
        for(int i=0;i<arrayPetugas.length;i++){
            System.out.println("total service time : "+arrayPetugas[i].getTotalservicetime()+" Counter : "+arrayPetugas[i].getCounter());
            if(arrayPetugas[i].getTotalservicetime()>0&&arrayPetugas[i].getCounter()>0){
                double temp=arrayPetugas[i].getTotalservicetime()*1.0/((double)arrayPetugas[i].getCounter()*1.0);
                bd = new BigDecimal(temp); 
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                res[0][i]=this.convertSeconds(bd.doubleValue());
            }
            else{
                res[0][i]="00:00:00";
            }
        }
        return res;
    }
    
     public Object[][] generateAverageServiceTime5(ExcelInputSimulation.ServerAwal[] arrayServer){
        Object[][] res=new Object[1][arrayServer.length];
        for(int i=0;i<arrayServer.length;i++){
            if(arrayServer[i].getTotalservicetime()>0&&arrayServer[i].getCounter()>0){
                double temp=arrayServer[i].getTotalservicetime()*1.0/((double)arrayServer[i].getCounter()*1.0);
                bd = new BigDecimal(temp); 
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                res[0][i]=this.convertSeconds(bd.doubleValue());
            }
            else{
                res[0][i]="00:00:00";
            }
        }
        return res;
    }
     
      public Object[][] generateAverageServiceTime6(ExcelInputSimulation.ServerPetugas[] arrayPetugas){
        Object[][] res=new Object[1][arrayPetugas.length];
        for(int i=0;i<arrayPetugas.length;i++){
            System.out.println("total service time : "+arrayPetugas[i].getTotalservicetime()+" Counter : "+arrayPetugas[i].getCounter());
            if(arrayPetugas[i].getTotalservicetime()>0&&arrayPetugas[i].getCounter()>0){
                double temp=arrayPetugas[i].getTotalservicetime()*1.0/((double)arrayPetugas[i].getCounter()*1.0);
                bd = new BigDecimal(temp); 
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                res[0][i]=this.convertSeconds(bd.doubleValue());
            }
            else{
                res[0][i]="00:00:00";
            }
        }
        return res;
    }
    
     public Object[][] generateAverageWaitingTime(ServerAwal[] arrayServer){
        Object[][] res=new Object[1][arrayServer.length];
        for(int i=0;i<arrayServer.length;i++){
            if(arrayServer[i].getTotalWaitingTime()>0&&arrayServer[i].getCounter()>0){
                double temp=arrayServer[i].getTotalWaitingTime()*1.0/((double)arrayServer[i].getCounter()*1.0);
                bd = new BigDecimal(temp); 
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                res[0][i]=this.convertSeconds(bd.doubleValue());
            }
            else{
                res[0][i]="00:00:00";
            }
        }
        return res;
    }
     
     public Object[][] generateAverageWaitingTime2(ServerPetugas[] arrayPetugas){
        Object[][] res=new Object[1][arrayPetugas.length];
        for(int i=0;i<arrayPetugas.length;i++){
            System.out.println("waiting time : petugas "+arrayPetugas[i].getTotalWaitingTime()+" counter : "+arrayPetugas[i].getCounter());
            if(arrayPetugas[i].getTotalWaitingTime()>0&&arrayPetugas[i].getCounter()>0){
                System.out.println("waiting time : petugas "+arrayPetugas[i].getTotalWaitingTime()+" counter : "+arrayPetugas[i].getCounter());
                double temp=arrayPetugas[i].getTotalWaitingTime()*1.0/((double)arrayPetugas[i].getCounter()*1.0);
                System.out.println("temp petugas waiting time :"+temp);
                bd = new BigDecimal(temp); 
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                res[0][i]=this.convertSeconds(bd.doubleValue());
            }
            else{
                res[0][i]="00:00:00";
            }
        }
        return res;
    }
     
     public Object[][] generateAverageWaitingTime3(ServerPerawat[] arrayPetugas){
        Object[][] res=new Object[1][arrayPetugas.length];
        for(int i=0;i<arrayPetugas.length;i++){
            System.out.println("waiting time : petugas "+arrayPetugas[i].getTotalWaitingTime()+" counter : "+arrayPetugas[i].getCounter());
            if(arrayPetugas[i].getTotalWaitingTime()>0&&arrayPetugas[i].getCounter()>0){
                System.out.println("waiting time : petugas "+arrayPetugas[i].getTotalWaitingTime()+" counter : "+arrayPetugas[i].getCounter());
                double temp=arrayPetugas[i].getTotalWaitingTime()*1.0/((double)arrayPetugas[i].getCounter()*1.0);
                System.out.println("temp petugas waiting time :"+temp);
                bd = new BigDecimal(temp); 
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                res[0][i]=this.convertSeconds(bd.doubleValue());
            }
            else{
                res[0][i]="00:00:00";
            }
        }
        return res;
    }
     
      public Object[][] generateAverageWaitingTime3(ExcelInputSimulation.ServerPerawat[] arrayPetugas){
        Object[][] res=new Object[1][arrayPetugas.length];
        for(int i=0;i<arrayPetugas.length;i++){
            System.out.println("waiting time : petugas "+arrayPetugas[i].getTotalWaitingTime()+" counter : "+arrayPetugas[i].getCounter());
            if(arrayPetugas[i].getTotalWaitingTime()>0&&arrayPetugas[i].getCounter()>0){
                System.out.println("waiting time : petugas "+arrayPetugas[i].getTotalWaitingTime()+" counter : "+arrayPetugas[i].getCounter());
                double temp=arrayPetugas[i].getTotalWaitingTime()*1.0/((double)arrayPetugas[i].getCounter()*1.0);
                System.out.println("temp petugas waiting time :"+temp);
                bd = new BigDecimal(temp); 
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                res[0][i]=this.convertSeconds(bd.doubleValue());
            }
            else{
                res[0][i]="00:00:00";
            }
        }
        return res;
    }
     
     public Object[][] generateAverageWaitingTime4(ServerDokter[] arrayPetugas){
        Object[][] res=new Object[1][arrayPetugas.length];
        for(int i=0;i<arrayPetugas.length;i++){
            System.out.println("waiting time : petugas "+arrayPetugas[i].getTotalWaitingTime()+" counter : "+arrayPetugas[i].getCounter());
            if(arrayPetugas[i].getTotalWaitingTime()>0&&arrayPetugas[i].getCounter()>0){
                System.out.println("waiting time : petugas "+arrayPetugas[i].getTotalWaitingTime()+" counter : "+arrayPetugas[i].getCounter());
                double temp=arrayPetugas[i].getTotalWaitingTime()*1.0/((double)arrayPetugas[i].getCounter()*1.0);
                System.out.println("temp petugas waiting time :"+temp);
                bd = new BigDecimal(temp); 
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                res[0][i]=this.convertSeconds(bd.doubleValue());
            }
            else{
                res[0][i]="00:00:00";
            }
        }
        return res;
    }
     
      public Object[][] generateAverageWaitingTime4(ExcelInputSimulation.ServerDokter[] arrayPetugas){
        Object[][] res=new Object[1][arrayPetugas.length];
        for(int i=0;i<arrayPetugas.length;i++){
            System.out.println("waiting time : petugas "+arrayPetugas[i].getTotalWaitingTime()+" counter : "+arrayPetugas[i].getCounter());
            if(arrayPetugas[i].getTotalWaitingTime()>0&&arrayPetugas[i].getCounter()>0){
                System.out.println("waiting time : petugas "+arrayPetugas[i].getTotalWaitingTime()+" counter : "+arrayPetugas[i].getCounter());
                double temp=arrayPetugas[i].getTotalWaitingTime()*1.0/((double)arrayPetugas[i].getCounter()*1.0);
                System.out.println("temp petugas waiting time :"+temp);
                bd = new BigDecimal(temp); 
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                res[0][i]=this.convertSeconds(bd.doubleValue());
            }
            else{
                res[0][i]="00:00:00";
            }
        }
        return res;
    }
     
      public Object[][] generateAverageWaitingTime5(ExcelInputSimulation.ServerAwal[] arrayServer){
        Object[][] res=new Object[1][arrayServer.length];
        for(int i=0;i<arrayServer.length;i++){
            if(arrayServer[i].getTotalWaitingTime()>0&&arrayServer[i].getCounter()>0){
                double temp=arrayServer[i].getTotalWaitingTime()*1.0/((double)arrayServer[i].getCounter()*1.0);
                bd = new BigDecimal(temp); 
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                res[0][i]=this.convertSeconds(bd.doubleValue());
            }
            else{
                res[0][i]="00:00:00";
            }
        }
        return res;
    }
      
      public Object[][] generateAverageWaitingTime6(ExcelInputSimulation.ServerPetugas[] arrayPetugas){
        Object[][] res=new Object[1][arrayPetugas.length];
        for(int i=0;i<arrayPetugas.length;i++){
            System.out.println("waiting time : petugas "+arrayPetugas[i].getTotalWaitingTime()+" counter : "+arrayPetugas[i].getCounter());
            if(arrayPetugas[i].getTotalWaitingTime()>0&&arrayPetugas[i].getCounter()>0){
                System.out.println("waiting time : petugas "+arrayPetugas[i].getTotalWaitingTime()+" counter : "+arrayPetugas[i].getCounter());
                double temp=arrayPetugas[i].getTotalWaitingTime()*1.0/((double)arrayPetugas[i].getCounter()*1.0);
                System.out.println("temp petugas waiting time :"+temp);
                bd = new BigDecimal(temp); 
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                res[0][i]=this.convertSeconds(bd.doubleValue());
            }
            else{
                res[0][i]="00:00:00";
            }
        }
        return res;
    }
     
     
     
     public Object[][] generateAverageDelayTime(ServerAwal[] arrayServer){
        Object[][] res=new Object[1][arrayServer.length];
        for(int i=0;i<arrayServer.length;i++){
            if(arrayServer[i].getTotalDelayTime()>0&&arrayServer[i].getCounter()>0){
                double temp=arrayServer[i].getTotalDelayTime()*1.0/((double)arrayServer[i].getCounter()*1.0);
                bd = new BigDecimal(temp); 
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                res[0][i]=this.convertSeconds(bd.doubleValue());
            }
            else{
                res[0][i]="00:00:00";
            }
        }
        return res;
    }
     
    public Object[][] generateAverageDelayTime2(ServerPetugas[] arrayServer){
        Object[][] res=new Object[1][arrayServer.length];
        for(int i=0;i<arrayServer.length;i++){
            System.out.println("delay time : petugas "+arrayServer[i].getTotalDelayTime()+" counter : "+arrayServer[i].getCounter());
            if(arrayServer[i].getTotalDelayTime()>0&&arrayServer[i].getCounter()>0){
                System.out.println("delay time : petugas "+arrayServer[i].getTotalDelayTime()+" counter : "+arrayServer[i].getCounter());
                double temp=arrayServer[i].getTotalDelayTime()*1.0/((double)arrayServer[i].getCounter()*1.0);
                System.out.println("temp petugas delay time :"+temp);
                bd = new BigDecimal(temp); 
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                res[0][i]=this.convertSeconds(bd.doubleValue());
            }
            else{
                res[0][i]="00:00:00";
            }
        }
        return res;
    }
    
     public Object[][] generateAverageDelayTime3(ServerPerawat[] arrayServer){
        Object[][] res=new Object[1][arrayServer.length];
        for(int i=0;i<arrayServer.length;i++){
            System.out.println("delay time : petugas "+arrayServer[i].getTotalDelayTime()+" counter : "+arrayServer[i].getCounter());
            if(arrayServer[i].getTotalDelayTime()>0&&arrayServer[i].getCounter()>0){
                System.out.println("delay time : petugas "+arrayServer[i].getTotalDelayTime()+" counter : "+arrayServer[i].getCounter());
                double temp=arrayServer[i].getTotalDelayTime()*1.0/((double)arrayServer[i].getCounter()*1.0);
                System.out.println("temp petugas delay time :"+temp);
                bd = new BigDecimal(temp); 
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                res[0][i]=this.convertSeconds(bd.doubleValue());
            }
            else{
                res[0][i]="00:00:00";
            }
        }
        return res;
    }
     
     public Object[][] generateAverageDelayTime3(ExcelInputSimulation.ServerPerawat[] arrayServer){
        Object[][] res=new Object[1][arrayServer.length];
        for(int i=0;i<arrayServer.length;i++){
            System.out.println("delay time : petugas "+arrayServer[i].getTotalDelayTime()+" counter : "+arrayServer[i].getCounter());
            if(arrayServer[i].getTotalDelayTime()>0&&arrayServer[i].getCounter()>0){
                System.out.println("delay time : petugas "+arrayServer[i].getTotalDelayTime()+" counter : "+arrayServer[i].getCounter());
                double temp=arrayServer[i].getTotalDelayTime()*1.0/((double)arrayServer[i].getCounter()*1.0);
                System.out.println("temp petugas delay time :"+temp);
                bd = new BigDecimal(temp); 
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                res[0][i]=this.convertSeconds(bd.doubleValue());
            }
            else{
                res[0][i]="00:00:00";
            }
        }
        return res;
    }
     
      public Object[][] generateAverageDelayTime4(ServerDokter[] arrayServer){
        Object[][] res=new Object[1][arrayServer.length];
        for(int i=0;i<arrayServer.length;i++){
            System.out.println("delay time : petugas "+arrayServer[i].getTotalDelayTime()+" counter : "+arrayServer[i].getCounter());
            if(arrayServer[i].getTotalDelayTime()>0&&arrayServer[i].getCounter()>0){
                System.out.println("delay time : petugas "+arrayServer[i].getTotalDelayTime()+" counter : "+arrayServer[i].getCounter());
                double temp=arrayServer[i].getTotalDelayTime()*1.0/((double)arrayServer[i].getCounter()*1.0);
                System.out.println("temp petugas delay time :"+temp);
                bd = new BigDecimal(temp); 
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                res[0][i]=this.convertSeconds(bd.doubleValue());
            }
            else{
                res[0][i]="00:00:00";
            }
        }
        return res;
    }
      
       public Object[][] generateAverageDelayTime4(ExcelInputSimulation.ServerDokter[] arrayServer){
        Object[][] res=new Object[1][arrayServer.length];
        for(int i=0;i<arrayServer.length;i++){
            System.out.println("delay time : petugas "+arrayServer[i].getTotalDelayTime()+" counter : "+arrayServer[i].getCounter());
            if(arrayServer[i].getTotalDelayTime()>0&&arrayServer[i].getCounter()>0){
                System.out.println("delay time : petugas "+arrayServer[i].getTotalDelayTime()+" counter : "+arrayServer[i].getCounter());
                double temp=arrayServer[i].getTotalDelayTime()*1.0/((double)arrayServer[i].getCounter()*1.0);
                System.out.println("temp petugas delay time :"+temp);
                bd = new BigDecimal(temp); 
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                res[0][i]=this.convertSeconds(bd.doubleValue());
            }
            else{
                res[0][i]="00:00:00";
            }
        }
        return res;
    }
      
     public Object[][] generateAverageDelayTime5(ExcelInputSimulation.ServerAwal[] arrayServer){
        Object[][] res=new Object[1][arrayServer.length];
        for(int i=0;i<arrayServer.length;i++){
            if(arrayServer[i].getTotalDelayTime()>0&&arrayServer[i].getCounter()>0){
                double temp=arrayServer[i].getTotalDelayTime()*1.0/((double)arrayServer[i].getCounter()*1.0);
                bd = new BigDecimal(temp); 
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                res[0][i]=this.convertSeconds(bd.doubleValue());
            }
            else{
                res[0][i]="00:00:00";
            }
        }
        return res;
    }
     
      public Object[][] generateAverageDelayTime6(ExcelInputSimulation.ServerPetugas[] arrayServer){
        Object[][] res=new Object[1][arrayServer.length];
        for(int i=0;i<arrayServer.length;i++){
            System.out.println("delay time : petugas "+arrayServer[i].getTotalDelayTime()+" counter : "+arrayServer[i].getCounter());
            if(arrayServer[i].getTotalDelayTime()>0&&arrayServer[i].getCounter()>0){
                System.out.println("delay time : petugas "+arrayServer[i].getTotalDelayTime()+" counter : "+arrayServer[i].getCounter());
                double temp=arrayServer[i].getTotalDelayTime()*1.0/((double)arrayServer[i].getCounter()*1.0);
                System.out.println("temp petugas delay time :"+temp);
                bd = new BigDecimal(temp); 
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                res[0][i]=this.convertSeconds(bd.doubleValue());
            }
            else{
                res[0][i]="00:00:00";
            }
        }
        return res;
    }
    
     
     public String generateSummaryOutput(ServerAwal[] arrayServer){
         String first="Output Summary Pendaftaran Awal\n \n";
         
         first+="Jumlah loket pendaftaran awal : "+arrayServer.length+" \n \n";
        
         double totalsystemspenttime=this.getMaxDepartureTimeServer(arrayServer)-this.getFirstArrivalTime(arrayServer);
         bd = new BigDecimal(totalsystemspenttime);
         bd = bd.setScale(2,BigDecimal.ROUND_UP);
         String totalsystemspenttime2=this.convertSeconds(totalsystemspenttime);
         first+="Total System Spent Time : "+totalsystemspenttime2+" \n \n";
         
         String servicetime=this.convertSeconds(this.generateAverageServiceTimeTotal(arrayServer));
         first+="Rata-rata waktu pelayanan : "+servicetime+" \n \n";
         
         String waitingtime=this.convertSeconds(this.generateAverageWaitingTimeTotal(arrayServer));
         first+="Rata-rata waktu tunggu : "+waitingtime+" \n \n";
         
         String delaytime=this.convertSeconds(this.generateAverageDelayTimeTotal(arrayServer));
         first+="Rata-rata waktu delay: "+delaytime+" \n \n";
         
//         double jumlahpasien=(this.getTotalPasien(arrayServer))/((this.getMaxDepartureTimeServer(arrayServer)*24*60)*(24*60*this.generateAverageWaitingTimeTotal(arrayServer)));
//         bd = new BigDecimal(jumlahpasien);
//         bd = bd.setScale(2,BigDecimal.ROUND_UP);
//         first+="JUMLAH PASIEN PADA SERVICE NODE DI WAKTU KE t : "+(int)jumlahpasien+" \n \n";
//         
//         double jumlahpasien2=(this.getTotalPasien(arrayServer))/((this.getMaxDepartureTimeServer(arrayServer)*24*60)*(this.generateAverageDelayTimeTotal(arrayServer)*24*60));
//         bd = new BigDecimal(jumlahpasien2);
//         bd = bd.setScale(2,BigDecimal.ROUND_UP); 
//         first+="JUMLAH PASIEN PADA ANTRIAN DI WAKTU KE t : "+(int)jumlahpasien2+" \n \n";
//         
//         
//         double jumlahpasien3=(this.getTotalPasien(arrayServer))/((this.getMaxDepartureTimeServer(arrayServer)*24*60)*(this.generateAverageServiceTimeTotal(arrayServer)*24*60));
//         bd = new BigDecimal(jumlahpasien3);
//         bd = bd.setScale(2,BigDecimal.ROUND_UP);
//         first+="JUMLAH PASIEN PADA PELAYANAN DI WAKTU KE t  : "+(int)jumlahpasien3+" \n \n";
             return first;
     }
     
     
      public String generateSummaryOutput2(ExcelInputSimulation.ServerAwal[] arrayServer){
         String first="Output Summary Pendaftaran Awal\n \n";
         
         first+="Jumlah loket pendaftaran awal : "+arrayServer.length+" \n \n";
        
         double totalsystemspenttime=this.getMaxDepartureTimeServer5(arrayServer)-this.getFirstArrivalTime5(arrayServer);
         bd = new BigDecimal(totalsystemspenttime);
         bd = bd.setScale(2,BigDecimal.ROUND_UP);
         String totalsystemspenttime2=this.convertSeconds(totalsystemspenttime);
         first+="Total System Spent Time : "+totalsystemspenttime2+" \n \n";
         
         String servicetime=this.convertSeconds(this.generateAverageServiceTimeTotal5(arrayServer));
         first+="Rata-rata waktu pelayanan : "+servicetime+" \n \n";
         
         String waitingtime=this.convertSeconds(this.generateAverageWaitingTimeTotal2(arrayServer));
         first+="Rata-rata waktu tunggu : "+waitingtime+" \n \n";
         
         String delaytime=this.convertSeconds(this.generateAverageDelayTimeTotal5(arrayServer));
         first+="Rata-rata waktu delay: "+delaytime+" \n \n";
         
         return first;
      }
      
     
      public String generateSummaryOutputPetugas(ServerPetugas[] arrayServer){
         String first="Output Summary Petugas\n \n";
         
         first+="Jumlah petugas poliklinik : "+arrayServer.length+" \n \n";
        
         double totalsystemspenttime=this.getMaxDepartureTimeServer2(arrayServer)-this.getFirstArrivalTime2(arrayServer);
         bd = new BigDecimal(totalsystemspenttime);
         bd = bd.setScale(2,BigDecimal.ROUND_UP);
         String totalsystemspenttime2=this.convertSeconds(totalsystemspenttime);
         first+="Total System Spent Time : "+totalsystemspenttime2+" \n \n";
         
         String servicetime=this.convertSeconds(this.generateAverageServiceTimeTotal2(arrayServer));
         first+="Rata-rata waktu pelayanan : "+servicetime+" \n \n";
         
         String waitingtime=this.convertSeconds(this.generateAverageWaitingTimeTotal2(arrayServer));
         first+="Rata-rata waktu tunggu : "+waitingtime+" \n \n";
         
         String delaytime=this.convertSeconds(this.generateAverageDelayTimeTotal2(arrayServer));
         first+="Rata-rata waktu delay: "+delaytime+" \n \n";
         
         return first;
      }
      
      public String generateSummaryOutputPetugas(ExcelInputSimulation.ServerPetugas[] arrayServer){
         String first="Output Summary Petugas\n \n";
         
         first+="Jumlah petugas poliklinik : "+arrayServer.length+" \n \n";
        
         double totalsystemspenttime=this.getMaxDepartureTimeServer2(arrayServer)-this.getFirstArrivalTime2(arrayServer);
         bd = new BigDecimal(totalsystemspenttime);
         bd = bd.setScale(2,BigDecimal.ROUND_UP);
         String totalsystemspenttime2=this.convertSeconds(totalsystemspenttime);
         first+="Total System Spent Time : "+totalsystemspenttime2+" \n \n";
         
         String servicetime=this.convertSeconds(this.generateAverageServiceTimeTotal2(arrayServer));
         first+="Rata-rata waktu pelayanan : "+servicetime+" \n \n";
         
         String waitingtime=this.convertSeconds(this.generateAverageWaitingTimeTotal2(arrayServer));
         first+="Rata-rata waktu tunggu : "+waitingtime+" \n \n";
         
         String delaytime=this.convertSeconds(this.generateAverageDelayTimeTotal2(arrayServer));
         first+="Rata-rata waktu delay: "+delaytime+" \n \n";
         
         return first;
      }
      
      
     public String generateSummaryOutputPerawat(ServerPerawat[] arrayServer){
         String first="Output Summary Perawat \n \n";
         
         first+="Jumlah perawat poliklinik : "+arrayServer.length+" \n \n";
        
         double totalsystemspenttime=this.getMaxDepartureTimeServer3(arrayServer)-this.getFirstArrivalTime3(arrayServer);
         bd = new BigDecimal(totalsystemspenttime);
         bd = bd.setScale(2,BigDecimal.ROUND_UP);
         String totalsystemspenttime2=this.convertSeconds(totalsystemspenttime);
         first+="Total System Spent Time : "+totalsystemspenttime2+" \n \n";
         
         String servicetime=this.convertSeconds(this.generateAverageServiceTimeTotal3(arrayServer));
         first+="Rata-rata waktu pelayanan : "+servicetime+" \n \n";
         
         String waitingtime=this.convertSeconds(this.generateAverageWaitingTimeTotal3(arrayServer));
         first+="Rata-rata waktu tunggu : "+waitingtime+" \n \n";
         
         String delaytime=this.convertSeconds(this.generateAverageDelayTimeTotal3(arrayServer));
         first+="Rata-rata waktu delay: "+delaytime+" \n \n";
         
         return first;
      }
     
     public String generateSummaryOutputPerawat(ExcelInputSimulation.ServerPerawat[] arrayServer){
         String first="Output Summary Perawat \n \n";
         
         first+="Jumlah perawat poliklinik : "+arrayServer.length+" \n \n";
        
         double totalsystemspenttime=this.getMaxDepartureTimeServer3(arrayServer)-this.getFirstArrivalTime3(arrayServer);
         bd = new BigDecimal(totalsystemspenttime);
         bd = bd.setScale(2,BigDecimal.ROUND_UP);
         String totalsystemspenttime2=this.convertSeconds(totalsystemspenttime);
         first+="Total System Spent Time : "+totalsystemspenttime2+" \n \n";
         
         String servicetime=this.convertSeconds(this.generateAverageServiceTimeTotal3(arrayServer));
         first+="Rata-rata waktu pelayanan : "+servicetime+" \n \n";
         
         String waitingtime=this.convertSeconds(this.generateAverageWaitingTimeTotal3(arrayServer));
         first+="Rata-rata waktu tunggu : "+waitingtime+" \n \n";
         
         String delaytime=this.convertSeconds(this.generateAverageDelayTimeTotal3(arrayServer));
         first+="Rata-rata waktu delay: "+delaytime+" \n \n";
         
         return first;
      }
     
      public String generateSummaryOutputDokter(ServerDokter[] arrayServer){
         String first="Output Summary Dokter \n \n";
         
         first+="Jumlah dokter poliklinik : "+arrayServer.length+" \n \n";
        
         double totalsystemspenttime=this.getMaxDepartureTimeServer4(arrayServer)-this.getFirstArrivalTime4(arrayServer);
         bd = new BigDecimal(totalsystemspenttime);
         bd = bd.setScale(2,BigDecimal.ROUND_UP);
         String totalsystemspenttime2=this.convertSeconds(totalsystemspenttime);
         first+="Total System Spent Time : "+totalsystemspenttime2+" \n \n";
         
         String servicetime=this.convertSeconds(this.generateAverageServiceTimeTotal4(arrayServer));
         first+="Rata-rata waktu pelayanan : "+servicetime+" \n \n";
         
         String waitingtime=this.convertSeconds(this.generateAverageWaitingTimeTotal4(arrayServer));
         first+="Rata-rata waktu tunggu : "+waitingtime+" \n \n";
         
         String delaytime=this.convertSeconds(this.generateAverageDelayTimeTotal4(arrayServer));
         first+="Rata-rata waktu delay: "+delaytime+" \n \n";
         
         return first;
      }
      
      public String generateSummaryOutputDokter(ExcelInputSimulation.ServerDokter[] arrayServer){
         String first="Output Summary Dokter \n \n";
         
         first+="Jumlah dokter poliklinik : "+arrayServer.length+" \n \n";
        
         double totalsystemspenttime=this.getMaxDepartureTimeServer4(arrayServer)-this.getFirstArrivalTime4(arrayServer);
         bd = new BigDecimal(totalsystemspenttime);
         bd = bd.setScale(2,BigDecimal.ROUND_UP);
         String totalsystemspenttime2=this.convertSeconds(totalsystemspenttime);
         first+="Total System Spent Time : "+totalsystemspenttime2+" \n \n";
         
         String servicetime=this.convertSeconds(this.generateAverageServiceTimeTotal4(arrayServer));
         first+="Rata-rata waktu pelayanan : "+servicetime+" \n \n";
         
         String waitingtime=this.convertSeconds(this.generateAverageWaitingTimeTotal4(arrayServer));
         first+="Rata-rata waktu tunggu : "+waitingtime+" \n \n";
         
         String delaytime=this.convertSeconds(this.generateAverageDelayTimeTotal4(arrayServer));
         first+="Rata-rata waktu delay: "+delaytime+" \n \n";
         
         return first;
      }
     
     
     public int getTotalPasien(ServerAwal[] arrayServer){
         int total=0;
         for(int i=0;i<arrayServer.length;i++){
             LinkedList<Customer> cust=arrayServer[i].getQueueReport2();
             total+=cust.size();
         }
         return total;
     }
     
     public double getFirstArrivalTime(ServerAwal[] arrayServer){
         bd = new BigDecimal(arrayServer[0].getQueueReport2().get(0).getTimeServiceBegin());
         bd = bd.setScale(2,BigDecimal.ROUND_UP);
         return bd.doubleValue();
     }
     
     public double getFirstArrivalTime2(ServerPetugas[] arrayServer){
         if(arrayServer[0].getQueueReport2().size()>0){
            bd = new BigDecimal(arrayServer[0].getQueueReport2().get(0).getTimeServiceBegin2());
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
             return 0;
         }
     }
     
     public double getFirstArrivalTime2(ExcelInputSimulation.ServerPetugas[] arrayServer){
         if(arrayServer[0].getQueueReport2().size()>0){
            bd = new BigDecimal(arrayServer[0].getQueueReport2().get(0).getTimeServiceBegin2());
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
             return 0;
         }
     }
     
     public double getFirstArrivalTime3(ServerPerawat[] arrayServer){
         if(arrayServer[0].getQueueReport2().size()>0){
            bd = new BigDecimal(arrayServer[0].getQueueReport2().get(0).getTimeServiceBegin3());
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
             return 0;
         }
     }
     
      public double getFirstArrivalTime3(ExcelInputSimulation.ServerPerawat[] arrayServer){
         if(arrayServer[0].getQueueReport2().size()>0){
            bd = new BigDecimal(arrayServer[0].getQueueReport2().get(0).getTimeServiceBegin3());
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
             return 0;
         }
     }
     
     public double getFirstArrivalTime4(ServerDokter[] arrayServer){
         if(arrayServer[0].getQueueReport2().size()>0){
            bd = new BigDecimal(arrayServer[0].getQueueReport2().get(0).getTimeServiceBegin4());
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
             return 0;
         }
     }
     
     public double getFirstArrivalTime4(ExcelInputSimulation.ServerDokter[] arrayServer){
         if(arrayServer[0].getQueueReport2().size()>0){
            bd = new BigDecimal(arrayServer[0].getQueueReport2().get(0).getTimeServiceBegin4());
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
             return 0;
         }
     }
     
     public double getFirstArrivalTime5(ExcelInputSimulation.ServerAwal[] arrayServer){
         bd = new BigDecimal(arrayServer[0].getQueueReport2().get(0).getTimeServiceBegin());
         bd = bd.setScale(2,BigDecimal.ROUND_UP);
         return bd.doubleValue();
     }
     
     public double generateAverageWaitingTimeTotal(ServerAwal[] arrayServer){
         double waitingtime=0;
         int size=0;
         for(int i=0;i<arrayServer.length;i++){
             LinkedList<Customer> customer=arrayServer[i].getQueueReport2();
             size+=customer.size();
             for(int k=0;k<customer.size();k++){
                 waitingtime+=customer.get(k).getWaitingtime();
             }
         }
         if(waitingtime>0&&size>0){
            double res=waitingtime/(size);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
             return 0;
         }
     }
     
     public double generateAverageWaitingTimeTotal2(ExcelInputSimulation.ServerAwal[] arrayServer){
         double waitingtime=0;
         int size=0;
         for(int i=0;i<arrayServer.length;i++){
             LinkedList<Customer> customer=arrayServer[i].getQueueReport2();
             size+=customer.size();
             for(int k=0;k<customer.size();k++){
                 waitingtime+=customer.get(k).getWaitingtime();
             }
         }
         if(waitingtime>0&&size>0){
            double res=waitingtime/(size);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
             return 0;
         }
     }
     
      public double generateAverageWaitingTimeTotal2(ServerPetugas[] arrayServer){
         double waitingtime=0;
         int size=0;
         for(int i=0;i<arrayServer.length;i++){
             LinkedList<Customer> customer=arrayServer[i].getQueueReport2();
             size+=customer.size();
             for(int k=0;k<customer.size();k++){
                 waitingtime+=customer.get(k).getWaitingtimepoli();
             }
         }
         if(waitingtime>0&&size>0){
            double res=waitingtime/(size);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
             return 0;
         }
     }
      
       public double generateAverageWaitingTimeTotal2(ExcelInputSimulation.ServerPetugas[] arrayServer){
         double waitingtime=0;
         int size=0;
         for(int i=0;i<arrayServer.length;i++){
             LinkedList<Customer> customer=arrayServer[i].getQueueReport2();
             size+=customer.size();
             for(int k=0;k<customer.size();k++){
                 waitingtime+=customer.get(k).getWaitingtimepoli();
             }
         }
         if(waitingtime>0&&size>0){
            double res=waitingtime/(size);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
             return 0;
         }
     }
      
      public double generateAverageWaitingTimeTotal3(ServerPerawat[] arrayServer){
         double waitingtime=0;
         int size=0;
         for(int i=0;i<arrayServer.length;i++){
             LinkedList<Customer> customer=arrayServer[i].getQueueReport2();
             size+=customer.size();
             for(int k=0;k<customer.size();k++){
                 waitingtime+=customer.get(k).getWaitingtimepoli2();
             }
         }
         if(waitingtime>0&&size>0){
            double res=waitingtime/(size);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
             return 0;
         }
     }
      
       public double generateAverageWaitingTimeTotal3(ExcelInputSimulation.ServerPerawat[] arrayServer){
         double waitingtime=0;
         int size=0;
         for(int i=0;i<arrayServer.length;i++){
             LinkedList<Customer> customer=arrayServer[i].getQueueReport2();
             size+=customer.size();
             for(int k=0;k<customer.size();k++){
                 waitingtime+=customer.get(k).getWaitingtimepoli2();
             }
         }
         if(waitingtime>0&&size>0){
            double res=waitingtime/(size);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
             return 0;
         }
     }
      
      public double generateAverageWaitingTimeTotal4(ServerDokter[] arrayServer){
         double waitingtime=0;
         int size=0;
         for(int i=0;i<arrayServer.length;i++){
             LinkedList<Customer> customer=arrayServer[i].getQueueReport2();
             size+=customer.size();
             for(int k=0;k<customer.size();k++){
                 waitingtime+=customer.get(k).getWaitingtimepoli3();
             }
         }
         if(waitingtime>0&&size>0){
            double res=waitingtime/(size);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
             return 0;
         }
     }
      
       public double generateAverageWaitingTimeTotal4(ExcelInputSimulation.ServerDokter[] arrayServer){
         double waitingtime=0;
         int size=0;
         for(int i=0;i<arrayServer.length;i++){
             LinkedList<Customer> customer=arrayServer[i].getQueueReport2();
             size+=customer.size();
             for(int k=0;k<customer.size();k++){
                 waitingtime+=customer.get(k).getWaitingtimepoli3();
             }
         }
         if(waitingtime>0&&size>0){
            double res=waitingtime/(size);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
             return 0;
         }
     }
     
      public double generateAverageDelayTimeTotal(ServerAwal[] arrayServer){
         double delaytime=0;
         int size=0;
         for(int i=0;i<arrayServer.length;i++){
             LinkedList<Customer> customer=arrayServer[i].getQueueReport2();
             size+=customer.size();
             for(int k=0;k<customer.size();k++){
                 delaytime+=customer.get(k).getDelaytime();
             }
         }
         if(delaytime>0&&size>0){
            double res=delaytime/(size);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
             return 0;
         }
     }
      
      
      public double generateAverageDelayTimeTotal2(ServerPetugas[] arrayServer){
         double delaytime=0;
         int size=0;
         for(int i=0;i<arrayServer.length;i++){
             LinkedList<Customer> customer=arrayServer[i].getQueueReport2();
             size+=customer.size();
             for(int k=0;k<customer.size();k++){
                 delaytime+=customer.get(k).getDelaytimepoli();
             }
         }
         if(delaytime>0&&size>0){
            double res=delaytime/(size);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
             return 0;
         }
     }
      
      public double generateAverageDelayTimeTotal2(ExcelInputSimulation.ServerPetugas[] arrayServer){
         double delaytime=0;
         int size=0;
         for(int i=0;i<arrayServer.length;i++){
             LinkedList<Customer> customer=arrayServer[i].getQueueReport2();
             size+=customer.size();
             for(int k=0;k<customer.size();k++){
                 delaytime+=customer.get(k).getDelaytimepoli();
             }
         }
         if(delaytime>0&&size>0){
            double res=delaytime/(size);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
             return 0;
         }
     }
      
      public double generateAverageDelayTimeTotal3(ServerPerawat[] arrayServer){
         double delaytime=0;
         int size=0;
         for(int i=0;i<arrayServer.length;i++){
             LinkedList<Customer> customer=arrayServer[i].getQueueReport2();
             size+=customer.size();
             for(int k=0;k<customer.size();k++){
                 delaytime+=customer.get(k).getDelaytimepoli2();
             }
         }
         if(delaytime>0&&size>0){
            double res=delaytime/(size);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
             return 0;
         }
     }
      
       public double generateAverageDelayTimeTotal3(ExcelInputSimulation.ServerPerawat[] arrayServer){
         double delaytime=0;
         int size=0;
         for(int i=0;i<arrayServer.length;i++){
             LinkedList<Customer> customer=arrayServer[i].getQueueReport2();
             size+=customer.size();
             for(int k=0;k<customer.size();k++){
                 delaytime+=customer.get(k).getDelaytimepoli2();
             }
         }
         if(delaytime>0&&size>0){
            double res=delaytime/(size);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
             return 0;
         }
     }
      
      public double generateAverageDelayTimeTotal4(ServerDokter[] arrayServer){
         double delaytime=0;
         int size=0;
         for(int i=0;i<arrayServer.length;i++){
             LinkedList<Customer> customer=arrayServer[i].getQueueReport2();
             size+=customer.size();
             for(int k=0;k<customer.size();k++){
                 delaytime+=customer.get(k).getDelaytimepoli3();
             }
         }
         if(delaytime>0&&size>0){
            double res=delaytime/(size);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
             return 0;
         }
     }
      
       public double generateAverageDelayTimeTotal4(ExcelInputSimulation.ServerDokter[] arrayServer){
         double delaytime=0;
         int size=0;
         for(int i=0;i<arrayServer.length;i++){
             LinkedList<Customer> customer=arrayServer[i].getQueueReport2();
             size+=customer.size();
             for(int k=0;k<customer.size();k++){
                 delaytime+=customer.get(k).getDelaytimepoli3();
             }
         }
         if(delaytime>0&&size>0){
            double res=delaytime/(size);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
             return 0;
         }
     }
      
      public double generateAverageDelayTimeTotal5(ExcelInputSimulation.ServerAwal[] arrayServer){
         double delaytime=0;
         int size=0;
         for(int i=0;i<arrayServer.length;i++){
             LinkedList<Customer> customer=arrayServer[i].getQueueReport2();
             size+=customer.size();
             for(int k=0;k<customer.size();k++){
                 delaytime+=customer.get(k).getDelaytime();
             }
         }
         if(delaytime>0&&size>0){
            double res=delaytime/(size);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
             return 0;
         }
     }
      
    public double generateAverageServiceTimeTotal(ServerAwal[] arrayServer){
         double servicetime=0;
         int size=0;
         for(int i=0;i<arrayServer.length;i++){
             LinkedList<Customer> customer=arrayServer[i].getQueueReport2();
             size+=customer.size();
             for(int k=0;k<customer.size();k++){
                 servicetime+=customer.get(k).getServicetime();
             }
         }
         if(servicetime>0&&size>0){
            double res=servicetime/(size);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
             return 0;
         }
     }
    
     public double generateAverageServiceTimeTotal2(ServerPetugas[] arrayServer){
         double servicetime=0;
         int size=0;
         for(int i=0;i<arrayServer.length;i++){
             LinkedList<Customer> customer=arrayServer[i].getQueueReport2();
             size+=customer.size();
             for(int k=0;k<customer.size();k++){
                 servicetime+=customer.get(k).getServicetimepoli();
             }
         }
         if(servicetime>0&&size>0){
            double res=servicetime/(size);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
             return 0;
         }
     }
     
      public double generateAverageServiceTimeTotal2(ExcelInputSimulation.ServerPetugas[] arrayServer){
         double servicetime=0;
         int size=0;
         for(int i=0;i<arrayServer.length;i++){
             LinkedList<Customer> customer=arrayServer[i].getQueueReport2();
             size+=customer.size();
             for(int k=0;k<customer.size();k++){
                 servicetime+=customer.get(k).getServicetimepoli();
             }
         }
         if(servicetime>0&&size>0){
            double res=servicetime/(size);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
             return 0;
         }
     }
     
     public double generateAverageServiceTimeTotal3(ServerPerawat[] arrayServer){
         double servicetime=0;
         int size=0;
         for(int i=0;i<arrayServer.length;i++){
             LinkedList<Customer> customer=arrayServer[i].getQueueReport2();
             size+=customer.size();
             for(int k=0;k<customer.size();k++){
                 servicetime+=customer.get(k).getServicetimepoli2();
             }
         }
         if(servicetime>0&&size>0){
            double res=servicetime/(size);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
             return 0;
         }
     }
     
     public double generateAverageServiceTimeTotal3(ExcelInputSimulation.ServerPerawat[] arrayServer){
         double servicetime=0;
         int size=0;
         for(int i=0;i<arrayServer.length;i++){
             LinkedList<Customer> customer=arrayServer[i].getQueueReport2();
             size+=customer.size();
             for(int k=0;k<customer.size();k++){
                 servicetime+=customer.get(k).getServicetimepoli2();
             }
         }
         if(servicetime>0&&size>0){
            double res=servicetime/(size);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
             return 0;
         }
     }
    
       public double generateAverageServiceTimeTotal4(ServerDokter[] arrayServer){
         double servicetime=0;
         int size=0;
         for(int i=0;i<arrayServer.length;i++){
             LinkedList<Customer> customer=arrayServer[i].getQueueReport2();
             size+=customer.size();
             for(int k=0;k<customer.size();k++){
                 servicetime+=customer.get(k).getServicetimepoli3();
             }
         }
         if(servicetime>0&&size>0){
            double res=servicetime/(size);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
             return 0;
         }
     }
       
     public double generateAverageServiceTimeTotal4(ExcelInputSimulation.ServerDokter[] arrayServer){
         double servicetime=0;
         int size=0;
         for(int i=0;i<arrayServer.length;i++){
             LinkedList<Customer> customer=arrayServer[i].getQueueReport2();
             size+=customer.size();
             for(int k=0;k<customer.size();k++){
                 servicetime+=customer.get(k).getServicetimepoli3();
             }
         }
         if(servicetime>0&&size>0){
            double res=servicetime/(size);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
             return 0;
         }
     }
       
     public double generateAverageServiceTimeTotal5(ExcelInputSimulation.ServerAwal[] arrayServer){
         double servicetime=0;
         int size=0;
         for(int i=0;i<arrayServer.length;i++){
             LinkedList<Customer> customer=arrayServer[i].getQueueReport2();
             size+=customer.size();
             for(int k=0;k<customer.size();k++){
                 servicetime+=customer.get(k).getServicetime();
             }
         }
         if(servicetime>0&&size>0){
            double res=servicetime/(size);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
             return 0;
         }
     }
     
      public double generateAverageServiceTimeTotal6(ExcelInputSimulation.ServerPetugas[] arrayServer){
         double servicetime=0;
         int size=0;
         for(int i=0;i<arrayServer.length;i++){
             LinkedList<Customer> customer=arrayServer[i].getQueueReport2();
             size+=customer.size();
             for(int k=0;k<customer.size();k++){
                 servicetime+=customer.get(k).getServicetimepoli();
             }
         }
         if(servicetime>0&&size>0){
            double res=servicetime/(size);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
             return 0;
         }
     }
       
       
    public double generateAverageServiceTimeBPJSLamaTotal(ServerAwal[] arrayServer){
         double servicetime=0;
         int size=0;
         for(int i=0;i<arrayServer.length;i++){
             LinkedList<Customer> customer=arrayServer[i].getQueueReport2();
             for(int k=0;k<customer.size();k++){
                 if(customer.get(k).getJenis().equals("BPJS Lama")){
                    servicetime+=customer.get(k).getServicetime();
                    size++;
                 }
             }
         }
         if(servicetime>0&&size>0){
            double res=servicetime/(size);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
             return 0;
         }
     }
    
    public double generateAverageServiceTimeBPJSLamaTotal2(ExcelInputSimulation.ServerAwal[] arrayServer){
         double servicetime=0;
         int size=0;
         for(int i=0;i<arrayServer.length;i++){
             LinkedList<Customer> customer=arrayServer[i].getQueueReport2();
             for(int k=0;k<customer.size();k++){
                 if(customer.get(k).getJenis().equals("BPJS Lama")){
                    servicetime+=customer.get(k).getServicetime();
                    size++;
                 }
             }
         }
         if(servicetime>0&&size>0){
            double res=servicetime/(size);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
             return 0;
         }
     }
    
    public double generateAverageServiceTimeBPJSLamaTotal2(ServerPetugas[] arrayServer){
         double servicetime=0;
         int size=0;
         for(int i=0;i<arrayServer.length;i++){
             LinkedList<Customer> customer=arrayServer[i].getQueueReport2();
             for(int k=0;k<customer.size();k++){
                 if(customer.get(k).getJenis().equals("BPJS Lama")){
                    servicetime+=customer.get(k).getServicetimepoli();
                    size++;
                 }
             }
         }
         if(servicetime>0&&size>0){
            double res=servicetime/(size);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
             return 0;
         }
     }
    
     public double generateAverageServiceTimeBPJSLamaTotal2(ExcelInputSimulation.ServerPetugas[] arrayServer){
         double servicetime=0;
         int size=0;
         for(int i=0;i<arrayServer.length;i++){
             LinkedList<Customer> customer=arrayServer[i].getQueueReport2();
             for(int k=0;k<customer.size();k++){
                 if(customer.get(k).getJenis().equals("BPJS Lama")){
                    servicetime+=customer.get(k).getServicetimepoli();
                    size++;
                 }
             }
         }
         if(servicetime>0&&size>0){
            double res=servicetime/(size);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
             return 0;
         }
     }
    
    public double generateAverageServiceTimeBPJSLamaTotal3(ServerPerawat[] arrayServer){
         double servicetime=0;
         int size=0;
         for(int i=0;i<arrayServer.length;i++){
             LinkedList<Customer> customer=arrayServer[i].getQueueReport2();
             for(int k=0;k<customer.size();k++){
                 if(customer.get(k).getJenis().equals("BPJS Lama")){
                    servicetime+=customer.get(k).getServicetimepoli2();
                    size++;
                 }
             }
         }
         if(servicetime>0&&size>0){
            double res=servicetime/(size);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
             return 0;
         }
     }
    
     public double generateAverageServiceTimeBPJSLamaTotal3(ExcelInputSimulation.ServerPerawat[] arrayServer){
         double servicetime=0;
         int size=0;
         for(int i=0;i<arrayServer.length;i++){
             LinkedList<Customer> customer=arrayServer[i].getQueueReport2();
             for(int k=0;k<customer.size();k++){
                 if(customer.get(k).getJenis().equals("BPJS Lama")){
                    servicetime+=customer.get(k).getServicetimepoli2();
                    size++;
                 }
             }
         }
         if(servicetime>0&&size>0){
            double res=servicetime/(size);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
             return 0;
         }
     }
    
    public double generateAverageServiceTimeBPJSLamaTotal4(ServerDokter[] arrayServer){
         double servicetime=0;
         int size=0;
         for(int i=0;i<arrayServer.length;i++){
             LinkedList<Customer> customer=arrayServer[i].getQueueReport2();
             for(int k=0;k<customer.size();k++){
                 if(customer.get(k).getJenis().equals("BPJS Lama")){
                    servicetime+=customer.get(k).getServicetimepoli3();
                    size++;
                 }
             }
         }
         if(servicetime>0&&size>0){
            double res=servicetime/(size);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
             return 0;
         }
     }
    
     public double generateAverageServiceTimeBPJSLamaTotal4(ExcelInputSimulation.ServerDokter[] arrayServer){
         double servicetime=0;
         int size=0;
         for(int i=0;i<arrayServer.length;i++){
             LinkedList<Customer> customer=arrayServer[i].getQueueReport2();
             for(int k=0;k<customer.size();k++){
                 if(customer.get(k).getJenis().equals("BPJS Lama")){
                    servicetime+=customer.get(k).getServicetimepoli3();
                    size++;
                 }
             }
         }
         if(servicetime>0&&size>0){
            double res=servicetime/(size);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
             return 0;
         }
     }
    
    
     public double generateAverageServiceTimeBPJSBaruTotal(ServerAwal[] arrayServer){
         double servicetime=0;
         int size=0;
         for(int i=0;i<arrayServer.length;i++){
             LinkedList<Customer> customer=arrayServer[i].getQueueReport2();
             for(int k=0;k<customer.size();k++){
                 if(customer.get(k).getJenis().equals("BPJS Baru")){
                    servicetime+=customer.get(k).getServicetime();
                    size++;
                 }
             }
         }
         if(servicetime>0&&size>0){
            double res=servicetime/(size);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
             return 0;
         }
     }
     
     public double generateAverageServiceTimeBPJSBaruTotal2(ExcelInputSimulation.ServerAwal[] arrayServer){
         double servicetime=0;
         int size=0;
         for(int i=0;i<arrayServer.length;i++){
             LinkedList<Customer> customer=arrayServer[i].getQueueReport2();
             for(int k=0;k<customer.size();k++){
                 if(customer.get(k).getJenis().equals("BPJS Baru")){
                    servicetime+=customer.get(k).getServicetime();
                    size++;
                 }
             }
         }
         if(servicetime>0&&size>0){
            double res=servicetime/(size);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
             return 0;
         }
     }
     
     public double generateAverageServiceTimeEmergency(ServerDokter[] arrayServer){
         double servicetime=0;
         int size=0;
         for(int i=0;i<arrayServer.length;i++){
             LinkedList<Customer> customer=arrayServer[i].getQueueReport2();
             for(int k=0;k<customer.size();k++){
                 if(customer.get(k).getJenis().equals("Emergency")){
                    servicetime+=customer.get(k).getServicetimepoli3();
                    size++;
                 }
             }
         }
         if(servicetime>0&&size>0){
            double res=servicetime/(size);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
             return 0;
         }
     }
     
      public double generateAverageServiceTimeEmergency(ExcelInputSimulation.ServerDokter[] arrayServer){
         double servicetime=0;
         int size=0;
         for(int i=0;i<arrayServer.length;i++){
             LinkedList<Customer> customer=arrayServer[i].getQueueReport2();
             for(int k=0;k<customer.size();k++){
                 if(customer.get(k).getJenis().equals("Emergency")){
                    servicetime+=customer.get(k).getServicetimepoli3();
                    size++;
                 }
             }
         }
         if(servicetime>0&&size>0){
            double res=servicetime/(size);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
             return 0;
         }
     }
     
     
     public double generateAverageServiceTimeBPJSBaruTotal2(ServerPetugas[] arrayServer){
         double servicetime=0;
         int size=0;
         for(int i=0;i<arrayServer.length;i++){
             LinkedList<Customer> customer=arrayServer[i].getQueueReport2();
             for(int k=0;k<customer.size();k++){
                 if(customer.get(k).getJenis().equals("BPJS Baru")){
                    servicetime+=customer.get(k).getServicetimepoli();
                    size++;
                 }
             }
         }
         if(servicetime>0&&size>0){
            double res=servicetime/(size);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
             return 0;
         }
     }
     
      public double generateAverageServiceTimeBPJSBaruTotal2(ExcelInputSimulation.ServerPetugas[] arrayServer){
         double servicetime=0;
         int size=0;
         for(int i=0;i<arrayServer.length;i++){
             LinkedList<Customer> customer=arrayServer[i].getQueueReport2();
             for(int k=0;k<customer.size();k++){
                 if(customer.get(k).getJenis().equals("BPJS Baru")){
                    servicetime+=customer.get(k).getServicetimepoli();
                    size++;
                 }
             }
         }
         if(servicetime>0&&size>0){
            double res=servicetime/(size);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
             return 0;
         }
     }
     
      public double generateAverageServiceTimeBPJSBaruTotal3(ServerPerawat[] arrayServer){
         double servicetime=0;
         int size=0;
         for(int i=0;i<arrayServer.length;i++){
             LinkedList<Customer> customer=arrayServer[i].getQueueReport2();
             for(int k=0;k<customer.size();k++){
                 if(customer.get(k).getJenis().equals("BPJS Baru")){
                    servicetime+=customer.get(k).getServicetimepoli2();
                    size++;
                 }
             }
         }
         if(servicetime>0&&size>0){
            double res=servicetime/(size);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
             return 0;
         }
     }
      
      public double generateAverageServiceTimeBPJSBaruTotal3(ExcelInputSimulation.ServerPerawat[] arrayServer){
         double servicetime=0;
         int size=0;
         for(int i=0;i<arrayServer.length;i++){
             LinkedList<Customer> customer=arrayServer[i].getQueueReport2();
             for(int k=0;k<customer.size();k++){
                 if(customer.get(k).getJenis().equals("BPJS Baru")){
                    servicetime+=customer.get(k).getServicetimepoli2();
                    size++;
                 }
             }
         }
         if(servicetime>0&&size>0){
            double res=servicetime/(size);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
             return 0;
         }
     }
      
       public double generateAverageServiceTimeBPJSBaruTotal4(ServerDokter[] arrayServer){
         double servicetime=0;
         int size=0;
         for(int i=0;i<arrayServer.length;i++){
             LinkedList<Customer> customer=arrayServer[i].getQueueReport2();
             for(int k=0;k<customer.size();k++){
                 if(customer.get(k).getJenis().equals("BPJS Baru")){
                    servicetime+=customer.get(k).getServicetimepoli3();
                    size++;
                 }
             }
         }
         if(servicetime>0&&size>0){
            double res=servicetime/(size);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
             return 0;
         }
     }
       
      public double generateAverageServiceTimeBPJSBaruTotal4(ExcelInputSimulation.ServerDokter[] arrayServer){
         double servicetime=0;
         int size=0;
         for(int i=0;i<arrayServer.length;i++){
             LinkedList<Customer> customer=arrayServer[i].getQueueReport2();
             for(int k=0;k<customer.size();k++){
                 if(customer.get(k).getJenis().equals("BPJS Baru")){
                    servicetime+=customer.get(k).getServicetimepoli3();
                    size++;
                 }
             }
         }
         if(servicetime>0&&size>0){
            double res=servicetime/(size);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
             return 0;
         }
     }
     
     
        public double generateAverageDelayTimeBPJSBaruTotal(ServerAwal[] arrayServer){
         double delaytime=0;
         int size=0;
         for(int i=0;i<arrayServer.length;i++){
             LinkedList<Customer> customer=arrayServer[i].getQueueReport2();
             for(int k=0;k<customer.size();k++){
                 if(customer.get(k).getJenis().equals("BPJS Baru")){
                   delaytime+=customer.get(k).getDelaytime();
                    size++;
                 }
             }
         }
         if(delaytime>0&&size>0){
            double res=delaytime/(size);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
             return 0;
         }
     }
        
         
        public double generateAverageDelayTimeBPJSLamaTotal(ServerAwal[] arrayServer){
         double delaytime=0;
         int size=0;
         for(int i=0;i<arrayServer.length;i++){
             LinkedList<Customer> customer=arrayServer[i].getQueueReport2();
             for(int k=0;k<customer.size();k++){
                 if(customer.get(k).getJenis().equals("BPJS Lama")){
                    delaytime+=customer.get(k).getDelaytime();
                    size++;
                 }
             }
         }
         if(delaytime>0&&size>0){
            double res=delaytime/(size);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
             return 0;
         }
     }

    public double generateAverageWaitingTimeBPJSLamaTotal(ServerAwal[] arrayServer){
         double waitingtime=0;
         int size=0;
         for(int i=0;i<arrayServer.length;i++){
             LinkedList<Customer> customer=arrayServer[i].getQueueReport2();
             for(int k=0;k<customer.size();k++){
                 if(customer.get(k).getJenis().equals("BPJS Lama")){
                    waitingtime+=customer.get(k).getWaitingtime();
                    size++;
                 }
             }
         }
         if(waitingtime>0&&size>0){
            double res=waitingtime/(size);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
             return 0;
         }
     }
     
    
    
    public double generateAverageWaitingTimeBPJSBaruTotal(ServerAwal[] arrayServer){
         double waitingtime=0;
         int size=0;
         for(int i=0;i<arrayServer.length;i++){
             LinkedList<Customer> customer=arrayServer[i].getQueueReport2();
             for(int k=0;k<customer.size();k++){
                 if(customer.get(k).getJenis().equals("BPJS Baru")){
                    waitingtime+=customer.get(k).getWaitingtime();
                    size++;
                 }
             }
         }
         if(waitingtime>0&&size>0){
            double res=waitingtime/(size);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
         }
         else{
            return 0; 
         }
     }
    
    public double generateServiceRate(ServerAwal[] arrayServer){
       if(this.generateAverageServiceTimeTotal(arrayServer)>0){
        double res=1/this.generateAverageServiceTimeTotal(arrayServer);
        bd = new BigDecimal(res);
        bd = bd.setScale(2,BigDecimal.ROUND_UP);
        return bd.doubleValue();
       }
       else{
           return 0;
       }
    }
    
    public double generateServiceRate5(ExcelInputSimulation.ServerAwal[] arrayServer){
       if(this.generateAverageServiceTimeTotal5(arrayServer)>0){
        double res=1/this.generateAverageServiceTimeTotal5(arrayServer);
        bd = new BigDecimal(res);
        bd = bd.setScale(2,BigDecimal.ROUND_UP);
        return bd.doubleValue();
       }
       else{
           return 0;
       }
    }
    
    public double generateServiceRate2(ServerPetugas[] arrayServer){
        if(this.generateAverageServiceTimeTotal2(arrayServer)>0){
            double res=1/this.generateAverageServiceTimeTotal2(arrayServer);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
        }
        else{
            return 0;
        }
    }
    
    public double generateServiceRate3(ServerPerawat[] arrayServer){
       if(this.generateAverageServiceTimeTotal3(arrayServer)>0){
        double res=1/this.generateAverageServiceTimeTotal3(arrayServer);
        bd = new BigDecimal(res);
        bd = bd.setScale(2,BigDecimal.ROUND_UP);
        return bd.doubleValue();
       }
       else{
           return 0;
       }
    }
    
    public double generateServiceRate3(ExcelInputSimulation.ServerPerawat[] arrayServer){
        if(this.generateAverageServiceTimeTotal3(arrayServer)>0){
            double res=1/this.generateAverageServiceTimeTotal3(arrayServer);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
        }
        else{
            return 0;
        }
    }
    
    public double generateServiceRate4(ServerDokter[] arrayServer){
       if(this.generateAverageServiceTimeTotal4(arrayServer)>0){
        double res=1/this.generateAverageServiceTimeTotal4(arrayServer);
        bd = new BigDecimal(res);
        bd = bd.setScale(2,BigDecimal.ROUND_UP);
        return bd.doubleValue();
       }
       else{
           return 0;
       }
    }
    
     public double generateServiceRate4(ExcelInputSimulation.ServerDokter[] arrayServer){
       if(this.generateAverageServiceTimeTotal4(arrayServer)>0){
        double res=1/this.generateAverageServiceTimeTotal4(arrayServer);
        bd = new BigDecimal(res);
        bd = bd.setScale(2,BigDecimal.ROUND_UP);
        return bd.doubleValue();
       }
       else{
           return 0;
       }
    }
    
    public double generateServiceRate6(ExcelInputSimulation.ServerPetugas[] arrayServer){
      if(this.generateAverageServiceTimeTotal6(arrayServer)>0){
        double res=1/this.generateAverageServiceTimeTotal6(arrayServer);
        bd = new BigDecimal(res);
        bd = bd.setScale(2,BigDecimal.ROUND_UP);
        return bd.doubleValue();
      }
      else{
          return 0;
      }
    }
    
    public double generateServiceRateBPJSLama(ServerAwal[] arrayServer){
        if(this.generateAverageServiceTimeBPJSLamaTotal(arrayServer)>0){
            double res=1/this.generateAverageServiceTimeBPJSLamaTotal(arrayServer);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
        }
        else{
            return 0;
        }
    }   
    
     public double generateServiceRateBPJSLama2(ServerPetugas[] arrayServer){
        if(this.generateAverageServiceTimeBPJSLamaTotal2(arrayServer)>0){
            double res=1/this.generateAverageServiceTimeBPJSLamaTotal2(arrayServer);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
        }
        else{
            return 0;
        }
    }  
     
    public double generateServiceRateBPJSLama3(ServerPerawat[] arrayServer){
        if(this.generateAverageServiceTimeBPJSLamaTotal3(arrayServer)>0){
            double res=1/this.generateAverageServiceTimeBPJSLamaTotal3(arrayServer);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
        }
        else{
            return 0;
        }
    } 
    
     public double generateServiceRateBPJSLama3(ExcelInputSimulation.ServerPerawat[] arrayServer){
        if(this.generateAverageServiceTimeBPJSLamaTotal3(arrayServer)>0){
            double res=1/this.generateAverageServiceTimeBPJSLamaTotal3(arrayServer);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
        }
        else{
            return 0;
        }
    } 
    
    public double generateServiceRateBPJSLama4(ServerDokter[] arrayServer){
        if(this.generateAverageServiceTimeBPJSLamaTotal4(arrayServer)>0){
            double res=1/this.generateAverageServiceTimeBPJSLamaTotal4(arrayServer);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
        }
        else{
            return 0;
        }
    }
    
     public double generateServiceRateBPJSLama4(ExcelInputSimulation.ServerDokter[] arrayServer){
        if(this.generateAverageServiceTimeBPJSLamaTotal4(arrayServer)>0){
            double res=1/this.generateAverageServiceTimeBPJSLamaTotal4(arrayServer);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
        }
        else{
            return 0;
        }
    }
    
     public double generateServiceRateBPJSLama5(ExcelInputSimulation.ServerAwal[] arrayServer){
        if(this.generateAverageServiceTimeBPJSLamaTotal2(arrayServer)>0){
            double res=1/this.generateAverageServiceTimeBPJSLamaTotal2(arrayServer);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
        }
        else{
            return 0;
        }
    }   
     
    public double generateServiceRateBPJSLama6(ExcelInputSimulation.ServerPetugas[] arrayServer){
        if(this.generateAverageServiceTimeBPJSLamaTotal2(arrayServer)>0){
            double res=1/this.generateAverageServiceTimeBPJSLamaTotal2(arrayServer);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
        }
        else{
            return 0;
        }
    }  
    
    public double generateServiceRateBPJSBaru(ServerAwal[] arrayServer){
        if(this.generateAverageServiceTimeBPJSBaruTotal(arrayServer)>0){
            double res=1/this.generateAverageServiceTimeBPJSBaruTotal(arrayServer);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
        }
        else{
            return 0;
        }
    }
     
    public double generateServiceRateBPJSBaru2(ServerPetugas[] arrayServer){
        if(this.generateAverageServiceTimeBPJSBaruTotal2(arrayServer)>0){
            double res=1/this.generateAverageServiceTimeBPJSBaruTotal2(arrayServer);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
        }
        else{
            return 0;
        }
    }
    
    public double generateServiceRateBPJSBaru2(ExcelInputSimulation.ServerPetugas[] arrayServer){
        if(this.generateAverageServiceTimeBPJSBaruTotal2(arrayServer)>0){
            double res=1/this.generateAverageServiceTimeBPJSBaruTotal2(arrayServer);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
        }
        else{
            return 0;
        }
    }
    
    public double generateServiceRateBPJSBaru3(ServerPerawat[] arrayServer){
        if(this.generateAverageServiceTimeBPJSBaruTotal3(arrayServer)>0){
            double res=1/this.generateAverageServiceTimeBPJSBaruTotal3(arrayServer);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
        }
        else{
            return 0;
        }
    }
    
    public double generateServiceRateBPJSBaru3(ExcelInputSimulation.ServerPerawat[] arrayServer){
        if(this.generateAverageServiceTimeBPJSBaruTotal3(arrayServer)>0){
            double res=1/this.generateAverageServiceTimeBPJSBaruTotal3(arrayServer);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
        }
        else{
            return 0;
        }
    }
    
    public double generateServiceRateBPJSBaru4(ServerDokter[] arrayServer){
        if(this.generateAverageServiceTimeBPJSBaruTotal4(arrayServer)>0){
            double res=1/this.generateAverageServiceTimeBPJSBaruTotal4(arrayServer);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
        }
        else{
            return 0;
        }
    }
    
    public double generateServiceRateBPJSBaru4(ExcelInputSimulation.ServerDokter[] arrayServer){
        if(this.generateAverageServiceTimeBPJSBaruTotal4(arrayServer)>0){
            double res=1/this.generateAverageServiceTimeBPJSBaruTotal4(arrayServer);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
        }
        else{
            return 0;
        }
    }
    
    public double generateServiceRateBPJSBaru5(ExcelInputSimulation.ServerAwal[] arrayServer){
        if(this.generateAverageServiceTimeBPJSBaruTotal2(arrayServer)>0){
            double res=1/this.generateAverageServiceTimeBPJSBaruTotal2(arrayServer);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
        }
        else{
            return 0;
        }
    }
    
    
     public double generateServiceRateEmergency(ServerDokter[] arrayServer){
        if(this.generateAverageServiceTimeEmergency(arrayServer)>0){
            double res=1/this.generateAverageServiceTimeEmergency(arrayServer);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
        }
        else{
            return 0;
        }
    }
     
     public double generateServiceRateEmergency(ExcelInputSimulation.ServerDokter[] arrayServer){
        if(this.generateAverageServiceTimeEmergency(arrayServer)>0){
            double res=1/this.generateAverageServiceTimeEmergency(arrayServer);
            bd = new BigDecimal(res);
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            return bd.doubleValue();
        }
        else{
            return 0;
        }
    }
    
     public double getMaxDepartureTimeServer(ServerAwal[] arrayServer){
         double[] maxDepartureTime=new double[arrayServer.length];
         for(int i=0;i<arrayServer.length;i++){
             maxDepartureTime[i]=this.getMaxDepartureTime(arrayServer[i].getQueueReport2());
         }
         double res=maxDepartureTime[0];
         for(int k=1;k<maxDepartureTime.length;k++){
             if(res<maxDepartureTime[k]){
                 res=maxDepartureTime[k];
             }
         }
         return res;
     }
     
      public double getMaxDepartureTimeServer2(ServerPetugas[] arrayServer){
         double[] maxDepartureTime=new double[arrayServer.length];
         for(int i=0;i<arrayServer.length;i++){
             maxDepartureTime[i]=this.getMaxDepartureTime2(arrayServer[i].getQueueReport2());
         }
         double res=maxDepartureTime[0];
         for(int k=1;k<maxDepartureTime.length;k++){
             if(res<maxDepartureTime[k]){
                 res=maxDepartureTime[k];
             }
         }
         return res;
     }
      
      public double getMaxDepartureTimeServer2(ExcelInputSimulation.ServerPetugas[] arrayServer){
         double[] maxDepartureTime=new double[arrayServer.length];
         for(int i=0;i<arrayServer.length;i++){
             maxDepartureTime[i]=this.getMaxDepartureTime2(arrayServer[i].getQueueReport2());
         }
         double res=maxDepartureTime[0];
         for(int k=1;k<maxDepartureTime.length;k++){
             if(res<maxDepartureTime[k]){
                 res=maxDepartureTime[k];
             }
         }
         return res;
     }
      
      public double getMaxDepartureTimeServer3(ServerPerawat[] arrayServer){
         double[] maxDepartureTime=new double[arrayServer.length];
         for(int i=0;i<arrayServer.length;i++){
             maxDepartureTime[i]=this.getMaxDepartureTime3(arrayServer[i].getQueueReport2());
         }
         double res=maxDepartureTime[0];
         for(int k=1;k<maxDepartureTime.length;k++){
             if(res<maxDepartureTime[k]){
                 res=maxDepartureTime[k];
             }
         }
         return res;
     }
      
       public double getMaxDepartureTimeServer3(ExcelInputSimulation.ServerPerawat[] arrayServer){
         double[] maxDepartureTime=new double[arrayServer.length];
         for(int i=0;i<arrayServer.length;i++){
             maxDepartureTime[i]=this.getMaxDepartureTime3(arrayServer[i].getQueueReport2());
         }
         double res=maxDepartureTime[0];
         for(int k=1;k<maxDepartureTime.length;k++){
             if(res<maxDepartureTime[k]){
                 res=maxDepartureTime[k];
             }
         }
         return res;
     }
      
      public double getMaxDepartureTimeServer4(ServerDokter[] arrayServer){
         double[] maxDepartureTime=new double[arrayServer.length];
         for(int i=0;i<arrayServer.length;i++){
             maxDepartureTime[i]=this.getMaxDepartureTime4(arrayServer[i].getQueueReport2());
         }
         double res=maxDepartureTime[0];
         for(int k=1;k<maxDepartureTime.length;k++){
             if(res<maxDepartureTime[k]){
                 res=maxDepartureTime[k];
             }
         }
         return res;
     }
      
       public double getMaxDepartureTimeServer4(ExcelInputSimulation.ServerDokter[] arrayServer){
         double[] maxDepartureTime=new double[arrayServer.length];
         for(int i=0;i<arrayServer.length;i++){
             maxDepartureTime[i]=this.getMaxDepartureTime4(arrayServer[i].getQueueReport2());
         }
         double res=maxDepartureTime[0];
         for(int k=1;k<maxDepartureTime.length;k++){
             if(res<maxDepartureTime[k]){
                 res=maxDepartureTime[k];
             }
         }
         return res;
     }
      
      public double getMaxDepartureTimeServer5(ExcelInputSimulation.ServerAwal[] arrayServer){
         double[] maxDepartureTime=new double[arrayServer.length];
         for(int i=0;i<arrayServer.length;i++){
             maxDepartureTime[i]=this.getMaxDepartureTime(arrayServer[i].getQueueReport2());
         }
         double res=maxDepartureTime[0];
         for(int k=1;k<maxDepartureTime.length;k++){
             if(res<maxDepartureTime[k]){
                 res=maxDepartureTime[k];
             }
         }
         return res;
     }
     
     public Object[][] generateTotalSpentTimeServer(ServerAwal[] arrayServer){
         Object[][] totalspenttime=new Object[1][arrayServer.length];
         for(int i=0;i<arrayServer.length;i++){
             int cust=arrayServer[i].getQueueReport2().size();
             if(cust>0){
                double max=arrayServer[i].getQueueReport2().get(cust-1).getTimeServiceEnd();
                System.out.println("max : "+this.convertSeconds(arrayServer[i].getQueueReport2().get(cust-1).getTimeServiceEnd()));
                double min=arrayServer[i].getQueueReport2().get(0).getArrivaltime();
                System.out.println("min : "+this.convertSeconds(arrayServer[i].getQueueReport2().get(0).getArrivaltime()));
                bd = new BigDecimal(max-min);
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                totalspenttime[0][i]=this.convertSeconds(bd.doubleValue());
             }
             else{
                 totalspenttime[0][i]="00:00:00";
             }
         }
         return totalspenttime;
     }
     
     public Object[][] generateTotalSpentTimeServer2(ServerPetugas[] arrayPetugas){
         Object[][] totalspenttime=new Object[1][arrayPetugas.length];
         for(int i=0;i<arrayPetugas.length;i++){
             int cust=arrayPetugas[i].getQueueReport2().size();
             System.out.println("cust : "+cust);
             if(cust>0){
                double max=arrayPetugas[i].getQueueReport2().get(cust-1).getTimeServiceEnd2();
                System.out.println("max cust petugas : "+this.convertSeconds(arrayPetugas[i].getQueueReport2().get(cust-1).getTimeServiceEnd2()));
                double min=arrayPetugas[i].getQueueReport2().get(0).getArrivaltimepoli();
                System.out.println("min cust petugas: "+this.convertSeconds(arrayPetugas[i].getQueueReport2().get(0).getArrivaltimepoli()));
                bd = new BigDecimal(max-min);
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                totalspenttime[0][i]=this.convertSeconds(bd.doubleValue());
             }
             else{
                 totalspenttime[0][i]="00:00:00";
             }
         }
         return totalspenttime;
     }
     
     public Object[][] generateTotalSpentTimeServer3(ServerPerawat[] arrayPetugas){
         Object[][] totalspenttime=new Object[1][arrayPetugas.length];
         for(int i=0;i<arrayPetugas.length;i++){
             int cust=arrayPetugas[i].getQueueReport2().size();
             System.out.println("cust : "+cust);
             if(cust>0){
                double max=arrayPetugas[i].getQueueReport2().get(cust-1).getTimeServiceEnd3();
                System.out.println("max cust petugas : "+this.convertSeconds(arrayPetugas[i].getQueueReport2().get(cust-1).getTimeServiceEnd2()));
                double min=arrayPetugas[i].getQueueReport2().get(0).getArrivaltimepoli2();
                System.out.println("min cust petugas: "+this.convertSeconds(arrayPetugas[i].getQueueReport2().get(0).getArrivaltimepoli()));
                bd = new BigDecimal(max-min);
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                totalspenttime[0][i]=this.convertSeconds(bd.doubleValue());
             }
             else{
                 totalspenttime[0][i]="00:00:00";
             }
         }
         return totalspenttime;
     }
     
     public Object[][] generateTotalSpentTimeServer3(ExcelInputSimulation.ServerPerawat[] arrayPetugas){
         Object[][] totalspenttime=new Object[1][arrayPetugas.length];
         for(int i=0;i<arrayPetugas.length;i++){
             int cust=arrayPetugas[i].getQueueReport2().size();
             System.out.println("cust : "+cust);
             if(cust>0){
                double max=arrayPetugas[i].getQueueReport2().get(cust-1).getTimeServiceEnd3();
                System.out.println("max cust petugas : "+this.convertSeconds(arrayPetugas[i].getQueueReport2().get(cust-1).getTimeServiceEnd2()));
                double min=arrayPetugas[i].getQueueReport2().get(0).getArrivaltimepoli2();
                System.out.println("min cust petugas: "+this.convertSeconds(arrayPetugas[i].getQueueReport2().get(0).getArrivaltimepoli()));
                bd = new BigDecimal(max-min);
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                totalspenttime[0][i]=this.convertSeconds(bd.doubleValue());
             }
             else{
                 totalspenttime[0][i]="00:00:00";
             }
         }
         return totalspenttime;
     }
     
     public Object[][] generateTotalSpentTimeServer4(ServerDokter[] arrayPetugas){
         Object[][] totalspenttime=new Object[1][arrayPetugas.length];
         for(int i=0;i<arrayPetugas.length;i++){
             int cust=arrayPetugas[i].getQueueReport2().size();
             System.out.println("cust : "+cust);
             if(cust>0){
                double max=arrayPetugas[i].getQueueReport2().get(cust-1).getTimeServiceEnd4();
                System.out.println("max cust petugas : "+this.convertSeconds(arrayPetugas[i].getQueueReport2().get(cust-1).getTimeServiceEnd2()));
                double min=arrayPetugas[i].getQueueReport2().get(0).getArrivaltimepoli3();
                System.out.println("min cust petugas: "+this.convertSeconds(arrayPetugas[i].getQueueReport2().get(0).getArrivaltimepoli()));
                bd = new BigDecimal(max-min);
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                totalspenttime[0][i]=this.convertSeconds(bd.doubleValue());
             }
             else{
                 totalspenttime[0][i]="00:00:00";
             }
         }
         return totalspenttime;
     }
     
     public Object[][] generateTotalSpentTimeServer4(ExcelInputSimulation.ServerDokter[] arrayPetugas){
         Object[][] totalspenttime=new Object[1][arrayPetugas.length];
         for(int i=0;i<arrayPetugas.length;i++){
             int cust=arrayPetugas[i].getQueueReport2().size();
             System.out.println("cust : "+cust);
             if(cust>0){
                double max=arrayPetugas[i].getQueueReport2().get(cust-1).getTimeServiceEnd4();
                System.out.println("max cust petugas : "+this.convertSeconds(arrayPetugas[i].getQueueReport2().get(cust-1).getTimeServiceEnd2()));
                double min=arrayPetugas[i].getQueueReport2().get(0).getArrivaltimepoli3();
                System.out.println("min cust petugas: "+this.convertSeconds(arrayPetugas[i].getQueueReport2().get(0).getArrivaltimepoli()));
                bd = new BigDecimal(max-min);
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                totalspenttime[0][i]=this.convertSeconds(bd.doubleValue());
             }
             else{
                 totalspenttime[0][i]="00:00:00";
             }
         }
         return totalspenttime;
     }
     
      public Object[][] generateTotalSpentTimeServer5(ExcelInputSimulation.ServerAwal[] arrayServer){
         Object[][] totalspenttime=new Object[1][arrayServer.length];
         for(int i=0;i<arrayServer.length;i++){
             int cust=arrayServer[i].getQueueReport2().size();
             if(cust>0){
                double max=arrayServer[i].getQueueReport2().get(cust-1).getTimeServiceEnd();
                System.out.println("max : "+this.convertSeconds(arrayServer[i].getQueueReport2().get(cust-1).getTimeServiceEnd()));
                double min=arrayServer[i].getQueueReport2().get(0).getArrivaltime();
                System.out.println("min : "+this.convertSeconds(arrayServer[i].getQueueReport2().get(0).getArrivaltime()));
                bd = new BigDecimal(max-min);
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                totalspenttime[0][i]=this.convertSeconds(bd.doubleValue());
             }
             else{
                 totalspenttime[0][i]="00:00:00";
             }
         }
         return totalspenttime;
     }
      
     public Object[][] generateTotalSpentTimeServer6(ExcelInputSimulation.ServerPetugas[] arrayPetugas){
         Object[][] totalspenttime=new Object[1][arrayPetugas.length];
         for(int i=0;i<arrayPetugas.length;i++){
             int cust=arrayPetugas[i].getQueueReport2().size();
             System.out.println("cust : "+cust);
             if(cust>0){
                double max=arrayPetugas[i].getQueueReport2().get(cust-1).getTimeServiceEnd2();
                System.out.println("max cust petugas : "+this.convertSeconds(arrayPetugas[i].getQueueReport2().get(cust-1).getTimeServiceEnd2()));
                double min=arrayPetugas[i].getQueueReport2().get(0).getArrivaltimepoli();
                System.out.println("min cust petugas: "+this.convertSeconds(arrayPetugas[i].getQueueReport2().get(0).getArrivaltimepoli()));
                bd = new BigDecimal(max-min);
                bd = bd.setScale(2,BigDecimal.ROUND_UP);
                totalspenttime[0][i]=this.convertSeconds(bd.doubleValue());
             }
             else{
                 totalspenttime[0][i]="00:00:00";
             }
         }
         return totalspenttime;
     }
     
     public double getMinDepartureTimeServer(ServerAwal[] arrayServer){
         double[] minDepartureTime=new double[arrayServer.length];
         for(int i=0;i<arrayServer.length;i++){
             minDepartureTime[i]=this.getMinDepartureTime(arrayServer[i].getQueueReport2());
         }
         double res=minDepartureTime[0];
         for(int k=1;k<minDepartureTime.length;k++){
             if(res>minDepartureTime[k]){
                 res=minDepartureTime[k];
             }
         }
         return res;
     }
     
     
     public double getMaxDepartureTime(LinkedList<Customer> customer){
         int cari=0;
         double compare=0;
         if(customer.size()>0){
            compare=customer.get(0).getTimeServiceEnd();
            for(int i=1;i<customer.size();i++){
                if(compare<customer.get(i).getTimeServiceEnd()){
                    compare=customer.get(i).getTimeServiceEnd();
                    cari=i;
                }
            }
         }
         return compare;
     }
     
     public double getMaxDepartureTime2(LinkedList<Customer> customer){
         int cari=0;
         double compare=0;
         if(customer.size()>0){
            compare=customer.get(0).getTimeServiceEnd2();
            for(int i=1;i<customer.size();i++){
                if(compare<customer.get(i).getTimeServiceEnd2()){
                    compare=customer.get(i).getTimeServiceEnd2();
                    cari=i;
                }
            }
         }
         return compare;
     }
     
     public double getMaxDepartureTime3(LinkedList<Customer> customer){
         int cari=0;
         double compare=0;
         if(customer.size()>0){
            compare=customer.get(0).getTimeServiceEnd3();
            for(int i=1;i<customer.size();i++){
                if(compare<customer.get(i).getTimeServiceEnd3()){
                    compare=customer.get(i).getTimeServiceEnd3();
                    cari=i;
                }
            }
         }
         return compare;
     }
     
      public double getMaxDepartureTime4(LinkedList<Customer> customer){
         int cari=0;
         double compare=0;
         if(customer.size()>0){
            compare=customer.get(0).getTimeServiceEnd4();
            for(int i=1;i<customer.size();i++){
                if(compare<customer.get(i).getTimeServiceEnd4()){
                    compare=customer.get(i).getTimeServiceEnd4();
                    cari=i;
                }
            }
         }
         return compare;
     }
     
     
     public double getMinDepartureTime(LinkedList<Customer> customer){
         int cari=0;
         double compare=0;
         if(customer.size()>0){
            compare=customer.get(0).getTimeServiceEnd();
            for(int i=1;i<customer.size();i++){
                if(compare>customer.get(i).getTimeServiceEnd()){
                    compare=customer.get(i).getTimeServiceEnd();
                    cari=i;
                }
            }
         }
         return compare;
     }
    
    public String[] generateColumnNamesServer(ServerAwal[] arrayServer){
        String [] columns=new String[arrayServer.length];
        for(int i=0;i<columns.length;i++){
            columns[i]="Loket ke - "+arrayServer[i].getServernumber();
        }
        return columns;
    }
     
    public String[] generateColumnNamesServer2(ServerPetugas[] arrayPetugas){
        String [] columns=new String[arrayPetugas.length];
        for(int i=0;i<columns.length;i++){
            columns[i]="Petugas ke - "+arrayPetugas[i].getServernumber();
        }
        return columns;
    }
    
     public String[] generateColumnNamesServer3(ServerPerawat[] arrayPetugas){
        String [] columns=new String[arrayPetugas.length];
        for(int i=0;i<columns.length;i++){
            columns[i]="Perawat ke - "+arrayPetugas[i].getServernumber();
        }
        return columns;
    }
     
     public String[] generateColumnNamesServer3(ExcelInputSimulation.ServerPerawat[] arrayPetugas){
        String [] columns=new String[arrayPetugas.length];
        for(int i=0;i<columns.length;i++){
            columns[i]="Perawat ke - "+arrayPetugas[i].getServernumber();
        }
        return columns;
    }
     
    public String[] generateColumnNamesServer4(ServerDokter[] arrayPetugas){
        String [] columns=new String[arrayPetugas.length];
        for(int i=0;i<columns.length;i++){
            columns[i]="Dokter ke - "+arrayPetugas[i].getServernumber();
        }
        return columns;
    }
    
     public String[] generateColumnNamesServer4(ExcelInputSimulation.ServerDokter[] arrayPetugas){
        String [] columns=new String[arrayPetugas.length];
        for(int i=0;i<columns.length;i++){
            columns[i]="Dokter ke - "+arrayPetugas[i].getServernumber();
        }
        return columns;
    }
    
    public String[] generateColumnNamesServer5(ExcelInputSimulation.ServerAwal[] arrayServer){
        String [] columns=new String[arrayServer.length];
        for(int i=0;i<columns.length;i++){
            columns[i]="Loket ke - "+arrayServer[i].getServernumber();
        }
        return columns;
    }
    
     public String[] generateColumnNamesServer6(ExcelInputSimulation.ServerPetugas[] arrayPetugas){
        String [] columns=new String[arrayPetugas.length];
        for(int i=0;i<columns.length;i++){
            columns[i]="Petugas ke - "+arrayPetugas[i].getServernumber();
        }
        return columns;
    }
    
    
   
   
     
    /**
     * @return the meanservicetime
     */
    public double getMeanservicetime() {
        return meanservicetime;
    }

    /**
     * @param meanservicetime the meanservicetime to set
     */
    public void setMeanservicetime(double meanservicetime) {
        this.meanservicetime = meanservicetime;
    }

    /**
     * @return the meanarrivaltime
     */
    public double getMeanarrivaltime() {
        return meanarrivaltime;
    }

    /**
     * @param meanarrivaltime the meanarrivaltime to set
     */
    public void setMeanarrivaltime(double meanarrivaltime) {
        this.meanarrivaltime = meanarrivaltime;
    }

    /**
     * @return the meanservicetime2
     */
    public double getMeanservicetime2() {
        return meanservicetime2;
    }

    /**
     * @param meanservicetime2 the meanservicetime2 to set
     */
    public void setMeanservicetime2(double meanservicetime2) {
        this.meanservicetime2 = meanservicetime2;
    }

    /**
     * @return the meanservicetimepoli
     */
    public double getMeanservicetimepoli() {
        return meanservicetimepoli;
    }

    /**
     * @param meanservicetimepoli the meanservicetimepoli to set
     */
    public void setMeanservicetimepoli(double meanservicetimepoli) {
        this.meanservicetimepoli = meanservicetimepoli;
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
    
    
}
