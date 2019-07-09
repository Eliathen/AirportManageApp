package gui.converters;

import entity.Luggage;
import gui.modelsFX.LuggageFX;

public class LuggageConverterTo {

    public static LuggageFX converterToLuggageFX(Luggage luggage){
        LuggageFX luggageFX = new LuggageFX();
        luggageFX.setCode(luggage.getCode());
        luggageFX.setHeight(luggage.getHeight());
        luggageFX.setWeight(luggage.getWeight());
        return luggageFX;
    }

    public static Luggage convertToLuggage(LuggageFX luggageFX){
        Luggage luggage = new Luggage();
        luggage.setCode(luggageFX.getCode());
        luggage.setHeight(luggageFX.getHeight());
        luggage.setWeight(luggageFX.getWeight());
        return luggage;
    }
}
