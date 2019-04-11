package dao;

import api.LuggageDao;
import entity.Luggage;

import java.sql.*;
import java.util.LinkedList;
import java.sql.SQLException;
import java.util.List;

public class LuggageDaoImpl extends BaseDao implements LuggageDao {

    public LuggageDaoImpl(){
        super();
    }

    public void saveLuggage(Luggage luggage) {
        try{
            PreparedStatement prepStmt = connection.prepareStatement("INSERT INTO LUGGAGE (CODE, WEIGHT, HEIGHT)  VALUES(?,?,?)");
            prepStmt.setString(1,luggage.getCode());
            prepStmt.setLong(2,luggage.getHeight());
            prepStmt.setInt(3,luggage.getHeight());

            prepStmt.execute();
            prepStmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void removeLuggageById(Long luggageId){
        try{
            PreparedStatement prepStmt = connection.prepareStatement("DELETE FROM LUGGAGE WHERE ID = ?");
            prepStmt.setLong(1, luggageId);
            prepStmt.execute();
            prepStmt.close();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void removeLuggageByCode(String code){
        try{
            PreparedStatement prepStmt = connection.prepareStatement("DELETE FROM LUGGAGE WHERE code LIKE ?");
            prepStmt.setString(1, code);
            prepStmt.execute();
            prepStmt.close();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public List<Luggage> getAllLuggage(){
        List<Luggage> luggages = new LinkedList<Luggage>();
        try{
            ResultSet result = statement.executeQuery("SELECT * FROM LUGGAGE");
            Long id;
            String code;
            Float weight;
            Integer height;
            while(result.next()){
                id = result.getLong("id");
                code = result.getString("name");
                weight = result.getFloat("address");
                height = result.getInt("city");

                Luggage luggage = new Luggage(id, code, weight, height);
                luggages.add(luggage);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return luggages;
    }

    public void updateLuggage(Luggage luggage){
        try{
            PreparedStatement prepStmt = connection.prepareStatement("UPDATE LUGGAGE SET CODE = ? WEIGHT = ? HEIGHT = ? WHERE id = ?");
            prepStmt.setString(1, luggage.getCode());
            prepStmt.setFloat(2,luggage.getWeight());
            prepStmt.setInt(3, luggage.getHeight());
            prepStmt.setLong(4, luggage.getId());

            prepStmt.execute();
            prepStmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
