import java.lang.String;

import api.*;
import dao.AirportDaoImpl;
import dao.UserDaoImpl;
import entity.*;
import java.util.List;

public class runApp {

    public static void main(String[] args){
        /*List<User> users;
        UserDaoImpl user = new UserDaoImpl();
        User userek = new User("Loginek", "haselko");
        user.saveUser(userek);
        users = user.getAllUsers();
        for(User us: users){
            System.out.println(us.toString());
        }*/

        List<Airport> airports;
        AirportDaoImpl airport = new AirportDaoImpl();
        //Airport porterek = new Airport(1,"Lotnisko1", "Tu jest ulica", "Berlin");
        //airport.saveAirport(porterek);
        airports = airport.getAllAirport();
        //airport.removeAirportById(1l);
        //airport.removeAirportById(2l);
        for(Airport ai: airports){
            System.out.println(ai.toString());
        }


    }
}
