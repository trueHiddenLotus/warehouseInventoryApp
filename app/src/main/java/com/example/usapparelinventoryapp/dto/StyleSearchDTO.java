package com.example.usapparelinventoryapp.dto;

public class StyleSearchDTO {
public String style_code;
public String style_color;
public String style_size;
public int quantity;

public int style_po;

public String pallet_asn;
public String pallet_location;

public int pallet_id;

    public StyleSearchDTO() {
    }

    public StyleSearchDTO(String style_code, String style_color, String style_size, int quantity, String pallet_asn, String pallet_location, int style_po, int pallet_id) {
        this.style_code = style_code;
        this.style_color = style_color;
        this.style_size = style_size;
        this.quantity = quantity;
        this.pallet_asn = pallet_asn;
        this.pallet_location = pallet_location;
        this.style_po = style_po;
        this.pallet_id = pallet_id;

    }



    public String getStyle_code() {
        return style_code;
    }

    public CharSequence setStyle_code() {
        this.style_code = style_code;
        return style_code;
    }

    public String getStyle_color() {
        return style_color;
    }

    public CharSequence setStyle_color() {
        this.style_color = style_color;
        return style_color;
    }

    public String getStyle_size() {
        return style_size;
    }

    public String setStyle_size() {
        this.style_size = style_size;
        return style_size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPallet_asn() {
        return pallet_asn;
    }

    public void setPallet_asn(String pallet_asn) {
        this.pallet_asn = pallet_asn;
    }


    public String getPallet_location() {
        return pallet_location;
    }

    public String setPallet_location() {
        this.pallet_location = pallet_location;
        return pallet_location;
    }

    public int getStyle_po() {
        return style_po;
    }

//    public void setStyle_po(int style_po) {
//        this.style_po = style_po;
//    }
    public int setStyle_po(int style_po) {
        this.style_po = style_po;
        return style_po;
    }

    public int getPallet_id() {
        return pallet_id;
    }

    public void setPallet_id(int pallet_id) {
        this.pallet_id = pallet_id;
    }

    @Override
    public String toString() {
        return "StyleSearchDTO{" +
                "style_code='" + style_code + '\'' +
                ", style_color='" + style_color + '\'' +
                ", style_size='" + style_size + '\'' +
                ", quantity=" + quantity +
                ", style_po=" + style_po +
                ", pallet_asn='" + pallet_asn + '\'' +
                ", pallet_location='" + pallet_location + '\'' +
                ", pallet_id=" + pallet_id +
                '}';
    }
}
