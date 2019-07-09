package gui.controllers;

import entity.User;
import exceptions.ApplicationException;
import exceptions.EmptyFieldException;
import exceptions.LoginAlreadyExistException;
import gui.utils.DialogsUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import services.UserService;

import java.net.URL;
import java.util.EventListener;
import java.util.ResourceBundle;


public class RegisterPanelController implements Initializable {

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    private Label resultLabel;

    @FXML
    private Button registerButton;

    public RegisterPanelController() {
    }

    public void addUser(){
        try {
            if(login.getText().trim().isEmpty()|| password.getText().trim().isEmpty()){
                throw new EmptyFieldException("Empty Field");
            }
            User user = new User();
            user.setLogin(login.getText());
            user.setPassword(password.getText());
            UserService userService = new UserService();
            userService.saveUser(user);
            resultLabel.setText("User created");
            resultLabel.setTextFill(Color.GREEN);
        }catch (LoginAlreadyExistException | EmptyFieldException e){
            resultLabel.setText(e.getMessage());
            resultLabel.setTextFill(Color.DARKRED);
        } catch (ApplicationException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
