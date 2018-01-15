package Server;

import Shared.Board;

public interface RulesFactory
{
    void setNumberOfPlayers(int numberOfPlayers);
    void createBoard();
    void createJudge();
    Board getBoard();
    Judge getJudge();
    int getMaxPlayers();
}
