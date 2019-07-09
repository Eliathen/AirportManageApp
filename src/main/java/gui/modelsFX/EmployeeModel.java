package gui.modelsFX;

import entity.Employee;
import exceptions.ApplicationException;
import exceptions.EmployeeAlreadyExistException;

import gui.converters.AirlineConvertTo;
import gui.converters.EmployeeConvertTo;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import services.EmployeeService;

public class EmployeeModel {

    private ObjectProperty<EmployeeFX> employeeFXObjectProperty = new SimpleObjectProperty<>(new EmployeeFX());

    public EmployeeFX getEmployeeFXObjectProperty() {
        return employeeFXObjectProperty.get();
    }

    public ObjectProperty<EmployeeFX> employeeFXObjectPropertyProperty() {
        return employeeFXObjectProperty;
    }

    public void setEmployeeFXObjectProperty(EmployeeFX employeeFXObjectProperty) {
        this.employeeFXObjectProperty.set(employeeFXObjectProperty);
    }

    public void saveEmployee() throws EmployeeAlreadyExistException, ApplicationException {
        Employee employee = EmployeeConvertTo.convertToEmployee(employeeFXObjectProperty.getValue());
        employee.setAirline(AirlineConvertTo.convertToAirline(employeeFXObjectProperty.getValue().getAirlineFX()));
        EmployeeService employeeService = new EmployeeService();
        employeeService.saveEmployee(employee);
    }

    public void updateEmployee() throws ApplicationException {
        Employee employee = EmployeeConvertTo.convertToEmployee(employeeFXObjectProperty.getValue());
        employee.setAirline(AirlineConvertTo.convertToAirline(employeeFXObjectProperty.getValue().getAirlineFX()));
        EmployeeService employeeService = new EmployeeService();
        employeeService.updateEmployee(employee);
    }
}
