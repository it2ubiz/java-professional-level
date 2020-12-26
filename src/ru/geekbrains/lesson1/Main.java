package ru.geekbrains.lesson1;


import java.util.ArrayList;
import java.util.Arrays;

/**
 * Java Core. Professional level. Lesson 1
 *
 * @author Nika Zurbaevi
 * @version dated December 25, 2020
 */
public class Main {
    public static void main(String[] args) {
        // task 1
        String[] strings = new String[]{"Hello", "my", "name", "is", "Nikolai"};
        twoItemSwap(strings, 0, strings.length - 1);

        // task 2
        convertToArrayList(strings);

        // task 3
        Apple firstApple = new Apple();
        Apple secondApple = new Apple();
        Orange firstOrange = new Orange();
        Orange secondOrange = new Orange();

        Box<Apple> appleBoxFirst = new Box<>();
        Box<Apple> appleBoxSecond = new Box<>();
        Box<Orange> orangeBoxFirst = new Box<>();
        Box<Orange> orangeBoxSecond = new Box<>();

        appleBoxFirst.addFruit(firstApple);
        appleBoxFirst.addFruit(secondApple);
        appleBoxSecond.addFruit(secondApple);
        appleBoxSecond.addFruit(secondApple);
        appleBoxSecond.addFruit(secondApple);
        appleBoxSecond.addFruit(secondApple);
        orangeBoxFirst.addFruit(firstOrange);
        orangeBoxFirst.addFruit(secondOrange);
        orangeBoxSecond.addFruit(firstOrange);
        orangeBoxSecond.addFruit(secondOrange);

        System.out.println("compare appleBox to orangeBox: " + appleBoxFirst.compare(orangeBoxFirst));
        System.out.println("compare orangeBox to appleBox: " + orangeBoxFirst.compare(appleBoxFirst));
        System.out.println("compare orangeBox to orangeBox: " + orangeBoxFirst.compare(orangeBoxFirst));
        System.out.println("compare appleBox to appleBox: " + appleBoxFirst.compare(appleBoxFirst));
        appleBoxFirst.intersperse(appleBoxSecond);
        System.out.println("appleBoxFirst.intersperse(appleBoxSecond) size appleBoxFirst: " + appleBoxFirst.getSize());
        System.out.println("appleBoxFirst.intersperse(appleBoxSecond) size appleBoxSecond: " + appleBoxSecond.getSize());
        orangeBoxSecond.intersperse(orangeBoxFirst);
        System.out.println("orangeBoxSecond.intersperse(orangeBoxFirst) size orangeBoxSecond: " + orangeBoxSecond.getSize());
        System.out.println("orangeBoxSecond.intersperse(orangeBoxFirst) size orangeBoxFirst: " + orangeBoxFirst.getSize());
    }

    public static <T> void twoItemSwap(T[] array, int firsItem, int secondItem) {
        T temp = array[firsItem];
        array[firsItem] = array[secondItem];
        array[secondItem] = temp;
        System.out.println("twoItemsSwap(" + firsItem + ", " + secondItem + "): " + Arrays.asList(array) + "\n");
    }

    public static <T> ArrayList<T> convertToArrayList(T array) {
        return new ArrayList<>(Arrays.asList(array));
    }
}
