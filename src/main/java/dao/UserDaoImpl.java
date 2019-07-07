package dao;

import entity.User;
import exceptions.LoginAlreadyExistException;
import org.hibernate.query.Query;
import org.hibernate.service.UnknownServiceException;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends BaseDao implements api.UserDao {

    public UserDaoImpl(){
    }
    public User getById(Long id){
        Query query = getCurrentSession().createQuery("From User where id=:id");
        query.setParameter("id", id);
        User user = (User) query.uniqueResult();
        System.out.println("getById: "+user.toString());
        return user;
    }

    public User getByLogin(String login) {
        try{
        Query query = getCurrentSession().createQuery("From User where login =: login");
        query.setParameter("login", login);
        User user = (User) query.uniqueResult();
        if(user == null){
            return new User();
        }
        return user;
        }catch(Exception e){
            return new User();
        }
    }
    public void saveUser(User user) throws LoginAlreadyExistException{
        if(!isLoginAlreadyExist(user.getLogin())) {
            getCurrentSession().save(user);
        }
        else {
            throw new LoginAlreadyExistException("Login exists");
        }

    }
    public boolean isLoginAlreadyExist(String login){
        User user = getByLogin(login);
        System.out.println("isLoginAlreadyExist: " + user.toString());
        if(user.getLogin()==null){
            return false;
        }
        return true;
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
