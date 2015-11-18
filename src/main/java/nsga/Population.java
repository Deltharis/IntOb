package nsga;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Delth on 18.11.2015.
 */
public abstract class Population<T extends Agent> {

    protected List<T> agents;
    protected Queue<List<T>> fronts = new LinkedList<>();

    public abstract Population generatePopulation(int size);
    public abstract void sort();
    public abstract Population generateChildPopulation();
    public abstract Population add(Population addend);

    public void add(T a) {
        agents.add(a);
    }

    public List<T> getAgents() {
        return agents;
    }


    public int getSize(){
        return agents.size();
    }

    public List<T> getNextFront() {
        return fronts.poll();
    }
}
