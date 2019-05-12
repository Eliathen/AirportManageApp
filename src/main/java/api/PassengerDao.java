package api;

import entity.Passenger;

import java.io.Serializable;
import java.util.List;

public interface PassengerDao extends Serializable {

    void savePassenger(Passenger passenger);

    void removePassengerById(Long id);

    void updatePassenger(Passenger passenger);

    Passenger getTickets(Passenger passenger);

    Passenger getByPesel(String pesel);

    List<Passenger> getAllPassenger();

    List<Passenger> getAllPassengerBySurname(String surname);

}