/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juliet;

import java.io.Serializable;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class Dresses implements Serializable{
    private String path;
    private String name;
    private double price;

    public Dresses(String path, String name, double price) {
        this.path = path;
        this.name = name;
        this.price = price;
    }
    
    public boolean equals(Dresses dresses){
        return (this.price == price && this.name.equals(dresses.name) && this.path.equals(dresses.path));
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
//    public void imageAutoFrame(JPanel jpanel,int size){
//        
//        JLabel[] Window = new JLabel[size];
//        JLabel[] Name = new JLabel[size];
//        JLabel[] Price = new JLabel[size];
//        
//        for(int i = 0 ; i <= size/3 ; i++){
//            
//            if( i != (size/3 ) ){
//                for(int j = 0 ; j < 3 ;j++){
//                    Window[i] = new JLabel();
//                    Window[i].setBackground(new java.awt.Color(204, 204, 204));
//                    Window[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/juliet/imageToHandle/Dresses/"+path)));
//                    Window[i].setOpaque(true);
//                    jpanel.add(Window[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(60 + (j*240), 100 + (i*440), 200, 329));
//                    
//                    Name[i] = new JLabel();
//                    Name[i].setFont(new java.awt.Font("Segoe UI", 3, 14));
//                    Name[i].setForeground(new java.awt.Color(51, 51, 51));
//                    Name[i].setText(name);
//                    Name[i].setToolTipText("");
//                    jpanel.add(Name[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(60 + (j*240), 440 + (i*440), -1, -1));
//                    
//                    Price[i] = new JLabel();
//                    Price[i].setFont(new java.awt.Font("Segoe UI", 1, 14));
//                    Price[i].setText("RM " + price);
//                    jpanel.add(Price[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(60 + (j*240), 460 + (i*440), -1, -1));
//                }
//            }
//            else{
//                for(int j = 0 ; j < size%3 ;j++){
//                    Window[i] = new JLabel();
//                    Window[i].setBackground(new java.awt.Color(204, 204, 204));
//                    Window[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/juliet/imageToHandle/Dresses/"+path)));
//                    Window[i].setOpaque(true);
//                    jpanel.add(Window[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(60 + (j*240), 100 + (i*440), 200, 329));
//                    
//                    Name[i] = new JLabel();
//                    Name[i].setFont(new java.awt.Font("Segoe UI", 3, 14));
//                    Name[i].setForeground(new java.awt.Color(51, 51, 51));
//                    Name[i].setText(name);
//                    Name[i].setToolTipText("");
//                    jpanel.add(Name[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(60 + (j*240), 440 + (i*440), -1, -1));
//                    
//                    Price[i] = new JLabel();
//                    Price[i].setFont(new java.awt.Font("Segoe UI", 1, 14));
//                    Price[i].setText("RM " + price);
//                    jpanel.add(Price[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(60 + (j*240), 460 + (i*440), -1, -1));
//                }
//            
//            }
//        }
//    }
}
