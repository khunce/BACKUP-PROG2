/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SimulasiAntrianPasien;
import java.util.LinkedList;
import java.util.Queue;
/**
 *
 * @author robby
 */
public class CustomerQ {
    
    private Queue<Customer> queueofcustomer;
    private int size;
    
    public CustomerQ(){
        this.queueofcustomer=new LinkedList();
    }
    
    public synchronized void add(Customer customer) {
        this.queueofcustomer.add(customer);
        notifyAll();
    }
    
    public int getSize(){
        return this.queueofcustomer.size();
    }
    
     public synchronized Customer poll() {
        while (queueofcustomer.size() == 0) {
            try {
                wait();
            } catch (InterruptedException ex) {
                System.out.println("wait error " + ex.getMessage());
            }
        }
        notifyAll();
        return queueofcustomer.poll();
    }
    
    
}
