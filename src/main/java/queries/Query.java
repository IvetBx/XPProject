package queries;

import dbConnect.DbContext;

import models.Person;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Query {

    public boolean userInDb(String login, String password) throws SQLException {

        try(PreparedStatement statement = DbContext.getConnection().prepareStatement("SELECT * FROM Person WHERE login = ? AND password = ?")) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();
            return result.next();
        }
    }

    public ResultSet executeQuery(String query, Object[] parameters) throws SQLException {

        try(PreparedStatement statement = DbContext.getConnection().prepareStatement(query)) {
            for (int i = 0; i < parameters.length; i++) {
                if (parameters[i] instanceof Integer) {
                    statement.setInt(i + 1, (Integer) parameters[i]);
                } else if (parameters[i] instanceof String) {
                    statement.setString(i + 1, (String) parameters[i]);
                } else if (parameters[i] instanceof Date) {
                    statement.setDate(i + 1, (Date) parameters[i]);
                } else if (parameters[i] instanceof Time) {
                    statement.setTime(i + 1, (Time) parameters[i]);
                } else if (parameters[i] instanceof Boolean) {
                    statement.setBoolean(i + 1, (Boolean) parameters[i]);
                }

            }

            return statement.executeQuery();

        }

    }

    public void myAppointmnets(int userId) throws SQLException {

        try(PreparedStatement statement = DbContext.getConnection().prepareStatement("SELECT * FROM appointment WHERE (doctor_id = ? OR patient_id = ?) AND patient_id is not null")){
            statement.setInt(1, userId);
            statement.setInt(2, userId);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                System.out.println(result.getDate("appointment_date") + " " + result.getTime("appointment_time"));
            }
        }
    }

    public void myDiagnosis(int userId) throws SQLException {

        try(PreparedStatement statement = DbContext.getConnection().prepareStatement("SELECT * FROM diagnosis, disease WHERE patient_id = ? AND disease_id = disease.id")){
            statement.setInt(1, userId);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                System.out.println(result.getString("name"));
            }
        }
    }

    public void doctors() throws SQLException {

        try(PreparedStatement statement = DbContext.getConnection().prepareStatement("SELECT * FROM person WHERE isdoctor = true")){
            ResultSet result = statement.executeQuery();
            while(result.next()){

                System.out.println(result.getInt("id") + " - " + result.getString("name") + " " + result.getString("surname"));
            }
        }

    }

    public void doctorFreeAppointments(int doctorId) throws SQLException {

        try(PreparedStatement statement = DbContext.getConnection().prepareStatement("SELECT * FROM appointment WHERE doctor_id = ? AND patient_id is null")){
            statement.setInt(1, doctorId);
            ResultSet result = statement.executeQuery();
            if(!result.next()){
                System.out.println("Doktor " + doctorId +" nema ziaden volny termin");
                return;
            }
            System.out.println( result.getInt("id") + " - " + result.getDate("appointment_date") + " " + result.getTime("appointment_time") );
            while(result.next()){
                System.out.println( result.getInt("id") + " - " + result.getDate("appointment_date") + " " + result.getTime("appointment_time") );
            }
        }

    }

    public void makeAppointment(int userId, int appointmentId) throws SQLException {

        try(PreparedStatement statement = DbContext.getConnection().prepareStatement("UPDATE appointment SET patient_id = ? WHERE id = ?")){
            statement.setInt(1, userId);
            statement.setInt(2, appointmentId);
            statement.executeUpdate();
        }

    }

    public void newFreeAppointment(int userId, Date date, Time time) throws SQLException {

        try(PreparedStatement statement = DbContext.getConnection().prepareStatement("INSERT INTO appointment (appointment_date, appointment_time, doctor_id, patient_id) VALUES (?, ?, ?, null)")){
            statement.setDate(1, date);
            statement.setTime(2, time);
            statement.setInt(3, userId);
            statement.execute();
        }
    }


    public void printInformationAboutPatients(List<Person> patients){
        if(patients == null || patients.size() == 0){
            System.out.println("You have not patients.");
            return;
        }
        for(Person person : patients){
            if(person.getFullName().strip().isEmpty()){
                System.out.println("Only login is available: " + person.getLogin() + ", " + person.getBirthday() + ", " + person.getAddress() + ", " + person.getCity());
            } else {
                System.out.println(person.getFullName() + ", " + person.getBirthday() + ", " + person.getAddress() + ", " + person.getCity());
            }
        }
    }

    public Person getUser(String login, String password) throws SQLException {

       /* try(ResultSet result = executeQuery("SELECT * FROM Person WHERE login = ? AND password = ?", new Object[]{login,password})) {
            if (!result.next()) {
                return null;
            }
            return new Person(result);

        }*/

        try(PreparedStatement statement = DbContext.getConnection().prepareStatement("SELECT * FROM Person WHERE login = ? AND password = ?")) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();
            if(!result.next()){
                return null;
            }
            return new Person(result);
        }

    }

    public List<Person> getMyPatients(int userId) throws SQLException {

        List<Person> allPatients = new ArrayList();
        try(PreparedStatement statement = DbContext.getConnection().prepareStatement("SELECT * FROM Doctor_Patient INNER JOIN Person WHERE doctor_id = ?")){
            statement.setInt(1, userId);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                Person person = new Person();
                person.setName(result.getString("name"));
                person.setSurname(result.getString("surname"));
                allPatients.add(person);
            }
        }
        return allPatients;
    }



}
