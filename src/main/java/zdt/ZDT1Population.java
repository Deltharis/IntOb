package zdt;

import nsga.Population;

/**
 * Created by Delth on 18.11.2015.
 */
public class ZDT1Population extends ZDTPopulation {


    @Override
    protected ZDTAgent createCorrectAgent() {
        return new ZDT1Agent();
    }

    @Override
    public Population generateChildPopulation() {
        return null; //TODO
    }
}
