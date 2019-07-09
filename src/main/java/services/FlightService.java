package services;

import dao.FlightDaoImpl;
import entity.Employee;
import entity.Flight;
import exceptions.ApplicationException;

import java.time.LocalDateTime;
import java.util.List;


public class FlightService {

    private FlightDaoImpl flightDao;

    public FlightService() {
        flightDao = new FlightDaoImpl();
    }

    public void saveFlight(Flight flight) throws ApplicationException {
        flightDao.openCurrentSessionWithTransaction();
        flightDao.saveFlight(flight);
        flightDao.closeCurrentSessionWithTransaction();
    }

    public void removeFlight(Flight flight) throws ApplicationException {
        flightDao.openCurrentSessionWithTransaction();
        flightDao.removeFlight(flight);
        flightDao.closeCurrentSessionWithTransaction();

    }

    public Flight getEmployees(Flight flight) throws ApplicationException {
        flightDao.openCurrentSession();
        flight = flightDao.getEmployees(flight);
        flightDao.closeCurrentSession();
        return flight;
    }

    public List<Flight> getAllFlight() throws ApplicationException {
        flightDao.openCurrentSession();
        List<Flight> flights = flightDao.getAllFlight();
        flightDao.closeCurrentSession();
        return flights;
    }
    public void updateFlight(Flight flight) throws ApplicationException {
        flightDao.openCurrentSessionWithTransaction();
        flightDao.updateFlight(flight);
        flightDao.closeCurrentSessionWithTransaction();
    }
}
