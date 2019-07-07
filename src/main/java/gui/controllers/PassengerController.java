package gui.controllers;

import gui.modelsFX.PassengerModel;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import gui.modelsFX.PassengerFX;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

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
    private TableColumn<PassengerFX, PassengerFX> deleteColumn;
    @FXML
    private TableColumn<PassengerFX, PassengerFX> editColumn;



    @FXML
    public void initialize(){
        this.passengerModel = new PassengerModel();
        initBindings();
    }

    private void initBindings() {


        this.passangerTableView.setItems(this.passengerModel.getPassengerFXObservableList());
        this.idColumn.setCellValueFactory(cellData->cellData.getValue().idProperty().asObject());
        this.nameColumn.setCellValueFactory(cellData->cellData.getValue().nameProperty());
        this.surnameColumn.setCellValueFactory(cellData->cellData.getValue().surnameProperty());
        this.peselColumn.setCellValueFactory(cellData->cellData.getValue().peselProperty());
        this.deleteColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        this.editColumn.setCellValueFactory((cellData -> new SimpleObjectProperty<>(cellData.getValue())));
        passengerModel.init();
        this.clearTextFields();
        this.deleteColumn.setCellFactory(param->new TableCell<PassengerFX, PassengerFX>(){

            Button button = createButton("/images/remove_button_icon.png");
            @Override
            protected void updateItem(PassengerFX passengerFX, boolean b) {
                super.updateItem(passengerFX, b);
                if(b){
                    setGraphic(null);
                    return;
                }
                if(!b) {
                    setGraphic(button);
                    button.setOnAction(event -> {
                        passengerModel.removePassenger(passengerFX);
                    });
                }
            }
        });

        this.editColumn.setCellFactory(param->new TableCell<PassengerFX, PassengerFX>(){

            Button button = createButton("/images/edit_button_icon.png");
            @Override
            protected void updateItem(PassengerFX passengerFX, boolean b) {
                super.updateItem(passengerFX, b);
                if(b){
                    setGraphic(null);
                    return;
                }
                if(!b) {
                    setGraphic(button);
                    button.setOnAction(event -> {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addPassenger.fxml"));
                        Scene scene = null;
                        try {
                            scene = new Scene(loader.load());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        AddPassengerController addPassengerController = loader.getController();
                        addPassengerController.setAddOrUpdateChoice(1);
                        addPassengerController.getPassengerModel().setPassengerFXObjectProperty(passengerFX);
                        addPassengerController.binds();
                        addPassengerController.setPassengerModelFromPassengerController(passengerModel);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.showAndWait();

                    });
                }
            }
        });
        this.passangerTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, nextValue) -> {
            this.passengerModel.setPassengerFXObjectProperty(nextValue);
        });

    }
//    @FXML
//    public void removePassenger(PassengerFX passengerFX){
//        this.passengerModel.removePassenger(passengerFX);
//    }

    @FXML
    public void addNewPassenger() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addPassenger.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        AddPassengerController addPassengerController = loader.getController();
        addPassengerController.setPassengerModelFromPassengerController(passengerModel);
        addPassengerController.setAddOrUpdateChoice(0);
        addPassengerController.clearFields();
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
    public void clearTextFields(){
        nameTextField.clear();
        surnameTextField.clear();
        peselTextField.clear();
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

    public void filterPassangers() {
        passengerModel.setName(nameTextField.getText());
        passengerModel.setSurname(surnameTextField.getText());
        passengerModel.setPesel(peselTextField.getText());
        passengerModel.filterPassangersList();
    }

    public void restartSearchFilters() {
        this.passengerModel.setName("");
        this.passengerModel.setSurname("");
        this.passengerModel.setPesel("");
        clearTextFields();
        passengerModel.init();
    }
}
