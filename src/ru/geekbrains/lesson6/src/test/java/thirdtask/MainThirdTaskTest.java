package thirdtask;

import org.junit.Assert;
import org.junit.Test;

public class MainThirdTaskTest {

    @Test
    public void doItFirstTest() {
        int[] in = new int[]{2, 2, 2, 2, 3, 3, 3, 3, 3, 7, 7, 7, 7, 7};
        Assert.assertFalse(MainThirdTask.doIt(in));
    }

    @Test
    public void doItSecondTest() {
        int[] in = new int[]{};
        Assert.assertFalse(MainThirdTask.doIt(in));
    }

    @Test
    public void doItThirdTest() {
        int[] in = new int[]{0, 0, 0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 2, 2};
        Assert.assertTrue(MainThirdTask.doIt(in));
    }

    @Test
    public void doItFourthTest() {
        int[] in = new int[]{0, 0, 0, 0, 0, 0, 9, 2, 2, 2, 2, 2, 2, 2, 4};
        Assert.assertTrue(MainThirdTask.doIt(in));
    }
}