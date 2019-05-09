package api;

import entity.Airline;
import java.util.List;


public interface AirlineDaoInterface {
    void saveAirline(Airline airline);

    void removeAirlineById(Long id);

    Airline getAirlineById(Long id);

    List<Airline> getAllAirline();

    void updateAirline(Airline airline);

}
