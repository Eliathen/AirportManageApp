package api;

import entity.Luggage;

import java.io.Serializable;
import java.util.List;

public interface LuggageDao extends Serializable {
    void saveLuggage(Luggage luggage);

    void removeLuggageByCode(String code);

    void updateLuggage(Luggage luggage);

    Luggage getLuggageByCode(String code);

    List<Luggage> getAllLuggage();
}
