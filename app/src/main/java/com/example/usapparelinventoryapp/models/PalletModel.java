package com.example.usapparelinventoryapp.models;

public class PalletModel {

    private int pallet_id;
    private String location;
    private String asn;



    public PalletModel() {
    }

    public PalletModel(int pallet_id, String location, String asn) {
        this.pallet_id = pallet_id;
        this.location = location;
        this.asn = asn;
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

    public String getAsn() {
        return asn;
    }

    public void setAsn(String asn) {
        this.asn = asn;
    }

    public CharSequence setAsnv() {
        this.asn = asn;
        return asn;
    }

    @Override
    public String toString() {
        return "PalletModel{" +
                "pallet_id=" + pallet_id +
                ", location='" + location + '\'' +
                ", asn='" + asn + '\'' +
                '}';
    }


}
