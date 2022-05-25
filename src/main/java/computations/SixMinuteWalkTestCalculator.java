package computations;

import models.Form;

import java.security.InvalidParameterException;

public class SixMinuteWalkTestCalculator implements Computation {
    private double MEN_HEIGHT = 7.57;
    private double MEN_AGE = 5.02;
    private double MEN_WEIGHT = 1.76;
    private double MEN_SHIFT = -309;

    private double MEN_LOWER_SHIFT= 153;

    private double WOMEN_HEIGHT = 2.11;
    private double WOMEN_AGE = 2.29;
    private double WOMEN_WEIGHT = 5.78;
    private double WOMEN_SHIFT = 667;

    private double WOMEN_LOWER_SHIFT= 139;


    @Override
    public double getResult(Form form) throws InvalidParameterException {
        if(form.isWoman()){
            return calculateResultWoman(form.getHeight(), form.getAge(), form.getWeight());
        }
        return calculateResultMan(form.getHeight(), form.getAge(), form.getWeight());
    }

    @Override
    public String getResultsInterpretation(double result) {
        return "Za 6 minút by ste mali optimálne prejsť " + result + "m";
    }

    private double calculateResultMan(double height, int age, double weight){
        return (int)((this.MEN_HEIGHT * height) - (this.MEN_AGE * age) - (this.MEN_WEIGHT * weight) + this.MEN_SHIFT);
    }

    private double calculateResultWoman(double height, int age, double weight){
        return (int)((this.WOMEN_HEIGHT * height) - (this.WOMEN_WEIGHT * age) - (this.WOMEN_AGE * weight) + this.WOMEN_SHIFT);
    }

}
