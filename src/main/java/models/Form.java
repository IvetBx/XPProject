package models;

public class Form {
    private int INCORRECT_NUMBER = -9999;

    private double height;
    private double weight;
    private double strokeVolume;
    private double heartRate;

    public Form() {
        this.height = INCORRECT_NUMBER;
        this.weight = INCORRECT_NUMBER;
        this.strokeVolume = INCORRECT_NUMBER;
        this.heartRate = INCORRECT_NUMBER;
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
}
