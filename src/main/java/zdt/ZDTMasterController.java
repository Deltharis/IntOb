package zdt;

import nsga.MasterController;

import java.util.*;

/**
 * Created by Delth on 18.11.2015.
 */
public abstract class ZDTMasterController extends MasterController {

    @Override
    protected Queue<ZDTAgent> crowdingSort(List<ZDTAgent> list) {
        List sortedAgentList = new ArrayList<>(list);
        Collections.sort(sortedAgentList, new ZDTAgent.CrowdedTournamentSelectionComparator());

        return new ArrayDeque<>(sortedAgentList);
    }
}
