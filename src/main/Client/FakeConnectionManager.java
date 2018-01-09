package Client;

import java.util.ArrayList;

public class FakeConnectionManager implements ConnectionManagerInterface{

    private ArrayList<Game> games;
    private Game actualGame;

    @Override
    public Game getGame() {
        System.out.println("getGame");
        return actualGame;
    }

    @Override
    public Game[] getGames() {
        System.out.println("getGames");
        return (Game[]) games.toArray(new Game[games.size()]);
    }

    @Override
    public boolean joinGame(String gameName) {
        System.out.println("Wybieram: " + gameName);
        for(Game game: games) {
            if (game.getName().equals(gameName))
                actualGame = game;
        }
        if(actualGame == null){
            actualGame = new Game(gameName,  6);
            games.add(actualGame);

        }
        return (actualGame != null);

    }

    FakeConnectionManager(){
        games = new ArrayList<Game>();
        for(int i = 0; i < 5; i++){
            games.add(new Game("Game: " + i, 6));
        }


    }
}
