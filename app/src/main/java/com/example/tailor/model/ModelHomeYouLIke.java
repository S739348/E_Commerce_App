package com.example.tailor.model;

public class ModelHomeYouLIke {
    String image,name;
    public ModelHomeYouLIke() {
    }

    public ModelHomeYouLIke(String image, String name) {
        this.image = image;
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
