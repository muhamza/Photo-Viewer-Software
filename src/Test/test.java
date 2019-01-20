/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

/**
 *
 * @author Hamza
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        LinkList[] arr = new LinkList[19];
        
        Hashing h = new Hashing(arr);
        
        System.out.println(h.insertkey("Lahore"));
        System.out.println(h.insertkey("Islamabad"));
        System.out.println(h.insertkey("Karachi"));
        System.out.println(h.insertkey("Faisalabad"));
        System.out.println(h.insertkey("Pakistan"));
        System.out.println(h.insertkey("Multan"));
        System.out.println(h.insertkey("Sahiwal"));
        System.out.println(h.insertkey("Muzaffarabad"));
        System.out.println(h.insertkey("Sukkur"));
        System.out.println(h.insertkey("Kotaddu"));
        System.out.println(h.insertkey("Rawalpindi"));
        System.out.println(h.insertkey("Peshawar"));
        System.out.println(h.insertkey("Gujranwala"));
    }
    
}
