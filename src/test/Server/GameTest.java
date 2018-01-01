package Server;

import org.junit.Test;

import java.net.Socket;

import static org.junit.Assert.*;

public class GameTest {

    Game game = new Game("game")
    {
        @Override
        public void startGame() {
            System.out.println("game_started");
        }
    };

    @Test
    public void ShouldReturnGameName() throws Exception
    {
        assertEquals("Game", game.getName());
    }

    @Test
    public void ShouldAddPlayer() throws Exception
    {
        Player p1 = new BasicPlayer(new Socket());
        game.addPlayer(p1);
        assertEquals(1, game.getActualPlayers());
    }

    @Test(expected = Exception.class)
    public void ShouldThrowExceptionIfThereIsToMuchPlayers() throws Exception
    {
        Player p1 = new BasicPlayer(new Socket());
        Player p2 = new BasicPlayer(new Socket());
        Player p3 = new BasicPlayer(new Socket());
        Player p4 = new BasicPlayer(new Socket());
        Player p5 = new BasicPlayer(new Socket());
        Player p6 = new BasicPlayer(new Socket());
        Player p7 = new BasicPlayer(new Socket());
        game.addPlayer(p1);
        game.addPlayer(p2);
        game.addPlayer(p3);
        game.addPlayer(p4);
        game.addPlayer(p5);
        game.addPlayer(p6);
        game.addPlayer(p7);

    }



    @Test
    public void ShouldremovePlayer() throws Exception
    {
        Player p1 = new BasicPlayer(new Socket());
        Player p2 = new BasicPlayer(new Socket());
        game.addPlayer(p1);
        game.addPlayer(p2);
        game.removePlayer(p1);
        assertEquals(1, game.getActualPlayers());
    }


}