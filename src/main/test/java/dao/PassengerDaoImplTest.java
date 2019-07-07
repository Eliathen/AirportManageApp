package dao;

import entity.Passenger;
import exceptions.ApplicationException;
import exceptions.PassengerAlreadyExistException;
import org.junit.jupiter.api.*;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PassengerDaoImplTest{
   public static PassengerDaoImpl passengerDao;
   private Long id;

    @BeforeAll
    public static void init(){
        assertDoesNotThrow(()->{
            passengerDao  = new PassengerDaoImpl();
            passengerDao.openCurrentSessionWithTransaction();
        });
    }
    @AfterAll
    public static void close(){
        passengerDao.getCurrentTransaction().rollback();
    }
    @Test
    @Order(1)
    public void isPassengerAlreadyExists() {
        assertFalse(passengerDao.isPassengerAlreadyExists("41010244430"));
    }

    @Transactional
    @Test
    @Order(0)
    public void savePassenger(){
        Passenger passenger = new Passenger();
        passenger.setPesel("41010244431");
        passenger.setName("John");
        passenger.setSurname("Jonhson");
        assertDoesNotThrow(()->{
            passengerDao.savePassenger(passenger);
        });
        assertEquals(passenger.getId(), passengerDao.getByPesel("41010244431").getId());
    }
    @Test
    @Order(2)
    public void getById() {
        Passenger passenger = passengerDao.getById(id);
        assertEquals(id,(Object) passenger.getId());
    }
    @Test
    @Order(1)
    public void getByPesel(){
        Passenger passenger = passengerDao.getByPesel("41010244431");
        id = passenger.getId();
        assertEquals("41010244431", passenger.getPesel());
    }

    @Test
    @Transactional
    @Order(3)
    public void updatePassenger(){
        Passenger passenger = passengerDao.getByPesel("41010244431");
        passenger.setPesel("23040383193");
        assertDoesNotThrow(()->{
            passengerDao.updatePassenger(passenger);
        });
        assertEquals("23040383193", passengerDao.getByPesel("23040383193").getPesel());
    }

    @Test
    @Order(1)
    public void getAllPassenger() {
        List<Passenger> passengers = passengerDao.getAllPassenger();
        assertEquals("41010244431", passengers.get(0).getPesel());
    }

    @Test
    @Order(4)
    public void removePassengerByPesel() {
        passengerDao.removePassengerByPesel("23040383193");
        assertNull(passengerDao.getByPesel("23040383193").getPesel());
    }

}