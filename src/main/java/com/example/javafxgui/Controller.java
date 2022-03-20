package com.example.javafxgui;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import animations.Shake;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    protected TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registerButton;

    @FXML
    private Button signInButton;

    @FXML
    void initialize() throws SQLException {

        signInButton.setOnAction(event -> {
            String loginText = loginField.getText().trim();
            String loginPassword = passwordField.getText().trim();

            if (!loginText.equals("") && !loginPassword.equals("")) {
                loginUser(loginText, loginPassword);
//                System.out.println(loginText);
            } else {
                System.out.println("Empty field");
                Shake userLoginAnimation = new Shake(loginField);
                Shake userPasswordAnimation = new Shake(passwordField);
                userLoginAnimation.playAnimation();
                userPasswordAnimation.playAnimation();
            }
        });

        registerButton.setOnAction(event -> {
//            registerButton.getScene().getWindow().hide();
            openNewScene("signUp.fxml");
        });
    }

    private void loginUser(String loginText, String loginPassword) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();
        user.setUserName(loginText);
        user.setPassword(loginPassword);
        ResultSet result = dbHandler.getUser(user);

        int counter = 0;

        while (true) {
            try {
                if (!result.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            counter++;
        }
        if (counter >= 1) {
            System.out.println("Success!");
            //            signInButton.getScene().getWindow().hide();
            openNewScene("app.fxml", "Hello user");
        } else {
            System.out.println("Wrong login or password!");
            Shake userLoginAnimation = new Shake(loginField);
            Shake userPasswordAnimation = new Shake(passwordField);
            userLoginAnimation.playAnimation();
            userPasswordAnimation.playAnimation();
        }
    }

    public static void openNewScene(String window, String sceneName) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ImageController.class.getResource(window));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle(sceneName);
        stage.showAndWait();
    }

    public static void openNewScene(String window) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ImageController.class.getResource(window));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
}




