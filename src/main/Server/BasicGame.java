package Server;
/**
 * Basic implementation of Chinese Checkers game
 * */
public class BasicGame extends Game {

    /**
     * Creates the game with specified name and sets maxPlayers as 6
     * @param name it is requested name*/
    public BasicGame(String name) {
        super(name);
        maxPlayers = 6;
    }

    @Override
    public void startGame() {
        System.out.println("Game started");
    }
}
