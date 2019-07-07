package gui.converters;

import entity.*;
import gui.modelsFX.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TicketConverterToTest {

    @Test
    void convertToTicketFX() {
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
        Passenger passenger = new Passenger(1L, "Name", "Surname", "42060327215");
        Ticket ticket = new Ticket();
        ticket.setId(1L);
        ticket.setFlight(flight);
        ticket.setPassenger(passenger);
        Luggage luggage = new Luggage();
        luggage.setCode("A1");
        luggage.setHeight(60);
        luggage.setWeight(60f);
        luggage.setTicket(ticket);
        List<Luggage> luggageList = new LinkedList<>();
        luggageList.add(luggage);
        ticket.setLuggages(luggageList);
        TicketFX ticketFX = TicketConverterTo.convertToTicketFX(ticket);
        assertNotNull(ticketFX);
        assertEquals(ticket.getPassenger().getId(), ticketFX.getPassengerFX().getId());
        assertEquals(ticket.getLuggages().get(0).getCode(), ticketFX.getLuggageFXES().get(0).getCode());

    }

    @Test
    void convertToTicket() {
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
        PassengerFX passengerFX = new PassengerFX();
        passengerFX.setPesel("42060327215");
        passengerFX.setId(1L);
        passengerFX.setName("Name");
        passengerFX.setSurname("Surname");
        TicketFX ticketFX = new TicketFX();
        ticketFX.setId(1L);
        ticketFX.setFlightFX(flightFX);
        ticketFX.setPassengerFX(passengerFX);
        LuggageFX luggageFX = new LuggageFX();
        luggageFX.setCode("A1");
        luggageFX.setHeight(60);
        luggageFX.setWeight(60f);
        luggageFX.setTicket(ticketFX);
        ObservableList<LuggageFX> luggageFXList = FXCollections.observableArrayList();
        luggageFXList.add(luggageFX);
        ticketFX.setLuggageFXES(luggageFXList);
        Ticket ticket = TicketConverterTo.convertToTicket(ticketFX);
        assertNotNull(ticket);
        assertEquals(ticketFX.getId(), ticket.getId());
        assertEquals(ticketFX.getPassengerFX().getPesel(), ticket.getPassenger().getPesel());
        assertEquals(ticketFX.getLuggageFXES().get(0).getCode(), ticket.getLuggages().get(0).getCode());
        assertEquals(ticketFX.getFlightFX().getDateTime(), ticket.getFlight().getInitialDate());
    }
}