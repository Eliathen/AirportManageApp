package dao;

import entity.Airline;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class AirlineDaoImpl extends BaseDao {

    public AirlineDaoImpl(){
        super();
    }

    public void saveAirline(Airline airline){
        try{
            PreparedStatement prepStmt = connection.prepareStatement("INSERT INTO AIRLINE (NAME, COUNTRY, OTHERDATAILS) VALUES(?,?,?)");
            prepStmt.setString(1,airline.getName());
            prepStmt.setString(2,airline.getCountry());
            prepStmt.setString(3,airline.getDetails());

            prepStmt.execute();
            prepStmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }

    }
    public void removeAirlineById(Long id){
        try{
            PreparedStatement prepStmt = connection.prepareStatement("DELETE FROM AIRLINE WHERE ID = ?");
            prepStmt.setLong(1,id);

            prepStmt.execute();
            prepStmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public List<Airline> getAllAirline(){
        List<Airline> airlines = new LinkedList<Airline>();
        try{
            String query = "SELECT * FROM AIRLINE";
            ResultSet result = statement.executeQuery(query);
            Long id;
            String name, country, details;
            while(result.next()){
                id = result.getLong("id");
                name = result.getString("name");
                country = result.getString("country");
                details = result.getString("details");

                Airline airline = new Airline(id,name,country,details);
                airlines.add(airline);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return airlines;
    }

}
