package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator{

    @Override
    public Maze generate(int rows, int columns) {
        Maze eMaze;
        if(rows<=0 || columns<=0){
             eMaze= new Maze (1,1);
        }
        else{
             eMaze = new Maze(rows, columns);
        }
        for (int i=0; i<rows; i++){
            for (int j=0; j<columns; j++){
                eMaze.setCell(i, j, 0);
            }
        }
        return eMaze;
    }


}
