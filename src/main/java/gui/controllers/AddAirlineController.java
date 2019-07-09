package gui.controllers;

import exceptions.ApplicationException;
import gui.modelsFX.AddAirlineModel;
import gui.modelsFX.AirlineFX;
import gui.utils.DialogsUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;


public class AddAirlineController {

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField countryTextField;

    @FXML
    private TextField otherDetailsTextField;

    @FXML
    private Label resultLabel;

    private AddAirlineModel addAirlineModel;

    private boolean isToSave;

    private AirlineController airlineController;

    public void init(){
        addAirlineModel = new AddAirlineModel();
        this.bindingsFields();

    }

    public void init(AirlineFX airlineFX){
        addAirlineModel = new AddAirlineModel();
        addAirlineModel.setAirlineFXObjectProperty(airlineFX);
        this.bindingsFields();

    }

    private void bindingsFields() {
        this.nameTextField.textProperty().bindBidirectional(this.addAirlineModel.getAirlineFXObjectProperty().nameProperty());
        this.countryTextField.textProperty().bindBidirectional(this.addAirlineModel.getAirlineFXObjectProperty().countryProperty());
        this.otherDetailsTextField.textProperty().bindBidirectional(this.addAirlineModel.getAirlineFXObjectProperty().otherDetailsProperty());

    }

    private boolean isAllTextFieldNotEmpty(){
        if(countryTextField !=null && countryTextField.getText()==null){
            return false;
        }
        if(nameTextField !=null && nameTextField.getText()==null){
            return false;
        }
        if(otherDetailsTextField !=null && otherDetailsTextField.getText()==null){
            return false;
        }
        return true;
    }

    public void saveAirline() {
        try {
            if (this.isAllTextFieldNotEmpty()) {
                if (isToSave) {
                    addAirlineModel.saveAirline();
                } else {
                    addAirlineModel.updateAirline();
                }
            } else {
                resultLabel.setText("Empty fields");
                resultLabel.setTextFill(Color.RED);
            }
            airlineController.initialize();
        }catch (ApplicationException e){
            DialogsUtils.errorDialog(e.getMessage());
        }
    }

    public AddAirlineModel getAddAirlineModel() {
        return addAirlineModel;
    }

    public void setAddAirlineModel(AddAirlineModel addAirlineModel) {
        this.addAirlineModel = addAirlineModel;
    }

    public boolean isToSave() {
        return isToSave;
    }

    public void setToSave(boolean toSave) {
        isToSave = toSave;
    }

    public AirlineController getAirlineController() {
        return airlineController;
    }

    public void setAirlineController(AirlineController airlineController) {
        this.airlineController = airlineController;
    }
}