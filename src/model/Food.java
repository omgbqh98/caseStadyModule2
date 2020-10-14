package model;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;

public class Food implements Serializable {
    private int id ;
    private static int cruID = 1;
    private String name;
    private int vnd;
    private int kg;
    private int thanhTien;

    public Food() {
        this.id = cruID++;
    }

    public Food(int id, String name, int vnd, int kg) {
        this.id = id;
        this.name = name;
        this.vnd = vnd;
        this.kg = kg;
    }

    public int getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(int thanhTien) {
        this.thanhTien = thanhTien;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVnd() {
        return vnd;
    }

    public void setVnd(int vnd) {
        this.vnd = vnd;
    }

    public int getKg() {
        return kg;
    }

    public void setKg(int kg) {
        this.kg = kg;
    }
}
