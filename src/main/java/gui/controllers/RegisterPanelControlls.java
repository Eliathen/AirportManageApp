package gui.controllers;

import entity.User;
import exceptions.LoginAlreadyExistException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import services.UserService;

import java.net.URL;
import java.util.EventListener;
import java.util.ResourceBundle;

public class RegisterPanelControlls implements Initializable {
    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    private Label resultLabel;

    public RegisterPanelControlls() {
    }

    public void addUser() throws LoginAlreadyExistException {
        try {
            User user = new User();
            user.setLogin(login.getText());
            user.setPassword(password.getText());
            System.out.println(user.toString());
            UserService userService = new UserService();
            userService.saveUser(user);
            resultLabel.setText("User created");
            resultLabel.setTextFill(Color.GREEN);
        }catch (LoginAlreadyExistException e){
            e.printStackTrace();
            resultLabel.setText("User already exist");
            resultLabel.setTextFill(Color.DARKRED);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
