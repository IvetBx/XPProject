package computations;
import models.Form;
import java.lang.Math;
import java.math.BigDecimal;
import java.math.RoundingMode;


import java.security.InvalidParameterException;
import java.util.AbstractMap;

public class CardiacIndexCalculator implements Computation {
    private double lowerLimit = 2.0;
    private double upperLimit = 4.0;
    private int INCORRECT_NUMBER = -9999;

   public CardiacIndexCalculator() {}

    @Override
    public double getResult(Form form) throws InvalidParameterException {
        AbstractMap.SimpleEntry<Boolean, String> checkedForm = checkForm(form);

        if (checkedForm.getKey()) {
            throw new InvalidParameterException(checkedForm.getValue());
        }

        double cardiacOutput = getCardiacOutput(form.getStrokeVolume(), form.getHeartRate());
        double bodySurfaceArea = getBodySurfaceArea(form.getHeight(), form.getWeight());
        double cardiacIndex = cardiacOutput / bodySurfaceArea;

        double roundedCardiacIndex = new BigDecimal(cardiacIndex).setScale(2, RoundingMode.HALF_UP).doubleValue();
        return roundedCardiacIndex;
    }

    @Override
    public String getResultsInterpretation(double result) {
        if (result < lowerLimit) {
            return "Váš výsledok sa rovná " + result + " l/min/m², čo poukazuje na kardiogénny šok.";
        }
        if (result > upperLimit) {
            return "Váš výsledok sa rovná " + result + " l/min/m², je vysoko mimo normálneho rozsahu srdcového indexu.";
        } else {
            return "Váš výsledok sa rovná " + result + " l/min/m², je to v rámci normálneho rozsahu srdcového indexu.";
        }
    }

    private double getCardiacOutput(double strokeVolume, double heartRate) {
       return (strokeVolume * heartRate) / 1000;
    }

    private double getBodySurfaceArea(double height, double weight) {
        return 0.024265 * Math.pow(height, 0.3964) * Math.pow(weight, 0.5378);
    }

    private AbstractMap.SimpleEntry<Boolean, String> checkForm(Form form) {
        String message = "";
        Boolean isIncorrect = false;

        if (form.getHeight() == INCORRECT_NUMBER) {
            isIncorrect = true;
            message += "Parametru VÝŠKA nebola priradená hodnota.\n";
        }

        if (form.getWeight() == INCORRECT_NUMBER) {
            isIncorrect = true;
            message += "Parametru VÁHA nebola priradená hodnota.\n";
        }

        if (form.getStrokeVolume() == INCORRECT_NUMBER) {
            isIncorrect = true;
            message += "Parametru OBJEM ZDVIHU (objem krvi vytlačený jedným úderom srdca) nebola priradená hodnota.\n";
        }

        if (form.getHeartRate() == INCORRECT_NUMBER) {
            isIncorrect = true;
            message += "Parametru TLKOT SRDCA nebola priradená hodnota.\n";
        }

        AbstractMap.SimpleEntry<Boolean, String> pair = new AbstractMap.SimpleEntry<>(isIncorrect, message);
        return pair;
    }
}
