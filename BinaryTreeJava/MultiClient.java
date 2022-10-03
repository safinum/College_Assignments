import java.net.*;
import java.io.*;
 
/**
 * Klasa umożliwiająca połączenie klienta z serwerem.
 */
public class MultiClient {
    /**
     * Metoda główna klasy MultiClient.
     */
 
    public static void main(String[] args) {

    try  {
 
        Socket socket = new Socket("localhost", 4444); 
            // Wysylanie do serwera
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        // Odbieranie z serwera
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        
        Console console = System.console();
        String text, textServer;

        while (true){
            textServer = in.readLine();
            System.out.println(textServer);
            if(textServer.indexOf(':') != -1){
                text = console.readLine();
                if(text.equals("bye")) break;
                out.println(text);
            }  
        }
           
        socket.close();
 
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
 
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}