/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import javax.swing.ImageIcon;

/**
 *
 * @author Hamza
 */
public class Node {
    String description;
    String date;
    String path;
    String folder;
    Node next;
    Node previous;
    
    public Node(String description, String date, String path, String folder){
        this.description = description;
        this.date = date;
        this.path = path;
        this.folder = folder;
    }
}
