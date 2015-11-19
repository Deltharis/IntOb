package nsga;

import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

/**
 * Created by Delth on 18.11.2015.
 */
public class Operators {

    /**
     * Oznaczenie stopnia zatłoczenia danego osobnika odbywa się w sposób następujący:
     * dla każdego kryterium wyznaczani są najbliżsi sąsiedzi tego osobnika.
     * Tak wyznaczone punkty traktowane są jako wierzchołki hiperkostki, dla której uśrednioną sumę długości krawędzi
     * przyjmuje się jako estymację stopnia zatłoczenia fragmentu przestrzeni reprezentowanego przez analizowanego osobnika.
     *
     * @param front population in which we look (entire population content or just certain Pareto front)
     * @param agent the one we want to get crowding level for? TODO should it be in first parameter too?
     * @return
     */
    public static double crowdingOperator(List<Agent> front, Agent agent) throws Exception {
        double crowdingDistance = 0.0d;

        List<Double> agentGenotype = agent.getGenotype();
        int N = agentGenotype.size();

        List<Double> edgeLengthList = new ArrayList<>();

        // sanity check no. 0 - do we have any agents in front?
        if (front.isEmpty()) throw new Exception("Front is empty!");

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // setting edge lengths to distances of first agent,
        // then iterate through the rest to find shortest distance for each dimension
        Iterator<Agent> frontIterator = front.iterator();
        Agent firstAgent = frontIterator.next();
        Iterator<Double> firstAgentGenotypeIterator = firstAgent.getGenotype().iterator();
        Iterator<Double> testedAgentGenotypeIterator = agentGenotype.iterator();

        while (firstAgentGenotypeIterator.hasNext() && testedAgentGenotypeIterator.hasNext()) {
            edgeLengthList.add(Math.abs(firstAgentGenotypeIterator.next() - testedAgentGenotypeIterator.next()));
        }

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // now, that we have distances in every dimension for first agent in front,
        // we try to find distances for every dimension by iterating through the rest of agents in the same manner
        while (frontIterator.hasNext()) {
            Agent probableNeighbor = frontIterator.next();
            List<Double> probableNeighborsGenotype = probableNeighbor.getGenotype();

            // sanity check no. 1 - size of genotype can't be less than one dimension
            if (probableNeighborsGenotype.size() < 1 || agentGenotype.size() < 1)
                throw new Exception("Genotype size is less than 1, IMPOSSIBRU!");
            // sanity check no. 2 - sizes of compared agents must be the same
            if (probableNeighborsGenotype.size() != agentGenotype.size())
                throw new Exception("Tested agents genotype size is not equal to front genotype size.");
            // sanity check no. 3 - agent can't be his own neighbor
            if (probableNeighborsGenotype.equals(agentGenotype)) continue;

            Iterator<Double> probableNeighborsGenotypeIterator = probableNeighborsGenotype.iterator();
            Iterator<Double> agentGenotypeIterator = agentGenotype.iterator();
            ListIterator<Double> newEdgeLengthIterator = edgeLengthList.listIterator();

            while (probableNeighborsGenotypeIterator.hasNext() && agentGenotypeIterator.hasNext() && newEdgeLengthIterator.hasNext()) {
                double probableNeighborsPosition = probableNeighborsGenotypeIterator.next();
                double agentsPosition = agentGenotypeIterator.next();
                double currentDistance = newEdgeLengthIterator.next();

                double newDistance = Math.abs(probableNeighborsPosition - agentsPosition);

                if (newDistance < currentDistance) {
                    newEdgeLengthIterator.set(newDistance);
                }
            }
        }

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // after tremendous search for shortest edges we calculate crowdingDistance as average edge length of hypercube
        for (Double edgeLength : edgeLengthList) {
            crowdingDistance += edgeLength;
        }
        crowdingDistance /= (double) N;

        return crowdingDistance;
    }

    /**
     * simplest crossover I could find. two go in, two new come out for a total of 4.
     *
     * @param parent1
     * @param parent2
     * @return
     */
    public static Pair<List<Double>, List<Double>> singlePointCrossover(List<Double> parent1, List<Double> parent2) {
        int size = parent1.size();
        int cutoff = size / 2; //it might clip things after the comma if it's not even, I'm cool with that. for ZDT - 14 I think?
        List<Double> child1 = new ArrayList<>();
        List<Double> child2 = new ArrayList<>();
        for (int i = 0; i < cutoff; i++) { //for ZDT - 0:13
            child1.add(parent1.get(i));
            child2.add(parent2.get(i));
        }
        for (int i = cutoff; i < size; i++) { //for ZDT - 14:29, total - 30, that's good.
            child1.add(parent2.get(i));
            child2.add(parent1.get(i));
        }
        return Pair.of(child1, child2);
    }

    /**
     * there could probably be a lot of them, we could use some nice looking solutions with inheritance and interfaces to easily swap mutation algorythms...
     * <p>
     * Or we could just use that one, it'll be fine.
     * <p>
     * changes a randomly chosen gene to a random value.
     * <p>
     * It is NOT safe, might modify the argument. If it's ever not wanted - change it, see if I care.
     *
     * @param genotype
     * @return
     */
    public static List<Double> mutate(List<Double> genotype) {
        Random r = new Random();
        int whichOne = r.nextInt(genotype.size() - 1); //choose which value to mutate
        genotype.set(whichOne, r.nextDouble()); //mutate
        return genotype;

    }
}
