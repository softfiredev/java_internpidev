package com.erpinterns.java_erpinterns.controllers;

import com.erpinterns.java_erpinterns.interfaces.HeadMasterService;
import com.erpinterns.java_erpinterns.models.HeadMasterDepartment;
import com.erpinterns.java_erpinterns.models.User;
import com.erpinterns.java_erpinterns.services.HeadmasterServiceImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.List;


public class AjouterHeadMaster {

    @FXML
    private ComboBox<User> cbUsers;

    @FXML
    private TextField tfProfessionalEmail;

    @FXML
    private TextField tfProtel;

    private HeadMasterService headmasterService;

    public AjouterHeadMaster() {
        this.headmasterService = new HeadmasterServiceImpl(); // Instantiate service
    }

    @FXML
    void initialize() {
        loadUsers();
    }

    private void loadUsers() {
        List<User> internUsers = headmasterService.getUsersByRole("Intern");
        cbUsers.getItems().addAll(internUsers);
    }

    @FXML
    void addHeadmaster() {
        User selectedUser = cbUsers.getValue();
        String professionalEmail = tfProfessionalEmail.getText();
        String protel = tfProtel.getText();

        if (selectedUser == null || professionalEmail.isEmpty() || protel.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please select a user and fill in all fields.");
            return;
        }

        HeadMasterDepartment headmaster = new HeadMasterDepartment(selectedUser.getId(), professionalEmail, protel);
        headmasterService.addHeadmaster(headmaster);

        headmasterService.updateRoleToHeadmaster(selectedUser.getId());

        showAlert(Alert.AlertType.INFORMATION, "Success", "Headmaster added successfully.");
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
