package com.a2.essteling;

import com.bumptech.glide.Glide;

import java.io.Serializable;

public class ShopItem implements Serializable {
    private String productId;
    private int price;
    private String description;
    private String name;

    //the id of the used image
    private int imageLocal;

    private String image;

    public ShopItem(String productId, String name, int price, String description, int imageLocal) {
        this.productId = productId;
        this.price = price;
        this.imageLocal = imageLocal;
        this.name = name;
        this.description = description;

    }

    public ShopItem(String productId, String name, int price, String description) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.description = description;

        this.imageLocal = R.drawable.ashizons_paprika_logo;
    }

    public ShopItem(String productId, String name,int price, String description, String image) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.image = image;
        this.imageLocal = -1;
        this.description = description;
    }

    public ShopItem(){}

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageLocal() {
        return imageLocal;
    }

    public void setImageLocal(int imageLocal) {
        this.imageLocal = imageLocal;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.imageLocal = -1;
        this.image = "https://mobiele-beleving-dev.herokuapp.com/cdn?file="+image;
    }
}