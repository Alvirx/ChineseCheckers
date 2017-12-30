package Client;
// raczej zrobilem ten interface niepotrzebnie
public interface ConnectionManagerInterface {
    Game[] getGames();
    void chooseGame(String gameName);
}
