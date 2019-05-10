package api;

import entity.Employee;

import java.io.Serializable;
import java.util.List;

public interface EmployeeDao extends Serializable {

    void saveEmployee(Employee employee);

    void removeEmployeeById(Long id);

    List<Employee> getAllEmployee();
    List<Employee> getAllEmployeeByOccupation(String occupation);

    void updateFlight(Employee employee);
}
