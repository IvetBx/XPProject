package models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Person {

    private String address;
    private Date birthday;
    private String city;
    private int id;
    private String information;
    private boolean isDoctor;
    private String login;
    private String name;
    private String password;
    private String surname;

    public Person(){

    }

    public Person(ResultSet resultSet) throws SQLException {
        this.address = resultSet.getString("address");
        this.birthday = resultSet.getDate("birthday");
        this.city = resultSet.getString("city");
        this.id = resultSet.getInt("id");
        this.information = resultSet.getString("information");
        this.isDoctor = resultSet.getBoolean("isdoctor");
        this.login = resultSet.getString("login");
        this.name = resultSet.getString("name");
        this.password = resultSet.getString("password");
        this.surname = resultSet.getString("surname");
    }

    public boolean isDoctor() {
        return isDoctor;
    }

    public int getId(){
        return this.id;
    }

    public String getFullName() {
        if(name == null){
            name = "";
        }
        if(surname == null){
            surname = "";
        }
        return (name + " " + surname).strip();
    }


    public String getAddress() {
        if(address == null){
            return "";
        }
        return address.strip();
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCity() {
        if(city == null){
            return "";
        }
        return city.strip();
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public void setDoctor(boolean doctor) {
        isDoctor = doctor;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
