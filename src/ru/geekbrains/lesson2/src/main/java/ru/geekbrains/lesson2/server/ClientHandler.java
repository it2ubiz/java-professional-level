package ru.geekbrains.lesson2.server;

import ru.geekbrains.lesson2.database.SQLService;
import ru.geekbrains.lesson2.entity.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

/**
 * Java Core. Professional level. Lesson 2
 *
 * @author Zurbaevi Nika
 */
public class ClientHandler {
    private Server server;
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    private String name;

    public ClientHandler(Server server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            listenForClientMessage();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    private void doAuthentication() {
        try {
            socket.setSoTimeout(120000);
            while (true) {
                boolean checkIsLoggedIn = false;
                String str = dataInputStream.readUTF();
                if (str.startsWith("/auth")) {
                    String[] parts = str.split("\\s");
                    String resultUser = server.getAuthenticationService().getNicknameByLoginAndPassword(parts[1], parts[2]);
                    if (resultUser != null) {
                        User user = new User(parts[1], parts[2]);
                        if (!server.isLoggedIn(user.getNick())) {
                            checkIsLoggedIn = true;
                            sendMessage("Login successful");
                            name = user.getNick();
                            server.broadcastMessage(name + " is logged in");
                            server.subscribe(this);
                            socket.setSoTimeout(0);
                        } else {
                            sendMessage("Authentication error. Account already logged in.");
                        }
                    } else {
                        sendMessage("Wrong login or password");
                    }
                } else if (str.startsWith("/reg")) {
                    String[] token = str.split("\\s");
                    if (server.getAuthenticationService().registration(token[1], token[2])) {
                        sendMessage("Registration success");
                    } else {
                        sendMessage("Registration failed");
                    }
                }
                if (checkIsLoggedIn) {
                    return;
                }
            }
        } catch (IOException exception) {
            SQLService.disconnect();
            exception.printStackTrace();
        }
    }

    private void listenForClientMessage() {
        new Thread(() -> {
            try {
                doAuthentication();
                receiveMsg();
            } catch (Exception exception) {
                exception.printStackTrace();
            } finally {
                server.unsubscribe(this);
            }
        }).start();
    }

    private void receiveMsg() {
        try {
            while (true) {
                StringBuilder message = new StringBuilder(dataInputStream.readUTF());
                if (message.toString().equals("/exit")) {
                    break;
                }
                if (message.toString().equals("/change")) {
                    Scanner scanner = new Scanner(System.in);
                    dataOutputStream.writeUTF("Enter your old name: ");
                    String oldNick = dataInputStream.readUTF();
                    dataOutputStream.writeUTF("Enter your new name: ");
                    String newNick = dataInputStream.readUTF();
                    SQLService.changeNick(oldNick, newNick);
                }
                if (message.toString().contains("/w")) {
                    String[] parts = message.toString().split("\\s");
                    String nickname = parts[1];
                    message = new StringBuilder();
                    for (int i = 0; i < parts.length; i++) {
                        if (i > 1) {
                            message.append(" ").append(parts[i]);
                        }
                    }
                    server.sendPrivateMessage(this, nickname, message.toString());
                } else {
                    server.broadcastMessage(name + ": " + message.toString());
                }
            }
        } catch (Exception exception) {
            SQLService.disconnect();
            exception.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        try {
            dataOutputStream.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ClientHandler that = (ClientHandler) object;
        return Objects.equals(server, that.server) &&
                Objects.equals(socket, that.socket) &&
                Objects.equals(dataInputStream, that.dataInputStream) &&
                Objects.equals(dataOutputStream, that.dataOutputStream) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(server, socket, dataInputStream, dataOutputStream, name);
    }
}
