package com.exercise.lab5_ex2.model;

public class Phone {
    private String name;
    private String brand;
    private int releaseYear;
    private double price;
    private int image;

    public Phone(String name, String brand, int releaseYear, double price, int image) {
        this.name = name;
        this.brand = brand;
        this.releaseYear = releaseYear;
        this.price = price;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public double getPrice() {
        return price;
    }

    public int getImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
