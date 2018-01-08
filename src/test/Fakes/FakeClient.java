package Fakes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
        import java.io.PrintWriter;
        import java.net.Socket;

public class FakeClient
{
    private static int PORT = 8901;
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    public FakeClient(String serverAddress) throws Exception
    {
        socket = new Socket(serverAddress, PORT);
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(socket.getOutputStream(), true);
    }

    public void sent(String text) throws Exception
    {
        output.println(text);
        socket.close();
    }

    public String read()
    {
        try {
            String response = input.readLine();
            return input.readLine();
        } catch (IOException e) {
            return null;
        }

    }

}
