package menu;

import dbConnect.DbContext;
import models.Person;
import queries.Query;

import java.sql.SQLException;
import java.util.Scanner;

public abstract class Menu {
    Query query;
    Person loggedUser;
    private boolean exit;

    public Menu(Query query, Person loggedUser) {
        this.query = query;
        this.loggedUser = loggedUser;
    }

    public void run() throws SQLException {
        exit = false;
        Scanner s = new Scanner(System.in);

        while(!exit){
            System.out.println();
            print();
            System.out.println();

            String command = s.nextLine();
            handle(command);
        }

    }

    public abstract void print() throws SQLException;

    public abstract void handle(String option) throws SQLException;

    public void exit(){
        loggedUser = null;
        exit = true;
    }

}
