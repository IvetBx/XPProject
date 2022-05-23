package computations;

import models.Form;
import java.lang.Math;

import java.security.InvalidParameterException;

public class FraminghamRiskCalculator implements Computation {

    private double baseInDeathProbabilityForWomen = 0.98767;
    private double baseInDeathProbabilityForMen = 0.9402;
    private int maxAgeForWomen = 78;
    private int maxAgeForMen = 70;
    private double bpTreatment;
    private double smoker;


    @Override
    public double getResult(Form form) throws InvalidParameterException {
        double riskScore;
        bpTreatment = getDouble(form.isBloodPressureTreatment());
        smoker = getDouble(form.isSmoker());

        if(form.isWoman()){
            riskScore = getRiskScoreForWoman(form);
            return getDeathProbability(riskScore, baseInDeathProbabilityForWomen) * 100;
        }

        riskScore = getRiskScoreForMan(form);
        return getDeathProbability(riskScore, baseInDeathProbabilityForMen) * 100;
    }

    @Override
    public String getResultsInterpretation(double result) {
        /*
Risk is considered low if the FRS is less than 10%, moderate if it is 10% to 19%, and high if it is 20% or higher. Decisions based on the Framingham tables are made every day in office practice.*/
        return null;
    }

    private double getRiskScoreForMan(Form form){
        double age = getLogProduct(FraminghamConst.ageM, form.getAge());
        double cholesterol = getLogProduct(FraminghamConst.totalCholesterolM, form.getTotalCholesterol());
        double hdl = getLogProduct(FraminghamConst.HDLCholesterolM, form.getHDLCholesterol());
        double systolicBP = getLogProduct(FraminghamConst.systolicBPM, form.getSystolicBloodPressure());
        double treatmentBP = getProduct(FraminghamConst.treatmentBPM, bpTreatment);
        double smoker2 = getProduct(FraminghamConst.smokerM, smoker);
        double age2 = getLogProduct(FraminghamConst.ageCholesterolM, form.getAge());
        double ageCholesterol = getLogProduct(age2, form.getTotalCholesterol());
        double correctAge = getAgeUsedInEquationWithSmoking(form.getAge(), false);
        double ageSmoker = getLogProduct(FraminghamConst.ageSmokerM * smoker, correctAge);
        double twiceAge = getLogProduct(FraminghamConst.ageTwiceM, form.getAge());
        double twiceAge2 = getLogProduct(twiceAge, form.getAge());

        return age + cholesterol + hdl + systolicBP + treatmentBP + smoker2 + ageCholesterol + ageSmoker + twiceAge2 - FraminghamConst.endM;
    }

    public double getRiskScoreForWoman(Form form){
        bpTreatment = getDouble(form.isBloodPressureTreatment());
        smoker = getDouble(form.isSmoker());

        double age = getLogProduct(FraminghamConst.ageW, form.getAge());
        double cholesterol = getLogProduct(FraminghamConst.totalCholesterolW, form.getTotalCholesterol());
        double hdl = getLogProduct(FraminghamConst.HDLCholesterolW, form.getHDLCholesterol());
        double systolicBP = getLogProduct(FraminghamConst.systolicBPW, form.getSystolicBloodPressure());
        double treatmentBP = getProduct(FraminghamConst.treatmentBPW, bpTreatment);
        double smoker2 = getProduct(FraminghamConst.smokerW, smoker);
        double age2 = getLogProduct(FraminghamConst.ageCholesterolW, form.getAge());
        double ageCholesterol = getLogProduct(age2, form.getTotalCholesterol());
        double correctAge = getAgeUsedInEquationWithSmoking(form.getAge(), true);
        double ageSmoker = getLogProduct(FraminghamConst.ageSmokerW * smoker, correctAge);
        return age + cholesterol + hdl + systolicBP + treatmentBP + smoker2 + ageCholesterol + ageSmoker - FraminghamConst.endW;
    }

    private double getDeathProbability(double riskScore, double baseAccordingToSex){
        return 1 -  Math.pow(baseAccordingToSex, Math.exp(riskScore));
    }

    public int getAgeUsedInEquationWithSmoking(int age, boolean isWoman){
        if(isWoman){
            return Math.min(age, maxAgeForWomen);
        }
        return Math.min(age, maxAgeForMen);
    }

    private double getLogProduct(double constant, double patientInfo){
        return constant * Math.log(patientInfo);
    }

    private double getProduct(double constant, double patientInfo){
        return constant * patientInfo;
    }

    private double getDouble(boolean value){
        if(value){
            return 1;
        }
        return 0;
    }
}
