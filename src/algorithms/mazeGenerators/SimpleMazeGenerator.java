package algorithms.mazeGenerators;

public class SimpleMazeGenerator extends AMazeGenerator {

    @Override
    public Maze generate(int rows, int columns) {
        Maze sMaze ;
        if(rows<=0 || columns<=0){
            sMaze= new Maze (1,1);
        }
        else{
            sMaze = new Maze(rows, columns);
        }
        double rand = Math.random();
        int currRow = sMaze.getStartPosition().getRowIndex();
        int currCol = sMaze.getStartPosition().getColumnIndex();
        sMaze.setCell(currRow,currCol, 0);

        while(currRow < sMaze.getGoalPosition().getRowIndex()|| currCol < sMaze.getGoalPosition().getColumnIndex())
        {
            rand = Math.random();
            if (rand < 0.5 && currRow < sMaze.getGoalPosition().getRowIndex()){
                currRow++;
                sMaze.setCell(currRow,currCol, 0);
            }
            else if(currCol < sMaze.getGoalPosition().getColumnIndex()) {
                currCol++;
                sMaze.setCell(currRow,currCol, 0);
            }
        }
        randomWalls(sMaze);
        return sMaze;
    }


    private void randomWalls (Maze m){
        for (int i=0; i<m.getRows(); i++){
            for (int j=0; j<m.getColumns(); j++){
                if (m.getCellValue(i,j) == 1){
                    double rand = Math.random();
                    if (rand <0.5){
                        m.setCell(i, j, 0);
                    }
                    else {
                        m.setCell(i, j, 1);
                    }
                }
            }
        }
    }
}

