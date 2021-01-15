package lesson4.secondtask.server;

import lesson4.secondtask.authentication.AuthenticationService;

import java.util.concurrent.ExecutorService;

/**
 * Java Core. Professional level. Lesson 4
 *
 * @author Zurbaevi Nika
 */
public interface Server {
    void broadcastMessage(String message);

    void sendPrivateMessage(ClientHandler sender, String nickname, String message);

    boolean isLoggedIn(String nickname);

    void subscribe(ClientHandler client);

    void unsubscribe(ClientHandler client);

    AuthenticationService getAuthenticationService();

    ExecutorService getExecutorService();
}
