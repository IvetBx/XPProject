package menu;

import models.Person;
import queries.Query;

import java.sql.SQLException;

public class PatientChoiceMenu extends Menu{
    public PatientChoiceMenu(Query query, Person loggedUser) {
        super(query, loggedUser);
    }

    @Override
    public void print() throws SQLException {

    }

    @Override
    public void handle(String option) throws SQLException {

    }
}
