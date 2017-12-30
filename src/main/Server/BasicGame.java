package Server;

public class BasicGame extends Game {

    public BasicGame(String name) {
        super(name);
        maxPlayers = 6;
    }

    @Override
    public void startGame() {
        System.out.println("Game started");
    }
}
