package com.pcverksted.pc;

public class Software {
    private String name;
    private double price;

    public Software(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Software: " + name + ", Price: $" + price;
    }
}
