package menu;

import models.Disease;
import models.Person;
import queries.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class AllDiseaseMenu extends Menu{
    ArrayList<Disease> diseases;
    int patientId;

    public AllDiseaseMenu(Query query, Person loggedUser, int patientId) {

        super(query, loggedUser);
        this.patientId = patientId;
    }

    @Override
    public void print() throws SQLException {
        diseases = query.allDisease();
        System.out.println("*** ZVOLTE CHOROBU ***");
        for (int i = 0; i < diseases.size();i++){
            System.out.println("* " + (i+1) + ". " + diseases.get(i).getName());
        }
        System.out.println("* 0. Späť");
        System.out.println("*******************");

    }

    @Override
    public void handle(String option) throws SQLException {
        if(loggedUser == null){
            System.out.println("NOBODY IS LOGGED");
            return;
        }
        if(Integer.parseInt(option) > 0 && Integer.parseInt(option) <= this.diseases.size()){
            System.out.println("Zadajte liečbu");
            Scanner s = new Scanner(System.in);
            String treatment = s.nextLine();
            query.addPatientDisease(patientId,diseases.get(Integer.parseInt(option)-1).getId(), treatment);
            return;
        }
        if(Integer.parseInt(option) == 0){
            exit();
            return;
        }

        System.out.println("INCORRECT COMMAND");
    }
}
