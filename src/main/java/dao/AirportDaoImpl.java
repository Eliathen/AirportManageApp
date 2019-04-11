package dao;

import api.AirportDao;
import entity.Airport;

import java.sql.*;
import java.util.LinkedList;
import java.sql.SQLException;
import java.util.List;

public class AirportDaoImpl extends BaseDao implements AirportDao {

    public AirportDaoImpl(){
        super();
    }

    public void saveAirport(Airport airport) {
        try{
            PreparedStatement prepStmt = connection.prepareStatement("INSERT INTO Airport (NAME, ADDRESS, CITY)  VALUES(?,?,?)");
            prepStmt.setString(1,airport.getName());
            prepStmt.setString(2,airport.getAddress());
            prepStmt.setString(3,airport.getCity());

            prepStmt.execute();
            prepStmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void removeAirportById(Long airportId){
        try{
            PreparedStatement prepStmt = connection.prepareStatement("DELETE FROM AIRPORT WHERE ID = ?");
            prepStmt.setLong(1, airportId);
            prepStmt.execute();
            prepStmt.close();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public List<Airport> getAllAirport(){
        List<Airport> airports = new LinkedList<Airport>();
        try{
            ResultSet result = statement.executeQuery("SELECT * FROM AIRPORT");
            Integer id;
            String name, address, city;
            while(result.next()){
                id = result.getInt("id");
                name = result.getString("name");
                address = result.getString("address");
                city = result.getString("city");

                Airport airport = new Airport(id, name, address, city);
                airports.add(airport);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return airports;
    }

    public void updateAirport(Airport airport){
        try{
            PreparedStatement prepStmt = connection.prepareStatement("UPDATE AIRPORT SET name = ? address = ? city = ? WHERE id = ?");
            prepStmt.setString(1, airport.getName());
            prepStmt.setString(2,airport.getAddress());
            prepStmt.setString(3, airport.getCity());
            prepStmt.setInt(4, airport.getId());

            prepStmt.execute();
            prepStmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
