package computations;
import models.Form;

import java.security.InvalidParameterException;

public interface Computation {

    double getResult(Form form) throws InvalidParameterException;

    String getResultsInterpretation(double result);
}
