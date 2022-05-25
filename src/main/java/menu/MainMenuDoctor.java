package menu;

import menu.DisplayDoctorsPatientsMenu;
import menu.Menu;
import models.Person;
import queries.Query;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Scanner;

public class MainMenuDoctor extends Menu {
    public MainMenuDoctor(Query query, Person loggedUser) {
        super(query, loggedUser);
    }

    @Override
    public void print() {
        System.out.println("***************************");
        System.out.println("******* HLAVNÉ MENU *******");
        System.out.println("***************************");
        System.out.println("* 1. Moje stretnutia      *");
        System.out.println("* 2. Vytvor voľný termín  *");
        System.out.println("* 3. Pacienti             *");
        System.out.println("* 4. Uprav môj popis      *");
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
            query.myAppointmnetsDoctor(loggedUser.getId());
            return;
        }
        else if(option.equals("2") && loggedUser.isDoctor()) {
            Scanner s = new Scanner(System.in);
            System.out.println("Zadajte dátum(yyyy-mm-dd)");
            String date = s.nextLine();
            System.out.println("Zadajte čas(hh:mm:ss)");
            String time = s.nextLine();
            query.newFreeAppointment(loggedUser.getId(), Date.valueOf(date), Time.valueOf(time));
            return;
        }
        else if(option.equals("3")){
            DisplayDoctorsPatientsMenu menu = new DisplayDoctorsPatientsMenu(query, loggedUser);
            menu.run();
            return;
        }
        else if(option.equals("4")){
            System.out.println("Zadajte informácie");
            Scanner s = new Scanner(System.in);
            String information = s.nextLine();
            query.updatePersonInformation(loggedUser.getId(),information);
            return;
        }
        else if(option.equals("0")){
            exit();
            return;
        }

        System.out.println("INCORRECT COMMAND");

    }


}
