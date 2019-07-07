package api;
import entity.User;
import exceptions.LoginAlreadyExistException;

import java.io.Serializable;
import java.util.List;


public interface UserDao extends Serializable {
     
     void saveUser(User user) throws LoginAlreadyExistException;

     User getByLogin(String login);

     boolean isCorrectLoginAndPassword(String login, String password);
}