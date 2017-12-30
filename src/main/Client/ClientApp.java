package Client;
import com.google.gson.Gson;
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;



public class ClientApp{


    public static void main(String[] args) {


        Application currentApp = new FirstPage();
        String [] arguments = {"pierwszy","drugi","trzeci"};
        Game t[] = new Game[5];
        for(int i = 0; i < t.length; i++){
            t[i] = new Game("gra" + i, i * 2, i * 3);
        }

        GameView view = new GameView("gra1", 4, 5);
        Gson gson = new Gson();
        String json = gson.toJson(t);

//        System.out.println(gson.fromJson(json, Game[].class)[3].getGameName());
        arguments[0] = json;
        Application.launch(FirstPage.class, arguments);


//        System.out.println(t);
    }


}
