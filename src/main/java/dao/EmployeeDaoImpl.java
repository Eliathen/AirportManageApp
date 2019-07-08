package dao;

import api.EmployeeDao;
import entity.Employee;
import exceptions.EmployeeAlreadyExistException;
import org.hibernate.query.Query;

import java.util.LinkedList;
import java.util.List;

public class EmployeeDaoImpl extends BaseDao implements EmployeeDao {

    public void saveEmployee(Employee employee) throws EmployeeAlreadyExistException {
        if(!isEmployeeAlreadyExist(employee.getPesel())){
            getCurrentSession().save(employee);

        }else{
            throw new EmployeeAlreadyExistException("Employee exists in Database");
        }
    }

    public void removeEmployeeById(Employee employee){
        getCurrentSession().delete(employee);
    }

    private boolean isEmployeeAlreadyExist(String pesel){
            Employee employee = getEmployeeByPesel(pesel);
            if(employee.getPesel()==null) {
                return false;
            } else {
                return true;
            }
    }

    public Employee getEmployeeByPesel(String pesel){
        try{
            Query query = getCurrentSession().createQuery("FROM Employee WHERE pesel =: pesel");
            query.setParameter("pesel", pesel);
            Employee employee = (Employee) query.uniqueResult();
            if(employee == null) {
                return new Employee();
            }
            return employee;
        }catch(NullPointerException e){
            return new Employee();
        }
    }

    public void updateEmployee(Employee employee){
        getCurrentSession().saveOrUpdate(employee);
    }
}
