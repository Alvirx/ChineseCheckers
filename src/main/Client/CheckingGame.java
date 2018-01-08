package Client;

import javafx.scene.control.Label;

public class CheckingGame implements Runnable {

    Label nameLabel = new Label("Oczekujesz na: " + game.getName());
    Label actualLabel = new Label("Aktualnie oczekujacych: ");
    Label readyLabel = new Label("Aktualnie gotowych: "  + game.getName());
    ConnectionManagerInterface manager;
    Game game;
    @Override
    public void run() {
        game = manager.getGame();
        while (game != null){
            nameLabel.setText("Oczekujesz na: " + game.getName());
            actualLabel.setText("Aktualnie oczekujacych: " + game.getActualPlayers());
            readyLabel.setText("Aktualnie gotowych: "  + game.getReadyPlayers());

            try{
                Thread.sleep(1000);
            }catch (Exception e){
                System.out.println("ups");
            }

            game = manager.getGame();
        }

    }

    CheckingGame(Label nameLabel, Label actualLabel, Label readyLabel, ConnectionManagerInterface manager){
        this.nameLabel = nameLabel;
        this.actualLabel = actualLabel;
        this.readyLabel = readyLabel;
        this.manager = manager;
    }


}
