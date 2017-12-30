package Client;

import com.google.gson.Gson;

import java.util.LinkedList;

/*
    Klasa pomiedzy serwerem, a gui, zamienia mi ona jsony na obiekty i odwrotnie
 */
public class ConnectionManager implements ConnectionManagerInterface{

    Gson gson;
    GameClient client;
    LinkedList<Game> games;


    @Override
    public Game[] getGames() {
//        client.sendMessage("GiveMeGames");
//        String json = client.receiveMessage();
//        Game games[] = gson.fromJson(json, Game[].class);


        return  games.toArray(new Game[games.size()] );
    }


    @Override
    public void chooseGame(Game game) {
//        games.add(game);
        String json = gson.toJson(game);
//        client.sendMessage(json);
        System.out.println(json);

    }

    public ConnectionManager() throws Exception{
        gson = new Gson();

        games = new LinkedList<Game>();
        for(int i = 0; i < 5; i++){
            games.add(new Game("game: " + i, i, i * 2));
        }
//        client = new GameClient("localhost");

    }
}
