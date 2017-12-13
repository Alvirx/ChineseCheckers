package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Player extends Thread
{
    int playerNumber;
    Socket socket;
    BufferedReader input;
    PrintWriter output;

    Player(Socket socket, int number)
    {
        this.socket=socket;
        playerNumber=number;

        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
        } catch (Exception e) {
            super.log("Player can`t connect at the moment: " + e);
        }
    }

    @Override
    public void run() {
        try {
            String command = input.readLine();
        } catch (IOException e) {
            //TODO
        }
    }
}
