import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * The Client.
 */
public class Client{
    /**
     * Main.
     * @param args the args
     */
    public static void main(String[] args){
        try{
            Socket socket = new Socket("localhost",8000);
            System.out.println("Client connected to the server ");
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            String text;

            while(true)
            {
                /*if ((dataInputStream.available() == 1)&& dataInputStream.toString().equals("over"))
                {
                    break;
                }*/
                //System.out.println("++++++++++++++++++");
                text = scanner.nextLine();
                dataOutputStream.writeUTF(text);
                if (text.equalsIgnoreCase("over"))
                    break;
                System.out.println(" " + dataInputStream.readUTF() + " ");
            }
            dataOutputStream.close();
            dataInputStream.close();
            socket.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}



