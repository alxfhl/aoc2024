package aoc2024;

import java.util.ArrayList;
import java.util.List;

public class Day01 {

    public static long getPart1(List<String> lines) {
        List<Long> leftValues = new ArrayList<>();
        List<Long> rightValues = new ArrayList<>();
        for (String line : lines) {
            line = line.trim();
            if (!line.isBlank()) {
                leftValues.add(Long.valueOf(line.substring(0, line.indexOf(' '))));
                rightValues.add(Long.valueOf(line.substring(line.lastIndexOf(' ') + 1)));
            }
        }
        leftValues.sort(Long::compareTo);
        rightValues.sort(Long::compareTo);
        long sum = 0;
        for (int i = 0; i < leftValues.size(); i++) {
            sum += Math.abs(leftValues.get(i) - rightValues.get(i));
        }
        return sum;
    }

    public static long getPart2(List<String> lines) {
        List<Long> leftValues = new ArrayList<>();
        List<Long> rightValues = new ArrayList<>();
        for (String line : lines) {
            line = line.trim();
            if (!line.isBlank()) {
                leftValues.add(Long.valueOf(line.substring(0, line.indexOf(' '))));
                rightValues.add(Long.valueOf(line.substring(line.lastIndexOf(' ') + 1)));
            }
        }
        long sum = 0;
        for (Long leftValue : leftValues) {
            sum += leftValue * rightValues.stream()
                    .filter(rightValue -> rightValue.equals(leftValue)).count();
        }
        return sum;
    }

}
