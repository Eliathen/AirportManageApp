package gui.controllers;

import javafx.fxml.FXML;


public class TopMenuButtonsController {

    @FXML
    public static MainController mainController;

    @FXML
    public void setMainController(MainController mainController) {

        this.mainController = mainController;
    }

    @FXML
    public void openFlights(){
        mainController.setCenter("/fxml/flight.fxml");
        mainController.clearLeft();
    }

    @FXML
    public void openPassengers() {
        mainController.setCenter("/fxml/passenger.fxml");
        mainController.clearLeft();
    }

    @FXML
    public void openAirlines() {
        mainController.setLeft("/fxml/airline.fxml");
        mainController.clearCenter();
    }

    @FXML
    public void openTickets(){
        mainController.clearLeft();
        mainController.setCenter("/fxml/ticket.fxml");
    }

    @FXML
    public void openAirports() {
        mainController.clearLeft();
        mainController.setCenter("/fxml/airport.fxml");
    }
}