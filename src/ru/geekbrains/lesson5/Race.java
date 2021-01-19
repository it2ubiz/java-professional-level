package ru.geekbrains.lesson5;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Java Core. Professional level. Lesson 5
 *
 * @author Nika Zurbaevi
 * @version dated January 19, 2021
 */
public class Race {
    private ArrayList<Stage> stages;

    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }

    public ArrayList<Stage> getStages() {
        return stages;
    }
}
