package dao;

import api.FlightDao;
import entity.Flight;
import org.hibernate.query.Query;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class FlightDaoImpl extends BaseDao implements FlightDao {

    public void saveFlight(Flight flight){
        getCurrentSession().save(flight);
    }

    public void removeFlight(Flight flight){
        getCurrentSession().delete(flight);
    }

    public Flight getFlightById(Long id){
        try{
            Query query = getCurrentSession().createQuery("FROM Flight WHERE id =: id");
            query.setParameter("id", id);
            Flight flight = (Flight)query.uniqueResult();
            return flight;
        }catch (NullPointerException e){
            return new Flight();
        }
    }
    public List<Flight> getAllFlight(){
        try{
            Query query = getCurrentSession().createQuery("FROM Flight");
            List<Flight> flights = query.getResultList();
            return flights;
        }catch(NullPointerException e){
            return new LinkedList<Flight>();
        }

    }

    public List<Flight> getAllFlightByDate(LocalDateTime date){
        try{
            Query query = getCurrentSession().createQuery("FROM Flight where initialDate =: initialDate");
            query.setParameter("initialDate", date);
            List<Flight> flights = query.getResultList();
            return flights;
        }catch(NullPointerException e){
            return new LinkedList<Flight>();
        }

    }

    public void updateFlight(Flight flight){
        getCurrentSession().update(flight);
    }
}
