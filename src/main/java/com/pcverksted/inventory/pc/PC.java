package com.pcverksted.inventory.pc;

import com.pcverksted.inventory.Hardware;
import com.pcverksted.inventory.Software;

import java.util.ArrayList;
import java.util.List;

public class PC {
    private List<Hardware> hardwareList = new ArrayList<>();
    private List<Software> softwareList = new ArrayList<>();
    private double totalInvested;
    private double totalProfit;
    private double sellPrice;
    private int id;




    // Getters and setters for the fields

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public List<Hardware> getHardwareList() {
        return hardwareList;
    }

    public void addHardware(Hardware hardware) {
        hardwareList.add(hardware);
    }

    public List<Software> getSoftwareList() {
        return softwareList;
    }

    public void addSoftware(Software software) {
        softwareList.add(software);
    }

    public double getTotalInvested() {
        return totalInvested;
    }

    public void setTotalInvested(double totalInvested) {
        this.totalInvested = totalInvested;
    }

    public double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(double totalProfit) {
        this.totalProfit = totalProfit;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public double calculateTotalInvested() {
        double total = 0.0;

        // Sum prices of hardware
        for (Hardware hardware : this.getHardwareList()) {
            total += hardware.getPrice();
        }

        // Sum prices of software
        for (Software software : this.getSoftwareList()) {
            total += software.getPrice();
        }

        return total;
    }


    // toString for Finn.no add text in Norwegian
    @Override
    public String toString() {
        return "PC til salgs:\n" +
                "Totalt investert: " + totalInvested + " NOK\n" +
                "Salgspris: " + sellPrice + " NOK\n" +
                "Fortjeneste: " + totalProfit + " NOK\n" +
                "\nSpesifikasjoner:\n" +
                getHardwareSpecToString() +
                "\nInstallert programvare:\n" +
                getSoftwareListToString();
    }

    // Generate hardware specification string
    public String getHardwareSpecToString() {
        StringBuilder sb = new StringBuilder();
        for ( Hardware hardware : hardwareList) {
            sb.append(hardware.toString()).append("\n");
        }
        return sb.toString();
    }

    // Generate software list string
    public String getSoftwareListToString() {
        StringBuilder sb = new StringBuilder();
        for ( Software software : softwareList) {
            sb.append(software.toString()).append("\n");
        }
        return sb.toString();
    }
}