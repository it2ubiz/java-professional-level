package ru.geekbrains.lesson5;

/**
 * Java Core. Professional level. Lesson 5
 *
 * @author Nika Zurbaevi
 * @version dated January 19, 2021
 */
public abstract class Stage {
    protected int length;
    protected String description;

    public String getDescription() {
        return description;
    }

    public abstract void go(Car c);
}