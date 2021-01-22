package secondtast;

import java.util.Arrays;

/**
 * Java Core. Professional level. Lesson 6
 *
 * @author Zurbaevi Nika
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(doIt(new int[]{3, 4, 1, 5, 9, 4, 4, 7, 4, 8, 6})));
        System.out.println(Arrays.toString(doIt(new int[]{4, 9, 8, 7, 6, 5, 4, 3, 2, 1})));
        System.out.println(Arrays.toString(doIt(new int[]{1, 1})));
    }

    public static int[] doIt(int[] array) throws RuntimeException {
        int[] result;
        int i = array.length - 1;
        int j = 0;
        boolean found = false;
        while (i >= 0 && !found) {
            found = array[i] == 4;
            j = i;
            i--;
        }
        if (found) {
            result = new int[array.length - j - 1];
            for (i = j + 1; i < array.length; i++) {
                result[i - j - 1] = array[i];
            }
        } else {
            throw new RuntimeException("there is no 4 in the array");
        }
        return result;
    }
}
