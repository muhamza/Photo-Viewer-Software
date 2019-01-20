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
public class LinkList {
    Node head;
    Node tail;
    String folder;
    
    public void insert(Node n){
        if(head == null){
            head = n;
            tail = n;
        }
        else{
            n.next = head;
            head.previous = n;
            head = n;
        }
    }
    
    public void print(){
        Node temp = head;
        while(temp != null){
            System.out.print(temp.description + "  ");
            temp = temp.next;
        }
        System.out.println("");
    }
}
