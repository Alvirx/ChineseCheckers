package Client;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GameView {

    private StringProperty gameName;
    private IntegerProperty maxNumberOfPlayers;
    private IntegerProperty actualNumberOfPLayers;

    GameView(String name, int actualNumber, int maxNumber){
        gameName = new SimpleStringProperty(name);
        maxNumberOfPlayers = new SimpleIntegerProperty(maxNumber);
        actualNumberOfPLayers = new SimpleIntegerProperty(actualNumber);
    }

    public String getGameName() {
        return gameName.getValue();
    }

    public void setGameName(String gameName) {
        this.gameName.set(gameName);
    }

    public int getMaxNumberOfPlayers() {
        return maxNumberOfPlayers.get();
    }

    public void setMaxNumberOfPlayers(int maxNumberOfPlayers) {
        this.maxNumberOfPlayers.set(maxNumberOfPlayers);
    }

    public int getActualNumberOfPLayers() {
        return actualNumberOfPLayers.get();
    }

    public void setActualNumberOfPLayers(int actualNumberOfPLayers) {
        this.actualNumberOfPLayers.set(actualNumberOfPLayers);
    }
}
