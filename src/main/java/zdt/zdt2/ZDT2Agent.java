package zdt.zdt2;

import zdt.ZDTAgent;

/**
 * Created by Delth on 18.11.2015.
 */
public class ZDT2Agent extends ZDTAgent {
    @Override
    protected double h(double f1, double g) {
        return (1 - Math.pow(f1 / g, 2)); //^2
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
