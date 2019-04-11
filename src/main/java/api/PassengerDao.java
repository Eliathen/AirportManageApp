package api;

import entity.Passenger;

import java.util.List;

public interface PassengerDao {

    void savePassenger(Passenger passenger);

    void removePassengerById(Long id);

    List<Passenger> getAllPassenger();
    List<Passenger> getAllPassengerBySurname(String surname);

    void updateFlight(Passenger passenger);
}