package Server;

import java.net.ServerSocket;

/**
 * A server for ChineseCheckers Game
 * It catch players that wants to connect and starts their Threads for communication
 * */
public class GameServer
{
    public static void main(String[] args) throws Exception {

        ServerSocket listener = new ServerSocket(8901);
        try {
            //rapidly try to catch players
            while (true) {
                //catch player
                Player newPlayer = new BasicPlayer(listener.accept());
                //start its Thread
                new Thread(newPlayer).start();
            }
        } finally {
            listener.close();
        }
    }
}
