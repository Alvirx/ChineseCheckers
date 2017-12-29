package Client;
import com.google.gson.Gson;
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;



public class ClientApp{


    public static void main(String[] args) {


        Application currentApp = new FirstPage();
        String [] arguments = {"pierwszy","drugi","trzeci"};
        Application.launch(FirstPage.class, arguments);
        GameView view = new GameView("gra1", 4, 5);
        Gson gson = new Gson();
        int [] t = {3,2,5,2};
        String json = gson.toJson(t);


        System.out.println(gson.toJson(view));
    }


}
