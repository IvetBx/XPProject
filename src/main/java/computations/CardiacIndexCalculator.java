package computations;
import models.Form;
import java.lang.Math;
import java.math.BigDecimal;
import java.math.RoundingMode;

import java.security.InvalidParameterException;

public class CardiacIndexCalculator implements Computation {
    private String NOT_FILLED_FORM = "Nastala chyba, dotazník nie je vyplnený!";
    private double lowerLimit = 2.0;
    private double upperLimit = 4.0;

   public CardiacIndexCalculator() {}

    @Override
    public double getResult(Form form) throws InvalidParameterException {
        if (!form.isFilled()) {
            throw new InvalidParameterException(NOT_FILLED_FORM);
        }

        double cardiacOutput = getCardiacOutput(form.getStrokeVolume(), form.getHeartRate());
        double bodySurfaceArea = getBodySurfaceArea(form.getHeight(), form.getWeight());
        double cardiacIndex = cardiacOutput / bodySurfaceArea;
        double roundedCardiacIndex = new BigDecimal(cardiacIndex).setScale(2, RoundingMode.HALF_UP).doubleValue();

        return roundedCardiacIndex;
    }

    @Override
    public String getResultsInterpretation(double result) {
       String interpretation = "Váš výsledok sa rovná " + result + " l/min/m², ";

       if (result < this.lowerLimit) {
           return interpretation + "čo poukazuje na kardiogénny šok.";
       }

       if (result > this.upperLimit) {
           return interpretation + "je vysoko mimo normálneho rozsahu srdcového indexu.";
       }

       return interpretation + "je to v rámci normálneho rozsahu srdcového indexu.";
    }

    private double getCardiacOutput(double strokeVolume, double heartRate) {
       return (strokeVolume * heartRate) / 1000;
    }

    private double getBodySurfaceArea(double height, double weight) {
        return 0.024265 * Math.pow(height, 0.3964) * Math.pow(weight, 0.5378);
    }
}
