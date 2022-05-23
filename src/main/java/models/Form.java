package models;

public class Form {
    private int INCORRECT_NUMBER = -9999;

    private double height;
    private double weight;
    private double strokeVolume;
    private double heartRate;
    private int age;
    private boolean isWoman;
    private boolean bloodPressureTreatment;
    private double systolicBloodPressure;
    private double totalCholesterol;
    private double HDLCholesterol;
    private boolean isSmoker;

    public Form() {
        this.height = INCORRECT_NUMBER;
        this.weight = INCORRECT_NUMBER;
        this.strokeVolume = INCORRECT_NUMBER;
        this.heartRate = INCORRECT_NUMBER;
        age = INCORRECT_NUMBER;
        isWoman = false;
        bloodPressureTreatment = false;
        systolicBloodPressure = INCORRECT_NUMBER;
        totalCholesterol = INCORRECT_NUMBER;
        HDLCholesterol = INCORRECT_NUMBER;
       isSmoker = false;
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

    public void setHeartRate(int heartRate) {
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

    public void setHeartRate(double heartRate) {
        this.heartRate = heartRate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isWoman() {
        return isWoman;
    }

    public void setWoman(boolean woman) {
        isWoman = woman;
    }

    public boolean isBloodPressureTreatment() {
        return bloodPressureTreatment;
    }

    public void setBloodPressureTreatment(boolean bloodPressureTreatment) {
        this.bloodPressureTreatment = bloodPressureTreatment;
    }

    public double getSystolicBloodPressure() {
        return systolicBloodPressure;
    }

    public void setSystolicBloodPressure(double systolicBloodPressure) {
        this.systolicBloodPressure = systolicBloodPressure;
    }

    public double getTotalCholesterol() {
        return totalCholesterol;
    }

    public void setTotalCholesterol(double totalCholesterol) {
        this.totalCholesterol = totalCholesterol;
    }

    public double getHDLCholesterol() {
        return HDLCholesterol;
    }

    public void setHDLCholesterol(double HDLCholesterol) {
        this.HDLCholesterol = HDLCholesterol;
    }

    public boolean isSmoker() {
        return isSmoker;
    }

    public void setSmoker(boolean smoker) {
        isSmoker = smoker;
    }
}
