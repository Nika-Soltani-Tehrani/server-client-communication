import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * The Server.
 */
public class Server{
    /**
     * Main.
     * @param args the args
     */
    public static void main(String[] args){
        try{
            ServerSocket serverSocket = new ServerSocket(8000);
            System.out.println("Server is listening...");
            Socket socket = serverSocket.accept();
            System.out.println("Server connected to port " + socket.getLocalAddress() + "----" +socket.getPort());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());


            String original;
            String text = "";
            Scanner scanner = new Scanner(System.in);
            //String in;

            while (true) {
                /*if(scanner.hasNext())
                {
                    if(scanner.nextLine().equalsIgnoreCase("over")) {
                        dataOutputStream.writeUTF("over");
                        break;
                    }
                }*/
                original = dataInputStream.readUTF();
                if (original.equals("over")) {
                    dataOutputStream.writeUTF("over");
                    break;
                }
                //String reverse = new StringBuilder(original).reverse().toString();
                //text = text.concat(reverse);
                //text = text.concat(text);
                text = text.concat(original);
                System.out.println("wrote " + text + " on client.");
                dataOutputStream.writeUTF(text);
            }

            System.out.println("Connection ended! ");
            dataOutputStream.close();
            dataInputStream.close();
            serverSocket.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}