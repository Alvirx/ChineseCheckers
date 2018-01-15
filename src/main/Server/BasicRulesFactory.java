package Server;

public class BasicRulesFactory implements RulesFactory {

    private final int maxPlayers=6;
    private int size;
    private int numberOfPlayers;
    private Server.Board board;
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
        //board = new Server.BasicBoard();
        //TODO tworzenie Hashsetu i przypisywanie go do stworzonej instancji BasicBoard






    }

    @Override
    public void createJudge()
    {
        judge = new BasicJudge();
    }

    @Override
    public Server.Board getBoard() {
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
