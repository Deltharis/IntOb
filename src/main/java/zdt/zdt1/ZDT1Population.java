package zdt.zdt1;

import nsga.Population;
import org.apache.commons.lang3.StringUtils;
import zdt.ZDTAgent;
import zdt.ZDTPopulation;

import java.util.List;

/**
 * Created by Delth on 18.11.2015.
 */
public class ZDT1Population extends ZDTPopulation {

    public ZDT1Population() {
    }

    public ZDT1Population(List<ZDTAgent> agents) {
        setAgents(agents);
    }

    @Override
    protected ZDTAgent createCorrectAgent() {
        return new ZDT1Agent();
    }

    @Override
    protected ZDTAgent createCorrectAgent(List<Double> genotype) {
        return new ZDT1Agent(genotype);
    }

    @Override
    protected Population createCorrectPopulation(List<ZDTAgent> agents) {
        return new ZDT1Population(agents);
    }

    @Override
    public String toString() {
        String agents = StringUtils.join(getAgents().toArray(), "\r\n");
        return "ZDT1Population{agents = " + agents + " }";
    }
}
