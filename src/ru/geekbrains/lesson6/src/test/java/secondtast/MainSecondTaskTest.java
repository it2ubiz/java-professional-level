package secondtast;

import org.junit.Assert;
import org.junit.Test;
import secondtask.MainSecondTask;

public class MainSecondTaskTest {

    @Test
    public void doItFirstTest() {
        int[] in = new int[]{1, 2, 3, 4, 5, 6};
        int[] out = new int[]{5, 6};
        Assert.assertArrayEquals(out, MainSecondTask.doIt(in));
    }

    @Test
    public void doItSecondTest() {
        int[] in = new int[]{1, 4, 3, 0, 5, 6};
        int[] out = new int[]{3, 0, 5, 6};
        Assert.assertArrayEquals(out, MainSecondTask.doIt(in));
    }

    @Test(expected = RuntimeException.class)
    public void doItThirdTest() {
        int[] in = new int[]{};
        MainSecondTask.doIt(in);
    }

    @Test
    public void doItFourthTest() {
        int[] in = new int[]{1, 2, 3, 4};
        int[] out = new int[]{};
        Assert.assertArrayEquals(out, MainSecondTask.doIt(in));
    }

    @Test(expected = RuntimeException.class)
    public void doItFiveTest() {
        int[] in = new int[]{1, 2, 3, 1};
        MainSecondTask.doIt(in);
    }
}