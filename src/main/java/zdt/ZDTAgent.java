package zdt;

import nsga.Agent;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

/**
 * Not sure if double is enough - maybe will need to change to something better? //TODO #lowpriority
 * Don't know how to make it so that interface has comparable and it knows it can only compare ZDTAgents //TODO #lowpriority
 * Probably needs a configurable amount of arguments in genotype? Dunno, didn't find it in the doc. //TODO
 * <p>
 * Created by Delth on 18.11.2015.
 */
public abstract class ZDTAgent implements Agent, Comparable<ZDTAgent> {

    public static final int NUMBER_OF_ARGUMENTS = 4;

    private List<Double> genotype;
    private Pair<Double, Double> fitness;

    /**
     * needs to be MINIMIZED. Maybe minus signs to make it work as normal? Not sure.
     *
     * @return
     */
    public Pair<Double, Double> getFitness() {
        if (fitness == null) {
            fitness = Pair.of(f1(), f2());
        }
        return fitness;
    }

    /**
     * asdded minus signs. The bigger the better
     *
     * @return
     */
    public Pair<Double, Double> getNormalizedFitness() {
        if (fitness == null) {
            fitness = Pair.of(f1(), f2());
        }
        return Pair.of(-fitness.getLeft(), -fitness.getRight());
    }

    private Double f1() {
        return genotype.get(0); //not true for ZDT5 and ZDT6, don't care
    }

    protected Double f2() {
        double g = g();
        double h = h(f1(), g);
        return g * h;
    }

    protected abstract double h(double f1, double g); //TODO for ZDT1-3

    protected abstract double g(); //TODO for ZDT1-3


    /**
     * needs to indicate Domination, as definition 1.2 shows it
     * <p>
     * could be written more universally... but fuck it
     *
     * @param agent
     * @return
     */
    @Override
    public int compareTo(ZDTAgent agent) {
        Pair<Double, Double> myFitness = getNormalizedFitness();
        Pair<Double, Double> hisFitness = agent.getNormalizedFitness();
        if (myFitness.getLeft() >= hisFitness.getLeft() && //1 not worse
                myFitness.getRight() >= hisFitness.getRight() && //2 not worse
                (myFitness.getLeft() > hisFitness.getLeft() || myFitness.getRight() > hisFitness.getRight())) { //better in at least one respect
            return 1;
        } else if (myFitness.getLeft() <= hisFitness.getLeft() &&
                myFitness.getRight() <= hisFitness.getRight() &&
                (myFitness.getLeft() < hisFitness.getLeft() || myFitness.getRight() < hisFitness.getRight())) {
            return -1;
        } else {
            return 0;
        }

    }
}
