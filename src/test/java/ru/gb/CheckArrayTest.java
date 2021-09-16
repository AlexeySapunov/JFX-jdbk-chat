package ru.gb;

import org.junit.Before;

import org.junit.Test;
import ru.gb.arraysActions.CheckArray;

import static org.junit.Assert.*;

public class CheckArrayTest {

    private CheckArray checkArray;

    @Before
    public void init() {
        checkArray = new CheckArray();
    }

    @Test
    public void checkOneOrFourToArray() {
        final boolean check = checkArray.checkArray(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0});
        assertTrue(check);
    }

    @Test(expected = RuntimeException.class)
    public void checkArrayToAnotherNumbers() {
        final boolean check = checkArray.checkArray(new int[]{1, 3, 4, 5, 6, 7, 8});
        assertFalse("В массиве только 1 и 4?", check);
    }

    @Test(timeout = 1000)
    public void checkArrayToLongWait() {
        int[] data = new int[900000];
        data[0] = 1;
        data[1] = 1;
        for (int i = 2; i < data.length; i++) {
            final double sin = Math.sin(i);
            data[i] = sin > 0.5 ? 1 : 4;
        }
        assertTrue("Ошибка", checkArray.checkArray(data));
    }
}
