package com.example.usapparelinventoryapp.models;

public class PalletModel {

    private int pallet_id;
    private String location;

    public PalletModel() {
    }

    public PalletModel(int pallet_id, String location) {
        this.pallet_id = pallet_id;
        this.location = location;
    }

    public int getPallet_id() {
        return pallet_id;
    }

    public int setPallet_id() {
        this.pallet_id = pallet_id;
        return pallet_id;
    }

    public String getLocation() {
        return location;
    }



    public CharSequence setLocation() {
        this.location = location;
        return location;
    }

    @Override
    public String toString() {
        return "PalletModel{" +
                "pallet_id=" + pallet_id +
                ", location='" + location + '\'' +
                '}';
    }


}
