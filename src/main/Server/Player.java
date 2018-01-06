package Server;

/**
 * abstract class for human and computer players
 * */
abstract class Player implements Runnable
{
    Game game;
    boolean isReady;
    int inGameNumber;

    /**
     * @return true if player is ready or false instead
     * */
    boolean isReady() {
        return isReady;
    }

    /**
     * Clears the information about player`s game
     * */
    void clear()
    {
        if(game!=null)
        {
            game.removePlayer(this);
            game = null;
        }
        isReady=false;
    }

    int getInGameNumber()
    {
        return inGameNumber;
    }

}
