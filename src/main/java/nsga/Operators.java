package nsga;

import java.util.List;

/**
 * Created by Delth on 18.11.2015.
 */
public class Operators {

    /**
     * Oznaczenie stopnia zatłoczenia danego osobnika odbywa się w sposób następujący:
     * dla każdego kryterium wyznaczani sąnajbliżsi sąsiedzi tego osobnika.
     * Tak wyznaczone punkty traktowane są jako wierzchołki hiperkostki, dla której uśrednioną sumę długości krawędzi
     * przyjmuje się jako estymację stopnia zatłoczenia fragmentu przestrzeni reprezentowanego przez analizowanego osobnika.
     *
     * @param front population in which we look (entire population content or just certain Pareto front)
     * @param agent the one we want to get crowding level for? TODO should it be in first parameter too?
     * @return
     */
    public static double crowdingOperator(List<Agent> front, Agent agent) {
        //TODO
        return 0;
    }
}
