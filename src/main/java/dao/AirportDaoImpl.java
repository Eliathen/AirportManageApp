package dao;

import entity.Airport;
import org.hibernate.query.Query;

import java.util.LinkedList;
import java.util.List;

public class AirportDaoImpl extends BaseDao implements api.AirportDao {

    public AirportDaoImpl() {
    }

    public void saveAirport(Airport airport){
        getCurrentSession().save(airport);
    }

    public void removeAirportById(Long id){
        getCurrentSession().delete(getAirportById(id));
    }

    public Airport getAirportById(Long id){
        try{
            Query query = getCurrentSession().createQuery("FROM Airport WHERE id =: id");
            query.setParameter("id", id);
            Airport airport = (Airport)query.uniqueResult();
            return airport;
        }catch(NullPointerException e){
            return new Airport();
        }
    }

    public List<Airport> getAllAirport(){
        try{
            Query query = getCurrentSession().createQuery("FROM Airport");
            List<Airport> airports= query.getResultList();
            return airports;
        }catch(NullPointerException e){
            return new LinkedList<Airport>();
        }
    }

    public void updateAirport(Airport airport){
        getCurrentSession().update(airport);
    }
}
