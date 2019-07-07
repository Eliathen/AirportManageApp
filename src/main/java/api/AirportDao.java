package api;

import entity.Airport;

import java.io.Serializable;
import java.util.List;

public interface AirportDao extends Serializable {

    void saveAirport(Airport airport);

    void removeAirportById(Long id);

    List<Airport> getAllAirport();

    void updateAirport(Airport airport);
}
