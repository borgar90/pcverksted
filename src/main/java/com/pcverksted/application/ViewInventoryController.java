package com.pcverksted.application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Label;

import java.util.Optional;

public class ViewInventoryController {

    @FXML
    private ComboBox<String> hardwareComboBox;

    @FXML
    private ComboBox<String> softwareComboBox;

    @FXML
    private ComboBox<String> computerComboBox;

    @FXML
    private Label itemDetailsLabel;

    // Sample observable lists for hardware, software, and computers
    private ObservableList<String> hardwareItems = FXCollections.observableArrayList();
    private ObservableList<String> softwareItems = FXCollections.observableArrayList();
    private ObservableList<String> computerItems = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Initialize the ComboBoxes
        initializeComboBox(hardwareComboBox, "Hardware");
        initializeComboBox(softwareComboBox, "Software");
        initializeComboBox(computerComboBox, "Computer");

        // Load data from the database
        loadDataFromDatabase();
    }

    private void initializeComboBox(ComboBox<String> comboBox, String itemType) {
        comboBox.setItems(FXCollections.observableArrayList());  // Set default empty list
        comboBox.getItems().add("Add new " + itemType + "...");   // Add option to add new item

        comboBox.setOnAction(event -> {
            String selectedItem = comboBox.getValue();
            if (selectedItem != null) {
                if (selectedItem.startsWith("Add new")) {
                    // If user selects "Add new", show a dialog to add new item
                    addNewItem(comboBox, itemType);
                } else {
                    // If user selects an existing item, display its details
                    displayItemDetails(selectedItem, itemType);
                }
            }
        });
    }

    // Method to load data from the database
    private void loadDataFromDatabase() {
        // Example of loading data into hardware, software, and computer ComboBoxes
        loadHardwareItems();
        loadSoftwareItems();
        loadComputerItems();
    }

    private void loadHardwareItems() {
        // Retrieve hardware items from the database and add to the hardwareItems list
        // For now, adding some dummy data
        hardwareItems.addAll("Motherboard X", "CPU Ryzen", "RAM 16GB");

        // If no items, set ComboBox text to "No items available"
        if (hardwareItems.isEmpty()) {
            hardwareComboBox.setPromptText("No hardware items available");
        } else {
            hardwareComboBox.getItems().addAll(hardwareItems);
        }
    }

    private void loadSoftwareItems() {
        // Similar logic for software items
        softwareItems.addAll("Windows 10", "Antivirus Pro", "Adobe Photoshop");

        if (softwareItems.isEmpty()) {
            softwareComboBox.setPromptText("No software items available");
        } else {
            softwareComboBox.getItems().addAll(softwareItems);
        }
    }

    private void loadComputerItems() {
        // Similar logic for computer items
        computerItems.addAll("Gaming PC", "Office Workstation", "Developer Machine");

        if (computerItems.isEmpty()) {
            computerComboBox.setPromptText("No computer items available");
        } else {
            computerComboBox.getItems().addAll(computerItems);
        }
    }

    // Method to add a new item to the ComboBox
    private void addNewItem(ComboBox<String> comboBox, String itemType) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add New " + itemType);
        dialog.setHeaderText(null);
        dialog.setContentText("Enter new " + itemType + " name:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(newItem -> {
            comboBox.getItems().add(comboBox.getItems().size() - 1, newItem);  // Add new item before "Add new..."
            comboBox.setValue(newItem);  // Select the new item
            // Optionally save the new item to the database
        });
    }

    // Method to display item details
    private void displayItemDetails(String selectedItem, String itemType) {
        // Fetch details from the database for the selected item
        // For now, displaying mock data
        String itemDetails = "Details for " + itemType + " '" + selectedItem + "':\n";
        itemDetails += "Example detail 1\n";
        itemDetails += "Example detail 2\n";

        itemDetailsLabel.setText(itemDetails);
    }
}