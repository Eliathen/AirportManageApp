package gui.converters;

import entity.Airline;
import entity.Employee;
import gui.modelsFX.AirlineFX;
import gui.modelsFX.EmployeeFX;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeConvertToTest {

    @Test
    void convertToEmpolyeeFX() {
        Employee employee = new Employee();
        employee.setName("Name1");
        employee.setId(1L);
        employee.setPesel("42060327215");
        employee.setSalary(1000F);
        employee.setSurname("Surname");
        Airline airline = new Airline();
        airline.setId(1L);
        employee.setAirline(airline);
        EmployeeFX employeeFX = EmployeeConvertTo.convertToEmpolyeeFX(employee);
        assertNotNull(employeeFX);
        assertEquals(employee.getName(), employeeFX.getName());
        assertEquals(employee.getPesel(), employeeFX.getPesel());
        assertEquals(employee.getSalary(), employeeFX.getSalary());
    }

    @Test
    void convertToEmployee() {
        EmployeeFX employeeFX = new EmployeeFX();
        employeeFX.setName("Name1");
        employeeFX.setId(1L);
        employeeFX.setPesel("42060327215");
        employeeFX.setSalary(1000F);
        employeeFX.setSurname("Surname");
        employeeFX.setAirlineFX(new AirlineFX());
        Employee employee = EmployeeConvertTo.convertToEmployee(employeeFX);
        assertNotNull(employeeFX);
        assertEquals(employeeFX.getName(), employee.getName());
        assertEquals(employeeFX.getPesel(), employee.getPesel());
        assertEquals(employeeFX.getSalary(), employee.getSalary());
    }
}