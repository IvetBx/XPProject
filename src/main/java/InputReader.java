import dbConnect.DbContext;
import menu.MainMenuDoctor;
import models.Person;
import queries.Query;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Scanner;

public class InputReader {

    Query query = new Query();
    Person loggedUser;

    public boolean loggedUserIsADoctor(){
        return loggedUser.isDoctor();
    }

    public void evaluateCommand(String[] parsedCommand) throws SQLException {
        if(loggedUser == null){
            System.out.println("NOBODY IS LOGGED");
            return;
        }

        if(parsedCommand[0].equals("stretnutia")){
            query.myAppointmnets(loggedUser.getId());
            return;
        }
        else if(parsedCommand[0].equals("diagnozy")){
            query.myDiagnosis(loggedUser.getId());
            return;
        }
        else if(parsedCommand[0].equals("doktori")){
            query.doctors();
            return;
        }
        else if(parsedCommand[0].equals("dates for an appointment")){
            query.doctorFreeAppointments(Integer.parseInt(parsedCommand[1]));
            return;
        }
        else if(parsedCommand[0].equals("zapis")){
            query.makeAppointment(loggedUser.getId(),Integer.parseInt(parsedCommand[1]));
            return;
        }
        else if(parsedCommand[0].equals("novy") && loggedUser.isDoctor()){
            query.newFreeAppointment(loggedUser.getId(), Date.valueOf(parsedCommand[1]), Time.valueOf(parsedCommand[2]));
            return;
        } else if(parsedCommand[0].equals("get patients") && loggedUserIsADoctor()){
            query.printInformationAboutPatients(query.getMyPatients(loggedUser.getId()));
            return;
        }

        System.out.println("INCORRECT COMMAND");
    }

    public void handleCommand(String command) throws SQLException {
        String[] parsedCommand = command.trim().split("\\s+");
        evaluateCommand(parsedCommand);
    }

    public void read(Connection connection) throws SQLException {

            Scanner s = new Scanner(System.in);

            System.out.println("Zadajte meno");
            String user_login = s.nextLine();
            System.out.println("Zadajte heslo");
            String user_password = s.nextLine();

            DbContext.setConnection(connection);

            logIn(user_login, user_password);

            if(loggedUser == null){
                System.out.println("NEUSPESNE PRIHLASENIE");
                return;
            }

            System.out.println("USPESNE PRIHLASENY POUZIVATEL");

        MainMenuDoctor menu = new MainMenuDoctor(query,null);
        menu.run();

    }

    public boolean isSuccessfullyLoggedIn(Person person){
        if (person == null){
            return false;
        }
        loggedUser = person;
        return true;
    }

    public void logIn(String login, String password) throws SQLException {
        Person user = query.getUser(login, password);

        if(!isSuccessfullyLoggedIn(user)){
            System.out.println("UNSUCCESSFULLY LOGGED IN");
            return;
        }
        System.out.println(user.getFullName() + " IS LOGGED SUCCESSFULLY");
    }

}


