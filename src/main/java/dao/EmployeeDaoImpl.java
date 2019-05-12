package dao;

import api.EmployeeDao;
import entity.Employee;
import org.hibernate.query.Query;

import java.util.LinkedList;
import java.util.List;

public class EmployeeDaoImpl extends BaseDao implements EmployeeDao {

    public void saveEmployee(Employee employee){
        if(isEmployeeAlreadyExist(employee.getPesel()) == false){
            getCurrentSession().save(employee);
        }
    }

    public void removeEmployeeById(Employee employee){
        getCurrentSession().delete(employee);
    }
    private boolean isEmployeeAlreadyExist(String pesel){
            if(getEmployeeByPesel(pesel)!=null) {
                System.out.println("Zwracam true");
                return true;
            } else {
                System.out.println("Zwracam false");
                return false;
            }
    }
    public Employee getFlights(Employee employee){
        try{
            Query query = getCurrentSession().createQuery("FROM Flight WHERE Id IN (SELECT flightId FROM Flight_Employee WHERE employeeId =: employeeId)");
            query.setParameter("employeeId", employee.getId());
            employee.setFlights(query.getResultList());
            return employee;
        }catch (NullPointerException e){
            return employee;
        }
    }
    public Employee getEmployeeByPesel(String pesel){
        try{
            Query query = getCurrentSession().createQuery("FROM Employee WHERE pesel =: pesel");
            query.setParameter("pesel", pesel);
            Employee employee = (Employee) query.uniqueResult();
            System.out.println("Wypisuje pobranego pracownika: " + employee);
            return employee;
        }catch(NullPointerException e){
            return null;
        }
    }
    public List<Employee> getAllEmployee(){
        try{
            Query query = getCurrentSession().createQuery("FROM Employee");
            List<Employee> employees = query.getResultList();
            return employees;
        }catch(NullPointerException e){
            return new LinkedList<Employee>();
        }
    }
    public List<Employee> getAllEmployeeByOccupation(String occupation){
        try{
            Query query = getCurrentSession().createQuery("FROM Employee WHERE occupation =: occupation");
            query.setParameter("occupation", occupation);
            List<Employee> employees = query.getResultList();
            return employees;
        }catch(NullPointerException e){
            return new LinkedList<Employee>();
        }
    }

    public void updateEmployee(Employee employee){
        getCurrentSession().saveOrUpdate(employee);
    }
}
