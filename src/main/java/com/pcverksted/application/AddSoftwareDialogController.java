package com.pcverksted.application;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Set;

public class AddSoftwareDialogController {

    @FXML
    private TextField titleField;

    @FXML
    private TextField typeField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField currencyField;

    // Constants for supported currencies
    private static final Set<String> SUPPORTED_CURRENCIES = Set.of("NOK", "USD", "EUR");

    // Store software types dynamically
    private static final Set<String> SOFTWARE_TYPES = new HashSet<>();

    @FXML
    private void handleAddSoftwareConfirm() {
        String title = titleField.getText();
        String type = typeField.getText();
        String price = priceField.getText();
        String currency = currencyField.getText().toUpperCase();

        // Validate input fields
        if (title.isEmpty() || type.isEmpty() || price.isEmpty() || currency.isEmpty()) {
            System.out.println("Please fill in all fields.");
            return;
        }

        // Validate currency
        if (!SUPPORTED_CURRENCIES.contains(currency)) {
            System.out.println("Unsupported currency. Please enter NOK, USD, or EUR.");
            return;
        }

        // Validate price is a number
        try {
            Double.parseDouble(price);
        } catch (NumberFormatException e) {
            System.out.println("Invalid price. Please enter a valid number.");
            return;
        }

        // Add new type to the list of software types
        SOFTWARE_TYPES.add(type);

        // Add logic to save the software (e.g., save to a database or update UI)
        System.out.println("Software added: " + title + " (" + type + "), Price: " + price + " " + currency);

        // Close the modal
        Stage stage = (Stage) titleField.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleCancel() {
        // Close the modal without saving
        Stage stage = (Stage) titleField.getScene().getWindow();
        stage.close();
    }
}