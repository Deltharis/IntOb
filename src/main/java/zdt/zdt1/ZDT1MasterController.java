package zdt.zdt1;

import nsga.Population;
import zdt.ZDTMasterController;

/**
 * Created by Delth on 20.11.2015.
 */
public class ZDT1MasterController extends ZDTMasterController {
    @Override
    protected Population newRandomPopulation(int size) {
        Population p = new ZDT1Population();
        p.initializePopulation(size);
        return p;
    }
}
