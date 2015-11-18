package zdt;

import nsga.Agent;
import nsga.MasterController;

import java.util.List;
import java.util.Queue;

/**
 * Created by Delth on 18.11.2015.
 */
public abstract class ZDTMasterController extends MasterController {

    @Override
    protected Queue<Agent> crowdingSort(List<Agent> list) {
        //TODO found in google what it is ("Crowding sort"), can't understand a word.
        return null;
    }
}
