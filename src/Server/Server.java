package Server;

import algorithms.mazeGenerators.*;
import algorithms.search.BestFirstSearch;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.DepthFirstSearch;
import algorithms.search.ISearchingAlgorithm;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static Server.Server.Configuration.getSizeOfThreadPoolProp;

public class Server {
    private int port;
    private int listeningInterval;
    private ISreverStrategy serverStrategy;
    private volatile boolean stop;
    private ExecutorService pool;

    public Server(int port, int listeningInterval, ISreverStrategy serverStrategy) {
        this.port = port;
        this.listeningInterval = listeningInterval;
        this.serverStrategy = serverStrategy;
    }

    public void start(){

        new Thread(()-> runServer()).start();
    }

    private void runServer() {
        try{
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningInterval);
            this.pool = Executors.newFixedThreadPool(getSizeOfThreadPoolProp());
            while (!stop){
                try{
                    Socket clientSocket = serverSocket.accept();
                    pool.execute( new Thread (()-> {handleClient(clientSocket);}));
                }catch(SocketTimeoutException e){
                    System.out.println("Socket TimeOut- no clients pending ");
                }
            }
            pool.shutdown();
            serverSocket.close();
        }catch(IOException e){
            System.out.println("IOException");
        }
    }

    private void handleClient(Socket clientSocket) {
        try{
            serverStrategy.serverStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            clientSocket.close();
        }catch (IOException e){
            System.out.println("IOException");
        }
    }

    public void stop(){
        stop = true;
    }


    public static class Configuration {

        public static int getSizeOfThreadPoolProp() {
            int res = 0;
            try (InputStream input = Server.class.getClassLoader().getResourceAsStream("config.properties")) {
                Properties prop = new Properties();
                prop.load(input);
                res = Integer.parseInt(prop.getProperty("ThreadPoolSize"));

            } catch (Exception e) {
                e.printStackTrace();
            }

            return res;
        }

        public static AMazeGenerator getMazeGeneratorProp() {
            AMazeGenerator mazeGenerator = null;
            try (InputStream input = Server.class.getClassLoader().getResourceAsStream("config.properties")) {
                Properties prop = new Properties();
                prop.load(input);
                String mazeG = prop.getProperty("MazeGenerator");
                if (mazeG.equals("MyMazeGenerator")) {
                    mazeGenerator = new MyMazeGenerator();
                } else if (mazeG.equals("SimpleMazeGenerator")) {
                    mazeGenerator = new SimpleMazeGenerator();
                } else if (mazeG.equals("EmptyMazeGenerator")) {
                    mazeGenerator = new EmptyMazeGenerator();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return mazeGenerator;
        }

        public static ISearchingAlgorithm getSearchingAlgorithmProp() {
            ISearchingAlgorithm searchA = null;
            try (InputStream input = Server.class.getClassLoader().getResourceAsStream("config.properties")) {
                Properties prop = new Properties();
                prop.load(input);

                String search = prop.getProperty("SearchingAlgorithm");
                if (search.equals("DepthFirstSearch")) {
                    searchA = new DepthFirstSearch();
                } else if (search.equals("BreadthFirstSearch")) {
                    searchA = new BreadthFirstSearch();
                } else if (search.equals("BestFirstSearch")) {
                    searchA = new BestFirstSearch();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return searchA;
        }
    }
}

