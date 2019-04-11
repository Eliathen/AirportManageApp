package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDao {

    protected Connection connection;
    protected Statement statement;
    public static final String DRIVER = "org.h2.Driver";
    public static final String DB_URL = "jdbc:h2:tcp://localhost/~/Airport";
    protected final String user = "admin";
    protected final String password = "";

    public BaseDao() {
        init();
    }

    public void init() {
        try {
            Class.forName(DRIVER);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(DB_URL, user, password);
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
