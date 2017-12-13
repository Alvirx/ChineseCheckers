package Client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameClient
{
    private static int PORT = 8901;
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    public GameClient(String serverAddress) throws Exception
    {
        socket = new Socket(serverAddress, PORT);
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(socket.getOutputStream(), true);
    }

    public void play() throws Exception
    {
        String response;
        try {
            while (true) {
                output.println("Hey I am client");
                response = input.readLine();
                //for now it just prints responses
                System.out.println(response);
            }
        } catch (Exception e) {
            System.out.println("Can`t connect to the server");
        } finally {
            socket.close();
        }
    }


    public static void main(String [] args)
    {
        try {
            GameClient client = new GameClient("localhost");
            client.play();

        } catch (Exception e) {
            System.out.println("Can`t connect to the server");
        }
    }
}
