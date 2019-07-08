package dao;
import entity.Luggage;
import org.hibernate.query.Query;

import java.util.LinkedList;
import java.util.List;

public class LuggageDaoImpl extends BaseDao implements api.LuggageDao {

    public LuggageDaoImpl(){

    }

    public void saveLuggage(Luggage luggage){
            getCurrentSession().saveOrUpdate(luggage);
    }

    public Luggage getLuggageByCode(String code){
        try {
            Query query = getCurrentSession().createQuery("FROM Luggage WHERE code =: code");
            query.setParameter("code", code);
            Luggage luggage = (Luggage) query.uniqueResult();
            if(luggage.getCode()==null){
                return new Luggage();
            }
            return luggage;
        }catch(NullPointerException e){
            return new Luggage();
        }
    }

    public void removeLuggageByCode(String code){
        Luggage luggage = getLuggageByCode(code);
        getCurrentSession().remove(luggage);
    }

    public void updateLuggage(Luggage luggage){
        getCurrentSession().update(luggage);
    }
}
