package dao;


import api.PassengerDaoInterface;
import entity.Passenger;
import org.hibernate.query.Query;

import java.util.LinkedList;
import java.util.List;

public class PassengerDao extends BaseDao implements PassengerDaoInterface {

    public void savePassenger(Passenger passenger){
        if(!isPassengerAlreadyExists(passenger.getPesel())){
            getCurrentSession().save(passenger);
        }
        else{

//            jeżeli taki pasażer istnieje
        }

    }
    public void removePassengerById(Long Id){
        Passenger passenger = getById(Id);
        currentSession.delete(passenger);
    }
    public Passenger getById(Long Id){
        Query query =currentSession.createQuery("FROM Passenger WHERE Id =: Id");
        query.setParameter("Id", Id);
        Passenger passenger = (Passenger) query.uniqueResult();
        return passenger;
    }
    public Passenger getByPesel(String pesel){
        //System.out.println("getByPesel");
        try {
            Query query = currentSession.createQuery("FROM Passenger  WHERE pesel =: pesel");
            query.setParameter("pesel", pesel);
            Passenger passenger = (Passenger) query.uniqueResult();
            return passenger;
        }catch(NullPointerException e){
            System.err.println("Pasazer nie istnieje");
            return new Passenger();
        }
    }
    public void updatePassenger(Passenger passenger){
        currentSession.update(passenger);
    }

    public boolean isPassengerAlreadyExists(String pesel){
        if(getByPesel(pesel).getId()==null){
            return false;
        }
        return true;
    }
    @SuppressWarnings("unchecked")
    public List<Passenger> getAllPassenger(){
        try{
            Query query = currentSession.createQuery("FROM Passenger");
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
            Query query = currentSession.createQuery("FROM Passanger WHERE surname =: surname");
            query.setParameter("surname", surname);
            List<Passenger> passengers = query.getResultList();
            return passengers;
        }catch (NullPointerException e){
            return new LinkedList<Passenger>();
        }
    }


}
