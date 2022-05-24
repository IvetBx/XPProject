package models;

public class TestForm extends Form {

    public TestForm() {
        super();
    }

    public boolean checkAnswerType(String answer, String type) {
        return super.checkAnswerType(answer, type);
    }

    public String checkAndSetAnswerValue(String parameter, double valueDouble) {
        return  super.checkAndSetAnswerValue(parameter, valueDouble);
    }

    public String checkAndSetAnswerValue(String parameter, boolean valueBoolean) {
        return  super.checkAndSetAnswerValue(parameter, valueBoolean);
    }
}
