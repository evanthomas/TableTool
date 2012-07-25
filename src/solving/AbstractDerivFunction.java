package solving;

import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;

public interface AbstractDerivFunction extends FirstOrderDifferentialEquations {
    public double[] initialConditions(double v);
}
