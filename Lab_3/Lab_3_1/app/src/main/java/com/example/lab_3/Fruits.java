package com.example.lab_3;

public class Fruits {
    private String fruitTitle;
    private String fruitDes;
    private int fruitImage;

    public Fruits(String fruitTitle, String fruitDes, int fruitImage) {
        this.fruitTitle = fruitTitle;
        this.fruitDes = fruitDes;
        this.fruitImage = fruitImage;
    }

    public String getFruitTitle() {
        return fruitTitle;
    }

    public void setFruitTitle(String fruitTitle) {
        this.fruitTitle = fruitTitle;
    }

    public String getFruitDes() {
        return fruitDes;
    }

    public void setFruitDes(String fruitDes) {
        this.fruitDes = fruitDes;
    }

    public int getFruitImage() {
        return fruitImage;
    }

    public void setFruitImage(int fruitImage) {
        this.fruitImage = fruitImage;
    }
}
