package Server;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;

/*
*
*
*
*
*
*
* */
public class BasicPlayer extends Player {

    Gson gson;
    Socket socket;
    BufferedReader input;
    PrintWriter output;

    /*creates thread for communication with client application*/
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


    /*communicates with application while game is on*/
    @Override
    public void run() {
        try {
            LinkedList<Game> list = Lobby.getInstance().getListOfGames();
            //TODO sent list of games to client
            //output.println(gson.toJson(list));
            output.println("Tutaj drukowana lista gier");


            String massage = input.readLine();
            while (!massage.startsWith("QUIT"))
            {
                if(massage.startsWith("JOIN") && game==null)
                {
                    String gameName = input.readLine();

                    game = Lobby.getInstance().getGame(gameName);
                    System.out.println(game.getName());
                    try
                    {
                        game.addPlayer(this);
                        //TODO sent game instance to the client via Json
                        //output.println(gson.toJson(game));
                        output.println("Nazwa gry:"+game.getName());

                    }
                    catch (Exception e)
                    {
                        output.println("TO_MUCH_PLAYERS");
                        game = null;
                    }
                }
                else if(massage.startsWith("READY") && game!=null)
                {
                    String ready = input.readLine();
                    isReady = gson.fromJson(ready, Boolean.class);
                    game.playersReady();
                }

                massage = input.readLine();
            }
            clear();
        } catch (Exception e) {
            //myGame.log("Player disconnected");
        }
    }
}
