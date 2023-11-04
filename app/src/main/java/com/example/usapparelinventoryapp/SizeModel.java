package com.example.usapparelinventoryapp;

public class SizeModel {

    private int size_id;
    private String size;
    public SizeModel() {

    }

    public SizeModel(int size_id, String size) {
        this.size_id = size_id;
        this.size = size;
    }

    public int getSize_id() {
        return size_id;
    }

    public void setSize_id(int size_id) {
        this.size_id = size_id;
    }

    public String getSize() {
        return size;
    }

    public CharSequence setSize() {
        this.size = size;
        return size;
    }

    @Override
    public String toString() {
        return "SizeModel{" +
                "size_id=" + size_id +
                ", size='" + size + '\'' +
                '}';
    }
}
