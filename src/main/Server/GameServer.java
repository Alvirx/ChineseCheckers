package Server;

import com.google.gson.Gson;

import java.net.ServerSocket;
import java.net.Socket;

public class GameServer
{
    /*
    * Catch players that wants to connect and starts its Thread
    * */
    public static void main(String[] args) throws Exception {

        Gson g = new Gson();
        Game game = Lobby.getInstance().getGame("gra");
        game.addPlayer(new BasicPlayer(new Socket()));
        System.out.println(g.toJson(game));

        /*ServerSocket listener = new ServerSocket(8901);
        try {
            System.out.println("Server is running");
            while (true) {

                Player newPlayer = new BasicPlayer(listener.accept());
                new Thread(newPlayer).start();
            }
        } finally {
            listener.close();
        }*/
    }
}
