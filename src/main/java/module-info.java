module com.erpinterns.java_erpinterns {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    exports com.erpinterns.java_erpinterns;
    opens com.erpinterns.java_erpinterns to javafx.fxml;

    exports com.erpinterns.java_erpinterns.controllers;
    opens com.erpinterns.java_erpinterns.controllers to javafx.fxml;

    exports com.erpinterns.java_erpinterns.models;
    opens com.erpinterns.java_erpinterns.models to javafx.fxml;
}