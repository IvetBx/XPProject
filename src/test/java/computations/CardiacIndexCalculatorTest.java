package computations;

import models.Form;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardiacIndexCalculatorTest {

    @Test
    void getResult() {
        CardiacIndexCalculator calculator = new CardiacIndexCalculator();
        Form form = new Form();

        form.setHeight(169);
        form.setWeight(71);
        form.setStrokeVolume(70);
        form.setHeartRate(64);
        assertEquals(2.44, calculator.getResult(form));

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
    }
}