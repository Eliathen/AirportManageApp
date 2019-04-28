package dao;

import api.UserDaoInterface;
import entity.User;
import exceptions.LoginAlreadyExistException;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.query.Query;
import exceptions.LoginAlreadyExistException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements UserDaoInterface {
    private Session currentSession;
    private Transaction currentTransaction;

    public UserDao(){
    }
    public Session openCurrentSession(){
        try {
            currentSession = getSessionFactory().openSession();
            return currentSession;
        }catch(HibernateException e){
            e.printStackTrace();
        }
        return currentSession;
    }
    public Session openCurrentSessionwithTransaction(){
        try {
            currentSession = getSessionFactory().openSession();
            currentTransaction = currentSession.beginTransaction();
            return currentSession;
        }catch(HibernateException e){
            e.printStackTrace();
        }
        return currentSession;
    }
    private static SessionFactory getSessionFactory() {
        try {
            SessionFactory sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
            return sessionFactory;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public void closeCurrentSession(){
        currentSession.close();
    }

    public Session getCurrentSession() {
        return currentSession;
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

    public void closeCurrentSessionwithTransaction(){
        currentTransaction.commit();
        currentSession.close();

    }
    public User getById(Long id){
        Query query = currentSession.createQuery("From User where id=:id");
        query.setParameter("id", id);
        User user = (User) query.uniqueResult();
        System.out.println(user.toString());
        return user;
    }

    public User getByLogin(String login){
        Query query = currentSession.createQuery("From User where login=:login");
        query.setParameter("login", login);
        User user = (User) query.uniqueResult();
        return user;
    }
    public void saveUser(User user) throws LoginAlreadyExistException{
        if(!isLoginAlreadyExist(user.getLogin())) {
            getCurrentSession().save(user);
        }
        else {
            throw new LoginAlreadyExistException("This login exists");
        }

    }
    public boolean isLoginAlreadyExist(String login){
        User user = getByLogin(login);
        if(user != null){
            return true;
        }
        return false;
    }
    public void removeUserById(Long userId){
        User user = getById(userId);
        getCurrentSession().delete(user);
    }
    public void removeUserByLogin(String login){
        User user = getByLogin(login);
        getCurrentSession().delete(user);
    }
    public void updateUser(User user){

        getCurrentSession().update(user);
    }
    public boolean isCorrentLoginAndPassword(String login, String password){
        User user = getByLogin(login);
        if(user == null){
            return false;
        }
        boolean isLoginCorrent = user.getLogin().equals(login);
        boolean isPasswordCorrent = user.getPassword().equals(password);

        return isLoginCorrent && isPasswordCorrent;
    }
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers(){
        try {
            String q = "FROM User";
            Query query;
            query = currentSession.createQuery(q);
            List<User> users = query.getResultList();
            return users;
        }catch (Exception e){
            System.err.println("SELECT FROM USER ERROR");
            return new ArrayList<User>();
        }
    }
}
