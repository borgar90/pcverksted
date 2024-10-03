package com.pcverksted.inventory;
// Sales Record Class
import com.pcverksted.pc.Computer;

import java.util.Date;

public class SalesRecord {
    private Computer computer;
    private double saleAmount;
    private Date saleDate;

    public SalesRecord(Computer computer, double saleAmount) {
        this.computer = computer;
        this.saleAmount = saleAmount;
        this.saleDate = new Date(); // Sets the current date
    }

    @Override
    public String toString() {
        return "Sale Record:\n" + computer.toString() + "\nSale Amount: $" + saleAmount + "\nSale Date: " + saleDate;
    }
}