package api;

import entity.Employee;
import exceptions.EmployeeAlreadyExistException;

import java.io.Serializable;
import java.util.List;

public interface EmployeeDao extends Serializable {

    void saveEmployee(Employee employee) throws EmployeeAlreadyExistException;

    void removeEmployeeById(Employee employee);

    Employee getEmployeeByPesel(String pesel);

    void updateEmployee(Employee employee);
}
