import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;


public class Connection extends Thread{
    DataInputStream in;
    DataOutputStream out;
    DataInputStream in2;
    DataOutputStream out2;
    Node node;

    public Connection(Node node) throws IOException {
        try {
            this.node = node;
            in = new DataInputStream(node.getSocket1().getInputStream());
            out = new DataOutputStream(node.getSocket1().getOutputStream());
            in2 = new DataInputStream(node.getSocket2().getInputStream());
            out2 = new DataOutputStream(node.getSocket2().getOutputStream());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void verifyState(String req){
        switch (node.getState()){
            case BUSY:
                node.pushRequest();
                break;
            case  WAITING:

                break;
            case FREE:
                break;
        }
    }

    public void run(){
        try {
            while (true){
                if(in.available() > 0){
                    String req =  in.readUTF();
                    if(req.equals("ok")){
                        node.check();
                    }else {
                        verifyState(req);
                    }
                }
                if(in2.available() > 0){
                    String req =  in2.readUTF();
                    if(req.equals("ok")){
                        node.check();
                    }else {
                        node.request();
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                node.getSocket1().close();
                node.getSocket2().close();
                System.out.println("Conexão fechada!");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
