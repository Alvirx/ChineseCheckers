package Server;

import java.util.LinkedList;

public abstract class Game
{
    protected String name;
    protected LinkedList<Player> players;
    protected int maxPlayers;

    public Game(String name)
    {
        this.name = name;
        players = new LinkedList<>();
    }

    /*returns name*/
    public String getName()
    {
        return name;
    }

    /*
    * adds player to the list of players if there is a place for him
    * else throws Exception
    * TODO make new Exception
    * */
    public synchronized void addPlayer(Player p) throws Exception
    {
        if(players.size()<maxPlayers) players.add(p);
        else throw new Exception();
    }

    /*Removes player form Game*/
    public void removePlayer(Player p)
    {
        players.remove(p);
        playersReady();
    }

    /*Checks if all players are ready, if they are starts the game*/
    public void playersReady()
    {
        boolean gameReady = true;
        for (Player player : players)
        {
            if(!player.isReady())
            {
                gameReady=false;
                break;
            }
        }
        if(gameReady) startGame();
    }

    /*Starts the Game*/
    public abstract void startGame();




}
