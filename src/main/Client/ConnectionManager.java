package Client;

import com.google.gson.Gson;
/*
    Klasa pomiedzy serwerem, a gui, zamienia mi ona jsony na obiekty i odwrotnie
 */
public class ConnectionManager implements ConnectionManagerInterface{

    Gson gson;
    GameClient client;


    @Override
    public Game[] getGames() {
        client.sendMessage("GiveMeGames");
        String json = client.receiveMessage();
        Game games[] = gson.fromJson(json, Game[].class);

        return games;
    }


    @Override
    public void chooseGame(Game game) {
        String json = gson.toJson(game);
        client.sendMessage(json);

    }

    public ConnectionManager() throws Exception{
        gson = new Gson();
        client = new GameClient("localhost");

    }
}
