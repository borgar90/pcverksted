package com.pcverksted.application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewComputerController {

    @FXML
    private VBox hardwareTypeList;  // To display hardware types
    @FXML
    private VBox installedPartsList;  // To display installed parts
    @FXML
    private VBox drawer;  // The drawer for adding parts
    @FXML
    private Label totalInvestmentLabel;
    @FXML
    private Label totalProfitLabel;

    private String currentComputer;  // Holds the name of the current computer
    private MainDashboardController mainController;

    public void setMainController(MainDashboardController mainController, String computer) {
        this.mainController = mainController;
        this.currentComputer = computer;
        loadComputerData();
    }



    // Populate the data related to the selected computer
    private void loadComputerData() {
        // Clear the existing lists
        hardwareTypeList.getChildren().clear();
        installedPartsList.getChildren().clear();

        // Example data population for hardware types and parts
        hardwareTypeList.getChildren().add(new Label("CPU"));
        installedPartsList.getChildren().add(new Label("Ryzen 5"));

        hardwareTypeList.getChildren().add(new Label("GPU"));
        installedPartsList.getChildren().add(new Label("NVIDIA GTX 3070"));

        // Update mini-report values (for example)
        totalInvestmentLabel.setText("$1500");
        totalProfitLabel.setText("$1875");
    }

    // Handle the "Back" button, return to the dashboard
    @FXML
    private void handleBackButton() {
        Stage stage = (Stage) totalInvestmentLabel.getScene().getWindow();
        stage.close();
        mainController.showMainDashboard();
    }

    // Show the drawer when adding a part
    @FXML
    private void handleAddPart() {
        drawer.setVisible(true);  // Open the drawer
    }

    // Select a part type in the drawer
    @FXML
    private void selectPartType() {
        // Logic for selecting a part type and displaying available parts of that type
        System.out.println("Part type selected!");
    }

    @FXML
    private void handleAddHardware() {
        System.out.println("Add Hardware button clicked.");
        // Logic to handle adding hardware
    }

    @FXML
    private void handleAddSoftware() {
        System.out.println("Add Software button clicked.");
        // Logic to handle adding software
    }

    @FXML
    private void handleSellComputer() {
        System.out.println("Sell button clicked.");
        // Logic to sell the computer
    }

    @FXML
    private void handleDiscardComputer() {
        System.out.println("Discard button clicked.");
        // Logic to discard the computer
    }

    @FXML
    private void handleReturnComputer() {
        System.out.println("Return button clicked.");
        // Logic to return the computer
    }
}