package com.erpinterns.java_erpinterns.controllers;

import com.erpinterns.java_erpinterns.models.HeadMasterDepartment;
import com.erpinterns.java_erpinterns.services.HeadmasterServiceImpl;
import com.erpinterns.java_erpinterns.utils.SceneManager;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.util.List;

public class ShowHeadmastersController {

    @FXML
    private TableView<HeadMasterDepartment> tableView;

    @FXML
    private TableColumn<HeadMasterDepartment, Integer> userIdColumn;

    @FXML
    private TableColumn<HeadMasterDepartment, String> professionalEmailColumn;

    @FXML
    private TableColumn<HeadMasterDepartment, String> protelColumn;

    private final HeadmasterServiceImpl headmasterService;
    private ObservableList<HeadMasterDepartment> headmasterList;

    public ShowHeadmastersController() {
        this.headmasterService = new HeadmasterServiceImpl();
        this.headmasterList = FXCollections.observableArrayList();
    }

    @FXML
    public void initialize() {
        initializeColumns();
        loadData();

    }

    private void initializeColumns() {
        userIdColumn.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getUserId()).asObject());
        professionalEmailColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getProfessionalEmail()));
        protelColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getProtel()));
    }

    private void loadData() {
        List<HeadMasterDepartment> headmasters = headmasterService.getAllHeadmasters();
        tableView.getItems().addAll(headmasters);
    }

    @FXML
    void showAddHeadmastersPage(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneManager sceneManager = SceneManager.getInstance(stage);
        sceneManager.navigateToAddHeadmaster();
    }


    @FXML
    void deleteHeadmaster() {
        HeadMasterDepartment selectedHeadmaster = tableView.getSelectionModel().getSelectedItem();
        if (selectedHeadmaster != null) {
            int userId = selectedHeadmaster.getUserId();
            headmasterService.deleteHeadmaster(userId);
            headmasterService.updateRoleToIntern(userId);
            headmasterList.remove(selectedHeadmaster);

            showAlert(Alert.AlertType.INFORMATION, "Success", "Headmaster deleted successfully.");
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Please select a headmaster to delete.");
        }
    }


    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
