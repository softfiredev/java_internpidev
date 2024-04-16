package com.erpinterns.java_erpinterns.utils;

import com.erpinterns.java_erpinterns.controllers.UpdateHeadmasterController;
import com.erpinterns.java_erpinterns.models.HeadMasterDepartment;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {

    private static SceneManager instance;
    private Stage stage;

    private SceneManager(Stage stage) {
        this.stage = stage;
    }

    public static SceneManager getInstance(Stage stage) {
        if (instance == null) {
            instance = new SceneManager(stage);
        }
        return instance;
    }

    public void navigateToShowHeadmasters() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/erpinterns/java_erpinterns/HeadmasterList.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root,1000, 500);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void navigateToAddHeadmaster() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/erpinterns/java_erpinterns/HeadmasterForm.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root,1000, 500);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void navigateToUpdateHeadmaster(HeadMasterDepartment selectedHeadmaster) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/erpinterns/java_erpinterns/updateheadmaster.fxml"));
            Parent root = loader.load();

            UpdateHeadmasterController controller = loader.getController();
            controller.initData(selectedHeadmaster);

            Stage stage = new Stage();
            stage.setScene(new Scene(root, 1000,500));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
}
}
