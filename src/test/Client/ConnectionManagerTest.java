package Client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionManagerTest {

    ConnectionManager manager;
    @BeforeEach
    void setUp() {
        try{
            manager = new ConnectionManager();
        }catch (Exception e){
            //do nothing
        }
    }

    @Test
    void getGamesTest(){
        try {
            manager = new ConnectionManager();
        }catch (Exception e){

        }
        Game[] games = manager.getGames();
        System.out.println(games.length);
    }
}