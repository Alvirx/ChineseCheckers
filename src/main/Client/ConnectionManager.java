package Client;

import com.google.gson.Gson;

import java.util.LinkedList;

/**
    Klasa pomiedzy serwerem, a gui, zamienia mi ona jsony na obiekty i odwrotnie
 */
public class ConnectionManager implements ConnectionManagerInterface{

    Gson gson;
    GameClient client;


    @Override
    public Game[] getGames() {
//        client.sendMessage("");
        Game[] games = null;

        String message = client.receiveMessage();
        if(message == "GAMES") {
            String json = client.receiveMessage();
            System.out.println(json);
            games = gson.fromJson(json, Game[].class);
        }

        return  games;
    }


    @Override
    public void chooseGame(String gameName) {
        client.sendMessage("JOIN");
        client.sendMessage(gameName);

    }

    public ConnectionManager() throws Exception{
        gson = new Gson();
        client = new GameClient("localhost");

    }
}
