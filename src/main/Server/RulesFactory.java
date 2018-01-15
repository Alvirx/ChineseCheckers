package Server;

public interface RulesFactory
{
    void setNumberOfPlayers(int numberOfPlayers);
    void createBoard();
    void createJudge();
    Server.Board getBoard();
    Judge getJudge();
    int getMaxPlayers();
}
