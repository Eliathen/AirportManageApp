package api;

import entity.Airline;
import java.util.List;


public interface AirlineDao {
    void saveAirline(Airline airline);

    void removeAirlineById(Long id);

    List<Airline> getAllAirline();

    void updateAirline(Airline airline);

}
