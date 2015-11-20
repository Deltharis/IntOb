package zdt.zdt2;

import nsga.Population;
import org.apache.commons.lang3.StringUtils;
import zdt.ZDTAgent;
import zdt.ZDTPopulation;

import java.util.List;

/**
 * Created by Delth on 19.11.2015.
 */
public class ZDT2Population extends ZDTPopulation {

    public ZDT2Population() {
    }

    public ZDT2Population(List<ZDTAgent> agents) {
        setAgents(agents);
    }

    @Override
    protected ZDTAgent createCorrectAgent() {
        return new ZDT2Agent();
    }

    @Override
    protected ZDTAgent createCorrectAgent(List<Double> genotype) {
        return new ZDT2Agent(genotype);
    }

    @Override
    protected Population createCorrectPopulation(List<ZDTAgent> agents) {
        return new ZDT2Population(agents);
    }

    @Override
    public String toString() {
        String agents = StringUtils.join(getAgents().toArray(), "\r\n");
        return "ZDT1Population{agents = " + agents + " }";
    }
}
