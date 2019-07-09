package gui.controllers;

import exceptions.ApplicationException;
import exceptions.EmployeeAlreadyExistException;
import exceptions.EmptyFieldException;
import exceptions.WrongPeselException;
import gui.modelsFX.EmployeeFX;
import gui.modelsFX.EmployeeModel;
import gui.utils.DialogsUtils;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.util.StringConverter;
import javafx.util.converter.FloatStringConverter;
import validators.PeselValidator;

import javax.naming.Binding;


public class EmployeeController {

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField surnameTextField;

    @FXML
    private TextField peselTextField;

    @FXML
    private TextField occupationTextField;

    @FXML
    private TextField salaryTextField;

    @FXML
    private Label resultLabel;

    private EmployeeModel employeeModel;

    private AirlineController airlineController;

    private boolean isToSave;

    private StringConverter<? extends Number> converter = new FloatStringConverter();

    public void init() {
        employeeModel = new EmployeeModel();
        this.bindingsFields();
        this.setListeners();

    }

    public void init(EmployeeFX employeeFX) {
        employeeModel = new EmployeeModel();
        this.employeeModel.setEmployeeFXObjectProperty(employeeFX);
        this.bindingsFields();
        this.setListeners();

    }

    public void bindingsFields() {
        this.nameTextField.textProperty().bindBidirectional(this.employeeModel.getEmployeeFXObjectProperty().nameProperty());
        this.surnameTextField.textProperty().bindBidirectional(this.employeeModel.getEmployeeFXObjectProperty().surnameProperty());
        this.peselTextField.textProperty().bindBidirectional(this.employeeModel.getEmployeeFXObjectProperty().peselProperty());
        this.occupationTextField.textProperty().bindBidirectional(this.employeeModel.getEmployeeFXObjectProperty().occupationProperty());
        Bindings.bindBidirectional(salaryTextField.textProperty(), employeeModel.getEmployeeFXObjectProperty().salaryProperty(), (StringConverter<Number>) converter);
    }

    private void setListeners() {
        this.salaryTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!isFieldNumeric(newValue)) {
                resultLabel.setText("Field salary has to be number");
                resultLabel.setTextFill(Color.RED);
            }
            if (isFieldNumeric(newValue)) {
                resultLabel.setText("");
            }
        });
        this.peselTextField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!isFieldNumeric(newValue)) {
                resultLabel.setText("Pesel has to be number");
                resultLabel.setTextFill(Color.RED);
            }
            if (isFieldNumeric(newValue)) {
                resultLabel.setText("");
            }
        });
    }

    private boolean isFieldNumeric(String text) {
        return text != null && text.matches("[0-9]*\\,?[0-9]+");

    }

    private boolean isAllTextFieldNotEmpty() {
        if (nameTextField != null && nameTextField.getText() == null) {
            return false;
        } else if (surnameTextField != null && surnameTextField.getText() == null) {
            return false;
        } else if (peselTextField != null && peselTextField.getText() == null) {
            return false;
        } else if (occupationTextField != null && occupationTextField.getText() == null) {
            return false;
        } else if (salaryTextField != null && salaryTextField.getText().trim().isEmpty()) {
            return false;
        }
        return true;
    }

    public void saveEmployee() {
        try {
            if (!this.isAllTextFieldNotEmpty()) {
                throw new EmptyFieldException("Empty Field");
            } else {
                PeselValidator peselValidator = new PeselValidator();
                peselValidator.validate(employeeModel.getEmployeeFXObjectProperty().getPesel());
                if (isToSave) {
                    employeeModel.saveEmployee();
                    resultLabel.setText("Save");
                    resultLabel.setTextFill(Color.GREEN);
                } else {
                    employeeModel.updateEmployee();
                    resultLabel.setText("Save");
                    resultLabel.setTextFill(Color.GREEN);
                }
                airlineController.initialize();
            }
        } catch (EmptyFieldException | WrongPeselException | EmployeeAlreadyExistException e) {
            resultLabel.setText(e.getMessage());
            resultLabel.setTextFill(Color.RED);
        } catch (ApplicationException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
    }

    public TextField getNameTextField() {
        return nameTextField;
    }

    public void setNameTextField(TextField nameTextField) {
        this.nameTextField = nameTextField;
    }

    public Label getResultLabel() {
        return resultLabel;
    }

    public void setResultLabel(Label resultLabel) {
        this.resultLabel = resultLabel;
    }

    public EmployeeModel getEmployeeModel() {
        return employeeModel;
    }

    public void setAirlineController(AirlineController airlineController) {
        this.airlineController = airlineController;
    }

    public void setToSave(boolean toSave) {
        isToSave = toSave;
    }
}

