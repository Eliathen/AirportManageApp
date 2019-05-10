package services;

import dao.AirportDaoImpl;
import entity.Airport;

import java.util.List;

public class AirportService {
    private AirportDaoImpl airportDao;

    public AirportService() {
        airportDao = new AirportDaoImpl();
    }
    public void saveAirport(Airport airport){
        airportDao.openCurrentSessionWithTransaction();
        airportDao.saveAirport(airport);
        airportDao.closeCurrentSessionWithTransaction();
    }
    public void removeAirportById(Long id){
        airportDao.openCurrentSessionWithTransaction();
        airportDao.removeAirportById(id);
        airportDao.closeCurrentSessionWithTransaction();
    }
    public void updateAirport(Airport airport){
        airportDao.openCurrentSessionWithTransaction();
        airportDao.updateAirport(airport);
        airportDao.closeCurrentSessionWithTransaction();
    }
    public Airport getAirportById(Long id){
        airportDao.openCurrentSession();
        Airport airport = airportDao.getAirportById(id);
        airportDao.closeCurrentSession();
        return airport;
    }
    public List<Airport> getAllAirport(){
        airportDao.openCurrentSession();
        List<Airport> airports = airportDao.getAllAirport();
        airportDao.closeCurrentSession();
        return airports;
    }
}
