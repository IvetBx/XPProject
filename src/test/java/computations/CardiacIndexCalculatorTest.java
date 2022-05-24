package computations;

import models.Form;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import static org.junit.jupiter.api.Assertions.*;

class CardiacIndexCalculatorTest {

    @Test
    void getResult() {
        CardiacIndexCalculator cardiacIndexCalculator1 = new CardiacIndexCalculator();
        Form form1 = new Form();
        form1.setHeight(169);
        form1.setWeight(71);
        form1.setStrokeVolume(70);
        form1.setHeartRate(64);
        form1.setAge(32);
        form1.setWoman(false);
        form1.setBloodPressureTreatment(true);
        form1.setSystolicBloodPressure(35);
        form1.setTotalCholesterol(110);
        form1.setHDLCholesterol(5);
        form1.setSmoker(true);
        assertEquals(2.44, cardiacIndexCalculator1.getResult(form1));

        CardiacIndexCalculator cardiacIndexCalculator2 = new CardiacIndexCalculator();
        Form form2 = new Form();
        form2.setHeight(189);
        form2.setWeight(90);
        form2.setStrokeVolume(90);
        form2.setHeartRate(75);
        form2.setAge(32);
        form2.setWoman(false);
        form2.setBloodPressureTreatment(true);
        form2.setSystolicBloodPressure(35);
        form2.setTotalCholesterol(110);
        form2.setHDLCholesterol(5);
        form2.setSmoker(true);
        assertEquals(3.1, cardiacIndexCalculator2.getResult(form2));

        assertThrows(InvalidParameterException.class, () -> {
            Form form3 = new Form();
            CardiacIndexCalculator cardiacIndexCalculator3 = new CardiacIndexCalculator();
            cardiacIndexCalculator3.getResult(form3);
        });

        Throwable exception = assertThrows(InvalidParameterException.class, () -> {
            Form form4 = new Form();
            form4.setHeight(150);
            form4.setWeight(50);
            form4.setStrokeVolume(65);

            CardiacIndexCalculator cardiacIndexCalculator4 = new CardiacIndexCalculator();
            cardiacIndexCalculator4.getResult(form4);
        });
        assertEquals("Nastala chyba, dotazník nie je vyplnený!", exception.getMessage());
    }

    @Test
    void getResultsInterpretation() {
        CardiacIndexCalculator calculator = new CardiacIndexCalculator();

        double cardiacIndex = 1.99;
        String expected = "Váš výsledok sa rovná " + cardiacIndex + " l/min/m², čo poukazuje na kardiogénny šok.";
        assertEquals(expected, calculator.getResultsInterpretation(cardiacIndex));

        cardiacIndex = 2.3956;
        expected = "Váš výsledok sa rovná " + cardiacIndex + " l/min/m², je to v rámci normálneho rozsahu srdcového indexu.";
        assertEquals(expected, calculator.getResultsInterpretation(cardiacIndex));

        cardiacIndex = 4.00001;
        expected = "Váš výsledok sa rovná " + cardiacIndex + " l/min/m², je vysoko mimo normálneho rozsahu srdcového indexu.";
        assertEquals(expected, calculator.getResultsInterpretation(cardiacIndex));

        cardiacIndex = -45;
        expected = "Váš výsledok sa rovná " + cardiacIndex + " l/min/m², čo poukazuje na kardiogénny šok.";
        assertEquals(expected, calculator.getResultsInterpretation(cardiacIndex));

        cardiacIndex = 0;
        expected = "Váš výsledok sa rovná " + cardiacIndex + " l/min/m², čo poukazuje na kardiogénny šok.";
        assertEquals(expected, calculator.getResultsInterpretation(cardiacIndex));
    }
}