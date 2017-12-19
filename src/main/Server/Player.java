package Server;


import java.net.Socket;

public interface Player extends Runnable
{
    void init(Socket socket, int number, Game game);
}
