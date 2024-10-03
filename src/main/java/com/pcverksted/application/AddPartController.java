package com.pcverksted.application;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddPartController {

    @FXML
    private TextField partName;

    @FXML
    private TextField price;

    @FXML
    private void handleAddPart() {
        String partNameValue = partName.getText();
        double priceValue = Double.parseDouble(price.getText());

        // Logic to add part to inventory
        System.out.println("Part Added: " + partNameValue + " with price: " + priceValue);

        // Clear fields
        partName.clear();
        price.clear();
    }
}