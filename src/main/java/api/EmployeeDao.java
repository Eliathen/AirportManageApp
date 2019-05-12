package api;

import entity.Employee;

import java.io.Serializable;
import java.util.List;

public interface EmployeeDao extends Serializable {

    void saveEmployee(Employee employee);

    void removeEmployeeById(Employee employee);

    Employee getFlights(Employee employee);

    Employee getEmployeeByPesel(String pesel);

    List<Employee> getAllEmployee();

    List<Employee> getAllEmployeeByOccupation(String occupation);

    void updateEmployee(Employee employee);
}
