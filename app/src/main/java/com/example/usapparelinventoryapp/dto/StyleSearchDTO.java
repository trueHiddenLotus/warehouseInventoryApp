package com.example.usapparelinventoryapp.dto;

public class StyleSearchDTO {
public String style_code;
public String style_color;
public String style_size;
public int quantity;
public String pallet_location;

    public StyleSearchDTO() {
    }

    public StyleSearchDTO(String style_code, String style_color, String style_size, int quantity, String pallet_location) {
        this.style_code = style_code;
        this.style_color = style_color;
        this.style_size = style_size;
        this.quantity = quantity;
        this.pallet_location = pallet_location;
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

    public String getPallet_location() {
        return pallet_location;
    }

    public String setPallet_location() {
        this.pallet_location = pallet_location;
        return pallet_location;
    }

    @Override
    public String toString() {
        return "StyleSearchDTO{" +
                "style_code='" + style_code + '\'' +
                ", style_color='" + style_color + '\'' +
                ", style_size='" + style_size + '\'' +
                ", quantity=" + quantity +
                ", pallet_location='" + pallet_location + '\'' +
                '}';
    }
}
