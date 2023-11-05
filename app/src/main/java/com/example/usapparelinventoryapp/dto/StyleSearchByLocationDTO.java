package com.example.usapparelinventoryapp.dto;

public class StyleSearchByLocationDTO {

    public int style_pallet_id;
    public String style_code;
    public String style_color;
    public String style_size;
    public int quantity;
    public String pallet_location;



    public int pallet_styles_id;

    public StyleSearchByLocationDTO() {
    }

    public StyleSearchByLocationDTO(int style_pallet_id, String style_code, String style_color, String style_size, int quantity, String pallet_location, int pallet_styles_id) {
        this.style_pallet_id = style_pallet_id;
        this.style_code = style_code;
        this.style_color = style_color;
        this.style_size = style_size;
        this.quantity = quantity;
        this.pallet_location = pallet_location;
        this.pallet_styles_id = pallet_styles_id;

    }

    public int getStyle_pallet_id() {
        return style_pallet_id;
    }

    public void setStyle_pallet_id(int style_pallet_id) {
        this.style_pallet_id = style_pallet_id;
    }

    public String getStyle_code() {
        return style_code;
    }

    public String setStyle_code() {
        this.style_code = style_code;
        return style_code;
    }

    public String getStyle_color() {
        return style_color;
    }

    public String setStyle_color() {
        this.style_color = style_color;
        return style_color;
    }

    public String getStyle_size() {
        return style_size;
    }

    public String  setStyle_size() {
        this.style_size = style_size;
        return  style_size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPallet_location() {
        this.pallet_location = pallet_location;
        return pallet_location;
    }

    public String setPallet_location() {
        this.pallet_location = pallet_location;
        return pallet_location;
    }

    public int getPallet_styles_id() {
        return pallet_styles_id;
    }

    public void setPallet_styles_id(int pallet_styles_id) {
        this.pallet_styles_id = pallet_styles_id;
    }


    @Override
    public String toString() {
        return "StyleSearchByLocationDTO{" +
                "style_pallet_id=" + style_pallet_id +
                ", style_code='" + style_code + '\'' +
                ", style_color='" + style_color + '\'' +
                ", style_size='" + style_size + '\'' +
                ", quantity=" + quantity +
                ", pallet_location='" + pallet_location + '\'' +
                ", pallet_styles_id=" + pallet_styles_id +
                '}';
    }
}
