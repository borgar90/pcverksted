package com.pcverksted.application;

import com.pcverksted.db.DatabaseUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import java.sql.Connection;
import java.util.Optional;

public class AddHardwareDialogController {

    @FXML
    private TextField nameField;

    @FXML
    private ComboBox<String> typeComboBox;

    @FXML
    private TextField priceField;

    @FXML
    private ComboBox<String> currencyComboBox;

    // Supported currencies
    private final List<String> SUPPORTED_CURRENCIES = Arrays.asList("NOK", "USD", "EUR");

    // Observable list of hardware types (for dynamic updates)
    private ObservableList<String> hardwareTypes;

    @FXML
    public void initialize() {
        // Populate ComboBox with supported currencies
        currencyComboBox.getItems().addAll(SUPPORTED_CURRENCIES);

        // Set NOK as the default value
        currencyComboBox.setValue("NOK");

        // Initialize hardware type ComboBox
        hardwareTypes = FXCollections.observableArrayList("Motherboard", "CPU", "RAM", "GPU", "Storage");
        typeComboBox.setItems(hardwareTypes);

        // Add option for adding a new type
        typeComboBox.getItems().add("Add new type...");

        // Listen for selection changes
        typeComboBox.setOnAction(event -> {
            if ("Add new type...".equals(typeComboBox.getValue())) {
                addNewType();
            }
        });
    }

    @FXML
    private void handleAddHardwareConfirm() {
        String name = nameField.getText();
        String type = typeComboBox.getValue();
        String price = priceField.getText();
        String currency = currencyComboBox.getValue();

        // Validate inputs
        if (name.isEmpty() || type.isEmpty() || price.isEmpty() || currency.isEmpty()) {
            System.out.println("Please fill in all fields.");
            return;
        }

        try {
            Double.parseDouble(price);  // Validate price as a numeric value
        } catch (NumberFormatException e) {
            System.out.println("Invalid price format.");
            return;
        }

        // If validation passes, save the hardware (your saveHardwareToDatabase method)
        saveHardwareToDatabase(name, type, price, currency);
    }

    @FXML
    private void handleCancel() {
        // Close the modal without saving
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }

    // Prompt user to add a new type
    private void addNewType() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add New Hardware Type");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter new hardware type:");

        // Get user input and add new type
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(type -> {
            if (!hardwareTypes.contains(type)) {
                hardwareTypes.add(type);
                typeComboBox.setValue(type);  // Set the new type as the selected value
            }
        });
    }


    private void saveHardwareToDatabase(String name, String type, String price, String currency) {
        String query = "INSERT INTO hardware (name, type, price, currency) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, type);
            stmt.setBigDecimal(3, new BigDecimal(price));
            stmt.setString(4, currency);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Hardware added successfully!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}