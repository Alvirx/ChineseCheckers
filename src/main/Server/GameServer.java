package Server;

import java.net.ServerSocket;

public class GameServer
{
    public static void main(String[] args) throws Exception {
        ServerSocket listener = new ServerSocket(8901);

        try {
            System.out.println("Server is running");
            while (true) {
                Game game = new Game();
                //TODO should take at least two players and then if they want to start the game or let them wait for others
                //for now only takes one player

                Player newPlayer = new BasicPlayer();
                newPlayer.init(listener.accept(), 1, game);
                game.addPlayer(newPlayer);

                game.start();
            }
        } finally {
            listener.close();
        }
    }
}
