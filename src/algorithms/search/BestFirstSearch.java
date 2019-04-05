package algorithms.search;

import java.util.PriorityQueue;

public class BestFirstSearch extends BreadthFirstSearch {

    public BestFirstSearch() {
        super.q = new PriorityQueue<>(new AStateComparator());
    }

    @Override
    public String getName() {
        return "Best First Search";
    }
}
