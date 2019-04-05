package algorithms.search;

import java.util.*;

public class DepthFirstSearch extends ASearchingAlgorithm {
    HashMap<String, AState> statesVisited ;

    public DepthFirstSearch() {
        this.statesVisited = new HashMap<>();
    }

    @Override
    public Solution solve(ISearchable iSearch) {
        DFS(iSearch, iSearch.getStartState());
        Solution sol = new Solution ();
        AState goal = iSearch.getGoalState();
        AState currState = statesVisited.get(goal.toString());
        while(currState.prev != null){
            sol.addState(currState);
            currState= currState.prev;
        }
        sol.addState(currState);
        this.sol = sol;

        return sol;
    }

    @Override
    public String getName() {
        return "Depth First Search";
    }

    private void DFS(ISearchable search, AState currState) {
        if(search==null || currState == null){
            return;
        }
        ArrayList<AState> neighbors ;
        Stack<AState> s = new Stack<>();
        s.push(currState);
        while (!s.empty()){
            currState = s.pop();
            if (!statesVisited.containsKey(currState.toString())) {
                statesVisited.put(currState.toString(), currState);
                neighbors = search.getAllPossibleStates(currState);
                for(int i=0; i<neighbors.size(); i++){
                    if (!statesVisited.containsKey(neighbors.get(i).toString())){
                        neighbors.get(i).prev = currState;
                        s.push(neighbors.get(i));
                    }

                }
            }
        }

    }
}
