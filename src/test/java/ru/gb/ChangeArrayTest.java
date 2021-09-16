package ru.gb;

import org.junit.Test;
import org.junit.runners.Parameterized;
import ru.gb.arraysActions.ChangeArray;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertArrayEquals;

public class ChangeArrayTest {

    private final ChangeArray changeArray = new ChangeArray();

    private final Number[] arrOriginal;
    private final int[] arrExpected;

    public ChangeArrayTest(Number[] arrOriginal, int[] arrExpected) {
        this.arrOriginal = arrOriginal;
        this.arrExpected = arrExpected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new int[]{1,2,5,4,3,7,2}, new int[]{1,7}, "Case 1"},
                {new int[]{7,3,5,8,2,1}, new int[]{1,3}, "Case 2"},
                {new int[]{4,2,9}, new int[]{2,9}, "Case 3"}
        });
    }

    @Test
    public void shouldArrayMatch() {
        assertArrayEquals("Заданный нами массив должен совпадать с полученным", arrExpected, changeArray.backArrAfterLastFour(arrOriginal));
    }

    @Test(expected = RuntimeException.class)
    public void testExceptionChangeArray() {
        changeArray.backArrAfterLastFour(arrOriginal);

    }
}
