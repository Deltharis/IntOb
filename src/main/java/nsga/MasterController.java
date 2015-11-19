package nsga;

import zdt.ZDTAgent;

import java.util.List;
import java.util.Queue;

/**
 * Created by Delth on 18.11.2015.
 */
public abstract class MasterController {

    protected static final int POPULATION_SIZE = 10;

    protected Population currentPopulation;
    protected Population childPopulation;
    protected Population sum;
    protected Population nextPopulation;
    protected boolean endCondition = false;

    public void start() {
        init();
        while (!endCondition) { //TODO no end condition?
            step1();
            step2();
            List<ZDTAgent> leftOverFront = step3();
            step4(leftOverFront);
            step5();
        }
    }

    /**
     * Utwórz w sposób losowy populację początkową P0 o rozmiarze N
     * <p>
     * a) Posortuj populację P0 zgodnie z kolejnymi stopniami zdominowania jej
     * <p>
     * b) Wykorzystując selekcję turniejową z operatorem zatłoczenia crowded tournament selection operator,
     * który omówiony zostanie poniżej, a także osobników. operatory rekombinacji oraz mutacji
     * utwórz populację potomną Q0 także o rozmiarze N.
     */
    protected void init() {
        currentPopulation = newRandomPopulation(POPULATION_SIZE);
        currentPopulation.sort();
        childPopulation = currentPopulation.generateChildPopulation();
    }

    /**
     * Utwórz populację Rt = Pt ∪Qt o rozmiarze 2N stanowiącą sumę populacji osobników rodzicielskich Pt
     * oraz osobników potomnych Qt
     * <p>
     * a) Przeprowadź dla populacji Rt sortowanie osobników zgodnie z kolejnymi stopniami zdominowania,
     * tworząc w ten sposób kolejne fronty Fi gdzie
     * <p>
     * i = 1, 2, . . .
     */
    private void step1() {
        sum = currentPopulation.add(childPopulation);
        sum.sort();
    }

    /**
     * Zdefiniuj populację Pt+1 jako zbiór pusty: Pt+1 = ∅, zdefiniuj licznik i = 1.
     */
    private void step2() {
        nextPopulation = newRandomPopulation(0);
    }

    /**
     * Dokonuj przypisania Pt+1 = Pt+1∪Fi oraz i = i+1 dopóki ilość osobników w zbiorze Pt+1 ∪ Fi jest niewiększa od N.
     * <p>
     * jeśli po dodaniu frontu ma wyjść więcej niż N:
     * do populacji Pt+1 trafia tylko tyle wybranych osobników z aktualnie przetwarzanego podzbioru (n-frontu)
     * populacji Rt aby rozmiar populacji Pt+1 był równy N.
     */
    private List<ZDTAgent> step3() {
        List<ZDTAgent> front = sum.getNextFront();
        while (front.size() < (POPULATION_SIZE - nextPopulation.getSize())) {
            while (nextPopulation.getSize() < POPULATION_SIZE && front.size() > 0) {
                nextPopulation.add(front.get(0));
                front.remove(0);
            }
            front = sum.getNextFront();
        }
        return front;
    }

    /**
     * Jeśli |Pt+1| < N przeprowadź na zbiorze Fi sortowanie zgodnie ze stopniem zatłoczenia (tzw. Crowding-sort).
     * Następnie włącz do zbioru Pt+1 N − |Pt+1|najlepszych (najmniej zatłoczonych) osobników ze zbioru Fi
     *
     * @param front Fi
     */
    private void step4(List<ZDTAgent> front) {
        if (nextPopulation.getSize() != POPULATION_SIZE) {
            Queue<ZDTAgent> queue = crowdingSort(front);
            while (nextPopulation.getSize() != POPULATION_SIZE) {
                nextPopulation.add(queue.poll());
            }
        }
    }

    /**
     * Wykorzystując selekcję turniejową z operatorem zatłoczenia crowded tournament selection operator,
     * a także operatory rekombinacji oraz mutacji w oparciu o populację Pt+1
     * utwórz populację potomną Qt+1 o rozmiarze N. Dokonaj podstawienia t = t + 1. Przejdź do kroku 1.
     */
    private void step5() {
        currentPopulation = nextPopulation;
        childPopulation = currentPopulation.generateChildPopulation();
        nextPopulation = null;
        sum = null;
    }

    protected abstract Population newRandomPopulation(int size);

    protected abstract Queue<ZDTAgent> crowdingSort(List<ZDTAgent> list);
}
