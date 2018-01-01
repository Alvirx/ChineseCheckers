package Client;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 */
public class GameClient
{
    private static int PORT = 8901;
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    void sendMessage(String message){
        System.out.println("wysyłam: " + message);
        output.println(message);
    }
    String receiveMessage(){
        String message = null;
        try {
            message = input.readLine();
        }catch (Exception e){
            System.out.println("error, nie wiem co mam robic");
        }finally {
            System.out.println("odbieram: " + message);
            return message;
        }
    }

    boolean closeConnection(){
        try{
            socket.close();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    GameClient(String serverAddress) throws Exception
    {
        socket = new Socket(serverAddress, PORT);
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(socket.getOutputStream(), true);
    }

/*
    public void play() throws Exception
    {
        String response;
        try {
            while (true) {
                response = input.readLine();
                output.println("JOIN");
                output.println("hihi");

                System.out.println(response);
            }
        } catch (Exception e) {
            System.out.println("Can`t connect to the server");
        } finally {
            socket.close();
        }
    }

*/

/*    public static void main(String [] args)
    {
        try {
            GameClient client = new GameClient("localhost");
            client.play();

        } catch (Exception e) {
            System.out.println("Can`t connect to the server");
        }
    }*/
}
