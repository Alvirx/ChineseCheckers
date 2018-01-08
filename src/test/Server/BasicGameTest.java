package Server;

import org.junit.Test;

import java.net.Socket;

import static org.junit.Assert.*;

public class BasicGameTest {

    Game game;

    @Test
    public void ShouldReturnGameName() throws Exception
    {
        game = new BasicGame("Game");
        assertEquals("Game", game.getName());
    }

    @Test
    public void ShouldAddPlayer() throws Exception
    {
        game = new BasicGame("Game");
        Player p1 = new BasicPlayer(new Socket());
        game.addPlayer(p1);
        assertEquals(1, game.getActualPlayers());
    }

    @Test(expected = Exception.class)
    public void ShouldThrowExceptionIfThereIsToMuchPlayers() throws Exception
    {
        game = new BasicGame("Game");
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
    public void ShouldRemovePlayer() throws Exception
    {
        game = new BasicGame("Game");
        Player p1 = new BasicPlayer(new Socket());
        Player p2 = new BasicPlayer(new Socket());
        game.addPlayer(p1);
        game.addPlayer(p2);
        game.removePlayer(p1);
        assertEquals(1, game.getActualPlayers());
    }



    @Test
    public void ShouldReturnNumberOfPlayers() throws Exception {
        game = new BasicGame("Game");
        game.addPlayer(new BasicPlayer(new Socket()));
        game.addPlayer(new BasicPlayer(new Socket()));
        game.addPlayer(new BasicPlayer(new Socket()));
        assertEquals(3,game.getActualPlayers());
    }

    @Test
    public void ShouldMarkPlayerAsReady()
    {
        //TODO
    }

    @Test
    public void  ShouldMarkPlayerAsNotReady()
    {
        //TODO
    }

    @Test
    public void ShouldCheckIfAllPlayersAreReady()
    {
        //TODO
    }


}