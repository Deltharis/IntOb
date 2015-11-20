package zdt.zdt2;

import nsga.Population;
import zdt.ZDTMasterController;

/**
 * Created by Delth on 20.11.2015.
 */
public class ZDT2MasterController extends ZDTMasterController {
    @Override
    protected Population newRandomPopulation(int size) {
        Population p = new ZDT2Population();
        p.initializePopulation(size);
        return p;
    }
}
