package algorithms.search;

public interface ISearchingAlgorithm {

    public Solution solve (ISearchable iSearch);
    public String getName();
    public int getNumberOfNodesEvaluated();
}
