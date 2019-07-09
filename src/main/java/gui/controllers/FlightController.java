package gui.controllers;

import exceptions.ApplicationException;
import gui.modelsFX.AirportFX;
import gui.modelsFX.FlightFX;
import gui.modelsFX.FlightModel;
import gui.utils.DialogsUtils;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;

public class FlightController {

    @FXML
    private ComboBox<AirportFX> initialAirportComboBox;

    @FXML
    private ComboBox<AirportFX> finalAirportComboBox;

    @FXML
    private DatePicker dateDatePicker;

    @FXML
    private Spinner<Integer> hourPicker;

    @FXML
    private Spinner<Integer> minutePicker;

    @FXML
    private TableView<FlightFX> flightTableView;

    @FXML
    private TableColumn<FlightFX, Long> idColumn;

    @FXML
    private TableColumn<FlightFX, AirportFX> initialAirportColumn;

    @FXML
    private TableColumn<FlightFX, AirportFX> finalAirportColumn;

    @FXML
    private TableColumn<FlightFX, LocalDateTime> dateColumn;

    @FXML
    private TableColumn<FlightFX, FlightFX> deleteColumn;

    @FXML
    private TableColumn<FlightFX, FlightFX> editColumn;

    private FlightModel flightModel;

    public void initialize(){
        flightModel = new FlightModel();
        try {
            this.flightModel.init();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
        SpinnerValueFactory<Integer> hourSpinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,23, 12);
        hourPicker.setValueFactory(hourSpinnerValueFactory);
        SpinnerValueFactory<Integer> minuteSpinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,59, 30);
        minutePicker.setValueFactory(minuteSpinnerValueFactory);
        this.initialAirportComboBox.setItems(this.flightModel.getInitialAiportFXObservableList());
        this.finalAirportComboBox.setItems(this.flightModel.getFinalAiportFXObservableList());
        this.initBindings();

    }

    private void initBindings(){
        this.flightTableView.setItems(this.flightModel.getFlightFXObservableList());
        this.idColumn.setCellValueFactory(cellData->cellData.getValue().idProperty().asObject());
        this.initialAirportColumn.setCellValueFactory(cellData->cellData.getValue().initialAirportProperty());
        this.finalAirportColumn.setCellValueFactory(cellData->cellData.getValue().finalAirportProperty());
        this.dateColumn.setCellValueFactory(cellData->cellData.getValue().dateTimeProperty());
        this.deleteColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        this.editColumn.setCellValueFactory((cellData -> new SimpleObjectProperty<>(cellData.getValue())));
        try {
            flightModel.init();
            this.deleteColumn.setCellFactory(param -> new TableCell<FlightFX, FlightFX>() {

                Button button = createButton("/images/remove_button_icon.png");

                @Override
                protected void updateItem(FlightFX flightFX, boolean b) {
                    super.updateItem(flightFX, b);
                    if (b) {
                        setGraphic(null);
                        return;
                    }
                    if (!b) {
                        setGraphic(button);
                        button.setOnAction(event -> {
                            try {
                                flightModel.removeFlight(flightFX);
                            } catch (ApplicationException e) {
                                DialogsUtils.errorDialog(e.getMessage());
                            }
                        });
                    }
                }
            });
            this.editColumn.setCellFactory(param -> new TableCell<FlightFX, FlightFX>() {

                Button button = createButton("/images/edit_button_icon.png");

                @Override
                protected void updateItem(FlightFX flightFX, boolean b) {
                    super.updateItem(flightFX, b);
                    if (b) {
                        setGraphic(null);
                        return;
                    }
                    if (!b) {
                        setGraphic(button);
                        button.setOnAction(event -> {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addFlight.fxml"));
                            Scene scene = null;
                            try {
                                scene = new Scene(loader.load());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            AddFlightController addFlightController = loader.getController();
                            addFlightController.setToSave(false);
                            addFlightController.init(flightModel);
                            FlightFX flightFX1 = flightModel.loadAirline(flightFX);
                            addFlightController.getFlightModel().setFlightFXObjectProperty(flightFX1);
                            try {
                                flightFX1 = flightModel.loadEmployees(flightFX1);
                            } catch (ApplicationException e) {
                                DialogsUtils.errorDialog(e.getMessage());
                            }
                            addFlightController.getFlightModel().getFlightFXObjectProperty().setHours(flightFX1.getDateTime().getHour());
                            addFlightController.getFlightModel().getFlightFXObjectProperty().setMinutes(flightFX1.getDateTime().getMinute());
                            addFlightController.getFlightModel().setLocalDate(flightFX1.getDateTime().toLocalDate());
                            try {
                                addFlightController.getFlightModel().setEmployeeObservableList(flightFX.employeesProperty());
                            } catch (ApplicationException e) {
                                DialogsUtils.errorDialog(e.getMessage());
                            }
                            addFlightController.initBindings();
                            addFlightController.setFlightModelFromFlightController(flightModel);
                            Stage stage = new Stage();
                            stage.setScene(scene);
                            stage.initModality(Modality.APPLICATION_MODAL);
                            stage.showAndWait();

                        });
                    }
                }
            });
        }catch (ApplicationException e){
            DialogsUtils.errorDialog(e.getMessage());
        }

    }

    private Button createButton(String path){
        try {
            Button button = new Button();
            Image image = new Image(this.getClass().getResource(path).toString());
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(15);
            imageView.setFitWidth(15);
            button.setGraphic(imageView);
            return button;
        }catch(NullPointerException e){
            Button button = new Button();
            return button;
        }
    }

    public void filterFlights() {
        this.flightModel.setInitialAiportObjectProperty(this.initialAirportComboBox.getValue());
        this.flightModel.setFinalAiportObjectProperty(this.finalAirportComboBox.getValue());
        this.flightModel.setLocalDate(dateDatePicker.getValue());
        this.flightModel.setHours(this.hourPicker.getValue());
        this.flightModel.setMinutes(this.minutePicker.getValue());
        try {
            this.flightModel.filterFlightList();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }

    }

    public void addNewFlight() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addFlight.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        AddFlightController addFlightController = loader.getController();
        addFlightController.init();
        addFlightController.setFlightModelFromFlightController(flightModel);
        addFlightController.setToSave(true);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public FlightModel getFlightModel() {
        return flightModel;
    }

    public void setFlightModel(FlightModel flightModel) {
        this.flightModel = flightModel;
    }

    public void resetFilters() {
        this.flightModel.setLocalDate(null);
        this.flightModel.setMinutes(30);
        this.flightModel.setHours(12);
        this.flightModel.setFinalAiportObjectProperty(null);
        this.flightModel.setInitialAiportObjectProperty(null);
        this.dateDatePicker.setValue(null);
        this.hourPicker.getValueFactory().setValue(12);
        this.minutePicker.getValueFactory().setValue(30);
        try {
            this.flightModel.init();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
    }
}