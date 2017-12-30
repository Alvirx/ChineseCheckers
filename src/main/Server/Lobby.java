package Server;

import java.util.LinkedList;

public class Lobby
{
    private static Lobby instance;
    private LinkedList<Game> listOfGames;

    /*private constructor since this is singleton pattern*/
    private Lobby()
    {
        listOfGames = new LinkedList<>();
    }

    /*returns instance of lobby*/
    static Lobby getInstance()
    {
         if(instance==null)
         {
            instance = new Lobby();
         }
         return instance;
    }

    /*Returns Game with specified name and if specified Game doesn`t exist - creates it*/
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

    /*returns list of games*/
    public LinkedList<Game> getListOfGames()
    {
        return listOfGames;
    }



}
