package com.pcverksted.inventory.hardware;

import com.pcverksted.inventory.Hardware;
import com.pcverksted.pc.Computer;

public class CPU extends Hardware {
    private double clockSpeed;
    private int cores;

    public CPU(String name, double price, String currency, double clockSpeed, int cores) {
        super(name, "CPU", price, currency);
        this.clockSpeed = clockSpeed;
        this.cores = cores;
    }

    // Getters and Setters
    public double getClockSpeed() {
        return clockSpeed;
    }

    public int getCores() {
        return cores;
    }

    @Override
    public void addToComputer( Computer computer) {
        // Specific behavior for adding a CPU
        super.addToComputer(computer);  // Call the generic method
        // Additional logic for CPU (if any)
    }

    @Override
    public String toString() {
        return getName() + " - " + getCores() + " cores, " + getClockSpeed() + " GHz";
    }
}
