package Client;

public class Game {
    private String gameName;
    private int maxNumberOfPlayers;
    private int actualNumberOfPLayers;

    public Game(String gameName, int maxNumberOfPlayers) {
        this.gameName = gameName;
        this.maxNumberOfPlayers = maxNumberOfPlayers;
        this.actualNumberOfPLayers = 0;
    }
    Game(String gameName, int actualNumberOfPLayers, int maxNumberOfPlayers){
        this.gameName = gameName;
        this.actualNumberOfPLayers = actualNumberOfPLayers;
        this.maxNumberOfPlayers = maxNumberOfPlayers;

    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public int getMaxNumberOfPlayers() {
        return maxNumberOfPlayers;
    }

    public void setMaxNumberOfPlayers(int maxNumberOfPlayers) {
        this.maxNumberOfPlayers = maxNumberOfPlayers;
    }

    public int getActualNumberOfPLayers() {
        return actualNumberOfPLayers;
    }

    public void setActualNumberOfPLayers(int actualNumberOfPLayers) {
        this.actualNumberOfPLayers = actualNumberOfPLayers;
    }
}
