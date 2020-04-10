/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juliet;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class Items implements Serializable{
    private String path;
    private String name;
    private double price;
    private int quantity;
    
    public Items(String path, String name, double price) {
        this.path = path;
        this.name = name;
        this.price = price;
    }
    
    public boolean equals(Items items){
        return (this.price == price && this.name.equals(items.name) && this.path.equals(items.path));
    }
    
    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
