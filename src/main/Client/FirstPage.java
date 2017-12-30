package Client;

import com.google.gson.Gson;
import javafx.application.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.*;
import javafx.scene.*;

import java.util.List;


public class FirstPage  extends Application{

    //Szerokosc i wysokosc aplikaci
    private final int HEIGHT = 600;
    private final int WIDTH = 600;
    private TableView<GameView>  gamesTable;// = new TableView<GameView>();
    private ObservableList<GameView> data ;

    private  ConnectionManagerInterface manager;


    @Override
    public void init() throws Exception{
        gamesTable = new TableView<GameView>();
        data = FXCollections.observableArrayList();
        manager = new ConnectionManager();

        Game [] gT = manager.getGames();
        for(Game game: gT){
            data.add(new GameView(game.getGameName(), game.getActualNumberOfPLayers(), game.getMaxNumberOfPlayers()));
        }
    }
    @Override
    public void start(Stage primaryStage){

        //Etykieta na gorze
        Label infoLabel = new Label("Dostepne gry:");
        infoLabel.setFont(new Font("Arial", 20));

        gamesTable.setEditable(false);

        //stworzmy porzadny tableView
        TableColumn nameOfGameColumn = new TableColumn("Nazwa Gry");
        nameOfGameColumn.setCellValueFactory(new PropertyValueFactory<GameView, String>("gameName"));
        TableColumn freePlacesColumn = new TableColumn( "Zajete miejsca");
        freePlacesColumn.setCellValueFactory(new PropertyValueFactory<GameView, String>("actualNumberOfPLayers"));

        TableColumn maxPlaceColumn = new TableColumn("Maksymalna ilosc graczy");
        maxPlaceColumn.setCellValueFactory(new PropertyValueFactory<GameView, String>("maxNumberOfPlayers"));

        gamesTable.setItems(data);
        gamesTable.getColumns().addAll(nameOfGameColumn, freePlacesColumn, maxPlaceColumn);
        gamesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //Na dole panel z przyciskami;
        Button newGameButton = new Button("Nowa gra");
        newGameButton.setOnAction(e -> {
            createGame();
        });
        HBox bottomPanel = new HBox();
        bottomPanel.getChildren().add(newGameButton);


        //tworzymy layout
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10, 10, 0, 10));
        vBox.getChildren().addAll(infoLabel, gamesTable, bottomPanel);

        Scene scene = new Scene(vBox, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void createGame(){
        Stage stage = new Stage();
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(10, 10,10,10));

        TextField nameTextField = new TextField();
        nameTextField.setPromptText("Nazwa Gry");
        TextField maxTextField = new TextField();
        maxTextField.setPromptText("Maks");

        Button createButton = new Button("Utworz");

        createButton.setOnAction( e ->{
            String name = nameTextField.getText();
            String max = maxTextField.getText();

            manager.chooseGame(new Game(name, Integer.parseInt(max)));
            stage.close();


        });

        hBox.getChildren().addAll(nameTextField, maxTextField, createButton);
        Scene scene = new Scene(hBox, WIDTH/2, HEIGHT/2);
        stage.setScene(scene);
        stage.show();

    }



}
