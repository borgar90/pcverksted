package com.pcverksted.application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ReportController {

    @FXML
    private Label totalInvestmentLabel;

    @FXML
    private Label stockValueLabel;

    @FXML
    private Label salesLabel;

    public void initialize() {
        // Example values - Fetch real values from inventory and sales data
        totalInvestmentLabel.setText("Total Investment: $5000");
        stockValueLabel.setText("Total Stock Value: $6250");
        salesLabel.setText("Total Sales: $3000");
    }
}