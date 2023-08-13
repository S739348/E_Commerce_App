package com.example.tailor.model;

import java.util.ArrayList;

public class ReviewModel {
    String profileImage,name,title,description,rating;
    ArrayList<String>itemImage=new ArrayList<>();
    public ReviewModel() {
    }

    public ReviewModel(String profileImage, String name, String title, String description, String rating, ArrayList<String> itemImage) {
        this.profileImage = profileImage;
        this.name = name;
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.itemImage = itemImage;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getItemImage() {
        return itemImage;
    }

    public void setItemImage(ArrayList<String> itemImage) {
        this.itemImage = itemImage;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
