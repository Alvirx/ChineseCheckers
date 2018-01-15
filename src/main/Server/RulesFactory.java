package Server;

public interface RulesFactory
{
    void createBoard();
    void createJudge();
    Server.Board getBoard();
    Judge getJudge();
}
