package zdt;

import nsga.Population;

/**
 * Created by Delth on 19.11.2015.
 */
public class ZDT2Population extends ZDTPopulation {

    @Override
    protected ZDTAgent createCorrectAgent() {
        return new ZDT2Agent();
    }

    @Override
    public Population generateChildPopulation() {
        return null; //TODO
    }
}
