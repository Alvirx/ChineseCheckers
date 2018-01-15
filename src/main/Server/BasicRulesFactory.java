package Server;

public class BasicRulesFactory implements RulesFactory {

    private int size;
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
}
