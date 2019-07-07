package dao;

import api.EmployeeDao;
import entity.*;
import exceptions.ApplicationException;
import exceptions.EmployeeAlreadyExistException;
import exceptions.PassengerAlreadyExistException;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class FlightDaoImplTest {

    private static FlightDaoImpl flightDao = new FlightDaoImpl();
    private static AirlineDaoImpl airlineDao = new AirlineDaoImpl();
    private static AirportDaoImpl airportDao = new AirportDaoImpl();
    private static PlaneDaoImpl planeDao = new PlaneDaoImpl();
    private static Airline airline;
    private static Plane plane;
    private static Airport airport;
    private static Flight flight;

    @BeforeAll
    static void init(){
        try {
            airlineDao.openCurrentSessionWithTransaction();
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
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

    }
    @Test
    @Order(0)
    void saveFlight() {
        flight = new Flight(airport, airport, LocalDateTime.now(), plane);
        flightDao.setCurrentSession(airlineDao.getCurrentSession());
        flightDao.setCurrentTransaction(airlineDao.getCurrentTransaction());
        flightDao.saveFlight(flight);
        assertEquals(flight.getId(), flightDao.getAllFlight().get(0).getId());
        flight = flightDao.getAllFlight().get(0);

    }
    @Test
    @Order(2)
    void getEmployees() {
        Employee employee = new Employee("Name1", "Surname1", "46080912158", "Pilot", 8900F);
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        employee.setAirline(airline);
        employeeDao.setCurrentSession(flightDao.getCurrentSession());
        employeeDao.setCurrentTransaction(flightDao.getCurrentTransaction());
        try {
            System.out.println(employee);
            System.out.println(employee.getAirline());
            System.out.println(employeeDao);
            employeeDao.saveEmployee(employee);
        } catch (EmployeeAlreadyExistException e) {
            e.printStackTrace();
        }
        flightDao.setCurrentSession(employeeDao.getCurrentSession());
        flightDao.setCurrentTransaction(employeeDao.getCurrentTransaction());
        List<Employee> employees = new LinkedList<>();
        employees.add(employee);
        flight.setEmployees(employees);
        flightDao.updateFlight(flight);
        flight.getEmployees().add(employeeDao.getEmployeeByPesel("46080912158"));
        flightDao.updateFlight(flight);
        assertEquals(1L, flightDao.getAllFlight().get(0).getEmployees().get(0).getId());
    }

    @Test
    @Order(1)
    void getAllFlight() {
        List<Flight> flightList = flightDao.getAllFlight();
        assertEquals(flight.getId(), flightList.get(0).getId());
    }
    @Test
    @Order(3)
    void updateFlight() {
        flight = flightDao.getAllFlight().get(0);
        LocalDateTime dateTime = LocalDateTime.now().minusDays(1);
        flight.setInitialDate(dateTime);
        flightDao.updateFlight(flight);
        assertEquals(dateTime, flightDao.getAllFlight().get(0).getInitialDate());
    }
}