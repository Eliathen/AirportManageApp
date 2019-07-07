package dao;

import entity.Airline;
import entity.Employee;
import entity.Plane;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AirlineDaoImplTest {
    private static AirlineDaoImpl airlineDao;
    private static PlaneDaoImpl planeDao;
    private static EmployeeDaoImpl employeeDao;
    private  static Employee employee;
    private   static Plane plane;

    @BeforeAll
    static void init(){
        assertDoesNotThrow(()->{
        airlineDao = new AirlineDaoImpl();
        planeDao = new PlaneDaoImpl();
        plane = new Plane("reg1", 123, "plane1", 70, 1000 );
        employeeDao = new EmployeeDaoImpl();
        employee = new Employee("Name1", "Surname1", "46080912158", "Pilot", 8900F);
        airlineDao.openCurrentSessionWithTransaction();
        });
    }
    @AfterAll
    static void close(){
        airlineDao.getCurrentTransaction().rollback();
        airlineDao.closeCurrentSession();
        planeDao.getCurrentTransaction().rollback();
        planeDao.closeCurrentSession();
        employeeDao.getCurrentTransaction().rollback();
        employeeDao.closeCurrentSession();
    }
    @Test
    @Order(0)
    void saveAirline() {
        assertDoesNotThrow(()->{
            airlineDao = new AirlineDaoImpl();
            airlineDao.openCurrentSessionWithTransaction();
        });
        Airline airline = new Airline(1L,"Airline1", "Country1", "otherDetials1");
        assertDoesNotThrow(()-> airlineDao.saveAirline(airline));
        assertEquals(airline.getId(), airlineDao.getAirlineById(airline.getId()).getId());
    }

    @Test
    @Order(1)
    void getAirlineById() {
        Airline airline = airlineDao.getAirlineById(1L);
        assertEquals(1L, airline.getId());
    }

    @Test
    @Order(5)
    void removeAirlineById() {
        airlineDao.removeAirlineById(1L);
        assertNull(airlineDao.getAirlineById(1L));
    }

    @Test
    @Order(2)
    void getAllAirline() {
        List<Airline> airlineList = airlineDao.getAllAirline();

        assertEquals(1L, airlineList.get(0).getId());
    }

    @Test
    @Order(4)
    void updateAirline() {
        Airline airline = airlineDao.getAirlineById(1L);
        airline.setCountry("Spain");
        airlineDao.updateAirline(airline);
        assertEquals("Spain", airlineDao.getAirlineById(1L).getCountry());
    }

    @Test
    @Order(2)
    void getPlanes() {
        plane.setAirline(airlineDao.getAirlineById(1L));
        planeDao.setCurrentSession(airlineDao.getCurrentSession());
        planeDao.setCurrentTransaction(airlineDao.getCurrentTransaction());
        planeDao.savePlane(plane);
        airlineDao.setCurrentSession(airlineDao.getCurrentSession());
        Airline airline = airlineDao.getAirlineById(1L);
        airline = airlineDao.getPlanes(airline);
        assertEquals("reg1", airline.getPlanes().get(0).getRegistrationNumber());
    }

    @Test
    @Order(3)
    void getEmployees() {
        employee.setAirline(airlineDao.getAirlineById(1L));
        employeeDao.setCurrentSession(airlineDao.getCurrentSession());
        employeeDao.setCurrentTransaction(airlineDao.getCurrentTransaction());
        assertDoesNotThrow(()-> employeeDao.saveEmployee(employee));
        airlineDao.setCurrentSession(planeDao.getCurrentSession());
        Airline airline = airlineDao.getAirlineById(1L);
        airline = airlineDao.getEmployees(airline);
        assertEquals("46080912158", airline.getEmployees().get(0).getPesel());

    }
}