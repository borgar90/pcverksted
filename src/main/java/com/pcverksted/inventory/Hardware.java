package com.pcverksted.inventory;

import com.pcverksted.inventory.hardware.CustomProperties;
import com.pcverksted.pc.Computer;

public class Hardware implements AddableToComputer {
    private String name;
    private String type; // Example: "CPU", "GPU", etc.
    private double price;
    private String currency;
    private CustomProperties customProperties; // For unique hardware capabilities

    public Hardware(String name, String type, double price, String currency) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.currency = currency;
        this.customProperties = new CustomProperties(); // Initialize custom properties
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

    public CustomProperties getCustomProperties() {
        return customProperties; // Return custom properties instance
    }

    @Override
    public void addToComputer(Computer computer) {
        computer.addHardware(this);
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - %.2f %s", name, type, price, currency);
    }
}