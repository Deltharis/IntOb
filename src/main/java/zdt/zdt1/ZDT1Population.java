package zdt.zdt1;

import nsga.Population;
import zdt.ZDTAgent;
import zdt.ZDTPopulation;

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
