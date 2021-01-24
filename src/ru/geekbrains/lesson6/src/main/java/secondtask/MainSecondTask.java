package secondtask;

import java.util.Arrays;

/**
 * Java Core. Professional level. Lesson 6
 *
 * @author Zurbaevi Nika
 */
public class MainSecondTask {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(doIt(new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7})));
        System.out.println(Arrays.toString(doIt(new int[]{4, 2, 85, 7, 96, 5, 2, 2, 2, 1})));
        System.out.println(Arrays.toString(doIt(new int[]{1, 1, 5, 7})));
    }

    public static int[] doIt(int[] array) throws RuntimeException {
        if (array.length == 0)
            throw new RuntimeException("empty array");
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] == 4) {
                if (i == array.length - 1) return new int[]{};
                int[] newArray = new int[array.length - i - 1];
                System.arraycopy(array, i + 1, newArray, 0, array.length - i - 1);
                return newArray;
            }
        }
        throw new RuntimeException("there is no 4 in the array");
    }
}
