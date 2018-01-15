package Server;

/**
 * Interface for multiplayer game
 * */
public interface Game
{
    /**
     * @return name of the game
     * */
    public String getName();

    /**
     * adds player to the list of players if there is a place for him
     * else throws Exception
     * @param p player that wants to join
     *
     * */
    void addPlayer(Player p) throws Exception;

    /**
     * @return number of actual players connected to the game
     * */
    int getActualPlayers();

    /**
     * Removes player form Game and then checks if all players are ready
     * @param p player that wants to quit
    */
    void removePlayer(Player p);

    /**
     * increments readyPlayers number and checks if all players are ready
     * */
    void playerReady(Player p);

    /**
     * decrements readyPlayers number
     * */
    void playerNotReady(Player p);


}
