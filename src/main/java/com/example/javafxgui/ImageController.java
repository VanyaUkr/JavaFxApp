package com.example.javafxgui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class ImageController extends Controller{

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label label1;

    @FXML
    void initialize() throws SQLException {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Controller controller = new Controller();
        User user = new User();

        user.setPassword("12345");
        user.setUserName("alex");

        ResultSet resultSet = databaseHandler.getUser(user);
        System.out.println(loginField.getText());

        while (resultSet.next()) {
            String value = resultSet.getString(2);
            label1.setText("Hello " + value);
            System.out.println(value);
        }

    }
}
