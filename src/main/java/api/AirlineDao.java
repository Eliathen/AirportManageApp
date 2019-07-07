package api;

import entity.Airline;

import java.io.Serializable;
import java.util.List;

public interface AirlineDao extends Serializable {

    void saveAirline(Airline airline);

    void removeAirlineById(Long id);

    Airline getAirlineById(Long id);
   
    List<Airline> getAllAirline();
   
    Airline getPlanes(Airline airline);
    
    Airline getEmployees(Airline airline);
    
    void updateAirline(Airline airline);

}