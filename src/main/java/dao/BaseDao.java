package dao;

import entity.*;
import exceptions.ApplicationException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public abstract class BaseDao {

    private Session currentSession;

    private Transaction currentTransaction;

    public BaseDao() {
    }

    public Session openCurrentSession() throws ApplicationException {
        try {
            currentSession = getSessionFactory().openSession();
            return currentSession;
        }catch(HibernateException e){
            e.printStackTrace();
        }
        return currentSession;
    }

    public void closeCurrentSession(){
        currentSession.close();
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public Session openCurrentSessionWithTransaction() throws ApplicationException {
        try {
            currentSession = getSessionFactory().openSession();
            currentTransaction = currentSession.beginTransaction();
            return currentSession;
        }catch(HibernateException e){
            throw new ApplicationException(e.getMessage());
        }
    }

    public void closeCurrentSessionWithTransaction(){
        currentTransaction.commit();
        currentSession.close();
    }

    private static SessionFactory getSessionFactory() throws ApplicationException {
        try {
            SessionFactory sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Airline.class)
                    .addAnnotatedClass(Airport.class)
                    .addAnnotatedClass(Employee.class)
                    .addAnnotatedClass(Flight.class)
                    .addAnnotatedClass(Luggage.class)
                    .addAnnotatedClass(Plane.class)
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Passenger.class)
                    .addAnnotatedClass(Ticket.class)
                    .buildSessionFactory();
            return sessionFactory;
        }catch(Exception e){
            throw new ApplicationException("Problem with connection with Database");
        }
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }
}
