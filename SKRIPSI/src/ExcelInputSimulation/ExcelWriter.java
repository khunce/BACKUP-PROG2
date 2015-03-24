/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExcelInputSimulation;

import SimulasiAntrianPasien.Customer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriter
{
    private FileOutputStream file;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private Iterator<Row> rowIterator;
    private String filepath;
    private String filename;
    private LinkedList<Customer> queuecustomer;
    private int totalbaru;
    private int totallama;
    private int emergency;
    private int totalpasienpoli;
    private LinkedList<String> arrivaltime;
    private LinkedList<String> servicetime;
    private LinkedList<String>  servicepetugas;
    private LinkedList<String> serviceperawat;
    private LinkedList<String> servicedokter;
    private int serverawal;
    private int serverpetugas;
    private int serverperawat;
    private int serverdokter;
    private int kapasitasantrian;
    
    public ExcelWriter(String filepath){
        this.filepath=filepath;
        this.filename=filename;
        
    }
    
    public boolean writeExcelFileReportAwal(String[] columnNames,Object[][] data2,Object[] arrivaltime,Object[] inter,Object[] service,Object[][] averageservicetime,Object[][] averagewaitingtime, Object[][] averagedelaytime,Object[][] totalspenttime,String s){
        boolean success=true;
        XSSFWorkbook workbook = new XSSFWorkbook();
         
        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("Tabel Report Pendaftaran Awal");
          
        //This data needs to be written (Object[])
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
       
        //write average service time
        Object[] column2=new Object[columnNames.length];
        for(int i=0;i<columnNames.length;i++){
            column2[i]="Average Service Time "+columnNames[i];
        }
        int m=2;
        data.put(m+"",column2);
        Object[] temp2=new Object[column2.length];
        m++;
        for(int i=0;i<temp2.length;i++){
            temp2[i]=averageservicetime[0][i];
        }
        data.put(m+"",temp2);
        m++;
        
        //write average watitng time
        Object[] column3=new Object[columnNames.length];
        for(int i=0;i<columnNames.length;i++){
            column3[i]="Average Waiting Time "+columnNames[i];
        }
        data.put(m+"",column3);
        Object[] temp3=new Object[column3.length];
        m++;
        for(int i=0;i<temp3.length;i++){
            temp3[i]=averagewaitingtime[0][i];
        }
        data.put(m+"",temp3);
        m++;
        
        //write average delay time
        Object[] column4=new Object[columnNames.length];
        for(int i=0;i<columnNames.length;i++){
            column4[i]="Average Delay Time "+columnNames[i];
        }
        data.put(m+"",column4);
        Object[] temp4=new Object[column4.length];
        m++;
        for(int i=0;i<temp4.length;i++){
            temp4[i]=averagedelaytime[0][i];
        }
        data.put(m+"",temp4);
        m++;
        
        //write total spent time
        Object[] column5=new Object[columnNames.length];
        for(int i=0;i<columnNames.length;i++){
            column5[i]="Total Spent Time "+columnNames[i];
        }
        data.put(m+"",column5);
        Object[] temp5=new Object[column5.length];
        m++;
        for(int i=0;i<temp5.length;i++){
            temp5[i]=totalspenttime[0][i];
        }
        data.put(m+"",temp5);
        m++;
        
         //write utility server
        Object[] column=new Object[columnNames.length];
        for(int i=0;i<columnNames.length;i++){
            column[i]="Utility Server "+columnNames[i];
        }
        data.put(m+"",column);
        m++;
        Object[] temp=new Object[column.length];
        for(int i=0;i<temp.length;i++){
            temp[i]=data2[0][i]+" ";
        }
        data.put(m+"",temp);
        m++;
        
                
        //wriTe arrivaltime
        data.put(m+"",new Object[]{"Summary Arrival Rate","Arrival Rate BPJS Lama","Arrival Rate BPJS Baru"});
        m++;
        data.put(m+"",arrivaltime);
        m++;
        
        //write interarrivaltime
        data.put(m+"",new Object[]{"Summary Interarrival Time","Interarrival time BPJS Lama","Interarrival time BPJS Baru"});
        m++;
        data.put(m+"",inter);
        m++;
        
        //write service rate
        data.put(m+"",new Object[]{"Summary Service Rate","Service Rate BPJS Lama","Service Rate BPJS Baru"});
        m++;
        data.put(m+"",service);
        m++;
        
        //write summary
        data.put(m+"",new Object[]{"Summary Pendaftaran Awal"});
        m++;
        data.put(m+"",new Object[]{s});

        //Iterate over data and write to sheet
        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset)
        {
            Row row = sheet.createRow(rownum++);
            Object [] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr)
            {
               Cell cell = row.createCell(cellnum++);
               if(obj instanceof String)
                    cell.setCellValue((String)obj);
                else if(obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
            }
        }
        try
        {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File(filepath+".xlsx"));
            workbook.write(out);
            out.close();
            success=true;
            System.out.println(filepath+"is written successfully on disk.");
        }
        catch (Exception e)
        {
            success=false;
        }
        return success;
    }
    
    public boolean writeExcelFileReportPerawat(String[] columnNames,Object[][] data2,Object[] arrivaltime,Object[] inter,Object[] service,Object[][] averageservicetime,Object[][] averagewaitingtime, Object[][] averagedelaytime,Object[][] totalspenttime,String s){
        boolean success=true;
        XSSFWorkbook workbook = new XSSFWorkbook();
         
        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("Tabel Report Perawat Poliklinik");
          
        //This data needs to be written (Object[])
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
       
        //write average service time
        Object[] column2=new Object[columnNames.length];
        for(int i=0;i<columnNames.length;i++){
            column2[i]="Average Service Time "+columnNames[i];
        }
        int m=2;
        data.put(m+"",column2);
        Object[] temp2=new Object[column2.length];
        m++;
        for(int i=0;i<temp2.length;i++){
            temp2[i]=averageservicetime[0][i];
        }
        data.put(m+"",temp2);
        m++;
        
        //write average watitng time
        Object[] column3=new Object[columnNames.length];
        for(int i=0;i<columnNames.length;i++){
            column3[i]="Average Waiting Time "+columnNames[i];
        }
        data.put(m+"",column3);
        Object[] temp3=new Object[column3.length];
        m++;
        for(int i=0;i<temp3.length;i++){
            temp3[i]=averagewaitingtime[0][i];
        }
        data.put(m+"",temp3);
        m++;
        
        //write average delay time
        Object[] column4=new Object[columnNames.length];
        for(int i=0;i<columnNames.length;i++){
            column4[i]="Average Delay Time "+columnNames[i];
        }
        data.put(m+"",column4);
        Object[] temp4=new Object[column4.length];
        m++;
        for(int i=0;i<temp4.length;i++){
            temp4[i]=averagedelaytime[0][i];
        }
        data.put(m+"",temp4);
        m++;
        
        //write total spent time
        Object[] column5=new Object[columnNames.length];
        for(int i=0;i<columnNames.length;i++){
            column5[i]="Total Spent Time "+columnNames[i];
        }
        data.put(m+"",column5);
        Object[] temp5=new Object[column5.length];
        m++;
        for(int i=0;i<temp5.length;i++){
            temp5[i]=totalspenttime[0][i];
        }
        data.put(m+"",temp5);
        m++;
        
         //write utility server
        Object[] column=new Object[columnNames.length];
        for(int i=0;i<columnNames.length;i++){
            column[i]="Utility Server "+columnNames[i];
        }
        data.put(m+"",column);
        m++;
        Object[] temp=new Object[column.length];
        for(int i=0;i<temp.length;i++){
            temp[i]=data2[0][i]+" ";
        }
        data.put(m+"",temp);
        m++;
        
                
        //wriTe arrivaltime
        data.put(m+"",new Object[]{"Summary Arrival Rate","Arrival Rate BPJS Lama","Arrival Rate BPJS Baru"});
        m++;
        data.put(m+"",arrivaltime);
        m++;
        
        //write interarrivaltime
        data.put(m+"",new Object[]{"Summary Interarrival Time","Interarrival time BPJS Lama","Interarrival time BPJS Baru"});
        m++;
        data.put(m+"",inter);
        m++;
        
        //write service rate
        data.put(m+"",new Object[]{"Summary Service Rate","Service Rate BPJS Lama","Service Rate BPJS Baru"});
        m++;
        data.put(m+"",service);
        m++;
        
        //write summary
        data.put(m+"",new Object[]{"Summary Perawat Poliklinik"});
        m++;
        data.put(m+"",new Object[]{s});

        //Iterate over data and write to sheet
        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset)
        {
            Row row = sheet.createRow(rownum++);
            Object [] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr)
            {
               Cell cell = row.createCell(cellnum++);
               if(obj instanceof String)
                    cell.setCellValue((String)obj);
                else if(obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
            }
        }
        try
        {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File(filepath+".xlsx"));
            workbook.write(out);
            out.close();
            success=true;
            System.out.println(filepath+"is written successfully on disk.");
        }
        catch (Exception e)
        {
            success=false;
        }
        return success;
    }
    
    public boolean writeExcelFileReportDokter(String[] columnNames,Object[][] data2,Object[] arrivaltime,Object[] inter,Object[] service,Object[][] averageservicetime,Object[][] averagewaitingtime, Object[][] averagedelaytime,Object[][] totalspenttime,String s){
        boolean success=true;
        XSSFWorkbook workbook = new XSSFWorkbook();
         
        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("Tabel Report Dokter Poliklinik");
          
        //This data needs to be written (Object[])
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
       
        //write average service time
        Object[] column2=new Object[columnNames.length];
        for(int i=0;i<columnNames.length;i++){
            column2[i]="Average Service Time "+columnNames[i];
        }
        int m=2;
        data.put(m+"",column2);
        Object[] temp2=new Object[column2.length];
        m++;
        for(int i=0;i<temp2.length;i++){
            temp2[i]=averageservicetime[0][i];
        }
        data.put(m+"",temp2);
        m++;
        
        //write average watitng time
        Object[] column3=new Object[columnNames.length];
        for(int i=0;i<columnNames.length;i++){
            column3[i]="Average Waiting Time "+columnNames[i];
        }
        data.put(m+"",column3);
        Object[] temp3=new Object[column3.length];
        m++;
        for(int i=0;i<temp3.length;i++){
            temp3[i]=averagewaitingtime[0][i];
        }
        data.put(m+"",temp3);
        m++;
        
        //write average delay time
        Object[] column4=new Object[columnNames.length];
        for(int i=0;i<columnNames.length;i++){
            column4[i]="Average Delay Time "+columnNames[i];
        }
        data.put(m+"",column4);
        Object[] temp4=new Object[column4.length];
        m++;
        for(int i=0;i<temp4.length;i++){
            temp4[i]=averagedelaytime[0][i];
        }
        data.put(m+"",temp4);
        m++;
        
        //write total spent time
        Object[] column5=new Object[columnNames.length];
        for(int i=0;i<columnNames.length;i++){
            column5[i]="Total Spent Time "+columnNames[i];
        }
        data.put(m+"",column5);
        Object[] temp5=new Object[column5.length];
        m++;
        for(int i=0;i<temp5.length;i++){
            temp5[i]=totalspenttime[0][i];
        }
        data.put(m+"",temp5);
        m++;
        
         //write utility server
        Object[] column=new Object[columnNames.length];
        for(int i=0;i<columnNames.length;i++){
            column[i]="Utility Server "+columnNames[i];
        }
        data.put(m+"",column);
        m++;
        Object[] temp=new Object[column.length];
        for(int i=0;i<temp.length;i++){
            temp[i]=data2[0][i]+" ";
        }
        data.put(m+"",temp);
        m++;
        
                
        //wriTe arrivaltime
        data.put(m+"",new Object[]{"Summary Arrival Rate","Arrival Rate BPJS Lama","Arrival Rate BPJS Baru","Arrival Rate Emergency"});
        m++;
        data.put(m+"",arrivaltime);
        m++;
        
        //write interarrivaltime
        data.put(m+"",new Object[]{"Summary Interarrival Time","Interarrival time BPJS Lama","Interarrival time BPJS Baru","Interarrival time Emergency"});
        m++;
        data.put(m+"",inter);
        m++;
        
        //write service rate
        data.put(m+"",new Object[]{"Summary Service Rate","Service Rate BPJS Lama","Service Rate BPJS Baru","Service Rate Emergency"});
        m++;
        data.put(m+"",service);
        m++;
        
        //write summary
        data.put(m+"",new Object[]{"Summary Dokter Poliklinik"});
        m++;
        data.put(m+"",new Object[]{s});

        //Iterate over data and write to sheet
        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset)
        {
            Row row = sheet.createRow(rownum++);
            Object [] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr)
            {
               Cell cell = row.createCell(cellnum++);
               if(obj instanceof String)
                    cell.setCellValue((String)obj);
                else if(obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
            }
        }
        try
        {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File(filepath+".xlsx"));
            workbook.write(out);
            out.close();
            success=true;
            System.out.println(filepath+"is written successfully on disk.");
        }
        catch (Exception e)
        {
            success=false;
        }
        return success;
    }
    
     public boolean writeExcelFileReportPetugas(String[] columnNames,Object[][] data2,Object[] arrivaltime,Object[] inter,Object[] service,Object[][] averageservicetime,Object[][] averagewaitingtime, Object[][] averagedelaytime,Object[][] totalspenttime,String s){
        boolean success=true;
        XSSFWorkbook workbook = new XSSFWorkbook();
         
        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("Tabel Report Petugas Poliklinik");
          
        //This data needs to be written (Object[])
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
       
        //write average service time
        Object[] column2=new Object[columnNames.length];
        for(int i=0;i<columnNames.length;i++){
            column2[i]="Average Service Time "+columnNames[i];
        }
        int m=2;
        data.put(m+"",column2);
        Object[] temp2=new Object[column2.length];
        m++;
        for(int i=0;i<temp2.length;i++){
            temp2[i]=averageservicetime[0][i];
        }
        data.put(m+"",temp2);
        m++;
        
        //write average watitng time
        Object[] column3=new Object[columnNames.length];
        for(int i=0;i<columnNames.length;i++){
            column3[i]="Average Waiting Time "+columnNames[i];
        }
        data.put(m+"",column3);
        Object[] temp3=new Object[column3.length];
        m++;
        for(int i=0;i<temp3.length;i++){
            temp3[i]=averagewaitingtime[0][i];
        }
        data.put(m+"",temp3);
        m++;
        
        //write average delay time
        Object[] column4=new Object[columnNames.length];
        for(int i=0;i<columnNames.length;i++){
            column4[i]="Average Delay Time "+columnNames[i];
        }
        data.put(m+"",column4);
        Object[] temp4=new Object[column4.length];
        m++;
        for(int i=0;i<temp4.length;i++){
            temp4[i]=averagedelaytime[0][i];
        }
        data.put(m+"",temp4);
        m++;
        
        //write total spent time
        Object[] column5=new Object[columnNames.length];
        for(int i=0;i<columnNames.length;i++){
            column5[i]="Total Spent Time "+columnNames[i];
        }
        data.put(m+"",column5);
        Object[] temp5=new Object[column5.length];
        m++;
        for(int i=0;i<temp5.length;i++){
            temp5[i]=totalspenttime[0][i];
        }
        data.put(m+"",temp5);
        m++;
        
         //write utility server
        Object[] column=new Object[columnNames.length];
        for(int i=0;i<columnNames.length;i++){
            column[i]="Utility Server "+columnNames[i];
        }
        data.put(m+"",column);
        m++;
        Object[] temp=new Object[column.length];
        for(int i=0;i<temp.length;i++){
            temp[i]=data2[0][i]+" ";
        }
        data.put(m+"",temp);
        m++;
        
                
        //wriTe arrivaltime
        data.put(m+"",new Object[]{"Summary Arrival Rate","Arrival Rate BPJS Lama","Arrival Rate BPJS Baru"});
        m++;
        data.put(m+"",arrivaltime);
        m++;
        
        //write interarrivaltime
        data.put(m+"",new Object[]{"Summary Interarrival Time","Interarrival time BPJS Lama","Interarrival time BPJS Baru"});
        m++;
        data.put(m+"",inter);
        m++;
        
        //write service rate
        data.put(m+"",new Object[]{"Summary Service Rate","Service Rate BPJS Lama","Service Rate BPJS Baru"});
        m++;
        data.put(m+"",service);
        m++;
        
        //write summary
        data.put(m+"",new Object[]{"Summary Petugas Poliklinik"});
        m++;
        data.put(m+"",new Object[]{s});

        //Iterate over data and write to sheet
        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset)
        {
            Row row = sheet.createRow(rownum++);
            Object [] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr)
            {
               Cell cell = row.createCell(cellnum++);
               if(obj instanceof String)
                    cell.setCellValue((String)obj);
                else if(obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
            }
        }
        try
        {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File(filepath+".xlsx"));
            workbook.write(out);
            out.close();
            success=true;
            System.out.println(filepath+"is written successfully on disk.");
        }
        catch (Exception e)
        {
            success=false;
        }
        return success;
    }
    
    public boolean writeExcelFile(LinkedList<Object[]> rawdata){
        boolean success=true;
        XSSFWorkbook workbook = new XSSFWorkbook();
         
        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("Tabel Output Pendaftaran Awal");
        int total=rawdata.size();
        int lama=0;
        int baru=0;
        for(int i=0;i<rawdata.size();i++){
            Object[] temp=rawdata.get(i);
            if(temp[1].equals("BPJS Lama")){
                lama++;
            }
            else if(temp[1].equals("BPJS Baru")){
                baru++;
            }
        }
        XSSFRow row = sheet.createRow(0);
        XSSFCell myCell = null;
        myCell = row.createCell(0);
        myCell.setCellValue("Pasien ke-");  
        myCell = row.createCell(1);
        myCell.setCellValue("Jenis");
        myCell = row.createCell(2);
        myCell.setCellValue("ArrivalTime");    
        myCell = row.createCell(3);
        myCell.setCellValue("Service Time");    
        myCell = row.createCell(4);
        myCell.setCellValue("Waktu Mulai Dilayani");    
        myCell = row.createCell(5);
        myCell.setCellValue("Delay Time");    
        myCell = row.createCell(6);
        myCell.setCellValue("Departure Time");    
        myCell = row.createCell(7);
        myCell.setCellValue("Waiting Time");   
        myCell = row.createCell(8);
        myCell.setCellValue("Loket ke-"); 
        int r2=2;
        for (int r=1;r <= rawdata.size(); r++ ,r2++ )
        {
			row = sheet.createRow(r);
                        Object[] temp=rawdata.get(r-1);
			//iterating c number of columns
			for (int c=0;c < 9; c++ )
			{
                                myCell = row.createCell(c);
				myCell.setCellValue(temp[c]+"");
			}
        }
        row = sheet.createRow(r2);
        myCell = row.createCell(0);
        myCell.setCellValue("Summary Pendaftaran Awal");
        r2++;
        row = sheet.createRow(r2);
        myCell = row.createCell(0);
        myCell.setCellValue("Total pasien : "+total);
        r2++;
        row = sheet.createRow(r2);
        myCell = row.createCell(0);
        myCell.setCellValue("Total pasien BPJS Lama : "+lama);
        r2++;
        row = sheet.createRow(r2);
        myCell = row.createCell(0);
        myCell.setCellValue("Total pasien BPJS Baru : "+baru);
		FileOutputStream fileOut=null;
                try {
                     fileOut = new FileOutputStream(new File(filepath+".xlsx"));
                } catch (FileNotFoundException ex) {
                    success=false;
                    Logger.getLogger(ExcelWriter.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    //write this workbook to an Outputstream.
                    workbook.write(fileOut);
                } catch (IOException ex) {
                    success=false;
                    Logger.getLogger(ExcelWriter.class.getName()).log(Level.SEVERE, null, ex);   
                }
                try {
                    fileOut.flush();
                } catch (IOException ex) {
                    success=false;
                    Logger.getLogger(ExcelWriter.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    fileOut.close();
                } catch (IOException ex) {
                    success=false;
                    Logger.getLogger(ExcelWriter.class.getName()).log(Level.SEVERE, null, ex);
                }
        return success;
    }
    
    public boolean writeExcelFilePoli(LinkedList<Object[]> rawdata){
        boolean success=true;
        XSSFWorkbook workbook = new XSSFWorkbook();
         
        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("Tabel Output Poliklinik");
        int total=rawdata.size();
        int lama=0;
        int baru=0;
        int emergency=0;
        for(int i=0;i<rawdata.size();i++){
            Object[] temp=rawdata.get(i);
            if(temp[2].equals("BPJS Lama")){
                lama++;
            }
            else if(temp[2].equals("BPJS Baru")){
                baru++;
            }
            else if(temp[2].equals("Emergency")){
                emergency++;
            }
        }
        XSSFRow row = sheet.createRow(0);
        XSSFCell myCell = null;
        myCell = row.createCell(0);
        myCell.setCellValue("Nomor urut di pendaftaran awal");  
        myCell = row.createCell(1);
        myCell.setCellValue("Pasien ke-");  
        myCell = row.createCell(2);
        myCell.setCellValue("Jenis");
        myCell = row.createCell(3);
        myCell.setCellValue("ArrivalTime");    
        myCell = row.createCell(4);
        myCell.setCellValue("Service Time");    
        myCell = row.createCell(5);
        myCell.setCellValue("Waktu Mulai Dilayani");    
        myCell = row.createCell(6);
        myCell.setCellValue("Delay Time");    
        myCell = row.createCell(7);
        myCell.setCellValue("Departure Time");    
        myCell = row.createCell(8);
        myCell.setCellValue("Waiting Time");   
        myCell = row.createCell(9);
        myCell.setCellValue("Petugas ke-"); 
        myCell = row.createCell(10);
        myCell.setCellValue("Perawat ke-"); 
        myCell = row.createCell(11);
        myCell.setCellValue("Dokter ke-"); 
        int r2=2;
        for (int r=0;r < rawdata.size(); r++ ,r2++ )
        {
			row = sheet.createRow(r+1);
                        Object[] temp=rawdata.get(r);
			//iterating c number of columns
			for (int c=0;c < 12; c++ )
			{
                                myCell = row.createCell(c);
				myCell.setCellValue(temp[c]+"");
			}
        }
        row = sheet.createRow(r2);
        myCell = row.createCell(0);
        myCell.setCellValue("Summary Poliklinik");
        r2++;
        row = sheet.createRow(r2);
        myCell = row.createCell(0);
        myCell.setCellValue("Total pasien : "+total);
        r2++;
        row = sheet.createRow(r2);
        myCell = row.createCell(0);
        myCell.setCellValue("Total pasien BPJS Lama : "+lama);
        r2++;
        row = sheet.createRow(r2);
        myCell = row.createCell(0);
        myCell.setCellValue("Total pasien BPJS Baru : "+baru);
        r2++;
        row = sheet.createRow(r2);
        myCell = row.createCell(0);
        myCell.setCellValue("Total pasien Emergency : "+emergency);
        FileOutputStream fileOut=null;
                try {
                     fileOut = new FileOutputStream(new File(filepath+".xlsx"));
                } catch (FileNotFoundException ex) {
                    success=false;
                    Logger.getLogger(ExcelWriter.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(success==true) {

                    try {
                //write this workbook to an Outputstream.
                        workbook.write(fileOut);
                    } catch (IOException ex) {
                        success=false;
                        Logger.getLogger(ExcelWriter.class.getName()).log(Level.SEVERE, null, ex);   
                    }
                }
                if(success==true){
                    try {
                        fileOut.flush();
                    } catch (IOException ex) {
                        success=false;
                        Logger.getLogger(ExcelWriter.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if(success==true){
                    try {
                        fileOut.close();
                    } catch (IOException ex) {
                        success=false;
                        Logger.getLogger(ExcelWriter.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        return success;
    }
    
    /**
     * @return the filepath
     */
    public String getFilepath() {
        return filepath;
    }

    /**
     * @param filepath the filepath to set
     */
    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    /**
     * @return the filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * @param filename the filename to set
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }
}
