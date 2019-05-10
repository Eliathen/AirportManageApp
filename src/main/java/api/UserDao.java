package api;
import entity.User;
import exceptions.LoginAlreadyExistException;

import java.io.Serializable;
import java.util.List;


public interface UserDao extends Serializable {
     void saveUser(User user) throws LoginAlreadyExistException;
     User getById(Long id);
     User getByLogin(String login);
     void removeUserById(Long userId);
     void removeUserByLogin(String login);

     List<User> getAllUsers();

     void updateUser(User user);

     boolean isCorrectLoginAndPassword(String login, String password);
}