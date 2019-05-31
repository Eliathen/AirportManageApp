package dao;

import api.PassengerDao;
import entity.Passenger;
import exceptions.PassengerAlreadyExist;
import org.hibernate.query.Query;

import java.util.LinkedList;
import java.util.List;

public class PassengerDaoImpl extends BaseDao implements PassengerDao {

    public void savePassenger(Passenger passenger) throws PassengerAlreadyExist{
        if(!isPassengerAlreadyExists(passenger.getPesel())){
            getCurrentSession().save(passenger);
        }else{
            throw new PassengerAlreadyExist("Podana osoba juz istnieje w bazie");
        }
    }
    public void removePassengerById(Long Id){
        Passenger passenger = getById(Id);
        getCurrentSession().delete(passenger);
    }
    public Passenger getById(Long id){
        Query query = getCurrentSession().createQuery("FROM Passenger WHERE Id =: Id");
        query.setParameter("Id", id);
        Passenger passenger = (Passenger) query.uniqueResult();
        return passenger;
    }
    public Passenger getTickets(Passenger passenger){
        try{
            Query query = getCurrentSession().createQuery("FROM Ticket WHERE passengerId =: passengerId");
            query.setParameter("passengerId", passenger.getId());
            passenger.setTickets(query.getResultList());
            return passenger;
        }catch(NullPointerException e){
            return passenger;
        }
    }
    public Passenger getByPesel(String pesel){
        try {
            Query query = getCurrentSession().createQuery("FROM Passenger  WHERE pesel =: pesel");
            query.setParameter("pesel", pesel);
            Passenger passenger = (Passenger) query.uniqueResult();
            if(passenger == null){
                return new Passenger();
            }
            return passenger;
        }catch(NullPointerException e){
            return new Passenger();
        }
    }

    public boolean isPassengerAlreadyExists(String pesel){
        Passenger passenger = getByPesel(pesel);
        if(passenger.getPesel()==null){
            return false;
        }
        return true;
    }
    public void updatePassenger(Passenger passenger){
        getCurrentSession().update(passenger);
    }
    @SuppressWarnings("unchecked")
    public List<Passenger> getAllPassenger(){
        try{
            Query query = getCurrentSession().createQuery("FROM Passenger");
            List<Passenger> passengers = query.getResultList();
            return passengers;
        }catch(NullPointerException e){
            System.err.println("SELECT FROM PASSENEGER ERROR");
            return new LinkedList<Passenger>();
        }
    }
    @SuppressWarnings("unchecked")
    public List<Passenger> getAllPassengerBySurname(String surname){
        try{
            Query query = getCurrentSession().createQuery("FROM Passanger WHERE surname =: surname");
            query.setParameter("surname", surname);
            List<Passenger> passengers = query.getResultList();
            return passengers;
        }catch (NullPointerException e){
            return new LinkedList<Passenger>();
        }
    }
}