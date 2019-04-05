package algorithms.mazeGenerators;

public abstract class AMazeGenerator implements IMazeGenerator {

    @Override
    public abstract Maze generate(int rows, int columns);

    @Override
    public long measureAlgorithmTimeMillis(int rows, int columns) {
        long startTime, endTime;
        startTime = System.currentTimeMillis();
        generate(rows, columns);
        endTime = System.currentTimeMillis();
        return endTime-startTime;
    }


}
