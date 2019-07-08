package dao;

import api.FlightDao;
import entity.Flight;
import org.hibernate.Hibernate;
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

    public Flight getEmployees(Flight flight){
            String hql = "SELECT e FROM Employee e join e.flights f WHERE f.id =: id";
            Query query = getCurrentSession().createQuery(hql);
            query.setParameter("id", flight.getId());
            flight.setEmployees(query.getResultList());
            return flight;
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

    public void updateFlight(Flight flight){
        getCurrentSession().update(flight);
    }
}
