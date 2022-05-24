package models;

import java.util.*;

public class Form {
    private int INCORRECT_NUMBER = -9999;
    private String CANCELED_FORM = "Vypĺňanie dotazníku sa zrušilo!";
    private String BAD_ANSWER_FORMAT = "Zlý formát odpovede, skúte napísať hodnotu znova!";
    private String BAD_PARAMETER = "Chyba, zlé meno parametra!";
    private String BAD_VALUE = "Zlá hodnota, nachádza sa mimo intervalu. Skúste zadať novú hodnotu!";
    private String OK = "OK";
    private String FORM_END = "Gratulujeme, odpovedali ste na všetky otázky.";
    private String X_SIGN = "x";
    private String INT_TYPE = "int";
    private String DOUBLE_TYPE = "double";
    private String BOOLEAN_TYPE = "boolean";

    private double height;
    private double weight;
    private double strokeVolume;
    private double heartRate;
    private int age;
    private Boolean isWoman;
    private Boolean bloodPressureTreatment;
    private double systolicBloodPressure;
    private double totalCholesterol;
    private double HDLCholesterol;
    private Boolean isSmoker;

    public Form() {
        this.height = INCORRECT_NUMBER;
        this.weight = INCORRECT_NUMBER;
        this.strokeVolume = INCORRECT_NUMBER;
        this.heartRate = INCORRECT_NUMBER;
        this.age = INCORRECT_NUMBER;
        this.isWoman = null;
        this.bloodPressureTreatment = null;
        this.systolicBloodPressure = INCORRECT_NUMBER;
        this.totalCholesterol = INCORRECT_NUMBER;
        this.HDLCholesterol = INCORRECT_NUMBER;
        this.isSmoker = null;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setStrokeVolume(double strokeVolume) {
        this.strokeVolume = strokeVolume;
    }

    public void setHeartRate(double heartRate) {
        this.heartRate = heartRate;
    }

    public double getHeight() {
        return this.height;
    }

    public double getWeight() {
        return this.weight;
    }

    public double getStrokeVolume() {
        return this.strokeVolume;
    }

    public double getHeartRate() {
        return this.heartRate;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isWoman() {
        return this.isWoman;
    }

    public void setWoman(boolean woman) {
        this.isWoman = woman;
    }

    public boolean isBloodPressureTreatment() {
        return this.bloodPressureTreatment;
    }

    public void setBloodPressureTreatment(boolean bloodPressureTreatment) {
        this.bloodPressureTreatment = bloodPressureTreatment;
    }

    public double getSystolicBloodPressure() {
        return this.systolicBloodPressure;
    }

    public void setSystolicBloodPressure(double systolicBloodPressure) {
        this.systolicBloodPressure = systolicBloodPressure;
    }

    public double getTotalCholesterol() {
        return this.totalCholesterol;
    }

    public void setTotalCholesterol(double totalCholesterol) {
        this.totalCholesterol = totalCholesterol;
    }

    public double getHDLCholesterol() {
        return this.HDLCholesterol;
    }

    public void setHDLCholesterol(double HDLCholesterol) {
        this.HDLCholesterol = HDLCholesterol;
    }

    public boolean isSmoker() {
        return this.isSmoker;
    }

    public void setSmoker(boolean smoker) {
        this.isSmoker = smoker;
    }

    public void getQuestions() {
        ArrayList<FormItem> formItems = generateQuestions();

        Scanner s = new Scanner(System.in);
        String answer;
        double answerDouble;
        boolean answerBoolean;
        String answerType;
        String valueCheckMessage = "";

        for (FormItem item : formItems) {
            System.out.println(item.getQuestion());

            while (true) {
                answer = s.nextLine();
                answer = answer.trim().toLowerCase();

                if (answer.equals(X_SIGN)) {
                    System.out.println(CANCELED_FORM);
                    return;
                }
                answerType = item.getAnswerType();

                if (answerType.equals(DOUBLE_TYPE) || answerType.equals(INT_TYPE)) {
                    if (!checkAnswerType(answer, answerType)) {
                        System.out.println(BAD_ANSWER_FORMAT);
                        continue;
                    }

                    answerDouble = Double.parseDouble(answer);
                    valueCheckMessage = checkAndSetAnswerValue(item.getParameter(), answerDouble);
                } else if (answerType.equals(BOOLEAN_TYPE)) {
                    if (!checkAnswerType(answer, answerType)) {
                        System.out.println(BAD_ANSWER_FORMAT);
                        continue;
                    }

                    answerBoolean = Boolean.parseBoolean(answer);
                    valueCheckMessage = checkAndSetAnswerValue(item.getParameter(), answerBoolean);
                }

                if (!valueCheckMessage.equals(OK)) {
                    System.out.println(valueCheckMessage);
                    continue;
                }

                break;
            }
        }
        System.out.println(FORM_END);
    }

    public boolean isFilled() {
        if (this.height == INCORRECT_NUMBER) {
            return false;
        }

        if (this.weight == INCORRECT_NUMBER) {
            return false;
        }

        if (this.strokeVolume == INCORRECT_NUMBER) {
            return false;
        }

        if (this.heartRate == INCORRECT_NUMBER) {
            return false;
        }

        if (this.age == INCORRECT_NUMBER) {
            return false;
        }

        if (this.isWoman == null) {
            return false;
        }

        if (this.bloodPressureTreatment == null) {
            return false;
        }

        if (this.systolicBloodPressure == INCORRECT_NUMBER) {
            return false;
        }

        if (this.totalCholesterol == INCORRECT_NUMBER) {
            return false;
        }

        if (this.HDLCholesterol == INCORRECT_NUMBER) {
            return false;
        }

        if (this.isSmoker == null) {
            return false;
        }
        return true;
    }

    private ArrayList<FormItem> generateQuestions() {
        return new ArrayList<FormItem>(Arrays.asList(
                    new FormItem("age", "Zadajte vek v rokoch:", "int"),
                    new FormItem("isWoman", "Ste žena? (true/false):", "bool"),
                    new FormItem("height", "Zadajte výšku v cm:", "double"),
                    new FormItem("weight", "Zadajte hmotnosť v kg:", "double"),
                    new FormItem("strokeVolume", "Zadajte zdvihový objem srdca (objem krvi vytlačený jedným úderom srdca) v mL.", "double"),
                    new FormItem("heartRate", "Zadajte počet úderov srdca za minútu:", "double"),
                    new FormItem("bloodPressureTreatment", "Beriete lieky na krvný tlak? (true/false):", "bool"),
                    new FormItem("systolicBloodPressure", "Zadajte systolický krvný tlak v mmHg:", "double"),
                    new FormItem("totalCholesterol", "Zadajte celkový cholesterol v mg/dL:", "double"),
                    new FormItem("HDLCholesterol", "Zadajte HDL cholesterol (lipoproteín s vysokou hustotou) v mg/dL:", "double"),
                    new FormItem("isSmoker", "Ste fajčiar? (true/false):", "bool")
               ));
    }

    protected boolean checkAnswerType(String answer, String type) {
        if (type.equals(DOUBLE_TYPE)) {
            try {
                Double.parseDouble(answer);
                return true;
            }
            catch(NumberFormatException e) {
                return false;
            }
        }

        if (type.equals(BOOLEAN_TYPE)) {
            if (answer.equals("true") || answer.equals("false")) {
                return true;
            }
            return false;
        }

        if (type.equals(INT_TYPE)) {
            try {
                Integer.parseInt(answer);
                return true;
            }
            catch(NumberFormatException e) {
                return false;
            }
        }
        return false;
    }

    protected String checkAndSetAnswerValue(String parameter, double valueDouble) {

        switch (parameter) {
            case "height":
                if (valueDouble >= 1 && valueDouble <= 300) {
                    setHeight(valueDouble);
                    return OK;
                }
                return BAD_VALUE;
            case "weight":
                if (valueDouble >= 1 && valueDouble <= 800) {
                    setWeight(valueDouble);
                    return OK;
                }
                return BAD_VALUE;
            case "strokeVolume":
                if (valueDouble >= 1 && valueDouble <= 290) {
                    setStrokeVolume(valueDouble);
                    return OK;
                }
                return BAD_VALUE;
            case "heartRate":
                if (valueDouble >= 1 && valueDouble <= 215) {
                    setHeartRate(valueDouble);
                    return OK;
                }
                return BAD_VALUE;
            case "age":
                if (valueDouble >= 1 && valueDouble <= 160) {
                    setAge((int)valueDouble);
                    return OK;
                }
                return BAD_VALUE;
            case "systolicBloodPressure":
                if (valueDouble >= 30 && valueDouble <= 300) {
                    setSystolicBloodPressure(valueDouble);
                    return OK;
                }
                return BAD_VALUE;
            case "totalCholesterol":
                if (valueDouble >= 100 && valueDouble <= 900) {
                    setTotalCholesterol(valueDouble);
                    return OK;
                }
                return BAD_VALUE;
            case "HDLCholesterol":
                if (valueDouble >= 1 && valueDouble <= 150) {
                    setHDLCholesterol(valueDouble);
                    return OK;
                }
                return BAD_VALUE;
        }

        return BAD_PARAMETER;
    }

    protected String checkAndSetAnswerValue(String parameter, boolean valueBoolean) {
        switch (parameter) {
            case "isWoman":
                setWoman(valueBoolean);
                return OK;
            case "bloodPressureTreatment":
                setBloodPressureTreatment(valueBoolean);
                return OK;
            case "isSmoker":
                setSmoker(valueBoolean);
                return OK;
        }

        return BAD_PARAMETER;
    }
}
