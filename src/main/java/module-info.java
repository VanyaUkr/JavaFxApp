module com.example.javafxgui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.example.javafxgui to javafx.fxml;
    exports com.example.javafxgui;
}