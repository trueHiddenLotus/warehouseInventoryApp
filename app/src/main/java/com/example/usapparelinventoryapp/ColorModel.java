package com.example.usapparelinventoryapp;

public class ColorModel {
    private int color_id;
    private String color;
    public ColorModel() {

    }

    public ColorModel(int color_id, String color) {
        this.color_id = color_id;
        this.color = color;
    }

    public int getColor_id() {
        return color_id;
    }

    public void setColor_id(int color_id) {
        this.color_id = color_id;
    }

    public String getColor() {
        return color;
    }

    public String setColor() {
        this.color = color;
        return color;
    }

    @Override
    public String toString() {
        return "ColorModel{" +
                "color_id=" + color_id +
                ", color='" + color + '\'' +
                '}';
    }
}
