/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juliet;

import java.io.Serializable;

/**
 *
 * @author User
 */
public class Scarves implements Serializable{
    private String path;
    private String name;
    private double price;

    public Scarves(String path, String name, double price) {
        this.path = path;
        this.name = name;
        this.price = price;
    }
    
    public boolean equals(Scarves scarves){
        return (this.price == price && this.name.equals(scarves.name) && this.path.equals(scarves.path));
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
