package Client;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GamePage extends Application {

    //Szerokosc i wysokosc aplikaci
    private final int HEIGHT = 600;
    private final int WIDTH = 600;

    private ConnectionManagerInterface manager;
    private Game game;

    public static void main(String[] args) {
        launch("gra3");
    }
    @Override
    public void init() throws Exception {
        Parameters parameters = getParameters();
        String gameName = parameters.getRaw().get(0);

        manager = new FakeConnectionManager();

        if(!manager.joinGame(gameName))
            System.exit(0);

        game = manager.getGame();


    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        lobbyWindow();

    }
    void lobbyWindow(){

        Stage stage = new Stage();
        Label nameLabel = new Label();
        nameLabel.setFont(new Font("Arial", 17));
        Label actualLabel = new Label();
        actualLabel.setFont(new Font("Arial", 17));
        Label readyLabel = new Label();
        readyLabel.setFont(new Font("Arial", 17));
        CheckingGame checkingGame = new CheckingGame(nameLabel, actualLabel, readyLabel, manager);
        Thread checkingGameThread = new Thread(checkingGame);
        checkingGameThread.start();

        VBox vBox = new VBox(nameLabel, actualLabel, readyLabel);
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vBox, WIDTH, HEIGHT);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                checkingGame.stop();
            }
        });
        stage.setScene(scene);
        stage.show();





    }

}
