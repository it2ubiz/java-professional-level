package ru.geekbrains.lesson2.authentication;

import ru.geekbrains.lesson2.database.SQLService;

/**
 * Java Core. Professional level. Lesson 2
 *
 * @author Zurbaevi Nika
 */
public class AuthenticationService {
    public String getNicknameByLoginAndPassword(String login, String password) {
        return SQLService.getNicknameByLoginAndPassword(login, password);
    }

    public boolean registration(String password, String nickname) {
        return SQLService.registration(nickname, password);
    }

    public boolean changeNick(String oldNick, String newNick) {
        return SQLService.changeNick(oldNick, newNick);
    }
}
