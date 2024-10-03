package com.pcverksted.pc;

public class Part {
    private String name;
    private double price;

    public Part(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Part: " + name + ", Price: $" + price;
    }
}
