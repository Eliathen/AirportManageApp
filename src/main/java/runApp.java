import java.lang.String;
import java.util.List;
import java.util.Scanner;

import api.*;
import dao.PassengerDao;
import dao.UserDao;
import entity.Passenger;
import entity.User;
import exceptions.LoginAlreadyExistException;
import services.PassengerService;
import services.UserService;
import sun.java2d.pipe.SolidTextRenderer;

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

        PassengerService pass = new PassengerService();
        //Passenger passenger = new Passenger("Tomasz", "Nowak", "99999999999");
         //pass.savePassenger(passenger);
//       List<Passenger> passengers = pass.getAllPassenger();
//       System.out.println(passengers);
       Passenger passenger1 = pass.getPassengerByPesel("99999999999");
       System.out.println(passenger1);
//        for (Passenger passek : passengers){
//            System.out.println(passek);
//        }
    }
}
