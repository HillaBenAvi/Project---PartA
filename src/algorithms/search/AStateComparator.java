package algorithms.search;

import java.util.Comparator;

public class AStateComparator implements Comparator<AState> {
    @Override
    public int compare(AState o1, AState o2) {
        return o1.cost-o2.cost;
    }
}
