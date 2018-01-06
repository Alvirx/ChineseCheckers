package Server;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;

/**
 *  A Thread for a network multi-player Chinese Checkers game. It have our CCP (Chinese Checkers
 *  protocol) which is mainly plain text with JSON addons for senting things like list of
 *  possible moves
 *  The strings that are sent in CCP are:
 *
 *  Client -> Server                                Server -> Client
 *  ----------------                                ----------------
 *  GET_GAMES                                       GAMES
 *  JOIN                                                List of games on the server converted to JSON
 *      "Name_Of_Game"                              YOUR_GAME
 *  READY                                               Requested game converted to JSON
 *      true or false converted to JSON             GAME_STARTED
 *  QUIT                                            TO_MUCH_PLAYERS
 *
 *
 *
 */
public class BasicPlayer extends Player {

    Gson gson;
    Socket socket;
    BufferedReader input;
    PrintWriter output;

    /**
     * creates Thread for communication with client application
     * which represents player in the game
     * @param socket socket to client application
     * */
    public BasicPlayer(Socket socket)
    {
        gson = new Gson();
        isReady = false;
        this.socket=socket;
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
        } catch (Exception e) {
            //myGame.log("Player can`t connect at the moment: " + e);
        }
    }

    /**
     * communicates with client application, sent information, recive it and validate it
     * */
    @Override
    public void run() {
        try {
            String massage = input.readLine();
            //While player does not close application
            while (!massage.startsWith("QUIT"))
            {

                if(massage.startsWith("GET_GAMES"))
                {
                    sentGames();
                }
                else if(massage.startsWith("JOIN") && game==null)
                {
                    joinGame(input.readLine());
                }
                else if(massage.startsWith("READY") && game!=null)
                {
                    validateReady(input.readLine());
                }
                //TODO other stuff
                massage = input.readLine();
            }
            clear();
        } catch (Exception e) {
            System.out.println("Error with communication with client");
        }
    }

    private void sentGames()
    {
        LinkedList<Game> list = Lobby.getInstance().getListOfGames();
        output.println("GAMES");
        output.println(gson.toJson(list.toArray(new Game[list.size()])));
    }

    private void joinGame(String gameName)
    {
        game = Lobby.getInstance().getGame(gameName);
        try
        {
            game.addPlayer(this);
            output.println("YOUR_GAME");
            output.println(gson.toJson(game));
        }
        catch (Exception e)
        {
            output.println("TO_MUCH_PLAYERS");
            game = null;
        }
    }

    private void validateReady(String ready)
    {
        boolean newVal = gson.fromJson(ready, Boolean.class);
        if(newVal)
        {
            game.playerReady(this);
        }
        else
        {
            game.playerNotReady(this);
        }
        isReady = newVal;
    }

}
