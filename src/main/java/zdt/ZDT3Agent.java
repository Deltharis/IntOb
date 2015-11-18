package zdt;

/**
 * Created by Delth on 18.11.2015.
 */
public class ZDT3Agent extends ZDTAgent {
    @Override
    protected double h(double f1, double g) {
        return 1 - Math.sqrt(f1 / g) - (f1 / g) * Math.sin(18 * f1 * Math.PI);
    }

    @Override
    protected double g() {
        double value = 1;
        for (Double x : genotype) {
            if (genotype.get(0).equals(x)) { //skip first argument, it's not used here
                continue;
            }
            value += (9 / (NUMBER_OF_ARGUMENTS - 1)) * x;

        }
        return value;
    }
}
