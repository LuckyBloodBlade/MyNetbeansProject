/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juliet;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.netbeans.lib.awtextra.AbsoluteConstraints;


/**
 *
 * @author User
 */
public class JulietApp extends javax.swing.JFrame {

    ArrayList<Dresses> dressesArray;
    ArrayList<Tops> topsArray;
    ArrayList<Bottom> bottomArray;
    ArrayList<Scarves> scarvesArray;
    ArrayList<Items> list;
    DecimalFormat formatter;
    
    JLabel[] Window = new JLabel[30];
    JLabel[] Name = new JLabel[30];
    JLabel[] Price = new JLabel[30];
    JButton[] buttonTest = new JButton[30];    
    
    /**
     * Creates new form JulietApp
     */
    public JulietApp() {
        initComponents();
        
        formatter = new DecimalFormat("##0.00");
        
        dressesArray = new ArrayList<Dresses>();
        topsArray = new ArrayList<Tops>();
        bottomArray = new ArrayList<Bottom>();
        scarvesArray = new ArrayList<Scarves>();
        list = new ArrayList<Items>();
        
        imageHandler();
        imageAutoFrameDresses(dresses, dressesArray.size(),dressesArray);
        imageAutoFrameTops(tops, topsArray.size(),topsArray);
        imageAutoFrameBottom(bottom, bottomArray.size(),bottomArray);
        imageAutoFrameScarves(scarves, scarvesArray.size(),scarvesArray);
        
    }
    
