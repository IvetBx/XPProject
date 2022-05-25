package menu;

import models.Person;
import queries.Query;

import java.sql.SQLException;

public class MainMenuPatient extends Menu{
    public MainMenuPatient(Query query, Person loggedUser) {
        super(query, loggedUser);
    }

    @Override
    public void print() {
        System.out.println("***************************");
        System.out.println("******* HLAVNÉ MENU *******");
        System.out.println("***************************");
        System.out.println("* 1. Moje stretnutia      *");
        System.out.println("* 2. Moje diagnózy        *");
        System.out.println("* 3. Zoznam doktorov      *");
        System.out.println("* 0. Odhlásenie           *");
        System.out.println("***************************");
    }

    @Override
    public void handle(String option) throws SQLException {
        if(loggedUser == null){
            System.out.println("NOBODY IS LOGGED");
            return;
        }

        if(option.equals("1")){
            query.myAppointmnetsPatient(loggedUser.getId());
            return;
        }
        else if(option.equals("2")){
            query.myDiagnosis(loggedUser.getId());
            return;
        }
        else if(option.equals("3")){
            //query.doctors();
            DisplayAllDoctorsMenu menu = new DisplayAllDoctorsMenu(query,loggedUser);
            menu.run();
            return;
        }
        else if(option.equals("0")) {
            exit();
            return;
        }

        System.out.println("INCORRECT COMMAND");

    }

}
