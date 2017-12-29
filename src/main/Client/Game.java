package Client;

public class Game {
    private String gameName;
    private int maxNumberOfPlayers;
    private int actualNumberOfPLayers;

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
