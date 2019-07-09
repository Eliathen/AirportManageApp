package services;

import dao.EmployeeDaoImpl;
import entity.Employee;
import exceptions.ApplicationException;
import exceptions.EmployeeAlreadyExistException;

import java.util.List;

public class EmployeeService {

    private EmployeeDaoImpl employeeDao;

    public EmployeeService() {
        employeeDao = new EmployeeDaoImpl();
    }

    public void saveEmployee(Employee employee) throws EmployeeAlreadyExistException, ApplicationException {
        employeeDao.openCurrentSessionWithTransaction();
        employeeDao.saveEmployee(employee);
        employeeDao.closeCurrentSessionWithTransaction();
    }

    public void removeEmployee(Employee employee) throws ApplicationException {
        employeeDao.openCurrentSessionWithTransaction();
        employeeDao.removeEmployeeById(employee);
        employeeDao.closeCurrentSessionWithTransaction();
    }

    public void updateEmployee(Employee employee) throws ApplicationException {
        employeeDao.openCurrentSessionWithTransaction();
        employeeDao.updateEmployee(employee);
        employeeDao.closeCurrentSessionWithTransaction();
    }
}
