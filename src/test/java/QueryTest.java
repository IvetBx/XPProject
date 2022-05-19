import models.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

class QueryTest {

    /*private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void successfullyLogin() throws SQLException {
        String login = "log";
        String password = "correct";

        Person person = new Person();
        person.setLogin(login);
        person.setPassword(password);
        person.setName("John");
        person.setSurname("Smith");

        EntityManager entityManager = Mockito.mock(EntityManager.class);
        Mockito.when(entityManager.getUser(login, password)).thenReturn(person);

        queries.Query query = new queries.Query();
        query.setEntityManager(entityManager);

        assertTrue(query.isSuccessfullyLoggedIn(person));
        String fullName = person.getFullName();
        assertEquals("John Smith", fullName);

        query.logIn(login, password);

        assertEquals(fullName + " IS LOGGED SUCCESSFULLY", outputStreamCaptor.toString()
                .trim());
    }

    @Test
    void unsuccessfullyLogin() throws SQLException {
        String login = "log";
        String incorrectPassword = "incorrect";

        Person person = null;

        EntityManager entityManager = Mockito.mock(EntityManager.class);
        Mockito.when(entityManager.getUser(login, incorrectPassword)).thenReturn(person);

        queries.Query query = new queries.Query();
        query.setEntityManager(entityManager);

        assertFalse(query.isSuccessfullyLoggedIn(person));

        query.logIn(login, incorrectPassword);

        assertEquals( "UNSUCCESSFULLY LOGGED IN", outputStreamCaptor.toString()
                .trim());
    }

    @Test
    void userWithIncompleteName() throws SQLException {
        String login = "log";
        String password = "correct";

        Person person = new Person();
        person.setLogin(login);
        person.setPassword(password);
        person.setName(null);
        person.setSurname("Smith");

        EntityManager entityManager = Mockito.mock(EntityManager.class);
        Mockito.when(entityManager.getUser(login, password)).thenReturn(person);

        queries.Query query = new queries.Query();
        query.setEntityManager(entityManager);

        assertTrue(query.isSuccessfullyLoggedIn(person));
        String fullName = person.getFullName();
        assertEquals("Smith", fullName);

        query.logIn(login, password);

        assertEquals(fullName + " IS LOGGED SUCCESSFULLY", outputStreamCaptor.toString()
                .trim());
    }

    @Test
    void userWithoutName() throws SQLException {
        String login = "log";
        String password = "correct";

        Person person = new Person();
        person.setName(null);
        person.setSurname(null);

        EntityManager entityManager = Mockito.mock(EntityManager.class);
        Mockito.when(entityManager.getUser(login, password)).thenReturn(person);

        queries.Query query = new queries.Query();
        query.setEntityManager(entityManager);

        assertTrue(query.isSuccessfullyLoggedIn(person));
        assertEquals("", person.getFullName());

        query.logIn(login, password);
        assertEquals("IS LOGGED SUCCESSFULLY", outputStreamCaptor.toString().trim());
    }

    @Test
    void printingInfoAboutPatients() {
        Person patient1 = new Person();
        patient1.setName("Jane");
        patient1.setSurname("White");
        patient1.setBirthday(Date.valueOf("1990-03-20"));
        patient1.setAddress("Street 22");
        patient1.setCity("London");

        Person patient2 = new Person();
        patient2.setName("Harry");
        patient2.setSurname("Potter");
        patient2.setBirthday(Date.valueOf("1990-03-20"));
        patient2.setAddress("Street 22");
        patient2.setCity("London");

        queries.Query query = new queries.Query();
        query.printInformationAboutPatients(new ArrayList<>(Arrays.asList(patient1, patient2)));

        String infoAboutPatients = "Jane White, 1990-03-20, Street 22, London\n" +
                "Harry Potter, 1990-03-20, Street 22, London";
        assertEquals(infoAboutPatients, outputStreamCaptor.toString().trim());
    }

    @Test
    void printingIncompleteInfoAboutPatients() {
        Person patient1 = new Person();
        patient1.setName(null);
        patient1.setSurname("White");
        patient1.setBirthday(Date.valueOf("1990-03-20"));
        patient1.setAddress("   ");
        patient1.setCity("London");

        Person patient2 = new Person();
        patient2.setName(null);
        patient2.setSurname(null);
        patient2.setLogin("iAmPatient");
        patient2.setBirthday(Date.valueOf("1990-03-20"));
        patient2.setAddress("Street 22");
        patient2.setCity("London");

        queries.Query query = new queries.Query();
        query.printInformationAboutPatients(new ArrayList<>(Arrays.asList(patient1, patient2)));

        String infoAboutPatients = "White, 1990-03-20, , London\n" +
                "Only login is available: iAmPatient, 1990-03-20, Street 22, London";
        assertEquals(infoAboutPatients, outputStreamCaptor.toString().trim());
    }

    @Test
    void printingIfThereAreNoPatients() {
        queries.Query query = new queries.Query();
        query.printInformationAboutPatients(new ArrayList<>());

        String infoAboutPatients = "You have not patients.";
        assertEquals(infoAboutPatients, outputStreamCaptor.toString().trim());
    }

    @Test
    void printingIfThereAreNoPatients2() {
        queries.Query query = new queries.Query();
        query.printInformationAboutPatients(null);

        String infoAboutPatients = "You have not patients.";
        assertEquals(infoAboutPatients, outputStreamCaptor.toString().trim());
    }

    @Test
    void getPatientsIfUserIsDoctor() throws SQLException {
        String login = "log";
        String password = "correct";

        Person doctor = new Person();
        doctor.setId(1);
        doctor.setDoctor(true);

        Person patient1 = new Person();
        patient1.setSurname("White");
        patient1.setBirthday(Date.valueOf("1990-03-20"));
        patient1.setAddress("Street 22");
        patient1.setCity("London");

        EntityManager entityManager = Mockito.mock(EntityManager.class);
        Mockito.when(entityManager.getUser(login, password)).thenReturn(doctor);
        Mockito.when(entityManager.getMyPatients(1)).thenReturn(new ArrayList<>(Arrays.asList(patient1)));

        queries.Query query = new queries.Query();
        query.setEntityManager(entityManager);

        query.logIn(login, password);
        assertTrue(query.loggedUserIsADoctor());

        String logIn = "IS LOGGED SUCCESSFULLY\n";
        String infoAboutPatients = "White, 1990-03-20, Street 22, London";
        query.evaluateCommand(new String[]{"get patients"});

        assertEquals(logIn + infoAboutPatients, outputStreamCaptor.toString().trim());
    }

    @Test
    void getPatientsIfUserIsNotDoctor() throws SQLException {
        Person doctor = new Person();
        doctor.setId(1);
        doctor.setDoctor(false);

        EntityManager entityManager = Mockito.mock(EntityManager.class);
        Mockito.when(entityManager.getUser("", "")).thenReturn(doctor);

        queries.Query query = new queries.Query();
        query.setEntityManager(entityManager);

        query.logIn("", "");
        assertFalse(query.loggedUserIsADoctor());

        String logIn = "IS LOGGED SUCCESSFULLY\n";
        String infoAboutPatients = "INCORRECT COMMAND";
        query.evaluateCommand(new String[]{"get patients"});

        assertEquals(logIn + infoAboutPatients, outputStreamCaptor.toString().trim());
    }

    @Test
    void getPatientsIfNobodyIsLogged() throws SQLException {

        EntityManager entityManager = Mockito.mock(EntityManager.class);

        queries.Query query = new queries.Query();
        query.setEntityManager(entityManager);

        query.evaluateCommand(new String[]{"get patients"});

        assertEquals("NOBODY IS LOGGED", outputStreamCaptor.toString().trim());
    }*/

}