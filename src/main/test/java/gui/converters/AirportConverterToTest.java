package gui.converters;

import entity.Airline;
import entity.Airport;
import gui.modelsFX.AirportFX;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AirportConverterToTest {

    @Test
    void convertToAirportFX() {
        Airport airport = new Airport();
        airport.setId(1L);
        airport.setName("name1");
        airport.setAddress("Addres");
        airport.setCity("City");
        AirportFX airportFX = AirportConverterTo.convertToAirportFX(airport);
        assertNotNull(airportFX);
        assertEquals(airport.getId(), airportFX.getId());
        assertEquals(airport.getName(), airportFX.getName());
        assertEquals(airport.getCity(), airportFX.getCity());
    }

    @Test
    void convertToAirport() {
        AirportFX airportFX = new AirportFX();
        airportFX.setId(1L);
        airportFX.setName("name1");
        airportFX.setAddress("Addres");
        airportFX.setCity("City");
        Airport airport = AirportConverterTo.convertToAirport(airportFX);
        assertNotNull(airport);
        assertEquals(airportFX.getName(), airport.getName());
        assertEquals(airportFX.getCity(), airport.getCity());
    }
}