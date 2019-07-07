package dao;

import api.PassengerDao;
import api.TicketDao;
import entity.*;
import exceptions.ApplicationException;
import exceptions.PassengerAlreadyExistException;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TicketDaoImplTest {
    private static TicketDaoImpl ticketDao = new TicketDaoImpl();
    private static PassengerDaoImpl passengerDao = new PassengerDaoImpl();
    private static LuggageDaoImpl luggageDao = new LuggageDaoImpl();
    private static FlightDaoImpl flightDao = new FlightDaoImpl();
    private static AirlineDaoImpl airlineDao = new AirlineDaoImpl();
    private static AirportDaoImpl airportDao = new AirportDaoImpl();
    private static PlaneDaoImpl planeDao = new PlaneDaoImpl();
    private static Ticket ticket= new Ticket();
    private static Passenger passenger;
    private static Luggage luggage;
    private static Airline airline;
    private static Plane plane;
    private static Airport airport;
    private static Flight flight;
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
            flight.setId(1L);

    }
    @AfterAll
    static void close(){
        airlineDao.getCurrentTransaction().rollback();
        airlineDao.closeCurrentSession();
        planeDao.getCurrentTransaction().rollback();
        planeDao.closeCurrentSession();
        ticketDao.getCurrentTransaction().rollback();
        ticketDao.closeCurrentSession();
        airportDao.getCurrentTransaction().rollback();
        airportDao.closeCurrentSession();
        flightDao.getCurrentTransaction().rollback();
        flightDao.closeCurrentSession();
        luggageDao.getCurrentTransaction().rollback();
        luggageDao.closeCurrentSession();
        passengerDao.getCurrentTransaction().rollback();
        passengerDao.closeCurrentSession();
    }
    @Test
    @Order(0)
    void saveTicket() {

        ticketDao.setCurrentSession(flightDao.getCurrentSession());
        ticketDao.setCurrentTransaction(flightDao.getCurrentTransaction());
        ticket = new Ticket();
        ticket.setFlight(flight);
        ticket.setPassenger(passenger);
        ticket = ticketDao.getTicketById(1L);
        assertEquals(passenger, ticket.getPassenger());

    }

    @Test
    @Order(1)
    void getTicketById() {
        assertEquals(ticket.getId(), ticketDao.getTicketById(1L).getId());
    }

    @Test
    @Order(3)
    void getTicketWithLuggages() {
        luggageDao.setCurrentSession(ticketDao.getCurrentSession());
        luggageDao.setCurrentTransaction(ticketDao.getCurrentTransaction());
        luggage = new Luggage("A1", 70F, 80);
        luggage.setTicket(ticket);
        luggageDao.saveLuggage(luggage);
        assertEquals(luggage.getCode(), luggageDao.getLuggageByCode("A1").getCode());
    }

    @Test
    @Order(2)
    void getAllTicket() {
        assertEquals(ticket.getId(), ticketDao.getAllTicket().get(0).getId());
    }

    @Test
    @Order(4)
    void updateTicket() {
        ticketDao.setCurrentSession(luggageDao.getCurrentSession());
        ticketDao.setCurrentTransaction(luggageDao.getCurrentTransaction());
        ticket.getPassenger().setName("Name2");
        ticketDao.updateTicket(ticket);
        assertEquals(ticket.getPassenger().getName(), ticketDao.getTicketById(1L).getPassenger().getName());
    }
}