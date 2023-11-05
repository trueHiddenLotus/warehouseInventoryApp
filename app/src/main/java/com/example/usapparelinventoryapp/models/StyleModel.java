package com.example.usapparelinventoryapp.models;

public class StyleModel {

    private int style_id;
    private String style;
    public StyleModel() {

    }

    public StyleModel(int style_id, String style) {
        this.style_id = style_id;
        this.style = style;
    }

    public int getStyle_id() {
        return style_id;
    }

    public void setStyle_id(int style_id) {
        this.style_id = style_id;
    }

    public String getStyle() {
        return style;
    }

    public String setStyle() {
        this.style = style;
        return style;
    }

    @Override
    public String toString() {
        return "StyleModel{" +
                "style_id=" + style_id +
                ", style='" + style + '\'' +
                '}';
    }
}
