package dao;

import entity.Airport;
import org.hibernate.query.Query;

import java.util.LinkedList;
import java.util.List;

public class AirportDaoImpl extends BaseDao implements api.AirportDao {
    public AirportDaoImpl() {
    }

    public void saveAirport(Airport airport){
        if(!isAirportAlreadyExists(airport.getId())){
            getCurrentSession().save(airport);
        }else{
            //lotnisko istnieje
        }
    }

    public void removeAirportById(Long id){
        getCurrentSession().delete(getAirportById(id));
    }
    private boolean isAirportAlreadyExists(Long id){
        try {
            if (getAirportById(id).getName() != null) {
                return true;
            }
        }catch(NullPointerException e){
            return false;
        }
        return false;
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
    public Airport getInitialFlights(Airport airport){
        try{
            Query query = getCurrentSession().createQuery("FROM Flight WHERE initialAirportId =: initialAirportId");
            query.setParameter("initialAirportId", airport.getId());
            airport.setInitialFlights(query.getResultList());
            return airport;
        }catch(NullPointerException e){
            return airport;
        }
    }
    public Airport getFinalFlights(Airport airport){
        try{
            Query query = getCurrentSession().createQuery("FROM Flight WHERE finalAirportId =: finalAirportId");
            query.setParameter("finalAirportId", airport.getId());
            airport.setInitialFlights(query.getResultList());
            return airport;
        }catch(NullPointerException e){
            return airport;
        }
    }
    public List<Airport> getAllAirport(){
        try{
            Query query = getCurrentSession().createQuery("FROM airport");
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
