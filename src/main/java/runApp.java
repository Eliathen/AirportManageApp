import java.lang.String;
import java.util.List;

import entity.*;
import services.*;

public class runApp {

    public static void main(String[] args) {

//        System.out.println("Podaj login i haslo:");
//
//        int a;
//        Scanner input = new Scanner(System.in);
//        UserService user = new UserService();
//        String loginLog = input.next();
//        String passwordLog = input.next();
//        if(user.isCorrectLoginAndPassword(loginLog, passwordLog)) {
//            while (true) {
//                System.out.println("Choose option\n\t1.Add user\n\t2.Delete user by Login\n\t3.Print All users");
//                a = input.nextInt();
//                switch (a) {
//                    case 1: {
//                        String login, password;
//                        System.out.println("Podaj login:");
//                        login = input.next();
//                        System.out.println("Podaj haslo:");
//                        password = input.next();
//                        User newUser = new User(login, password);
//                        try {
//                            user.saveUser(newUser);
//                        } catch (LoginAlreadyExistException e) {
//                            e.printStackTrace();
//                        }
//                        break;
//                    }
//                    case 2: {
//                        String login;
//                        System.out.println("Podaj login:");
//                        login = input.next();
//                        user.removeUserByLogin(login);
//                        break;
//                    }
//                    case 3: {
//                        List<User> users = user.getAllUsers();
//                        for (User u : users) {
//                            System.out.println(u.toString());
//                        }
//                        break;
//                    }
//                    default: {
//                        System.exit(1);
//                    }
//                }
//            }
//
//        }else{
//            System.out.println("Incorrent Login or Password");
//        }
//         LuggageService luggageService = new LuggageService();
//         Luggage luggage = luggageService.getLuggageByCode("A1");
//         System.out.println(luggage);
//         PassengerService passengerService = new PassengerService();
//         Passenger passenger = passengerService.getPassengerByPesel("99999999999");
//         System.out.println(passenger);
//         TicketService ticketService = new TicketService();
//         Ticket ticket = new Ticket();
//         ticket.addLuggage(luggage);
//         ticket.setPassenger(passenger);
//         Luggage luggage2 = luggageService.getLuggageByCode("A3");
//         ticket.addLuggage(luggage2);
//         System.out.println(ticket);
//            Airline airline = new Airline("Lotnisko", "Country","Empty");
        AirlineService airlineService = new AirlineService();
//        System.out.println(airline);
//        airlineService.saveAirline(airline);
//        Airline airline = airlineService.getAirlineById(1l);
//        airline.setCountry("Germany");
//        airline.setDetails("The best airline in the world");
//        airlineService.updateAirline(airline);
          List<Airline> airlines = airlineService.getAllAirlines();
          System.out.println(airlines);
          System.out.println();
          PlaneService planeService = new PlaneService();
//          Plane plane = new Plane("AAB",46, "Samolot2", 220, 2500L);
//          planeService.savePlane(plane);
        Plane plane = planeService.getPlaneById(3l);
        Airline airline = airlineService.getAirlineById(1l);
        plane.setAirline(airline);
        planeService.updatePlane(plane);
        List<Plane> planes = planeService.getAllPlane();
        System.out.println(planes);
    }
}
