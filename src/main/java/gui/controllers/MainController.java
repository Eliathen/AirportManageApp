package gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;


public class MainController {

    @FXML
    private BorderPane borderPaneMain;

    @FXML
    private TopMenuButtonsController topMenuButtonsController;

    public void initialize(){
        topMenuButtonsController.setMainController(this);

    }

    public void setCenter(String fxmlPath){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(fxmlPath));
        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        borderPaneMain.setCenter(parent);
    }

    public void setLeft(String fxmlPath){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(fxmlPath));
        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        borderPaneMain.setLeft(parent);
    }

    public BorderPane getBorderPaneMain() {
        return borderPaneMain;
    }

    public void setBorderPaneMain(BorderPane borderPaneMain) {
        this.borderPaneMain = borderPaneMain;
    }

    public void clearLeft(){
        borderPaneMain.setLeft(null);
    }

    public void clearCenter(){
        borderPaneMain.setCenter(null);
    }
}