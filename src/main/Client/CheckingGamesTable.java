package Client;

import javafx.collections.ObservableList;

public class CheckingGamesTable implements Runnable{
    private ObservableList<Game> data;
    private ConnectionManagerInterface manager;

    @Override
    public void run() {
        Game[] games;
        while(data != null){
            games = manager.getGames();
            for(Game game: data){

            }
        }
    }

    CheckingGamesTable(ObservableList<Game> data, ConnectionManagerInterface manager){
        this.data = data;
        this.manager = manager;
    }
}
