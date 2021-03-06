package gui.modelsFX;

import entity.Passenger;
import exceptions.ApplicationException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import services.PassengerService;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PassengerModel {

    private ObjectProperty<PassengerFX> passengerFXObjectProperty = new SimpleObjectProperty<>(new PassengerFX());

    private ObservableList<PassengerFX> passengerFXObservableList = FXCollections.observableArrayList();

    private List<PassengerFX> passengerFXList = new ArrayList<>();

    private String name;

    private String surname;

    private String  pesel;

    public PassengerFX getPassengerFXObjectProperty() {
        return passengerFXObjectProperty.get();
    }

    public void setPassengerFXObjectProperty(PassengerFX passengerFXObjectProperty) {
        this.passengerFXObjectProperty.set(passengerFXObjectProperty);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public void removePassenger(PassengerFX passengerFX) throws ApplicationException {
        PassengerService passengerService = new PassengerService();
        passengerService.removePassengerByPesel(passengerFX.getPesel());
        this.init();
    }

    public void init() throws ApplicationException {
        PassengerService passengerService = new PassengerService();
        List<Passenger> passengers =  passengerService.getAllPassenger();
        this.passengerFXList.clear();
        passengers.forEach(passenger -> {
            PassengerFX passengerFX = new PassengerFX();
            passengerFX.setId(passenger.getId());
            passengerFX.setName(passenger.getName());
            passengerFX.setSurname(passenger.getSurname());
            passengerFX.setPesel(passenger.getPesel());
            this.passengerFXList.add(passengerFX);
        });
        this.passengerFXObservableList.setAll(passengerFXList);
    }

    public void filterPassengersList(){
        if(!getName().isEmpty() && !getSurname().isEmpty() && !getPesel().isEmpty()){
            filterPredicate(predicateName().and(predicateSurname()).and(predicatePesel()));
        }
        else if(!getName().isEmpty() && !getSurname().isEmpty()){
            filterPredicate(predicateName().and(predicateSurname()));
        }
        else if(!getName().isEmpty() && !getPesel().isEmpty()){
            filterPredicate(predicateName().and(predicatePesel()));
        }
        else if(!getSurname().isEmpty() && !getPesel().isEmpty()){
            filterPredicate(predicateSurname().and(predicatePesel()));
        }
        else if(!getName().isEmpty()){
            filterPredicate(predicateName());
        }
        else if(!getSurname().isEmpty()){
            filterPredicate(predicateSurname());
        }
        else if(!getPesel().isEmpty()){
            filterPredicate(predicatePesel());
        }
        else{
            this.passengerFXObservableList.setAll(this.passengerFXList);
        }
    }

    private Predicate<PassengerFX> predicateName() {
        return passengerFX -> passengerFX.getName().contains(getName());
    }

    private Predicate<PassengerFX> predicateSurname(){
        return passengerFX ->passengerFX.getSurname().contains(getSurname());
    }

    private Predicate<PassengerFX> predicatePesel(){
        return passengerFX ->passengerFX.getPesel().contains(getPesel());
    }

    private void filterPredicate(Predicate<PassengerFX> predicate){
        List<PassengerFX> newList = passengerFXList.stream().filter(predicate).collect(Collectors.toList());
        this.passengerFXObservableList.setAll(newList);
    }

    public ObservableList<PassengerFX> getPassengerFXObservableList() {
        return passengerFXObservableList;
    }

    public void setPassengerFXObservableList(ObservableList<PassengerFX> passengerFXObservableList) {
        this.passengerFXObservableList = passengerFXObservableList;
    }
}
