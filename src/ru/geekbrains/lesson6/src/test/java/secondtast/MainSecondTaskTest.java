package secondtast;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import secondtask.MainSecondTask;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author Zurbaevi Nika
 */
@RunWith(Parameterized.class)
public class MainSecondTaskTest {

    private int[] in;
    private int[] out;

    public MainSecondTaskTest(int[] in, int[] out) {
        this.in = in;
        this.out = out;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7}, new int[]{1, 7}},
                {new int[]{4, 2, 85, 7, 96, 5, 2, 2, 2, 1}, new int[]{2, 85, 7, 96, 5, 2, 2, 2, 1}},
                {new int[]{1, 3, 7, 5, 4, 4, 3, 2, 7, 9, 1}, new int[]{3, 2, 7, 9, 1}},
        });
    }

    @Test
    public void test() throws RuntimeException {
        Assert.assertArrayEquals(out, MainSecondTask.doIt(in));
    }
}
