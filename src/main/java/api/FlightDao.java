package api;

import entity.Airline;
import entity.Flight;
import sun.util.resources.LocaleData;

import java.util.List;

public interface FlightDao {
    void saveFlight(Flight flight);

    void removeFlightById(Long id);

    List<Flight> getAllFlight();
    List<Flight> getAllFlightByData(LocaleData data);

    void updateFlight(Flight flight);
}
