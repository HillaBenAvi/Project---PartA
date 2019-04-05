package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import java.util.ArrayList;

public class SearchableMaze implements ISearchable{

    private Maze maze;

    public SearchableMaze(Maze m) {
        maze = m;
    }

    @Override
    public AState getStartState() {
        return new MazeState(maze.getStartPosition(),2);
    }

    @Override
    public AState getGoalState() {
        return new MazeState(maze.getGoalPosition(),2);
    }

    @Override
    public ArrayList<AState> getAllPossibleStates(AState s) {
        ArrayList <AState> possibleStates = new ArrayList<>();
        if(s == null){
            return possibleStates;
        }
        boolean right=false;
        boolean left=false;
        boolean up=false;
        boolean down=false;
        Position p = (Position)s.getData();
        Position pTemp = new Position(p.getRowIndex() + 1, p.getColumnIndex());


        if(maze.isInTheRange(pTemp)&& maze.getCellValue(pTemp)==0){
            possibleStates.add(new MazeState(pTemp, 3));
            down =true;
        }
        pTemp = new Position(p.getRowIndex() - 1, p.getColumnIndex());
        if(maze.isInTheRange(pTemp) && maze.getCellValue(pTemp)==0 ) {
            possibleStates.add(new MazeState(pTemp, 3));
            up = true;
        }
        pTemp = new Position(p.getRowIndex() , p.getColumnIndex()+1);
        if(maze.isInTheRange(pTemp) && maze.getCellValue(pTemp)==0) {
            possibleStates.add(new MazeState(pTemp, 3));
            right = true;
        }
        pTemp = new Position(p.getRowIndex(), p.getColumnIndex()-1);
        if(maze.isInTheRange(pTemp) && maze.getCellValue(pTemp)==0) {
            possibleStates.add(new MazeState(pTemp, 3));
            left = true;
        }
        pTemp = new Position(p.getRowIndex()-1,p.getColumnIndex()+1);
        if((up || right)&& maze.isInTheRange(pTemp) && maze.getCellValue(pTemp)==0){
            possibleStates.add(new MazeState(pTemp, 2));
        }
        pTemp =new Position (p.getRowIndex()+1, p.getColumnIndex()+1);
        if( (down|| right) &&  maze.isInTheRange(pTemp) && maze.getCellValue(pTemp)==0){
            possibleStates.add(new MazeState(pTemp,2));
        }
        pTemp =new Position (p.getRowIndex()+1, p.getColumnIndex()-1);
        if( (down|| left) &&  maze.isInTheRange(pTemp) && maze.getCellValue(pTemp)==0){
            possibleStates.add(new MazeState(pTemp, 2));
        }
        pTemp =new Position (p.getRowIndex()-1, p.getColumnIndex()-1);
        if( (up|| left) &&  maze.isInTheRange(pTemp) && maze.getCellValue(pTemp)==0){
            possibleStates.add(new MazeState(pTemp, 2));
        }

        return possibleStates;
    }
}
