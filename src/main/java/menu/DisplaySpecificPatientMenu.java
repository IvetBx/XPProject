package menu;

import models.Person;
import queries.Query;

import java.sql.SQLException;

public class DisplaySpecificPatientMenu extends Menu{
    int patientId;

    public DisplaySpecificPatientMenu(Query query, Person loggedUser, int patientId) {
        super(query, loggedUser);
        this.patientId = patientId;
    }

    @Override
    public void print() throws SQLException {
        System.out.println("**************");
        System.out.println("* 1. Pridajte diagnózu");
        System.out.println("* 2. Informácie o pacientovi");
        System.out.println("* 0. Späť");
        System.out.println("**************");
    }

    @Override
    public void handle(String option) throws SQLException {
        if(loggedUser == null){
            System.out.println("NOBODY IS LOGGED");
            return;
        }
        if(Integer.parseInt(option) == 1){
            AllDiseaseMenu menu = new AllDiseaseMenu(query, loggedUser, patientId);
            menu.run();
            return;
        }
        if(Integer.parseInt(option) == 2){
            Person patient = query.getPersonById(patientId);
            System.out.println(patient.getFullName());
            System.out.println(patient.getAddress());
            System.out.println(patient.getCity());
            System.out.println(patient.getInformation());
            return;
        }
        if(Integer.parseInt(option) == 0){
            exit();
            return;
        }

        System.out.println("INCORRECT COMMAND");
    }
}
