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
        if(!isAirlineAlreadyExists(airline.getId())){
            getCurrentSession().save(airline);
        }else{
            //Jesli linia juz istnieje
        }
    }
    private boolean isAirlineAlreadyExists(Long id){
        try {
            if (getAirlineById(id).getName() != null) {
                return true;
            }
        }catch(NullPointerException e){
            return false;
        }
        return false;
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
