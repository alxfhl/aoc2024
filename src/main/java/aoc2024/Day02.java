package aoc2024;

import aoc2024.tools.Parse;

import java.util.ArrayList;
import java.util.List;

public class Day02 {

    public static long getPart1(List<String> lines) {
        long count = 0;
        for (String line : lines) {
            List<Integer> levels = Parse.getIntegers(line);
            if (isSave(levels)) {
                count++;
            }
        }
        return count;
    }

    public static boolean isSave(List<Integer> levels) {
        if (isInc(levels.get(0), levels.get(1))) {
            for (int i = 1; i < levels.size() - 1; i++) {
                if (!isInc(levels.get(i), levels.get(i + 1))) {
                    return false;
                }
            }
            return true;
        }
        if (isDec(levels.get(0), levels.get(1))) {
            for (int i = 1; i < levels.size() - 1; i++) {
                if (!isDec(levels.get(i), levels.get(i + 1))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static boolean isInc(int i1, int i2) {
        return i2 > i1 && i2 <= i1 + 3;
    }

    public static boolean isDec(int i1, int i2) {
        return i2 < i1 && i2 >= i1 - 3;
    }

    public static long getPart2(List<String> lines) {
        long count = 0;
        for (String line : lines) {
            List<Integer> levels = Parse.getIntegers(line);
            for (int i = 0; i < levels.size(); i++) {
                List<Integer> copy = new ArrayList<>(levels);
                copy.remove(i);
                if (isSave(copy)) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }

}
