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
public class DateSearching {
    LinkList[] arr;
    LinkList lret = new LinkList();
    public DateSearching(LinkList[] arr){
        this.arr = arr;
    }
    
    public LinkList search(Hashing h){
        Node t = arr[h.insertkey("Lahore")].head;
        while(t != null){
            if(t.date.equals(Main.search)){
                Node n = t;
                lret.insert(n);
            }
            else{
                t=t.next;
            }
        }
        return lret;
    }
}
