/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import javax.swing.JOptionPane;

/**
 *
 * @author Hamza
 */
public class Hashing {
    LinkList[] l;
    
    public Hashing(LinkList[] l){
        this.l = l;
    }
    
    private int stringToInt(String search){
        int value = 0;
        for(int i=0; i<search.length(); i++){
            value = value + (int)search.charAt(i)*(int)Math.pow(31,i);
        }
        return value;
    }
    
    public int insertkey(String event){
        int valstr = Math.abs(stringToInt(event));
        //System.out.println("Valstr = " +valstr);              //print valuestr
        int index = valstr %10;
        if(l[index].head == null){
            //System.out.println("i = " + 0);                   //print nr. of collisions
            return index;
        }
        else{
            int i= 0;
            while(i<l.length && l[index].head!=null){
                int index2 = hash2(valstr,i);
                index = ((index+index2)*31)%l.length;
                i++;
            }
            //System.out.println("i = " + i);                   //print nr. of collisions after rehash
            if(l[index].head != null){
                return -1;
            }
            else{
                return index;                
            }
        }
    }
    
    public int searchkey(String event){
        int valstr = Math.abs(stringToInt(event));
        int index = valstr %10;
        if(l[index].folder.equals(event)){
            
            //System.out.println(index);
            return index;
        }
        else{
            int i = 0;
            try{
                while(i<l.length && !l[index].folder.equals(event)){
                    int index2 = hash2(valstr,i);
                    index = ((index+index2)*31)%l.length;
                    i++;
                }
                if(l[index].folder.equals(event) && i<l.length){
                  return index;  
                }
            }
            catch(Exception e){}
            return -1;
        }
    }
    public void printFolder(){
        for(int i =0;i<l.length;i++){
            System.out.println(l[i].folder);
        }
        System.out.println("");
    }
    
    private int hash2(int s, int d){
        
        d = (int)Math.pow(s,d); 
        s = s/d;
        s = s%10;
        return s; 
    }
    
//    public void display(){
//        for(int i=0; i<l.length; i++){
//            System.out.println( i +" "+l[i].folder);
//        }
//    }
}
