package api;

import entity.Flight;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public interface FlightDao extends Serializable {
    void saveFlight(Flight flight);

    void removeFlight(Flight flight);

    List<Flight> getAllFlight();

    List<Flight> getAllFlightByDate(LocalDateTime date);

    void updateFlight(Flight flight);
}
