package login;

import models.Person;
import queries.Query;

import java.sql.SQLException;
import java.util.Scanner;

public class Login {
    Query query;
    Person loggedUser = null;

    public Login(Query query) {
        this.query = query;
    }

    public void start() throws SQLException {
        while (loggedUser == null) {
            userLogin();
        }
    }

    public void userLogin() throws SQLException {
        Scanner s = new Scanner(System.in);
        System.out.println("Zadajte meno");
        String user_login = s.nextLine();
        System.out.println("Zadajte heslo");
        String user_password = s.nextLine();
        logIn(user_login, user_password);
    }

    public void logIn(String login, String password) throws SQLException {
        Person user = query.getUser(login, password);

        if(!isSuccessfullyLoggedIn(user)){
            System.out.println("UNSUCCESSFULLY LOGGED IN");
            return;
        }
        System.out.println(user.getFullName() + " IS LOGGED SUCCESSFULLY");
    }

    public boolean isSuccessfullyLoggedIn(Person person){
        if (person == null){
            return false;
        }
        loggedUser = person;
        return true;
    }

    public Person getLoggedUser(){
        return this.loggedUser;
    }
}
