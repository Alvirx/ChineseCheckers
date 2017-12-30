package Server;

import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class LobbyTest {
    @Test
    public void ShouldReturnAlwaysTheSameInstance() throws Exception
    {
        Lobby l1 = Lobby.getInstance();
        Lobby l2 = Lobby.getInstance();
        assertSame(l1,l2);
    }

    @Test
    public void ShouldReturnTheSameSpecifiedGame() throws Exception
    {
        Game game1 = Lobby.getInstance().getGame("game");
        Game game2 = Lobby.getInstance().getGame("game");
        assertSame(game1,game2);
    }

    @Test
    public void ShouldReturnDifferentSpecifiedGames() throws Exception
    {
        Game game1 = Lobby.getInstance().getGame("game");
        Game game2 = Lobby.getInstance().getGame("other_game");
        assertNotSame(game1,game2);
    }

    @Test
    public void ShouldReturnListOfGames() throws Exception
    {
        Game game1 = Lobby.getInstance().getGame("game");
        Game game2 = Lobby.getInstance().getGame("other_game");
        Game game3 = Lobby.getInstance().getGame("another_game");
        LinkedList<Game> list = Lobby.getInstance().getListOfGames();
        assertEquals(list.get(0).getName(),"game");
        assertEquals(list.get(1).getName(),"other_game");
        assertEquals(list.get(2).getName(),"another_game");
    }

}