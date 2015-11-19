package zdt.zdt3;

/**
 * Created by Delth on 19.11.2015.
 */

import nsga.Population;
import zdt.ZDTAgent;
import zdt.ZDTPopulation;

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
