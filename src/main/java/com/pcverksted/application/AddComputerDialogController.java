package com.pcverksted.application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddComputerDialogController {

    @FXML
    private TextField computerTitleField;

    @FXML
    private Label errorLabel;

    private MainDashboardController mainController;

    // Method to set the reference to the MainDashboardController
    public void setMainController(MainDashboardController controller) {
        this.mainController = controller;
    }

    @FXML
    private void handleAddComputer() {
        String title = computerTitleField.getText().trim();

        // Validate title (must be at least 2 characters long)
        if (title.length() < 2) {
            errorLabel.setText("Title must be at least 2 characters long.");
            errorLabel.setVisible(true);
        } else {
            // Add the computer to the main list
            mainController.addComputer(title);

            // Close the dialog
            Stage stage = (Stage) computerTitleField.getScene().getWindow();
            stage.close();
        }
    }
}