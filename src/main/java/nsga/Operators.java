package nsga;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Delth on 18.11.2015.
 */
public class Operators {

    /**
     * Oznaczenie stopnia zatłoczenia danego osobnika odbywa się w sposób następujący:
     * dla każdego kryterium wyznaczani sąnajbliżsi sąsiedzi tego osobnika.
     * Tak wyznaczone punkty traktowane są jako wierzchołki hiperkostki, dla której uśrednioną sumę długości krawędzi
     * przyjmuje się jako estymację stopnia zatłoczenia fragmentu przestrzeni reprezentowanego przez analizowanego osobnika.
     *
     * @param front population in which we look (entire population content or just certain Pareto front)
     * @param agent the one we want to get crowding level for? TODO should it be in first parameter too?
     * @return
     */
    public static double crowdingOperator(List<Agent> front, Agent agent) {
        //TODO
        return 0;
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
        int cutoff = size / 2; //it might clip things after the comma if it's not even, I'm cool with that.
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
        genotype.set(whichOne, r.nextDouble());
        return genotype;

    }
}
