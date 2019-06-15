package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;


import java.io.*;

import static Server.Server.Configuration.getMazeGeneratorProp;

public class ServerStrategyGenerateMaze implements ISreverStrategy {
    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream objectInput = new ObjectInputStream(inFromClient);
            ObjectOutputStream objectOutput = new ObjectOutputStream(outToClient);
            ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
            MyCompressorOutputStream compressor = new MyCompressorOutputStream(byteOutput);
            objectOutput.flush();

            AMazeGenerator mazeGenerator = getMazeGeneratorProp();
            int [] mazeSize = (int[]) objectInput.readObject();
            Maze maze = mazeGenerator.generate(mazeSize[0], mazeSize[1]); //creates the maze
            byte[] mazeByteArray = maze.toByteArray();

            compressor.write(mazeByteArray);
            objectOutput.writeObject(byteOutput.toByteArray());
            objectOutput.flush();
            compressor.flush();
            compressor.close();
            byteOutput.flush();
            byteOutput.close();
            //objectOutput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
