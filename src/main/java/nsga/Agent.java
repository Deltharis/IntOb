package nsga;

import java.util.List;

/**
 * Created by Delth on 18.11.2015.
 * <p>
 * Needs overriding compareTo to indicate domination.
 */

public interface Agent {
    List<Double> getGenotype();
    Double getCrowdingDistance();
}
