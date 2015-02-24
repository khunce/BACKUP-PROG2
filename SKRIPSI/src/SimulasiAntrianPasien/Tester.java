
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SimulasiAntrianPasien;

import ExcelInputSimulation.ExcelReader;
import RandomVariate.Exponential;
import RandomVariate.LCGRandom;
import RandomVariate.LCGRandom2;
import RandomVariate.Poisson;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Random;
/**
 *
 * @author robby
 */
public class Tester {
    public static void main(String[] args){
        Random rng=new Random();
        StatisticsGenerator gen=new StatisticsGenerator();
        LCGRandom2 random2=new LCGRandom2(1);
        Exponential e=new Exponential(0.33,1);
        Poisson p=new Poisson(5,(int)rng.nextInt(9)+1);
        LinkedList list=new LinkedList();
        list.add("x");
        for(int i=0;i<100;i++){
            System.out.print("exponential : "+e.gen());
        }
        System.out.println();
        for(int i=0;i<10;i++){
            System.out.print(0.0+p.gen()+" ");
        }
        System.out.println(rng.nextInt(3));
        System.out.println("TES Create Customer");
        System.out.println(System.currentTimeMillis()%(3600*60));
        double coba= 7.41;
        int up=(int)coba;
        System.out.println(up);
        double kurang=coba-up;
        System.out.println(kurang);
            BigDecimal bd = new BigDecimal(kurang); 
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            //kurang=bd.doubleValue();
            System.out.println(kurang);
            double seconds=kurang*60;
            System.out.println(seconds);
            System.out.println(Math.round(seconds));
//            if(up<10&&seconds<10){
//                ret="00:0"+up+":0"+(int)seconds;
//            }
//            else if(up>=10&&seconds<10){
//                ret="00:"+up+":0"+(int)seconds;
//            }
//            else if(up<10&&seconds>=10){
//                ret="00:0"+up+":"+(int)seconds;
//            }
//            else{
//                ret="00:"+up+":"+(int)seconds;
//            }
       System.out.println(gen.convertSeconds(coba));
//      }  Random r2=new Random();
//       System.out.println(r2.nextInt(2));
//       ExcelReader ex=new ExcelReader("E:\\demo_read_input.xlsx","Excel");
//       ex.createFile();
//       ex.readExcel();
//       LinkedList<Customer> cust=ex.getQueueOfCustomer();
//       for(int i=0;i<cust.size();i++){
//           System.out.println(cust.get(i).getNumber()+" "+cust.get(i).getArrivaltime()+" "+cust.get(i).getServicetime()+" "+cust.get(i).getJenis());
//       }
//       System.out.println(ex.getQueueOfCustomer().size()+" "+ex.getTotalbaru()+" "+ex.getTotallama()+" "+ex.getEmergency()+" "+ex.getTotalpasienpoli());
//   
       double r=0;
       bd = new BigDecimal(r); 
            bd = bd.setScale(2,BigDecimal.ROUND_UP);
            System.out.println(bd.doubleValue());
    }
    
    
    public static double exponential(Random rng, double mean) {
        return -mean * Math.log(rng.nextDouble());
    }  
}
