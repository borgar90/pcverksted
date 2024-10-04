package com.pcverksted.db;

import com.pcverksted.inventory.pc.PC;
import com.pcverksted.inventory.Hardware;
import com.pcverksted.inventory.Software;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PCDAO {
    private Connection connection;

    public PCDAO(Connection connection) {
        this.connection = connection;
    }

    // Create a new PC entry
    public void addPC(PC pc) throws SQLException {
        String sql = "INSERT INTO pcs (total_invested, total_profit, sell_price) VALUES (?, ?, ?) RETURNING id";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, pc.getTotalInvested());
            stmt.setDouble(2, pc.getTotalProfit());
            stmt.setDouble(3, pc.getSellPrice());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int pcId = rs.getInt("id");
                // Save hardware and software for this PC
                saveHardware(pcId, pc);
                saveSoftware(pcId, pc);
            }
        }
    }

    private void saveHardware(int pcId, PC pc) throws SQLException {
        String sql = "INSERT INTO hardware (pc_id, name, type, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (Hardware hardware : pc.getHardwareList()) {
                stmt.setInt(1, pcId);
                stmt.setString(2, hardware.getName());
                stmt.setString(3, hardware.getType());
                stmt.setDouble(4, hardware.getPrice());
                stmt.addBatch();
            }
            stmt.executeBatch(); // Execute all inserts in a batch
        }
    }

    private void saveSoftware(int pcId, PC pc) throws SQLException {
        String sql = "INSERT INTO software (pc_id, name, type, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (Software software : pc.getSoftwareList()) {
                stmt.setInt(1, pcId);
                stmt.setString(2, software.getName());
                stmt.setString(3, software.getType());
                stmt.setDouble(4, software.getPrice());
                stmt.addBatch();
            }
            stmt.executeBatch(); // Execute all inserts in a batch
        }
    }

    // Method to get PC by ID (read)
    public PC getPC(int id) throws SQLException {
        PC pc = null;
        String sql = "SELECT * FROM pcs WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                pc = new PC();
                pc.setSellPrice(rs.getDouble("sell_price"));

                // Load hardware and software for this PC
                loadHardware(pc);
                loadSoftware(pc);

                // Calculate total invested based on the hardware and software components
                double totalInvested = pc.calculateTotalInvested();
                pc.setTotalInvested(totalInvested);
                pc.setTotalProfit(pc.getSellPrice() - totalInvested); // Assuming profit is calculated this way
            }
        }
        return pc;
    }

    private void loadHardware(PC pc) throws SQLException {
        String sql = "SELECT * FROM hardware WHERE pc_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, pc.getId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Hardware hardware = new Hardware(rs.getString("name"), rs.getString("type"), rs.getDouble("price"), rs.getString("currency"));
                pc.addHardware(hardware);
            }
        }
    }

    private void loadSoftware(PC pc) throws SQLException {
        String sql = "SELECT * FROM software WHERE pc_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, pc.getId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Software software = new Software(rs.getString("name"), rs.getDouble("price"));
                software.setType(rs.getString("type"));
                pc.addSoftware(software);
            }
        }
    }

    // You can add methods for updating and deleting PCs as needed
}
