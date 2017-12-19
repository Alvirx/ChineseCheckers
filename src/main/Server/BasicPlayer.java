package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class BasicPlayer implements Player
{
    int playerNumber;
    Socket socket;
    BufferedReader input;
    PrintWriter output;
    Game myGame;

    public void init(Socket socket, int number, Game game)
    {
        this.socket=socket;
        this.playerNumber=number;
        this.myGame=game;
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
        } catch (Exception e) {
            myGame.log("Player can`t connect at the moment: " + e);
        }
    }


    @Override
    public void run() {
        try {
            String command = input.readLine();

            //for now it just prints commands from player
            System.out.println(command);
            output.println("Hello I am server");

        } catch (IOException e) {
            myGame.log("Player disconnected");
        }
    }
}
