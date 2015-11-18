package zdt;

import nsga.Population;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * ZDT1,2,3 is restricted [0,1] in values
 * <p>
 * Created by Delth on 18.11.2015.
 */
public class ZDTPopulation extends Population<ZDTAgent> {
    @Override
    public Population generatePopulation(int size) {
        //TODO find a way to make this method work for whatever agent, or just implement in subclasses
        //oh, and use randomDoubleList, cause why not
        return null;
    }

    protected ArrayList<Double> randomDoubleList(int n) {
        ArrayList<Double> list = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            list.add(random.nextDouble());
        }
        return list;
    }

    /**
     * this method could be in the superclass - if I could make comparables work the way I wanna. But I can't. TODO #lowpriority
     */
    @Override
    public void sort() {
        fronts = new LinkedList<>();
        List<ZDTAgent> tmp = new ArrayList<>();
        tmp.addAll(agents);
        int count = 0;
        while (count < agents.size()) { //need to use up all agents
            List<ZDTAgent> front = new ArrayList<>(); //new front
            for (ZDTAgent zdtAgent : tmp) {
                boolean dominated = false;
                for (ZDTAgent zdtAgent1 : tmp) {
                    if (zdtAgent.compareTo(zdtAgent1) < 0) { //if it's dominated by something, we don't want it anymore
                        dominated = true;
                    }
                }
                if (!dominated) { //if it wasn't dominated add to the current front, and note we added an agent for while condition
                    front.add(zdtAgent);
                    count++;
                }
            }
            tmp.removeAll(front); //TODO can I do that? or do I need to count dominance times and assign fronts based on that as per script? It SHOULD be equivalent, but dunno
            fronts.add(front);
        }
    }

    @Override
    public Population generateChildPopulation() {
        //TODO find a way to make this method work for whatever agent, or just implement in subclasses
        return null;
    }

    //TODO #lowpriority make this method return a copy maybe? or create a method for copy? would be prittier. Should work either way.
    @Override
    public Population add(Population addend) {
        if (!(addend instanceof ZDTPopulation)) {
            throw new RuntimeException("Don't add different populations together!"); //TODO will that work, or will addend be of type ZDT_number_Population and crash? I can't into java.
        }
        agents.addAll(addend.getAgents());
        return this;
    }
}
