package com.pcverksted.application;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddSoftwareController {

    @FXML
    private TextField softwareName;

    @FXML
    private TextField licensePrice;

    @FXML
    private void handleAddSoftware() {
        String softwareNameValue = softwareName.getText();
        double licensePriceValue = Double.parseDouble(licensePrice.getText());

        // Logic to add software to the system
        System.out.println("Software Added: " + softwareNameValue + " with license price: " + licensePriceValue);

        // Clear fields
        softwareName.clear();
        licensePrice.clear();
    }
}