package com.a2.essteling;

public class ShopItem {
    private String name;
    private String price;

    //the id of the used image
    private int image;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public ShopItem(String name, String price, int imageID) {
        this.name = name;
        this.price = price;
        this.image = imageID;
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
