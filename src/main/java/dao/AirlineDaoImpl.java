package dao;

import entity.Airline;
import org.hibernate.query.Query;

import java.util.LinkedList;
import java.util.List;
import api.AirlineDao;

public class AirlineDaoImpl extends BaseDao implements AirlineDao {

    public AirlineDaoImpl() {

    }
    public void saveAirline(Airline airline) {
            getCurrentSession().save(airline);
    }

    public Airline getAirlineById(Long id) {
        try {
            Query query = getCurrentSession().createQuery("FROM Airline WHERE id =: id");
            query.setParameter("id", id);
            Airline airline = (Airline) query.uniqueResult();
            return airline;
        }catch(NullPointerException e){
            return new Airline();
        }
    }
    public Airline getPlanes(Airline airline){
        try{
            Query query = getCurrentSession().createQuery("FROM Plane where id=: id");
            query.setParameter("id", airline.getId());
            airline.setPlanes(query.getResultList());
            return airline;
        }catch(NullPointerException e){
            return airline;
        }
    }
    public Airline getEmployees(Airline airline){
        try{
            Query query = getCurrentSession().createQuery("FROM Employee where id=: id");
            query.setParameter("id", airline.getId());
            airline.setEmployees(query.getResultList());
            return airline;
        }catch(NullPointerException e){
            return airline;
        }
    }
    public void removeAirlineById(Long id) {
        Airline airline = getAirlineById(id);
        getCurrentSession().delete(airline);
    }

    public List<Airline> getAllAirline() {
        try{
            Query query = getCurrentSession().createQuery("FROM Airline");
            List<Airline> airlines = query.getResultList();
            return airlines;
        }catch(NullPointerException e){
            return new LinkedList<Airline>();
        }
    }

    public void updateAirline(Airline airline) {
           getCurrentSession().update(airline);
    }
}
