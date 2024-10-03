package com.pcverksted.pc;
import java.util.ArrayList;
import java.util.List;
public class Computer {
    private List<Part> parts;
    private List<Software> softwareList;

    public Computer() {
        parts = new ArrayList<>();
        softwareList = new ArrayList<>();
    }

    public void addPart(Part part) {
        parts.add(part);
    }

    public void addSoftware(Software software) {
        softwareList.add(software);
    }

    public double calculateTotalInvestment() {
        double total = 0;
        for (Part part : parts) {
            total += part.getPrice();
        }
        for (Software software : softwareList) {
            total += software.getPrice();
        }
        return total;
    }

    public double calculateTotalValue() {
        return calculateTotalInvestment() * 1.25; // Adding 25% markup
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Computer Configuration:\nParts:\n");
        for (Part part : parts) {
            sb.append(part.toString()).append("\n");
        }
        sb.append("\nSoftware:\n");
        for (Software software : softwareList) {
            sb.append(software.toString()).append("\n");
        }
        return sb.toString();
    }
}
