package gui.controllers;

import exceptions.ApplicationException;
import exceptions.WrongPeselException;
import gui.modelsFX.AddTicketModel;
import gui.modelsFX.FlightFX;
import gui.modelsFX.LuggageFX;
import gui.modelsFX.TicketFX;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import services.PassengerService;
import services.TicketService;
import validators.PeselValidator;

import java.io.IOException;

public class AddTicketController {

    @FXML
    private TextField peselTextField;

    @FXML
    private ComboBox<FlightFX> flightComboBox;

    @FXML
    private ListView<LuggageFX> luggageListView;

    @FXML
    private Label resultLabel;

    private AddTicketModel addTicketModel;

    private TicketController ticketController;

    private boolean isToSave;

    public void init(){
        try {
            addTicketModel = new AddTicketModel();
            addTicketModel.init();
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
        initFields();
        setListener();
    }

    public void init(TicketFX ticketFX) {
        try {
            addTicketModel = new AddTicketModel();
            addTicketModel.init();
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
        this.addTicketModel.setTicketFXObjectProperty(ticketFX);
        initFields();
        initBindings();
        setListener();
    }

    private void setListener() {
        this.luggageListView.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode()== KeyCode.DELETE){
                LuggageFX luggageFX = luggageListView.getSelectionModel().getSelectedItem();
                addTicketModel.getLuggageFXObservableList().remove(luggageFX);
            }
        });
        this.peselTextField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if(!isFieldNumeric(newValue)){
                resultLabel.setText("Pesel has to be number");
                resultLabel.setTextFill(Color.RED);
            }
            if(isFieldNumeric(newValue)){
                resultLabel.setText("");
            }
        });
    }

    public void initFields(){
        this.flightComboBox.setItems(addTicketModel.getFlightFXObservableList());
        this.luggageListView.setItems(this.addTicketModel.getLuggageFXObservableList());
    }

    public void initBindings() {
        this.luggageListView.setItems(this.addTicketModel.getTicketFXObjectProperty().getLuggageFXES());
        this.flightComboBox.valueProperty().bindBidirectional(this.addTicketModel.getTicketFXObjectProperty().flightFXProperty());
        this.peselTextField.textProperty().bindBidirectional(this.addTicketModel.getTicketFXObjectProperty().getPassangerFX().peselProperty());
    }

    private boolean isFieldNumeric(String text){
        return text != null && text.matches("[0-9]*\\,?[0-9]+");

    }

    public void saveTicket() {
        try {
            PeselValidator peselValidator = new PeselValidator();
            peselValidator.validate(peselTextField.getText());
            this.addTicketModel.setPesel(this.peselTextField.getText());
            if(isToSave()) {
                this.addTicketModel.saveTicket();
                ticketController.getTicketModel().init();
            }
            else{
                this.addTicketModel.updateTicket();
                ticketController.getTicketModel().init();
            }
            this.resultLabel.setText("Save");
            this.resultLabel.setTextFill(Color.GREEN);
        }catch (WrongPeselException e) {
            this.resultLabel.setText(e.getMessage());
            this.resultLabel.setTextFill(Color.RED);
        }
        catch (ApplicationException e) {
            e.printStackTrace();
        }
    }

    public void flightComboBoxOnAction() {
        this.addTicketModel.getTicketFXObjectProperty().setFlightFX((flightComboBox.getSelectionModel().getSelectedItem()));
    }

    public void addLuggage() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addLuggage.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
            AddLuggageController addLuggageController = loader.getController();
            addLuggageController.init(this);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public AddTicketModel getAddTicketModel() {
        return addTicketModel;
    }

    public void setAddTicketModel(AddTicketModel addTicketModel) {
        this.addTicketModel = addTicketModel;
    }

    public TicketController getTicketController() {
        return ticketController;
    }

    public void setTicketController(TicketController ticketController) {
        this.ticketController = ticketController;
    }

    public boolean isToSave() {
        return isToSave;
    }

    public void setToSave(boolean toSave) {
        isToSave = toSave;
    }
}