package Client;

import javafx.scene.control.Label;

public class CheckingGame implements Runnable {

    Label nameLabel;
    Label actualLabel;
    Label readyLabel;
    ConnectionManagerInterface manager;
    Game game;
    boolean running;

    @Override
    public void run() {
        running = true;
        game = manager.getGame();
        while (game != null && running){
            nameLabel.setText("Oczekujesz na: " + game.getName());
            actualLabel.setText("Aktualnie oczekujacych: " + game.getActualPlayers());
            readyLabel.setText("Aktualnie gotowych: "  + game.getReadyPlayers());

            try{
                System.out.println("zyje");
                Thread.sleep(1000);
            }catch (Exception e){
                System.out.println("ups");
            }

            game = manager.getGame();
        }

    }
    public void stop(){
        running = false;
    }

    CheckingGame(Label nameLabel, Label actualLabel, Label readyLabel, ConnectionManagerInterface manager){
        this.nameLabel = nameLabel;
        this.actualLabel = actualLabel;
        this.readyLabel = readyLabel;
        this.manager = manager;
    }


}
