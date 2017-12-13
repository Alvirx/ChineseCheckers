package Server;

import java.util.LinkedList;

public class Game
{
    LinkedList<Thread> players = new LinkedList<>();

    //adds player to list of players
    void addPlayer(Player p)
    {
        players.add(new Thread(p));
    }

    //starts the game
    public void start()
    {
        for(Thread player : players) player.start();
    }

    /*
    *For now this method only prints message at the stdOut
    * */
    public void log(String message)
    {
        System.out.println(message);
    }
}
