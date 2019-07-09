package services;

import dao.AirportDaoImpl;
import entity.Airport;
import exceptions.ApplicationException;

import java.util.List;

public class AirportService {

    private AirportDaoImpl airportDao;

    public AirportService() {
        airportDao = new AirportDaoImpl();
    }

    public void saveAirport(Airport airport) throws ApplicationException {
        airportDao.openCurrentSessionWithTransaction();
        airportDao.saveAirport(airport);
        airportDao.closeCurrentSessionWithTransaction();
    }

    public void removeAirportById(Long id) throws ApplicationException {
        airportDao.openCurrentSessionWithTransaction();
        airportDao.removeAirportById(id);
        airportDao.closeCurrentSessionWithTransaction();
    }

    public void updateAirport(Airport airport) throws ApplicationException {
        airportDao.openCurrentSessionWithTransaction();
        airportDao.updateAirport(airport);
        airportDao.closeCurrentSessionWithTransaction();
    }

    public List<Airport> getAllAirport() throws ApplicationException {
        airportDao.openCurrentSession();
        List<Airport> airports = airportDao.getAllAirport();
        airportDao.closeCurrentSession();
        return airports;
    }
}
