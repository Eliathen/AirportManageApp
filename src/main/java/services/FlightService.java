package services;

import dao.FlightDaoImpl;
import entity.Flight;

import java.time.LocalDateTime;
import java.util.List;

public class FlightService {
    private FlightDaoImpl flightDao;

    public FlightService() {
        flightDao = new FlightDaoImpl();
    }

    public void saveFlight(Flight flight){
        flightDao.openCurrentSessionWithTransaction();
        flightDao.saveFlight(flight);
        flightDao.closeCurrentSessionWithTransaction();
    }

    public void removeFlight(Flight flight){
        flightDao.openCurrentSessionWithTransaction();
        flightDao.removeFlight(flight);
        flightDao.closeCurrentSessionWithTransaction();

    }
    public Flight getFlightById(Long id){
        flightDao.openCurrentSession();
        Flight flight = flightDao.getFlightById(id);
        flightDao.closeCurrentSession();
        return flight;
    }
    public List<Flight> getAllFlight(){
        flightDao.openCurrentSession();
        List<Flight> flights = flightDao.getAllFlight();
        flightDao.closeCurrentSession();
        return flights;
    }

    public List<Flight> getAllFlightByData(LocalDateTime date){
        flightDao.openCurrentSession();
        List<Flight> flights = flightDao.getAllFlightByDate(date);
        flightDao.closeCurrentSession();
        return flights;
    }

    public void updateFlight(Flight flight){
        flightDao.openCurrentSessionWithTransaction();
        flightDao.updateFlight(flight);
        flightDao.closeCurrentSessionWithTransaction();
    }
}
