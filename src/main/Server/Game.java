package Server;

/**
 * Abstract class for multiplayer game
 * */
public abstract class Game
{
    protected String name;
    private int actualPlayers;
    private int readyPlayers;
    int maxPlayers;


    /**
     * Creates instance of the game and sets actualPlayers and readyPlayers to 0
     * */
    public Game(String name)
    {
        this.name = name;
        actualPlayers = 0;
        readyPlayers = 0;
    }

    /**
     * @return name of the game
     * */
    public String getName()
    {
        return name;
    }

    /**
     * adds player to the list of players if there is a place for him
     * else throws Exception  TODO make new Exception
     * @param p player that wants to join
     *
     * */
    synchronized void addPlayer(Player p) throws Exception
    {
        if(actualPlayers<maxPlayers) actualPlayers++;
        else throw new Exception();
    }

    /**
     * @return number of actual players connected to the game
     * */
    int getActualPlayers()
    {
        return actualPlayers;
    }

    /**
     * Removes player form Game and then checks if all players are ready
     * @param p player that wants to quit
    */
    void removePlayer(Player p)
    {
        actualPlayers--;
        if(p.isReady()) readyPlayers--;
        areAllPlayersReady();
    }

    /**
     * increments readyPlayers number and checks if all players are ready
     * */
    void playerReady()
    {
        readyPlayers++;
        areAllPlayersReady();
    }

    /**
     * decrements readyPlayers number
     * */
    void playerNotReady()
    {
        readyPlayers--;
    }

    /**
     * Checks if all players are ready, if they are starts the game
     * */
    public void areAllPlayersReady()
    {
        if(actualPlayers==readyPlayers) startGame();
    }

    /**
     * Starts the Game
     * */
    public abstract void startGame();

}
