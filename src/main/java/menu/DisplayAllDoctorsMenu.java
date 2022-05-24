package menu;

import doctor.Doctor;
import models.Person;
import queries.Query;

import java.sql.SQLException;
import java.util.ArrayList;

public class DisplayAllDoctorsMenu extends Menu{
    ArrayList<Doctor> doctors;

    public DisplayAllDoctorsMenu(Query query, Person loggedUser) {
        super(query, loggedUser);
    }

    @Override
    public void print() throws SQLException {
        doctors = query.doctorsArray();
        System.out.println("******* ZOZNAM DOKTOROV *******");
        for(int i = 0;i<doctors.size();i++){
            System.out.println("* "+(i+1)+". " + doctors.get(i).getName() +" " + doctors.get(i).getSurname());
        }
        System.out.println("* 0. Späť");
        System.out.println("******************************");
    }

    @Override
    public void handle(String option) throws SQLException {
        if(loggedUser == null){
            System.out.println("NOBODY IS LOGGED");
            return;
        }
        if(Integer.parseInt(option) > 0 && Integer.parseInt(option) <= doctors.size() ){
            DisplaySpecificDoctorMenu menu = new DisplaySpecificDoctorMenu(query, loggedUser, doctors.get(Integer.parseInt(option)-1).getId());
            menu.run();
            return;
        }
        if(Integer.parseInt(option) == 0){
            exit();
            return;
        }

        System.out.println("INCORRECT COMMAND");

    }
}
