package api;
import entity.User;

import java.io.Serializable;
import java.util.List;


public interface UserDaoInterface extends Serializable {
    public void saveUser(User user);
    public User getById(Long id);
    public User getByLogin(String login);
    public void removeUserById(Long userId);
    public void removeUserByLogin(String login);

    public List<User> getAllUsers();

    public void updateUser(User user);
}