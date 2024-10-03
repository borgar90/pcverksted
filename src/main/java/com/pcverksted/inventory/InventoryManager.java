package com.pcverksted.inventory;
import com.pcverksted.pc.Computer;

import java.util.ArrayList;
import java.util.List;

public class InventoryManager {
    private List<Computer> computers;
    private List<SalesRecord> salesRecords;

    public InventoryManager() {
        computers = new ArrayList<>();
        salesRecords = new ArrayList<>();
    }

    public void addComputer(Computer computer) {
        computers.add(computer);
    }

    public void registerSale(Computer computer, double saleAmount) {
        SalesRecord record = new SalesRecord(computer, saleAmount);
        salesRecords.add(record);
    }

    public double getTotalInvestment() {
        double total = 0;
        for ( Computer computer : computers) {
            total += computer.calculateTotalInvestment();
        }
        return total;
    }

    public double getTotalStockValue() {
        double total = 0;
        for (Computer computer : computers) {
            total += computer.calculateTotalValue();
        }
        return total;
    }

    public void generateReport() {
        System.out.println("Total Investment: $" + getTotalInvestment());
        System.out.println("Total Value in Stock: $" + getTotalStockValue());
        System.out.println("\nSales Records:");
        for (SalesRecord record : salesRecords) {
            System.out.println(record);
        }
    }
}