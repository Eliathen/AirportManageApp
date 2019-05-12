package api;

import entity.Airline;
import entity.Flight;
import sun.util.resources.LocaleData;

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
