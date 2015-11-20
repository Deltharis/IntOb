package nsga;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Delth on 18.11.2015.
 */
public abstract class Population<T extends Agent> {

    protected List<T> agents = new ArrayList<>();
    protected Queue<List<T>> fronts = new LinkedList<>();

    /**
     * let the size be divisible by 8, or hacky children generation won't work.
     *
     * @param size
     */
    public abstract void initializePopulation(int size);
    public abstract void sort();

    /**
     * #theinternet
     * To obtain child solution, firstly the parents are selected from the current population (i.e. the parent population as you called it)
     * using tournament selection, the comparison criteria in the tournament selection are again based on non-dominance comparison using pareto objective
     * (or possibly include crowding distance comparison and constraint dominance if non-dominance comparison fail to select the better parent from the tournament).
     * Once the parents are selected using tournament selection as mentioned, using crossover and mutation operators on the selected parents to produce the offspring solutions.
     * <p>
     * also: http://stackoverflow.com/questions/10938979/nsga-2-multi-objetive-genethic-algorithm-anyone-could-give-me-a-simple-explana
     * <p>
     * it would seem we do tournament based on domination and magical non-crowdedness, chossing who knows how many
     * put them into mating pool, crossover (somehow) and mutate (somehow) untill we get size N
     * <p>
     * fuck those guys.
     *
     * @return
     */
    public abstract Population generateChildPopulation();
    public abstract Population add(Population addend);

    public void add(T a) {
        agents.add(a);
    }

    public List<T> getAgents() {
        return agents;
    }

    public void setAgents(List<T> agents) {
        this.agents = agents;
    }

    public int getSize(){
        return agents.size();
    }

    public List<T> getNextFront() {
        return fronts.poll();
    }
}
