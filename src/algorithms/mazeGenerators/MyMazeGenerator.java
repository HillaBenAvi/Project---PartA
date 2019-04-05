package algorithms.mazeGenerators;

import java.util.ArrayList;


public class MyMazeGenerator extends AMazeGenerator {

    @Override
    public Maze generate(int rows, int columns) {
        Maze myMaze;
        if(rows<=0 || columns<=0){
            myMaze= new Maze (1,1);
        }
        else{
            myMaze = new Maze(rows, columns);
        }
        ArrayList<Position> neighbors = new ArrayList<>();
        Position p = myMaze.getGoalPosition();
        myMaze.setCell(p.getRowIndex(),p.getColumnIndex(),0);
        addNeighbors(myMaze, neighbors, p);

        while (neighbors.size() > 0){
            int index = (int) (Math.random () * neighbors.size());
            p = neighbors.get(index);
            if (numOfZeroNeighbors(myMaze, p)==1){
                myMaze.setCell(p.getRowIndex(), p.getColumnIndex(), 0);
                addNeighbors(myMaze, neighbors, p);
            }
            neighbors.remove(index);
        }

        for(int i=0; i<myMaze.getColumns(); i++){
            if(myMaze.getCellValue(0,i)==0){
                myMaze.setStartPosition(0,i);
                break;
            }
        }

        return myMaze;
    }

    private void addNeighbors (Maze maze, ArrayList<Position> list, Position p){
        if (p.getRowIndex()+1<maze.getRows() && maze.getCellValue(p.getRowIndex()+1, p.getColumnIndex())==1){
            Position n  = new Position (p.getRowIndex()+1, p.getColumnIndex());
            if(numOfZeroNeighbors(maze, n)==1)
                list.add(n);
        }
        if (p.getRowIndex()-1>=0 && maze.getCellValue(p.getRowIndex()-1, p.getColumnIndex())==1){
            Position n  = new Position (p.getRowIndex()-1, p.getColumnIndex());
            if(numOfZeroNeighbors(maze, n)==1)
                list.add(n);
        }
        if (p.getColumnIndex()+1<maze.getColumns() && maze.getCellValue(p.getRowIndex(), p.getColumnIndex()+1)==1){
            Position n  = new Position (p.getRowIndex(), p.getColumnIndex()+1);
            if(numOfZeroNeighbors(maze, n)==1)
                list.add(n);
        }
        if (p.getColumnIndex()-1>=0 && maze.getCellValue(p.getRowIndex(), p.getColumnIndex()-1)==1){
            Position n  = new Position (p.getRowIndex(), p.getColumnIndex()-1);
            if(numOfZeroNeighbors(maze, n)==1)
                list.add(n);
        }
    }

    private int numOfZeroNeighbors (Maze maze, Position p){
        int counter =0;
        if (p.getRowIndex()+1<maze.getRows() && maze.getCellValue(p.getRowIndex()+1, p.getColumnIndex())==0){
            counter++;
        }
        if (p.getRowIndex()-1>=0 && maze.getCellValue(p.getRowIndex()-1, p.getColumnIndex())==0){
            counter++;
        }
        if (p.getColumnIndex()+1<maze.getColumns() && maze.getCellValue(p.getRowIndex(), p.getColumnIndex()+1)==0){
            counter++;
        }
        if (p.getColumnIndex()-1>=0 && maze.getCellValue(p.getRowIndex(), p.getColumnIndex()-1)==0){
            counter++;
        }
        return counter;
    }

}
