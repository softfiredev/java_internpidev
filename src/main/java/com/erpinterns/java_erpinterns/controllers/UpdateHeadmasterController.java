package com.erpinterns.java_erpinterns.controllers;

import com.erpinterns.java_erpinterns.models.HeadMasterDepartment;
import com.erpinterns.java_erpinterns.services.HeadmasterServiceImpl;
import com.erpinterns.java_erpinterns.utils.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateHeadmasterController {

    @FXML
    private TextField userIdField;

    @FXML
    private TextField professionalEmailField;

    @FXML
    private TextField protelField;

    private HeadMasterDepartment selectedHeadmaster;
    private final HeadmasterServiceImpl headmasterService;

    public UpdateHeadmasterController() {
        this.headmasterService = new HeadmasterServiceImpl();
    }

    public void initData(HeadMasterDepartment selectedHeadmaster) {
        this.selectedHeadmaster = selectedHeadmaster;
        userIdField.setText(String.valueOf(selectedHeadmaster.getUserId()));
        professionalEmailField.setText(selectedHeadmaster.getProfessionalEmail());
        protelField.setText(selectedHeadmaster.getProtel());
    }

    @FXML
    void updateHeadmaster(ActionEvent event) {
        int userId = Integer.parseInt(userIdField.getText());
        String professionalEmail = professionalEmailField.getText();
        String protel = protelField.getText();

        selectedHeadmaster.setProfessionalEmail(professionalEmail);
        selectedHeadmaster.setProtel(protel);
        headmasterService.updateHeadmaster(selectedHeadmaster);

        showAlert(Alert.AlertType.INFORMATION, "Success", "Headmaster updated successfully.");
    }

    private void navigateToShowHeadmasters() {
        Stage stage = (Stage) userIdField.getScene().getWindow();
        SceneManager sceneManager = SceneManager.getInstance(stage);
        sceneManager.navigateToShowHeadmasters();
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
