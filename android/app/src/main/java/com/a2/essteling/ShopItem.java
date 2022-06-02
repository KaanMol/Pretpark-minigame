package com.a2.essteling;

import com.bumptech.glide.Glide;

import java.io.Serializable;

public class ShopItem implements Serializable {
    private String name;
    private String price;
    private String description;

    //the id of the used image
    private int image;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public ShopItem(String name, String price, String description,int imageID) {
        this.name = name;
        this.price = price;
        this.image = imageID;
        this.description = description;
        
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ShopItem(String name, String price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;

        this.image = R.drawable.ashizons_paprika_logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
