package Client;

import com.google.gson.Gson;
import javafx.application.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.*;
import javafx.scene.*;

import java.util.ArrayList;


public class FirstPage  extends Application{

    //Szerokosc i wysokosc aplikaci
    private final int HEIGHT = 600;
    private final int WIDTH = 600;

    /**
     * Konstrukcja odpowiedzialna za wyswietlanie gier,
     * gameTable to tablica w ktorej zostaja wyswietlane klasa GameView w osobnych rzedach przechowywane w data
     */
    private TableView<GameView>  gamesTable;
    private ObservableList<GameView> data ;

    /**
     * Manager to klasa odpowiezialna za odebranie obiektu, zamiane go na jsona i wyslanie do serwera
     */
    private  ConnectionManagerInterface manager;
    private ArrayList<GamePage> games;


    @Override
    public void init() throws Exception{
        gamesTable = new TableView<GameView>();
//        manager = new ConnectionManager();
        manager = new FakeConnectionManager();
    }
    @Override
    public void start(Stage primaryStage){
        listOfGameWindow(primaryStage);
    }

    void listOfGameWindow(Stage stage){

        loadData();

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

        Button chooseGameButton = new Button("Wejdz w gre");
        chooseGameButton.setOnAction(e -> {
            chooseGame();
        });

        Button refreshButton = new Button("Odswież");
        refreshButton.setOnAction(e -> {
            loadData();
        });
        HBox bottomPanel = new HBox();
        bottomPanel.setSpacing(10);
        bottomPanel.getChildren().addAll(newGameButton, chooseGameButton, refreshButton);


        //tworzymy layout
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10, 10, 0, 10));
        vBox.getChildren().addAll(infoLabel, gamesTable, bottomPanel);

        Scene scene = new Scene(vBox, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();

    }

    //Nadaje funkcjonalnosc przyciskowi newGame
    private void createGame(){
        Stage stage = new Stage();
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(10, 10,10,10));

        Label infoLabel = new Label();
        infoLabel.setText("Podaj prosze nazwe nowej gry :)");
        infoLabel.setFont(new Font("Arial", 17));

        TextField nameTextField = new TextField();
        nameTextField.setPromptText("Nazwa Gry");
//        TextField maxTextField = new TextField();
//        maxTextField.setPromptText("Maks");

        Button createButton = new Button("Utworz");

        createButton.setOnAction( e ->{
                String name = nameTextField.getText();
                joinGame(name);
                stage.close();

        });

        hBox.getChildren().addAll(nameTextField, createButton);
        hBox.setSpacing(5);
        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.setAlignment(Pos.CENTER);
        VBox vBox = new VBox();
        vBox.getChildren().addAll(infoLabel, hBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);
        Scene scene = new Scene( vBox, WIDTH * 0.8, HEIGHT/2);
        stage.setScene(scene);
        stage.show();

    }

    //Nadaje funkcjonalnosc przyciskowi StartGameButton
    void chooseGame(){

        GameView selectedItem = gamesTable.getSelectionModel().getSelectedItem();
        joinGame(selectedItem.getGameName());
    }
    private void loadData(){
        data = FXCollections.observableArrayList();
        Game [] gT = manager.getGames();
        for(Game game: gT){
            data.add(new GameView(game.getName(), game.getActualPlayers(), game.getMaxPlayers()));
        }
        gamesTable.setItems(data);
//        Thread checkingGamesTableThread = new Thread(new CheckingGamesTable(data, manager));
//        checkingGamesTableThread.start();
    }

    void joinGame(String gameName){
//        String [] args = new String[1];
//        args[0] = gameName;
//        Application.launch(GamePage.class, args);
        manager.joinGame(gameName);
        lobbyWindow();
    }
    void lobbyWindow(){
        games.add(new GamePage(manager));



    }

    public static void main(String[] args) {
        launch(args);
    }


}
