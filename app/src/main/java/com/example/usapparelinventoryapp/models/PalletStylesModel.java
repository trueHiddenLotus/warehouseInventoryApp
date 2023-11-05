package com.example.usapparelinventoryapp.models;

public class PalletStylesModel {

    int palletStylesId;
    int style_pallet_id;
    String styleCode;
    String styleColor;
    String styleSize;
    int quantity;

    public PalletStylesModel() {
    }

    public PalletStylesModel(int palletStylesId, int style_pallet_id, String styleCode, String styleColor, String styleSize, int quantity) {
        this.palletStylesId = palletStylesId;
        this.style_pallet_id = style_pallet_id;
        this.styleCode = styleCode;
        this.styleColor = styleColor;
        this.styleSize = styleSize;
        this.quantity = quantity;
    }

    public int getPalletStylesId() {
        return palletStylesId;
    }

    public void setPalletStylesId(int palletStylesId) {
        this.palletStylesId = palletStylesId;
    }

    public int getStyle_pallet_id() {
        return style_pallet_id;
    }

    public void setStyle_pallet_id(int style_pallet_id) {
        this.style_pallet_id = style_pallet_id;
    }

    public String getStyleCode() {
        return styleCode;
    }

    public void setStyleCode(String styleCode) {
        this.styleCode = styleCode;
    }

    public String getStyleColor() {
        return styleColor;
    }

    public void setStyleColor(String styleColor) {
        this.styleColor = styleColor;
    }

    public String getStyleSize() {
        return styleSize;
    }

    public void setStyleSize(String styleSize) {
        this.styleSize = styleSize;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "PalletStylesModel{" +
                "palletStylesId=" + palletStylesId +
                ", style_pallet_id=" + style_pallet_id +
                ", styleCode='" + styleCode + '\'' +
                ", styleColor='" + styleColor + '\'' +
                ", styleSize='" + styleSize + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
