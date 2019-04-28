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
        userDao.openCurrentSessionwithTransaction();
        userDao.saveUser(user);
        userDao.closeCurrentSessionwithTransaction();
    }
    public void removeUserById(Long userId){
        userDao.openCurrentSessionwithTransaction();
        userDao.removeUserById(userId);
        userDao.closeCurrentSessionwithTransaction();
    }
    public void removeUserByLogin(String login){
        userDao.openCurrentSessionwithTransaction();
        userDao.removeUserByLogin(login);
        userDao.closeCurrentSessionwithTransaction();
    }
    public void updateUser(User user){
        userDao.openCurrentSessionwithTransaction();
        userDao.updateUser(user);
        userDao.closeCurrentSessionwithTransaction();
    }
    public boolean isCorrentLoginAndPassword(String login, String password){
        userDao.openCurrentSession();
        boolean result = userDao.isCorrentLoginAndPassword(login, password);
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
