package dao;

import api.LuggageDao;
import entity.*;
import exceptions.ApplicationException;
import exceptions.PassengerAlreadyExistException;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LuggageDaoImplTest {
    private static TicketDaoImpl ticketDao = new TicketDaoImpl();
    private static PassengerDaoImpl passengerDao = new PassengerDaoImpl();
    private static LuggageDaoImpl luggageDao = new LuggageDaoImpl();
    private static FlightDaoImpl flightDao = new FlightDaoImpl();
    private static AirlineDaoImpl airlineDao = new AirlineDaoImpl();
    private static AirportDaoImpl airportDao = new AirportDaoImpl();
    private static PlaneDaoImpl planeDao = new PlaneDaoImpl();
    private static Ticket ticket= new Ticket();
    private static Passenger passenger = new Passenger();
    private static Luggage luggage = new Luggage();
    private static Airline airline = new Airline();
    private static Plane plane = new Plane();
    private static Airport airport = new Airport();
    private static Flight flight = new Flight();

    @BeforeAll
    static void init(){
        try {
            luggageDao.openCurrentSessionWithTransaction();
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
        passengerDao.setCurrentSession(luggageDao.getCurrentSession());
        passengerDao.setCurrentTransaction(luggageDao.getCurrentTransaction());
        passenger = new Passenger("John", "Johnson", "46080912158");
        try {
            passengerDao.savePassenger(passenger);
        } catch (PassengerAlreadyExistException e) {
            e.printStackTrace();
        }
        passenger = passengerDao.getById(1L);

        airlineDao.setCurrentSession(passengerDao.getCurrentSession());
        airlineDao.setCurrentTransaction(passengerDao.getCurrentTransaction());
        airline = new Airline(1L,"Airline1", "Country1", "otherDetials1");
        airlineDao.saveAirline(airline);
        airline = airlineDao.getAirlineById(1L);

        plane = new Plane("reg1", 123, "plane1", 70, 1000 );
        planeDao.setCurrentSession(airlineDao.getCurrentSession());
        planeDao.setCurrentTransaction(airlineDao.getCurrentTransaction());
        plane.setAirline(airline);
        planeDao.savePlane(plane);
        plane = planeDao.getPlaneById(1L);

        airportDao.setCurrentSession(airlineDao.getCurrentSession());
        airportDao.setCurrentTransaction(airlineDao.getCurrentTransaction());
        airport = new Airport("Airport Hamburg","Flughafenstr. 1-3, 22335 Hamburg", "Hamburg");
        airportDao.saveAirport(airport);
        airport = airportDao.getAirportById(1L);

        flight = new Flight(airport, airport, LocalDateTime.now(), plane);
        flightDao.setCurrentSession(airlineDao.getCurrentSession());
        flightDao.setCurrentTransaction(airlineDao.getCurrentTransaction());
        flightDao.saveFlight(flight);

        ticketDao.setCurrentSession(flightDao.getCurrentSession());
        ticketDao.setCurrentTransaction(flightDao.getCurrentTransaction());
        ticket = new Ticket();
        ticket.setFlight(flight);
        ticket.setPassenger(passenger);
        ticketDao.saveTicket(ticket);
        ticket = ticketDao.getTicketById(1L);

    }
    @Test
    @Order(0)
    void saveLuggage() {
        luggageDao.setCurrentSession(ticketDao.getCurrentSession());
        luggageDao.setCurrentTransaction(ticketDao.getCurrentTransaction());
        Luggage luggage = new Luggage("A1", 60F, 80);
        luggage.setTicket(ticket);
        System.out.println(luggage);
        System.out.println(ticket);
        luggageDao.saveLuggage(luggage);
        assertEquals(luggage.getHeight(), luggageDao.getLuggageByCode("A1").getHeight());
    }

    @Test
    void getLuggageByCode() {
        assertEquals(luggage.getWeight(), luggageDao.getLuggageByCode("A1").getWeight());
    }

    @Test
    void updateLuggage() {
        luggage = luggageDao.getLuggageByCode("A1");
        luggage.setHeight(100);
        luggageDao.saveLuggage(luggage);
        assertEquals(luggage.getHeight(), luggageDao.getLuggageByCode("A1").getHeight());
    }
}