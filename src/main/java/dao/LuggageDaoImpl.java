package dao;

import entity.Luggage;
import org.hibernate.query.Query;

import java.util.LinkedList;
import java.util.List;


public class LuggageDaoImpl extends BaseDao implements api.LuggageDao {

    public LuggageDaoImpl(){

    }

    public void saveLuggage(Luggage luggage){
        if(!isLuggageAlreadyExists(luggage.getCode())){
            getCurrentSession().save(luggage);
        }else{
            //Jezeli bagaz juz istnieje
        }
    }
    public boolean isLuggageAlreadyExists(String code){
        if((getLuggageByCode(code))!=null){
            return true;
        }else{
            return false;
        }
    }
    public Luggage getLuggageByCode(String code){
        try {
            Query query = getCurrentSession().createQuery("FROM Luggage WHERE code =: code");
            query.setParameter("code", code);
            Luggage luggage = (Luggage) query.uniqueResult();
            return luggage;
        }catch(NullPointerException e){
            return new Luggage();
        }
    }

    public void removeLuggageByCode(String code){
        Luggage luggage = getLuggageByCode(code);
        getCurrentSession().delete(luggage);
    }

    public void updateLuggage(Luggage luggage){
        getCurrentSession().update(luggage);
    }

    public List<Luggage> getAllLuggage(){
        try{
            Query query = getCurrentSession().createQuery("From Luggage");
            List<Luggage> luggages = query.getResultList();
            return luggages;
        }catch(NullPointerException e){
            return new LinkedList<Luggage>();
        }
    }
}
