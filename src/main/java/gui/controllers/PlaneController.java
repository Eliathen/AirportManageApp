package gui.controllers;

import exceptions.ApplicationException;
import gui.modelsFX.PlaneFX;
import gui.modelsFX.PlaneModel;
import gui.utils.DialogsUtils;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;


public class PlaneController {

    @FXML
    private TextField modelTextField ;

    @FXML
    private TextField registrationTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField capacityTextField;

    @FXML
    private TextField weightTextField;

    @FXML
    private Label resultLabel;

    private PlaneModel planeModel;

    private AirlineController airlineController;

    private boolean isToSave;

    StringConverter<? extends Number> converter =  new IntegerStringConverter();

    public void init() {
        planeModel = new PlaneModel();
        this.bindingsFields();
        this.setListeners();
    }

    public void init(PlaneFX planeFX){
        planeModel = new PlaneModel();
        planeModel.setPlaneFXObjectProperty(planeFX);
        this.bindingsFields();
        this.setListeners();

    }

    public void setListeners(){
        this.modelTextField.textProperty().addListener((Observable, oldValue, newValue)->{
            if(!isFieldNumeric(newValue)){
                resultLabel.setText("Field model number has to be number");
                resultLabel.setTextFill(Color.RED);
            }
            if(isFieldNumeric(newValue)){
                resultLabel.setText("");
            }
        });
        this.capacityTextField.textProperty().addListener((Observable, oldValue, newValue)->{
            if(!isFieldNumeric(newValue)){
                resultLabel.setText("Field capacity has to be number");
                resultLabel.setTextFill(Color.RED);
            }
            if(isFieldNumeric(newValue)){
                resultLabel.setText("");
            }
        });
        this.weightTextField.textProperty().addListener((Observable, oldValue, newValue)->{
            if(!isFieldNumeric(newValue)){
                resultLabel.setText("Field weight has to be number");
                resultLabel.setTextFill(Color.RED);
            }
            if(isFieldNumeric(newValue)){
                resultLabel.setText("");
            }
        });

    }

    public void bindingsFields(){
            Bindings.bindBidirectional(modelTextField.textProperty(), planeModel.getPlaneFXObjectProperty().modelNumberProperty(), (StringConverter<Number>)converter);
            this.registrationTextField.textProperty().bindBidirectional(this.planeModel.getPlaneFXObjectProperty().registrationNumberProperty());
            this.nameTextField.textProperty().bindBidirectional(this.planeModel.getPlaneFXObjectProperty().nameProperty());
            Bindings.bindBidirectional(capacityTextField.textProperty(), planeModel.getPlaneFXObjectProperty().capacityProperty(), (StringConverter<Number>)converter);
            Bindings.bindBidirectional(weightTextField.textProperty(), planeModel.getPlaneFXObjectProperty().weightProperty(), (StringConverter<Number>)converter);
    }

    private boolean isFieldNumeric(String text){
        return text != null && text.matches("[0-9]*\\,?[0-9]+");

    }

    private boolean isAllTextFieldNotEmpty(){
        if(modelTextField != null && modelTextField.getText().trim().isEmpty()){
            return false;
        }
        if(registrationTextField !=null && registrationTextField.getText()==null){
            return false;
        }
        if(nameTextField !=null && nameTextField.getText()==null){
            return false;
        }
        if(capacityTextField != null && capacityTextField.getText().trim().isEmpty()){
            return false;
        }
        if(weightTextField !=null && weightTextField.getText().trim().isEmpty()){
            return false;
        }
        return true;
    }

    public void savePlane()  {
        if(this.isAllTextFieldNotEmpty()) {
            try {
                if (isToSave) {
                    planeModel.savePlane();
                } else {
                    planeModel.updatePlane();
                }
            }catch (ApplicationException e){
                DialogsUtils.errorDialog(e.getMessage());
            }
        }
        else{
            resultLabel.setText("Empty fields");
            resultLabel.setTextFill(Color.RED);
        }
        airlineController.initialize();
    }

    public PlaneModel getPlaneModel() {
        return planeModel;
    }

    public void setPlaneModel(PlaneModel planeModel) {
        this.planeModel = planeModel;
    }

    public AirlineController getAirlineController() {
        return airlineController;
    }

    public void setAirlineController(AirlineController airlineController) {
        this.airlineController = airlineController;
    }

    public TextField getModelTextField() {
        return modelTextField;
    }

    public void setModelTextField(TextField modelTextField) {
        this.modelTextField = modelTextField;
    }

    public TextField getRegistrationTextField() {
        return registrationTextField;
    }

    public void setRegistrationTextField(TextField registrationTextField) {
        this.registrationTextField = registrationTextField;
    }

    public TextField getNameTextField() {
        return nameTextField;
    }

    public void setNameTextField(TextField nameTextField) {
        this.nameTextField = nameTextField;
    }

    public TextField getCapacityTextField() {
        return capacityTextField;
    }

    public void setCapacityTextField(TextField capacityTextField) {
        this.capacityTextField = capacityTextField;
    }

    public TextField getWeightTextField() {
        return weightTextField;
    }

    public void setWeightTextField(TextField weightTextField) {
        this.weightTextField = weightTextField;
    }

    public boolean isToSave() {
        return isToSave;
    }

    public void setToSave(boolean toSave) {
        isToSave = toSave;
    }
}