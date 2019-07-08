package dao;

import api.PassengerDao;
import entity.Passenger;
import exceptions.ApplicationException;
import exceptions.PassengerAlreadyExistException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import java.util.LinkedList;
import java.util.List;

public class PassengerDaoImpl extends BaseDao implements PassengerDao {

    public void savePassenger(Passenger passenger) throws PassengerAlreadyExistException {
        if(!isPassengerAlreadyExists(passenger.getPesel())){
            getCurrentSession().save(passenger);
        }else{
            throw new PassengerAlreadyExistException("Passengeer exists in Datebase");
        }
    }
    public Passenger getById(Long id){
            Query query = getCurrentSession().createQuery("FROM Passenger WHERE Id =: Id");
            query.setParameter("Id", id);
            Passenger passenger = (Passenger) query.uniqueResult();
            if(passenger==null){
                Passenger passenger1 = new Passenger();
                return passenger1;
            }
            return passenger;
    }
    public void removePassengerByPesel(String pesel){
        Passenger passenger = getByPesel(pesel);
        if(passenger.getPesel()!=null){
            getCurrentSession().delete(passenger);
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
    public void updatePassenger(Passenger passenger) throws PassengerAlreadyExistException {
        try {
            Query query = getCurrentSession().createQuery("UPDATE Passenger SET name = :name, surname = :surname, pesel= :pesel WHERE id = :id");
            query.setParameter("name", passenger.getName());
            query.setParameter("surname", passenger.getSurname());
            query.setParameter("pesel", passenger.getPesel());
            query.setParameter("id", passenger.getId());
            int update = query.executeUpdate();
        }catch(ConstraintViolationException e){
            throw new PassengerAlreadyExistException("Pesel exists in database");
        }
    }
    @SuppressWarnings("unchecked")
    public List<Passenger> getAllPassenger(){
        try{
            Query query = getCurrentSession().createQuery("FROM Passenger");
            List<Passenger> passengers = query.getResultList();
            return passengers;
        }catch(NullPointerException e){
            return new LinkedList<Passenger>();
        }
    }
}