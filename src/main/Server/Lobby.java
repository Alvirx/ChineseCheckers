package Server;

import java.util.LinkedList;

/**
 * Stores list of games on the server and
 * manages them
 * */
public class Lobby
{
    private static Lobby instance;
    private LinkedList<Game> listOfGames;

    /**private constructor since this is singleton pattern*/
    private Lobby()
    {
        listOfGames = new LinkedList<>();
    }

    /**@return instance of Lobby class*/
    static Lobby getInstance()
    {
         if(instance==null)
         {
            instance = new Lobby();
         }
         return instance;
    }

    /**
     * Looks for a game in the list of games
     * and if specified Game doesn`t exist - creates it
     * @param name name of requested game
     * @eturn Game with specified name
     * */
    public Game getGame(String name)
    {
        for (Game game : listOfGames)
        {
            if(game.getName().equals(name)) return game;
        }
        Game game = new BasicGame(name);
        listOfGames.add(game);
        return game;
    }

    /**
     * @return list of games
     * */
    public LinkedList<Game> getListOfGames()
    {
        return listOfGames;
    }



}
