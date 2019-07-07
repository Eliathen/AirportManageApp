package services;

import dao.AirlineDaoImpl;
import entity.Airline;

import java.util.List;

public class AirlineService {
    AirlineDaoImpl airlineDao;

    public AirlineService(){
        airlineDao = new AirlineDaoImpl();
    }
    public void saveAirline(Airline airline){
        airlineDao.openCurrentSessionWithTransaction();
        airlineDao.saveAirline(airline);
        airlineDao.closeCurrentSessionWithTransaction();
    }
    public Airline getAirlineById(Long id){
        airlineDao.openCurrentSession();
        Airline airline = airlineDao.getAirlineById(id);
        airlineDao.closeCurrentSession();
        return airline;
    }
    public Airline getEmployees(Airline airline){
        airlineDao.openCurrentSession();
        airline = airlineDao.getEmployees(airline);
        airlineDao.closeCurrentSession();
        return airline;
    }
    public Airline getPlanes(Airline airline){
        airlineDao.openCurrentSession();
        airline = airlineDao.getPlanes(airline);
        airlineDao.closeCurrentSession();
        return airline;
    }
    public void removeAirlineById(Long id){
        airlineDao.openCurrentSessionWithTransaction();
        airlineDao.removeAirlineById(id);
        airlineDao.closeCurrentSessionWithTransaction();
    }
    public void updateAirline(Airline airline){
        airlineDao.openCurrentSessionWithTransaction();
        airlineDao.updateAirline(airline);
        airlineDao.closeCurrentSessionWithTransaction();
    }
    public List<Airline> getAllAirlines(){
        airlineDao.openCurrentSession();
        List<Airline> airlines = airlineDao.getAllAirline();
        airlineDao.closeCurrentSession();
        return airlines;
    }
}
