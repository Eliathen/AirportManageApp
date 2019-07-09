package gui.converters;

import entity.Employee;
import gui.modelsFX.EmployeeFX;


public class EmployeeConvertTo {

    public static EmployeeFX convertToEmpolyeeFX(Employee employee){
        EmployeeFX employeeFX = new EmployeeFX();
        employeeFX.setId(employee.getId());
        employeeFX.setName(employee.getName());
        employeeFX.setSurname(employee.getSurname());
        employeeFX.setOccupation(employee.getOccupation());
        employeeFX.setPesel(employee.getPesel());
        employeeFX.setSalary(employee.getSalary());
        employeeFX.setAirlineFX(AirlineConvertTo.convertToAirlineFX(employee.getAirline()));
        return employeeFX;
    }

    public static Employee convertToEmployee(EmployeeFX employeeFX){
        Employee employee = new Employee();
        employee.setId(employeeFX.getId());
        employee.setName(employeeFX.getName());
        employee.setSurname(employeeFX.getSurname());
        employee.setOccupation(employeeFX.getOccupation());
        employee.setPesel(employeeFX.getPesel());
        employee.setSalary(employeeFX.getSalary());
        employee.setAirline(AirlineConvertTo.convertToAirline(employeeFX.getAirlineFX()));
        return employee;
    }
}
