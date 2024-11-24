// Project Prolog
// Name: Al Baker
// CS3250 Section 601
// Project: Assignment 13
// Date: 11/24/2024
// Purpose: Connect to the chat server and send and receive messages.

package com.almariebaker.assignment13;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.border.Border;

public class ChatClient extends JFrame {
    public static final int DEFAULT_PORT = 4688;
    Border padding = BorderFactory.createEmptyBorder(20, 20, 20, 20);
    ChatReader chatReader;
    JTextArea msgTextArea;
    JScrollPane scrollPane;
    JTextField msgEntryField;
    Socket socket;
    Scanner input;
    PrintWriter output;
    String username = "anon";

    synchronized void addMessage(String message) {
        this.msgTextArea.append(message);
        this.msgTextArea.setCaretPosition(this.msgTextArea.getDocument().getLength());
    }

    public ChatClient(String user) {
        this.username = user;
        this.setTitle("Chat: " + user);

        this.setLayout(new BorderLayout());
        this.msgTextArea = new JTextArea();
        this.msgTextArea.setBorder(this.padding);
        this.msgTextArea.setEditable(false);
        this.msgTextArea.setFocusable(false);
        this.scrollPane = new JScrollPane(this.msgTextArea);
        this.scrollPane.setBorder(this.padding);

        this.msgEntryField = new JTextField();
        JPanel userInput = new JPanel();
        userInput.setLayout(new BoxLayout(userInput, BoxLayout.X_AXIS));
        userInput.setBorder(this.padding);
        userInput.add(this.msgEntryField);

        JButton sendButton = new JButton("Send");
        sendButton.addActionListener((e) -> { this.sendMessage(); });
        userInput.add(sendButton);
        JButton disconnectButton = new JButton("Quit");
        disconnectButton.addActionListener((e) -> { this.disconnect(); });
        userInput.add(disconnectButton);

        this.add(this.scrollPane, "Center");
        this.add(userInput, "South");

        InputMap inputMap = this.msgEntryField.getInputMap(JComponent.WHEN_FOCUSED);
        inputMap.put(KeyStroke.getKeyStroke(10, 0), "sendMessageCmd");

        ActionMap actionMap = this.msgEntryField.getActionMap();
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
        } else if (message.equalsIgnoreCase("connect")) {
            this.connect();
            this.msgEntryField.setText("");
        } else {
            this.addMessage(this.username + ": " + message + "\n");
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
