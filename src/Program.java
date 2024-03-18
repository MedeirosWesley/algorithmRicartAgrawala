import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.locks.Condition;

public class Program {
    public void main(String[] arg) throws IOException {
        try {
            int serverPort = 7896;
            ServerSocket listenSocket = new ServerSocket(serverPort);
            Socket socket1 = listenSocket.accept();
            Socket socket2 = listenSocket.accept();
            Node node = new Node(socket1, socket2);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
