package gui.controllers;

import javafx.fxml.FXML;

public class TopMenuButtonsController {

    @FXML
    private MainController mainController;

    @FXML
    public void setMainController(MainController mainController) {

        this.mainController = mainController;
    }
    @FXML
    public void openFlights(){
        mainController.setCenter("/fxml/flight.fxml");
    }
    @FXML
    public void openPassengers() {
        mainController.setCenter("/fxml/passenger.fxml");
    }

    @FXML
    public void openAirlines() {
        System.out.println("openAirlines");
    }


    @FXML
    public void openAirports() {
        System.out.println("openAirports");
    }
}
