package nsga;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Delth on 18.11.2015.
 */
public abstract class Population {

    private List<Agent> agents;
    private Queue<List<Agent>> fronts = new LinkedList<>();

    public abstract Population generatePopulation(int size);
    public abstract void sort();
    public abstract Population generateChildPopulation();
    public abstract Population add(Population addend);
    public void add(Agent a){
        agents.add(a);
    }


    public int getSize(){
        return agents.size();
    }

    public List<Agent> getNextFront(){
        return fronts.poll();
    }
}
