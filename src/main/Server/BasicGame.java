package Server;

import Shared.Board;

import java.util.ArrayList;

/**
 * Basic implementation of Chinese Checkers game
 * */
public class BasicGame implements Game {


    private String name;
    private int actualPlayers;
    private int maxPlayers;
    private ArrayList<Boolean> players;

    private RulesFactory rulesFactory;



    Judge judge;
    Board board;

    /**
     * Creates instance of the game and sets actualPlayers and readyPlayers to 0
     * */
    public BasicGame(String name)
    {
        this.name = name;
        actualPlayers = 0;
        rulesFactory = new BasicRulesFactory();
        maxPlayers = rulesFactory.getMaxPlayers();
        players = new ArrayList<>();
        for (int i=0;i<maxPlayers;i++) players.add(null);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public synchronized void addPlayer(Player p) throws Exception
    {
        if(actualPlayers<maxPlayers)
        {
            actualPlayers++;
            for (int i=0;i<maxPlayers;i++)
            {
                if(players.get(i)==null)
                {
                    players.set(i, false);
                    break;
                }
            }
        }
        else throw new Exception();
    }

    @Override
    public void removePlayer(Player p)
    {
        if(players.get(p.getInGameNumber())!=null)
        {
            players.set(p.getInGameNumber(), null);
            actualPlayers--;
            if(areAllPlayersReady()) startGame();
        }
    }

    @Override
    public int getActualPlayers()
    {
        return actualPlayers;
    }

    @Override
    public void playerReady(Player p)
    {
        if(players.get(p.getInGameNumber())!=null)
        {
            players.set(p.getInGameNumber(), true);
            if(areAllPlayersReady()) startGame();
        }
    }

    @Override
    public void playerNotReady(Player p)
    {
        if(players.get(p.getInGameNumber())!=null)
        {
            players.set(p.getInGameNumber(), false);
        }
    }

    @Override
    public void setRulesFactory(RulesFactory rulesFactory) {
        this.rulesFactory = rulesFactory;
        maxPlayers = rulesFactory.getMaxPlayers();
    }

    /**
     * Checks if all players are ready
     * */
    private boolean areAllPlayersReady()
    {
        boolean flag = true;
        for (int i=0;i<maxPlayers;i++)
        {
            if(players.get(i)!=null && !players.get(i))
            {
                flag = false;
                break;
            }
        }
        return flag;
    }

    //TODO everything
    private void startGame() {
        rulesFactory.setNumberOfPlayers(actualPlayers);
        rulesFactory.createBoard();
        rulesFactory.createJudge();
        board = rulesFactory.getBoard();
        judge = rulesFactory.getJudge();
    }
}
