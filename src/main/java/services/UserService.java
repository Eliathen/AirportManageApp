package services;

import dao.UserDaoImpl;
import entity.User;
import exceptions.ApplicationException;
import exceptions.LoginAlreadyExistException;

import java.util.List;


public class UserService {

    private static UserDaoImpl userDao;

    public UserService() {
        userDao = new UserDaoImpl();
    }

    public void saveUser(User user) throws LoginAlreadyExistException, ApplicationException {
        userDao.openCurrentSessionWithTransaction();
        userDao.saveUser(user);
        userDao.closeCurrentSessionWithTransaction();
    }

    public boolean isCorrectLoginAndPassword(String login, String password) throws ApplicationException {
        userDao.openCurrentSession();
        boolean result = userDao.isCorrectLoginAndPassword(login, password);
        userDao.closeCurrentSession();
        return result;
    }
}
