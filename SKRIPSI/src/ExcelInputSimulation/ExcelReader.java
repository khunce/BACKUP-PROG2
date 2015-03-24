/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExcelInputSimulation;
import SimulasiAntrianPasien.Customer;
import SimulasiAntrianPasien.StatisticsGenerator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author robby
 */
public class ExcelReader {
    
    private FileInputStream file;
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
    public ExcelReader(String filepath,String filename){
        this.filename=filename;
        this.filepath=filepath;
        this.queuecustomer=new LinkedList<Customer>();
        this.arrivaltime=new LinkedList<String>();
        this.servicetime=new LinkedList<String>();
        this.servicepetugas=new LinkedList<String>();
        this.serviceperawat=new LinkedList<String>();
        this.servicedokter=new LinkedList<String>();
        this.totalbaru=0;
        this.totallama=0;
        this.emergency=0;
        this.totalpasienpoli=0;
        this.serverpetugas=0;
        this.serverawal=0;
        this.serverdokter=0;
        this.serverperawat=0;
    }
    
    public ExcelReader(){
        
    }
    
    public boolean createFile(){
        boolean create=true;
        if(filename.endsWith(".xlsx")||filename.endsWith(".xls")){
            try {
                this.file=new FileInputStream(new File(this.filepath));
            } catch (FileNotFoundException ex) {
                create=false;
            }
        }
        else{
            create=false;
        }
        return create;
    }
    
    public boolean readExcelLimited(){
         boolean create=true;
         if(filename.endsWith(".xlsx")||filename.endsWith(".xls")){
                try {
                    this.workbook = new XSSFWorkbook(this.file);
                    XSSFSheet sheet = workbook.getSheetAt(0);
                                         Iterator<Row> rowIterator = sheet.iterator();
                                         StatisticsGenerator gen=new StatisticsGenerator();
                                         int counterrow=0;
                                         while (rowIterator.hasNext()) 
                                         {
                                                 Row row = rowIterator.next();
                                                 Iterator<Cell> cellIterator = row.cellIterator();
                                                 if(counterrow==0){
                                                     int k=0;
                                                     while (cellIterator.hasNext()) 
                                                     {

                                                             Cell cell = cellIterator.next();
                                                             if(k==9){
                                                                 this.kapasitasantrian=(int)cell.getNumericCellValue();
                                                                 System.out.print( this.kapasitasantrian);
                                                             }
                                                             k++;


                                                     }

                                                 }
                                                 if(counterrow>1){
                                                     int i=0;
                                                     Customer temp=new Customer();
                                                     while (cellIterator.hasNext()) 
                                                     {

                                                             Cell cell = cellIterator.next();
                                                             if(i==0){
                                                                 temp.setNumber((int)cell.getNumericCellValue());
                                                                 System.out.print( cell.getNumericCellValue()+ "\t");
                                                             }
                                                             else if(i==1){
                                                                 System.out.print(cell.getStringCellValue());
                                                                 temp.setJenis(cell.getStringCellValue());
                                                                 if(temp.getJenis().equals("BPJS Lama")){
                                                                     this.totallama++;
                                                                 }
                                                                 else if(temp.getJenis().equals("BPJS Baru")){
                                                                     this.totalbaru++;
                                                                 }
                                                                 else{
                                                                     this.emergency++;
                                                                 }
                                                             }
                                                             else if(i==2){
                                                                 String temprealtime=cell.getStringCellValue();
                                                                 temp.setArrivaltime(gen.convertToRealTime(temprealtime));
                                                                 this.getArrivaltime().add(temprealtime);
                                                                 System.out.print(temprealtime+ "\t"+ " "+temp.getArrivaltime()+" "+gen.convertSeconds(temp.getArrivaltime())+" \t");
                                                             }
                                                             else if(i==3){
                                                                 String temprealtime=cell.getStringCellValue();
                                                                 temp.setServicetime(gen.convertToRealTime(temprealtime));
                                                                 this.getServicetime().add(temprealtime);
                                                                 System.out.print(temprealtime+ "\t");
                                                             }
                                                             else if(i==4){
                                                                 String tempboolean=cell.getStringCellValue();
                                                                 if(tempboolean.contains("Y")){
                                                                     this.totalpasienpoli++;
                                                                     temp.setToPoliklinik(true);
                                                                 }
                                                                 else{
                                                                     temp.setToPoliklinik(false);
                                                                 }
                                                                 System.out.print(tempboolean+ "\t");
                                                             }
                                                             else if(i==5){
                                                                 String temprealtime=cell.getStringCellValue();
                                                                 temp.setServicetimepoli(gen.convertToRealTime(temprealtime));
                                                                 this.getServicepetugas().add(temprealtime);
                                                             }
                                                             else if(i==6){
                                                                 String temprealtime=cell.getStringCellValue();
                                                                 temp.setServicetimepoli2(gen.convertToRealTime(temprealtime));
                                                                 this.getServiceperawat().add(temprealtime);
                                                             }
                                                             else if(i==7){
                                                                 String temprealtime=cell.getStringCellValue();
                                                                 temp.setServicetimepoli3(gen.convertToRealTime(temprealtime));
                                                                 this.getServicedokter().add(temprealtime);
                                                             }
                                                             i++;

                                                     }
                                                     getQueuecustomer().add(temp);
                                                 }
                                                 System.out.println("");
                                                 counterrow++;
                                         }
                }
                catch (IOException ex) {
                    create=false;
                }   
        }
        else{
                    create=false;
         }    
        try {
            file.close();
        } catch (IOException ex) {
             create=false;
        }
        return create;
         
    }
    
