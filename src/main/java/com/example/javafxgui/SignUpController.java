package com.example.javafxgui;

import animations.Shake;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class SignUpController {

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private RadioButton radioButtonFemale;

    @FXML
    private RadioButton radioButtonMale;

    @FXML
    private ToggleGroup radioGroupGender;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField signUpCountry;

    @FXML
    private TextField signUpName;

    @FXML
    private TextField signUpSurname;

    @FXML
    void initialize() {

        signUpButton.setOnAction(event -> {

            if (!passwordField.getText().trim().equals("") && !loginField.getText().trim().equals("")) {
                signUpNewUser();
                signUpButton.getScene().getWindow().hide();
                Controller.openNewScene("app.fxml");
            } else {
                System.out.println("Empty login");
                Shake userLoginAnimation = new Shake(loginField);
                Shake userPasswordAnimation = new Shake(passwordField);
                userLoginAnimation.playAnimation();
                userPasswordAnimation.playAnimation();
            }
        });
    }

    private void signUpNewUser() {
        DatabaseHandler dbHandler = new DatabaseHandler();

        String firstName = signUpName.getText();
        String lastName = signUpSurname.getText();
        String userName = loginField.getText();
        String password = passwordField.getText();
        String location = signUpCountry.getText();
        String gender;
        if (radioButtonMale.isSelected())
            gender = "Male";
        else gender = "Female";

        User user = new User(firstName, lastName, userName, password, location, gender);
        dbHandler.signUpUser(user);
    }

}
