/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juliet;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author User
 */
public class Receipt extends javax.swing.JFrame {

    ArrayList<Items> list;
    DecimalFormat formatter;
    
    /**
     * Creates new form Receipt
     */
    public Receipt() {
        initComponents();
        
        formatter = new DecimalFormat("##0.00");
        list = new ArrayList<Items>();
        
        populateArrayList();
        designReceipt();
    }
    
    public void designReceipt(){
        Date obj = new Date();
        String date = obj.toString();
        
        int divider = 45;
        String s = String.format("%-41s %6s %37s\n", "*", "Juliet", "*");
        String s1 = String.format("%-32s %14s %28s\n", "*", "www.juliet.com", "*");
        String output = s + s1;
        String s2 = String.format("%-60s %25s\n", "ITEM", "PRICE");
        
        area.setText("\n");
        for(int i=0;i < divider;i++)
            area.setText(area.getText()+"*");
        area.setText(area.getText()+"\n");
        
        area.setText(area.getText()+output);
        
        for(int i=0;i < divider;i++)
            area.setText(area.getText()+"*");
        area.setText(area.getText()+"\n");
        
        area.setText(area.getText()+"DATE: "+date+"\n");
        
        area.setText(area.getText()+s2);
        for(int i=0;i < list.size();i++){
            String name = list.get(i).getName();
            String sItem = String.format("%-60.60s%-20.2f\n", name , list.get(i).getPrice());
            area.setText(area.getText()+sItem);
        }
        
        double total=0;
        for(int i = 0; i < list.size();i++){
            total+=list.get(i).getPrice();
        }
        
        for(int i=0;i < divider;i++)
            area.setText(area.getText()+"*");
        area.setText(area.getText()+"\n");
        
        String s3 = String.format("%60s%20s\n", "TOTAL", "RM "+formatter.format(total));
        area.setText(area.getText()+s3);
        area.setText(area.getText()+"\n\n\n");
        String a1 = String.format("%-20s %32s %20s\n", " ", "Return can be made within", " ");
        String a2 = String.format("%-20s %32s %20s\n", " ", "14 days with proof of purchase", " ");
        String a3 = String.format("%-20s %32s %20s\n", " ", "Thank You For Shopping with Us", " ");
        area.setText(area.getText()+a1+a2+a3);

    }

    public void populateArrayList(){
        try{
            FileInputStream file = new FileInputStream("Receipt.dat");
            ObjectInputStream inputFile = new ObjectInputStream(file);
            
            boolean endOfFile = false;
            
            while(!endOfFile){
                try{
                    list.add((Items) inputFile.readObject());
                }
                catch(EOFException e){
                    endOfFile = true;
                }
                catch(Exception f){
                     JOptionPane.showMessageDialog(null, f.getMessage());
                }
            }
            inputFile.close();
        }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
//    public void saveListToFile(){
//        try{
//            FileOutputStream file = new FileOutputStream("Receipt.dat");
//            ObjectOutputStream outputFile = new ObjectOutputStream(file);
//            
//            for(int i=0; i < list.size() ; i++){
//                outputFile.writeObject(list.get(i));
//            }
//            
//            outputFile.close();
//            
//            JOptionPane.showMessageDialog(null, "Successfully saved");
//            this.dispose();
//        }
//        catch(IOException e){
//            JOptionPane.showMessageDialog(null, e.getMessage());
//        }
//    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        area = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        area.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jScrollPane1.setViewportView(area);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 585, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 653, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Receipt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Receipt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Receipt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Receipt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Receipt().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane area;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}