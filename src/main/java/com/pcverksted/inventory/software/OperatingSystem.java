package com.pcverksted.inventory.software;

import com.pcverksted.inventory.Software;
import com.pcverksted.pc.Computer;

public class OperatingSystem extends Software {
    private String version;
    private String supportedArchitectures;

    public OperatingSystem(String name, double price, String currency, String version, String supportedArchitectures) {
        super(name, "Operating System", price, currency);
        this.version = version;
        this.supportedArchitectures = supportedArchitectures;
    }

    // Getters and Setters
    public String getVersion() {
        return version;
    }

    public String getSupportedArchitectures() {
        return supportedArchitectures;
    }

    @Override
    public void addToComputer( Computer computer) {
        // Specific behavior for adding an OS
        super.addToComputer(computer);  // Call the generic method
        // Additional logic for OS (if any)
    }

    @Override
    public String toString() {
        return getName() + " " + getVersion() + " - " + getSupportedArchitectures();
    }
}
