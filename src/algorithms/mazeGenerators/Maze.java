package algorithms.mazeGenerators;

import java.util.ArrayList;

public class Maze {

    private int [][] maze;
    private int rows, columns;
    private Position startPosition, goalPosition;

    public Maze(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        maze = new int [rows][columns];
        startPosition = new Position (0,0);
        goalPosition = new Position(rows-1, columns -1);
        for (int i=0; i<rows; i++){
            for (int j=0; j<columns; j++){
                maze[i][j]= 1;
            }
        }
    }

    public void setCell(int row, int column, int value){
        if ( row<0 || row>this.rows || column<0 || column>this.columns){
            return;
        }
        maze[row][column] = value;
    }

    public int getCellValue (int row, int column){
        return maze[row][column];
    }

    public int getCellValue(Position p){
        return maze[p.getRowIndex()][p.getColumnIndex()];
    }

    public Position getStartPosition(){
        return startPosition;
    }

    public Position getGoalPosition(){
        return goalPosition;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setStartPosition(int row, int col){
        startPosition = new Position(row, col);
    }

    public void print (){
        for (int i=0; i<rows; i++){
            for(int j=0; j<columns; j++){
                if (i == startPosition.getRowIndex() && j == startPosition.getColumnIndex()){
                    System.out.print("S");
                }
                else if (i == goalPosition.getRowIndex() && j == goalPosition.getColumnIndex()){
                    System.out.print("E");
                }
                else{
                    System.out.print(maze[i][j]);
                }
            }
            System.out.println();
        }
    }

    public boolean isInTheRange (Position p){
        if(p.getRowIndex()<0 || p.getRowIndex()>rows-1 || p.getColumnIndex()<0 || p.getColumnIndex()>columns-1){
            return false;
        }
        return true;
    }
}
