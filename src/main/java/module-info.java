module com.erpinterns.java_erpinterns {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.erpinterns.java_erpinterns to javafx.fxml;
    exports com.erpinterns.java_erpinterns;
}