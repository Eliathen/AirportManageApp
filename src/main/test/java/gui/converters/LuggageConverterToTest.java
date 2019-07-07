package gui.converters;

import entity.Luggage;
import gui.modelsFX.LuggageFX;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LuggageConverterToTest {

    @Test
    void converterToLuggageFX() {
        Luggage luggage = new Luggage("A1", 50f,60);
        LuggageFX luggageFX = LuggageConverterTo.converterToLuggageFX(luggage);
        assertNotNull(luggageFX);
        assertEquals(luggage.getCode(), luggageFX.getCode());
        assertEquals(luggage.getHeight(), luggageFX.getHeight());
        assertEquals(luggage.getWeight(), luggageFX.getWeight());
    }

    @Test
    void convertToLuggage() {
        LuggageFX luggageFX = new LuggageFX();
        luggageFX.setCode("A1");
        luggageFX.setWeight(50F);
        luggageFX.setHeight(60);
        Luggage luggage = LuggageConverterTo.convertToLuggage(luggageFX);
        assertNotNull(luggage);
        assertEquals(luggageFX.getCode(), luggage.getCode());
        assertEquals(luggageFX.getHeight(), luggage.getHeight());
        assertEquals(luggageFX.getWeight(), luggage.getWeight());
    }
}