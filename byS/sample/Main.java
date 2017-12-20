package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import sample.board.Board;
import sample.board.Field;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Main extends Application {
    // rozmiar sceny
    int STAGE_WIDTH = 600;
    int STAGE_HEIGHT = 600;

    // informacje o polu
    int RADIAOUS_OF_FIELD = 10;
    int DISTANCE_BETWEEN_FIELDS = 5;



    @Override
    public void start(Stage primaryStage) throws Exception{

        Group root = new Group();
        draw(root);
        Scene scene = new Scene(root, 600, 600);
        primaryStage.setTitle("Chinese cheekers");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    void draw(Group group){

/*        Circle circle1 = new Circle();
        circle1.setCenterX(100.0f);
        circle1.setCenterY(100.0f);
        circle1.setRadius(50.0f);
        group.getChildren().add(circle1);*/

        int startX = STAGE_WIDTH / 2;
        int startY = STAGE_HEIGHT  /8;
        int radious = 10;
        int multiplier = 15;

        Board board = new Board(4);
        HashSet<Field> fields = board.getFields();

        for(Field field : fields){
            Circle circle = new Circle();
            circle.setRadius(radious);
            circle.setCenterX(startX + field.getX() * multiplier);
            circle.setCenterY(startY + field.getY() * multiplier);
            System.out.println("namalowano" + field);

            group.getChildren().add(circle);


        }




        boolean hasChildren = true;

        HashSet<Field> used = new HashSet<>();




    }




    public static void main(String[] args) {
        launch(args);
    }
}
