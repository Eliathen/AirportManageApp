package gui.converters;

import entity.Airline;
import entity.Airport;
import entity.Flight;
import entity.Plane;
import gui.modelsFX.AirlineFX;
import gui.modelsFX.AirportFX;
import gui.modelsFX.FlightFX;
import gui.modelsFX.PlaneFX;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class FlightConverterToTest {

    @Test
    void convertToFlightFX() {
        Flight flight = new Flight();
        Airport airport = new Airport();
        airport.setId(1L);
        Plane plane = new Plane();
        plane.setModelNumber(123);
        plane.setCapacity(123);
        plane.setWeight(12);
        plane.setId(1L);
        Airline airline = new Airline();
        airline.setId(1L);
        plane.setAirline(airline);
        flight.setInitialDate(LocalDateTime.now());
        flight.setId(1L);
        flight.setFinalAirport(airport);
        flight.setInitialAirport(airport);
        flight.setPlane(plane);
        FlightFX flightFX = FlightConverterTo.convertToFlightFX(flight);
        assertNotNull(flightFX);
        assertEquals(flight.getId(), flightFX.getId());
        assertEquals(flight.getInitialDate(), flightFX.getDateTime());
        assertEquals(flight.getFinalAirport().getId(), flightFX.getFinalAirport().getId());
        assertEquals(flight.getInitialAirport().getId(), flightFX.getInitialAirport().getId());
        assertEquals(flight.getPlane().getId(), flightFX.getPlaneFX().getId());
    }

    @Test
    void convertToFlight() {
        FlightFX flightFX = new FlightFX();
        AirportFX airportFX = new AirportFX();
        airportFX.setId(1L);
        PlaneFX planeFX = new PlaneFX();
        planeFX.setModelNumber(123);
        planeFX.setCapacity(123);
        planeFX.setWeight(12);
        planeFX.setId(1L);
        AirlineFX airlineFX = new AirlineFX();
        airlineFX.setId(1L);
        planeFX.setAirlineFX(airlineFX);
        flightFX.setDateTime(LocalDateTime.now());
        flightFX.setId(1L);
        flightFX.setFinalAirport(airportFX);
        flightFX.setInitialAirport(airportFX);
        flightFX.setPlaneFX(planeFX);
        Flight flight = FlightConverterTo.convertToFlight(flightFX);
        assertNotNull(flightFX);
        assertEquals(flightFX.getId(), flight.getId());
        assertEquals(flightFX.getDateTime(), flight.getInitialDate());
        assertEquals(flightFX.getFinalAirport().getId(), flight.getFinalAirport().getId());
        assertEquals(flightFX.getInitialAirport().getId(), flight.getInitialAirport().getId());
        assertEquals(flightFX.getPlaneFX().getId(), flight.getPlane().getId());
    }
}