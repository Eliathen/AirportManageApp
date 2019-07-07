package services;

import dao.EmployeeDaoImpl;
import entity.Employee;

import java.util.List;

public class EmployeeService {
    private EmployeeDaoImpl employeeDao;

    public EmployeeService() {
        employeeDao = new EmployeeDaoImpl();
    }

    public void saveEmployee(Employee employee){
        employeeDao.openCurrentSessionWithTransaction();
        employeeDao.saveEmployee(employee);
        employeeDao.closeCurrentSessionWithTransaction();
    }

    public void removeEmployee(Employee employee){
        employeeDao.openCurrentSessionWithTransaction();
        employeeDao.removeEmployeeById(employee);
        employeeDao.closeCurrentSessionWithTransaction();
    }
    public Employee getTickets(Employee employee){
        employeeDao.openCurrentSession();
        employee = employeeDao.getFlights(employee);
        employeeDao.closeCurrentSession();
        return employee;
    }

    public Employee getEmployeeByPesel(String pesel){
        employeeDao.openCurrentSession();
        Employee employee = employeeDao.getEmployeeByPesel(pesel);
        employeeDao.closeCurrentSession();
        return employee;
    }

    public List<Employee> getAllEmployee(){
        employeeDao.openCurrentSession();
        List<Employee> employees = employeeDao.getAllEmployee();
        employeeDao.closeCurrentSession();
        return employees;
    }

    public List<Employee> getAllEmployeeByOccupation(String occupation){
        employeeDao.openCurrentSession();
        List<Employee> employees = employeeDao.getAllEmployeeByOccupation(occupation);
        employeeDao.closeCurrentSession();
        return employees;
    }

    public void updateEmployee(Employee employee){
        employeeDao.openCurrentSessionWithTransaction();
        employeeDao.updateEmployee(employee);
        employeeDao.closeCurrentSessionWithTransaction();
    }
}
