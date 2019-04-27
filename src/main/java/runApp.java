import java.lang.String;
import java.util.List;
import java.util.Scanner;

import api.*;
import dao.UserDao;
import entity.User;
import services.UserService;

public class runApp {

    public static void main(String[] args){
        //System.out.println("Podaj login i haslo:");

        int a;
        Scanner input = new Scanner(System.in);
        UserService user = new UserService();
        while(true) {
            System.out.println("Choose option\n\t1.Add user\n\t2.Delete user by Login\n\t3.Print All users");
            a = input.nextInt();
            switch (a) {
                case 1: {
                    String login, password;
                    System.out.println("Podaj login:");
                    login = input.next();
                    System.out.println("Podaj haslo:");
                    password = input.next();
                    User newUser = new User(login, password);
                    user.saveUser(newUser);
                    break;
                }
                case 2: {
                    String login;
                    System.out.println("Podaj login:");
                    login = input.next();
                    user.removeUserByLogin(login);
                    break;
                }
                case 3: {
                    List<User> users = user.getAllUsers();
                    for (User u : users) {
                        System.out.println(u.toString());
                    }
                    break;
                }
                default: {
                    System.exit(1);
                }
            }
        }
    }
}
