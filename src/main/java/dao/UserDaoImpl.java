package dao;

import api.UserDao;
import entity.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.sql.SQLException;

public class UserDaoImpl extends baseDao implements UserDao {

    public UserDaoImpl() {
        super();
    }

    public void saveUser(User user) {
        try{
            PreparedStatement prepStmt = connection.prepareStatement("INSERT INTO USER (login, password) VALUES (?,?)");
            prepStmt.setString(1, user.getLogin());
            prepStmt.setString(2,user.getPassword());

            prepStmt.execute();
            prepStmt.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void removeUserById(Long userId) {
        try{
            PreparedStatement prepStmt = connection.prepareStatement("DELETE FROM USER WHERE ID = ?");
            prepStmt.setLong(1, userId);
            prepStmt.execute();
            prepStmt.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void removeUserByLogin(String login) {
        try{
            PreparedStatement prepStmt = connection.prepareStatement("DELETE FROM USER WHERE login = ?");
            prepStmt.setString(1, login);

            prepStmt.execute();
            prepStmt.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new LinkedList<User>();
        try{
            ResultSet result = statement.executeQuery("SELECT * FROM USER");
            Long id;
            String login, password;
            while(result.next()){
                id = result.getLong("id");
                login = result.getString("login");
                password = result.getString("password");

                User user = new User (id,login,password);
                users.add(user);
            }
            statement.close();

        }catch(SQLException e){
            e.printStackTrace();
        }
        return users;
    }

    public void updateUser(User user) {
        try {
            PreparedStatement prepStmt;
            prepStmt = connection.prepareStatement("UPDATE USER SET login = ? password = ? WHERE id = ?");

            prepStmt.setString(1,user.getLogin());
            prepStmt.setString(2,user.getPassword());
            prepStmt.setLong(3,user.getId());

            prepStmt.execute();
            prepStmt.close();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
