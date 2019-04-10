package api;

import entity.Airport;
import java.util.List;

public interface AirportDao {
    void saveAirport(Airport airport);

    void removeAirportById(Long id);
    List<Airport> getAllAirport();

    void updateAirport(Airport airport);
}
