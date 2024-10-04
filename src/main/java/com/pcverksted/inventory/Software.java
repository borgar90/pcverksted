package com.pcverksted.inventory;

import com.pcverksted.pc.Computer;

public abstract class Software implements AddableToComputer {
    private String name;
    private String type;
    private double price;
    private String currency;

    public Software(String name, String type, double price, String currency) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.currency = currency;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public void addToComputer( Computer computer) {
        // Default behavior for adding software to a computer
        computer.addSoftware(this);
    }

    // Other common methods for software
}