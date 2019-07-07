package api;

import entity.Passenger;
import exceptions.PassengerAlreadyExistException;

import java.io.Serializable;
import java.util.List;

public interface PassengerDao extends Serializable {
    
    void savePassenger(Passenger passenger) throws PassengerAlreadyExistException;
    
    void updatePassenger(Passenger passenger) throws PassengerAlreadyExistException;
    
    void removePassengerByPesel(String pesel);
    
    Passenger getByPesel(String pesel);
    
    List<Passenger> getAllPassenger();
}