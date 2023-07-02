package com.example.tailor.model;

public class SizeModel {
    String Image,size;

    public SizeModel() {
    }

    public SizeModel(String image, String size) {
        Image = image;
        this.size = size;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
