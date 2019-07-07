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
}
