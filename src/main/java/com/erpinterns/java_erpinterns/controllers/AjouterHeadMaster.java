package com.erpinterns.java_erpinterns.controllers;

import com.erpinterns.java_erpinterns.interfaces.HeadMasterService;
import com.erpinterns.java_erpinterns.models.HeadMasterDepartment;
import com.erpinterns.java_erpinterns.services.HeadmasterServiceImpl;
import com.erpinterns.java_erpinterns.utils.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;

public class AjouterHeadMaster {

    @FXML
    private ComboBox<String> cbUsers; // Change ComboBox type to ComboBox<String>

    @FXML
    private TextField tfProfessionalEmail;

    @FXML
    private TextField tfProtel;

    private HeadMasterService headmasterService;
    private int selectedUserId;

    public AjouterHeadMaster() {
        this.headmasterService = new HeadmasterServiceImpl();
    }

    @FXML
    void initialize() {
        loadUsers();
    }

    private void loadUsers() {
        List<String> internFirstNames = headmasterService.getInternFirstNames(); // Fetch first names
        cbUsers.getItems().addAll(internFirstNames);

        // Listen for ComboBox selection changes
        cbUsers.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Fetch and store the ID of the selected user based on the selected first name
                selectedUserId = headmasterService.getUserIdByFirstName(newValue);
            }
        });
    }

    @FXML
    void addHeadmaster() {
        String selectedFirstName = cbUsers.getValue();
        String professionalEmail = tfProfessionalEmail.getText();
        String protel = tfProtel.getText();

        if (selectedFirstName == null || selectedFirstName.isEmpty() || professionalEmail.isEmpty() || protel.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please select a user and fill in all fields.");
            return;
        }

        // Create a new HeadMasterDepartment object and add to database using the stored user ID
        HeadMasterDepartment headmaster = new HeadMasterDepartment(selectedUserId, professionalEmail, protel);
        headmasterService.addHeadmaster(headmaster);

        // Update user's role to Headmaster using the stored user ID
        headmasterService.updateRoleToHeadmaster(selectedUserId);

        showAlert(Alert.AlertType.INFORMATION, "Success", "Headmaster added successfully.");
    }

    @FXML
    void showHeadmastersPage(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneManager sceneManager = SceneManager.getInstance(stage);
        sceneManager.navigateToShowHeadmasters();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