    public void saveListToFile(){
        try{
            FileOutputStream file = new FileOutputStream("Receipt.dat");
            ObjectOutputStream outputFile = new ObjectOutputStream(file);
            
            for(int i=0; i < list.size() ; i++){
                outputFile.writeObject(list.get(i));
            }
            
            outputFile.close();
            
            JOptionPane.showMessageDialog(null, "Successfully saved");
//            this.dispose();
        }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void resetRowToJTable(ArrayList<Items> list){
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        
        Object rowData[] = new Object[4];
        
//        System.out.println(list.get(0).getQuantity());

        int rowCount = model.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }

        for(int i = 0; i < list.size(); i++)
        {
            rowData[0] = list.get(i).getName();
            rowData[1] = "RM "+formatter.format(list.get(i).getPrice());
            rowData[2] = "Remove";
//            rowData[2] = list.get(i).getQuantity();
//            double total = (list.get(i).getPrice())*(list.get(i).getQuantity());
//            rowData[3] = total;
    
            model.addRow(rowData);
        }
        
        Action delete = new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                JTable table = (JTable)e.getSource();
                int modelRow = Integer.valueOf( e.getActionCommand() );
                ((DefaultTableModel)table.getModel()).removeRow(modelRow);
                
                list.remove(modelRow);
                resetTotal();
            }
        };

        ButtonColumn buttonColumn = new ButtonColumn(jTable1, delete, 2);
        buttonColumn.setMnemonic(KeyEvent.VK_D);
                
    }
    
    public void imageAutoFrameDresses(JPanel jpanel,int index,ArrayList<Dresses> dress){
        
        for(int i = 0 ; i <= index/3 ; i++){
            
            if( i != (index/3 ) ){
                for(int j = 0 ; j < 3 ;j++){
                    String path = dress.get(((i*3)+(j+1))-1).getPath();
                    String name = dress.get(((i*3)+(j+1))-1).getName();
                    double price = dress.get(((i*3)+(j+1))-1).getPrice();
                    
                    Window[i] = new JLabel();
                    Window[i].setBackground(new java.awt.Color(204, 204, 204));
                    Window[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/juliet/imageToHandle/Dresses/"+path)));
                    Window[i].setOpaque(true);
                    jpanel.add(Window[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(40 + (j*240), 100 + (i*440), 200, 329));
                    
                    Name[i] = new JLabel();
                    Name[i].setFont(new java.awt.Font("Segoe UI", 3, 14));
                    Name[i].setForeground(new java.awt.Color(51, 51, 51));
                    Name[i].setText(name);
                    Name[i].setToolTipText("");
                    jpanel.add(Name[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(40 + (j*240), 440 + (i*440), -1, -1));
                    
                    Price[i] = new JLabel();
                    Price[i].setFont(new java.awt.Font("Segoe UI", 1, 14));
                    Price[i].setText("RM "+formatter.format(price));
                    jpanel.add(Price[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(40 + (j*240), 460 + (i*440), -1, -1));
                    
                    buttonTest[i] = new JButton();
                    buttonTest[i].setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                    buttonTest[i].setText("Add To Cart");
                    buttonTest[i].setBorder(null);
                    buttonTest[i].addActionListener(new java.awt.event.ActionListener() {
                        @Override
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            
                            Items item = new Items(path,name,price);
                            list.add(item);
                            System.out.println("here:"+list.size());
                        }
                    });
                    jpanel.add(buttonTest[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(140 + (j*240), 460 + (i*440), 100, 20));
                }
            }
            else{
                for(int j = 0 ; j < index%3 ;j++){
                    
                    String path = dress.get(((i*3)+(j+1))-1).getPath();
                    String name = dress.get(((i*3)+(j+1))-1).getName();
                    double price = dress.get(((i*3)+(j+1))-1).getPrice();
                    
                    Window[i] = new JLabel();
                    Window[i].setBackground(new java.awt.Color(204, 204, 204));
                    Window[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/juliet/imageToHandle/Dresses/"+path)));
                    Window[i].setOpaque(true);
                    jpanel.add(Window[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(40 + (j*240), 100 + (i*440), 200, 329));
                    
                    Name[i] = new JLabel();
                    Name[i].setFont(new java.awt.Font("Segoe UI", 3, 14));
                    Name[i].setForeground(new java.awt.Color(51, 51, 51));
                    Name[i].setText(name);
                    Name[i].setToolTipText("");
                    jpanel.add(Name[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(40 + (j*240), 440 + (i*440), -1, -1));
                    
                    Price[i] = new JLabel();
                    Price[i].setFont(new java.awt.Font("Segoe UI", 1, 14));
                    Price[i].setText("RM "+formatter.format(price));
                    jpanel.add(Price[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(40 + (j*240), 460 + (i*440), -1, -1));
                    
                    buttonTest[i] = new JButton();
                    buttonTest[i].setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                    buttonTest[i].setText("Add To Cart");
                    buttonTest[i].setBorder(null);
                    buttonTest[i].addActionListener(new java.awt.event.ActionListener() {
                        @Override
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            
                            Items item = new Items(path,name,price);
                            list.add(item);
                        }
                    });
                    jpanel.add(buttonTest[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(140 + (j*240), 460 + (i*440), 100, 20));
                }
            
            }
        }
    }

    public void imageAutoFrameTops(JPanel jpanel,int index,ArrayList<Tops> tops){
        
        for(int i = 0 ; i <= index/3 ; i++){
            
            if( i != (index/3 ) ){
                for(int j = 0 ; j < 3 ;j++){
                    String path = tops.get(((i*3)+(j+1))-1).getPath();
                    String name = tops.get(((i*3)+(j+1))-1).getName();
                    double price = tops.get(((i*3)+(j+1))-1).getPrice();
                    
                    Window[i] = new JLabel();
                    Window[i].setBackground(new java.awt.Color(204, 204, 204));
                    Window[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/juliet/imageToHandle/Tops/"+path)));
                    Window[i].setOpaque(true);
                    jpanel.add(Window[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(40 + (j*240), 100 + (i*440), 200, 329));
                    
                    Name[i] = new JLabel();
                    Name[i].setFont(new java.awt.Font("Segoe UI", 3, 14));
                    Name[i].setForeground(new java.awt.Color(51, 51, 51));
                    Name[i].setText(name);
                    Name[i].setToolTipText("");
                    jpanel.add(Name[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(40 + (j*240), 440 + (i*440), -1, -1));
                    
                    Price[i] = new JLabel();
                    Price[i].setFont(new java.awt.Font("Segoe UI", 1, 14));
                    Price[i].setText("RM "+formatter.format(price));
                    jpanel.add(Price[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(40 + (j*240), 460 + (i*440), -1, -1));
                    
                    buttonTest[i] = new JButton();
                    buttonTest[i].setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                    buttonTest[i].setText("Add To Cart");
                    buttonTest[i].setBorder(null);
                    buttonTest[i].addActionListener(new java.awt.event.ActionListener() {
                        @Override
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            
                            Items item = new Items(path,name,price);
                            list.add(item);
                            System.out.println("here:"+list.size());
                        }
                    });
                    jpanel.add(buttonTest[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(140 + (j*240), 460 + (i*440), 100, 20));
                }
            }
            else{
                for(int j = 0 ; j < index%3 ;j++){
                    
                    String path = tops.get(((i*3)+(j+1))-1).getPath();
                    String name = tops.get(((i*3)+(j+1))-1).getName();
                    double price = tops.get(((i*3)+(j+1))-1).getPrice();
                    
                    Window[i] = new JLabel();
                    Window[i].setBackground(new java.awt.Color(204, 204, 204));
                    Window[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/juliet/imageToHandle/Tops/"+path)));
                    Window[i].setOpaque(true);
                    jpanel.add(Window[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(40 + (j*240), 100 + (i*440), 200, 329));
                    
                    Name[i] = new JLabel();
                    Name[i].setFont(new java.awt.Font("Segoe UI", 3, 14));
                    Name[i].setForeground(new java.awt.Color(51, 51, 51));
                    Name[i].setText(name);
                    Name[i].setToolTipText("");
                    jpanel.add(Name[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(40 + (j*240), 440 + (i*440), -1, -1));
                    
                    Price[i] = new JLabel();
                    Price[i].setFont(new java.awt.Font("Segoe UI", 1, 14));
                    Price[i].setText("RM "+formatter.format(price));
                    jpanel.add(Price[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(40 + (j*240), 460 + (i*440), -1, -1));
                    
                    buttonTest[i] = new JButton();
                    buttonTest[i].setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                    buttonTest[i].setText("Add To Cart");
                    buttonTest[i].setBorder(null);
                    buttonTest[i].addActionListener(new java.awt.event.ActionListener() {
                        @Override
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            
                            Items item = new Items(path,name,price);
                            list.add(item);
                        }
                    });
                    jpanel.add(buttonTest[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(140 + (j*240), 460 + (i*440), 100, 20));
                }
            
            }
        }
    }

    public void imageAutoFrameBottom(JPanel jpanel,int index,ArrayList<Bottom> bottom){
        
        for(int i = 0 ; i <= index/3 ; i++){
            
            if( i != (index/3 ) ){
                for(int j = 0 ; j < 3 ;j++){
                    String path = bottom.get(((i*3)+(j+1))-1).getPath();
                    String name = bottom.get(((i*3)+(j+1))-1).getName();
                    double price = bottom.get(((i*3)+(j+1))-1).getPrice();
                    
                    Window[i] = new JLabel();
                    Window[i].setBackground(new java.awt.Color(204, 204, 204));
                    Window[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/juliet/imageToHandle/Bottom/"+path)));
                    Window[i].setOpaque(true);
                    jpanel.add(Window[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(40 + (j*240), 100 + (i*440), 200, 329));
                    
                    Name[i] = new JLabel();
                    Name[i].setFont(new java.awt.Font("Segoe UI", 3, 14));
                    Name[i].setForeground(new java.awt.Color(51, 51, 51));
                    Name[i].setText(name);
                    Name[i].setToolTipText("");
                    jpanel.add(Name[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(40 + (j*240), 440 + (i*440), -1, -1));
                    
                    Price[i] = new JLabel();
                    Price[i].setFont(new java.awt.Font("Segoe UI", 1, 14));
                    Price[i].setText("RM "+formatter.format(price));
                    jpanel.add(Price[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(40 + (j*240), 460 + (i*440), -1, -1));
                    
                    buttonTest[i] = new JButton();
                    buttonTest[i].setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                    buttonTest[i].setText("Add To Cart");
                    buttonTest[i].setBorder(null);
                    buttonTest[i].addActionListener(new java.awt.event.ActionListener() {
                        @Override
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            
                            Items item = new Items(path,name,price);
                            list.add(item);
                            System.out.println("here:"+list.size());
                        }
                    });
                    jpanel.add(buttonTest[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(140 + (j*240), 460 + (i*440), 100, 20));
                }
            }
            else{
                for(int j = 0 ; j < index%3 ;j++){
                    
                    String path = bottom.get(((i*3)+(j+1))-1).getPath();
                    String name = bottom.get(((i*3)+(j+1))-1).getName();
                    double price = bottom.get(((i*3)+(j+1))-1).getPrice();
                    
                    Window[i] = new JLabel();
                    Window[i].setBackground(new java.awt.Color(204, 204, 204));
                    Window[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/juliet/imageToHandle/Bottom/"+path)));
                    Window[i].setOpaque(true);
                    jpanel.add(Window[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(40 + (j*240), 100 + (i*440), 200, 329));
                    
                    Name[i] = new JLabel();
                    Name[i].setFont(new java.awt.Font("Segoe UI", 3, 14));
                    Name[i].setForeground(new java.awt.Color(51, 51, 51));
                    Name[i].setText(name);
                    Name[i].setToolTipText("");
                    jpanel.add(Name[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(40 + (j*240), 440 + (i*440), -1, -1));
                    
                    Price[i] = new JLabel();
                    Price[i].setFont(new java.awt.Font("Segoe UI", 1, 14));
                    Price[i].setText("RM "+formatter.format(price));
                    jpanel.add(Price[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(40 + (j*240), 460 + (i*440), -1, -1));
                    
                    buttonTest[i] = new JButton();
                    buttonTest[i].setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                    buttonTest[i].setText("Add To Cart");
                    buttonTest[i].setBorder(null);
                    buttonTest[i].addActionListener(new java.awt.event.ActionListener() {
                        @Override
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            
                            Items item = new Items(path,name,price);
                            list.add(item);
                        }
                    });
                    jpanel.add(buttonTest[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(140 + (j*240), 460 + (i*440), 100, 20));
                }
            
            }
        }
    }

    public void imageAutoFrameScarves(JPanel jpanel,int index,ArrayList<Scarves> scarves){
        
        for(int i = 0 ; i <= index/3 ; i++){
            
            if( i != (index/3 ) ){
                for(int j = 0 ; j < 3 ;j++){
                    String path = scarves.get(((i*3)+(j+1))-1).getPath();
                    String name = scarves.get(((i*3)+(j+1))-1).getName();
                    double price = scarves.get(((i*3)+(j+1))-1).getPrice();
                    
                    Window[i] = new JLabel();
                    Window[i].setBackground(new java.awt.Color(204, 204, 204));
                    Window[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/juliet/imageToHandle/Scarves/"+path)));
                    Window[i].setOpaque(true);
                    jpanel.add(Window[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(40 + (j*240), 100 + (i*440), 200, 329));
                    
                    Name[i] = new JLabel();
                    Name[i].setFont(new java.awt.Font("Segoe UI", 3, 14));
                    Name[i].setForeground(new java.awt.Color(51, 51, 51));
                    Name[i].setText(name);
                    Name[i].setToolTipText("");
                    jpanel.add(Name[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(40 + (j*240), 440 + (i*440), -1, -1));
                    
                    Price[i] = new JLabel();
                    Price[i].setFont(new java.awt.Font("Segoe UI", 1, 14));
                    Price[i].setText("RM "+formatter.format(price));
                    jpanel.add(Price[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(40 + (j*240), 460 + (i*440), -1, -1));
                    
                    buttonTest[i] = new JButton();
                    buttonTest[i].setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                    buttonTest[i].setText("Add To Cart");
                    buttonTest[i].setBorder(null);
                    buttonTest[i].addActionListener(new java.awt.event.ActionListener() {
                        @Override
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            
                            Items item = new Items(path,name,price);
                            list.add(item);
                            System.out.println("here:"+list.size());
                        }
                    });
                    jpanel.add(buttonTest[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(140 + (j*240), 460 + (i*440), 100, 20));
                }
            }
            else{
                for(int j = 0 ; j < index%3 ;j++){
                    
                    String path = scarves.get(((i*3)+(j+1))-1).getPath();
                    String name = scarves.get(((i*3)+(j+1))-1).getName();
                    double price = scarves.get(((i*3)+(j+1))-1).getPrice();
                    
                    Window[i] = new JLabel();
                    Window[i].setBackground(new java.awt.Color(204, 204, 204));
                    Window[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/juliet/imageToHandle/Scarves/"+path)));
                    Window[i].setOpaque(true);
                    jpanel.add(Window[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(40 + (j*240), 100 + (i*440), 200, 329));
                    
                    Name[i] = new JLabel();
                    Name[i].setFont(new java.awt.Font("Segoe UI", 3, 14));
                    Name[i].setForeground(new java.awt.Color(51, 51, 51));
                    Name[i].setText(name);
                    Name[i].setToolTipText("");
                    jpanel.add(Name[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(40 + (j*240), 440 + (i*440), -1, -1));
                    
                    Price[i] = new JLabel();
                    Price[i].setFont(new java.awt.Font("Segoe UI", 1, 14));
                    Price[i].setText("RM "+formatter.format(price));
                    jpanel.add(Price[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(40 + (j*240), 460 + (i*440), -1, -1));
                    
                    buttonTest[i] = new JButton();
                    buttonTest[i].setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                    buttonTest[i].setText("Add To Cart");
                    buttonTest[i].setBorder(null);
                    buttonTest[i].addActionListener(new java.awt.event.ActionListener() {
                        @Override
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            
                            Items item = new Items(path,name,price);
                            list.add(item);
                        }
                    });
                    jpanel.add(buttonTest[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(140 + (j*240), 460 + (i*440), 100, 20));
                }
            
            }
        }
    }
    
    public void imageHandler(){
        File[] files;
        
        File currentDir = new File("");
        files = new File(currentDir.getAbsolutePath()+"\\src\\juliet\\imageToHandle\\Dresses").listFiles();

        for (File file : files){
                String[] looseEnd = file.getName().toString().split(".jpg");
                String[] part = looseEnd[0].split(";");
                String path = file.getName().toString();
                String name = part[0];
                double price = Double.parseDouble(part[1]);
                Dresses dress = new Dresses(path,name,price);
                dressesArray.add(dress);
        }
        
        files = new File(currentDir.getAbsolutePath()+"\\src\\juliet\\imageToHandle\\Tops").listFiles();

        for (File file : files){
                String[] looseEnd = file.getName().toString().split(".jpg");
                String[] part = looseEnd[0].split(";");
                String path = file.getName().toString();
                String name = part[0];
                double price = Double.parseDouble(part[1]);
                Tops top= new Tops(path,name,price);
                topsArray.add(top);
        }
        
        files = new File(currentDir.getAbsolutePath()+"\\src\\juliet\\imageToHandle\\Bottom").listFiles();

        for (File file : files){
                String[] looseEnd = file.getName().toString().split(".jpg");
                String[] part = looseEnd[0].split(";");
                String path = file.getName().toString();
                String name = part[0];
                double price = Double.parseDouble(part[1]);
                Bottom bottom= new Bottom(path,name,price);
                bottomArray.add(bottom);
        }
        
        files = new File(currentDir.getAbsolutePath()+"\\src\\juliet\\imageToHandle\\Scarves").listFiles();

        for (File file : files){
                String[] looseEnd = file.getName().toString().split(".jpg");
                String[] part = looseEnd[0].split(";");
                String path = file.getName().toString();
                String name = part[0];
                double price = Double.parseDouble(part[1]);
                Scarves scarves = new Scarves(path,name,price);
                scarvesArray.add(scarves);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CLOSE = new javax.swing.JLabel();
        DRAG = new javax.swing.JLabel();
        Parent = new javax.swing.JPanel();
        Front = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        Email = new javax.swing.JTextField();
        PhoneNum = new javax.swing.JTextField();
        Start = new javax.swing.JButton();
        Dashboard = new javax.swing.JPanel();
        CLOSE1 = new javax.swing.JLabel();
        Header = new javax.swing.JPanel();
        SidePanel = new javax.swing.JPanel();
        Cart = new javax.swing.JLabel();
        Checkout = new javax.swing.JLabel();
        Home = new javax.swing.JLabel();
        Dresses = new javax.swing.JLabel();
        Tops = new javax.swing.JLabel();
        Bottom = new javax.swing.JLabel();
        Scarves = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        AboutUs = new javax.swing.JLabel();
        ContactUs = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ContactUs1 = new javax.swing.JLabel();
        ContactUs2 = new javax.swing.JLabel();
        ContactUs3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane2 = new ModernScrollPane(menuParent);
        menuParent = new javax.swing.JPanel();
        home = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        dresses = new javax.swing.JPanel();
        dress_window = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        namedress = new javax.swing.JLabel();
        pricedress = new javax.swing.JLabel();
        tops = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        bottom = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        scarves = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        about = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jLabel13 = new javax.swing.JLabel();
        cart = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocation(new java.awt.Point(0, 0));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        CLOSE.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        CLOSE.setForeground(new java.awt.Color(255, 255, 255));
        CLOSE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CLOSE.setText("X");
        CLOSE.setFocusable(false);
        CLOSE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CLOSEMouseClicked(evt);
            }
        });
        getContentPane().add(CLOSE, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 0, 20, -1));

        DRAG.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                DRAGMouseDragged(evt);
            }
        });
        DRAG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                DRAGMousePressed(evt);
            }
        });
        getContentPane().add(DRAG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1030, 60));

        Parent.setBackground(new java.awt.Color(255, 255, 255));
        Parent.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Parent.setLayout(new java.awt.CardLayout());

        Front.setBackground(new java.awt.Color(0, 0, 0));
        Front.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/juliet/image/websitecmsd6f334f5c343b471dae8802fe5d0a1f7e3603922.jpg"))); // NOI18N
        Front.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-120, 60, -1, 480));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Palace Script MT", 3, 84)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 82, 82));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Juliet");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 520, 500, 90));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Segoe Script", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 51, 51));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Your imagination is your limits");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 642, 500, -1));

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 102, 153));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("THE BEST FASHION ONLINE SHOP");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 613, 476, -1));

        Front.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 500, 710));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("PHONE NUMBER");
        Front.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 630, -1, 20));
        Front.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 670, 280, 20));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("EMAIL");
        Front.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 570, -1, -1));
        Front.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 610, 280, 10));

        Email.setBackground(new java.awt.Color(0, 0, 0));
        Email.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        Email.setForeground(new java.awt.Color(252, 252, 252));
        Email.setToolTipText("");
        Email.setBorder(null);
        Email.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        Email.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        Email.setOpaque(false);
        Front.add(Email, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 590, 280, 20));

        PhoneNum.setBackground(new java.awt.Color(0, 0, 0));
        PhoneNum.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        PhoneNum.setForeground(new java.awt.Color(252, 252, 252));
        PhoneNum.setToolTipText("");
        PhoneNum.setBorder(null);
        PhoneNum.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        PhoneNum.setOpaque(false);
        Front.add(PhoneNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 650, 280, 20));

        Start.setBackground(new java.awt.Color(255, 122, 122));
        Start.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Start.setForeground(new java.awt.Color(255, 255, 255));
        Start.setText("Start Shopping");
        Start.setBorder(null);
        Start.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartActionPerformed(evt);
            }
        });
        Front.add(Start, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 690, 130, 30));

        Parent.add(Front, "card4");

        Dashboard.setBackground(new java.awt.Color(255, 255, 255));
        Dashboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        CLOSE1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        CLOSE1.setForeground(new java.awt.Color(255, 255, 255));
        CLOSE1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CLOSE1.setText("X");
        CLOSE1.setFocusable(false);
        CLOSE1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CLOSE1MouseClicked(evt);
            }
        });
        Dashboard.add(CLOSE1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 0, 20, -1));

        Header.setBackground(new java.awt.Color(0, 0, 0));
        Header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        Dashboard.add(Header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1030, 30));

        SidePanel.setBackground(new java.awt.Color(154, 54, 54));
        SidePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Cart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/juliet/image/icons8_shopping_bag_40px.png"))); // NOI18N
        Cart.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Cart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CartMouseClicked(evt);
            }
        });
        SidePanel.add(Cart, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 40, 30));

        Checkout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/juliet/image/icons8_checkout_40px.png"))); // NOI18N
        Checkout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CheckoutMouseClicked(evt);
            }
        });
        SidePanel.add(Checkout, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, -1, 30));

        Home.setBackground(new java.awt.Color(195, 85, 85));
        Home.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Home.setForeground(new java.awt.Color(255, 255, 255));
        Home.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Home.setText("HOME");
        Home.setOpaque(true);
        Home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HomeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                HomeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                HomeMouseExited(evt);
            }
        });
        SidePanel.add(Home, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 190, 30));

        Dresses.setBackground(new java.awt.Color(154, 54, 54));
        Dresses.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Dresses.setForeground(new java.awt.Color(255, 255, 255));
        Dresses.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Dresses.setText("DRESSES");
        Dresses.setOpaque(true);
        Dresses.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DressesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                DressesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                DressesMouseExited(evt);
            }
        });
        SidePanel.add(Dresses, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 190, 30));

        Tops.setBackground(new java.awt.Color(154, 54, 54));
        Tops.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Tops.setForeground(new java.awt.Color(255, 255, 255));
        Tops.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Tops.setText("TOPS");
        Tops.setOpaque(true);
        Tops.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TopsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TopsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                TopsMouseExited(evt);
            }
        });
        SidePanel.add(Tops, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 190, 30));

        Bottom.setBackground(new java.awt.Color(154, 54, 54));
        Bottom.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Bottom.setForeground(new java.awt.Color(255, 255, 255));
        Bottom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Bottom.setText("BOTTOM");
        Bottom.setOpaque(true);
        Bottom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BottomMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BottomMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BottomMouseExited(evt);
            }
        });
        SidePanel.add(Bottom, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 190, 30));

        Scarves.setBackground(new java.awt.Color(154, 54, 54));
        Scarves.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Scarves.setForeground(new java.awt.Color(255, 255, 255));
        Scarves.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Scarves.setText("SCARVES");
        Scarves.setOpaque(true);
        Scarves.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ScarvesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ScarvesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ScarvesMouseExited(evt);
            }
        });
        SidePanel.add(Scarves, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 190, 30));
        SidePanel.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 150, -1));
        SidePanel.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 590, 150, 10));

        AboutUs.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        AboutUs.setForeground(new java.awt.Color(255, 255, 255));
        AboutUs.setText("ABOUT US");
        AboutUs.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AboutUs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AboutUsMouseClicked(evt);
            }
        });
        SidePanel.add(AboutUs, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 560, -1, 30));

        ContactUs.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ContactUs.setForeground(new java.awt.Color(255, 255, 255));
        ContactUs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/juliet/image/icons8_phone_20px.png"))); // NOI18N
        ContactUs.setText("05 - 6416054");
        ContactUs.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        SidePanel.add(ContactUs, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 620, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/juliet/image/icons8_facebook_old_20px.png"))); // NOI18N
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        SidePanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 670, -1, -1));

        ContactUs1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        ContactUs1.setForeground(new java.awt.Color(255, 255, 255));
        ContactUs1.setText("CONTACT US");
        ContactUs1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        SidePanel.add(ContactUs1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 600, -1, -1));

        ContactUs2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        ContactUs2.setForeground(new java.awt.Color(255, 255, 255));
        ContactUs2.setText("@JulietStudio");
        ContactUs2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        SidePanel.add(ContactUs2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 700, -1, -1));

        ContactUs3.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        ContactUs3.setForeground(new java.awt.Color(255, 255, 255));
        ContactUs3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/juliet/image/icons8_envelope_20px.png"))); // NOI18N
        ContactUs3.setText("JulietStudio@gmail.com");
        ContactUs3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        SidePanel.add(ContactUs3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 640, -1, 30));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/juliet/image/icons8_instagram_20px_1.png"))); // NOI18N
        SidePanel.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 670, -1, -1));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/juliet/image/icons8_twitter_20px.png"))); // NOI18N
        SidePanel.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 670, -1, -1));

        Dashboard.add(SidePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 190, 740));

        jLabel9.setFont(new java.awt.Font("Palace Script MT", 3, 84)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 82, 82));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Juliet");
        Dashboard.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, 500, 90));

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Segoe Script", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 0, 102));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Be Bold Be Glamorous");
        Dashboard.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 110, 500, -1));

        jSeparator3.setBackground(new java.awt.Color(0, 0, 0));
        Dashboard.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, 770, 10));

        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        menuParent.setBackground(new java.awt.Color(255, 255, 255));
        menuParent.setLayout(new java.awt.CardLayout());

        home.setBackground(new java.awt.Color(255, 255, 255));
        home.setMinimumSize(new java.awt.Dimension(770, 2066));
        home.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/juliet/image/750_263.jpg"))); // NOI18N
        jLabel1.setOpaque(true);
        home.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 750, 263));

        jLabel14.setBackground(new java.awt.Color(153, 153, 153));
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/juliet/image/450_263.jpg"))); // NOI18N
        jLabel14.setOpaque(true);
        home.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 300, 450, 263));

        jLabel15.setBackground(new java.awt.Color(153, 153, 153));
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/juliet/image/282_263.jpg"))); // NOI18N
        jLabel15.setOpaque(true);
        home.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 282, 263));
        home.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 1050, -1, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        home.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 1660, 750, -1));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel27.setText("BEST SELLER");
        home.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 1110, -1, -1));

        jSeparator7.setBackground(new java.awt.Color(0, 0, 0));
        home.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 1160, 750, 20));

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/juliet/image/HARLEY HEM DRAWSTRING TROUSER_BLACK WHITE_RM99.jpg"))); // NOI18N
        jLabel28.setOpaque(true);
        home.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 1210, 200, 329));

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/juliet/image/FlowerRoad_20.jpg"))); // NOI18N
        jLabel29.setOpaque(true);
        home.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 1210, 200, 329));

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/juliet/image/ribbed_long_dress_with_tule_in_black_RM49.jpg"))); // NOI18N
        jLabel30.setOpaque(true);
        home.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 1210, 200, 329));

        jLabel31.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        jLabel31.setText("SCARVES");
        home.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 1550, -1, -1));

        jLabel32.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(102, 102, 102));
        jLabel32.setText("Flower Road");
        home.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 1580, 80, -1));

        jLabel33.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        jLabel33.setText("DRESSES");
        home.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 1550, -1, -1));

        jLabel34.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(102, 102, 102));
        jLabel34.setText("Ribbed Long Dress With Tule");
        home.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 1580, 200, -1));

        jLabel35.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        jLabel35.setText("BOTTOM");
        home.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 1550, -1, -1));

        jLabel36.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(102, 102, 102));
        jLabel36.setText("Harley Hem Drawstring");
        home.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 1580, 160, -1));

        jPanel3.setBackground(new java.awt.Color(235, 186, 189));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/juliet/image/LOUNGE SET_WHITE_RM139.jpg"))); // NOI18N
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 210, 320));

        jLabel17.setFont(new java.awt.Font("Segoe Script", 3, 36)); // NOI18N
        jLabel17.setText("New Arrival");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, -1, -1));

        jLabel18.setFont(new java.awt.Font("Segoe Print", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 51, 51));
        jLabel18.setText("are timeless wardrobe winners.");
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 180, -1, -1));

        jLabel19.setFont(new java.awt.Font("Segoe Print", 0, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 51, 51));
        jLabel19.setText("No matter the fit,");
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 120, -1, -1));

        jLabel21.setFont(new java.awt.Font("Segoe Print", 0, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(51, 51, 51));
        jLabel21.setText("fabric or flare, these dresses");
        jPanel3.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 150, -1, -1));

        home.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 690, 750, 320));

        menuParent.add(home, "card2");

        dresses.setBackground(new java.awt.Color(255, 255, 255));
        dresses.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dress_window.setBackground(new java.awt.Color(204, 204, 204));
        dress_window.setIcon(new javax.swing.ImageIcon(getClass().getResource("/juliet/imageToHandle/Dresses/Kylie_Dress_In_Emerald_Green;39.jpg"))); // NOI18N
        dress_window.setOpaque(true);
        dresses.add(dress_window, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 200, 329));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel25.setText("Dresses");
        dresses.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));

        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));
        dresses.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 680, 10));

        namedress.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        namedress.setForeground(new java.awt.Color(51, 51, 51));
        namedress.setText("Kylie_Dress_In_Emerald_Green");
        namedress.setToolTipText("");
        dresses.add(namedress, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 440, -1, -1));

        pricedress.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        pricedress.setText("RM 39.00");
        dresses.add(pricedress, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 460, -1, -1));

        menuParent.add(dresses, "card4");

        tops.setBackground(new java.awt.Color(255, 255, 255));
        tops.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel26.setText("Tops");
        tops.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));

        jSeparator10.setForeground(new java.awt.Color(0, 0, 0));
        tops.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 680, 10));

        menuParent.add(tops, "card5");

        bottom.setBackground(new java.awt.Color(255, 255, 255));
        bottom.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel37.setText("Bottom");
        bottom.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));

        jSeparator9.setForeground(new java.awt.Color(0, 0, 0));
        bottom.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 680, 10));

        menuParent.add(bottom, "card5");

        scarves.setBackground(new java.awt.Color(255, 255, 255));
        scarves.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel38.setText("Scarves");
        scarves.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));

        jSeparator8.setForeground(new java.awt.Color(0, 0, 0));
        scarves.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 680, 10));

        menuParent.add(scarves, "card5");

        about.setBackground(new java.awt.Color(255, 255, 255));
        about.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);
        jScrollPane1.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setEnabled(false);
        jScrollPane1.setOpaque(false);

        jTextPane1.setEditable(false);
        jTextPane1.setBorder(null);
        jTextPane1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextPane1.setText("\tThe company Juliet was founded by the end of 2019 by Muhammad Saqif Bin Badlishah with a few other colleagues, Muhammad Shazrul Nazuan Bin Mohd Khairul Yusnizal, Syarina Arifah Binti Ramli, Juz Nur Fatiha Deena Binti Mohd Fuad, and also Nur Shafiqah Izzatie Binti Zainal Abidin. It is an online fashion boutique company based in Shah Alam, where it offers trendy and unique clothing designs for customers to buy daily essentials online and delivers it to the customers’ choice of delivery addresses. Since it is a new company, Juliet only consists of approximately 20 workers in different departments working together in order to provide quality services for each and every customer.\n\n\tThe difference of this company from others is we make the pricing of our products more affordable to our customers. Our target audience is focused more on women to buy our clothes because we put many clothes with variety of choice of women size and style. Our concept is to make sure that customers can get their clothes with an affordable price. The price we put is in range of RM30 to RM50 but our quality is still in brand-new condition.\n\n\tWe focus more on online based company by using online transaction and used third party delivery service which is PosLaju. We hope our effort will get more customer satisfaction as a feedback.");
        jTextPane1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(jTextPane1);

        jScrollPane1.removeMouseWheelListener(jScrollPane1.getMouseWheelListeners()[0]);

        about.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 400, 580, 450));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/juliet/image/store.jpg"))); // NOI18N
        about.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, -1, -1));

        menuParent.add(about, "card3");

        cart.setBackground(new java.awt.Color(255, 255, 255));

        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel39.setText("Your Items");

        jSeparator11.setForeground(new java.awt.Color(0, 0, 0));

        jScrollPane4.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane4.setBorder(null);
        jScrollPane4.setOpaque(false);

        jTable1.setBackground(new java.awt.Color(204, 0, 0));
        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NAME", "PRICE", "REMOVE"
            }
        ));
        jTable1.setGridColor(new java.awt.Color(255, 255, 255));
        jTable1.setRowMargin(2);
        jScrollPane4.setViewportView(jTable1);
        Color ivory = new Color(227,175,175);
        jTable1.setOpaque(true);
        jTable1.setFillsViewportHeight(true);
        jTable1.setBackground(ivory);

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(227, 175, 175));
        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel40.setText("TOTAL");

        javax.swing.GroupLayout cartLayout = new javax.swing.GroupLayout(cart);
        cart.setLayout(cartLayout);
        cartLayout.setHorizontalGroup(
            cartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cartLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(cartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator11)
                    .addComponent(jLabel39)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
                    .addGroup(cartLayout.createSequentialGroup()
                        .addComponent(jLabel40)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(219, 219, 219)))
                .addContainerGap(69, Short.MAX_VALUE))
        );
        cartLayout.setVerticalGroup(
            cartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cartLayout.createSequentialGroup()
                .addGroup(cartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cartLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(cartLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel39)))
                .addGap(38, 38, 38)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40))
                .addContainerGap())
        );

        menuParent.add(cart, "card8");

        jScrollPane2.setViewportView(menuParent);

        Dashboard.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 140, 810, 630));
        jScrollPane2.getVerticalScrollBar().setUnitIncrement(10);

        Parent.add(Dashboard, "card3");

        getContentPane().add(Parent, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1024, 768));

        setSize(new java.awt.Dimension(1022, 768));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void CLOSEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CLOSEMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_CLOSEMouseClicked

    int xx,xy;
    private void DRAGMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DRAGMousePressed
        // TODO add your handling code here:
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_DRAGMousePressed

    private void DRAGMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DRAGMouseDragged
        // TODO add your handling code here:
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x-xx,y-xy);
    }//GEN-LAST:event_DRAGMouseDragged

    private void StartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartActionPerformed
        // TODO add your handling code here:
        nextCard(Parent,Dashboard);
        
    }//GEN-LAST:event_StartActionPerformed

    private void CLOSE1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CLOSE1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_CLOSE1MouseClicked

    private void TopsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TopsMouseEntered
        // TODO add your handling code here:
        setBold(Tops);
    }//GEN-LAST:event_TopsMouseEntered

    private void TopsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TopsMouseExited
        // TODO add your handling code here:
        resetBold(new JLabel[]{Tops});
    }//GEN-LAST:event_TopsMouseExited

    private void BottomMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BottomMouseEntered
        // TODO add your handling code here:
        setBold(Bottom);
    }//GEN-LAST:event_BottomMouseEntered

    private void BottomMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BottomMouseExited
        // TODO add your handling code here:
        resetBold(new JLabel[]{Bottom});
    }//GEN-LAST:event_BottomMouseExited

    private void ScarvesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ScarvesMouseEntered
        // TODO add your handling code here:
        setBold(Scarves);
    }//GEN-LAST:event_ScarvesMouseEntered

    private void ScarvesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ScarvesMouseExited
        // TODO add your handling code here:
        resetBold(new JLabel[]{Scarves});
    }//GEN-LAST:event_ScarvesMouseExited

    private void DressesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DressesMouseEntered
        // TODO add your handling code here:
        setBold(Dresses);
    }//GEN-LAST:event_DressesMouseEntered

    private void DressesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DressesMouseExited
        // TODO add your handling code here:
        resetBold(new JLabel[]{Dresses});
    }//GEN-LAST:event_DressesMouseExited

    private void HomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HomeMouseEntered
        // TODO add your handling code here:
        setBold(Home);
    }//GEN-LAST:event_HomeMouseEntered

    private void HomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HomeMouseExited
        // TODO add your handling code here:
        resetBold(new JLabel[]{Home});
    }//GEN-LAST:event_HomeMouseExited

    private void HomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HomeMouseClicked
        // TODO add your handling code here:
        setColor(Home);
        setBold(Home);
        resetColor(new JLabel[]{Dresses,Tops,Bottom,Scarves});
        resetBold(new JLabel[]{Dresses,Tops,Bottom,Scarves});
        
        nextCard(menuParent,home);
    }//GEN-LAST:event_HomeMouseClicked

    private void DressesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DressesMouseClicked
        // TODO add your handling code here:
        setColor(Dresses);
        setBold(Dresses);
        resetColor(new JLabel[]{Home,Tops,Bottom,Scarves});
        resetBold(new JLabel[]{Home,Tops,Bottom,Scarves});
        
        nextCard(menuParent,dresses);
    }//GEN-LAST:event_DressesMouseClicked

    private void TopsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TopsMouseClicked
        // TODO add your handling code here:
        setColor(Tops);
        setBold(Tops);
        resetColor(new JLabel[]{Home,Dresses,Bottom,Scarves});
        resetBold(new JLabel[]{Home,Dresses,Bottom,Scarves});
        
        nextCard(menuParent,tops);
    }//GEN-LAST:event_TopsMouseClicked

    private void BottomMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BottomMouseClicked
        // TODO add your handling code here:
        setColor(Bottom);
        setBold(Bottom);
        resetColor(new JLabel[]{Home,Dresses,Tops,Scarves});
        resetBold(new JLabel[]{Home,Dresses,Tops,Scarves});
        
        nextCard(menuParent,bottom);
    }//GEN-LAST:event_BottomMouseClicked

    private void ScarvesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ScarvesMouseClicked
        // TODO add your handling code here:
        setColor(Scarves);
        setBold(Scarves);
        resetColor(new JLabel[]{Home,Dresses,Tops,Bottom});
        resetBold(new JLabel[]{Home,Dresses,Tops,Bottom});
        
        nextCard(menuParent,scarves);
    }//GEN-LAST:event_ScarvesMouseClicked

    private void AboutUsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AboutUsMouseClicked
        // TODO add your handling code here:
        nextCard(menuParent,about);
        
        resetColor(new JLabel[]{Home,Dresses,Tops,Bottom,Scarves});
        resetBold(new JLabel[]{Home,Dresses,Tops,Bottom,Scarves});
    }//GEN-LAST:event_AboutUsMouseClicked

    private void CartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CartMouseClicked
        // TODO add your handling code here:
        
        nextCard(menuParent,cart);
        
        resetColor(new JLabel[]{Home,Dresses,Tops,Bottom,Scarves});
        resetBold(new JLabel[]{Home,Dresses,Tops,Bottom,Scarves});
        
        resetRowToJTable(list);
        
        resetTotal();
    }//GEN-LAST:event_CartMouseClicked

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void CheckoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CheckoutMouseClicked
        // TODO add your handling code here:
        saveListToFile();
        
        new Receipt().setVisible(true);
    }//GEN-LAST:event_CheckoutMouseClicked
                                 
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
            java.util.logging.Logger.getLogger(JulietApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JulietApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JulietApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JulietApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JulietApp().setVisible(true);
            }
        });
    }
    
    private void setColor(JLabel label){
        label.setBackground(new Color(195,85,85));
    }
    private void resetColor(JLabel[] label){
        for(int i=0;i<label.length;i++){
            label[i].setBackground(new Color(154,54,54));
        }
    }
    private void setBold(JLabel label){
        if(!compareColor(label))
            label.setFont(new java.awt.Font("Segoe UI", 1, 14));
    }
    private void resetBold(JLabel[] label){
        for(int i=0;i<label.length;i++){
            if(!compareColor(label[i]))
                label[i].setFont(new java.awt.Font("Segoe UI", 0, 14));
        }
    }
    private Boolean compareColor(JLabel label){
        int flag=0;
        int[] color = new int[]{195,85,85};//The Side Pane Base Color
        
        if(color[0]==label.getBackground().getRed())
            flag++;
        if(color[1]==label.getBackground().getGreen())
            flag++;
        if(color[2]==label.getBackground().getBlue())
            flag++;
        if(flag==3)
            return true;
        else
            return false;
    }
    private void nextCard(JPanel panel,JPanel nextPanel){
        panel.removeAll();
        panel.add(nextPanel);
        panel.repaint();
        panel.revalidate();
    }

