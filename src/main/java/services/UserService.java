package services;

import dao.UserDao;
import entity.User;
import exceptions.LoginAlreadyExistException;

import java.util.List;


public class UserService {
    private static UserDao userDao;

    public UserService() {
        userDao = new UserDao();
    }

    public void saveUser(User user) throws LoginAlreadyExistException {
        userDao.openCurrentSessionWithTransaction();
        userDao.saveUser(user);
        userDao.closeCurrentSessionWithTransaction();
    }
    public void removeUserById(Long userId){
        userDao.openCurrentSessionWithTransaction();
        userDao.removeUserById(userId);
        userDao.closeCurrentSessionWithTransaction();
    }
    public void removeUserByLogin(String login){
        userDao.openCurrentSessionWithTransaction();
        userDao.removeUserByLogin(login);
        userDao.closeCurrentSessionWithTransaction();
    }
    public void updateUser(User user){
        userDao.openCurrentSessionWithTransaction();
        userDao.updateUser(user);
        userDao.closeCurrentSessionWithTransaction();
    }
    public boolean isCorrectLoginAndPassword(String login, String password){
        userDao.openCurrentSession();
        boolean result = userDao.isCorrectLoginAndPassword(login, password);
        userDao.closeCurrentSession();
        return result;
    }
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        userDao.openCurrentSession();
        List <User> users = userDao.getAllUsers();
        userDao.closeCurrentSession();
        return users;
    }
}
