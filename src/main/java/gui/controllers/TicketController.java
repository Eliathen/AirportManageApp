package gui.controllers;

import exceptions.ApplicationException;
import gui.modelsFX.*;
import gui.utils.DialogsUtils;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class TicketController {

    @FXML
    private TextField peselTextField;

    @FXML
    private TableView<TicketFX> ticketTableView;

    @FXML
    private TableColumn<TicketFX, Long> idColumn;

    @FXML
    private TableColumn<TicketFX, String> nameColumn;

    @FXML
    private TableColumn<TicketFX, String> surnameColumn;

    @FXML
    private TableColumn<TicketFX, String> peselColumn;

    @FXML
    private TableColumn<TicketFX, FlightFX> flightColumn;

    @FXML
    private TableColumn<TicketFX, TicketFX> deleteColumn;

    @FXML
    private TableColumn<TicketFX, TicketFX> editColumn;

    private TicketModel ticketModel;

    public void initialize() {
        ticketModel = new TicketModel();
        init();
    }

    private void init(){
        ticketTableView.setItems(this.ticketModel.getTicketObservableList());
        this.idColumn.setCellValueFactory(cellData->cellData.getValue().idProperty().asObject());
        this.nameColumn.setCellValueFactory(cellData->cellData.getValue().getPassengerFX().nameProperty());
        this.surnameColumn.setCellValueFactory(cellData->cellData.getValue().getPassengerFX().surnameProperty());
        this.peselColumn.setCellValueFactory(cellData->cellData.getValue().getPassengerFX().peselProperty());
        this.flightColumn.setCellValueFactory(cellData-> cellData.getValue().flightFXProperty());
        this.deleteColumn.setCellValueFactory(cellData->new SimpleObjectProperty<>(cellData.getValue()));
        this.editColumn.setCellValueFactory(cellData->new SimpleObjectProperty<>(cellData.getValue()));

        try{
            ticketModel.init();
            this.deleteColumn.setCellFactory(param->new TableCell<TicketFX, TicketFX>(){
                Button button = createButton("/images/remove_button_icon.png");

                @Override
                protected void updateItem(TicketFX ticketFX, boolean b){
                    super.updateItem(ticketFX, b);
                    if (b) {
                        setGraphic(null);
                        return;
                    }
                    if (!b) {
                        setGraphic(button);
                        button.setOnAction(event -> {
                            try {
                                ticketModel.removeTicket(ticketFX);
                            } catch (ApplicationException e) {
                                DialogsUtils.errorDialog(e.getMessage());
                            }
                        });
                    }
                }
            });
            this.editColumn.setCellFactory(param -> new TableCell<TicketFX, TicketFX>() {

                Button button = createButton("/images/edit_button_icon.png");

                @Override
                protected void updateItem(TicketFX ticketFX, boolean b) {
                    super.updateItem(ticketFX, b);
                    if (b) {
                        setGraphic(null);
                        return;
                    }
                    if (!b) {
                        setGraphic(button);
                        button.setOnAction(event -> {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addTicket.fxml"));
                            Scene scene = null;
                            try {
                                scene = new Scene(loader.load());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            AddTicketController addTicketController = loader.getController();
                            addTicketController.setTicketController(TicketController.this);
                            addTicketController.init(ticketFX);
                            addTicketController.setToSave(false);
                            addTicketController.getAddTicketModel().setLuggageFXObservableList(ticketFX.getLuggageFXES());
                            Stage stage = new Stage();
                            stage.setScene(scene);
                            stage.initModality(Modality.APPLICATION_MODAL);
                            stage.showAndWait();

                        });
                    }
                }
            });


        }catch(ApplicationException e){
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

    public void addTicket() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addTicket.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        AddTicketController addTicketController = loader.getController();
        addTicketController.setTicketController(this);
        addTicketController.setToSave(true);
        addTicketController.init();
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public TicketModel getTicketModel() {
        return ticketModel;
    }

    public void setTicketModel( ) {
        this.ticketModel = ticketModel;
    }

    public void filterTicketsList( ) {
        this.ticketModel.setPesel(this.peselTextField.getText());
        this.ticketModel.filterTicketList();
    }

    public void resetFilters( ) {
        this.peselTextField.setText("");
        try {
            this.ticketModel.init();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
    }
}