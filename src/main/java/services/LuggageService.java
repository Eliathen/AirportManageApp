package services;

import dao.LuggageDaoImpl;
import entity.Luggage;

import java.util.List;

public class LuggageService {
    private LuggageDaoImpl luggageDao;

    public LuggageService(){
        this.luggageDao = new LuggageDaoImpl();

    }
    public void saveLuggage(Luggage luggage){
        luggageDao.openCurrentSessionWithTransaction();
        luggageDao.saveLuggage(luggage);
        luggageDao.closeCurrentSessionWithTransaction();
    }
    public Luggage getLuggageByCode(String code){
        luggageDao.openCurrentSession();
        Luggage luggage = luggageDao.getLuggageByCode(code);
        luggageDao.closeCurrentSession();
        return luggage;
    }
    public void removeLuggageByCode(String code){
        luggageDao.openCurrentSessionWithTransaction();
        luggageDao.removeLuggageByCode(code);
        luggageDao.closeCurrentSessionWithTransaction();
    }
    public void updateLuggage(Luggage luggage){
        luggageDao.openCurrentSessionWithTransaction();
        luggageDao.updateLuggage(luggage);
        luggageDao.openCurrentSessionWithTransaction();
    }
    public List<Luggage> getAllLuggage(){
        luggageDao.openCurrentSession();
        List<Luggage> luggages = luggageDao.getAllLuggage();
        luggageDao.closeCurrentSession();
        return luggages;
    }
}
