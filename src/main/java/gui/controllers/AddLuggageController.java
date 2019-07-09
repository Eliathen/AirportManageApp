package gui.controllers;

import entity.Luggage;
import exceptions.ApplicationException;
import gui.modelsFX.AddLuggageModel;
import gui.modelsFX.LuggageFX;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.util.StringConverter;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import services.LuggageService;

import javax.naming.Binding;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AddLuggageController {

    @FXML
    private TextField codeTextField;

    @FXML
    private TextField weightTextField;

    @FXML
    private TextField heightTextField;

    @FXML
    private Label resultLabel;

    private AddTicketController addTicketController;

    private AddLuggageModel addLuggageModel;

    private StringConverter<? extends Number> converter =  new FloatStringConverter();

    private StringConverter<? extends Number> converterInteger = new IntegerStringConverter();

    public void init(AddTicketController addTicketController){
        addLuggageModel = new AddLuggageModel();
        this.addTicketController = addTicketController;
        initBindigs();
        setListeners();
    }

    private void setListeners() {
        this.weightTextField.textProperty().addListener((observableValue, s, t1) -> {
            if(!isFieldNumeric(t1)){
                resultLabel.setText("Field weight has to be number");
                resultLabel.setTextFill(Color.RED);
            }
            if(isFieldNumeric(t1)){
                resultLabel.setText("");
            }
        });
        this.heightTextField.textProperty().addListener((observableValue, s, t1) -> {
            if(!isFieldNumeric(t1)){
                resultLabel.setText("Field height has to be number");
                resultLabel.setTextFill(Color.RED);
            }
            if(isFieldNumeric(t1)){
                resultLabel.setText("");
            }
        });

    }

    private boolean isFieldNumeric(String text){
        return text != null && text.matches("[0-9]*\\,?[0-9]+");

    }

    private void initBindigs() {
        this.codeTextField.textProperty().bindBidirectional(this.addLuggageModel.getLuggageFXObjectProperty().codeProperty());
        Bindings.bindBidirectional(weightTextField.textProperty(), this.addLuggageModel.getLuggageFXObjectProperty().weightProperty(), (StringConverter<Number>)converter);
        Bindings.bindBidirectional(heightTextField.textProperty(), this.addLuggageModel.getLuggageFXObjectProperty().heightProperty(), (StringConverter<Number>)converterInteger);
    }

    private boolean isAllTextFieldNotEmpty(){
        if(codeTextField!=null && codeTextField.getText()==null){
            return false;
        }
        else if(weightTextField!=null && codeTextField.getText().trim().isEmpty()){
            return false;
        }
        else if(heightTextField!=null && codeTextField.getText().trim().isEmpty()){
            return false;
        }
        return true;
    }

    public void saveLuggage() throws ApplicationException {
        if(isAllTextFieldNotEmpty()) {
            LuggageService luggageService = new LuggageService();
            Luggage luggage = luggageService.getLuggageByCode(this.addLuggageModel.getLuggageFXObjectProperty().getCode());
            List<LuggageFX> list = this.addTicketController.getAddTicketModel().getLuggageFXObservableList().stream().filter(luggageFX -> luggageFX.getCode().equals(this.addLuggageModel.getLuggageFXObjectProperty().getCode())).collect(Collectors.toList());
            if(luggage.getCode()==null && list.isEmpty()) {
                this.addTicketController.getAddTicketModel().addToLuggageFXObservableList(this.addLuggageModel.getLuggageFXObjectProperty());
                resultLabel.setText("Save");
                resultLabel.setTextFill(Color.GREEN);
            }else{
                resultLabel.setText("Code exist in DB");
                resultLabel.setTextFill(Color.RED);
            }
        }
    }
}