package queries;

import appointment.Appointment;
import dbConnect.DbContext;

import doctor.Doctor;
import models.Disease;
import models.Person;

import javax.print.Doc;
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


    public void myAppointmnetsDoctor(int doctorId) throws SQLException {

        try(PreparedStatement statement = DbContext.getConnection().prepareStatement("SELECT * FROM appointment, person WHERE doctor_id = ? AND patient_id is not null AND person.id = patient_id")){
            statement.setInt(1, doctorId);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                System.out.println(result.getDate("appointment_date") + " " + result.getTime("appointment_time") + " " + result.getString("name") + " " + result.getString("surname"));
            }
        }
    }

    public void myAppointmnetsPatient(int patientId) throws SQLException {

        try(PreparedStatement statement = DbContext.getConnection().prepareStatement("SELECT * FROM appointment, person WHERE patient_id = ? AND person.id = doctor_id")){
            statement.setInt(1, patientId);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                System.out.println(result.getDate("appointment_date") + " " + result.getTime("appointment_time") + " " + result.getString("name") + " " + result.getString("surname"));
            }
        }
    }

    public void myAppointmnets(int userId) throws SQLException {

        try(PreparedStatement statement = DbContext.getConnection().prepareStatement("SELECT * FROM appointment WHERE (doctor_id = ? OR patient_id = ?) AND patient_id is not null")){
            statement.setInt(1, userId);
            statement.setInt(2, userId);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                Person doctor = getPersonById(result.getInt("doctor_id"));
                Person patient = getPersonById(result.getInt("patient_id"));
                System.out.println(result.getDate("appointment_date") + " " + result.getTime("appointment_time")+ " DOKTOR: " + doctor.getFullName() + " PACIENT: " + patient.getFullName());
            }
        }
    }

    public Person getPersonById(int userId) throws SQLException {

        try(PreparedStatement statement = DbContext.getConnection().prepareStatement("SELECT * FROM person WHERE id = ?")){
            statement.setInt(1, userId);
            ResultSet result = statement.executeQuery();
            if(!result.next()){
                return null;
            }
            return new Person(result);
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

    public ArrayList<Doctor> doctorsArray() throws SQLException {

        try(PreparedStatement statement = DbContext.getConnection().prepareStatement("SELECT * FROM person WHERE isdoctor = true")){
            ArrayList<Doctor> allDoctors = new ArrayList<>();
            ResultSet result = statement.executeQuery();
            while(result.next()){
                allDoctors.add(new Doctor(result.getInt("id"), result.getString("name"),result.getString("surname")));
            }
            return  allDoctors;

        }

    }


    public ArrayList<Appointment> doctorFreeAppointments(int doctorId) throws SQLException {

        try(PreparedStatement statement = DbContext.getConnection().prepareStatement("SELECT * FROM appointment WHERE doctor_id = ? AND patient_id is null")){
            statement.setInt(1, doctorId);
            ResultSet result = statement.executeQuery();
            ArrayList<Appointment> appointments = new ArrayList<>();

            while(result.next()){
                appointments.add(new Appointment(result.getInt("id"), result.getDate("appointment_date"), result.getTime("appointment_time")));
            }
            return appointments;
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

    public ArrayList<Person> getMyPatients(int userId) throws SQLException {

        ArrayList<Person> allPatients = new ArrayList();
        try(PreparedStatement statement = DbContext.getConnection().prepareStatement("SELECT * FROM Doctor_Patient, Person WHERE doctor_id = ? AND patient_id = person.id")){
            statement.setInt(1, userId);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                Person person = new Person();
                person.setId(result.getInt("patient_id"));
                person.setName(result.getString("name"));
                person.setSurname(result.getString("surname"));
                allPatients.add(person);
            }
        }
        return allPatients;
    }

    public ArrayList<Disease> allDisease() throws SQLException {

        try(PreparedStatement statement = DbContext.getConnection().prepareStatement("SELECT * FROM disease")){
            ResultSet result = statement.executeQuery();
            ArrayList<Disease> allDisease = new ArrayList<>();
            while(result.next()){
                allDisease.add(new Disease(result.getInt("id"), result.getString("name")));
            }
            return allDisease;
        }
    }

    public void addPatientDisease(int userId, int diseaseId) throws SQLException {

        try(PreparedStatement statement = DbContext.getConnection().prepareStatement("INSERT INTO diagnosis (patient_id, disease_id) VALUES (?, ?)")){
            statement.setInt(1, userId);
            statement.setInt(2, diseaseId);
            statement.execute();
        }
    }

}
