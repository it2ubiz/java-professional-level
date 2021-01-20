package ru.geekbrains.lesson5;

/**
 * Java Core. Professional level. Lesson 5
 *
 * @author Nika Zurbaevi
 * @version dated January 19, 2021
 */
public class Tunnel extends Stage {
    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }

    @Override
    public void go(Car c) {
        try {
            System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
            Main.semaphore.acquire();
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(c.getName() + " закончил этап: " + description);
            Main.semaphore.release();
        }
    }
}
