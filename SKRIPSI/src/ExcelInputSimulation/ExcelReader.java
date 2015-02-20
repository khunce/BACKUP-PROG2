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
    public ExcelReader(String filepath,String filename){
        this.filename=filename;
        this.filepath=filepath;
        this.queuecustomer=new LinkedList<Customer>();
        this.arrivaltime=new LinkedList<String>();
        this.servicetime=new LinkedList<String>();
        this.totalbaru=0;
        this.totallama=0;
        this.emergency=0;
        this.totalpasienpoli=0;
    }
    
    public ExcelReader(){
        
    }
    
    public void createFile(){
        try {
            this.file=new FileInputStream(new File(this.filepath));
        } catch (FileNotFoundException ex) {

        }
    }
    
    public void readExcel(){
        try {
            this.workbook = new XSSFWorkbook(this.file);
        } catch (IOException ex) {
            
        }
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
                        StatisticsGenerator gen=new StatisticsGenerator();
                        int counterrow=0;
			while (rowIterator.hasNext()) 
			{
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
                                if(counterrow>0){
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
                                            i++;

                                    }
                                    getQueuecustomer().add(temp);
                                }
                                System.out.println("");
                                counterrow++;
                        }
                      
        try {
            file.close();
        } catch (IOException ex) {
            
        }
        
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
}
