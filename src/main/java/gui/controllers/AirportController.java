package gui.controllers;

import exceptions.ApplicationException;
import gui.modelsFX.AirportFX;
import gui.modelsFX.AirportModel;
import gui.modelsFX.AirportFX;
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

public class AirportController {

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField addressTextField;

    @FXML
    private TextField cityTextField;

    private AirportModel airportModel;

    @FXML
    private TableView<AirportFX> airportTableView;

    @FXML
    private TableColumn<AirportFX, Long> idColumn;

    @FXML
    private TableColumn<AirportFX, String> nameColumn;

    @FXML
    private TableColumn<AirportFX, String> addressColumn;

    @FXML
    private TableColumn<AirportFX, String> cityColumn;

    @FXML
    private TableColumn<AirportFX, AirportFX> deleteColumn;

    @FXML
    private TableColumn<AirportFX, AirportFX> editColumn;

    public void initialize(){
        airportModel = new AirportModel();
        initBindings();
    }

    private void initBindings() {
        this.airportTableView.setItems(this.airportModel.getAirportFXObservableList());
        this.idColumn.setCellValueFactory(cellData->cellData.getValue().idProperty().asObject());
        this.nameColumn.setCellValueFactory(cellData->cellData.getValue().nameProperty());
        this.addressColumn.setCellValueFactory(cellData->cellData.getValue().addressProperty());
        this.cityColumn.setCellValueFactory(cellData->cellData.getValue().cityProperty());
        this.deleteColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        this.editColumn.setCellValueFactory((cellData -> new SimpleObjectProperty<>(cellData.getValue())));
        try {
            airportModel.init();

            this.deleteColumn.setCellFactory(param -> new TableCell<AirportFX, AirportFX>() {

                Button button = createButton("/images/remove_button_icon.png");

                @Override
                protected void updateItem(AirportFX airportFX, boolean b) {
                    super.updateItem(airportFX, b);
                    if (b) {
                        setGraphic(null);
                        return;
                    }
                    if (!b) {
                        setGraphic(button);
                        button.setOnAction(event -> {
                            try {
                                airportModel.removeAirport(airportFX);
                            } catch (ApplicationException e) {
                                DialogsUtils.errorDialog(e.getMessage());
                            }
                        });
                    }
                }
            });

            this.editColumn.setCellFactory(param -> new TableCell<AirportFX, AirportFX>() {

                Button button = createButton("/images/edit_button_icon.png");

                @Override
                protected void updateItem(AirportFX airportFX, boolean b) {
                    super.updateItem(airportFX, b);
                    if (b) {
                        setGraphic(null);
                        return;
                    }
                    if (!b) {
                        setGraphic(button);
                        button.setOnAction(event -> {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addAirport.fxml"));
                            Scene scene = null;
                            try {
                                scene = new Scene(loader.load());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            AddAirportController addAirportController = loader.getController();
                            addAirportController.setAddOrUpdateChoice(1);
                            addAirportController.getAirportModel().setAirportFXObjectProperty(airportFX);
                            addAirportController.binds();
                            addAirportController.setAirportModelFromAirportController(airportModel);
                            Stage stage = new Stage();
                            stage.setScene(scene);
                            stage.initModality(Modality.APPLICATION_MODAL);
                            stage.showAndWait();

                        });
                    }
                }
            });
            this.airportTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, nextValue) -> {
                this.airportModel.setAirportFXObjectProperty(nextValue);
            });
        }catch (ApplicationException e){
            DialogsUtils.errorDialog(e.getMessage());
        }
    }

    public Button createButton(String path){
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
            button.setText("DELETE");
            return button;
        }
    }

    public void addNewAirport() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addAirport.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        AddAirportController addAirportController = loader.getController();
        addAirportController.setAirportModelFromAirportController(airportModel);
        addAirportController.setAddOrUpdateChoice(0);
        addAirportController.clearFields();
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public void clearTextFields(){
        nameTextField.clear();
        addressTextField.clear();
        cityTextField.clear();
    }

    public void restartSearchFilters() {
        this.airportModel.setName("");
        this.airportModel.setAddress("");
        this.airportModel.setCity("");
        clearTextFields();
        try {
            airportModel.init();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }

    }

    public void filterAirports() {
        airportModel.setName(nameTextField.getText());
        airportModel.setAddress(addressTextField.getText());
        airportModel.setCity(cityTextField.getText());
        airportModel.filterAirportList();
    }
}