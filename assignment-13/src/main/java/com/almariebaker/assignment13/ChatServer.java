package com.almariebaker.assignment13;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    public static final int DEFAULT_PORT = 4688;
    List<ClientHandler> clients = new ArrayList<>();
    int clientCount;

    public ChatServer() { }

    void run() {
        try (ServerSocket serverSocket = new ServerSocket(DEFAULT_PORT)) {
            int id = 0;
            this.clientCount = 0;
            serverSocket.setReuseAddress(true);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Spawning " + id);

                // Create new handler for this client and thread for this handler
                ClientHandler handler = new ClientHandler(socket, id, this);
                Thread handlerThread = new Thread(handler);

                // Add handler to list of clients
                this.clients.add(handler);
                System.out.println(this.clients.size() + " clients in the list.");

                // Start thread
                handlerThread.start();

                // Increment id anc client count
                ++id;
                this.clientCount = id;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendToAll(String message, int id) {
        Iterator<ClientHandler> clientIterator = this.clients.iterator();

        // While there are more clients in the list
        while (clientIterator.hasNext()) {
            ClientHandler currentHandler = clientIterator.next();

            // Send message to this client if it's connected and not the same as the sender
            if (currentHandler.id != id && currentHandler.isConnected()) {
                currentHandler.send(message);
            }
        }
    }

    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer();
        chatServer.run();
    }

    private static class ClientHandler implements Runnable {
        private final Socket incoming;
        int id;
        Scanner in;
        PrintWriter out;
        ChatServer server;
        String name;
        boolean connected;

        public boolean isConnected() {
            return this.connected;
        }

        public ClientHandler(Socket socket, int id, ChatServer chatServer) {
            this.incoming = socket;
            this.id = id;
            this.server = chatServer;
            System.out.println("ClientHandler socket local port is " + this.incoming.getLocalPort() + " remote port is " + this.incoming.getPort());
        }

        public void send(String message) {
            if (this.connected) {
                this.out.println(message);
            }
        }

        public void handleMsg(String message) {
            // Split message on spaces
            String[] words = message.split("\\s");
            System.out.println(this.id + ": " + message);

            // Check if first word is 'disconnect' or send message to all clients
            if (words[0].equalsIgnoreCase("disconnect")) {
                this.disconnect();
            } else {
                this.server.sendToAll(this.name + ": " + message, this.id);
            }
        }

        public void disconnect() {
            this.server.sendToAll(this.name + " has left the chat.", this.id);

            try {
                // Close connection and remove client from list
                this.send("disconnected");
                this.connected = false;
                this.incoming.close();
                this.server.clients.remove(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void getConnectMsg() {
            // Split message on spaces
            String message = this.in.nextLine();
            String[] words = message.split("\\s");

            // Check if first word is 'connect' and set username and id
            if (words.length >= 2 && words[0].equalsIgnoreCase("connect")) {
                this.name = words[1];
                this.server.sendToAll(this.name + " has joined the chat.", this.id);
            } else {
                System.out.println("*" + message + "*");
                throw new RuntimeException("invalid connect string");
            }
        }

        public void run() {
            try (
                InputStream input = this.incoming.getInputStream();
                OutputStream output = this.incoming.getOutputStream();
            ) {
                this.connected = true;
                this.in = new Scanner(input);
                this.out = new PrintWriter(output, true);
                this.send("Hello! Welcome to the CS3250 Chat Server.");
                this.getConnectMsg();

                while (this.connected && this.in.hasNextLine()) {
                    this.handleMsg(this.in.nextLine());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
