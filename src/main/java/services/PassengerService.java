package services;

import dao.PassengerDaoImpl;
import entity.Passenger;
import exceptions.PassengerAlreadyExistException;

import java.util.List;

public class PassengerService {
    private PassengerDaoImpl passengerDao;

    public PassengerService() {
        this.passengerDao = new PassengerDaoImpl();
    }
    public void savePassenger(Passenger passenger) throws PassengerAlreadyExistException {
        passengerDao.openCurrentSessionWithTransaction();
        passengerDao.savePassenger(passenger);
        passengerDao.closeCurrentSessionWithTransaction();
    }
    public void removePassengerById(Long id){
        passengerDao.openCurrentSessionWithTransaction();
        passengerDao.removePassengerById(id);
        passengerDao.closeCurrentSessionWithTransaction();
    }
    public void removePassengerByPesel(String pesel){
        passengerDao.openCurrentSessionWithTransaction();
        passengerDao.removePassengerByPesel(pesel);
        passengerDao.closeCurrentSessionWithTransaction();
    }
    public void updatePassenger(Passenger passenger) throws PassengerAlreadyExistException {
        passengerDao.openCurrentSessionWithTransaction();
        passengerDao.updatePassenger(passenger);
        passengerDao.closeCurrentSessionWithTransaction();
    }
    public Passenger getTickets(Passenger passenger){
        passengerDao.openCurrentSession();
        passenger = passengerDao.getTickets(passenger);
        passengerDao.closeCurrentSession();
        return passenger;
    }
    public Passenger getPassengerByPesel(String pesel){
        passengerDao.openCurrentSessionWithTransaction();
        Passenger passenger = passengerDao.getByPesel(pesel);
        passengerDao.closeCurrentSessionWithTransaction();
        return passenger;
    }
    public List<Passenger> getAllPassenger(){
        passengerDao.openCurrentSession();
        List<Passenger> passengers = passengerDao.getAllPassenger();
        passengerDao.closeCurrentSession();
        return passengers;
    }
    public List<Passenger> getAllPassengerBySurname(String surname){
        passengerDao.openCurrentSession();
        List<Passenger> passengers = passengerDao.getAllPassengerBySurname(surname);
        passengerDao.closeCurrentSession();
        return passengers;
    }

}
