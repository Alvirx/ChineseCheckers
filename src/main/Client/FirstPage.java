package Client;

import com.google.gson.Gson;
import javafx.application.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.*;
import javafx.scene.*;

import java.util.List;


public class FirstPage  extends Application{
    private final int HEIGHT = 600;
    private final int WEIGHT = 600;
//    private String args;
    private List<String> args;
    private TableView<GameView>  gamesTable;// = new TableView<GameView>();
    private ObservableList<GameView> data ;/*//= FXCollections.observableArrayList(
            new GameView("gra1", 4, 6),
            new GameView("gra2", 4, 6),
            new GameView("gra3", 4, 6)
    );*/


    @Override
    public void init() throws Exception{
        gamesTable = new TableView<GameView>();
        data = FXCollections.observableArrayList();

        args = getParameters().getRaw();
        System.out.println(args.get(0));
        String json = args.get(0);
        Gson gson = new Gson();
        Game [] gT = gson.fromJson(json, Game[].class);
        for(Game game: gT){
            data.add(new GameView(game.getGameName(), game.getActualNumberOfPLayers(), game.getMaxNumberOfPlayers()));
        }
        gamesTable = new TableView<GameView>();
        data = FXCollections.observableArrayList();
    }
    @Override
    public void start(Stage primaryStage){

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

        //tworzymy layout
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10, 10, 0, 10));
        vBox.getChildren().addAll(infoLabel, gamesTable);

        Scene scene = new Scene(vBox, WEIGHT, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();

    }



}