    public boolean readExcel(){
        boolean create=true;
        if(filename.endsWith(".xlsx")||filename.endsWith(".xls")){
               try {
                this.workbook = new XSSFWorkbook(this.file);
                XSSFSheet sheet = workbook.getSheetAt(0);
                            Iterator<Row> rowIterator = sheet.iterator();
                            StatisticsGenerator gen=new StatisticsGenerator();
                            int counterrow=0;
                            while (rowIterator.hasNext()) 
                            {
                                    Row row = rowIterator.next();
                                    Iterator<Cell> cellIterator = row.cellIterator();
                                    if(counterrow==0){
                                        int k=0;
                                        while (cellIterator.hasNext()) 
                                        {

                                                Cell cell = cellIterator.next();
                                                if(k==1){
                                                   this.serverawal=(int)cell.getNumericCellValue();
                                                   System.out.print( this.serverawal);
                                                }
                                                else if(k==3){
                                                   this.serverpetugas=(int)cell.getNumericCellValue();
                                                   System.out.print( this.serverpetugas);
                                                }
                                                else if(k==5){
                                                    this.serverperawat=(int)cell.getNumericCellValue();
                                                    System.out.print( this.serverperawat);
                                                }
                                                else if(k==7){
                                                    this.serverdokter=(int)cell.getNumericCellValue();
                                                    System.out.print( this.serverdokter);
                                                }
                                                else if(k==9){
                                                    this.kapasitasantrian=(int)cell.getNumericCellValue();
                                                    System.out.print( this.kapasitasantrian);
                                                }
                                                k++;
                                        }

                                    }
                                    if(counterrow>1){
                                        int i=0;
                                        Customer temp=new Customer();
                                        while (cellIterator.hasNext()) 
                                        {

                                                Cell cell = cellIterator.next();
                                                if(i==0){
                                                    temp.setNumber((int)cell.getNumericCellValue());
                                                    System.out.print( cell.getNumericCellValue()+ "\t");
                                                }
                                                else if(i==1){
                                                    System.out.print(cell.getStringCellValue());
                                                    temp.setJenis(cell.getStringCellValue());
                                                    if(temp.getJenis().equals("BPJS Lama")){
                                                        this.totallama++;
                                                    }
                                                    else if(temp.getJenis().equals("BPJS Baru")){
                                                        this.totalbaru++;
                                                    }
                                                    else{
                                                        this.emergency++;
                                                    }
                                                }
                                                else if(i==2){
                                                    String temprealtime=cell.getStringCellValue();
                                                    temp.setArrivaltime(gen.convertToRealTime(temprealtime));
                                                    this.getArrivaltime().add(temprealtime);
                                                    System.out.print(temprealtime+ "\t"+ " "+temp.getArrivaltime()+" "+gen.convertSeconds(temp.getArrivaltime())+" \t");
                                                }
                                                else if(i==3){
                                                    String temprealtime=cell.getStringCellValue();
                                                    temp.setServicetime(gen.convertToRealTime(temprealtime));
                                                    this.getServicetime().add(temprealtime);
                                                    System.out.print(temprealtime+ "\t");
                                                }
                                                else if(i==4){
                                                    String tempboolean=cell.getStringCellValue();
                                                    if(tempboolean.contains("Y")){
                                                        this.totalpasienpoli++;
                                                        temp.setToPoliklinik(true);
                                                    }
                                                    else{
                                                        temp.setToPoliklinik(false);
                                                    }
                                                    System.out.print(tempboolean+ "\t");
                                                }
                                                else if(i==5){
                                                    String temprealtime=cell.getStringCellValue();
                                                    temp.setServicetimepoli(gen.convertToRealTime(temprealtime));
                                                    this.getServicepetugas().add(temprealtime);
                                                }
                                                else if(i==6){
                                                    String temprealtime=cell.getStringCellValue();
                                                    temp.setServicetimepoli2(gen.convertToRealTime(temprealtime));
                                                    this.getServiceperawat().add(temprealtime);
                                                }
                                                else if(i==7){
                                                    String temprealtime=cell.getStringCellValue();
                                                    temp.setServicetimepoli3(gen.convertToRealTime(temprealtime));
                                                    this.getServicedokter().add(temprealtime);
                                                }
                                                i++;

                                        }
                                        getQueuecustomer().add(temp);
                                    }
                                    System.out.println("");
                                    counterrow++;
                            }
            }
            catch (IOException ex) {
                create=false;
            }
        } 
        else{
                create=false;
         }
        try {
            file.close();
        } catch (IOException ex) {
            create=false;
        }
        return create;
        
    }

    
    public LinkedList<Customer> getQueueOfCustomer(){
        return this.getQueuecustomer();
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

    /**
     * @return the totalbaru
     */
    public int getTotalbaru() {
        return totalbaru;
    }

    /**
     * @param totalbaru the totalbaru to set
     */
    public void setTotalbaru(int totalbaru) {
        this.totalbaru = totalbaru;
    }

    /**
     * @return the totallama
     */
    public int getTotallama() {
        return totallama;
    }

    /**
     * @param totallama the totallama to set
     */
    public void setTotallama(int totallama) {
        this.totallama = totallama;
    }

    /**
     * @return the emergency
     */
    public int getEmergency() {
        return emergency;
    }

    /**
     * @param emergency the emergency to set
     */
    public void setEmergency(int emergency) {
        this.emergency = emergency;
    }

    /**
     * @return the totalpasienpoli
     */
    public int getTotalpasienpoli() {
        return totalpasienpoli;
    }

    /**
     * @param totalpasienpoli the totalpasienpoli to set
     */
    public void setTotalpasienpoli(int totalpasienpoli) {
        this.totalpasienpoli = totalpasienpoli;
    }

    /**
     * @return the arrivaltime
     */
    public LinkedList<String> getArrivaltime() {
        return arrivaltime;
    }

    /**
     * @param arrivaltime the arrivaltime to set
     */
    public void setArrivaltime(LinkedList<String> arrivaltime) {
        this.arrivaltime = arrivaltime;
    }

    /**
     * @return the servicetime
     */
    public LinkedList<String> getServicetime() {
        return servicetime;
    }

    /**
     * @param servicetime the servicetime to set
     */
    public void setServicetime(LinkedList<String> servicetime) {
        this.servicetime = servicetime;
    }

    /**
     * @return the queuecustomer
     */
    public LinkedList<Customer> getQueuecustomer() {
        return queuecustomer;
    }

    /**
     * @param queuecustomer the queuecustomer to set
     */
    public void setQueuecustomer(LinkedList<Customer> queuecustomer) {
        this.queuecustomer = queuecustomer;
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
     * @return the kapasitasantrian
     */
    public int getKapasitasantrian() {
        return kapasitasantrian;
    }

    /**
     * @param kapasitasantrian the kapasitasantrian to set
     */
    public void setKapasitasantrian(int kapasitasantrian) {
        this.kapasitasantrian = kapasitasantrian;
    }

    /**
     * @return the servicepetugas
     */
    public LinkedList<String> getServicepetugas() {
        return servicepetugas;
    }

    /**
     * @param servicepetugas the servicepetugas to set
     */
    public void setServicepetugas(LinkedList<String> servicepetugas) {
        this.servicepetugas = servicepetugas;
    }

    /**
     * @return the serviceperawat
     */
    public LinkedList<String> getServiceperawat() {
        return serviceperawat;
    }

    /**
     * @param serviceperawat the serviceperawat to set
     */
    public void setServiceperawat(LinkedList<String> serviceperawat) {
        this.serviceperawat = serviceperawat;
    }

    /**
     * @return the servicedokter
     */
    public LinkedList<String> getServicedokter() {
        return servicedokter;
    }

    /**
     * @param servicedokter the servicedokter to set
     */
    public void setServicedokter(LinkedList<String> servicedokter) {
        this.servicedokter = servicedokter;
    }
}
