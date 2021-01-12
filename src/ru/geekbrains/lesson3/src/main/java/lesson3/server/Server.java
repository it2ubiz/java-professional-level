package lesson3.server;

import lesson3.authentication.AuthenticationService;

/**
 * Java Core. Professional level. Lesson 2
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
}
