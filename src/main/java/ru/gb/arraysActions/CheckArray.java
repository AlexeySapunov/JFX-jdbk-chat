package ru.gb.arraysActions;

public class CheckArray {

    public boolean checkArray(int[] num) {
        boolean one = false;
        boolean four = false;

        for (int j : num) {
            if (one & four) break;
            if (j == 1) one = true;
            if (j == 4) four = true;
        }

        return one && four;
    }

}
