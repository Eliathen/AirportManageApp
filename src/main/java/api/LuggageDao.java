package api;

import entity.Luggage;

import java.util.List;

public interface LuggageDao {
    void saveLuggage(Luggage luggage);

    void removeLuggageById(Long id);

    void removeLuggageByCode(String code);

    void updateLuggage(Luggage luggage);

    List<Luggage> getAllLuggage();
}
