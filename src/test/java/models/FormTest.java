package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FormTest {

    @Test
    void isFilled() {
        Form form1 = new Form();
        assertFalse(form1.isFilled());

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
        assertFalse(form2.isFilled());

        Form form3 = new Form();
        form3.setHeight(150);
        form3.setWeight(45);
        form3.setStrokeVolume(65);
        form3.setHeartRate(60);
        form3.setAge(15);
        form3.setWoman(true);
        form3.setBloodPressureTreatment(true);
        form3.setSystolicBloodPressure(30);
        form3.setTotalCholesterol(95);
        form3.setHDLCholesterol(3);
        form3.setSmoker(false);
        assertTrue(form3.isFilled());
    }

    @Test
    void checkAnswerType() {
        TestForm testForm = new TestForm();

        assertTrue(testForm.checkAnswerType("3.5897", "double"));
        assertTrue(testForm.checkAnswerType("69871", "int"));
        assertTrue(testForm.checkAnswerType("true", "boolean"));

        assertTrue(testForm.checkAnswerType("-646.77", "double"));
        assertTrue(testForm.checkAnswerType("-3569", "int"));
        assertTrue(testForm.checkAnswerType("false", "boolean"));

        assertTrue(testForm.checkAnswerType("1000", "double"));

        assertFalse(testForm.checkAnswerType("3.5897kuuu", "double"));
        assertFalse(testForm.checkAnswerType("1.25", "int"));
        assertFalse(testForm.checkAnswerType("TRUE", "boolean"));

        assertFalse(testForm.checkAnswerType("hhae", "double"));
        assertFalse(testForm.checkAnswerType("?", "int"));
        assertFalse(testForm.checkAnswerType("False", "boolean"));

        assertFalse(testForm.checkAnswerType("56", "hhadfaa"));
        assertFalse(testForm.checkAnswerType("46", "INT"));
    }

    @Test
    void checkAndSetAnswerValue_double() {
        TestForm testForm = new TestForm();

        String BAD_PARAMETER = "Chyba, zlé meno parametra!";
        assertEquals(BAD_PARAMETER, testForm.checkAndSetAnswerValue("2fa5afjkj", 55.6));
        assertEquals(BAD_PARAMETER, testForm.checkAndSetAnswerValue("weIGHt", 5.36));

        String BAD_VALUE = "Zlá hodnota, nachádza sa mimo intervalu. Skúste zadať novú hodnotu!";
        assertEquals(BAD_VALUE, testForm.checkAndSetAnswerValue("height", 350.12));
        assertEquals("OK", testForm.checkAndSetAnswerValue("height", 158.98));
        assertEquals(158.98, testForm.getHeight());

        assertEquals(BAD_VALUE, testForm.checkAndSetAnswerValue("weight", 1500));
        assertEquals("OK", testForm.checkAndSetAnswerValue("weight", 45));
        assertEquals(45, testForm.getWeight());

        assertEquals(BAD_VALUE, testForm.checkAndSetAnswerValue("strokeVolume", 290.1));
        assertEquals("OK", testForm.checkAndSetAnswerValue("strokeVolume", 66.6));
        assertEquals(66.6, testForm.getStrokeVolume());

        assertEquals(BAD_VALUE, testForm.checkAndSetAnswerValue("heartRate", -95));
        assertEquals("OK", testForm.checkAndSetAnswerValue("heartRate", 100.0));
        assertEquals(100.0, testForm.getHeartRate());

        assertEquals(BAD_VALUE, testForm.checkAndSetAnswerValue("age", 99998));
        assertEquals("OK", testForm.checkAndSetAnswerValue("age", 24));
        assertEquals(24, testForm.getAge());

        assertEquals(BAD_VALUE, testForm.checkAndSetAnswerValue("systolicBloodPressure", 15));
        assertEquals("OK", testForm.checkAndSetAnswerValue("systolicBloodPressure", 35.67452));
        assertEquals(35.67452, testForm.getSystolicBloodPressure());

        assertEquals(BAD_VALUE, testForm.checkAndSetAnswerValue("totalCholesterol", 0.000));
        assertEquals("OK", testForm.checkAndSetAnswerValue("totalCholesterol", 899.999));
        assertEquals(899.999, testForm.getTotalCholesterol());

        assertEquals(BAD_VALUE, testForm.checkAndSetAnswerValue("HDLCholesterol", -999.9999));
        assertEquals("OK", testForm.checkAndSetAnswerValue("HDLCholesterol", 125.6521));
        assertEquals(125.6521, testForm.getHDLCholesterol());

    }

    @Test
    void checkAndSetAnswerValue_boolean() {
        TestForm testForm = new TestForm();

        String BAD_PARAMETER = "Chyba, zlé meno parametra!";
        assertEquals(BAD_PARAMETER, testForm.checkAndSetAnswerValue("asjf", true));
        assertEquals(BAD_PARAMETER, testForm.checkAndSetAnswerValue("ISwoman", true));

        assertEquals("OK", testForm.checkAndSetAnswerValue("isWoman", true));
        assertEquals(true, testForm.isWoman());

        assertEquals("OK", testForm.checkAndSetAnswerValue("bloodPressureTreatment", false));
        assertEquals(false, testForm.isBloodPressureTreatment());

        assertEquals("OK", testForm.checkAndSetAnswerValue("isSmoker", false));
        assertEquals(false, testForm.isSmoker());
    }
}