package gui.controllers;

import exceptions.ApplicationException;
import gui.utils.DialogsUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.UserService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginPanelController implements Initializable {

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    private Label resultLabel;

    @FXML
    private Button loginButton;

    public LoginPanelController() {

    }

    @FXML
    public void checkLoginAndPassword(){
        UserService userService = new UserService();
        try {
            if(userService.isCorrectLoginAndPassword(login.getText(), password.getText())){
                try {
                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    stage.close();
                    Parent root = FXMLLoader.load(getClass().getResource("/fxml/borderPaneMain.fxml"));
                    stage.setScene(new Scene(root));
                    stage.show();
                    stage.setResizable(false);
                    stage.setMinWidth(1024);
                    stage.setMinHeight(800);
                    stage.setTitle("Flight Managment");
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
            else{
                resultLabel.setText("Incorrect login or password");
            }
        } catch (ApplicationException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
    }

    @FXML
    public void addNewUser(){
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/registerPanel.fxml"));
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.showAndWait();
        }catch (IOException e){
            DialogsUtils.errorDialog(e.getMessage());
        }

    }
}
