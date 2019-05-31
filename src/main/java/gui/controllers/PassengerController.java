package gui.controllers;

import exceptions.PassengerAlreadyExist;
import gui.modelsFX.PassengerModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import gui.modelsFX.PassengerFX;

public class PassengerController {

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField surnameTextField;
    @FXML
    private TextField peselTextField;

    @FXML
    private Label resultLabel;
    @FXML
    private Button addButton;

    private PassengerModel passengerModel;

    @FXML
    private TableView<PassengerFX> passangerTableView;

    @FXML
    private TableColumn<PassengerFX, Long> idColumn;

    @FXML
    private TableColumn<PassengerFX, String> nameColumn;
    @FXML
    private TableColumn<PassengerFX, String> surnameColumn;
    @FXML
    private TableColumn<PassengerFX, String> peselColumn;
    @FXML
    private MenuItem deleteMenuItem;


    @FXML
    public void initialize(){
        this.passengerModel = new PassengerModel();
        initBindings();
    }

    private void initBindings() {
        addButton.disableProperty().bind(nameTextField.textProperty().isEmpty());
        addButton.disableProperty().bind(surnameTextField.textProperty().isEmpty());
        addButton.disableProperty().bind(peselTextField.textProperty().isEmpty());
        deleteMenuItem.disableProperty().bind(passangerTableView.getSelectionModel().selectedItemProperty().isNull());
        passengerModel.init();
        this.passangerTableView.setItems(this.passengerModel.getPassengerFXObservableList());
        this.idColumn.setCellValueFactory(cellData->cellData.getValue().idProperty().asObject());
        this.nameColumn.setCellValueFactory(cellData->cellData.getValue().nameProperty());
        this.surnameColumn.setCellValueFactory(cellData->cellData.getValue().surnameProperty());
        this.peselColumn.setCellValueFactory(cellData->cellData.getValue().peselProperty());

        this.passangerTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, nextValue) -> {
            this.passengerModel.setPassengerFXObjectProperty(nextValue);
        });

    }
    @FXML
    public void removePassenger(){
        this.passengerModel.removePassenger();
    }

    @FXML
    public void addNewPassenger() {
        try {
            passengerModel.addNewPassenger(nameTextField.getText(), surnameTextField.getText(), peselTextField.getText());
            resultLabel.setText("Dodano do bazy");
            resultLabel.setTextFill(Color.GREEN);
            clearTextFields();
            passengerModel.init();
        }catch(PassengerAlreadyExist e){
            resultLabel.setText(e.getMessage());
            resultLabel.setTextFill(Color.DARKRED);
        }
    }
    public void clearTextFields(){
        nameTextField.clear();
        surnameTextField.clear();
        peselTextField.clear();
    }
}
