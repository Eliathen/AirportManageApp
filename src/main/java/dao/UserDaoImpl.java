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
        return user.getLogin() != null;
    }
    public boolean isCorrectLoginAndPassword(String login, String password){
        User user = getByLogin(login) ;
        if(user == null || login == null || password == null || user.getLogin()==null || user.getPassword()==null){
            return false;
        }
        boolean isLoginCorrent = user.getLogin().equals(login);
        boolean isPasswordCorrent = user.getPassword().equals(password);

        return isLoginCorrent && isPasswordCorrent;
    }
}
