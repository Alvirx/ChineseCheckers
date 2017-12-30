package Server;

/*abstract class for human and computer players*/
public abstract class Player implements Runnable
{
    protected Game game;
    protected boolean isReady;

    /*
    * returns true if player is ready to game
    * else returns false
    */
    public boolean isReady()
    {
        return isReady;
    }

    /*Clears the information about game*/
   public void clear()
    {
        if(game!=null)
        {
            game.removePlayer(this);
            game = null;
        }
        isReady = false;
    }

}
