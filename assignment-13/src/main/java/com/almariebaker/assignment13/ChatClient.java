package com.almariebaker.assignment13;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.*;

public class ChatClient extends JFrame {
    public static final int DEFAULT_PORT = 4688;
    ChatReader chatReader;
    JTextArea msgTextArea;
    JScrollPane scrollPane;
    JTextField msgEntryField;
    JPanel entryPanel;
    Socket socket;
    Scanner input;
    PrintWriter output;
    String username = "anon";

    synchronized void addMessage(String var1) {
        this.msgTextArea.append(var1);
        this.msgTextArea.setCaretPosition(this.msgTextArea.getDocument().getLength());
    }

    public ChatClient(String user) {
        this.username = user;
        this.setTitle("Chat: " + user);
        this.setLayout(new BorderLayout());
        this.msgTextArea = new JTextArea();
        this.scrollPane = new JScrollPane(this.msgTextArea);
        this.msgEntryField = new JTextField();
        this.entryPanel = new JPanel();
        this.add(this.scrollPane, "Center");
        this.add(this.msgEntryField, "South");
        InputMap inputMap = this.msgEntryField.getInputMap(JComponent.WHEN_FOCUSED);
        ActionMap actionMap = this.msgEntryField.getActionMap();
        inputMap.put(KeyStroke.getKeyStroke(10, 0), "sendMessageCmd");
        actionMap.put("sendMessageCmd", new KeyAction("sendMessageCmd"));
        this.msgTextArea.setLineWrap(true);
        this.msgTextArea.setWrapStyleWord(true);
    }

    void connect() {
        try {
            this.socket = new Socket("localhost", 4688);
            this.input = new Scanner(this.socket.getInputStream());
            this.output = new PrintWriter(this.socket.getOutputStream(), true);
            this.chatReader = new ChatReader();
            this.chatReader.start();
            this.output.println("connect " + this.username);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void disconnect() {
        this.output.println("disconnect " + this.username);
        this.chatReader.disconnect();

        try {
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.exit(0);
    }

    void sendMessage() {
        String message = this.msgEntryField.getText();
        if (message.equalsIgnoreCase("/q")) {
            this.disconnect();
        } else {
            this.addMessage(message + "\n");
            this.write(message);
            this.msgEntryField.setText("");
        }
    }

    public void write(String message) {
        this.output.println(message);
    }

    public static void main(String[] args) {
        String user = "anon";
        if (args.length > 0) {
            user = args[0];
        }

        ChatClient client = new ChatClient(user);
        client.setSize(1000, 700);
        client.connect();
        client.setVisible(true);
    }

    class ChatReader extends Thread {
        boolean done = false;

        ChatReader() {
        }

        public void disconnect() {
            this.done = true;
        }

        public void run() {
            while(!this.done) {
                String message = this.read();
                if (message != null) {
                    ChatClient.this.addMessage(message + "\n");
                }
            }

        }

        String read() {
            return ChatClient.this.input.hasNextLine() ? ChatClient.this.input.nextLine() : null;
        }
    }

    class KeyAction extends AbstractAction {
        String cmd;

        public KeyAction(String command) {
            this.cmd = command;
        }

        public String getCmd() {
            return this.cmd;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (this.cmd.equals("sendMessageCmd")) {
                ChatClient.this.sendMessage();
            }
        }
    }
}
