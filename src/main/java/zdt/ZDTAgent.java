package zdt;

import nsga.Agent;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Comparator;
import java.util.List;

/**
 * Not sure if double is enough - maybe will need to change to something better? //TODO #lowpriority
 * Don't know how to make it so that interface has comparable and it knows it can only compare ZDTAgents //TODO #lowpriority
 * <p>
 * Created by Delth on 18.11.2015.
 */
public abstract class ZDTAgent implements Agent, Comparable<ZDTAgent> {

    public static final int NUMBER_OF_ARGUMENTS = 30; //defined in definition, same for 1-3

    protected List<Double> genotype;
    protected Pair<Double, Double> fitness;
    protected Double crowdingDistance;

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
     * added minus signs. The bigger the better
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
        return genotype.get(0); //not true for ZDT5 and ZDT6, don't care (YET!)
    }

    protected Double f2() {
        double g = g();
        double h = h(f1(), g);
        return g * h;
    }

    protected abstract double h(double f1, double g);

    protected abstract double g();

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

    @Override
    public List<Double> getGenotype() {
        return genotype;
    }

    public void setGenotype(List<Double> genotype) {
        this.genotype = genotype;
    }

    @Override
    public Double getCrowdingDistance(){
        return crowdingDistance;
    }

    public void setCrowdingDistance(Double crowdingDistance) {
        this.crowdingDistance = crowdingDistance;
    }

    /**
     * { r(i) < r(j)   ∨   ( d(i) > d(j)  ∧  r(i) = r(j) ) }.
     * <p>
     * "Zgodnie z pierwszą częścią warunku, zwycięzcą turnieju staje się osobnik charakteryzowany przez niższy
     * stopień zdominowania. W myśl zaś drugiej części warunku, w przypadku gdy osobniki charakteryzowane są
     * przez ten sam stopień zdominowania (są zlokalizowane na tym samym n-froncie), zwycięzcą staje się
     * osobnik zlokalizowany w mniej zatłoczonym fragmencie przestrzeni przeszukiwań."
     */
    public static class CrowdedTournamentSelectionComparator implements Comparator<ZDTAgent> {
        @Override
        public int compare(ZDTAgent o1, ZDTAgent o2) {
            if (o1.compareTo(o2) == 1)
                return 1;
            else if (o1.compareTo(o2) == -1)
                return -1;
            else if (o1.compareTo(o2) == 0) {
                if (o1.getCrowdingDistance()
                        <
                        o2.getCrowdingDistance())
                    return 1;
                else if (o1.getCrowdingDistance() > o2.getCrowdingDistance())
                    return -1;
            }
            return 0;
        }
    }
}
