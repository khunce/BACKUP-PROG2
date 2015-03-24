
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SimulasiAntrianPasien;

import ExcelInputSimulation.ExcelReader;
import RandomVariate.Exponential;
import RandomVariate.Exponential2;
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
        StatisticsGenerator gen=new StatisticsGenerator();
        Exponential exp=new Exponential(0.35,2);
        for(int i=0;i<5;i++){
            System.out.println(gen.convertSeconds(exp.gen()));
        }
        Random rng=new Random();
        System.out.println(exponential(rng,6));
        Poisson p=new Poisson(0.32222222,2);
        System.out.println(p.gen());
        StatisticsGenerator gen2=new StatisticsGenerator();
        System.out.println(gen.convertSeconds(1.63));
        String x="demo_read_input_xlsx";
        System.out.println(x.endsWith(".xlsx"));
    }
    
    
    
    public static double exponential(Random rng, double mean) {
        return -mean * Math.log(rng.nextDouble());
    }  
}
