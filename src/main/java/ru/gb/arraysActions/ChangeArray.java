package ru.gb.arraysActions;

public class ChangeArray {

    public <T extends Number> int[] backArrAfterLastFour(final T[] arr) {
        Number[] result = null;

        for (int i = arr.length - 1; i > 0; i--) {
            if (arr[i].equals(4)) {
                result = new Number[arr.length - i - 1];
                System.arraycopy(arr, i + 1, result, 0, arr.length - i - 1);
                break;
            }
        }

        if (result == null) throw new RuntimeException("В исходном массиве не встретилось ни одной 4-ки!");

        return null;
    }

}
