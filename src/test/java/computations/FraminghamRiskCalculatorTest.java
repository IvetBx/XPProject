package computations;

import models.Form;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FraminghamRiskCalculatorTest {

    @Test
    void getResult() {
        Form form = new Form();
        form.setAge(55);
        form.setWoman(true);
        form.setBloodPressureTreatment(false);
        form.setSystolicBloodPressure(50);
        form.setTotalCholesterol(193.35);
        form.setHDLCholesterol(1);
        form.setSmoker(true);

        FraminghamRiskCalculator framinghamRiskCalculator = new FraminghamRiskCalculator();
        assertEquals(3.25, framinghamRiskCalculator.getRiskScoreForWoman(form), 0.1);
        assertEquals(27.45, framinghamRiskCalculator.getResult(form), 1.0);
    }

    @Test
    void getResultsInterpretation() {
    }

    @Test
    void ageInEquationWithSmokingForWomen() {
        FraminghamRiskCalculator framinghamRiskCalculator = new FraminghamRiskCalculator();

        assertEquals(42, framinghamRiskCalculator.getAgeUsedInEquationWithSmoking(42, true));
        assertEquals(70, framinghamRiskCalculator.getAgeUsedInEquationWithSmoking(70, true));
        assertEquals(78, framinghamRiskCalculator.getAgeUsedInEquationWithSmoking(78, true));
        assertEquals(78, framinghamRiskCalculator.getAgeUsedInEquationWithSmoking(80, true));
        assertEquals(78, framinghamRiskCalculator.getAgeUsedInEquationWithSmoking(99, true));
        assertEquals(0, framinghamRiskCalculator.getAgeUsedInEquationWithSmoking(0, true));
        assertEquals(-100, framinghamRiskCalculator.getAgeUsedInEquationWithSmoking(-100, true));
        assertEquals(78, framinghamRiskCalculator.getAgeUsedInEquationWithSmoking(2147483647, true));

    }

    @Test
    void ageInEquationWithSmokingForMen() {
        FraminghamRiskCalculator framinghamRiskCalculator = new FraminghamRiskCalculator();

        assertEquals(42, framinghamRiskCalculator.getAgeUsedInEquationWithSmoking(42, false));
        assertEquals(70, framinghamRiskCalculator.getAgeUsedInEquationWithSmoking(70, false));
        assertEquals(70, framinghamRiskCalculator.getAgeUsedInEquationWithSmoking(78, false));
        assertEquals(70, framinghamRiskCalculator.getAgeUsedInEquationWithSmoking(99, false));
        assertEquals(0, framinghamRiskCalculator.getAgeUsedInEquationWithSmoking(0, false));
        assertEquals(-100, framinghamRiskCalculator.getAgeUsedInEquationWithSmoking(-100, false));
        assertEquals(70, framinghamRiskCalculator.getAgeUsedInEquationWithSmoking(2147483647, false));
    }
}