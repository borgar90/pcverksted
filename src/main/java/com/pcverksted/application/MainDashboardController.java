package com.pcverksted.application;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainDashboardController {

    @FXML
    private GridPane computerGrid;

    private List<String> computers = new ArrayList<>();
    private String selectedComputer = null;

    @FXML
    public void initialize() {
        // Initial loading of computers (this could be fetched from a database)
        computers.add("Computer 1");
        computers.add("Computer 2");
        computers.add("Computer 3");
        computers.add("Computer 4");
        computers.add("Computer 5");

        loadComputers();
    }

    public void showMainDashboard() {
        try {
            // Load the MainDashboard FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/pcverksted/application/MainDashboard.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));

            // Show the main dashboard
            stage.show();

            // Optionally close the current stage (ViewComputer window)
            Stage currentStage = (Stage) computerGrid.getScene().getWindow(); // or use another control instead of `totalInvestmentLabel`
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddComputer() {
        try {
            // Load the AddComputerDialog FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/pcverksted/application/AddComputerDialog.fxml"));
            Stage dialogStage = new Stage(StageStyle.DECORATED);
            dialogStage.setTitle("Add New Computer");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.setScene(new Scene(loader.load()));

            // Set a reference to the MainDashboardController in the dialog
            AddComputerDialogController controller = loader.getController();
            controller.setMainController(this);

            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Navigate to the "View Computer" screen when a computer is selected or added
    private void viewComputer(String computerName) {
        try {
            // Load the ViewComputer FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/pcverksted/application/ViewComputer.fxml"));
            Stage viewComputerStage = new Stage();
            viewComputerStage.setScene(new Scene(loader.load()));

            // Set a reference to this controller in the ViewComputerController
            ViewComputerController controller = loader.getController();
            controller.setMainController(this, computerName);

            viewComputerStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    // When a computer is added, highlight it and navigate to the "View Computer" screen
    public void addComputer(String title) {
        computers.add(title);
        loadComputers();

        // Highlight the newly added computer and navigate to its view
        selectedComputer = title;
        highlightSelectedComputer();
        viewComputer(title);
    }

    private void loadComputers() {
        computerGrid.getChildren().clear();  // Clear the existing grid

        int columns = 4;  // 4 computers per row
        int rows = Math.min(3, (int) Math.ceil((double) computers.size() / columns));  // Maximum of 3 rows

        for (int i = 0; i < computers.size(); i++) {
            int row = i / columns;
            int col = i % columns;

            StackPane computerPane = createComputerPane(computers.get(i));
            computerGrid.add(computerPane, col, row);  // Add to grid
        }

        // Automatically highlight the selected computer
        highlightSelectedComputer();
    }

    private StackPane createComputerPane(String computerName) {
        StackPane pane = new StackPane();
        pane.setPrefSize(150, 150);

        // Create a rectangle as the background
        Rectangle rectangle = new Rectangle(150, 150);
        rectangle.setArcWidth(20);
        rectangle.setArcHeight(20);
        rectangle.setFill(Color.LIGHTGRAY);

        // Create a label for the computer name
        Label label = new Label(computerName);

        pane.getChildren().addAll(rectangle, label);

        // Add mouse hover effects for visual feedback
        pane.setOnMouseEntered(event -> handleMouseHoverEnter(pane));
        pane.setOnMouseExited(event -> handleMouseHoverExit(pane));

        // Set action for selection of a computer
        pane.setOnMouseClicked(event -> handleComputerSelection(event, computerName, pane));

        return pane;
    }

    private void handleComputerSelection(MouseEvent event, String computerName, StackPane pane) {
        // Handle selection of a computer
        selectedComputer = computerName;
        highlightSelectedComputer();
    }

    private void handleMouseHoverEnter(StackPane pane) {
        Rectangle rect = (Rectangle) pane.getChildren().get(0);
        rect.setFill(Color.LIGHTBLUE);  // Change color on hover
    }

    private void handleMouseHoverExit(StackPane pane) {
        Rectangle rect = (Rectangle) pane.getChildren().get(0);
        rect.setFill(Color.LIGHTGRAY);  // Revert color when no longer hovering
    }

    private void highlightSelectedComputer() {
        // Reset styles for all panes (deselect all)
        computerGrid.getChildren().forEach(node -> {
            StackPane stackPane = (StackPane) node;
            Rectangle rect = (Rectangle) stackPane.getChildren().get(0);
            rect.setStroke(null);
        });

        // Highlight the selected computer
        computerGrid.getChildren().forEach(node -> {
            StackPane stackPane = (StackPane) node;
            Label label = (Label) stackPane.getChildren().get(1);

            if (label.getText().equals(selectedComputer)) {
                Rectangle selectedRect = (Rectangle) stackPane.getChildren().get(0);
                selectedRect.setStroke(Color.BLUE);
                selectedRect.setStrokeWidth(3);

                // Fancy fade-in animation for the selected computer
                FadeTransition ft = new FadeTransition(Duration.millis(500), stackPane);
                ft.setFromValue(0.5);
                ft.setToValue(1.0);
                ft.play();
            }
        });
    }


    @FXML
    private void handleAddHardware() {
        try {
            // Load the AddHardwareDialog FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/pcverksted/application/AddHardwareDialog.fxml"));
            Stage dialogStage = new Stage(StageStyle.DECORATED);
            dialogStage.setTitle("Add New Hardware");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.setScene(new Scene(loader.load()));

            // Show modal and wait for user input
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddSoftware() {
        try {
            // Load the AddSoftwareDialog FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/pcverksted/application/AddSoftwareDialog.fxml"));
            Stage dialogStage = new Stage(StageStyle.DECORATED);
            dialogStage.setTitle("Add New Software");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.setScene(new Scene(loader.load()));

            // Show modal and wait for user input
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void handleViewInventory() {
        try {
            // Load the AddSoftwareDialog FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/pcverksted/application/InventoryDashboard.fxml"));
            Stage dialogStage = new Stage(StageStyle.DECORATED);
            dialogStage.setTitle("View Inventory");
            dialogStage.setScene(new Scene(loader.load()));

            // Show modal and wait for user input
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleViewReports() {
        // Logic to view reports
        System.out.println("Viewing reports...");
    }
}