/*    
    private void readFile(String filename){
        try{
            BufferedReader in = new BufferedReader(new FileReader(filename));
            
            String read;
            while((read=in.readLine())!=null){
                
                System.out.println(read);
            }
            in.close();
        }catch(FileNotFoundException fnf){
            System.out.println(fnf.getMessage());}
        catch(IOException ioe){
            System.out.println(ioe.getMessage());}
        catch(Exception e){
            System.out.println(e.getMessage());}
    }
    
    private void writeFile(String a,String b,String filename){
        try{
            PrintWriter out = new PrintWriter(filename);
            
            String read;
            while(true){
                out.print(a);
                out.println("");
                out.print(b);
            }
            out.close();
        }catch(FileNotFoundException fnf){
            System.out.println(fnf.getMessage());}
        catch(IOException ioe){
            System.out.println(ioe.getMessage());}
        catch(Exception e){
            System.out.println(e.getMessage());}
    }
*/
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AboutUs;
    private javax.swing.JLabel Bottom;
    private javax.swing.JLabel CLOSE;
    private javax.swing.JLabel CLOSE1;
    private javax.swing.JLabel Cart;
    private javax.swing.JLabel Checkout;
    private javax.swing.JLabel ContactUs;
    private javax.swing.JLabel ContactUs1;
    private javax.swing.JLabel ContactUs2;
    private javax.swing.JLabel ContactUs3;
    private javax.swing.JLabel DRAG;
    private javax.swing.JPanel Dashboard;
    private javax.swing.JLabel Dresses;
    private javax.swing.JTextField Email;
    private javax.swing.JPanel Front;
    private javax.swing.JPanel Header;
    private javax.swing.JLabel Home;
    private javax.swing.JPanel Parent;
    private javax.swing.JTextField PhoneNum;
    private javax.swing.JLabel Scarves;
    private javax.swing.JPanel SidePanel;
    private javax.swing.JButton Start;
    private javax.swing.JLabel Tops;
    private javax.swing.JPanel about;
    private javax.swing.JPanel bottom;
    private javax.swing.JPanel cart;
    private javax.swing.JLabel dress_window;
    private javax.swing.JPanel dresses;
    private javax.swing.JPanel home;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JPanel menuParent;
    private javax.swing.JLabel namedress;
    private javax.swing.JLabel pricedress;
    private javax.swing.JPanel scarves;
    private javax.swing.JPanel tops;
    // End of variables declaration//GEN-END:variables

    private void resetTotal() {
        double total=0;
        for(int i = 0; i < list.size();i++){
            total+=list.get(i).getPrice();
        }
        jTextField1.setText("RM "+formatter.format(total)); //To change body of generated methods, choose Tools | Templates.
    }
}
