package api;

import entity.Passenger;
import exceptions.PassengerAlreadyExistException;

import java.io.Serializable;
import java.util.List;

public interface PassengerDao extends Serializable {

    void savePassenger(Passenger passenger) throws PassengerAlreadyExistException;

    void removePassengerById(Long id);

    void updatePassenger(Passenger passenger) throws PassengerAlreadyExistException;

    Passenger getTickets(Passenger passenger);

    void removePassengerByPesel(String pesel);

    Passenger getByPesel(String pesel);

    List<Passenger> getAllPassenger();

    List<Passenger> getAllPassengerBySurname(String surname);

}