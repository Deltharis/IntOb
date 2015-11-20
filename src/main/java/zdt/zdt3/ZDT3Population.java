package zdt.zdt3;

/**
 * Created by Delth on 19.11.2015.
 */

import nsga.Population;
import org.apache.commons.lang3.StringUtils;
import zdt.ZDTAgent;
import zdt.ZDTPopulation;

import java.util.List;

public class ZDT3Population extends ZDTPopulation {

    public ZDT3Population() {
    }

    public ZDT3Population(List<ZDTAgent> agents) {
        setAgents(agents);
    }

    @Override
    protected ZDTAgent createCorrectAgent() {
        return new ZDT3Agent();
    }

    @Override
    protected ZDTAgent createCorrectAgent(List<Double> genotype) {
        return new ZDT3Agent(genotype);
    }

    @Override
    protected Population createCorrectPopulation(List<ZDTAgent> agents) {
        return new ZDT3Population(agents);
    }

    @Override
    public String toString() {
        String agents = StringUtils.join(getAgents().toArray(), "\r\n");
        return "ZDT1Population{agents = " + agents + " }";
    }
}
