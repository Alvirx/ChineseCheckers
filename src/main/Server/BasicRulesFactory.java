package Server;

import Shared.Board;

public class BasicRulesFactory implements RulesFactory {

    private final int maxPlayers=6;
    private int size;
    private int numberOfPlayers;
    private Board board;
    private Judge judge;

    public BasicRulesFactory()
    {
        size=6;
    }

    public void setSize(int size)
    {
        this.size=size;
    }

    @Override
    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    @Override
    public void createBoard()
    {
        //board = new Shared.BasicBoard();
        //TODO tworzenie Hashsetu i przypisywanie go do stworzonej instancji BasicBoard






    }

    @Override
    public void createJudge()
    {
        judge = new BasicJudge();
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public Judge getJudge() {
        return judge;
    }

    @Override
    public int getMaxPlayers() {
        return maxPlayers;
    }
}
