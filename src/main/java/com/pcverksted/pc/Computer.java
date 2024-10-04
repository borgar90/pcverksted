package com.pcverksted.pc;
import com.pcverksted.inventory.Hardware;
import com.pcverksted.inventory.Software;
import java.util.ArrayList;
import java.util.List;

public class Computer {
    private List<Hardware> hardwareComponents;
    private List<Software> softwareComponents;

    public Computer() {
        this.hardwareComponents = new ArrayList<>();
        this.softwareComponents = new ArrayList<>();
    }

    public void addHardware(Hardware hardware) {
        hardwareComponents.add(hardware);
        System.out.println("Added hardware: " + hardware.getName());
    }

    public void addSoftware(Software software) {
        softwareComponents.add(software);
        System.out.println("Added software: " + software.getName());
    }

    public List<Hardware> getHardwareComponents() {
        return hardwareComponents;
    }

    public List<Software> getSoftwareComponents() {
        return softwareComponents;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Computer Configuration:\n");
        sb.append("Hardware:\n");
        for ( Hardware hardware : hardwareComponents) {
            sb.append(" - ").append(hardware).append("\n");
        }
        sb.append("Software:\n");
        for (Software software : softwareComponents) {
            sb.append(" - ").append(software).append("\n");
        }
        return sb.toString();
    }
}
