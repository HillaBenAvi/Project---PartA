package algorithms.search;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    protected Solution sol;

    @Override
    public abstract Solution solve(ISearchable iSearch);

    @Override
    public abstract String getName();

    @Override
    public int getNumberOfNodesEvaluated() {
        return sol.getSolutionPath().size();
    }
}
