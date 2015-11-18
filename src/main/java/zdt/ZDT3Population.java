package zdt;

/**
 * Created by Delth on 19.11.2015.
 */

import nsga.Population;

public class ZDT3Population extends ZDTPopulation {

    @Override
    protected ZDTAgent createCorrectAgent() {
        return new ZDT3Agent();
    }

    @Override
    public Population generateChildPopulation() {
        return null; //TODO
    }
}
