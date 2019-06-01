package gui.controllers;

import entity.Passenger;
import exceptions.EmptyFieldException;
import exceptions.PassengerAlreadyExistException;
import exceptions.WrongPeselException;
import gui.modelsFX.PassengerModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import services.PassengerService;
import validators.PeselValidator;

public class AddPassengerController {
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField surnameTextField;
    @FXML
    private TextField peselTextField;
    @FXML
    private Label resultLabel;

    private Integer addOrUpdateChoice;

    private PassengerModel passengerModel;

    private PassengerModel passengerModelFromPassengerController;

    @FXML
    public void initialize(){
        this.passengerModel = new PassengerModel();
        this.binds();
    }
    public void binds(){
        this.nameTextField.textProperty().bindBidirectional(this.passengerModel.getPassengerFXObjectProperty().nameProperty());
        this.surnameTextField.textProperty().bindBidirectional(this.passengerModel.getPassengerFXObjectProperty().surnameProperty());
        this.peselTextField.textProperty().bindBidirectional(this.passengerModel.getPassengerFXObjectProperty().peselProperty());

    }
    public TextField getNameTextField() {
        return nameTextField;
    }

    public void setNameTextField(TextField nameTextField) {
        this.nameTextField = nameTextField;
    }

    public TextField getSurnameTextField() {
        return surnameTextField;
    }

    public void setSurnameTextField(TextField surnameTextField) {
        this.surnameTextField = surnameTextField;
    }

    public TextField getPeselTextField() {
        return peselTextField;
    }

    public void setPeselTextField(TextField peselTextField) {
        this.peselTextField = peselTextField;
    }


    public PassengerModel getPassengerModelFromPassengerController() {
        return passengerModelFromPassengerController;
    }

    public void setPassengerModelFromPassengerController(PassengerModel passengerModelFromPassengerController) {
        this.passengerModelFromPassengerController = passengerModelFromPassengerController;
    }

    public PassengerModel getPassengerModel() {
        return passengerModel;
    }

    public void setPassengerModel(PassengerModel passengerModel) {
        this.passengerModel = passengerModel;
    }

    public Integer getAddOrUpdateChoice() {
        return addOrUpdateChoice;
    }

    public void setAddOrUpdateChoice(Integer addOrUpdateChoice) {
        this.addOrUpdateChoice = addOrUpdateChoice;
    }

    public void savePassenger() {
        try{
            System.out.println(nameTextField.getText());
            System.out.println(surnameTextField.getText());
            System.out.println(peselTextField.getText());
            if(nameTextField.getText().isBlank() || surnameTextField.getText().isBlank() || peselTextField.getText().isBlank()){
                throw new EmptyFieldException("Empty Field");
            }
            else {
                if (getAddOrUpdateChoice() == 0) {
                    addNewPassanger();
                    passengerModelFromPassengerController.init();
                } else {
                    updatePassanger();
                    passengerModelFromPassengerController.init();
                }
            }
        }catch(EmptyFieldException e){
            resultLabel.setText(e.getMessage());
            resultLabel.setTextFill(Color.DARKRED);
        }catch(Exception e){
            resultLabel.setText("Incorrect format");
            resultLabel.setTextFill(Color.DARKRED);
        }
    }
    public void addNewPassanger(){
        PassengerService passengerService = new PassengerService();
        Passenger passenger = new Passenger(nameTextField.getText(),surnameTextField.getText(),peselTextField.getText());
        try {
            PeselValidator peselValidator = new PeselValidator();
            peselValidator.validate(passenger.getPesel());
            passengerService.savePassenger(passenger);
            resultLabel.setText("Save");
            resultLabel.setTextFill(Color.GREEN);
        }catch (WrongPeselException|PassengerAlreadyExistException e) {
            passengerModelFromPassengerController.init();
            resultLabel.setText(e.getMessage());
            resultLabel.setTextFill(Color.DARKRED);
        }
    }
    public void updatePassanger(){
        PassengerService passengerService = new PassengerService();
        Long id = passengerModel.getPassengerFXObjectProperty().getId();
        Passenger passenger = new Passenger(id, nameTextField.getText(),surnameTextField.getText(),peselTextField.getText());
        try {
            PeselValidator peselValidator = new PeselValidator();
            peselValidator.validate(passenger.getPesel());
            passengerService.updatePassenger(passenger);
            resultLabel.setText("Save");
            resultLabel.setTextFill(Color.GREEN);
        }catch (WrongPeselException|PassengerAlreadyExistException e) {
            passengerModelFromPassengerController.init();
            resultLabel.setText(e.getMessage());
            resultLabel.setTextFill(Color.DARKRED);
        }
    }
    public void clearFields(){
        this.nameTextField.clear();
        this.surnameTextField.clear();
        this.peselTextField.clear();
    }
}
