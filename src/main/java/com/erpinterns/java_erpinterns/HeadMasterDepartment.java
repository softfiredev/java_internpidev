package com.erpinterns.java_erpinterns;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class HeadMasterDepartment {

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;

    @FXML
    private void addHeadmaster() {
        String name = nameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        System.out.println("Headmaster added: Name - " + name + ", Email - " + email + ", Phone - " + phone);
    }
}
