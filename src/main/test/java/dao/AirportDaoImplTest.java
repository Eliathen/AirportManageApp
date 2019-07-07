package dao;

import entity.Airport;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AirportDaoImplTest {
    public static AirportDaoImpl airportDao;
    private Long id;

    @BeforeAll
    public static void init(){
        assertDoesNotThrow(()->{
            airportDao  = new AirportDaoImpl();
            airportDao.openCurrentSessionWithTransaction();
        });
    }
    @AfterAll
    public static void close(){
        airportDao.getCurrentTransaction().rollback();
    }

    @Test
    @Order(0)
    void saveAirport() {
        Airport airport = new Airport(1L,"Airport Hamburg","Flughafenstr. 1-3, 22335 Hamburg", "Hamburg");
        assertDoesNotThrow(()->{
            airportDao.saveAirport(airport);
        });
        assertEquals(airport.getId(), airportDao.getAirportById(airport.getId()).getId());
    }

    @Test
    @Order(3)
    void removeAirportById() {
        airportDao.removeAirportById(1L);
        assertNull(airportDao.getAirportById(1L));
    }

    @Test
    @Order(1)
    void getAirportById() {
        Airport airport = airportDao.getAirportById(1L);
        assertEquals(1L, airport.getId());
    }

    @Test
    @Order(1)
    void getAllAirport() {
        List<Airport> airportList = airportDao.getAllAirport();
        assertEquals(1L, airportList.get(0).getId());
    }

    @Test
    @Order(2)
    void updateAirport() {
        Airport airport = airportDao.getAirportById(1L);
        airport.setCity("Berlin");
        assertDoesNotThrow(()->{
            airportDao.updateAirport(airport);
        });
        assertEquals("Berlin", airportDao.getAirportById(1L).getCity());
    }
}
