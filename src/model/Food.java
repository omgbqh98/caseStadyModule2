package model;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;

public class Food implements Serializable {
    private int id ;
    private static int cruID = 1;
    private String name;
    private String vnd;
    private int kg;
    private long thanhTien;
    private String time;

    public Food() {
        this.id = cruID++;
    }

    public Food(int id, String name, String vnd, int kg) {
        this.id = id;
        this.name = name;
        this.vnd = vnd;
        this.kg = kg;
    }

    public long getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(long thanhTien) {
        this.thanhTien = thanhTien;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public String getVnd() {
        return vnd;
    }

    public void setVnd(String vnd) {
        this.vnd = vnd;
    }

    public int getKg() {
        return kg;
    }

    public void setKg(int kg) {
        this.kg = kg;
    }
}
