package services;

import dao.PassengerDao;
import entity.Passenger;

import java.util.List;

public class PassengerService {
    private PassengerDao passengerDao;

    public PassengerService() {
        this.passengerDao = new PassengerDao();
    }
    public void savePassenger(Passenger passenger){
        passengerDao.openCurrentSessionWithTransaction();
        passengerDao.savePassenger(passenger);
        passengerDao.closeCurrentSessionWithTransaction();
    }
    public void removePassengerById(Long id){
        passengerDao.openCurrentSessionWithTransaction();
        passengerDao.removePassengerById(id);
        passengerDao.closeCurrentSessionWithTransaction();
    }
    public void updatePassenger(Passenger passenger){
        passengerDao.openCurrentSessionWithTransaction();
        passengerDao.updatePassenger(passenger);
        passengerDao.closeCurrentSessionWithTransaction();
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
