package com.example.tailor.model;
import java.util.ArrayList;
import java.util.List;

public class Product {
    private String name,WearType,itemCategory;
    private String key;
    private List<String> images=new ArrayList<>();
    private String status;
    private String price;
    private String discount;
    private String stock;
    private String id;

    public Product() {}

    public Product(String WearType,String itemCategory,String name, ArrayList<String> images, String status, String price, String discount, String stock, String id) {
        this.WearType=WearType;
        this.itemCategory=itemCategory;
        this.name = name;
        this.images = images;
        this.status = status;
        this.price = price;
        this.discount = discount;
        this.stock = stock;
        this.id = id;
    }
    public Product(String name, ArrayList<String> images, String status, String price, String discount, String stock, String id) {
        this.name = name;
        this.images = images;
        this.status = status;
        this.price = price;
        this.discount = discount;
        this.stock = stock;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWearType() {
        return WearType;
    }

    public void setWearType(String wearType) {
        WearType = wearType;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
