package computations;

import models.Form;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SixMinuteWalkTestCalculatorTest {

    @Test
    void getResult() {
        SixMinuteWalkTestCalculator calculator = new SixMinuteWalkTestCalculator();

        Form testForm = new Form();
        testForm.setHeight(177);
        testForm.setAge(72);
        testForm.setWeight(80);
        testForm.setWoman(false);

        assertEquals(528, calculator.getResult(testForm));

        testForm.setHeight(150);
        testForm.setAge(22);
        testForm.setWeight(65);
        testForm.setWoman(false);

        assertEquals(601, calculator.getResult(testForm));

        testForm.setHeight(162);
        testForm.setAge(41);
        testForm.setWeight(50);
        testForm.setWoman(true);

        assertEquals(657, calculator.getResult(testForm));

        testForm.setHeight(190);
        testForm.setAge(32);
        testForm.setWeight(70);
        testForm.setWoman(true);

        assertEquals(722, calculator.getResult(testForm));

    }

    @Test
    void getResultsInterpretation() {
    }
}