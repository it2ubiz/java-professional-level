package thirdtask;

/**
 * Java Core. Professional level. Lesson 6
 *
 * @author Zurbaevi Nika
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(doIt(new int[]{3, 4, 1, 5, 9, 4, 4, 7, 4, 8, 6}));
        System.out.println(doIt(new int[]{4, 9, 8, 7, 6, 5, 4, 3, 2, 1}));
        System.out.println(doIt(new int[]{5, 7, 5, 27, 16, 7}));
        System.out.println(doIt(new int[]{2, 3, 6, 7, 9}));
    }

    public static boolean doIt(int[] array) {
        for (int j : array) {
            if (j == 1 || j == 4)
                return true;
        }
        return false;
    }
}
