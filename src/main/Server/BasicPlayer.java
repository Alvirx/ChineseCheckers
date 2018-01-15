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
 *                                                      {List of games on the server converted to JSON}
 *
 *
 *  JOIN                                            YOUR_GAME (if player was successfully joined)
 *      {Name Of Game}                                  {Requested game converted to JSON}
 *                                                  TO_MUCH_PLAYERS (if there is to much players)
 *
 *  READY                                           GAME_STARTED(if all players are now ready)
 *      {true or false converted to JSON}
 *
 *
 *  UPDATE_GAME                                     UP_TO_DATE (if hashes are the same)
 *      hash of game object                         NOT_UP_TO_DATE (if hashes are different)
 *                                                          Requested game converted to JSON (without board)
 *
 *
 *
 *  UPDATE_BOARD                                    BOARD_UP_TO_DATE (if number is same as number on server)
 *      number of moves that player                 BOARD_NOT_UP_TO_DATE (if those numbers are different)
 *      already have been noticed                       list of field pairs that ware moves made From one
 *      and have them                                   to another, chronologically ordered. This list is
 *                                                      started from first move that player haven`t been
 *                                                      noticed yet.
 *                                                  GAME_FINISHED (if those numbers are different
 *                                                                  and game was finished)
 *                                                      list of field pairs that ware moves made From one
 *                                                      to another, chronologically ordered. This list is
 *                                                      started from first move that player haven`t been
 *                                                      noticed yet.
 *                                                      Number of player that won the game
 *
 *
 *  GET_POSSIBLE_MOVES                              POSSIBLE_MOVES
 *      id of field that should be checked              table of integers filled with id`s of fields
 *                                                      where move is possible converted to json
 *
 *  MOVE                                            MOVE_SUCCEEDED(if move was done)
 *      From-field id ; Targed-field id             MOVE_FAIL (if move was not correct)
 *
 *
 *
 *  QUIT
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
