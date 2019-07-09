package services;

import dao.PassengerDaoImpl;
import entity.Passenger;
import exceptions.ApplicationException;
import exceptions.PassengerAlreadyExistException;

import java.util.List;

public class PassengerService {

    private PassengerDaoImpl passengerDao;

    public PassengerService() {
        this.passengerDao = new PassengerDaoImpl();
    }

    public void savePassenger(Passenger passenger) throws PassengerAlreadyExistException, ApplicationException {
        passengerDao.openCurrentSessionWithTransaction();
        passengerDao.savePassenger(passenger);
        passengerDao.closeCurrentSessionWithTransaction();
    }

    public void removePassengerByPesel(String pesel) throws ApplicationException {
        passengerDao.openCurrentSessionWithTransaction();
        passengerDao.removePassengerByPesel(pesel);
        passengerDao.closeCurrentSessionWithTransaction();
    }

    public void updatePassenger(Passenger passenger) throws PassengerAlreadyExistException, ApplicationException {
        passengerDao.openCurrentSessionWithTransaction();
        passengerDao.updatePassenger(passenger);
        passengerDao.closeCurrentSessionWithTransaction();
    }

    public Passenger getPassengerByPesel(String pesel) throws ApplicationException {
        passengerDao.openCurrentSessionWithTransaction();
        Passenger passenger = passengerDao.getByPesel(pesel);
        passengerDao.closeCurrentSessionWithTransaction();
        return passenger;
    }

    public List<Passenger> getAllPassenger() throws ApplicationException {
        passengerDao.openCurrentSession();
        List<Passenger> passengers = passengerDao.getAllPassenger();
        passengerDao.closeCurrentSession();
        return passengers;
    }


}
