package gui.controllers;

import exceptions.ApplicationException;
import gui.modelsFX.*;
import gui.utils.DialogsUtils;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AddFlightController {

    @FXML
    private ComboBox<AirportFX> initialAirportComboBox;

    @FXML
    private ComboBox<AirportFX> finalAirportComboBox;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Spinner<Integer> hourPicker;

    @FXML
    private Spinner<Integer> minutePicker;

    @FXML
    private ComboBox<AirlineFX> airlineComboBox;

    @FXML
    private ComboBox<PlaneFX> planeComboBox;

    @FXML
    private ListView<EmployeeFX> allEmployeeListView;

    @FXML
    private ListView<EmployeeFX> choosenEmployeeListView;

    private FlightModel flightModelFromFlightController;

    private FlightModel flightModel;

    private boolean isToSave;

    public void init(){
        flightModel = new FlightModel();
        SpinnerValueFactory<Integer> hourSpinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,23, 12);
        hourPicker.setValueFactory(hourSpinnerValueFactory);
        SpinnerValueFactory<Integer> minuteSpinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,59, 30);
        minutePicker.setValueFactory(minuteSpinnerValueFactory);
        this.initialAirportComboBox.setItems(this.flightModel.getInitialAiportFXObservableList());
        this.finalAirportComboBox.setItems(this.flightModel.getFinalAiportFXObservableList());
        this.airlineComboBox.setItems(this.flightModel.getAirlineFXObservableList());
        this.planeComboBox.setItems(this.flightModel.getPlaneFXObservableList());
        this.allEmployeeListView.setItems(this.flightModel.getAllEmployeeFXObservableList());
        this.choosenEmployeeListView.setItems(this.flightModel.getChoosenEmployeeFXObservableList());
        try {
            this.flightModel.init();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
    }

    public void init(FlightModel flightModel){
        SpinnerValueFactory<Integer> hourSpinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,23, 12);
        hourPicker.setValueFactory(hourSpinnerValueFactory);
        SpinnerValueFactory<Integer> minuteSpinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,59, 30);
        minutePicker.setValueFactory(minuteSpinnerValueFactory);

        this.flightModel = flightModel;
    }

    public void initBindings(){
        this.initialAirportComboBox.setItems(this.flightModel.getInitialAiportFXObservableList());
        this.finalAirportComboBox.setItems(this.flightModel.getFinalAiportFXObservableList());
        this.airlineComboBox.setItems(this.flightModel.getAirlineFXObservableList());
        this.planeComboBox.setItems(this.flightModel.getPlaneFXObservableList());
        this.allEmployeeListView.setItems(this.flightModel.getAllEmployeeFXObservableList());
        this.choosenEmployeeListView.setItems(this.flightModel.getChoosenEmployeeFXObservableList());
        this.initialAirportComboBox.valueProperty().bindBidirectional(this.flightModel.getFlightFXObjectProperty().initialAirportProperty());
        this.finalAirportComboBox.valueProperty().bindBidirectional(this.flightModel.getFlightFXObjectProperty().finalAirportProperty());
        this.airlineComboBox.valueProperty().bindBidirectional(this.flightModel.getFlightFXObjectProperty().airlineFXProperty());
        this.planeComboBox.valueProperty().bindBidirectional(this.flightModel.getFlightFXObjectProperty().planeFXProperty());
        this.hourPicker.getValueFactory().valueProperty().bindBidirectional(this.flightModel.getFlightFXObjectProperty().hoursProperty().asObject());
        this.minutePicker.getValueFactory().valueProperty().bindBidirectional(this.flightModel.getFlightFXObjectProperty().minutesProperty().asObject());
        this.datePicker.valueProperty().bindBidirectional(this.flightModel.localDateProperty());

    }

    public void saveFlight() {
        this.flightModel.setHours(this.hourPicker.getValue());
        this.flightModel.setMinutes(this.minutePicker.getValue());
        try {
            if (isToSave) {
                this.flightModel.saveFlight();
            } else {
                this.flightModel.updateFlight();
            }
            flightModelFromFlightController.init();
        }catch (ApplicationException e){
            DialogsUtils.errorDialog(e.getMessage());
        }
    }

    public FlightModel getFlightModelFromFlightController() {
        return flightModelFromFlightController;
    }

    public void setFlightModelFromFlightController(FlightModel flightModelFromFlightController) {
        this.flightModelFromFlightController = flightModelFromFlightController;
    }

    public boolean isToSave() {
        return isToSave;
    }

    public void setToSave(boolean toSave) {
        isToSave = toSave;
    }

    public void finalAirportComboBoxOnAction() {
        this.flightModel.setFinalAiportObjectProperty(this.finalAirportComboBox.getSelectionModel().getSelectedItem());
    }

    public void initialAirportComboBoxOnAction() {
        this.flightModel.setInitialAiportObjectProperty(this.initialAirportComboBox.getSelectionModel().getSelectedItem());
    }

    public void airlineComboBoxOnAction() {
        this.flightModel.setAirlineFXObjectProperty(this.airlineComboBox.getSelectionModel().getSelectedItem());
        try {
            this.flightModel.initPlaneList();
            this.flightModel.initEmployeeList();
        }catch (ApplicationException e){
            DialogsUtils.errorDialog(e.getMessage());
        }
    }

    public void planeComboBoxOnAction() {
        this.flightModel.setPlaneFXObjectProperty(this.planeComboBox.getSelectionModel().getSelectedItem());
    }

    public void setDate() {
        this.flightModel.setLocalDate(this.datePicker.getValue());
    }

    public void chooseEmployee() {
        EmployeeFX employeeFX = this.allEmployeeListView.getSelectionModel().getSelectedItem();
        this.flightModel.addToChoosenEmployeeObservableList(employeeFX);
        this.flightModel.removeFromAllEmployeeObservableList(employeeFX);

    }

    public void backEmployee() {
        EmployeeFX employeeFX = this.choosenEmployeeListView.getSelectionModel().getSelectedItem();
        this.flightModel.removeFromChoosenEmployeeObservableList(employeeFX);
        this.flightModel.addToAllEmployeeObservableList(employeeFX);
    }

    public FlightModel getFlightModel() {
        return flightModel;
    }

    public void setFlightModel(FlightModel flightModel) {
        this.flightModel = flightModel;
    }
}
