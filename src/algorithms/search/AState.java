package algorithms.search;

import java.util.Comparator;

public abstract class AState {

    protected AState prev;
    protected Object curr;
    protected int cost;

    public AState(Object o, int cost) {
        this.prev = null;
        this.curr = o;
        this.cost = cost;
    }

    public Object getData (){
        return curr;
    }

    @Override
    public String toString() {
        return curr.toString();
    }
}
