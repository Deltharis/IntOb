package zdt.zdt3;

import nsga.Population;
import zdt.ZDTMasterController;

/**
 * Created by Delth on 20.11.2015.
 */
public class ZDT3MasterController extends ZDTMasterController {
    @Override
    protected Population newRandomPopulation(int size) {
        Population p = new ZDT3Population();
        p.initializePopulation(size);
        return p;
    }
}
