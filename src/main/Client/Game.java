package Client;

public class Game {

    private String name;
    private int actualPlayers;
    private int readyPlayers;
    private int maxPlayers;

    public Game(String name, int maxPlayers) {
        this.name = name;
        this.maxPlayers = maxPlayers;
        this.actualPlayers = 0;
        this.readyPlayers = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getActualPlayers() {
        return actualPlayers;
    }

    public void setActualPlayers(int actualPlayers) {
        this.actualPlayers = actualPlayers;
    }

    public int getReadyPlayers() {
        return readyPlayers;
    }

    public void setReadyPlayers(int readyPlayers) {
        this.readyPlayers = readyPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }
}
