package gui.modelsFX;

import api.PassengerDao;
import dao.PassengerDaoImpl;
import entity.Passenger;
import exceptions.PassengerAlreadyExist;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import services.PassengerService;

import java.util.List;

public class PassengerModel {

    private ObservableList<PassengerFX> passengerFXObservableList = FXCollections.observableArrayList();


    public void addNewPassenger(String name, String surname, String pesel) throws PassengerAlreadyExist {
        Passenger passenger = new Passenger(name,surname,pesel);
        PassengerService passengerService = new PassengerService();
        passengerService.savePassenger(passenger);
        passengerFXObservableList.clear();


    }

    public void init(){
        PassengerService passengerService = new PassengerService();
        List<Passenger> passengers =  passengerService.getAllPassenger();
        this.passengerFXObservableList.clear();
        passengers.forEach(passenger -> {
            PassengerFX passengerFX = new PassengerFX();
            passengerFX.setId(passenger.getId());
            passengerFX.setName(passenger.getName());
            passengerFX.setSurname(passenger.getSurname());
            passengerFX.setPesel(passenger.getPesel());
            this.passengerFXObservableList.add(passengerFX);
        });
    }

    public ObservableList<PassengerFX> getPassengerFXObservableList() {
        return passengerFXObservableList;
    }

    public void setPassengerFXObservableList(ObservableList<PassengerFX> passengerFXObservableList) {
        this.passengerFXObservableList = passengerFXObservableList;
    }
}
