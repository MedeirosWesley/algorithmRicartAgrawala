import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Node {
    private String id = "80699";
    private MyState myState = MyState.FREE;
    private int osn = 0;
    private int hsn = 0;
    private List<Boolean> oks = new ArrayList<>();
    private List<String> requests = new ArrayList<>();

    private Socket socket1;
    private Socket socket2;

    private  DataInputStream in;
    private DataOutputStream out;

    public MyState getState() {
        return myState;
    }

    public void setState(MyState myState) {
        this.myState = myState;
    }

    public Node(Socket socket1, Socket socket2){
        this.socket1 = socket1;
        this.socket2 = socket2;
    }

    public Socket getSocket2() {
        return socket2;
    }

    public void setSocket2(Socket socket2) {
        this.socket2 = socket2;
    }

    public Socket getSocket1() {
        return socket1;
    }

    public void setSocket1(Socket socket1) {
        this.socket1 = socket1;
    }

    public boolean check() {
        oks.add(true);
        if(oks.size() == 2){
            oks.clear();
            this.myState = MyState.BUSY;
            return true;
        }
        return false;
    }

    public void request() {

    }

    public void pushRequest(){}
}
