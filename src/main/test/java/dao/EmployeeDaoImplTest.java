package dao;

import entity.Airline;
import entity.Employee;
import exceptions.ApplicationException;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeDaoImplTest {
    private static EmployeeDaoImpl employeeDao;
    private static AirlineDaoImpl airlineDao;
    @BeforeAll
    public static void init(){
        assertDoesNotThrow(()->{
            employeeDao  = new EmployeeDaoImpl();
            airlineDao = new AirlineDaoImpl();
        });
    }
    @AfterAll
    public static void close(){
        employeeDao.getCurrentTransaction().rollback();
    }
    @Test
    @Order(0)
    void saveEmployee() {
        Airline airline = new Airline(1L,"Airline1", "Country1", "otherDetials1");
        assertDoesNotThrow(()->{
            airlineDao.openCurrentSessionWithTransaction();
            airlineDao.saveAirline(airline);
        });
        Airline airline1 = airlineDao.getAirlineById(1L);
        airlineDao.getCurrentSession().close();
        try {
            employeeDao.openCurrentSessionWithTransaction();
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
        System.out.println(airline1);
        Employee employee = new Employee("Name1", "Surname1", "46080912158", "Pilot", 8900F);
        employee.setAirline(airline1);
        System.out.println(employee);
        System.out.println(employee.getAirline());
        assertDoesNotThrow(()-> employeeDao.saveEmployee(employee));
        assertEquals(employee.getName(), employeeDao.getEmployeeByPesel("46080912158").getName());

    }

    @Test
    @Order(3)
    void removeEmployeeById() {
        Employee employee = employeeDao.getEmployeeByPesel("46080912158");
        employeeDao.removeEmployeeById(employee);
        assertNull(employeeDao.getEmployeeByPesel("46080912158").getPesel());

    }

    @Test
    @Order(1)
    void getEmployeeByPesel() {
        Employee employee = employeeDao.getEmployeeByPesel("46080912158");
        assertEquals("46080912158", employee.getPesel());
    }

    @Test
    @Order(2)
    void updateEmployee() {
        Employee employee = employeeDao.getEmployeeByPesel("46080912158");
        employee.setName("Name2");
        assertEquals("Name2", employeeDao.getEmployeeByPesel("46080912158").getName());
    }
}