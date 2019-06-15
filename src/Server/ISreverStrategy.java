package Server;

import java.io.InputStream;
import java.io.OutputStream;

public interface ISreverStrategy {
    void serverStrategy(InputStream inFromClient, OutputStream outToClient);

}
