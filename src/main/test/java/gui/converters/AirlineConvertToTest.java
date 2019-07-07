package gui.converters;

import entity.Airline;
import gui.modelsFX.AirlineFX;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AirlineConvertToTest {

    @Test
    void convertToAirlineFX() {
        Airline airline = new Airline();
        airline.setCountry("Poland");
        airline.setId(1L);
        airline.setName("Name");
        airline.setDetails("details");
        AirlineFX airlineFX = AirlineConvertTo.convertToAirlineFX(airline);
        assertNotNull(airlineFX);
        assertEquals(airlineFX.getCountry(), airline.getCountry());

    }

    @Test
    void convertToAirline() {
        AirlineFX airlineFX = new AirlineFX();
        airlineFX.setCountry("Poland");
        airlineFX.setName("Name");
        airlineFX.setOtherDetails("Details");
        airlineFX.setId(1L);
        Airline airline = AirlineConvertTo.convertToAirline(airlineFX);
        assertEquals(airline.getName(), airlineFX.getName());
        assertEquals(airline.getDetails(), airlineFX.getOtherDetails());
        assertNotNull(airline);
    }
}