package Client;
// raczej zrobilem ten interface niepotrzebnie
public interface ConnectionManagerInterface {

    Game getGame();
    Game[] getGames();
    void chooseGame(String gameName);
}
