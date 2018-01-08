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
    public Game getGame(){
        Game game = null;
        String message = client.receiveMessage();
        if(message.equals("YOUR_GAME")){
            String json = client.receiveMessage();
            game = gson.fromJson(json, Game.class);
        }

        return game;
    }


    @Override
    public Game[] getGames() {
        client.sendMessage("GETGAMES");
        Game[] games = null;

        String message = client.receiveMessage();
        System.out.println(message);
        if(message.equals("GAMES")) {
            String json = client.receiveMessage();
            System.out.println(json);
            games = gson.fromJson(json, Game[].class);
            System.out.println(games.length);
        }

        return  games;
    }

    void closeConnection(){
        client.sendMessage("QUIT");
        if(!client.closeConnection())
            System.out.println("Something went wrong");

    }


    @Override
    public void chooseGame(String gameName) {
        client.sendMessage("JOIN");
        System.out.println("wybieram");
        client.sendMessage(gameName);

    }

    public ConnectionManager() throws Exception{
        gson = new Gson();
        client = new GameClient("localhost");

    }
}
