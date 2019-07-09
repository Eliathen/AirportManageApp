package gui.controllers;

import entity.Airport;
import exceptions.ApplicationException;
import exceptions.EmptyFieldException;
import gui.modelsFX.AirportModel;
import gui.utils.DialogsUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import services.AirportService;

public class AddAirportController {

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField addressTextField;

    @FXML
    private TextField cityTextField;

    @FXML
    private Label resultLabel;

    private Integer addOrUpdateChoice;

    private AirportModel airportModel;

    private AirportModel airportModelFromAirportController;

    @FXML
    public void initialize(){
        this.airportModel = new AirportModel();
        this.binds();
    }

    public void binds(){
        this.nameTextField.textProperty().bindBidirectional(this.airportModel.getAirportFXObjectProperty().nameProperty());
        this.addressTextField.textProperty().bindBidirectional(this.airportModel.getAirportFXObjectProperty().addressProperty());
        this.cityTextField.textProperty().bindBidirectional(this.airportModel.getAirportFXObjectProperty().cityProperty());

    }

    public TextField getNameTextField() {
        return nameTextField;
    }

    public void setNameTextField(TextField nameTextField) {
        this.nameTextField = nameTextField;
    }

    public TextField getAddressTextField() {
        return addressTextField;
    }

    public void setAddressTextField(TextField addressTextField) {
        this.addressTextField = addressTextField;
    }

    public TextField getCityTextField() {
        return cityTextField;
    }

    public void setCityTextField(TextField cityTextField) {
        this.cityTextField = cityTextField;
    }

    public Label getResultLabel() {
        return resultLabel;
    }

    public void setResultLabel(Label resultLabel) {
        this.resultLabel = resultLabel;
    }

    public Integer getAddOrUpdateChoice() {
        return addOrUpdateChoice;
    }

    public void setAddOrUpdateChoice(Integer addOrUpdateChoice) {
        this.addOrUpdateChoice = addOrUpdateChoice;
    }

    public AirportModel getAirportModel() {
        return airportModel;
    }

    public void setAirportModel(AirportModel airportModel) {
        this.airportModel = airportModel;
    }

    public AirportModel getAirportModelFromAirportController() {
        return airportModelFromAirportController;
    }

    public void setAirportModelFromAirportController(AirportModel airportModelFromAirportController) {
        this.airportModelFromAirportController = airportModelFromAirportController;
    }

    public void saveAirport() {
        try{
            if(nameTextField.getText().isBlank() || addressTextField.getText().isBlank() || cityTextField.getText().isBlank()){
                throw new EmptyFieldException("Empty Field");
            }
            else {
                if (getAddOrUpdateChoice() == 0) {
                    addNewAirport();
                    airportModelFromAirportController.init();
                } else {
                    updateAirport();
                    airportModelFromAirportController.init();
                }
            }
        }catch(EmptyFieldException e){
            resultLabel.setText(e.getMessage());
            resultLabel.setTextFill(Color.DARKRED);
        } catch (ApplicationException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
    }

    public void addNewAirport(){
        AirportService airportService = new AirportService();
        Airport airport = new Airport(nameTextField.getText(),addressTextField.getText(),cityTextField.getText());
        try {

            airportService.saveAirport(airport);
            resultLabel.setText("Save");
            resultLabel.setTextFill(Color.GREEN);
        }catch (Exception e) {
            try {
                airportModelFromAirportController.init();
            } catch (ApplicationException ex) {
                ex.printStackTrace();
            }
            resultLabel.setText(e.getMessage());
            resultLabel.setTextFill(Color.DARKRED);
        }
    }

    public void updateAirport(){
        AirportService airportService = new AirportService();
        Long id = airportModel.getAirportFXObjectProperty().getId();
        Airport airport = new Airport(id, nameTextField.getText(),addressTextField.getText(),cityTextField.getText());
        try {

            airportService.updateAirport(airport);
            resultLabel.setText("Save");
            resultLabel.setTextFill(Color.GREEN);
        }catch (Exception e) {
            try {
                airportModelFromAirportController.init();
            } catch (ApplicationException ex) {
                ex.printStackTrace();
            }
            resultLabel.setText(e.getMessage());
            resultLabel.setTextFill(Color.DARKRED);
        }
    }

    public void clearFields(){
        this.nameTextField.clear();
        this.addressTextField.clear();
        this.cityTextField.clear();
    }
}