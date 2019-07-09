package services;

import dao.LuggageDaoImpl;
import entity.Luggage;
import exceptions.ApplicationException;

import java.util.List;

public class LuggageService {

    private LuggageDaoImpl luggageDao;

    public LuggageService(){
        this.luggageDao = new LuggageDaoImpl();

    }

    public void saveLuggage(Luggage luggage) throws ApplicationException {
        luggageDao.openCurrentSessionWithTransaction();
        luggageDao.saveLuggage(luggage);
        luggageDao.closeCurrentSessionWithTransaction();
    }

    public Luggage getLuggageByCode(String code) throws ApplicationException {
        luggageDao.openCurrentSession();
        Luggage luggage = luggageDao.getLuggageByCode(code);
        luggageDao.closeCurrentSession();
        return luggage;
    }

    public void removeLuggageByCode(String code) throws ApplicationException {
        luggageDao.openCurrentSessionWithTransaction();
        luggageDao.removeLuggageByCode(code);
        luggageDao.closeCurrentSessionWithTransaction();
    }

    public void updateLuggage(Luggage luggage) throws ApplicationException {
        luggageDao.openCurrentSessionWithTransaction();
        luggageDao.updateLuggage(luggage);
        luggageDao.closeCurrentSessionWithTransaction();
    }
}
