import java.io.*;
import java.net.*;

public class SimpleClient {
    public static void main(String[] args) {
        String hostname = "localhost";
        // String hostname = "127.0.0.1";
        int port = 1234;
         try (
            Socket socket = new Socket(hostname, port);
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader((input)));
         ) {
             // Determine if it's get or post
             String operation = args[0];
             switch (operation) {
                 case "GET":
                     writer.println(args[1]);
                     break;
                 case "POST":
                     break;
             }
             // Send a message to the server
            writer.println(args[0]);

            // Read the server's response
             String response;
             while ((response = reader.readLine()) != null) {
                 if (response.equals("END")) { break; }
                 System.out.println("Server response: " + response);
             }
         } catch (IOException e) {
            e.printStackTrace();
         }
    }
}
