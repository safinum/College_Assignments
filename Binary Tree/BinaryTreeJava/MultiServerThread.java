import java.io.*;
import java.net.*;
 
/**
 * Klasa uruchamiająca serwer na porcie 4444.
 */
public class MultiServerThread {
    /**
     * Metoda główna klasy MultiServerThread.
     */
 
    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(4444)) {
 
            System.out.println("Server is listening on port 4444");
 
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");
 
                new MultiThread(socket).start();
            }
 
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}