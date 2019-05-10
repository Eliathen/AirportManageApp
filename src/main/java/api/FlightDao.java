package api;

import entity.Airline;
import entity.Flight;
import sun.util.resources.LocaleData;

import java.io.Serializable;
import java.util.List;

public interface FlightDao extends Serializable {
    void saveFlight(Flight flight);

    void removeFlightById(Long id);

    List<Flight> getAllFlight();
    List<Flight> getAllFlightByData(LocaleData data);

    void updateFlight(Flight flight);
}
