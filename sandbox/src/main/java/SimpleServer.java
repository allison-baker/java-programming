import java.io.*;
import java.net.*;

public class SimpleServer {
    public static void main(String[] args) {
        MockDatabase.setUp();

        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Server is listening on port 1234.");

            // Wait for a client connection
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");

                // Handle the client connection in a separate thread (optional)
                new ClientHandler(socket).start();
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

    private static class ClientHandler extends Thread {
        private Socket socket;

        public ClientHandler(Socket socket) { this.socket = socket; }

        public void run() {
            try (
                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);
            ) {
                String message;
                while ((message = reader.readLine()) != null) {
                    System.out.println("Received: " + message);

                    String[] parts = message.split("-");
                    MockDatabase.setPair(parts[0], parts[1]);
                    writer.println("Echo: " + message);

                    for (String key : MockDatabase.getKeys()) {
                        writer.println(key + "-" + MockDatabase.getValue(key));
                    }

                    writer.println("END");
                }
            } catch (IOException e) { e.printStackTrace(); }
              finally { try { socket.close(); } catch (IOException e) { e.printStackTrace(); } }
        }
    }
}
