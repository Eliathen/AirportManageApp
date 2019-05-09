package dao;

import api.UserDaoInterface;
import entity.User;
import exceptions.LoginAlreadyExistException;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.query.Query;
import exceptions.LoginAlreadyExistException;

import java.util.ArrayList;
import java.util.List;

public class UserDao extends BaseDao implements UserDaoInterface {

    public UserDao(){
    }
    public User getById(Long id){
        Query query = getCurrentSession().createQuery("From User where id=:id");
        query.setParameter("id", id);
        User user = (User) query.uniqueResult();
        System.out.println(user.toString());
        return user;
    }

    public User getByLogin(String login){
        Query query = getCurrentSession().createQuery("From User where login =: login");
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
    public boolean isCorrectLoginAndPassword(String login, String password){
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
            query = getCurrentSession().createQuery(q);
            List<User> users = query.getResultList();
            return users;
        }catch (Exception e){
            System.err.println("SELECT FROM USER ERROR");
            return new ArrayList<User>();
        }
    }
}
