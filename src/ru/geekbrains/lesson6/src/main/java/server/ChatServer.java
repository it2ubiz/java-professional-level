package server;

import authentication.AuthenticationService;
import database.SQLService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Java Core. Professional level. Lesson 6
 *
 * @author Zurbaevi Nika
 */
public class ChatServer implements Server {
    private static final Logger LOGGER = LogManager.getLogger(ChatServer.class.getName());
    private Set<ClientHandler> clients;
    private AuthenticationService authenticationService;
    private Socket socket;
    private ServerSocket serverSocket;
    private ExecutorService executorService;

    public ChatServer() {
        SQLService.getInstance();
        executorService = Executors.newCachedThreadPool();
        try {
            LOGGER.info("Server is running");
            serverSocket = new ServerSocket(8081);
            clients = new HashSet<>();
            authenticationService = new AuthenticationService();
            while (true) {
                LOGGER.info("Server is waiting for connection");
                socket = serverSocket.accept();
                LOGGER.info("Client connected");
                new ClientHandler(this, socket);
            }
        } catch (Exception exception) {
            LOGGER.error("Server error: " + exception.getMessage());
        } finally {
            SQLService.disconnect();
            executorService.shutdown();
            try {
                serverSocket.close();
            } catch (IOException e) {
                LOGGER.error("Server socket: " + e.getMessage());
            }
            LOGGER.info("Server shut down");
        }
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    @Override
    public synchronized void broadcastMessage(String message) {
        for (ClientHandler clientHandler : clients) {
            clientHandler.sendMessage(message);
        }
    }

    @Override
    public synchronized void sendPrivateMessage(ClientHandler sender, String nickname, String message) {
        for (ClientHandler clientHandler : clients) {
            if (clientHandler.getName().equals(nickname)) {
                clientHandler.sendMessage("From " + sender.getName() + ": " + message);
            }
        }
    }

    @Override
    public synchronized boolean isLoggedIn(String nickname) {
        for (ClientHandler clientHandler : clients) {
            return clientHandler.getName().equals(nickname);
        }
        return false;
    }

    @Override
    public synchronized void subscribe(ClientHandler client) {
        clients.add(client);
    }

    @Override
    public synchronized void unsubscribe(ClientHandler client) {
        clients.remove(client);
    }

    @Override
    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }
}
