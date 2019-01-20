/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

/**
 *
 * @author Hamza
 */
public class PhotoViewer extends javax.swing.JFrame {
    Hashing h;
    LinkList[] l;                               //Hashing Array
    LinkList tempry;
    LinkList cl;                                //Linked List which belongs to the class
    Node temp;                                  //Temp variable for for traversing the Linked list;
    int index=-1;
    int key;
    
    public PhotoViewer() {
        //int t = getSizeArray();
        l = new LinkList[61];                   //Creating new array for Hashing      
        for(int i=0; i<l.length; i++){          //initialization of Linked Lists
            l[i] = new LinkList();
        }

        tempry = new LinkList();
        h = new Hashing(l);  
        imagesInitialization();                 //making nodes and inserting into linked lists
        if(Main.jRadioButton1.isSelected()){
            eventSearch();
        }
        else if(Main.jRadioButton2.isSelected()){
            dateSearch();
        }
        else if(Main.jRadioButton3.isSelected()){
            specificSearch();
        }
        //h.printFolder();
        //display();
    }
    
    public void eventSearch(){
        index = h.searchkey(Main.search);        //index for folder Main.search (change)
        if(index == -1){
            this.setVisible(false);
            JOptionPane.showMessageDialog(null,Main.search + " not found!");
            new Main().setVisible(true);
        }
        else{
            temp = l[index].head;                    //initializing temp variable
            cl = l[index];
            initComponents();
            getContentPane().setBackground(new Color(255,255,255));
            showImage(cl.head);                      //displays the photo
        }
    }
    
    public void specificSearch(){
        String search = Main.search;
        String[] arr = search.split(", ");
        
        if(arr.length > 1){
            int key = h.searchkey(arr[0]);
                Node t = l[key].head;
                while(t!=null){
                    if(t.description.equalsIgnoreCase(arr[1])){
                        Node n = new Node(t.description,t.date,t.path,t.folder);
                        tempry.insert(n);
                        t=t.next;
                    }
                    else{
                        t=t.next;
                    }  
                }
                if(tempry.head == null){
                    this.setVisible(false);
                    JOptionPane.showMessageDialog(null,Main.search + " not found!");
                    new Main().setVisible(true);
                }
                else{
                    initComponents();
                    getContentPane().setBackground(new Color(255,255,255));
                    cl = tempry;
                    temp = cl.head;
                    showImage(cl.head);
                }
        }
        else if(arr.length == 1){
            for(int i=0;i<l.length;i++){
            
                if(l[i].head == null){
                    continue;
                }
                else{
                    Node t = l[i].head;
                    while(t!=null){
                        if(t.description.contains(search)){
                            Node n = new Node(t.description,t.date,t.path,t.folder);
                            tempry.insert(n);
                            t=t.next;
                        }
                        else{
                            t=t.next;
                        }  
                    }
                }
            }
            if(tempry.head == null){
                this.setVisible(false);
                JOptionPane.showMessageDialog(null,Main.search + " not found!");
                new Main().setVisible(true);
            }
            else{
                initComponents();
                getContentPane().setBackground(new Color(255,255,255));
                cl = tempry;
                temp = cl.head;
                showImage(cl.head);    
            }
        }
    }
    
    public void dateSearch(){
        String search = Main.search;
        for(int i=0;i<l.length;i++){
            
            if(l[i].head == null){
                continue;
            }
            else{
                Node t = l[i].head;
                while(t!=null){
                    if(t.date.equals(search)){
                        Node n = new Node(t.description,t.date,t.path,t.folder);
                        tempry.insert(n);
                        t=t.next;
                    }
                    else{
                        t=t.next;
                    }  
                }
            }
        }
        if(tempry.head == null){
                this.setVisible(false);
                JOptionPane.showMessageDialog(null,"Pictures with the date "+Main.search + " could not be found!");
                new Main().setVisible(true);
        }
        else{
            initComponents();
            getContentPane().setBackground(new Color(255,255,255));
            cl = tempry;
            temp = cl.head;
            showImage(cl.head);            
        }

    }
    
    
    public void imagesInitialization(){
        String[] arr = new String[4];
        Node n;
        try{
            FileInputStream fstream = new FileInputStream("C:\\Users\\Gigabyte\\Desktop\\Paths.txt");
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            String event = "";
            int key=-1;
            
            while((strLine = br.readLine()) != null){
                if(strLine.contains("*")){
                    event = strLine.substring(1);
                    key = h.insertkey(event);
                }
                else if(strLine.length() > 0){
                    arr = strLine.split(",");
                    n = new Node(arr[0],arr[1],arr[2],event);
                    l[key].folder = event;
                    l[key].insert(n); 
                }
                else{
                    if(strLine.length() == 0)
                    continue;
                }
            }
            in.close();
        } 
        catch (Exception ex) {
            System.out.println("Error :" +ex.getMessage());
        }
    }
    
    public void showImage(Node temp){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("PhotoViewer");
        ImageIcon icon = new ImageIcon("C:\\Users\\Gigabyte\\Desktop\\Photos\\"+temp.path+".jpg" );
        Image image = icon.getImage().getScaledInstance(jLabel1.getWidth(), jLabel1.getHeight(), Image.SCALE_SMOOTH);
        jLabel1.setIcon(new ImageIcon(image));
        jLabel2.setText("  " + temp.description + ", " + temp.folder);
    }
    
    public int getSizeArray(){
        int count = 0;
        try{
            FileInputStream fstream = new FileInputStream("C:\\Users\\Gigabyte\\Desktop\\Paths.txt");
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            while((strLine = br.readLine()) != null){
                if(strLine.contains("*")){
                    count++;
                }
                else{
                    continue;
                }
            }
            in.close();
        } 
        catch (Exception ex) {
            System.out.println("Error :" +ex.getMessage());
        }
        
        count = (count * 2) + (count * 1/3) + 7; 
        return count;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jButton1.setBackground(new java.awt.Color(102, 153, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("Next");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(102, 153, 255));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("Previous");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 102, 102));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setText("Exit");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
                .addGap(32, 32, 32)
                .addComponent(jButton3)
                .addGap(23, 23, 23))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 14, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(temp.next != null){
            temp = temp.next;
            showImage(temp); 
        }
        else if(temp == cl.tail){
            temp = cl.head;
            showImage(temp);
        }
        else{
            JOptionPane.showMessageDialog(null, "No Photos to show!");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if(temp.previous != null){
            temp = temp.previous;
            showImage(temp); 
        }
        else if(temp == cl.head){
            temp = cl.tail;
            showImage(temp);
        }
        else{
            JOptionPane.showMessageDialog(null, "No Photos to show!");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Main().setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed
    
    public void display(){
        for(int i=0; i<l.length; i++){
            if(l[i].head == null){
                System.out.println(i + ") null");
            }
            else{
                System.out.print(i +")");
                l[i].print();
            }
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PhotoViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PhotoViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PhotoViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PhotoViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PhotoViewer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
