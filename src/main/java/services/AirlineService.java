package services;

import dao.AirlineDaoImpl;
import entity.Airline;
import exceptions.ApplicationException;

import java.util.List;

public class AirlineService {

    AirlineDaoImpl airlineDao;

    public AirlineService(){
        airlineDao = new AirlineDaoImpl();
    }

    public void saveAirline(Airline airline) throws ApplicationException {
        airlineDao.openCurrentSessionWithTransaction();
        airlineDao.saveAirline(airline);
        airlineDao.closeCurrentSessionWithTransaction();
    }

    public Airline getEmployees(Airline airline) throws ApplicationException {
        airlineDao.openCurrentSession();
        airline = airlineDao.getEmployees(airline);
        airlineDao.closeCurrentSession();
        return airline;
    }
    public Airline getPlanes(Airline airline) throws ApplicationException {
        airlineDao.openCurrentSession();
        airline = airlineDao.getPlanes(airline);
        airlineDao.closeCurrentSession();
        return airline;
    }

    public void removeAirlineById(Long id) throws ApplicationException {
        airlineDao.openCurrentSessionWithTransaction();
        airlineDao.removeAirlineById(id);
        airlineDao.closeCurrentSessionWithTransaction();
    }

    public void updateAirline(Airline airline) throws ApplicationException {
        airlineDao.openCurrentSessionWithTransaction();
        airlineDao.updateAirline(airline);
        airlineDao.closeCurrentSessionWithTransaction();
    }

    public List<Airline> getAllAirlines() throws ApplicationException {
        airlineDao.openCurrentSession();
        List<Airline> airlines = airlineDao.getAllAirline();
        airlineDao.closeCurrentSession();
        return airlines;
    }
}
